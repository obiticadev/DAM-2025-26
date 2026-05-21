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

    /**
     * RETO EXTRA 01: Mapeador individual que calcula y aplica un descuento promocional
     * condicional (porcentaje entre 0 y 100) sobre el precio final con IVA.
     *
     * @param entity la entidad origen
     * @param descuentoPorcentaje el porcentaje de descuento a aplicar (ej: 10.0 para 10%)
     * @return DTO con el precio final calculado
     */
    public static ProductoDto pasoExtra01(ProductoEntity entity, double descuentoPorcentaje) {
        // TODO extra: si la entidad es null o el descuento está fuera del rango [0, 100], lanzar IllegalArgumentException.
        // Calcula el precio con IVA (21%) y luego aplica el descuento. Retorna el ProductoDto resultante.
        return null;
    }

    /**
     * RETO EXTRA 02: Conversión en lote robusta que omite y filtra de forma segura
     * elementos nulos (tanto la lista en sí, que retornaría vacía, como elementos null individuales).
     *
     * @param entidades lista de entidades
     * @return lista de DTOs mapeados sin elementos null
     */
    public static List<ProductoDto> pasoExtra02(List<ProductoEntity> entidades) {
        // TODO extra: si 'entidades' es null, retornar una lista vacía.
        // Filtra los elementos null de la lista y mapea los válidos usando toDto.
        return List.of();
    }

    /**
     * RETO EXTRA 03: Mapeo inverso de DTO a Entidad deduciendo algebraicamente el
     * precio base sin el 21% de IVA a partir del precio con IVA del DTO.
     *
     * @param dto DTO del producto
     * @return Entidad con el precio base sin IVA deducido
     */
    public static ProductoEntity pasoExtra03(ProductoDto dto) {
        // TODO extra: si el dto es null, lanzar IllegalArgumentException.
        // Deduce el precioSinIva dividiendo el precioConIva entre 1.21.
        return null;
    }

    /**
     * RETO EXTRA 04: Conversión de catálogo agrupando productos por rangos de precio:
     * - "BARATO" si el precio con IVA es menor de 50.0.
     * - "MEDIO" si el precio con IVA está entre 50.0 (inclusive) y 100.0 (exclusive).
     * - "CARO" si el precio con IVA es mayor o igual a 100.0.
     *
     * @param entidades lista de entidades
     * @return mapa que agrupa los productos mapeados por su rango de precio
     */
    public static java.util.Map<String, List<ProductoDto>> pasoExtra04(List<ProductoEntity> entidades) {
        // TODO extra: si 'entidades' es null, lanzar IllegalArgumentException.
        // Mapea a DTOs (omitiendo nulls) y agrúpalos en un mapa con claves "BARATO", "MEDIO" o "CARO".
        return java.util.Map.of();
    }

    /**
     * RETO EXTRA 05: Redondeo matemático exacto de importes a dos cifras decimales
     * usando precisión financiera (RoundingMode.HALF_UP).
     *
     * @param precio importe a redondear
     * @return importe redondeado a 2 decimales
     */
    public static double pasoExtra05(double precio) {
        // TODO extra: si el precio es negativo, lanzar IllegalArgumentException.
        // Redondea el valor double a 2 decimales con precisión exacta (puedes usar BigDecimal o Math).
        return 0.0;
    }

    /**
     * RETO EXTRA 06: Paginación manual de la lista mapeada aplicando desplazamientos
     * (offset) y límites (limit) de forma segura.
     *
     * @param entidades lista de entidades
     * @param offset índice base (0-indexed)
     * @param limit cantidad máxima de elementos a retornar
     * @return sublista de DTOs paginados
     */
    public static List<ProductoDto> pasoExtra06(List<ProductoEntity> entidades, int offset, int limit) {
        // TODO extra: si 'entidades' es null, o offset < 0, o limit <= 0, lanzar IllegalArgumentException.
        // Mapea la lista a DTOs y devuelve la sublista adecuada cuidando de no exceder los límites de la lista.
        return List.of();
    }

    /**
     * RETO EXTRA 07: Validador de viabilidad comercial inyectando un indicador de "disponibilidad"
     * en base al stock proporcionado.
     *
     * @param entity entidad del producto
     * @param stock cantidad de inventario
     * @return true si tiene stock positivo y precio sin IVA mayor a cero, false en caso contrario
     */
    public static boolean pasoExtra07(ProductoEntity entity, int stock) {
        // TODO extra: si la entidad es null o el stock es negativo, lanzar IllegalArgumentException.
        // Verifica si es comercialmente viable (stock > 0 y precioSinIva > 0).
        return false;
    }

    /**
     * RETO EXTRA 08: Búsqueda del producto más económico del catálogo de entidades
     * retornando directamente su DTO mapeado.
     *
     * @param entidades lista de entidades
     * @return el DTO del producto con el menor precio con IVA, o null si la lista está vacía
     */
    public static ProductoDto pasoExtra08(List<ProductoEntity> entidades) {
        // TODO extra: si la lista 'entidades' es null, lanzar IllegalArgumentException.
        // Mapea todas a DTOs y encuentra la de menor precio con IVA. Si está vacía, retorna null.
        return null;
    }

    /**
     * RETO EXTRA 09: Conversión bulk internacional recalculando precios multiplicándolos
     * por una tasa de cambio dinámica.
     *
     * @param entidades lista de entidades
     * @param tasaCambio factor multiplicador de divisa
     * @return lista de DTOs en la divisa destino
     */
    public static List<ProductoDto> pasoExtra09(List<ProductoEntity> entidades, double tasaCambio) {
        // TODO extra: si la lista es null o la tasaCambio <= 0, lanzar IllegalArgumentException.
        // Convierte a DTOs aplicando el IVA y multiplicando por la tasa de cambio dada.
        return List.of();
    }

    /**
     * RETO EXTRA 10: Resolución del símbolo y divisa local según el código de configuración de localización.
     * Ejemplos: "es-ES" -> "EUR", "en-US" -> "USD", "en-GB" -> "GBP".
     *
     * @param localeStr cadena con código regional (ej: "es-ES" o "en-US")
     * @return el código ISO de 3 letras de la moneda (ej: "EUR")
     */
    public static String pasoExtra10(String localeStr) {
        // TODO extra: si localeStr es null o vacío, lanzar IllegalArgumentException.
        // Parsea el localeStr (ej: dividiendo por "-" o usando Locale.forLanguageTag) y devuelve el código de moneda ISO 4217.
        return null;
    }

}
