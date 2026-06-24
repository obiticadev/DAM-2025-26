package com.masterclass.api.b46_datacomp;

/**
 * Dato que maneja el componente DAO de {@code Ej354DataAccessComponent} (record inmutable, apoyo).
 *
 * @param id     identificador único del cliente
 * @param nombre nombre del cliente
 */
public record Cliente(int id, String nombre) {
}
