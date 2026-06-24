package com.masterclass.api.b43_erp;

/**
 * Modelo maestro de cliente compartido por el bloque b43 (≈ {@code res.partner} de Odoo).
 *
 * <p>Es un <em>record</em> inmutable (idea de b01): un registro de datos del ERP. El
 * {@code idExterno} es la clave de negocio estable que viaja entre sistemas (el "ref" de
 * Odoo), distinta del id técnico autoincremental de cada base de datos. La integración
 * idempotente de b43 (Ej336) se apoya en este {@code idExterno} para decidir alta vs. modificación.
 *
 * @param idExterno clave de negocio estable (p.ej. "CLI-001"); nunca null ni vacía
 * @param nombre    razón social / nombre del cliente
 * @param email     correo de contacto (puede ir vacío)
 * @param pais      código de país ISO de 2 letras en mayúsculas (p.ej. "ES")
 */
public record ClienteErp(String idExterno, String nombre, String email, String pais) {
}
