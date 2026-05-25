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
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.1).
 *
 * <p>Tabla PRODUCTO(id INT, nombre VARCHAR, precio DOUBLE) (la crea el test).
 */
public final class Ej095ResultSetMapping {

    public record Producto(int id, String nombre, double precio) {
    }

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
        return "SELECT id,nombre,precio FROM PRODUCTO ORDER BY id";
    }

    /**
     * TODO extra 2: Comprueba si el ResultSet tiene más filas.
     */
    public static boolean desafioTieneMasFilas(java.sql.ResultSet rs) throws java.sql.SQLException {
        return rs.next();
    }

    /**
     * TODO extra 3: Obtiene el ID del producto actual en el ResultSet.
     */
    public static int desafioObtenerId(java.sql.ResultSet rs) throws java.sql.SQLException {
        return rs.getInt("id");
    }

    /**
     * TODO extra 4: Obtiene el nombre del producto actual en el ResultSet.
     */
    public static String desafioObtenerNombre(java.sql.ResultSet rs) throws java.sql.SQLException {
        return rs.getString("nombre");
    }

    /**
     * TODO extra 5: Obtiene el precio del producto actual en el ResultSet.
     */
    public static double desafioObtenerPrecio(java.sql.ResultSet rs) throws java.sql.SQLException {
        return rs.getDouble("precio");
    }

    /**
     * TODO extra 6: Construye una instancia de Producto.
     */
    public static Producto desafioInstanciarProducto(int id, String nombre, double precio) {
        return new Producto(id, nombre, precio);
    }

    /**
     * TODO extra 7: Agrega un Producto a una lista mutable.
     */
    public static void desafioAgregarALista(java.util.List<Producto> lista, Producto p) {
        lista.add(p);
    }

    /**
     * TODO extra 8: Valida que la lista de salida no sea nula.
     */
    public static void desafioValidarListaDeSalida(java.util.List<Producto> lista) {
        if (lista == null) {
            throw new IllegalArgumentException("Lista nula");
        }
    }

    /**
     * TODO extra 9: Retorna una lista inmodificable de productos mapeados.
     */
    public static java.util.List<Producto> desafioMapearInmodificable(java.util.List<Producto> lista) {
        return java.util.List.copyOf(lista);
    }

    /**
     * TODO extra 10: Verifica si un double obtenido del ResultSet fue nulo en base de datos.
     */
    public static boolean desafioFueNulo(java.sql.ResultSet rs) throws java.sql.SQLException {
        return rs.wasNull();
    }

}
