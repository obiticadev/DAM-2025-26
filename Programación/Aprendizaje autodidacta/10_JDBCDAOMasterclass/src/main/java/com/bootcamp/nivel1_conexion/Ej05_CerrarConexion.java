package com.bootcamp.nivel1_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Ej05 — Cierre seguro de la conexión
 * Teoría: teoria/01_Conexion_JDBC_Singleton.md
 *
 * Objetivo: implementar el cierre defensivo de la conexión,
 * verificando el estado antes de intentar cerrar.
 * Cerrar una conexión ya cerrada lanza SQLException — hay que evitarlo.
 */
public class Ej05_CerrarConexion {

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej05.db";

    public static Connection getConexion() {
        try {
            if (instance == null || instance.isClosed()) {
                instance = DriverManager.getConnection(URL);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
        return instance;
    }

    /**
     * Cierra la conexión Singleton solo si está activa.
     * No lanza excepción en ningún caso — la captura internamente.
     * Imprime un mensaje indicando si cerró o si ya estaba cerrada/null.
     */
    public static void cerrarSiAbierta() {
        // TODO 1: Comprueba que instance no sea null Y que !instance.isClosed().
        //         Si se cumple, llama a instance.close() e imprime
        //         "Conexión cerrada correctamente."
        //         Si no se cumple, imprime "La conexión ya estaba cerrada o era null."
        //         Captura SQLException internamente e imprime el error por System.err.
    }

    /**
     * Indica si la conexión Singleton está activa y lista para usar.
     *
     * @return true si instance no es null y no está cerrada, false en cualquier otro caso
     */
    public static boolean estaDisponible() {
        // TODO 2: Devuelve true si instance != null Y !instance.isClosed().
        //         Captura SQLException y devuelve false si ocurre.
        return false;
    }

    /**
     * Cierra la conexión actual y abre una nueva de inmediato.
     * Garantiza que la conexión devuelta siempre está abierta.
     *
     * @return la nueva conexión abierta
     * @throws SQLException si no se puede abrir la nueva conexión
     */
    public static Connection reiniciarConexion() throws SQLException {
        // TODO 3: Llama a cerrarSiAbierta() para cerrar la actual.
        //         Luego llama a getConexion() para abrir una nueva.
        //         Devuelve el resultado de getConexion().
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        System.out.println("Disponible antes de conectar: " + estaDisponible());

        getConexion();
        System.out.println("Disponible tras conectar: " + estaDisponible());

        cerrarSiAbierta();
        System.out.println("Disponible tras cerrar: " + estaDisponible());

        cerrarSiAbierta(); // Doble cierre — no debe explotar
        System.out.println("Doble cierre sin excepción: OK");

        Connection nueva = reiniciarConexion();
        System.out.println("Conexión reiniciada, abierta: " + !nueva.isClosed());
    }
}
