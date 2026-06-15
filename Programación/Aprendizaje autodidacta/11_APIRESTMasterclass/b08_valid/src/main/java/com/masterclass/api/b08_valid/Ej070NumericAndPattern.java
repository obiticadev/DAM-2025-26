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
        // GUÍA: teoría 8.1 — válido = sin violaciones. Reutiliza camposInvalidos.
        // PISTA: return camposInvalidos(dto).isEmpty();
        // OJO: el test manda un ProductoDto válido y espera assertFalse →
        //      placeholder; tu implementación correcta dará true para ese caso.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esProductoValido");
    }

    /**
     * RETO EXTRA 2: Comprobar si un SKU es válido usando la expresión regular.
     *
     * @param sku código sku
     * @return true si cumple con el patrón ABC-1234
     */
    public static boolean skuValido(String sku) {
        // GUÍA: una línea con la misma regex que la constraint @Pattern (teoría 8.2).
        // 1. null → false.
        // 2. Reutiliza el patrón "[A-Z]{3}-\\d{4}" del campo sku.
        // PISTA: return sku != null && sku.matches("[A-Z]{3}-\\d{4}");
        // OJO: el test manda "ABC-1234" (válido) y espera assertFalse → placeholder.
        //      "abc-1234" o "AB-1234" deben dar false: matches ancla a toda la cadena.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para skuValido");
    }

    /**
     * RETO EXTRA 3: Comprobar si el precio tiene el formato numérico correcto (6 enteros y 2 decimales).
     *
     * @param precio precio decimal
     * @return true si el precio está dentro del formato monetario
     */
    public static boolean formatoPrecioValido(java.math.BigDecimal precio) {
        // GUÍA: equivale a @Digits(integer=6, fraction=2) (teoría 8.2) hecho a mano.
        // 1. null → false.
        // 2. precision() - scale() = nº de dígitos enteros (debe ser <= 6).
        //    scale() = nº de decimales (debe ser <= 2). Cuidado con scale negativo.
        // PISTA: int enteros = precio.precision() - precio.scale();
        //        return enteros <= 6 && precio.scale() <= 2;
        // OJO: el test manda BigDecimal.TEN (10, válido) y espera assertFalse →
        //      placeholder. CUIDADO: no uses double; precision()/scale() son de BigDecimal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatoPrecioValido");
    }

    /**
     * RETO EXTRA 4: Determinar si hay existencias suficientes.
     *
     * @param stock cantidad en stock
     * @return true si es mayor que cero
     */
    public static boolean stockDisponible(int stock) {
        // GUÍA: una línea — "mayor que cero" (el Javadoc lo dice literalmente).
        // PISTA: return stock > 0;
        // OJO: el test manda 10 y espera assertFalse → placeholder; la espec real
        //      da true para 10. Es > 0 estricto: stock 0 = sin existencias.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stockDisponible");
    }

    /**
     * RETO EXTRA 5: Comprobar si el descuento está entre los límites válidos (0 y 100).
     *
     * @param descuento porcentaje de descuento
     * @return true si está en el rango permitido
     */
    public static boolean descuentoPermitido(int descuento) {
        // GUÍA: una línea — rango cerrado [0, 100] (como @Min(0) + @Max(100), 8.1).
        // PISTA: return descuento >= 0 && descuento <= 100;
        // OJO: el test manda 50 (en rango) y espera assertFalse → placeholder.
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
        // GUÍA: aritmética con BigDecimal (teoría 8.2: dinero NUNCA con double).
        // 1. Valida dto/precio/iva no nulos (lanza o devuelve null según prefieras).
        // 2. precioFinal = precio * (1 + iva). Con BigDecimal:
        //    precio.multiply(BigDecimal.ONE.add(iva)).
        // 3. Redondea a 2 decimales: .setScale(2, RoundingMode.HALF_UP).
        // PISTA: return dto.precio.multiply(BigDecimal.ONE.add(iva))
        //            .setScale(2, java.math.RoundingMode.HALF_UP);
        // OJO: el test (precio 10.00, iva 0.21) usa assertNull → placeholder; la
        //      espec real devuelve 12.10. CUIDADO: BigDecimal es inmutable, captura
        //      el retorno de multiply/setScale (no muta el original).
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
        // GUÍA: el prefijo es la parte anterior al guion. Reutiliza skuValido (reto 2).
        // 1. null en cualquiera de los dos → false.
        // 2. Opción simple: comprueba sku.startsWith(categoria + "-").
        //    Opción robusta: sku.split("-", 2)[0].equals(categoria).
        // PISTA: return sku != null && categoria != null && sku.startsWith(categoria + "-");
        // OJO: el test ("ABC-1234","ABC") espera assertFalse → placeholder; la espec
        //      real da true (el prefijo "ABC" coincide).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSkuDeCategoria");
    }

    /**
     * RETO EXTRA 8: Calcular el precio final tras aplicar el descuento del producto.
     *
     * @param dto producto
     * @return precio neto con el descuento aplicado
     */
    public static java.math.BigDecimal precioConDescuento(ProductoDto dto) {
        // GUÍA: BigDecimal de nuevo (teoría 8.2). precioNeto = precio * (1 - desc/100).
        // 1. Convierte el descuento (int) a fracción: new BigDecimal(dto.descuento)
        //    dividido por 100, o BigDecimal.valueOf(dto.descuento).movePointLeft(2).
        // 2. factor = 1 - fracción; resultado = precio.multiply(factor).
        // 3. Redondea a 2 decimales con setScale(2, HALF_UP).
        // PISTA: var frac = BigDecimal.valueOf(dto.descuento).movePointLeft(2);
        //        return dto.precio.multiply(BigDecimal.ONE.subtract(frac))
        //            .setScale(2, java.math.RoundingMode.HALF_UP);
        // OJO: el test (precio 10.00, descuento 50) usa assertNull → placeholder;
        //      la espec real devuelve 5.00.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para precioConDescuento");
    }

    /**
     * RETO EXTRA 9: Generar un SKU genérico a partir de un prefijo de categoría de 3 letras.
     *
     * @param prefijo prefijo de categoría
     * @return SKU en formato "PRE-9999" (rellenado con 9s)
     */
    public static String generarSkuDefecto(String prefijo) {
        // GUÍA: construir un SKU que cumpla "[A-Z]{3}-\\d{4}" (formato "PRE-9999").
        // 1. Normaliza el prefijo a 3 mayúsculas (toUpperCase). Asume 3 letras.
        // 2. Concatena "-9999".
        // PISTA: return prefijo.toUpperCase() + "-9999";
        // COMPRUEBA: skuValido(generarSkuDefecto("abc")) debería dar true.
        // OJO: el test ("ABC") espera "" → placeholder; la espec real da "ABC-9999".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarSkuDefecto");
    }

    /**
     * RETO EXTRA 10: Validar y redondear el precio del producto a 2 decimales para evitar desbordamiento.
     *
     * @param dto producto
     * @return copia de ProductoDto con el precio redondeado de forma segura
     */
    public static ProductoDto validarYRedondearPrecio(ProductoDto dto) {
        // GUÍA: "wither" inmutable (teoría 1.1) — copia el dto con el precio redondeado.
        // 1. Si dto o dto.precio son null → decide (null o lanzar; el test no lo cubre).
        // 2. Redondea precio a 2 decimales: dto.precio.setScale(2, HALF_UP).
        // 3. Devuelve un NUEVO ProductoDto con sku/stock/descuento iguales y el precio
        //    redondeado.
        // PISTA: var p = dto.precio.setScale(2, java.math.RoundingMode.HALF_UP);
        //        return new ProductoDto(dto.sku, p, dto.stock, dto.descuento);
        // OJO: el test (precio 10.005) usa assertNull → placeholder; la espec real
        //      devuelve un dto con precio 10.01 (HALF_UP redondea hacia arriba).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarYRedondearPrecio");
    }

}
