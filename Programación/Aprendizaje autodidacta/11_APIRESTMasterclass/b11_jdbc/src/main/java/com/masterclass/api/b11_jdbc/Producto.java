package com.masterclass.api.b11_jdbc;

/**
 * Modelo de dominio compartido por los ejercicios del bloque 11.
 *
 * <p>Un {@code record} inmutable (teoría 1.1) que representa una fila de la
 * tabla PRODUCTO(id, nombre, precio). Lo usan {@link Ej095ResultSetMapping} y
 * {@link Ej101RowMapperAndExtractor}.
 */
public record Producto(int id, String nombre, double precio) {
}
