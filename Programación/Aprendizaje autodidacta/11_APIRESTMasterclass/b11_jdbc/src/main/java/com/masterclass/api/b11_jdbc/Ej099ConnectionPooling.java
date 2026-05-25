package com.masterclass.api.b11_jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Ejercicio 099 · Pool de conexiones con HikariCP.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.4).
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
        return ds != null;
    }

    /**
     * TODO extra 2: Obtiene una conexión del pool y valida que esté abierta.
     */
    public static boolean desafioValidarConexionPool(javax.sql.DataSource ds) {
        try (var c = ds.getConnection()) {
            return c != null && !c.isClosed();
        } catch (java.sql.SQLException e) {
            return false;
        }
    }

    /**
     * TODO extra 3: Retorna el tamaño mínimo de conexiones inactivas configurado en el pool (valor representativo).
     */
    public static int desafioMinIdleRecomendado() {
        return 5;
    }

    /**
     * TODO extra 4: Retorna el tamaño máximo de conexiones configurado en el pool (valor representativo).
     */
    public static int desafioMaxConnectionsRecomendado() {
        return 10;
    }

    /**
     * TODO extra 5: Comprueba si el DataSource es una instancia de un pool Hikari (simulación).
     */
    public static boolean desafioEsHikariDataSource(javax.sql.DataSource ds) {
        return ds != null && ds.getClass().getName().contains("Hikari");
    }

    /**
     * TODO extra 6: Retorna el timeout de conexión recomendado en milisegundos (p.ej. 30000).
     */
    public static long desafioConnectionTimeoutRecomendado() {
        return 30000L;
    }

    /**
     * TODO extra 7: Verifica que una conexión obtenida del pool provenga de una URL válida.
     */
    public static boolean desafioVerificarUrlConexion(java.sql.Connection conn) {
        try {
            return conn.getMetaData().getURL() != null;
        } catch (java.sql.SQLException e) {
            return false;
        }
    }

    /**
     * TODO extra 8: Simula la liberación de una conexión devolviéndola al pool (cierre try-with-resources).
     */
    public static void desafioLiberarConexion(java.sql.Connection conn) throws java.sql.SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * TODO extra 9: Comprueba si el pool está activo validando que no lance excepciones al conectar repetidamente.
     */
    public static boolean desafioTestearPool(javax.sql.DataSource ds, int intentos) {
        for (int i = 0; i < intentos; i++) {
            if (!desafioValidarConexionPool(ds)) return false;
        }
        return true;
    }

    /**
     * TODO extra 10: Retorna un resumen descriptivo del pool de conexiones.
     */
    public static String desafioResumirPool(javax.sql.DataSource ds) {
        return ds == null ? "Pool desactivado" : "Pool activo: " + ds.getClass().getSimpleName();
    }

}
