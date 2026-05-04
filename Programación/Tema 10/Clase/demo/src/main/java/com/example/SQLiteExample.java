package com.example;

import java.sql.*;

public class SQLiteExample {

    
    private static final  String URL = "jdbc:sqlite:mi_base.db";
    //private static final  String URL = "jdbc:sqlite:" + System.getProperty("user.home") + "/miapp/mi_base.db"; 
    public static void main(String[] args) {
        crearTabla();
        insertarUsuario("Juan", 25);
        insertarUsuario("Ana", 30);
        mostrarUsuarios();
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void crearTabla() {
        String sql = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                edad INTEGER
            );
        """;

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla creada correctamente");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarUsuario(String nombre, int edad) {
        String sql = "INSERT INTO usuarios(nombre, edad) VALUES(?, ?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setInt(2, edad);
            pstmt.executeUpdate();

            System.out.println("Usuario insertado: " + nombre);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarUsuarios() {
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de usuarios:");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("nombre") + " - " +
                    rs.getInt("edad")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}