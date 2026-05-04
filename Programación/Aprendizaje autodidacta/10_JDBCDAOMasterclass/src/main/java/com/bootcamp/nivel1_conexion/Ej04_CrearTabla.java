package com.bootcamp.nivel1_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej04 — Crear tabla con CREATE TABLE IF NOT EXISTS
 * Teoría: teoria/01_Conexion_JDBC_Singleton.md
 *
 * Objetivo: practicar la creación de tablas con DDL, entendiendo
 * la diferencia entre ejecutar la misma sentencia una o dos veces.
 * IF NOT EXISTS garantiza que no falla si la tabla ya existe.
 */
public class Ej04_CrearTabla {

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej04.db";

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
     * Crea la tabla "Peliculas" en la base de datos si no existe.
     * Columnas: id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL,
     *           director TEXT, anio INTEGER.
     *
     * @throws SQLException si la sentencia DDL falla
     */
    public static void crearTablaPeliculas() throws SQLException {
        // TODO 1: Construye el SQL con CREATE TABLE IF NOT EXISTS Peliculas
        //         con las columnas indicadas en el Javadoc.
        //         Usa try-with-resources con Statement para ejecutarlo.
        //         Imprime "Tabla Peliculas creada (o ya existía)."
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Crea una tabla con el nombre y la definición de columnas indicados.
     * Usa CREATE TABLE IF NOT EXISTS para que sea idempotente.
     *
     * @param nombre     nombre de la tabla a crear
     * @param definicion definición de columnas, p.ej. "id INTEGER PRIMARY KEY, nombre TEXT"
     * @throws SQLException si la sentencia DDL falla
     */
    public static void crearTablaPersonalizada(String nombre, String definicion) throws SQLException {
        // TODO 2: Construye el SQL concatenando:
        //         "CREATE TABLE IF NOT EXISTS " + nombre + " (" + definicion + ")"
        //         Usa try-with-resources con Statement para ejecutarlo.
        //         Imprime "Tabla creada: " + nombre
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Comprueba si una tabla existe en la base de datos SQLite.
     * La query a usar (te la dan en el examen) es:
     * {@code SELECT name FROM sqlite_master WHERE type='table' AND name='<nombre>'}
     *
     * @param nombre nombre de la tabla a buscar
     * @return true si la tabla existe, false si no
     * @throws SQLException si la consulta falla
     */
    public static boolean tablaExiste(String nombre) throws SQLException {
        // TODO 3: Usa la query indicada en el Javadoc.
        //         Ejecuta stmt.executeQuery(sql) dentro de try-with-resources.
        //         Devuelve rs.next() — true si hay alguna fila, false si no.
        return false;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        crearTablaPeliculas();
        System.out.println("¿Existe Peliculas? " + tablaExiste("Peliculas"));

        crearTablaPeliculas(); // Segunda llamada — no debe fallar
        System.out.println("Segunda llamada OK (IF NOT EXISTS funciona)");

        crearTablaPersonalizada("Actores", "id INTEGER PRIMARY KEY, nombre TEXT NOT NULL");
        System.out.println("¿Existe Actores? " + tablaExiste("Actores"));
        System.out.println("¿Existe Directores? " + tablaExiste("Directores"));
    }
}
