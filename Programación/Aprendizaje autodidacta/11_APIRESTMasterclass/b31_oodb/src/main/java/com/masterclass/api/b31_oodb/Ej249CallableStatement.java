package com.masterclass.api.b31_oodb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Ejercicio 249 · CallableStatement: llamar a procedimientos y funciones almacenados.
 *
 * <p>Un procedimiento/función almacenado es código que vive <b>dentro</b> del SGBD y se invoca
 * desde Java con {@link CallableStatement} y la sintaxis de escape JDBC: {@code {call P(?)}}
 * para un procedimiento y {@code {? = call F(?)}} para una función con valor de retorno. Los
 * parámetros de salida se declaran con {@code registerOutParameter(indice, Types.X)} y se leen
 * después con {@code getInt}/{@code getString}.
 *
 * <p>En H2 no hay PL/SQL: una "función almacenada" es un método Java registrado con
 * {@code CREATE ALIAS} (ver {@link ProcsAlmacenados}). El test da de alta los alias SUMAR,
 * SALUDAR y FACT antes de invocarte.
 *
 * <p>Teoría: {@code teoria/31_ObjetoRelacional_OO.md} (sección 31.1).
 */
public final class Ej249CallableStatement {

    private Ej249CallableStatement() {
    }

    /**
     * Llama a la función almacenada SUMAR(a, b) y devuelve su resultado.
     *
     * @return la suma calculada por el SGBD, o -1 si no se ha implementado
     */
    public static int llamarSuma(Connection c, int a, int b) throws SQLException {
        // TODO 1: prepara la llamada con c.prepareCall("{? = call SUMAR(?, ?)}") dentro de try-with-resources.
        // TODO 2: registra el parámetro de SALIDA: cs.registerOutParameter(1, Types.INTEGER).
        // TODO 3: fija las ENTRADAS empezando en el índice 2: cs.setInt(2, a) y cs.setInt(3, b).
        // TODO 4: ejecuta con cs.execute().
        // TODO 5: recupera el valor de retorno con cs.getInt(1).
        // TODO 6: devuélvelo; deja que SQLException se propague (la declara el método).
        return -1;
    }

    /**
     * Llama a la función almacenada SALUDAR(nombre) y devuelve el saludo.
     *
     * @return el saludo "Hola, <nombre>", o {@code null} si no se ha implementado o nombre es null
     */
    public static String llamarSaludo(Connection c, String nombre) throws SQLException {
        // TODO 7: si nombre es null devuelve null sin tocar la BD (evita un fallo en la función).
        // TODO 8: prepara cs = c.prepareCall("{? = call SALUDAR(?)}") y registra salida Types.VARCHAR en el índice 1.
        // TODO 9: fija la entrada con cs.setString(2, nombre) y ejecuta.
        // TODO 10: devuelve cs.getString(1) (try-with-resources; propaga SQLException).
        return null;
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:h2:mem:demo249", "sa", "")) {
            c.createStatement().execute(
                    "CREATE ALIAS SUMAR FOR \"com.masterclass.api.b31_oodb.ProcsAlmacenados.sumar\"");
            c.createStatement().execute(
                    "CREATE ALIAS SALUDAR FOR \"com.masterclass.api.b31_oodb.ProcsAlmacenados.saludar\"");
            System.out.println("llamarSuma(3,4) = " + llamarSuma(c, 3, 4));
            System.out.println("llamarSaludo(\"Ana\") = " + llamarSaludo(c, "Ana"));
        }
    }

    /**
     * Reto Extra 1: misma suma, pero recalcando que el índice 1 es la salida y 2,3 las entradas.
     * @return SUMAR(a,b) (el test envía 3 y 4 y espera 7)
     */
    public static int sumaConIndices(Connection c, int a, int b) throws SQLException {
        // GUÍA: teoría 31.1. Es llamarSuma reescrito para fijar el modelo mental de los índices:
        // en "{? = call SUMAR(?, ?)}" el ? de la izquierda es el índice 1 (OUT), y los dos
        // siguientes son 2 y 3 (IN). registerOutParameter(1, Types.INTEGER); setInt(2,a); setInt(3,b).
        // OJO: el test usa (3,4) y espera 7. Confundir los índices es el error nº1 del bloque.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumaConIndices");
    }

    /**
     * Reto Extra 2: invoca la función con un Statement normal y CALL (sin parámetro OUT).
     * @return el resultado de "CALL SUMAR(2, 3)" leído del ResultSet (el test espera 5)
     */
    public static int sumaConCallStatement(Connection c) throws SQLException {
        // GUÍA: una función H2 también se puede llamar como consulta: st.executeQuery("CALL SUMAR(2, 3)").
        // Recorre el ResultSet: rs.next(); return rs.getInt(1).
        // CULTURA: CALL ... devuelve una tabla de una fila/columna; es el atajo cuando no necesitas OUT.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumaConCallStatement");
    }

    /**
     * Reto Extra 3: llama a la función almacenada FACT(n).
     * @return n! calculado por el SGBD (el test envía 5 y espera 120)
     */
    public static int factorialPorFuncion(Connection c, int n) throws SQLException {
        // GUÍA: mismo patrón que llamarSuma con "{? = call FACT(?)}": OUT en 1, setInt(2, n).
        // OJO: el test registra el alias FACT -> ProcsAlmacenados.factorial y comprueba 120 para n=5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para factorialPorFuncion");
    }

    /**
     * Reto Extra 4: la sintaxis de escape JDBC para una FUNCIÓN con retorno.
     * @return la cadena "{? = call SUMAR(?, ?)}" exacta
     */
    public static String escapeFuncion() {
        // GUÍA: una línea: return "{? = call SUMAR(?, ?)}";
        // OJO: el test compara con equals; cuida los espacios (uno tras la coma, uno alrededor del =).
        // CULTURA: las llaves {} son el "escape" portable de JDBC: el driver lo traduce a su dialecto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escapeFuncion");
    }

    /**
     * Reto Extra 5: ¿es una llamada a función (con retorno) o a procedimiento?
     * @return true si el SQL de escape contiene "= call" (función), false si es "{call ...}"
     */
    public static boolean esSintaxisFuncion(String sql) {
        // GUÍA: una línea: return sql != null && sql.contains("= call");
        // OJO: el test pasa "{? = call F(?)}" (true) y "{call P(?)}" (false). Defiende el null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSintaxisFuncion");
    }

    /**
     * Reto Extra 6: el código de tipo SQL para registrar una salida entera.
     * @return Types.INTEGER (el test compara con java.sql.Types.INTEGER)
     */
    public static int tipoSalidaEntera() {
        // GUÍA: una línea: return Types.INTEGER;
        // CULTURA: registerOutParameter necesita saber el tipo para reservar el hueco de salida;
        // por eso pasas un int de java.sql.Types (INTEGER, VARCHAR, NUMERIC, ...).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tipoSalidaEntera");
    }

    /**
     * Reto Extra 7: saludo en mayúsculas a partir de la función SALUDAR.
     * @return llamarSaludo(c, nombre) en mayúsculas (el test envía "Ana" y espera "HOLA, ANA")
     */
    public static String saludoEnMayusculas(Connection c, String nombre) throws SQLException {
        // GUÍA: reutiliza llamarSaludo(c, nombre) y aplica .toUpperCase().
        // OJO: "Hola, Ana".toUpperCase() -> "HOLA, ANA" (con la coma y el espacio).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludoEnMayusculas");
    }

    /**
     * Reto Extra 8: comprueba que la función almacenada da el resultado esperado.
     * @return true si llamarSuma(c, 2, 2) vale 4 (el test lo verifica)
     */
    public static boolean sumaEsCorrecta(Connection c) throws SQLException {
        // GUÍA: return llamarSuma(c, 2, 2) == 4; (reutiliza el core, no repitas la llamada JDBC).
        // CUIDADO: si llamarSuma aún devuelve el centinela -1, este reto será rojo: implementa antes el core.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumaEsCorrecta");
    }

    /**
     * Reto Extra 9: llamar a un alias inexistente debe lanzar SQLException, no romper el programa.
     * @return true si prepareCall/execute sobre "NOEXISTE" lanza SQLException (capturada aquí)
     */
    public static boolean llamarInexistenteLanza(Connection c) {
        // GUÍA: try (var cs = c.prepareCall("{call NOEXISTE()}")) { cs.execute(); return false; }
        //       catch (SQLException e) { return true; }
        // CULTURA: un procedimiento que no existe es error del SGBD -> SQLException. Tragártela aquí
        // es legítimo porque el test SOLO quiere comprobar que detectas el fallo (devuelve true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para llamarInexistenteLanza");
    }

    /**
     * Reto Extra 10: longitud del saludo devuelto por la función.
     * @return llamarSaludo(c, nombre).length() (el test envía "Ana" -> "Hola, Ana" -> 9)
     */
    public static int longitudSaludo(Connection c, String nombre) throws SQLException {
        // GUÍA: return llamarSaludo(c, nombre).length();  // "Hola, Ana" tiene 9 caracteres.
        // PISTA: reutiliza el core; no vuelvas a abrir un CallableStatement.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitudSaludo");
    }
}
