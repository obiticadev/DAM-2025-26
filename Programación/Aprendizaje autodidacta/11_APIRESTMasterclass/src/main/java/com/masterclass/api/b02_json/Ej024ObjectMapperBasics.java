package com.masterclass.api.b02_json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Ejercicio 024 · ObjectMapper: serializar y deserializar.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.2).
 */
public final class Ej024ObjectMapperBasics {

    /** DTO de ejemplo (los componentes son el contrato). */
    public record Cliente(Long id, String nombre) {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Ej024ObjectMapperBasics() {
    }

    /**
     * Serializa un Cliente a JSON.
     *
     * @param c cliente a convertir
     * @return cadena JSON, p.ej. {@code {"id":1,"nombre":"Ana"}}
     * @throws RuntimeException si Jackson falla (envuelve la checked)
     */
    public static String toJson(Cliente c) {
        // TODO 1: abre un try alrededor de la serialización.
        // TODO 2: usa MAPPER.writeValueAsString(c).
        // TODO 3: devuelve la cadena resultante.
        // TODO 4: captura JsonProcessingException.
        // TODO 5: relánzala envuelta en una RuntimeException (no la silencies).
        return "";
    }

    /**
     * Deserializa JSON a un Cliente.
     *
     * @param json texto JSON con id y nombre
     * @return instancia de Cliente
     * @throws RuntimeException si el JSON es inválido
     */
    public static Cliente fromJson(String json) {
        // TODO 6: abre un try alrededor de la deserialización.
        // TODO 7: usa MAPPER.readValue(json, Cliente.class).
        // TODO 8: devuelve el objeto poblado.
        // TODO 9: captura JsonProcessingException.
        // TODO 10: relánzala como RuntimeException con el mensaje original.
        return null;
    }

    public static void main(String[] args) {
        String j = toJson(new Cliente(1L, "Ana"));
        System.out.println(j + " -> " + fromJson(j));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: abre un try alrededor de la serialización.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: usa MAPPER.writeValueAsString(c).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: devuelve la cadena resultante.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: captura JsonProcessingException.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: relánzala envuelta en una RuntimeException (no la silencies).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: abre un try alrededor de la deserialización.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: usa MAPPER.readValue(json, Cliente.class).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: devuelve el objeto poblado.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: captura JsonProcessingException.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: relánzala como RuntimeException con el mensaje original.
    }

}
