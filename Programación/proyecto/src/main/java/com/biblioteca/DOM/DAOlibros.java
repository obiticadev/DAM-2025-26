package com.biblioteca.DOM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOlibros {
    private final Conexion conexion;

    public DAOlibros() {
        this.conexion = new Conexion();
    }

    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS libros (
                    id                  INT           NOT NULL AUTO_INCREMENT,
                    titulo              VARCHAR(255)  NOT NULL,
                    autor               VARCHAR(255)  NOT NULL,
                    genero              VARCHAR(100)  NOT NULL,
                    isbn                VARCHAR(20)   NOT NULL UNIQUE,
                    anio_publicacion    INT           NOT NULL,
                    copias_totales      INT           NOT NULL DEFAULT 1,
                    copias_disponibles  INT           NOT NULL,
                    tipo                ENUM('papel', 'electronico') NOT NULL,
                    PRIMARY KEY (id)
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
    public void librosDisponibles() {
        String sql = "SELECT * FROM libros WHERE copias_disponibles > 0";

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
    public void filtrarPorAutor() {
        String sql = "SELECT * FROM libros WHERE autor = ?";

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
    public void filtrarPorGenero() {
        String sql = "SELECT * FROM libros WHERE genero = ?";

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
