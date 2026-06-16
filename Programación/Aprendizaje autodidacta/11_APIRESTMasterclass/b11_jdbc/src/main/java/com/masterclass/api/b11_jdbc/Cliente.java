package com.masterclass.api.b11_jdbc;

/**
 * Modelo de dominio compartido por los ejercicios del bloque 11.
 *
 * <p>Un {@code record} inmutable (teoría 1.1) que representa una fila de la
 * tabla CLIENTE(id, nombre, email). Lo usan los retos extra de
 * {@link Ej096CrudDao}.
 */
public record Cliente(int id, String nombre, String email) {
}
