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
}
