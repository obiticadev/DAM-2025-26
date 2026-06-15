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
        // GUÍA: teoría 7.2 (reutiliza toDto y luego aplica el descuento).
        // 1. Si entity es null -> IllegalArgumentException.
        // 2. Si descuentoPorcentaje < 0 o > 100 -> IllegalArgumentException.
        // 3. Parte del DTO con IVA llamando a toDto(entity) (NO recalcules el IVA).
        // 4. Aplica el descuento sobre precioConIva: precio * (1 - desc/100).
        // 5. Devuelve un ProductoDto NUEVO con ese precio final.
        // PISTA: var base = toDto(entity);
        //        double finalPrecio = base.precioConIva() * (1 - descuentoPorcentaje / 100.0);
        //        return new ProductoDto(base.id(), base.nombre(), finalPrecio);
        // OJO: el test usa precio 1000 -> conIva 1210; con 10% espera EXACTAMENTE
        //      1089.0 (= 1210 - 121). Y exige IllegalArgumentException para entity
        //      null, descuento -5.0 y descuento 105.0. Cuidado: usa 100.0 (double),
        //      no 100 (int), o la división truncará.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * RETO EXTRA 02: Conversión en lote robusta que omite y filtra de forma segura
     * elementos nulos (tanto la lista en sí, que retornaría vacía, como elementos null individuales).
     *
     * @param entidades lista de entidades
     * @return lista de DTOs mapeados sin elementos null
     */
    public static List<ProductoDto> pasoExtra02(List<ProductoEntity> entidades) {
        // GUÍA: teoría 7.2 (variante robusta de toDtoList con stream).
        // 1. Si entidades es null -> devuelve List.of() (vacía), NO lances excepción.
        // 2. Filtra antes de mapear: descarta los elementos null de la lista Y los
        //    que tengan precioSinIva < 0 (no son productos válidos).
        // 3. Mapea con toDto y recoge a List.
        // PISTA: return entidades.stream()
        //          .filter(java.util.Objects::nonNull)
        //          .filter(e -> e.precioSinIva() >= 0)
        //          .map(Ej064ManualMapper::toDto)
        //          .toList();
        // OJO: el test mete una lista con un null y un producto de precio -10.0; espera
        //      size()==2 (sobreviven A=121.0 y C=60.5). Y pasoExtra02(null) debe
        //      devolver lista vacía (isEmpty()), no NPE. El orden se conserva: A antes que C.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * RETO EXTRA 03: Mapeo inverso de DTO a Entidad deduciendo algebraicamente el
     * precio base sin el 21% de IVA a partir del precio con IVA del DTO.
     *
     * @param dto DTO del producto
     * @return Entidad con el precio base sin IVA deducido
     */
    public static ProductoEntity pasoExtra03(ProductoDto dto) {
        // GUÍA: mapeo inverso (DTO -> Entity), deshaciendo el IVA.
        // 1. Si dto es null -> IllegalArgumentException.
        // 2. Despeja el precio base: si conIva = sinIva * 1.21, entonces
        //    sinIva = conIva / 1.21.
        // 3. Devuelve new ProductoEntity(dto.id(), dto.nombre(), precioBase).
        // PISTA: return new ProductoEntity(dto.id(), dto.nombre(), dto.precioConIva() / 1.21);
        // OJO: el test parte de conIva 1210.0 y espera precioSinIva EXACTAMENTE 1000.0
        //      (tolerancia 0.0001). Divide entre 1.21, no restes un 21%.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
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
        // GUÍA: teoría 7.2 + collectors (groupingBy, bloque 1.4).
        // 1. Si entidades es null -> IllegalArgumentException.
        // 2. Mapea a DTO y agrupa por una función-clave que clasifique el precioConIva:
        //    < 50 -> "BARATO"; >= 50 y < 100 -> "MEDIO"; >= 100 -> "CARO".
        // 3. Extrae esa clasificación a un método/lambda auxiliar para que groupingBy
        //    quede legible.
        // PISTA: return entidades.stream().map(Ej064ManualMapper::toDto)
        //          .collect(Collectors.groupingBy(d -> {
        //              double p = d.precioConIva();
        //              if (p < 50.0) return "BARATO";
        //              if (p < 100.0) return "MEDIO";
        //              return "CARO";
        //          }));
        // OJO: los límites son [.. ,50) BARATO, [50,100) MEDIO, [100, ..) CARO. El test
        //      mete 24.2, 72.6 y 121.0 y espera UN elemento en cada grupo, con "A"
        //      como único BARATO. Necesitas importar java.util.stream.Collectors.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * RETO EXTRA 05: Redondeo matemático exacto de importes a dos cifras decimales
     * usando precisión financiera (RoundingMode.HALF_UP).
     *
     * @param precio importe a redondear
     * @return importe redondeado a 2 decimales
     */
    public static double pasoExtra05(double precio) {
        // GUÍA: error común nº4 del bloque -> dinero con BigDecimal, no double a pelo.
        // 1. Si precio < 0 -> IllegalArgumentException.
        // 2. Crea un BigDecimal desde el double, escálalo a 2 decimales con HALF_UP
        //    y vuelve a double.
        // PISTA: return java.math.BigDecimal.valueOf(precio)
        //          .setScale(2, java.math.RoundingMode.HALF_UP)
        //          .doubleValue();
        // OJO: el test exige 10.335 -> 10.34 (HALF_UP redondea el 5 hacia arriba) y
        //      10.334 -> 10.33. Si usaras Math.round o double crudo, 10.335 podría
        //      caer a 10.33 por el error de coma flotante. Negativo -> IAE.
        // CULTURA: en facturación SIEMPRE BigDecimal; un céntimo perdido por producto
        //      por millón de ventas es una auditoría.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
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
        // GUÍA: teoría 7.2 (tabla de operaciones: skip + limit).
        // 1. Si entidades es null -> IllegalArgumentException.
        // 2. Si offset < 0 o limit <= 0 -> IllegalArgumentException.
        // 3. Mapea y aplica skip(offset).limit(limit) en ese orden.
        // PISTA: return entidades.stream().map(Ej064ManualMapper::toDto)
        //          .skip(offset).limit(limit).toList();
        // OJO: el test pide (input,0,2) -> [A,B] y (input,2,5) -> [C,D] (limit mayor
        //      que lo que queda devuelve solo lo disponible, sin error). offset -1 y
        //      limit 0 deben lanzar IAE. skip/limit reciben long, el int hace autocast.
        // CULTURA: esto es la paginación "offset/limit" que verás en Spring Data como
        //      Pageable (b15); aquí la haces a mano para entender qué hace por debajo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
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
        // GUÍA: predicado de negocio (¿se puede vender?).
        // 1. Si entity es null -> IllegalArgumentException.
        // 2. Si stock < 0 -> IllegalArgumentException (stock negativo es dato corrupto).
        // 3. Devuelve true solo si stock > 0 Y entity.precioSinIva() > 0.
        // PISTA: return stock > 0 && entity.precioSinIva() > 0;
        // OJO: el test: (precio 10, stock 5) -> true; (precio 10, stock 0) -> false
        //      (sin stock no se vende); (precio 0, stock 10) -> false (gratis no es
        //      viable). entity null y stock -1 -> IAE. Cuidado: stock 0 NO lanza, solo
        //      devuelve false; el que lanza es stock < 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * RETO EXTRA 08: Búsqueda del producto más económico del catálogo de entidades
     * retornando directamente su DTO mapeado.
     *
     * @param entidades lista de entidades
     * @return el DTO del producto con el menor precio con IVA, o null si la lista está vacía
     */
    public static ProductoDto pasoExtra08(List<ProductoEntity> entidades) {
        // GUÍA: teoría 7.2 (min devuelve Optional, conecta con bloque 1.2).
        // 1. Si entidades es null -> IllegalArgumentException.
        // 2. Si está vacía -> devuelve null.
        // 3. Mapea a DTO y quédate con el de menor precioConIva usando min + comparator.
        // PISTA: return entidades.stream().map(Ej064ManualMapper::toDto)
        //          .min(java.util.Comparator.comparingDouble(ProductoDto::precioConIva))
        //          .orElse(null);
        // OJO: el test: de [100,10,50] espera el DTO "Barato" con precioConIva 12.1
        //      (=10*1.21). Lista vacía -> null; null -> IAE. No confundas null (lista
        //      vacía, válido) con IAE (lista null, error).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
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
        // GUÍA: teoría 7.2 (toDto + transformación final del precio).
        // 1. Si entidades es null -> IllegalArgumentException.
        // 2. Si tasaCambio < 0 -> IllegalArgumentException.
        // 3. Mapea a DTO y crea un DTO nuevo con precioConIva * tasaCambio.
        // PISTA: return entidades.stream().map(Ej064ManualMapper::toDto)
        //          .map(d -> new ProductoDto(d.id(), d.nombre(), d.precioConIva() * tasaCambio))
        //          .toList();
        // OJO: el test: precio 100 -> conIva 121.0; con tasa 1.10 espera 133.1 EXACTO.
        //      entidades null y tasa -1.0 -> IAE. Aplica la tasa SOBRE el precio con IVA,
        //      no sobre el precio sin IVA.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * RETO EXTRA 10: Resolución del símbolo y divisa local según el código de configuración de localización.
     * Ejemplos: "es-ES" -> "EUR", "en-US" -> "USD", "en-GB" -> "GBP".
     *
     * @param localeStr cadena con código regional (ej: "es-ES" o "en-US")
     * @return el código ISO de 3 letras de la moneda (ej: "EUR")
     */
    public static String pasoExtra10(String localeStr) {
        // GUÍA: mapeo clave->valor (tabla de localización a moneda ISO).
        // 1. Si localeStr es null o isEmpty() -> IllegalArgumentException.
        // 2. Traduce el código regional a moneda: "es-ES"->"EUR", "en-US"->"USD",
        //    "en-GB"->"GBP". Un switch sobre localeStr es lo más limpio.
        // PISTA: return switch (localeStr) {
        //            case "es-ES" -> "EUR";
        //            case "en-US" -> "USD";
        //            case "en-GB" -> "GBP";
        //            default -> "EUR";   // o el default que decidas
        //        };
        // OJO: el test comprueba esos tres mapeos exactos; null y "" (cadena vacía)
        //      deben lanzar IAE. No confundas isEmpty() (longitud 0) con isBlank()
        //      (solo espacios): aquí "" es el caso a cubrir.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
