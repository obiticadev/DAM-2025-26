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
}
