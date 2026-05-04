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
                    tipo                TEXT NOT NULL, -- 'PAPEL' o 'ELECTRONICO'
                    -- Campos específicos
                    formato             TEXT,          -- Solo para electrónico
                    url_descarga        TEXT,          -- Solo para electrónico
                    ubicacion           TEXT           -- Solo para papel
                );
                """;

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla LIBROS lista");

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
            pstmt.setInt(5, libro.getAnioPublicacion().getYear());
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
            } else {
                System.out.println("Ha habido un error en alguna parte");
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
                LocalDate anioPublicacion = LocalDate.parse(rs.getString("anioPublicacion"));
                int copiasTotales = rs.getInt("copiasTotales");
                int copiasDisponibles = rs.getInt("copiasDisponibles");
                Tipo tipo = Tipo.valueOf(rs.getString("tipo"));

                if (tipo == Tipo.ELECTRONICO) {
                    Formato formato = Formato.valueOf(rs.getString("formato"));
                    String url = rs.getString("url");

                    lista.add(new LibroElectronico(id, titulo, autor, genero, isbn, anioPublicacion, copiasTotales,
                            copiasDisponibles, tipo, id, formato, url));
                } else {
                    String ubicacion = rs.getString("ubicacion");

                    lista.add(new LibroEnPapel(id, titulo, autor, genero, isbn, anioPublicacion, copiasTotales,
                            copiasDisponibles, tipo, id, ubicacion));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // TODO pendiente por revisar
    public void librosDisponibles() {
    }

    // TODO pendiente por revisar
    public void filtrarPorX() {
    }
}
