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

    /**
     * RETO EXTRA 1: Comprobar manualmente si un slug cumple con el formato exacto de slug.
     *
     * @param slug cadena a evaluar
     * @return true si es correcto
     */
    public static boolean esSlugSintacticamenteValido(String slug) {
        // TODO extra: RETO EXTRA 1: Comprobar manualmente si un slug cumple con el formato exacto de slug.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSlugSintacticamenteValido");
    }

    /**
     * RETO EXTRA 2: Comprobar si una cadena empieza por el carácter guión '-'.
     *
     * @param s cadena a evaluar
     * @return true si empieza por '-'
     */
    public static boolean empiezaPorGuion(String s) {
        // TODO extra: RETO EXTRA 2: Comprobar si una cadena empieza por el carácter guión '-'.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para empiezaPorGuion");
    }

    /**
     * RETO EXTRA 3: Comprobar si una cadena termina por el carácter guión '-'.
     *
     * @param s cadena a evaluar
     * @return true si termina por '-'
     */
    public static boolean terminaPorGuion(String s) {
        // TODO extra: RETO EXTRA 3: Comprobar si una cadena termina por el carácter guión '-'.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para terminaPorGuion");
    }

    /**
     * RETO EXTRA 4: Comprobar si una cadena contiene únicamente minúsculas, números y guiones.
     *
     * @param s cadena a evaluar
     * @return true si cumple con el conjunto permitido de caracteres
     */
    public static boolean contieneSoloMinusculasNumerosYGuiones(String s) {
        // TODO extra: RETO EXTRA 4: Comprobar si una cadena contiene únicamente minúsculas, números y guiones.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneSoloMinusculasNumerosYGuiones");
    }

    /**
     * RETO EXTRA 5: Normalizar una cadena cualquiera para convertirla en un slug válido.
     *
     * @param s cadena de texto
     * @return slug normalizado (minúsculas, sin espacios, con guiones)
     */
    public static String normalizarStringASlug(String s) {
        // TODO extra: RETO EXTRA 5: Normalizar una cadena cualquiera para convertirla en un slug válido.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarStringASlug");
    }

    /**
     * RETO EXTRA 6: Instanciar programáticamente y probar el validador SlugValidator.
     *
     * @return true si se puede crear la instancia
     */
    public static boolean esValidatorActivo() {
        // TODO extra: RETO EXTRA 6: Instanciar programáticamente y probar el validador SlugValidator.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValidatorActivo");
    }

    /**
     * RETO EXTRA 7: Generar un slug a partir de un título de artículo aplicando reglas estándar.
     *
     * @param titulo título del artículo
     * @return slug generado
     */
    public static String generarSlugArticulo(String titulo) {
        // TODO extra: RETO EXTRA 7: Generar un slug a partir de un título de artículo aplicando reglas estándar.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarSlugArticulo");
    }

    /**
     * RETO EXTRA 8: Comprobar si un valor null se trata como válido dentro de las especificaciones del validador de slug.
     *
     * @param value valor de entrada
     * @return true si el validador considera el null como correcto
     */
    public static boolean esNullConsideradoValido(String value) {
        // TODO extra: RETO EXTRA 8: Comprobar si un valor null se trata como válido dentro de las especificaciones del validador de slug.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNullConsideradoValido");
    }

    /**
     * RETO EXTRA 9: Contar el número de guiones dentro del slug.
     *
     * @param s slug
     * @return número de guiones encontrados
     */
    public static int contarGuiones(String s) {
        // TODO extra: RETO EXTRA 9: Contar el número de guiones dentro del slug.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarGuiones");
    }

    /**
     * RETO EXTRA 10: Comprobar si el ArticuloDto es válido invocando directamente el flujo completo de validación.
     *
     * @param dto artículo DTO
     * @return true si es completamente válido
     */
    public static boolean esSlugPerfecto(ArticuloDto dto) {
        // TODO extra: RETO EXTRA 10: Comprobar si el ArticuloDto es válido invocando directamente el flujo completo de validación.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSlugPerfecto");
    }

}
