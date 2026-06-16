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
        // GUÍA: teoría 11.1 (forma de la URL JDBC: jdbc:<motor>:<detalles>).
        // 1. Defiende el null primero (un null no "empieza" por nada).
        // 2. Una línea: return url != null && url.startsWith("jdbc:");
        // OJO: el test exige true para "jdbc:h2:mem:test" y false para
        //      "http://localhost" — basta el prefijo "jdbc:".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioValidarUrl");
    }

    /**
     * TODO extra 2: Obtiene las credenciales por defecto (sa, "").
     */
    public static String[] desafioObtenerCredencialesPorDefecto() {
        // GUÍA: H2 en memoria usa usuario "sa" y password vacía (teoría 11.1).
        // Una línea: return new String[] {"sa", ""};
        // OJO: el test compara con assertArrayEquals(new String[]{"sa", ""}, ...):
        //      el orden y los valores EXACTOS importan (primero usuario, luego pass).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerCredencialesPorDefecto");
    }

    /**
     * TODO extra 3: Valida una conexión H2 local.
     */
    public static boolean desafioValidarConexionH2(String url, String user, String pass) {
        // GUÍA: combina los retos 1 y 9 (teoría 11.1).
        // 1. Si la url no es válida (reutiliza desafioValidarUrl), devuelve false.
        // 2. try-with-resources: DriverManager.getConnection(url, user, pass).
        // 3. Dentro, devuelve c.isValid(1).
        // 4. Captura SQLException y devuelve false (aquí SÍ se traga: es un test booleano).
        // PISTA: try (var c = DriverManager.getConnection(...)) { return c.isValid(1); }
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioValidarConexionH2");
    }

    /**
     * TODO extra 4: Verifica si una conexión ya ha sido cerrada.
     */
    public static boolean desafioVerificarEstadoCerrada(java.sql.Connection c) throws java.sql.SQLException {
        // GUÍA: teoría 11.1 (una conexión cerrada no sirve).
        // Una línea: return c == null || c.isClosed();
        // OJO: el test abre una conexión válida y espera FALSE (está abierta).
        //      Un null cuenta como "cerrada/inutilizable" → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioVerificarEstadoCerrada");
    }

    /**
     * TODO extra 5: Lanza una excepción SQL genérica.
     */
    public static void desafioLanzarErrorSql() throws java.sql.SQLException {
        // GUÍA: la cara opuesta de "no silenciar" (teoría 11.1): aquí practicas LANZAR.
        // Una línea: throw new java.sql.SQLException("Error simulado");
        // OJO: el test solo comprueba assertThrows(SQLException.class, ...): basta
        //      con que el tipo lanzado sea SQLException (el mensaje da igual aquí).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioLanzarErrorSql");
    }

    /**
     * TODO extra 6: Comprueba si el timeout de conexión es positivo.
     */
    public static boolean desafioComprobarTimeoutValido(int timeout) {
        // GUÍA: isValid(timeout) exige un timeout >= 0; un timeout útil es > 0 (teoría 11.1).
        // Una línea: return timeout > 0;
        // OJO: el test manda 5 (true) y 0 (false): el cero NO se considera válido.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioComprobarTimeoutValido");
    }

    /**
     * TODO extra 7: Retorna el mensaje descriptivo de un SQLException.
     */
    public static String desafioPropagarExcepcionConMensaje(java.sql.SQLException e) {
        // GUÍA: toda excepción lleva su mensaje (teoría 1.9 / 11.5: nunca lo pierdas).
        // Una línea: return e.getMessage();
        // OJO: el test crea new SQLException("Demo error") y espera exactamente "Demo error".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioPropagarExcepcionConMensaje");
    }

    /**
     * TODO extra 8: Simula el cierre de recursos AutoCloseable.
     */
    public static boolean desafioSimularCierreAutomatico(AutoCloseable res) {
        // GUÍA: es lo que hace try-with-resources por dentro (teoría 11.1 / 1.9).
        // 1. try { res.close(); return true; }
        // 2. catch (Exception e) { return false; }   // close() de AutoCloseable lanza Exception.
        // OJO: el test pasa un mock 'AutoCloseable mock = () -> flag[0] = true;' y
        //      luego comprueba que flag[0] quedó en true → DEBES invocar close().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioSimularCierreAutomatico");
    }

    /**
     * TODO extra 9: Comprueba de forma segura si la conexión es válida con timeout.
     */
    public static boolean desafioEsConexionValidaConTimeout(java.sql.Connection c, int timeout) {
        // GUÍA: versión "a prueba de balas" de isValid (teoría 11.1).
        // 1. try { return c != null && c.isValid(timeout); }
        // 2. catch (SQLException e) { return false; }
        // PISTA: el && con corto-circuito evita el NPE si c es null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioEsConexionValidaConTimeout");
    }

    /**
     * TODO extra 10: Retorna verdadero solo si la conexión está abierta y no nula.
     */
    public static boolean desafioEsConexionAbierta(java.sql.Connection c) {
        // GUÍA: es el negado del reto 4 (teoría 11.1). Reutiliza la idea.
        // 1. try { return c != null && !c.isClosed(); }
        // 2. catch (SQLException e) { return false; }
        // OJO: el test abre una conexión y espera true (abierta y no nula).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioEsConexionAbierta");
    }

}
