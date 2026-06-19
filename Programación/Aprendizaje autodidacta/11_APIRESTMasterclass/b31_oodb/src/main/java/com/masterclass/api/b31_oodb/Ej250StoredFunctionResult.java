package com.masterclass.api.b31_oodb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 250 · Funciones almacenadas que devuelven valor o conjunto de filas.
 *
 * <p>Dos sabores de "función almacenada": las que devuelven un <b>escalar</b> (un número, un
 * texto) y las que devuelven un <b>ResultSet</b> (una tabla). Las primeras se leen con un
 * parámetro OUT o con {@code CALL}; las segundas se consumen como una consulta normal
 * ({@code executeQuery}) recorriendo el {@link ResultSet}.
 *
 * <p>El test registra el alias FACT (escalar) y EMPLEADOS (tabla), y crea/rellena la tabla EMP.
 *
 * <p>Teoría: {@code teoria/31_ObjetoRelacional_OO.md} (sección 31.2).
 */
public final class Ej250StoredFunctionResult {

    private Ej250StoredFunctionResult() {
    }

    /**
     * Llama a la función escalar FACT(n) y devuelve su valor.
     *
     * @return n! calculado por el SGBD, o -1 si no se ha implementado
     */
    public static int valorFuncion(Connection c, int n) throws SQLException {
        // TODO 1: prepara cs = c.prepareCall("{? = call FACT(?)}") en try-with-resources.
        // TODO 2: registra la salida con cs.registerOutParameter(1, Types.INTEGER).
        // TODO 3: fija la entrada con cs.setInt(2, n).
        // TODO 4: ejecuta con cs.execute().
        // TODO 5: devuelve cs.getInt(1) (propaga SQLException).
        return -1;
    }

    /**
     * Llama a la función de tabla EMPLEADOS() y devuelve los nombres en una lista.
     *
     * @return lista de nombres (nunca null), o lista vacía si no se ha implementado
     */
    public static List<String> filasFuncion(Connection c) throws SQLException {
        // TODO 6: prepara cs = c.prepareCall("{call EMPLEADOS()}") en try-with-resources.
        // TODO 7: ejecuta como consulta: ResultSet rs = cs.executeQuery() (devuelve una tabla).
        // TODO 8: recorre con while (rs.next()) y añade rs.getString("nombre") (o getString(1)) a una List.
        // TODO 9: devuelve la lista; si no hay filas devuelve una lista VACÍA, nunca null.
        // TODO 10: el try-with-resources cierra el CallableStatement (y su ResultSet); propaga SQLException.
        return List.of();
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:h2:mem:demo250", "sa", "")) {
            var st = c.createStatement();
            st.execute("CREATE TABLE EMP(id INT, nombre VARCHAR(50))");
            st.execute("INSERT INTO EMP VALUES (1,'Ana'),(2,'Beto'),(3,'Caro')");
            st.execute("CREATE ALIAS FACT FOR \"com.masterclass.api.b31_oodb.ProcsAlmacenados.factorial\"");
            st.execute("CREATE ALIAS EMPLEADOS FOR \"com.masterclass.api.b31_oodb.ProcsAlmacenados.empleados\"");
            System.out.println("valorFuncion(5) = " + valorFuncion(c, 5));
            System.out.println("filasFuncion() = " + filasFuncion(c));
        }
    }

    /**
     * Reto Extra 1: el factorial de 0 es 1.
     * @return FACT(0) (el test espera 1)
     */
    public static int factorialDeCero(Connection c) throws SQLException {
        // GUÍA: reutiliza valorFuncion(c, 0). El bucle de factorial no entra y devuelve 1.
        // OJO: el test comprueba exactamente 1 (caso límite del producto vacío).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para factorialDeCero");
    }

    /**
     * Reto Extra 2: suma de dos factoriales calculados por la función.
     * @return FACT(3) + FACT(4) (el test espera 6 + 24 = 30)
     */
    public static int sumaFactoriales(Connection c) throws SQLException {
        // GUÍA: return valorFuncion(c, 3) + valorFuncion(c, 4);  // reutiliza el core dos veces.
        // PISTA: 3! = 6 y 4! = 24 -> 30.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumaFactoriales");
    }

    /**
     * Reto Extra 3: nombres de empleados en mayúsculas.
     * @return los nombres de filasFuncion en mayúsculas (el test espera [ANA, BETO, CARO])
     */
    public static List<String> nombresEnMayusculas(Connection c) throws SQLException {
        // GUÍA: parte de filasFuncion(c) y transforma con stream().map(String::toUpperCase).toList().
        // OJO: el orden viene de la función (ORDER BY nombre): Ana, Beto, Caro -> ANA, BETO, CARO.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombresEnMayusculas");
    }

    /**
     * Reto Extra 4: cuántas filas devuelve la función de tabla.
     * @return filasFuncion(c).size() (el test espera 3)
     */
    public static int cuantosEmpleados(Connection c) throws SQLException {
        // GUÍA: return filasFuncion(c).size();  // reutiliza el core, no vuelvas a consultar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cuantosEmpleados");
    }

    /**
     * Reto Extra 5: ¿está un empleado en el resultado de la función?
     * @return true si filasFuncion contiene el nombre (el test pregunta por "Beto" -> true)
     */
    public static boolean contieneEmpleado(Connection c, String nombre) throws SQLException {
        // GUÍA: return filasFuncion(c).contains(nombre);
        // OJO: contains es sensible a mayúsculas: el test usa "Beto" tal cual está en la tabla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneEmpleado");
    }

    /**
     * Reto Extra 6: el escape JDBC para llamar a un procedimiento SIN retorno.
     * @return la cadena "{call EMPLEADOS()}" exacta
     */
    public static String escapeProcedimiento() {
        // GUÍA: una línea: return "{call EMPLEADOS()}";
        // CULTURA: sin "? =" delante porque no recoges un valor de retorno, consumes un ResultSet.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escapeProcedimiento");
    }

    /**
     * Reto Extra 7: el código de tipo SQL para un texto.
     * @return Types.VARCHAR (el test compara con java.sql.Types.VARCHAR)
     */
    public static int tipoTexto() {
        // GUÍA: una línea: return Types.VARCHAR;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tipoTexto");
    }

    /**
     * Reto Extra 8: factorial de un número negativo (producto vacío).
     * @return FACT(-3) (con nuestra implementación, 1; el test lo verifica)
     */
    public static int factorialNegativo(Connection c) throws SQLException {
        // GUÍA: reutiliza valorFuncion(c, -3). El bucle "for i=2; i<=-3" no entra -> 1.
        // CULTURA: matemáticamente n! no se define para negativos; aquí ilustra que el comportamiento
        // de una función almacenada lo decide su código, no el SGBD.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para factorialNegativo");
    }

    /**
     * Reto Extra 9: el primer nombre devuelto por la función.
     * @return filasFuncion(c).get(0) (el test espera "Ana", primero por ORDER BY)
     */
    public static String primerEmpleado(Connection c) throws SQLException {
        // GUÍA: parte de filasFuncion(c); si está vacía devuelve null, si no get(0).
        // OJO: la función ordena por nombre, así que el primero es "Ana".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para primerEmpleado");
    }

    /**
     * Reto Extra 10: concatena los nombres separados por comas.
     * @return "Ana,Beto,Caro" (el test lo compara con equals)
     */
    public static String concatenarNombres(Connection c) throws SQLException {
        // GUÍA: return String.join(",", filasFuncion(c));
        // OJO: sin espacios alrededor de la coma; el orden es el de la función (Ana,Beto,Caro).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para concatenarNombres");
    }
}
