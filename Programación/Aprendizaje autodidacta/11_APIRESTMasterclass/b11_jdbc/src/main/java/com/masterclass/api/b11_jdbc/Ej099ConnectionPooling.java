package com.masterclass.api.b11_jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Ejercicio 099 · Pool de conexiones con HikariCP.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.7).
 */
public final class Ej099ConnectionPooling {

    private Ej099ConnectionPooling() {
    }

    /**
     * Construye un DataSource HikariCP con tamaño de pool acotado.
     *
     * @param url     URL JDBC
     * @param maxPool tamaño máximo del pool (&gt; 0)
     * @return el DataSource (pool) listo
     * @throws IllegalArgumentException si maxPool &lt;= 0
     */
    public static DataSource crearPool(String url, int maxPool) {
        // TODO 1: valida maxPool > 0.
        // TODO 2: crea un HikariConfig.
        // TODO 3: config.setJdbcUrl(url).
        // TODO 4: config.setMaximumPoolSize(maxPool).
        // TODO 5: config.setUsername("sa") y setPassword("") para H2.
        // TODO 6: crea y devuelve new HikariDataSource(config).
        return null;
    }

    /**
     * Sobrecarga de apoyo para los retos extra: crea un pool Hikari indicando
     * credenciales. (La usan los tests de los desafíos; se da implementada para
     * que el módulo compile — el ejercicio base sigue siendo {@link #crearPool(String, int)}.)
     *
     * @param url  URL JDBC
     * @param user usuario
     * @param pass password
     * @return un HikariDataSource configurado
     */
    public static DataSource crearPool(String url, String user, String pass) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(pass);
        return new HikariDataSource(config);
    }

    /**
     * Pide y devuelve N conexiones al pool, comprobando que se reutilizan.
     *
     * @param ds DataSource (pool)
     * @param n  número de conexiones a tomar/cerrar en serie
     * @return true si todas fueron válidas
     * @throws SQLException si el pool falla
     */
    public static boolean usarYDevolver(DataSource ds, int n) throws SQLException {
        // TODO 7: bucle n veces.
        // TODO 8: en cada vuelta, try-with-resources ds.getConnection().
        // TODO 9: comprueba isValid(1); si alguna falla, devuelve false.
        // TODO 10: cerrar la conexión la DEVUELVE al pool (no la destruye): devuelve true.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }

    /**
     * TODO extra 1: Comprueba si un DataSource está configurado de forma no nula.
     */
    public static boolean desafioDataSourceNoNulo(javax.sql.DataSource ds) {
        // GUÍA: teoría 11.7. Una línea: return ds != null;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioDataSourceNoNulo");
    }

    /**
     * TODO extra 2: Obtiene una conexión del pool y valida que esté abierta.
     */
    public static boolean desafioValidarConexionPool(javax.sql.DataSource ds) {
        // GUÍA: teoría 11.7 — pedir una conexión al pool y comprobar que está viva.
        // 1. try (var c = ds.getConnection()) { return c != null && !c.isClosed(); }
        // 2. catch (SQLException e) { return false; }
        // OJO: el try-with-resources DEVUELVE la conexión al pool al salir (no la destruye).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioValidarConexionPool");
    }

    /**
     * TODO extra 3: Retorna el tamaño mínimo de conexiones inactivas configurado en el pool (valor representativo).
     */
    public static int desafioMinIdleRecomendado() {
        // GUÍA: teoría 11.7 (parámetro minimumIdle). Una línea: return 5;
        // OJO: el test exige exactamente 5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioMinIdleRecomendado");
    }

    /**
     * TODO extra 4: Retorna el tamaño máximo de conexiones configurado en el pool (valor representativo).
     */
    public static int desafioMaxConnectionsRecomendado() {
        // GUÍA: teoría 11.7 (parámetro maximumPoolSize). Una línea: return 10;
        // OJO: el test exige exactamente 10.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioMaxConnectionsRecomendado");
    }

    /**
     * TODO extra 5: Comprueba si el DataSource es una instancia de un pool Hikari (simulación).
     */
    public static boolean desafioEsHikariDataSource(javax.sql.DataSource ds) {
        // GUÍA: teoría 11.7 — Hikari es el pool por defecto de Spring Boot.
        // Una línea: return ds != null && ds.getClass().getName().contains("Hikari");
        // OJO: el test crea el pool con crearPool(url,"sa","") y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioEsHikariDataSource");
    }

    /**
     * TODO extra 6: Retorna el timeout de conexión recomendado en milisegundos (p.ej. 30000).
     */
    public static long desafioConnectionTimeoutRecomendado() {
        // GUÍA: teoría 11.7 (parámetro connectionTimeout, en ms). Una línea: return 30000L;
        // OJO: el test exige 30000L exacto (long, no int).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioConnectionTimeoutRecomendado");
    }

    /**
     * TODO extra 7: Verifica que una conexión obtenida del pool provenga de una URL válida.
     */
    public static boolean desafioVerificarUrlConexion(java.sql.Connection conn) {
        // GUÍA: los metadatos de la conexión incluyen la URL JDBC (teoría 11.1/11.7).
        // 1. try { return conn.getMetaData().getURL() != null; }
        // 2. catch (SQLException e) { return false; }
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioVerificarUrlConexion");
    }

    /**
     * TODO extra 8: Simula la liberación de una conexión devolviéndola al pool (cierre try-with-resources).
     */
    public static void desafioLiberarConexion(java.sql.Connection conn) throws java.sql.SQLException {
        // GUÍA: teoría 11.7 — close() sobre una conexión del pool la DEVUELVE al pool.
        // if (conn != null) conn.close();
        // OJO: el test comprueba que tras llamar, conn.isClosed() es true (la "vista"
        //      de la conexión queda cerrada aunque por dentro el pool la recicle).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioLiberarConexion");
    }

    /**
     * TODO extra 9: Comprueba si el pool está activo validando que no lance excepciones al conectar repetidamente.
     */
    public static boolean desafioTestearPool(javax.sql.DataSource ds, int intentos) {
        // GUÍA: reutiliza el reto 2 en bucle (teoría 11.7: reutilización de conexiones).
        // for (int i = 0; i < intentos; i++) if (!desafioValidarConexionPool(ds)) return false;
        // return true;
        // OJO: el test pide 3 intentos sobre un pool válido y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioTestearPool");
    }

    /**
     * TODO extra 10: Retorna un resumen descriptivo del pool de conexiones.
     */
    public static String desafioResumirPool(javax.sql.DataSource ds) {
        // GUÍA: teoría 11.7. Operador ternario:
        // return ds == null ? "Pool desactivado" : "Pool activo: " + ds.getClass().getSimpleName();
        // OJO: el test exige EXACTAMENTE "Pool desactivado" cuando ds es null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioResumirPool");
    }

}
