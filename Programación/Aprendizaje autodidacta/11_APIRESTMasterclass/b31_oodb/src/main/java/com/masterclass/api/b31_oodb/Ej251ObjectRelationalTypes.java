package com.masterclass.api.b31_oodb;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Ejercicio 251 · Tipos objeto-relacionales: columnas que guardan estructuras (ARRAY).
 *
 * <p>El modelo relacional puro solo admite valores atómicos en cada celda (1ª forma normal).
 * Las extensiones <b>objeto-relacionales</b> rompen esa regla: una celda puede contener un
 * {@code ARRAY}, un tipo estructurado ({@code STRUCT}) o una referencia. JDBC los expone con
 * {@link java.sql.Array}, {@link java.sql.Struct} y {@link java.sql.Ref}.
 *
 * <p>Aquí practicas el más portable, {@code ARRAY}: una columna {@code VARCHAR ARRAY} que
 * guarda varias etiquetas en una sola fila. El test crea la tabla PROD con una fila de
 * etiquetas {@code ARRAY['java','sql','jdbc']}.
 *
 * <p>Teoría: {@code teoria/31_ObjetoRelacional_OO.md} (sección 31.3).
 */
public final class Ej251ObjectRelationalTypes {

    private Ej251ObjectRelationalTypes() {
    }

    /**
     * Lee la columna ARRAY 'etiquetas' del producto indicado.
     *
     * @return los elementos como Object[], o {@code null} si no hay fila o no se ha implementado
     */
    public static Object[] leerEtiquetas(Connection c, int id) throws SQLException {
        // TODO 1: prepara "SELECT etiquetas FROM PROD WHERE id=?" en try-with-resources y ps.setInt(1, id).
        // TODO 2: ResultSet rs = ps.executeQuery(); si !rs.next() devuelve null.
        // TODO 3: obtén el array SQL: java.sql.Array arr = rs.getArray("etiquetas").
        // TODO 4: si arr es null, devuelve null.
        // TODO 5: conviértelo a Java con Object[] datos = (Object[]) arr.getArray().
        // TODO 6: devuelve datos (propaga SQLException; el try-with-resources cierra el statement).
        return null;
    }

    /**
     * Cuenta cuántos elementos tiene la columna ARRAY del producto.
     *
     * @return número de etiquetas, o -1 si no se ha implementado
     */
    public static int contarEtiquetas(Connection c, int id) throws SQLException {
        // TODO 7: reutiliza leerEtiquetas(c, id) para no repetir el acceso a la BD.
        // TODO 8: si el resultado es null (producto inexistente) devuelve 0, NO -1.
        // TODO 9: devuelve etiquetas.length.
        // TODO 10: cualquier SQLException se propaga (la declara el método).
        return -1;
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:h2:mem:demo251", "sa", "")) {
            var st = c.createStatement();
            st.execute("CREATE TABLE PROD(id INT, etiquetas VARCHAR ARRAY)");
            st.execute("INSERT INTO PROD VALUES (1, ARRAY['java','sql','jdbc'])");
            System.out.println("leerEtiquetas(1).length = "
                    + (leerEtiquetas(c, 1) == null ? "null" : leerEtiquetas(c, 1).length));
            System.out.println("contarEtiquetas(1) = " + contarEtiquetas(c, 1));
        }
    }

    /**
     * Reto Extra 1: la primera etiqueta del array.
     * @return etiquetas[0] como String (el test espera "java")
     */
    public static String primeraEtiqueta(Connection c, int id) throws SQLException {
        // GUÍA: parte de leerEtiquetas(c, id); si es null o vacío devuelve null, si no (String) datos[0].
        // OJO: los elementos llegan como Object; castea a String. El primero es "java".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para primeraEtiqueta");
    }

    /**
     * Reto Extra 2: ¿contiene el array una etiqueta concreta?
     * @return true si la etiqueta está (el test pregunta por "sql" -> true)
     */
    public static boolean contieneEtiqueta(Connection c, int id, String etiqueta) throws SQLException {
        // GUÍA: recorre leerEtiquetas(c, id) o usa java.util.Arrays.asList(datos).contains(etiqueta).
        // PISTA: Object[] datos = leerEtiquetas(c, id); return datos != null && Arrays.asList(datos).contains(etiqueta);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneEtiqueta");
    }

    /**
     * Reto Extra 3: todas las etiquetas en mayúsculas.
     * @return las etiquetas en mayúsculas (el test espera [JAVA, SQL, JDBC])
     */
    public static java.util.List<String> etiquetasEnMayusculas(Connection c, int id) throws SQLException {
        // GUÍA: Object[] datos = leerEtiquetas(c, id); recorre y añade ((String) o).toUpperCase() a una List.
        // OJO: el test compara con List.of("JAVA","SQL","JDBC"); respeta el orden del array.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para etiquetasEnMayusculas");
    }

    /**
     * Reto Extra 4: crea un ARRAY SQL desde Java con Connection.createArrayOf y mide su tamaño.
     * @return el número de elementos del array creado (el test espera 2)
     */
    public static int crearArrayDesdeJava(Connection c) throws SQLException {
        // GUÍA: Array a = c.createArrayOf("VARCHAR", new Object[]{"uno","dos"});
        //       Object[] datos = (Object[]) a.getArray(); return datos.length;
        // CULTURA: createArrayOf es el camino inverso (Java -> SQL), útil para pasar un ARRAY como parámetro.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearArrayDesdeJava");
    }

    /**
     * Reto Extra 5: tamaño de un ARRAY literal en una consulta.
     * @return el length de ARRAY[10,20,30,40] (el test espera 4)
     */
    public static int tamanoArrayLiteral(Connection c) throws SQLException {
        // GUÍA: try (var rs = c.createStatement().executeQuery("SELECT ARRAY[10,20,30,40]")) {
        //         rs.next(); Object[] datos = (Object[]) rs.getArray(1).getArray(); return datos.length; }
        // PISTA: H2 evalúa ARRAY[...] como un valor; lo recoges con getArray(1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoArrayLiteral");
    }

    /**
     * Reto Extra 6: une las etiquetas con comas.
     * @return "java,sql,jdbc" (el test lo compara con equals)
     */
    public static String unirEtiquetas(Connection c, int id) throws SQLException {
        // GUÍA: Object[] datos = leerEtiquetas(c, id); convierte a String[] o usa un StringBuilder.
        // PISTA: return String.join(",", Arrays.stream(datos).map(Object::toString).toArray(String[]::new));
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para unirEtiquetas");
    }

    /**
     * Reto Extra 7: el código de tipo JDBC para un ARRAY.
     * @return Types.ARRAY (el test compara con java.sql.Types.ARRAY)
     */
    public static int tipoArray() {
        // GUÍA: una línea: return Types.ARRAY;
        // CULTURA: cada tipo objeto-relacional tiene su constante: Types.ARRAY, Types.STRUCT, Types.REF.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tipoArray");
    }

    /**
     * Reto Extra 8: índice (0-based) de una etiqueta dentro del array.
     * @return la posición de la etiqueta (el test busca "jdbc" -> 2; -1 si no está)
     */
    public static int indiceDeEtiqueta(Connection c, int id, String etiqueta) throws SQLException {
        // GUÍA: Object[] datos = leerEtiquetas(c, id); return Arrays.asList(datos).indexOf(etiqueta);
        // OJO: "jdbc" es el tercer elemento -> índice 2. indexOf ya devuelve -1 si no está.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indiceDeEtiqueta");
    }

    /**
     * Reto Extra 9: la última etiqueta del array.
     * @return el último elemento (el test espera "jdbc")
     */
    public static String ultimaEtiqueta(Connection c, int id) throws SQLException {
        // GUÍA: Object[] datos = leerEtiquetas(c, id); return (String) datos[datos.length - 1];
        // PISTA: defiende null/vacío antes de indexar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ultimaEtiqueta");
    }

    /**
     * Reto Extra 10: ¿tiene el producto al menos una etiqueta?
     * @return true si contarEtiquetas > 0 (el test espera true para el producto 1)
     */
    public static boolean tieneEtiquetas(Connection c, int id) throws SQLException {
        // GUÍA: return contarEtiquetas(c, id) > 0;  // reutiliza el core.
        // CUIDADO: si contarEtiquetas sigue devolviendo -1, este reto será rojo; implementa antes el core.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneEtiquetas");
    }
}
