package com.masterclass.api.b08_valid;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ejercicio 073 · Constraint personalizada (@Slug).
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.2).
 *
 * <p>Un "slug" válido = solo minúsculas, dígitos y guiones, sin guiones al inicio/fin.
 */
public final class Ej073CustomConstraint {

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = SlugValidator.class)
    public @interface Slug {
        String message() default "no es un slug válido";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    /** Implementación de la validación del slug. */
    public static class SlugValidator implements ConstraintValidator<Slug, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext ctx) {
            // TODO 1: si value es null, considéralo válido (deja @NotNull aparte) -> true.
            // TODO 2: si está vacío -> false.
            // TODO 3: no debe empezar por '-'.
            // TODO 4: no debe terminar por '-'.
            // TODO 5: solo se permiten [a-z0-9-] (usa una regex o recorre los chars).
            // TODO 6: cualquier mayúscula o carácter raro -> false.
            // TODO 7: devuelve true solo si pasa todas las comprobaciones.
            return false;
        }
    }

    public static class ArticuloDto {
        // TODO 8: anota 'slug' con @Slug (tu constraint) y con @NotBlank.
        public String slug;

        public ArticuloDto(String slug) {
            this.slug = slug;
        }
    }

    private static final Validator VALIDATOR =
            Validation.buildDefaultValidatorFactory().getValidator();

    private Ej073CustomConstraint() {
    }

    /**
     * @param dto artículo
     * @return true si el slug es válido (sin violaciones)
     */
    public static boolean esValido(ArticuloDto dto) {
        // TODO 9: usa VALIDATOR.validate(dto) (infraestructura).
        // TODO 10: válido = el conjunto de violaciones está vacío.
        return VALIDATOR.validate(dto).isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(esValido(new ArticuloDto("mi-articulo-1")));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si value es null, considéralo válido (deja @NotNull aparte) -> true.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si está vacío -> false.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: no debe empezar por '-'.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: no debe terminar por '-'.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: solo se permiten [a-z0-9-] (usa una regex o recorre los chars).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: cualquier mayúscula o carácter raro -> false.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: devuelve true solo si pasa todas las comprobaciones.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: anota 'slug' con @Slug (tu constraint) y con @NotBlank.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: usa VALIDATOR.validate(dto) (infraestructura).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: válido = el conjunto de violaciones está vacío.
    }

}
