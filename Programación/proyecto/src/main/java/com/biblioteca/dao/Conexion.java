package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // 1. Atributo estático que almacenará la única instancia de la conexión
    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:biblioteca.db";

    // 2. Constructor PRIVADO para evitar que se creen instancias con "new
    // Conexion()"
    private Conexion() {
    }

    // 3. Método estático público para obtener la conexión
    public static Connection getConexion() {
        try {
            // Si la conexión no existe o se cerró, la creamos
            if (instance == null || instance.isClosed()) {
                instance = DriverManager.getConnection(URL);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con SQLite: " + e.getMessage());
        }
        return instance;
    }

    // Opcional: Método para cerrar la conexión de forma segura
    public static void cerrarConexion() {
        if (instance != null) {
            try {
                instance.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}