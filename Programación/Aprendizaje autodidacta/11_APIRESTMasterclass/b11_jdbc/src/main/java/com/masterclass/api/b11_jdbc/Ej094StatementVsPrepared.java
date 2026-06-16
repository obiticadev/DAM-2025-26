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
        // GUÍA: teoría 11.2 (marcadores ? en vez de concatenar).
        // Una línea: return "INSERT INTO USUARIO(id,nombre) VALUES (?,?)";
        // OJO: el test compara con equals carácter a carácter — copia el SQL EXACTO
        //      (sin espacios extra, columnas id,nombre en ese orden).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlInsert");
    }

    /**
     * TODO extra 2: Evita la inyección SQL simulando el formateo y escapado de un string.
     */
    public static String desafioEvitarConcatenacionSql(String nombre) {
        // GUÍA: muestra POR QUÉ escapar a mano es frágil (teoría 11.2). En SQL una
        // comilla simple se escapa duplicándola ('' = una comilla literal).
        // 1. Si nombre es null, devuelve "NULL" (sin comillas).
        // 2. Si no: envuelve en comillas y duplica las internas:
        //    return "'" + nombre.replace("'", "''") + "'";
        // OJO: el test manda "O'Reilly" y espera "'O''Reilly'" (comillas externas +
        //      la interna duplicada). Esto es lo que el PreparedStatement hace por ti.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioEvitarConcatenacionSql");
    }

    /**
     * TODO extra 3: Prepara un PreparedStatement a partir de la conexión.
     */
    public static java.sql.PreparedStatement desafioCrearPreparedStatement(java.sql.Connection conn, String sql) throws java.sql.SQLException {
        // GUÍA: teoría 11.2. Una línea: return conn.prepareStatement(sql);
        // OJO: el test solo comprueba assertNotNull sobre el resultado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioCrearPreparedStatement");
    }

    /**
     * TODO extra 4: Fija el ID en el primer parámetro del PreparedStatement.
     */
    public static void desafioEstablecerIdParam(java.sql.PreparedStatement ps, int id) throws java.sql.SQLException {
        // GUÍA: teoría 11.2 — los índices EMPIEZAN EN 1, no en 0.
        // Una línea: ps.setInt(1, id);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioEstablecerIdParam");
    }

    /**
     * TODO extra 5: Fija el nombre en el segundo parámetro del PreparedStatement.
     */
    public static void desafioEstablecerNombreParam(java.sql.PreparedStatement ps, String nombre) throws java.sql.SQLException {
        // GUÍA: teoría 11.2 — el segundo ? es el índice 2.
        // Una línea: ps.setString(2, nombre);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioEstablecerNombreParam");
    }

    /**
     * TODO extra 6: Ejecuta la actualización (INSERT) y devuelve el número de filas afectadas.
     */
    public static int desafioEjecutarUpdate(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        // GUÍA: teoría 11.2 — executeUpdate() devuelve el nº de filas afectadas.
        // Una línea: return ps.executeUpdate();
        // OJO: el test prepara un INSERT de una fila y espera 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioEjecutarUpdate");
    }

    /**
     * TODO extra 7: Verifica si un Statement está cerrado de forma segura.
     */
    public static boolean desafioVerificarCierreStatement(java.sql.PreparedStatement ps) {
        // GUÍA: análogo a desafioVerificarEstadoCerrada del Ej093 (teoría 11.4: no fugas).
        // 1. try { return ps == null || ps.isClosed(); }
        // 2. catch (SQLException e) { return true; }   // si no podemos saberlo, asúmelo cerrado.
        // OJO: el test comprueba false con el ps abierto y true tras ps.close().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioVerificarCierreStatement");
    }

    /**
     * TODO extra 8: Retorna el SQL de conteo parametrizado.
     */
    public static String desafioObtenerSqlSelectCount() {
        // GUÍA: teoría 11.2. Una línea:
        // return "SELECT COUNT(*) FROM USUARIO WHERE nombre = ?";
        // OJO: el test compara con equals — respeta espacios y mayúsculas (COUNT(*) ... = ?).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlSelectCount");
    }

    /**
     * TODO extra 9: Mapea la primera columna de un ResultSet a un entero.
     */
    public static int desafioMapearCountResultSet(java.sql.ResultSet rs) throws java.sql.SQLException {
        // GUÍA: teoría 11.2/11.3 — un COUNT siempre da una fila; léela y saca la columna 1.
        // 1. if (rs.next()) return rs.getInt(1);
        // 2. return 0;   // por si acaso no hubiera fila.
        // OJO: el test ejecuta SELECT COUNT(*) sobre una tabla con 1 fila y espera 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioMapearCountResultSet");
    }

    /**
     * TODO extra 10: Retorna el conteo total realizando toda la consulta parametrizada localmente.
     */
    public static int desafioContarSeguroLocal(java.sql.Connection conn, String nombre) throws java.sql.SQLException {
        // GUÍA: ensambla los retos 8 y 9 (teoría 11.2) — el patrón COUNT parametrizado completo.
        // 1. String sql = desafioObtenerSqlSelectCount();              // reutiliza el reto 8
        // 2. try (var ps = conn.prepareStatement(sql)) {
        // 3.     ps.setString(1, nombre);
        // 4.     try (var rs = ps.executeQuery()) {
        // 5.         return desafioMapearCountResultSet(rs);           // reutiliza el reto 9
        //        }
        //    }
        // OJO: el test espera 1 para "Ana" (existe) y 0 para "Inexistente".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioContarSeguroLocal");
    }

}
