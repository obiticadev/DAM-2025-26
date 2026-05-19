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
}

/** Anotacion simulada (@Operation/@Schema combinadas). */
record AnnotationMeta178(String summary, String description, String javaType, boolean required) {
}
