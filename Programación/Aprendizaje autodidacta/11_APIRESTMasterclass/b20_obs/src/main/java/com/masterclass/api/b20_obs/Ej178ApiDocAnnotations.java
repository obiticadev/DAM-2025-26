package com.masterclass.api.b20_obs;

import java.util.Map;

/**
 * Ejercicio 178 · Anotaciones de documentacion API.
 *
 * <p>Teoria: {@code teoria/20_Observabilidad_Documentacion.md} (seccion 20.2).
 *
 * <p>Las anotaciones {@link io.swagger.v3.oas.annotations.Operation} y
 * {@link io.swagger.v3.oas.annotations.media.Schema} enriquecen el documento
 * OpenAPI. Aqui simulamos esas anotaciones con un record y resolvemos la
 * descripcion y el esquema efectivos aplicando las reglas de precedencia.
 */
public final class Ej178ApiDocAnnotations {

    private Ej178ApiDocAnnotations() {
    }

    /**
     * Resuelve descripcion y esquema efectivos a partir de anotaciones simuladas.
     *
     * @param anot anotacion simulada de la operacion/campo; no null
     * @return mapa con "description" y "schema" ya resueltos
     * @throws IllegalArgumentException si anot es null
     */
    public static Map<String, String> resolver(AnnotationMeta178 anot) {
        // TODO 1: si anot es null -> IllegalArgumentException.
        // TODO 2: calcula la descripcion: usa anot.summary() si no esta en blanco.
        // TODO 3: si summary esta en blanco, cae a anot.description().
        // TODO 4: si ambas estan en blanco, usa el literal "sin descripcion".
        // TODO 5: calcula el tipo de esquema base segun anot.javaType() (String->"string").
        // TODO 6: mapea Integer/Long/int/long a "integer"; Boolean/boolean a "boolean".
        // TODO 7: cualquier otro tipo no primitivo -> "object".
        // TODO 8: si anot.required() es true, anexa " (required)" al schema.
        // TODO 9: construye el Map de salida con claves "description" y "schema".
        // TODO 10: devuelve el mapa resuelto.
        return Map.of();
    }

    public static void main(String[] args) {
        System.out.println(resolver(
                new AnnotationMeta178("Crea usuario", "", "Long", true)));
    }

        /**
     * RETO EXTRA 01: Valida campo requerido.
     */
    public static boolean esRequerido(AnnotationMeta178 anot) {
        // GUÍA: una línea — accesor del campo boolean del record.
        // return anot.required();
        // (Defensa opcional: si anot es null -> IllegalArgumentException.)
        // El test manda required=true y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRequerido");
    }

    /**
     * RETO EXTRA 02: Obtiene tipo original.
     */
    public static String obtenerTipoJava(AnnotationMeta178 anot) {
        // GUÍA: una línea — accesor javaType() (el tipo Java SIN traducir a OpenAPI).
        // return anot.javaType();
        // OJO: el test manda javaType="String" y espera "String" tal cual; NO lo
        // conviertas a "string" (esa traducción es de resolver(), no de aquí).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTipoJava");
    }

    /**
     * RETO EXTRA 03: Obtiene summary.
     */
    public static String obtenerSummary(AnnotationMeta178 anot) {
        // GUÍA: una línea — accesor del record.
        // return anot.summary();
        // El test manda summary="a" y espera "a".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerSummary");
    }

    /**
     * RETO EXTRA 04: Obtiene descripcion.
     */
    public static String obtenerDescription(AnnotationMeta178 anot) {
        // GUÍA: una línea — accesor del record.
        // return anot.description();
        // OJO: aquí devuelves description() crudo, SIN la precedencia summary>description
        // (esa lógica vive en resolver()). El test manda description="b" y espera "b".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerDescription");
    }

    /**
     * RETO EXTRA 05: Verifica summary.
     */
    public static boolean tieneSummary(AnnotationMeta178 anot) {
        // GUÍA: "tiene summary" = summary presente y no en blanco (teoría 20.2:
        // usa isBlank, no == null, para cubrir " ").
        // PISTA: return anot != null && anot.summary() != null && !anot.summary().isBlank();
        // El test manda summary="a" (true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneSummary");
    }

    /**
     * RETO EXTRA 06: Verifica descripcion.
     */
    public static boolean tieneDescription(AnnotationMeta178 anot) {
        // GUÍA: espejo de tieneSummary pero sobre description(). Reutiliza el patrón.
        // PISTA: return anot != null && anot.description() != null && !anot.description().isBlank();
        // El test manda description="b" (true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneDescription");
    }

    /**
     * RETO EXTRA 07: Crea anotacion simulada.
     */
    public static AnnotationMeta178 crearAnotacion(String s, String d, String t, boolean r) {
        // GUÍA: una línea — factoría del record (orden: summary, description, javaType, required).
        // return new AnnotationMeta178(s, d, t, r);
        // El test solo comprueba assertNotNull.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearAnotacion");
    }

    /**
     * RETO EXTRA 08: Valida si es tipo Objeto.
     */
    public static boolean esObjetoType(String t) {
        // GUÍA: teoría 20.2 (tabla tipo Java -> tipo OpenAPI). "object" es el
        // cajón de sastre: todo lo que NO es string/integer/boolean.
        // 1. Si t es null -> false.
        // 2. Comprueba que NO esté en el conjunto de tipos primitivos/conocidos.
        // PISTA: Set.of("String","Integer","Long","int","long","Boolean","boolean")
        //        .contains(t) == false  → es object.
        //        return t != null && !conocidos.contains(t);
        // OJO: el test manda "User" (un tipo de dominio) y espera true.
        // Reutiliza la idea de esEnteroType (reto 9) para el conjunto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esObjetoType");
    }

    /**
     * RETO EXTRA 09: Valida si es tipo Entero.
     */
    public static boolean esEnteroType(String t) {
        // GUÍA: teoría 20.2 — los tipos Java que mapean a "integer".
        // PISTA: return Set.of("Integer","Long","int","long").contains(t);
        // OJO: el test manda "int" (minúscula, primitivo) y espera true; cubre
        // tanto wrappers (Integer/Long) como primitivos (int/long).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEnteroType");
    }

    /**
     * RETO EXTRA 10: Texto representativo.
     */
    public static String formatearRequerido(AnnotationMeta178 anot) {
        // GUÍA: texto según el flag required.
        // PISTA: return anot.required() ? "required" : "optional";
        // OJO: el test manda required=true y espera EXACTAMENTE "required"
        // (sin paréntesis ni espacios; el " (required)" con paréntesis es solo
        // para el schema en resolver(), no aquí).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearRequerido");
    }

}

/** Anotacion simulada (@Operation/@Schema combinadas). */
record AnnotationMeta178(String summary, String description, String javaType, boolean required) {
}
