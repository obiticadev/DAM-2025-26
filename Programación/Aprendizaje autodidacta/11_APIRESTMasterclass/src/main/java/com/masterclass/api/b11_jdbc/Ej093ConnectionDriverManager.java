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
}
