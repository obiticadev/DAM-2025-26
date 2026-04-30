package com.biblioteca.DOM;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.biblioteca.Clases.Usuario;

public class DAOusuarios {
    private final Conexion conexion;

    public DAOusuarios() {
        this.conexion = new Conexion();
    }

    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id               INT           NOT NULL AUTO_INCREMENT,
                    nombre           VARCHAR(100)  NOT NULL,
                    apellidos        VARCHAR(150)  NOT NULL,
                    email            VARCHAR(200)  UNIQUE,
                    telefono         VARCHAR(20),
                    fecha_registro   DATE          NOT NULL,
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

    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre, apellidos, email, telefono, fecha_registro) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = conexion.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getTelefono());
            pstmt.setDate(5, Date.valueOf(usuario.getFechaRegistro()));
            pstmt.executeUpdate();

            System.out.println("Usuario insertado: " + usuario.getNombre());

        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error con la Base de Datos");
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

}
