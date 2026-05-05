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

import com.biblioteca.Clases.Usuario;
import com.biblioteca.Enum.Aviso;

public class DAOusuarios {

    public DAOusuarios() {
    }

    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id               INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre           TEXT NOT NULL,
                    apellidos        TEXT NOT NULL,
                    email            TEXT UNIQUE,
                    telefono         TEXT,
                    fecha_registro   TEXT NOT NULL
                );
                """;

        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla USUARIOS lista");
            new Logs("Tabla usuarios creada", Aviso.INFO).guardarLog();

        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error al crear tabla usuarios: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
    }

    // public Usuario(int id, String nombre, String apellido, String email, String
    // telefono, LocalDate fechaRegistro)
    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre, apellidos, email, telefono, fecha_registro) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getTelefono());
            pstmt.setDate(5, Date.valueOf(usuario.getFechaRegistro()));

            int num = pstmt.executeUpdate();

            if (num > 0) {
                System.out.println("Insertado correctamente");
                new Logs("Usuario insertado: " + usuario.getNombre() + " " + usuario.getApellido(), Aviso.INFO).guardarLog();
            } else {
                System.out.println("Ha habido un error en alguna parte");
                new Logs("Error al insertar usuario: " + usuario.getNombre(), Aviso.AVISO).guardarLog();
            }

        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error con la Base de Datos");
            new Logs("Error de base de datos al insertar usuario: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = Conexion.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                LocalDate fechaRegistro = LocalDate.parse(rs.getString("fechaRegistro"));

                lista.add(new Usuario(id, nombre, apellido, email, telefono, fechaRegistro));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error al obtener usuarios: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }

        new Logs("Obtenidos " + lista.size() + " usuarios", Aviso.INFO).guardarLog();
        return lista;
    }

}
