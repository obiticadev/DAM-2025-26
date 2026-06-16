package com.masterclass.api.b11_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 095 · Mapear ResultSet a objetos.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.3).
 *
 * <p>Tabla PRODUCTO(id INT, nombre VARCHAR, precio DOUBLE) (la crea el test).
 * El modelo {@link Producto} es un record de paquete compartido con el Ej101.
 */
public final class Ej095ResultSetMapping {

    private Ej095ResultSetMapping() {
    }

    /**
     * Lee todos los productos ordenados por id ascendente.
     *
     * @param conn conexión
     * @return lista de Producto
     * @throws SQLException si falla la consulta
     */
    public static List<Producto> listar(Connection conn) throws SQLException {
        List<Producto> out = new ArrayList<>();
        // TODO 1: SQL "SELECT id,nombre,precio FROM PRODUCTO ORDER BY id".
        // TODO 2: try-with-resources para PreparedStatement.
        // TODO 3: try-with-resources para el ResultSet (ps.executeQuery()).
        // TODO 4: itera con while (rs.next()).
        // TODO 5: lee rs.getInt("id").
        // TODO 6: lee rs.getString("nombre").
        // TODO 7: lee rs.getDouble("precio").
        // TODO 8: construye un Producto con esos 3 valores.
        // TODO 9: añádelo a 'out'.
        // TODO 10: tras el bucle, devuelve 'out' (orden por id garantizado por el ORDER BY).
        return out;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }

    /**
     * TODO extra 1: Retorna la query SQL de selección de productos.
     */
    public static String desafioObtenerSqlSelect() {
        // GUÍA: teoría 11.3 — el ORDER BY lo hace la BD, no Java.
        // Una línea: return "SELECT id,nombre,precio FROM PRODUCTO ORDER BY id";
        // OJO: el test compara con equals — copia el SQL EXACTO (incluido ORDER BY id).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlSelect");
    }

    /**
     * TODO extra 2: Comprueba si el ResultSet tiene más filas.
     */
    public static boolean desafioTieneMasFilas(java.sql.ResultSet rs) throws java.sql.SQLException {
        // GUÍA: teoría 11.3 — next() avanza el cursor Y dice si había fila.
        // Una línea: return rs.next();
        // OJO: el test lo llama dos veces sobre una tabla de 1 fila: primero true
        //      (se posiciona en la fila), luego false (ya no hay más). next() NO es
        //      una consulta pasiva: cada llamada AVANZA.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioTieneMasFilas");
    }

    /**
     * TODO extra 3: Obtiene el ID del producto actual en el ResultSet.
     */
    public static int desafioObtenerId(java.sql.ResultSet rs) throws java.sql.SQLException {
        // GUÍA: teoría 11.3 — lectura por nombre de columna.
        // Una línea: return rs.getInt("id");
        // OJO: el test posiciona el cursor (rs.next()) y espera 10.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerId");
    }

    /**
     * TODO extra 4: Obtiene el nombre del producto actual en el ResultSet.
     */
    public static String desafioObtenerNombre(java.sql.ResultSet rs) throws java.sql.SQLException {
        // GUÍA: teoría 11.3. Una línea: return rs.getString("nombre");
        // OJO: el test espera "Tablet".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerNombre");
    }

    /**
     * TODO extra 5: Obtiene el precio del producto actual en el ResultSet.
     */
    public static double desafioObtenerPrecio(java.sql.ResultSet rs) throws java.sql.SQLException {
        // GUÍA: teoría 11.3. Una línea: return rs.getDouble("precio");
        // OJO: el test espera 150.5 (con delta 0.001).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerPrecio");
    }

    /**
     * TODO extra 6: Construye una instancia de Producto.
     */
    public static Producto desafioInstanciarProducto(int id, String nombre, double precio) {
        // GUÍA: teoría 1.1 (record) + 11.3 (fila → objeto).
        // Una línea: return new Producto(id, nombre, precio);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioInstanciarProducto");
    }

    /**
     * TODO extra 7: Agrega un Producto a una lista mutable.
     */
    public static void desafioAgregarALista(java.util.List<Producto> lista, Producto p) {
        // GUÍA: teoría 11.3 — el bucle de mapeo va acumulando en una lista.
        // Una línea: lista.add(p);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioAgregarALista");
    }

    /**
     * TODO extra 8: Valida que la lista de salida no sea nula.
     */
    public static void desafioValidarListaDeSalida(java.util.List<Producto> lista) {
        // GUÍA: validación defensiva (teoría 1.9).
        // if (lista == null) throw new IllegalArgumentException("Lista nula");
        // OJO: el test exige IllegalArgumentException con null, y NINGUNA excepción
        //      con una lista vacía (vacía es válida; null no).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioValidarListaDeSalida");
    }

    /**
     * TODO extra 9: Retorna una lista inmodificable de productos mapeados.
     */
    public static java.util.List<Producto> desafioMapearInmodificable(java.util.List<Producto> lista) {
        // GUÍA: una vista inmutable protege la lista frente a modificaciones externas.
        // Una línea: return java.util.List.copyOf(lista);
        // OJO: el test hace .add(...) sobre el resultado y espera
        //      UnsupportedOperationException — List.copyOf devuelve una lista inmutable
        //      (NO uses Collections.unmodifiableList sobre la misma referencia: copyOf copia).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioMapearInmodificable");
    }

    /**
     * TODO extra 10: Verifica si un double obtenido del ResultSet fue nulo en base de datos.
     */
    public static boolean desafioFueNulo(java.sql.ResultSet rs) throws java.sql.SQLException {
        // GUÍA: teoría 11.3 — la trampa del NULL. getDouble sobre NULL devuelve 0.0;
        // wasNull() te dice si la ÚLTIMA columna leída era realmente NULL.
        // Una línea: return rs.wasNull();
        // OJO: el test inserta precio NULL, hace rs.getDouble("precio") y LUEGO llama
        //      a este método esperando true. wasNull() solo es fiable justo tras el getXxx.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioFueNulo");
    }

}
