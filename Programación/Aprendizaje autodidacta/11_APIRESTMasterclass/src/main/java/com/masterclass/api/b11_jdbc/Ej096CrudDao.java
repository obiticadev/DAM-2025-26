package com.masterclass.api.b11_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ejercicio 096 · DAO CRUD con JDBC puro.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.1).
 *
 * <p>Tabla CLIENTE(id INT PRIMARY KEY, nombre VARCHAR) (la crea el test).
 */
public final class Ej096CrudDao {

    private final Connection conn;

    public Ej096CrudDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param id     id
     * @param nombre nombre
     * @throws SQLException si falla el INSERT
     */
    public void crear(int id, String nombre) throws SQLException {
        // TODO 1: prepara "INSERT INTO CLIENTE(id,nombre) VALUES (?,?)".
        // TODO 2: setea parámetros y executeUpdate (try-with-resources).
    }

    /**
     * @param id id buscado
     * @return el nombre, o null si no existe
     * @throws SQLException si falla la query
     */
    public String leerNombre(int id) throws SQLException {
        // TODO 3: prepara "SELECT nombre FROM CLIENTE WHERE id=?".
        // TODO 4: ejecuta; si rs.next() devuelve el nombre.
        // TODO 5: si no hay fila, devuelve null.
        return null;
    }

    /**
     * @param id     id a actualizar
     * @param nombre nuevo nombre
     * @return true si se actualizó alguna fila
     * @throws SQLException si falla el UPDATE
     */
    public boolean actualizar(int id, String nombre) throws SQLException {
        // TODO 6: prepara "UPDATE CLIENTE SET nombre=? WHERE id=?".
        // TODO 7: executeUpdate devuelve nº de filas; true si > 0.
        return false;
    }

    /**
     * @param id id a borrar
     * @return true si se borró
     * @throws SQLException si falla el DELETE
     */
    public boolean borrar(int id) throws SQLException {
        // TODO 8: prepara "DELETE FROM CLIENTE WHERE id=?".
        // TODO 9: executeUpdate; true si afectó alguna fila.
        // TODO 10: cada método abre y cierra su PreparedStatement (no fugas de recursos).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: prepara "INSERT INTO CLIENTE(id,nombre) VALUES (?,?)".
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: setea parámetros y executeUpdate (try-with-resources).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: prepara "SELECT nombre FROM CLIENTE WHERE id=?".
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: ejecuta; si rs.next() devuelve el nombre.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si no hay fila, devuelve null.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: prepara "UPDATE CLIENTE SET nombre=? WHERE id=?".
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: executeUpdate devuelve nº de filas; true si > 0.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: prepara "DELETE FROM CLIENTE WHERE id=?".
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: executeUpdate; true si afectó alguna fila.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: cada método abre y cierra su PreparedStatement (no fugas de recursos).
    }

}
