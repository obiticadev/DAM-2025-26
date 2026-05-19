package com.masterclass.api.b11_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ejercicio 094 · PreparedStatement contra inyección SQL.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.2).
 *
 * <p>La tabla USUARIO(id INT, nombre VARCHAR) ya existe (la crea el test).
 */
public final class Ej094StatementVsPrepared {

    private Ej094StatementVsPrepared() {
    }

    /**
     * Inserta un usuario de forma SEGURA (parametrizada).
     *
     * @param conn   conexión activa
     * @param id     id del usuario
     * @param nombre nombre (puede contener comillas: debe ser seguro)
     * @throws SQLException si falla el INSERT
     */
    public static void insertarSeguro(Connection conn, int id, String nombre) throws SQLException {
        // TODO 1: define el SQL con marcadores: "INSERT INTO USUARIO(id,nombre) VALUES (?,?)".
        // TODO 2: NUNCA construyas el SQL concatenando 'nombre' (eso es inyectable).
        // TODO 3: usa try-with-resources con conn.prepareStatement(sql).
        // TODO 4: ps.setInt(1, id).
        // TODO 5: ps.setString(2, nombre) — el driver escapa el valor.
        // TODO 6: ejecuta con ps.executeUpdate().
        // TODO 7: el PreparedStatement se cierra solo (try-with-resources).
    }

    /**
     * Cuenta cuántos usuarios hay con ese nombre exacto (consulta parametrizada).
     *
     * @param conn   conexión
     * @param nombre nombre a contar
     * @return número de coincidencias
     * @throws SQLException si falla la query
     */
    public static int contarPorNombre(Connection conn, String nombre) throws SQLException {
        // TODO 8: prepara "SELECT COUNT(*) FROM USUARIO WHERE nombre = ?".
        // TODO 9: setString(1, nombre), executeQuery, rs.next(), rs.getInt(1).
        // TODO 10: devuelve el conteo (0 si no hay filas — habrá una fila con el count).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }
}
