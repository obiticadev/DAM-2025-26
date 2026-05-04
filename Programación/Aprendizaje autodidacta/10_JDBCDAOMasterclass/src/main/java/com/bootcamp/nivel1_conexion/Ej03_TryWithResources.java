package com.bootcamp.nivel1_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej03 — try-with-resources con Statement
 * Teoría: teoria/01_Conexion_JDBC_Singleton.md
 *
 * Objetivo: practicar el cierre automático de Statement usando
 * try-with-resources. La Connection se gestiona manualmente (Singleton).
 */
public class Ej03_TryWithResources {

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej03.db";

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
     * Ejecuta una sentencia SQL (DDL o DML sin retorno) usando try-with-resources.
     * El Statement se cierra automáticamente al salir del bloque try.
     *
     * @param sql sentencia SQL a ejecutar (p.ej. CREATE TABLE, DROP TABLE)
     * @throws SQLException si la sentencia falla
     */
    public static void ejecutarSentencia(String sql) throws SQLException {
        // TODO 1: Obtén la conexión con getConexion().
        //         Abre un Statement dentro de try-with-resources:
        //             try (Statement stmt = conexion.createStatement()) { ... }
        //         Dentro del try llama a stmt.execute(sql).
        //         Imprime "Sentencia ejecutada: " + sql.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Ejecuta una sentencia SQL y devuelve el boolean que retorna Statement.execute().
     * Usa try-with-resources para cerrar el Statement automáticamente.
     *
     * @param sql sentencia SQL a ejecutar
     * @return el valor boolean devuelto por stmt.execute(sql)
     * @throws SQLException si la sentencia falla
     */
    public static boolean ejecutarConRetorno(String sql) throws SQLException {
        // TODO 2: Igual que ejecutarSentencia(), pero captura el resultado de
        //         stmt.execute(sql) en una variable boolean y devuélvela.
        //         Usa también try-with-resources.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Demuestra visualmente que el Statement se cierra al salir del bloque try.
     * Imprime el estado de isClosed() dentro y fuera del try.
     *
     * @throws SQLException si ocurre un error al crear el Statement
     */
    public static void verificarCierreStatement() throws SQLException {
        // TODO 3: Declara la variable Statement stmt fuera del try para poder
        //         acceder a ella después.
        //         Abre el Statement con try-with-resources.
        //         Dentro del try imprime: "¿stmt cerrado dentro del try? " + stmt.isClosed()
        //         Fuera del try imprime: "¿stmt cerrado fuera del try? " + stmt.isClosed()
        //         No ejecutes ninguna sentencia SQL — solo observa el estado.
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        ejecutarSentencia("CREATE TABLE IF NOT EXISTS prueba_ej03 (id INTEGER PRIMARY KEY)");

        boolean resultado = ejecutarConRetorno("DROP TABLE IF EXISTS prueba_ej03");
        System.out.println("DROP devolvió: " + resultado);

        verificarCierreStatement();
    }
}
