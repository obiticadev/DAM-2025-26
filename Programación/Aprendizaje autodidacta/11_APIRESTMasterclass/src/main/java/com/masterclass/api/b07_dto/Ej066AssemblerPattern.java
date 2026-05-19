package com.masterclass.api.b07_dto;

import java.util.List;

/**
 * Ejercicio 066 · Patrón Assembler (ensambla un DTO desde varias fuentes).
 *
 * <p>Teoría: {@code teoria/07_DTOs_y_Mapeo.md} (sección 7.3).
 */
public final class Ej066AssemblerPattern {

    public record Pedido(Long id, String cliente) {
    }

    public record Linea(Long pedidoId, String producto, int cantidad) {
    }

    /** DTO compuesto que el Assembler ensambla. */
    public record PedidoDetalleDto(Long id, String cliente, int totalUnidades, List<String> productos) {
    }

    private Ej066AssemblerPattern() {
    }

    /**
     * Ensambla el DTO de detalle combinando el pedido y sus líneas.
     *
     * @param pedido cabecera
     * @param lineas líneas del pedido (de otra fuente)
     * @return DTO agregado
     * @throws IllegalArgumentException si pedido es null o las líneas no son de ese pedido
     */
    public static PedidoDetalleDto ensamblar(Pedido pedido, List<Linea> lineas) {
        // TODO 1: si pedido es null -> IllegalArgumentException.
        // TODO 2: si lineas es null, trátalo como lista vacía.
        // TODO 3: valida que TODA línea tenga pedidoId == pedido.id (coherencia).
        // TODO 4: si alguna no coincide -> IllegalArgumentException.
        // TODO 5: calcula totalUnidades sumando cantidad de todas las líneas (stream).
        // TODO 6: extrae la lista de nombres de producto (map + toList).
        // TODO 7: copia id y cliente del pedido.
        // TODO 8: combina datos de DOS fuentes (esa es la esencia del Assembler).
        // TODO 9: construye el PedidoDetalleDto.
        // TODO 10: devuélvelo.
        return null;
    }

    public static void main(String[] args) {
        var d = ensamblar(new Pedido(1L, "Ana"),
                List.of(new Linea(1L, "cafe", 2), new Linea(1L, "te", 1)));
        System.out.println(d);
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si pedido es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si lineas es null, trátalo como lista vacía.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: valida que TODA línea tenga pedidoId == pedido.id (coherencia).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si alguna no coincide -> IllegalArgumentException.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: calcula totalUnidades sumando cantidad de todas las líneas (stream).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: extrae la lista de nombres de producto (map + toList).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: copia id y cliente del pedido.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: combina datos de DOS fuentes (esa es la esencia del Assembler).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: construye el PedidoDetalleDto.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuélvelo.
    }

}
