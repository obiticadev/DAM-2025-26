package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.biblioteca.Clases.Libro;
import com.biblioteca.Clases.LibroElectronico;
import com.biblioteca.Clases.LibroEnPapel;
import com.biblioteca.Enum.Aviso;
import com.biblioteca.Enum.Formato;
import com.biblioteca.Enum.Genero;
import com.biblioteca.Enum.Tipo;

public class DAOlibros {

    public DAOlibros() {
    }

    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS libros (
                    id                  INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo              TEXT NOT NULL,
                    autor               TEXT NOT NULL,
                    genero              TEXT NOT NULL,
                    isbn                TEXT NOT NULL UNIQUE,
                    anio_publicacion    INTEGER NOT NULL,
                    copias_totales      INTEGER NOT NULL DEFAULT 1,
                    copias_disponibles  INTEGER NOT NULL,
                    tipo                TEXT NOT NULL,
                    formato             TEXT,
                    url_descarga        TEXT,
                    ubicacion           TEXT
                );
                """;

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla LIBROS lista");
            new Logs("Tabla libros creada", Aviso.INFO).guardarLog();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarLibro(Libro libro) {
        String sql = """
                INSERT INTO libros (titulo, autor, genero, isbn, anio_publicacion,
                                   copias_totales, copias_disponibles, tipo,
                                   formato, url_descarga, ubicacion)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getGenero().name());
            pstmt.setString(4, libro.getIsbn());
            pstmt.setInt(5, libro.getAnioPublicacion());
            pstmt.setInt(6, libro.getCopiasTotales());
            pstmt.setInt(7, libro.getCopiasDisponibles());
            pstmt.setString(8, libro.getTipo().name());

            if (libro instanceof LibroElectronico le) {
                pstmt.setString(9, le.getFormato().name());
                pstmt.setString(10, le.getUrlDescarga());
                pstmt.setNull(11, Types.VARCHAR);
            } else if (libro instanceof LibroEnPapel lp) {
                pstmt.setNull(9, Types.VARCHAR);
                pstmt.setNull(10, Types.VARCHAR);
                pstmt.setString(11, lp.getUbicacion());
            }

            int num = pstmt.executeUpdate();
            if (num > 0) {
                System.out.println("Insertado correctamente");
                new Logs("Libro insertado: " + libro.getTitulo(), Aviso.INFO).guardarLog();
            } else {
                System.out.println("Ha habido un error en alguna parte");
                new Logs("Error al insertar libro: " + libro.getTitulo(), Aviso.AVISO).guardarLog();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error de BD al insertar libro: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
    }

    public List<Libro> obtenerTodosLosLibros() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros";

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                Genero genero = Genero.valueOf(rs.getString("genero"));
                String isbn = rs.getString("isbn");
                int anioPublicacion = rs.getInt("anio_publicacion");

                int copiasTotales = rs.getInt("copias_totales");
                int copiasDisponibles = rs.getInt("copias_disponibles");

                Tipo tipo = Tipo.valueOf(rs.getString("tipo"));

                if (tipo == Tipo.ELECTRONICO) {
                    Formato formato = Formato.valueOf(rs.getString("formato"));

                    String url = rs.getString("url_descarga");
                    lista.add(new LibroElectronico(id, titulo, autor, genero, isbn,
                            anioPublicacion, copiasTotales, copiasDisponibles, tipo, id, formato, url));
                } else {
                    String ubicacion = rs.getString("ubicacion");
                    lista.add(new LibroEnPapel(id, titulo, autor, genero, isbn,
                            anioPublicacion, copiasTotales, copiasDisponibles, tipo, id, ubicacion));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error al obtener libros: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
        new Logs("Obtenidos " + lista.size() + " libros", Aviso.INFO).guardarLog();
        return lista;
    }

    public List<Libro> librosDisponibles() {
        new Logs("Consulta de libros disponibles", Aviso.INFO).guardarLog();
        return obtenerTodosLosLibros().stream()
                .filter(a -> a.getCopiasDisponibles() > 0)
                .collect(Collectors.toList());
    }

    // TODO [PRÁCTICA STREAMS] Reemplazar filtrarPorX() por:
    // 1) buscarPorAutor(String autor)
    // 2) buscarPorGenero(Genero genero)
    // → Objetivo: Recuperar la lista completa de libros y utilizar Streams
    // (.filter()) para encontrar los que coinciden con el autor o género pasado por
    // parámetro.
    // → Añadir opciones en el menú de libros de App.java.
    public void filtrarPorX() {
        new Logs("Filtro de libros por criterio", Aviso.INFO).guardarLog();
    }

    public Optional<Libro> buscarLibroPorId(int id) {
        new Logs("Búsqueda del libro por ID", Aviso.INFO).guardarLog();
        return obtenerTodosLosLibros().stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    public boolean actualizarLibro(Libro libro) {
        String sql = "UPDATE libros SET titulo=?, autor=?, genero=?, isbn=?, anio_publicacion=?, copias_totales=?, copias_disponibles=?, tipo=?, formato=?, url_descarga=?, ubicacion=? WHERE id=?";
        try (Connection conn = Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getGenero().name());
            pstmt.setString(4, libro.getIsbn());
            pstmt.setInt(5, libro.getAnioPublicacion());
            pstmt.setInt(6, libro.getCopiasTotales());
            pstmt.setInt(7, libro.getCopiasDisponibles());
            pstmt.setString(8, libro.getTipo().name());

            if (libro instanceof LibroElectronico le) {
                pstmt.setString(9, le.getFormato().name());
                pstmt.setString(10, le.getUrlDescarga());
                pstmt.setNull(11, Types.VARCHAR);
            } else if (libro instanceof LibroEnPapel lp) {
                pstmt.setNull(9, Types.VARCHAR);
                pstmt.setNull(10, Types.VARCHAR);
                pstmt.setString(11, lp.getUbicacion());
            } else {
                pstmt.setNull(9, Types.VARCHAR);
                pstmt.setNull(10, Types.VARCHAR);
                pstmt.setNull(11, Types.VARCHAR);
            }
            pstmt.setInt(12, libro.getId());

            int num = pstmt.executeUpdate();
            if (num > 0) {
                new Logs("Libro actualizado: " + libro.getId(), Aviso.INFO).guardarLog();
                return true;
            }
        } catch (SQLException e) {
            new Logs("Error actualizando libro: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
        return false;
    }

    public boolean eliminarLibro(int id) {
        String checkSql = "SELECT COUNT(*) FROM prestamos WHERE id_libro = ? AND estado = 'ACTIVO'";
        try (Connection conn = Conexion.getConexion();
                PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("No se puede eliminar: el libro tiene préstamos activos.");
                new Logs("Intento fallido de eliminar libro " + id + " con préstamos activos", Aviso.AVISO)
                        .guardarLog();
                return false;
            }
        } catch (SQLException e) {
            new Logs("Error comprobando préstamos de libro: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
            return false;
        }

        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int num = pstmt.executeUpdate();
            if (num > 0) {
                new Logs("Libro eliminado: " + id, Aviso.INFO).guardarLog();
                return true;
            }
        } catch (SQLException e) {
            new Logs("Error eliminando libro: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
        return false;
    }

    // TODO [RECOMENDACIÓN] Extraer método privado mapearLibro(ResultSet rs).
    // → Evita duplicar la lógica de mapeo en cada método SELECT.
}
