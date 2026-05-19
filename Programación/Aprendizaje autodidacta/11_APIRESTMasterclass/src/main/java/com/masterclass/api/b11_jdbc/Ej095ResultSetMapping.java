package com.masterclass.api.b11_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 095 · Mapear ResultSet a objetos.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.1).
 *
 * <p>Tabla PRODUCTO(id INT, nombre VARCHAR, precio DOUBLE) (la crea el test).
 */
public final class Ej095ResultSetMapping {

    public record Producto(int id, String nombre, double precio) {
    }

    private Ej095ResultSetMapping() {
    }

    /**
     * Lee todos los productos ordenados por id ascendente.
     *
     * @param conn conexión
     * @return lista de Producto
     * @throws SQLException si falla la consulta
     */
    public static List<Producto> listar(Connection conn) throws SQLException {
        List<Producto> out = new ArrayList<>();
        // TODO 1: SQL "SELECT id,nombre,precio FROM PRODUCTO ORDER BY id".
        // TODO 2: try-with-resources para PreparedStatement.
        // TODO 3: try-with-resources para el ResultSet (ps.executeQuery()).
        // TODO 4: itera con while (rs.next()).
        // TODO 5: lee rs.getInt("id").
        // TODO 6: lee rs.getString("nombre").
        // TODO 7: lee rs.getDouble("precio").
        // TODO 8: construye un Producto con esos 3 valores.
        // TODO 9: añádelo a 'out'.
        // TODO 10: tras el bucle, devuelve 'out' (orden por id garantizado por el ORDER BY).
        return out;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: SQL "SELECT id,nombre,precio FROM PRODUCTO ORDER BY id".
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: try-with-resources para PreparedStatement.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: try-with-resources para el ResultSet (ps.executeQuery()).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: itera con while (rs.next()).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: lee rs.getInt("id").
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: lee rs.getString("nombre").
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: lee rs.getDouble("precio").
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: construye un Producto con esos 3 valores.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: añádelo a 'out'.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: tras el bucle, devuelve 'out' (orden por id garantizado por el ORDER BY).
    }

}
