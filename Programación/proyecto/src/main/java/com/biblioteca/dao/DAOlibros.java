package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
                int anioPublicacion = rs.getInt("anio_Publicacion");

                // TODO [BUG] Nombres de columna incorrectos (usar snake_case):
                // → "copiasTotales" → "copias_totales"
                // → "copiasDisponibles" → "copias_disponibles"
                int copiasTotales = rs.getInt("copiasTotales");
                int copiasDisponibles = rs.getInt("copiasDisponibles");

                Tipo tipo = Tipo.valueOf(rs.getString("tipo"));

                if (tipo == Tipo.ELECTRONICO) {
                    Formato formato = Formato.valueOf(rs.getString("formato"));
                    // TODO [BUG] Columna "url" no existe → usar "url_descarga"
                    String url = rs.getString("url");
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

    // TODO [CÓDIGO FALTANTE] Implementar librosDisponibles().
    // → SQL: SELECT * FROM libros WHERE copias_disponibles > 0
    // → Devolver List<Libro>. Reutilizar lógica de mapeo (ver RECOMENDACIÓN abajo).
    // → Imprimir resultados en consola.
    public void librosDisponibles() {
        new Logs("Consulta de libros disponibles", Aviso.INFO).guardarLog();
    }

    // TODO [CÓDIGO FALTANTE] Reemplazar filtrarPorX() por:
    // 1) buscarPorAutor(String autor) → SQL: WHERE autor LIKE '%' || ? || '%'
    // 2) buscarPorGenero(Genero genero) → SQL: WHERE genero = ?
    // → Añadir opciones en el menú de libros de App.java.
    public void filtrarPorX() {
        new Logs("Filtro de libros por criterio", Aviso.INFO).guardarLog();
    }

    // TODO [CÓDIGO FALTANTE] Implementar buscarLibroPorId(int id).
    // → SQL: SELECT * FROM libros WHERE id = ?
    // → Necesario para validar existencia antes de crear préstamo.

    // TODO [CÓDIGO FALTANTE] Implementar actualizarLibro(Libro libro).
    // → SQL: UPDATE libros SET titulo=?, autor=?, ... WHERE id=?

    // TODO [CÓDIGO FALTANTE] Implementar eliminarLibro(int id).
    // → Verificar que no tenga préstamos activos antes de borrar.

    // TODO [RECOMENDACIÓN] Extraer método privado mapearLibro(ResultSet rs).
    // → Evita duplicar la lógica de mapeo en cada método SELECT.
}
