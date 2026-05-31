package com.masterclass.api.b11_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Ejercicio 093 · Connection vía DriverManager.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.1).
 */
public final class Ej093ConnectionDriverManager {

    private Ej093ConnectionDriverManager() {
    }

    /**
     * Abre una conexión, comprueba que es válida y la cierra.
     *
     * @param url  URL JDBC (p.ej. "jdbc:h2:mem:t1")
     * @param user usuario
     * @param pass password
     * @return true si la conexión se obtuvo y era válida
     * @throws SQLException si el driver falla al conectar
     */
    public static boolean conectaYValida(String url, String user, String pass) throws SQLException {
        // TODO 1: usa try-with-resources con DriverManager.getConnection(url,user,pass).
        // TODO 2: dentro del try, obtén la Connection.
        // TODO 3: comprueba conn.isValid(2) (timeout 2s).
        // TODO 4: devuelve ese booleano.
        // TODO 5: el try-with-resources cierra la conexión automáticamente.
        // TODO 6: NO cierres la conexión a mano (lo hace el recurso).
        // TODO 7: deja que SQLException se propague (la declara el método).
        // TODO 8: no captures y silencies la excepción.
        // TODO 9: una conexión cerrada NO es válida (por eso validamos dentro del try).
        // TODO 10: devuelve false solo si isValid devolvió false.
        return false;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(conectaYValida("jdbc:h2:mem:demo", "sa", ""));
    }

    /**
     * TODO extra 1: Valida que la URL del driver comience con jdbc:.
     */
    public static boolean desafioValidarUrl(String url) {
        return url != null && url.startsWith("jdbc:");
    }

    /**
     * TODO extra 2: Obtiene las credenciales por defecto (sa, "").
     */
    public static String[] desafioObtenerCredencialesPorDefecto() {
        return new String[] {"sa", ""};
    }

    /**
     * TODO extra 3: Valida una conexión H2 local.
     */
    public static boolean desafioValidarConexionH2(String url, String user, String pass) {
        if (!desafioValidarUrl(url)) return false;
        try (var c = java.sql.DriverManager.getConnection(url, user, pass)) {
            return c.isValid(1);
        } catch (java.sql.SQLException e) {
            return false;
        }
    }

    /**
     * TODO extra 4: Verifica si una conexión ya ha sido cerrada.
     */
    public static boolean desafioVerificarEstadoCerrada(java.sql.Connection c) throws java.sql.SQLException {
        return c == null || c.isClosed();
    }

    /**
     * TODO extra 5: Lanza una excepción SQL genérica.
     */
    public static void desafioLanzarErrorSql() throws java.sql.SQLException {
        throw new java.sql.SQLException("Error simulado");
    }

    /**
     * TODO extra 6: Comprueba si el timeout de conexión es positivo.
     */
    public static boolean desafioComprobarTimeoutValido(int timeout) {
        return timeout > 0;
    }

    /**
     * TODO extra 7: Retorna el mensaje descriptivo de un SQLException.
     */
    public static String desafioPropagarExcepcionConMensaje(java.sql.SQLException e) {
        return e.getMessage();
    }

    /**
     * TODO extra 8: Simula el cierre de recursos AutoCloseable.
     */
    public static boolean desafioSimularCierreAutomatico(AutoCloseable res) {
        try {
            res.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 9: Comprueba de forma segura si la conexión es válida con timeout.
     */
    public static boolean desafioEsConexionValidaConTimeout(java.sql.Connection c, int timeout) {
        try {
            return c != null && c.isValid(timeout);
        } catch (java.sql.SQLException e) {
            return false;
        }
    }

    /**
     * TODO extra 10: Retorna verdadero solo si la conexión está abierta y no nula.
     */
    public static boolean desafioEsConexionAbierta(java.sql.Connection c) {
        try {
            return c != null && !c.isClosed();
        } catch (java.sql.SQLException e) {
            return false;
        }
    }

}
