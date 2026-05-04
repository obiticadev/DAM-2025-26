package com.bootcamp.nivel1_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Ej02 — Singleton de Conexión
 * Teoría: teoria/01_Conexion_JDBC_Singleton.md
 *
 * Objetivo: implementar el patrón Singleton para gestionar UNA sola
 * instancia de Connection durante toda la vida del programa.
 *
 * Esta es la pieza central de todos los ejercicios siguientes.
 * Apréndela de memoria — en el examen la construyes en 2 minutos.
 */
public class Ej02_SingletonConexion {

    // TODO 1: Declara aquí el atributo estático privado "instance" de tipo Connection
    //         e inicialízalo a null. Es el "candado" del Singleton.
    private static Connection instance = null;

    // TODO 2: Declara aquí la constante estática privada URL.
    //         Valor: "jdbc:sqlite:bootcamp_ej02.db"
    private static final String URL = "jdbc:sqlite:bootcamp_ej02.db";

    /**
     * Constructor privado: impide que nadie haga {@code new Ej02_SingletonConexion()}.
     * El acceso a la conexión se hace exclusivamente a través de {@link #getConexion()}.
     */
    private Ej02_SingletonConexion() {
        // TODO 3: No añadas nada aquí. Solo necesita existir y ser privado.
    }

    /**
     * Devuelve la única instancia de Connection, creándola si no existe o si está cerrada.
     * Implementa el guard: {@code instance == null || instance.isClosed()}.
     *
     * @return la instancia Singleton de Connection, o null si falla la conexión
     */
    public static Connection getConexion() {
        // TODO 4: Comprueba si instance es null O si instance.isClosed() es true.
        //         En ese caso, crea una nueva conexión con DriverManager.getConnection(URL)
        //         y asígnala a instance.
        //         Captura SQLException con try/catch e imprime el error por System.err.
        //         Al final devuelve instance.
        return null;
    }

    /**
     * Cierra la conexión Singleton si está activa.
     * No hace nada si instance es null.
     */
    public static void cerrarConexion() {
        // TODO 5: Comprueba que instance no sea null.
        //         Si no es null, llama a instance.close().
        //         Captura SQLException con try/catch e imprime el error por System.err.
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        Connection c1 = getConexion();
        Connection c2 = getConexion();

        System.out.println("¿c1 y c2 son el mismo objeto? " + (c1 == c2));
        System.out.println("Conexión abierta: " + !c1.isClosed());

        cerrarConexion();
        System.out.println("Tras cerrar, isClosed: " + c1.isClosed());

        Connection c3 = getConexion();
        System.out.println("Nueva conexión tras cerrar, abierta: " + !c3.isClosed());
    }
}
