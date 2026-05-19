package com.masterclass.api.b20_obs;

import java.util.Map;

/**
 * Ejercicio 181 · Logging estructurado con MDC.
 *
 * <p>Teoria: {@code teoria/20_Observabilidad_Documentacion.md} (seccion 20.5).
 *
 * <p>El {@code MDC} (Mapped Diagnostic Context) de SLF4J adjunta campos
 * (userId, traceId...) a cada log. Aqui formateamos una linea de log
 * estructurada en JSON usando Jackson (ya en el pom) a partir del nivel, el
 * mensaje y el mapa de campos MDC.
 */
public final class Ej181StructuredLoggingMdc {

    private Ej181StructuredLoggingMdc() {
    }

    /**
     * Formatea una linea de log estructurado como JSON.
     *
     * @param nivel  nivel de log (INFO, WARN, ERROR...); no en blanco
     * @param mensaje mensaje del log; no null
     * @param mdc    campos MDC a incluir; no null (puede estar vacio)
     * @return cadena JSON con claves "level", "message" y las del MDC
     * @throws IllegalArgumentException si nivel en blanco, mensaje o mdc null
     */
    public static String formatear(String nivel, String mensaje, Map<String, String> mdc) {
        // TODO 1: si nivel es null o en blanco -> IllegalArgumentException.
        // TODO 2: si mensaje es null -> IllegalArgumentException.
        // TODO 3: si mdc es null -> IllegalArgumentException.
        // TODO 4: crea un LinkedHashMap para preservar orden de las claves.
        // TODO 5: pon "level" con el nivel normalizado a mayusculas (trim).
        // TODO 6: pon "message" con el mensaje tal cual.
        // TODO 7: copia todos los pares del mdc al mapa (no sobrescribas level/message).
        // TODO 8: crea un com.fasterxml.jackson.databind.ObjectMapper.
        // TODO 9: serializa el mapa a JSON (writeValueAsString); envuelve la
        //         JsonProcessingException en IllegalStateException.
        // TODO 10: devuelve la cadena JSON resultante.
        return "{}";
    }

    public static void main(String[] args) {
        System.out.println(formatear("INFO", "ok", Map.of("traceId", "abc")));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si nivel es null o en blanco -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si mensaje es null -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si mdc es null -> IllegalArgumentException.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: crea un LinkedHashMap para preservar orden de las claves.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: pon "level" con el nivel normalizado a mayusculas (trim).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: pon "message" con el mensaje tal cual.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: copia todos los pares del mdc al mapa (no sobrescribas level/message).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: crea un com.fasterxml.jackson.databind.ObjectMapper.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: serializa el mapa a JSON (writeValueAsString); envuelve la
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la cadena JSON resultante.
    }

}
