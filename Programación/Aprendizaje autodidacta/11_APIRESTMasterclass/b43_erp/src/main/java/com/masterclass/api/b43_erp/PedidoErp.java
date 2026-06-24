package com.masterclass.api.b43_erp;

/**
 * Modelo transaccional de pedido compartido por el bloque b43 (≈ {@code sale.order} de Odoo).
 *
 * <p>A diferencia de {@link ClienteErp} (dato maestro, cambia poco), un pedido es un
 * <em>movimiento</em>: se crea, no se reedita. Las agregaciones de BI de Ej335 (ventas por
 * mes, top de clientes, ticket medio) recorren listas de estos registros — exactamente la
 * misma idea de {@code GROUP BY} que viste con SQL/JPA en b15, pero ahora en memoria sobre
 * objetos del ERP.
 *
 * @param idExterno   identificador del pedido (p.ej. "SO-2026-0001")
 * @param idCliente   {@code idExterno} del cliente que lo realizó (clave foránea de negocio)
 * @param fecha       fecha del pedido en formato ISO {@code yyyy-MM-dd}
 * @param categoria   categoría de producto principal del pedido (p.ej. "Software")
 * @param importe     importe total del pedido con impuestos incluidos (euros)
 */
public record PedidoErp(String idExterno, String idCliente, String fecha, String categoria, double importe) {
}
