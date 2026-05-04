package com.bootcamp.nivel1_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Ej01 — Primera conexión a SQLite (sin Singleton)
 * Teoría: teoria/01_Conexion_JDBC_Singleton.md
 *
 * Objetivo: abrir una conexión cruda a SQLite, confirmar que está abierta
 * y cerrarla manualmente. Sin patrones, sin clases auxiliares.
 */
public class Ej01_PrimeraConexion {

    private static final String URL = "jdbc:sqlite:bootcamp_ej01.db";

    /**
     * Abre una conexión directa a SQLite usando DriverManager.
     *
     * @return un objeto Connection recién abierto
     * @throws SQLException si el driver no puede abrir la conexión
     */
    public static Connection abrirConexion() throws SQLException {
        // TODO 1: Llama a DriverManager.getConnection(URL) y devuelve el resultado.
        //         No uses Singleton, no guardes nada en un campo — solo abre y devuelve.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Cierra la conexión recibida por parámetro.
     *
     * @param conn la conexión que se quiere cerrar
     * @throws SQLException si ocurre un error al cerrar
     */
    public static void cerrarConexion(Connection conn) throws SQLException {
        // TODO 2: Llama a conn.close().
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Indica si la conexión está actualmente abierta.
     *
     * @param conn la conexión a inspeccionar
     * @return true si la conexión NO está cerrada, false si está cerrada
     * @throws SQLException si isClosed() lanza excepción
     */
    public static boolean estaAbierta(Connection conn) throws SQLException {
        // TODO 3: Devuelve la negación de conn.isClosed().
        //         isClosed() devuelve true cuando está CERRADA, así que debes negarlo.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        Connection conn = abrirConexion();
        System.out.println("¿Conexión abierta? " + estaAbierta(conn));

        cerrarConexion(conn);
        System.out.println("¿Conexión abierta tras cerrar? " + estaAbierta(conn));
    }
}
