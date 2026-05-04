package com.bootcamp.nivel1_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Ej06 — Guard de estado antes de reutilizar la conexión
 * Teoría: teoria/01_Conexion_JDBC_Singleton.md
 *
 * Objetivo: entender el guard completo {@code null || isClosed()} que protege
 * el Singleton. Sin este guard, reutilizar una conexión cerrada lanza
 * una excepción difícil de diagnosticar.
 */
public class Ej06_VerificarEstado {

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej06.db";

    /**
     * Imprime en consola el estado actual de la conexión Singleton.
     * Los tres estados posibles son: SIN CONEXIÓN (null), CERRADA, ABIERTA Y OPERATIVA.
     */
    public static void diagnosticarEstado() {
        // TODO 1: Comprueba los tres estados en este orden:
        //         1. Si instance == null → imprime "Estado: SIN CONEXIÓN (null)"
        //         2. Si instance.isClosed() → imprime "Estado: CERRADA"
        //         3. En caso contrario → imprime "Estado: ABIERTA Y OPERATIVA"
        //         Captura SQLException internamente.
    }

    /**
     * Devuelve la conexión Singleton, creando una nueva si es necesario.
     * Aplica el guard completo: {@code instance == null || instance.isClosed()}.
     * Imprime "Creando nueva conexión." o "Reutilizando conexión existente." según el caso.
     *
     * @return la instancia Singleton de Connection, o null si falla la conexión
     */
    public static Connection getConexionSegura() {
        // TODO 2: Evalúa el guard: instance == null || instance.isClosed()
        //         Si entra → imprime "Creando nueva conexión." y crea la conexión.
        //         Si no entra → imprime "Reutilizando conexión existente."
        //         Captura SQLException internamente.
        //         Devuelve instance.
        return null;
    }

    /**
     * Cierra la conexión Singleton si está activa.
     * No hace nada ni lanza excepción si ya estaba cerrada o era null.
     */
    public static void cerrar() {
        // TODO 3: Si instance no es null, llama a instance.close().
        //         Captura SQLException internamente e imprime el error por System.err.
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) {
        diagnosticarEstado(); // null

        getConexionSegura();  // crea nueva
        diagnosticarEstado(); // abierta

        getConexionSegura();  // reutiliza
        diagnosticarEstado(); // sigue abierta

        cerrar();
        diagnosticarEstado(); // cerrada

        getConexionSegura();  // crea nueva de nuevo
        diagnosticarEstado(); // abierta otra vez
    }
}
