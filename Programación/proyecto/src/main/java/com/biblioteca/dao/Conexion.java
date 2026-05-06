package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // TODO [BUG / DECISIÓN DE DISEÑO] El patrón Singleton actual está en conflicto con try-with-resources.
    //  ─────────────────────────────────────────────────────────────────────────
    //  PROBLEMA: Todos los DAOs usan try(Connection conn = Conexion.getConexion()) { ... }
    //  lo cual CIERRA la conexión singleton al salir del bloque. La siguiente llamada a getConexion()
    //  detecta isClosed() y crea una nueva. Esto anula las ventajas del Singleton (reutilización).
    //
    //  OPCIONES (elige UNA):
    //
    //  OPCIÓN A — Eliminar Singleton, devolver conexión nueva cada vez:
    //    public static Connection getConexion() throws SQLException {
    //        return DriverManager.getConnection(URL);
    //    }
    //    → Los DAOs seguirían usando try-with-resources normalmente.
    //    → Cada operación abre y cierra su propia conexión (patrón más seguro para SQLite).
    //
    //  OPCIÓN B — Mantener Singleton, NO usar try-with-resources en los DAOs:
    //    → Quitar "Connection conn = Conexion.getConexion()" del try(...).
    //    → Cerrar la conexión solo al final del programa con Conexion.cerrarConexion().
    //    → Riesgo: si olvidas cerrar, se queda abierta indefinidamente.
    //  ─────────────────────────────────────────────────────────────────────────

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