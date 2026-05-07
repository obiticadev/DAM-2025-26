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

    private List<Usuario> listaUsuarios;

    public DAOusuarios() {
        // TODO [COMPLETAR] Inicializar lista correctamente si vas a usarla de caché
        this.listaUsuarios = new ArrayList<>();
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
                new Logs("Usuario insertado: " + usuario.getNombre() + " " + usuario.getApellido(), Aviso.INFO)
                        .guardarLog();
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

                String apellido = rs.getString("apellidos");

                String email = rs.getString("email");
                String telefono = rs.getString("telefono");

                LocalDate fechaRegistro = LocalDate.parse(rs.getString("fecha_registro"));

                lista.add(new Usuario(id, nombre, apellido, email, telefono, fechaRegistro));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Logs("Error al obtener usuarios: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }

        new Logs("Obtenidos " + lista.size() + " usuarios", Aviso.INFO).guardarLog();
        return lista;
    }

    // TODO [PRÁCTICA STREAMS] Implementar buscarUsuarioPorId(int id).
    // → Objetivo: Obtener la lista de usuarios con obtenerTodosLosUsuarios() y
    // buscar el que coincida con el id usando filter() y findFirst() de Streams.
    // → Devolver un Optional<Usuario> o null si no se encuentra.
    // → Se necesita para validar que el usuario existe antes de crear un préstamo.
    public void buscarUsuarioPorId(int id) {

    }

    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, apellidos = ?, email = ?, telefono = ? WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getTelefono());
            pstmt.setInt(5, usuario.getId());
            int num = pstmt.executeUpdate();
            if (num > 0) {
                new Logs("Usuario actualizado: " + usuario.getId(), Aviso.INFO).guardarLog();
                return true;
            }
        } catch (SQLException e) {
            new Logs("Error actualizando usuario: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
        return false;
    }

    public boolean eliminarUsuario(int id) {
        String checkSql = "SELECT COUNT(*) FROM prestamos WHERE id_usuario = ? AND estado = 'ACTIVO'";
        try (Connection conn = Conexion.getConexion();
                PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("No se puede eliminar: el usuario tiene préstamos activos.");
                new Logs("Intento fallido de eliminar usuario " + id + " con préstamos activos", Aviso.AVISO)
                        .guardarLog();
                return false;
            }
        } catch (SQLException e) {
            new Logs("Error comprobando préstamos de usuario: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
            return false;
        }

        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = Conexion.getConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int num = pstmt.executeUpdate();
            if (num > 0) {
                new Logs("Usuario eliminado: " + id, Aviso.INFO).guardarLog();
                return true;
            }
        } catch (SQLException e) {
            new Logs("Error eliminando usuario: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
        return false;
    }

}
