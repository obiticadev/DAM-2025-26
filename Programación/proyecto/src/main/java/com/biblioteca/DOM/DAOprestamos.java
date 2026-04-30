package com.biblioteca.DOM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOprestamos {
    private final Conexion conexion;

    public DAOprestamos() {
        this.conexion = new Conexion();
    }

    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS prestamos (
                    id                          INT   NOT NULL AUTO_INCREMENT,
                    id_usuario                  INT   NOT NULL,
                    id_libro                    INT   NOT NULL,
                    fecha_prestamo              DATE  NOT NULL,
                    fecha_devolucion_prevista   DATE  NOT NULL,
                    fecha_devolucion_real       DATE  NULL,
                    estado                      ENUM('activo', 'devuelto', 'retrasado') NOT NULL DEFAULT 'activo',
                    PRIMARY KEY (id),
                    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
                    FOREIGN KEY (id_libro)   REFERENCES libros(id)
                );
                """;

        try (Connection conn = conexion.conectar();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla creada correctamente");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO EJEMPLO -- Borrar o modificar
    public void insertarUsuario(String nombre, int edad) {
        String sql = "INSERT INTO usuarios(nombre, edad) VALUES(?, ?)";

        try (Connection conn = conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setInt(2, edad);
            pstmt.executeUpdate();

            System.out.println("Usuario insertado: " + nombre);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO EJEMPLO -- Borrar o modificar
    public void mostrarUsuarios() {
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = conexion.conectar();
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
    public void prestamosActivosDeUnUsuario() {
        String sql = """
                SELECT l.titulo, p.fecha_prestamo, p.fecha_devolucion_prevista
                FROM prestamos p
                JOIN libros l ON p.id_libro = l.id
                WHERE p.id_usuario = ? AND p.estado = 'activo'""";

        try (Connection conn = conexion.conectar();
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
        String sql = """
                SELECT id_libro, COUNT(*) AS total
                FROM prestamos
                GROUP BY id_libro
                ORDER BY total DESC
                LIMIT 1""";

        try (Connection conn = conexion.conectar();
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
    public void generoConMasPrestamos() {
        String sql = """
                SELECT l.genero, COUNT(*) AS total
                FROM prestamos p
                JOIN libros l ON p.id_libro = l.id
                GROUP BY l.genero
                ORDER BY total DESC""";

        try (Connection conn = conexion.conectar();
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
