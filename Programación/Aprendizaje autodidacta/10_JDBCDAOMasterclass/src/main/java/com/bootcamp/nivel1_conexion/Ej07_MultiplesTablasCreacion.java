package com.bootcamp.nivel1_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej07 — Crear múltiples tablas desde el mismo Singleton
 * Teoría: teoria/01_Conexion_JDBC_Singleton.md
 *
 * Objetivo: demostrar que un único objeto Connection puede ejecutar
 * múltiples sentencias DDL para crear distintas tablas.
 * La conexión es siempre la misma — los Statement se abren y cierran.
 *
 * Simularás la inicialización de una base de datos de una librería
 * con tres tablas: Autores, Libros y Editoriales.
 */
public class Ej07_MultiplesTablasCreacion {

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej07.db";

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
     * Crea la tabla "Autores" si no existe.
     * Columnas: id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, nacionalidad TEXT.
     *
     * @throws SQLException si la sentencia DDL falla
     */
    public static void crearTablaAutores() throws SQLException {
        // TODO 1: Construye el SQL con CREATE TABLE IF NOT EXISTS Autores
        //         con las columnas del Javadoc.
        //         Usa try-with-resources con Statement y getConexion().
        //         Imprime "Tabla Autores lista."
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Crea la tabla "Editoriales" si no existe.
     * Columnas: id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, pais TEXT.
     *
     * @throws SQLException si la sentencia DDL falla
     */
    public static void crearTablaEditoriales() throws SQLException {
        // TODO 2: Igual que crearTablaAutores() pero para la tabla Editoriales.
        //         Imprime "Tabla Editoriales lista."
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Crea la tabla "Libros" si no existe.
     * Columnas: id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL,
     *           isbn TEXT, anio INTEGER, id_autor INTEGER, id_editorial INTEGER.
     *
     * @throws SQLException si la sentencia DDL falla
     */
    public static void crearTablaLibros() throws SQLException {
        // TODO 3: Igual que los anteriores pero para la tabla Libros.
        //         Imprime "Tabla Libros lista."
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Inicializa la base de datos creando las tres tablas en orden.
     * Todas usan la misma instancia de Connection (Singleton).
     *
     * @throws SQLException si alguna creación de tabla falla
     */
    public static void inicializarBaseDatos() throws SQLException {
        // TODO 4: Llama a crearTablaAutores(), crearTablaEditoriales() y crearTablaLibros()
        //         en ese orden.
        //         Imprime la referencia de getConexion() para confirmar que es el mismo objeto
        //         en los tres casos: "Conexión usada: " + getConexion()
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        inicializarBaseDatos();

        System.out.println("Referencia conexión desde main: " + getConexion());
        System.out.println("Base de datos inicializada con 3 tablas.");
    }
}
