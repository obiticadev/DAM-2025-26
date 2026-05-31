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

    /**
     * TODO extra 1: Retorna el SQL de inserción parametrizado.
     */
    public static String desafioObtenerSqlInsert() {
        return "INSERT INTO USUARIO(id,nombre) VALUES (?,?)";
    }

    /**
     * TODO extra 2: Evita la inyección SQL simulando el formateo y escapado de un string.
     */
    public static String desafioEvitarConcatenacionSql(String nombre) {
        if (nombre == null) return "NULL";
        return "'" + nombre.replace("'", "''") + "'";
    }

    /**
     * TODO extra 3: Prepara un PreparedStatement a partir de la conexión.
     */
    public static java.sql.PreparedStatement desafioCrearPreparedStatement(java.sql.Connection conn, String sql) throws java.sql.SQLException {
        return conn.prepareStatement(sql);
    }

    /**
     * TODO extra 4: Fija el ID en el primer parámetro del PreparedStatement.
     */
    public static void desafioEstablecerIdParam(java.sql.PreparedStatement ps, int id) throws java.sql.SQLException {
        ps.setInt(1, id);
    }

    /**
     * TODO extra 5: Fija el nombre en el segundo parámetro del PreparedStatement.
     */
    public static void desafioEstablecerNombreParam(java.sql.PreparedStatement ps, String nombre) throws java.sql.SQLException {
        ps.setString(2, nombre);
    }

    /**
     * TODO extra 6: Ejecuta la actualización (INSERT) y devuelve el número de filas afectadas.
     */
    public static int desafioEjecutarUpdate(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        return ps.executeUpdate();
    }

    /**
     * TODO extra 7: Verifica si un Statement está cerrado de forma segura.
     */
    public static boolean desafioVerificarCierreStatement(java.sql.PreparedStatement ps) {
        try {
            return ps == null || ps.isClosed();
        } catch (java.sql.SQLException e) {
            return true;
        }
    }

    /**
     * TODO extra 8: Retorna el SQL de conteo parametrizado.
     */
    public static String desafioObtenerSqlSelectCount() {
        return "SELECT COUNT(*) FROM USUARIO WHERE nombre = ?";
    }

    /**
     * TODO extra 9: Mapea la primera columna de un ResultSet a un entero.
     */
    public static int desafioMapearCountResultSet(java.sql.ResultSet rs) throws java.sql.SQLException {
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    /**
     * TODO extra 10: Retorna el conteo total realizando toda la consulta parametrizada localmente.
     */
    public static int desafioContarSeguroLocal(java.sql.Connection conn, String nombre) throws java.sql.SQLException {
        String sql = desafioObtenerSqlSelectCount();
        try (var ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (var rs = ps.executeQuery()) {
                return desafioMapearCountResultSet(rs);
            }
        }
    }

}
