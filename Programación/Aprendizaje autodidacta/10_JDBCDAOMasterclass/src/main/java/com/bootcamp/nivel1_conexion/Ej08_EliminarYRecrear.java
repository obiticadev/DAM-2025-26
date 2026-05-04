package com.bootcamp.nivel1_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej08 — DROP TABLE IF EXISTS + recrear desde cero
 * Teoría: teoria/01_Conexion_JDBC_Singleton.md
 *
 * Objetivo: aprender a eliminar tablas de forma segura con
 * DROP TABLE IF EXISTS y volver a crearlas. Este es el patrón
 * "reset de base de datos" que se usa en tests o arranques limpios.
 */
public class Ej08_EliminarYRecrear {

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej08.db";

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
     * Elimina una tabla de la base de datos si existe.
     * Usa DROP TABLE IF EXISTS para que no falle si la tabla ya fue eliminada.
     *
     * @param nombreTabla nombre de la tabla a eliminar
     * @throws SQLException si la sentencia DDL falla
     */
    public static void eliminarTabla(String nombreTabla) throws SQLException {
        // TODO 1: Construye el SQL: "DROP TABLE IF EXISTS " + nombreTabla
        //         Usa try-with-resources con Statement para ejecutarlo.
        //         Imprime "Tabla eliminada (si existía): " + nombreTabla
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Crea la tabla "Productos" si no existe.
     * Columnas: id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL,
     *           precio REAL, stock INTEGER.
     *
     * @throws SQLException si la sentencia DDL falla
     */
    public static void crearTablaProductos() throws SQLException {
        // TODO 2: Construye el SQL con CREATE TABLE IF NOT EXISTS Productos
        //         con las columnas del Javadoc.
        //         Usa try-with-resources con Statement.
        //         Imprime "Tabla Productos creada."
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Reinicia la tabla Productos: la elimina si existe y la vuelve a crear vacía.
     *
     * @throws SQLException si alguna de las dos operaciones falla
     */
    public static void resetTablaProductos() throws SQLException {
        // TODO 3: Llama a eliminarTabla("Productos") y luego a crearTablaProductos().
        //         Imprime "Tabla Productos reiniciada."
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Comprueba si una tabla existe en la base de datos SQLite.
     * La query (te la dan en el examen) es:
     * {@code SELECT name FROM sqlite_master WHERE type='table' AND name='<nombre>'}
     *
     * @param nombre nombre de la tabla a buscar
     * @return true si la tabla existe, false si no
     * @throws SQLException si la consulta falla
     */
    public static boolean tablaExiste(String nombre) throws SQLException {
        // TODO 4: Usa la query del Javadoc.
        //         Ejecuta stmt.executeQuery(sql) dentro de try-with-resources.
        //         Devuelve rs.next() — true si hay fila, false si no.
        return false;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        System.out.println("¿Productos existe antes? " + tablaExiste("Productos"));

        crearTablaProductos();
        System.out.println("¿Productos existe tras crear? " + tablaExiste("Productos"));

        resetTablaProductos();
        System.out.println("¿Productos existe tras reset? " + tablaExiste("Productos"));

        eliminarTabla("Productos");
        System.out.println("¿Productos existe tras eliminar? " + tablaExiste("Productos"));

        eliminarTabla("Productos"); // Segunda vez — no debe fallar
        System.out.println("Segunda eliminación sin excepción: OK");
    }
}
