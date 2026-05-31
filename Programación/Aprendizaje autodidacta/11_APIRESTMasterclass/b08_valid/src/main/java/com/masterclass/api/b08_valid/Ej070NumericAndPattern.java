package com.masterclass.api.b08_valid;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Ejercicio 070 · Constraints numéricas y de patrón.
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.2).
 */
public final class Ej070NumericAndPattern {

    /** DTO de producto. Añade las constraints pedidas. */
    public static class ProductoDto {
        // TODO 1: 'sku' con @NotBlank.
        // TODO 2: 'sku' con @Pattern(regexp = "[A-Z]{3}-\\d{4}") (p.ej. ABC-1234).
        public String sku;

        // TODO 3: 'precio' con @NotNull.
        // TODO 4: 'precio' con @DecimalMin(value = "0.01") (positivo).
        // TODO 5: 'precio' con @Digits(integer = 6, fraction = 2) (formato monetario).
        public java.math.BigDecimal precio;

        // TODO 6: 'stock' con @Min(0) (no negativo).
        // TODO 7: 'stock' con @Max(100000) (límite de almacén).
        public int stock;

        // TODO 8: 'descuento' con @Min(0) y @Max(100) (porcentaje 0-100).
        public int descuento;

        public ProductoDto(String sku, java.math.BigDecimal precio, int stock, int descuento) {
            this.sku = sku;
            this.precio = precio;
            this.stock = stock;
            this.descuento = descuento;
        }
    }

    private static final Validator VALIDATOR =
            Validation.buildDefaultValidatorFactory().getValidator();

    private Ej070NumericAndPattern() {
    }

    /**
     * @param dto producto a validar
     * @return campos que incumplen alguna constraint
     */
    public static Set<String> camposInvalidos(ProductoDto dto) {
        // TODO 9: método infraestructura ya implementado.
        // TODO 10: lograr que las anotaciones produzcan exactamente los inválidos esperados.
        return VALIDATOR.validate(dto).stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        System.out.println(camposInvalidos(
                new ProductoDto("bad", java.math.BigDecimal.ZERO, -1, 200)));
    }

    /**
     * RETO EXTRA 1: Comprobar si el ProductoDto es completamente válido.
     *
     * @param dto producto
     * @return true si cumple con todas las constraints
     */
    public static boolean esProductoValido(ProductoDto dto) {
        // TODO extra: RETO EXTRA 1: Comprobar si el ProductoDto es completamente válido.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esProductoValido");
    }

    /**
     * RETO EXTRA 2: Comprobar si un SKU es válido usando la expresión regular.
     *
     * @param sku código sku
     * @return true si cumple con el patrón ABC-1234
     */
    public static boolean skuValido(String sku) {
        // TODO extra: RETO EXTRA 2: Comprobar si un SKU es válido usando la expresión regular.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para skuValido");
    }

    /**
     * RETO EXTRA 3: Comprobar si el precio tiene el formato numérico correcto (6 enteros y 2 decimales).
     *
     * @param precio precio decimal
     * @return true si el precio está dentro del formato monetario
     */
    public static boolean formatoPrecioValido(java.math.BigDecimal precio) {
        // TODO extra: RETO EXTRA 3: Comprobar si el precio tiene el formato numérico correcto (6 enteros y 2 decimales).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatoPrecioValido");
    }

    /**
     * RETO EXTRA 4: Determinar si hay existencias suficientes.
     *
     * @param stock cantidad en stock
     * @return true si es mayor que cero
     */
    public static boolean stockDisponible(int stock) {
        // TODO extra: RETO EXTRA 4: Determinar si hay existencias suficientes.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stockDisponible");
    }

    /**
     * RETO EXTRA 5: Comprobar si el descuento está entre los límites válidos (0 y 100).
     *
     * @param descuento porcentaje de descuento
     * @return true si está en el rango permitido
     */
    public static boolean descuentoPermitido(int descuento) {
        // TODO extra: RETO EXTRA 5: Comprobar si el descuento está entre los límites válidos (0 y 100).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para descuentoPermitido");
    }

    /**
     * RETO EXTRA 6: Calcular precio final aplicando el IVA indicado.
     *
     * @param dto producto
     * @param iva porcentaje de IVA (ej: 0.21 para 21%)
     * @return precio final con IVA
     */
    public static java.math.BigDecimal precioConIva(ProductoDto dto, java.math.BigDecimal iva) {
        // TODO extra: RETO EXTRA 6: Calcular precio final aplicando el IVA indicado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para precioConIva");
    }

    /**
     * RETO EXTRA 7: Comprobar si el SKU pertenece a una categoría según su prefijo de 3 letras.
     *
     * @param sku código sku
     * @param categoria prefijo de 3 letras esperado
     * @return true si coincide el prefijo
     */
    public static boolean esSkuDeCategoria(String sku, String categoria) {
        // TODO extra: RETO EXTRA 7: Comprobar si el SKU pertenece a una categoría según su prefijo de 3 letras.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSkuDeCategoria");
    }

    /**
     * RETO EXTRA 8: Calcular el precio final tras aplicar el descuento del producto.
     *
     * @param dto producto
     * @return precio neto con el descuento aplicado
     */
    public static java.math.BigDecimal precioConDescuento(ProductoDto dto) {
        // TODO extra: RETO EXTRA 8: Calcular el precio final tras aplicar el descuento del producto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para precioConDescuento");
    }

    /**
     * RETO EXTRA 9: Generar un SKU genérico a partir de un prefijo de categoría de 3 letras.
     *
     * @param prefijo prefijo de categoría
     * @return SKU en formato "PRE-9999" (rellenado con 9s)
     */
    public static String generarSkuDefecto(String prefijo) {
        // TODO extra: RETO EXTRA 9: Generar un SKU genérico a partir de un prefijo de categoría de 3 letras.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarSkuDefecto");
    }

    /**
     * RETO EXTRA 10: Validar y redondear el precio del producto a 2 decimales para evitar desbordamiento.
     *
     * @param dto producto
     * @return copia de ProductoDto con el precio redondeado de forma segura
     */
    public static ProductoDto validarYRedondearPrecio(ProductoDto dto) {
        // TODO extra: RETO EXTRA 10: Validar y redondear el precio del producto a 2 decimales para evitar desbordamiento.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarYRedondearPrecio");
    }

}
