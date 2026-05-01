package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.Clases.Prestamo;
import com.biblioteca.Enum.Estado;

public class DAOprestamos {

    public DAOprestamos() {
    }

    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS prestamos (
                    id                          INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_usuario                  INTEGER NOT NULL,
                    id_libro                    INTEGER NOT NULL,
                    fecha_prestamo              TEXT NOT NULL,
                    fecha_devolucion_prevista   TEXT NOT NULL,
                    fecha_devolucion_real       TEXT,
                    estado                      TEXT NOT NULL DEFAULT 'ACTIVO',
                    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
                    FOREIGN KEY (id_libro)   REFERENCES libros(id)
                );
                """;

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla PRESTAMOS lista");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // public Prestamo(int idUsuario, int idLibro, LocalDate fechaPrestamo,
    // LocalDate fechaDevolucionPrevista, LocalDate fechaDevolucionReal, Estado
    // estado)
    public void insertarUsuario(Prestamo prestamo) {
        String sql = """
                INSERT INTO prestamos(int idUsuario, int idLibro, LocalDate fechaPrestamo, LocalDate fechaDevolucionPrevista, LocalDate fechaDevolucionReal, Estado estado)
                VALUES(?, ?, ?, ?, ?, ?)
                        """;

        try (Connection conn = Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, prestamo.getIdUsuario());
            pstmt.setInt(2, prestamo.getIdLibro());
            pstmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            pstmt.setDate(4, Date.valueOf(prestamo.getFechaDevolucionPrevista()));
            pstmt.setDate(5, Date.valueOf(prestamo.getFechaDevolucionReal()));
            pstmt.setString(6, prestamo.getEstado().name());

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

    public List<Prestamo> obtenerTodosLosPrestamos() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idUsuario = rs.getInt("idUsuario");
                int idLibro = rs.getInt("idLibro");
                LocalDate fechaPrestamo = LocalDate.parse(rs.getString("fechaPrestamo"));
                LocalDate fechaDevolucionPrevista = LocalDate.parse(rs.getString("fechaDevolucionPrevista"));
                LocalDate fechaDevolucionReal = LocalDate.parse(rs.getString("fechaDevolucionReal"));
                Estado estado = Estado.valueOf(rs.getString("estado"));

                lista.add(new Prestamo(id, idUsuario, idLibro, fechaPrestamo, fechaDevolucionPrevista,
                        fechaDevolucionReal, estado));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // TODO pendiente por revisar
    public void prestamosActivosDeUnUsuario() {
        String sql = """
                SELECT l.titulo, p.fecha_prestamo, p.fecha_devolucion_prevista
                FROM prestamos p
                JOIN libros l ON p.id_libro = l.id
                WHERE p.id_usuario = ? AND p.estado = 'activo'""";

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de usuarios:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("nombre") + " - " +
                                rs.getInt("edad"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO pendiente por revisar
    public void libroMasPrestado() {
    }

    // TODO pendiente por revisar
    public void generoConMasPrestamos() {
        String sql = """
                SELECT l.genero, COUNT(*) AS total
                FROM prestamos p
                JOIN libros l ON p.id_libro = l.id
                GROUP BY l.genero
                ORDER BY total DESC""";

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de usuarios:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("nombre") + " - " +
                                rs.getInt("edad"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
