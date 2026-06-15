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
        // GUÍA: la misma lógica que SlugValidator.isValid (teoría 8.5), pero booleana.
        // 1. null o vacío → false (aquí es un test sintáctico, no la convención del
        //    validador). Sin guion al inicio ni al final.
        // 2. Solo [a-z0-9-]. Lo más limpio: una regex completa.
        // PISTA: return slug != null && slug.matches("[a-z0-9]+(-[a-z0-9]+)*");
        //    (esa regex ya prohíbe guion inicial/final y dobles guiones).
        // OJO: el test manda "mi-slug" (válido) y espera assertFalse → placeholder.
        // CULTURA: reutiliza esta misma comprobación dentro de SlugValidator.isValid.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSlugSintacticamenteValido");
    }

    /**
     * RETO EXTRA 2: Comprobar si una cadena empieza por el carácter guión '-'.
     *
     * @param s cadena a evaluar
     * @return true si empieza por '-'
     */
    public static boolean empiezaPorGuion(String s) {
        // GUÍA: una línea — protege null y usa startsWith.
        // PISTA: return s != null && s.startsWith("-");
        // OJO: el test manda "-algo" (sí empieza por guion) y espera assertFalse →
        //      placeholder; la espec real da true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para empiezaPorGuion");
    }

    /**
     * RETO EXTRA 3: Comprobar si una cadena termina por el carácter guión '-'.
     *
     * @param s cadena a evaluar
     * @return true si termina por '-'
     */
    public static boolean terminaPorGuion(String s) {
        // GUÍA: gemelo del reto 2 — usa endsWith.
        // PISTA: return s != null && s.endsWith("-");
        // OJO: el test manda "algo-" y espera assertFalse → placeholder; la espec
        //      real da true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para terminaPorGuion");
    }

    /**
     * RETO EXTRA 4: Comprobar si una cadena contiene únicamente minúsculas, números y guiones.
     *
     * @param s cadena a evaluar
     * @return true si cumple con el conjunto permitido de caracteres
     */
    public static boolean contieneSoloMinusculasNumerosYGuiones(String s) {
        // GUÍA: solo el conjunto de caracteres [a-z0-9-], sin reglas de posición.
        // PISTA: return s != null && s.matches("[a-z0-9-]+");
        //    (aquí SÍ permitimos guion en cualquier posición, a diferencia del reto 1).
        // OJO: el test manda "abc-123" (solo válidos) y espera assertFalse →
        //      placeholder; la espec real da true. "ABC" o "a_b" deben dar false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneSoloMinusculasNumerosYGuiones");
    }

    /**
     * RETO EXTRA 5: Normalizar una cadena cualquiera para convertirla en un slug válido.
     *
     * @param s cadena de texto
     * @return slug normalizado (minúsculas, sin espacios, con guiones)
     */
    public static String normalizarStringASlug(String s) {
        // GUÍA: convierte texto libre en slug (es lo que hace un CMS con un título).
        // 1. null → "". Pasa a minúsculas (toLowerCase).
        // 2. Sustituye todo lo que no sea [a-z0-9] por "-": replaceAll("[^a-z0-9]+","-").
        // 3. Quita guiones sobrantes en los extremos: replaceAll("^-|-$","").
        // PISTA: return s == null ? "" : s.toLowerCase()
        //            .replaceAll("[^a-z0-9]+","-").replaceAll("^-+|-+$","");
        // OJO: el test manda "Hola Mundo" y espera "" → placeholder; la espec real
        //      produce "hola-mundo". COMPRUEBA: esSlugSintacticamenteValido del resultado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarStringASlug");
    }

    /**
     * RETO EXTRA 6: Instanciar programáticamente y probar el validador SlugValidator.
     *
     * @return true si se puede crear la instancia
     */
    public static boolean esValidatorActivo() {
        // GUÍA: crea una instancia del validador y compruébala con un caso conocido.
        // 1. new SlugValidator(); luego invoca isValid("slug-ok", null) (el contexto
        //    puede ser null si tu isValid no lo usa).
        // PISTA: return new SlugValidator().isValid("slug-ok", null);
        // OJO: el test espera assertFalse → placeholder; con un isValid correcto
        //      "slug-ok" daría true. CUIDADO: si el ejercicio base aún tiene isValid
        //      devolviendo siempre false, este método dará false por ESE bug, no por
        //      tu código — arregla primero los TODO 1..7 de SlugValidator.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValidatorActivo");
    }

    /**
     * RETO EXTRA 7: Generar un slug a partir de un título de artículo aplicando reglas estándar.
     *
     * @param titulo título del artículo
     * @return slug generado
     */
    public static String generarSlugArticulo(String titulo) {
        // GUÍA: es el mismo trabajo que normalizarStringASlug (reto 5). Reutilízalo.
        // PISTA: return normalizarStringASlug(titulo);
        // OJO: el test manda "Nuevo Post!" y espera "" → placeholder; la espec real
        //      produce "nuevo-post" (el '!' se descarta, el espacio pasa a guion).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarSlugArticulo");
    }

    /**
     * RETO EXTRA 8: Comprobar si un valor null se trata como válido dentro de las especificaciones del validador de slug.
     *
     * @param value valor de entrada
     * @return true si el validador considera el null como correcto
     */
    public static boolean esNullConsideradoValido(String value) {
        // GUÍA: pregunta directa por la convención de 8.5 — un ConstraintValidator
        //       trata null como VÁLIDO (la obligatoriedad la pone @NotBlank).
        // 1. Invoca el validador con ese valor: new SlugValidator().isValid(value, null).
        // PISTA: return new SlugValidator().isValid(value, null);
        // OJO: el test pasa null y espera assertFalse → placeholder; pero la espec
        //      (y el TODO 1 de SlugValidator) dicen que null → true. Esa es justo la
        //      lección: null es válido, lo prohíbe otra anotación.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNullConsideradoValido");
    }

    /**
     * RETO EXTRA 9: Contar el número de guiones dentro del slug.
     *
     * @param s slug
     * @return número de guiones encontrados
     */
    public static int contarGuiones(String s) {
        // GUÍA: cuenta apariciones de '-'. null → 0.
        // 1. Lo más limpio con streams: s.chars().filter(c -> c == '-').count() (cast a int).
        //    Alternativa: s.length() - s.replace("-", "").length().
        // PISTA: return s == null ? 0 : (int) s.chars().filter(c -> c == '-').count();
        // OJO: el test manda "a-b-c" y espera 0 → placeholder; la espec real cuenta 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarGuiones");
    }

    /**
     * RETO EXTRA 10: Comprobar si el ArticuloDto es válido invocando directamente el flujo completo de validación.
     *
     * @param dto artículo DTO
     * @return true si es completamente válido
     */
    public static boolean esSlugPerfecto(ArticuloDto dto) {
        // GUÍA: reutiliza esValido(dto) (ya implementado): pasa por el motor completo
        //       (@Slug + @NotBlank) en vez de comprobar a mano.
        // PISTA: return esValido(dto);
        // OJO: el test manda slug "slug" (válido) y espera assertFalse → placeholder;
        //      con SlugValidator y las anotaciones correctas daría true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSlugPerfecto");
    }

}
