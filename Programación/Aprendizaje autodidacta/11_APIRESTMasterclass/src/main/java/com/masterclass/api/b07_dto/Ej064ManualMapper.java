package com.masterclass.api.b07_dto;

import java.util.List;

/**
 * Ejercicio 064 · Mapper manual y mapeo de listas.
 *
 * <p>Teoría: {@code teoria/07_DTOs_y_Mapeo.md} (sección 7.3).
 */
public final class Ej064ManualMapper {

    public record ProductoEntity(Long id, String nombre, double precioSinIva) {
    }

    public record ProductoDto(Long id, String nombre, double precioConIva) {
    }

    private Ej064ManualMapper() {
    }

    /**
     * Mapea una entidad a DTO aplicando el IVA del 21 %.
     *
     * @param e entidad
     * @return DTO con precio con IVA
     */
    public static ProductoDto toDto(ProductoEntity e) {
        // TODO 1: si e es null -> IllegalArgumentException.
        // TODO 2: copia id y nombre tal cual.
        // TODO 3: calcula precioConIva = precioSinIva * 1.21.
        // TODO 4: construye y devuelve el ProductoDto.
        return null;
    }

    /**
     * Mapea una lista completa reutilizando {@link #toDto}.
     *
     * @param entidades lista de entidades (puede estar vacía, nunca null)
     * @return lista de DTOs en el mismo orden
     */
    public static List<ProductoDto> toDtoList(List<ProductoEntity> entidades) {
        // TODO 5: si 'entidades' es null -> IllegalArgumentException.
        // TODO 6: abre un stream sobre la lista.
        // TODO 7: aplica .map usando la referencia a método toDto.
        // TODO 8: NO dupliques la lógica de conversión (reutiliza toDto).
        // TODO 9: recoge a List preservando el orden.
        // TODO 10: devuelve la lista resultante.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(toDto(new ProductoEntity(1L, "x", 100)));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si e es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: copia id y nombre tal cual.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: calcula precioConIva = precioSinIva * 1.21.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: construye y devuelve el ProductoDto.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si 'entidades' es null -> IllegalArgumentException.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: abre un stream sobre la lista.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: aplica .map usando la referencia a método toDto.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: NO dupliques la lógica de conversión (reutiliza toDto).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: recoge a List preservando el orden.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la lista resultante.
    }

}
