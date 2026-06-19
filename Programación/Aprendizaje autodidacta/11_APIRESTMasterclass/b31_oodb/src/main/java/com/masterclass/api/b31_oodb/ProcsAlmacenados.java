package com.masterclass.api.b31_oodb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase de apoyo del bloque b31 (no es un ejercicio).
 *
 * <p>H2 no tiene PL/SQL: sus "procedimientos y funciones almacenados" son métodos Java
 * estáticos registrados con {@code CREATE ALIAS NOMBRE FOR "com.....metodo"}. Esta clase
 * reúne esos métodos para que los ejercicios y sus tests los den de alta como alias y los
 * invoquen con {@link java.sql.CallableStatement}. Es el equivalente didáctico, sobre H2, a
 * un {@code CREATE PROCEDURE}/{@code CREATE FUNCTION} de Oracle o PostgreSQL.
 *
 * <p>Patrón clave: si el primer parámetro del método es {@link Connection}, H2 le inyecta la
 * conexión actual; el método puede devolver un {@link ResultSet} y la función se consumirá
 * como una consulta ({@code executeQuery}).
 *
 * <p>Teoría: {@code teoria/31_ObjetoRelacional_OO.md} (secciones 31.1 y 31.2).
 */
public final class ProcsAlmacenados {

    private ProcsAlmacenados() {
    }

    /** Función escalar: suma dos enteros. Se registra como alias SUMAR. */
    public static int sumar(int a, int b) {
        return a + b;
    }

    /** Función escalar: saluda. Se registra como alias SALUDAR. */
    public static String saludar(String nombre) {
        return "Hola, " + nombre;
    }

    /** Función escalar: factorial (n! ; con n &lt; 2 devuelve 1). Se registra como alias FACT. */
    public static int factorial(int n) {
        int r = 1;
        for (int i = 2; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    /**
     * Función de tabla: devuelve un ResultSet con los nombres de la tabla EMP.
     * H2 inyecta la conexión en el primer parámetro. Se registra como alias EMPLEADOS.
     */
    public static ResultSet empleados(Connection conn) throws SQLException {
        return conn.createStatement().executeQuery("SELECT nombre FROM EMP ORDER BY nombre");
    }
}
