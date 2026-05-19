package com.masterclass.api.b02_json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * Ejercicio 026 · Objetos anidados y colecciones en JSON.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.2).
 */
public final class Ej026NestedAndCollections {

    public record Linea(String producto, int cantidad) {
    }

    public record Pedido(Long id, List<Linea> lineas) {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Ej026NestedAndCollections() {
    }

    /**
     * Serializa un pedido con sus líneas anidadas.
     *
     * @param p pedido
     * @return JSON con array "lineas"
     */
    public static String toJson(Pedido p) {
        // TODO 1: abre try alrededor de la serialización.
        // TODO 2: usa MAPPER.writeValueAsString(p).
        // TODO 3: el array "lineas" debe anidarse automáticamente (Jackson recorre el record).
        // TODO 4: devuelve la cadena.
        // TODO 5: captura JsonProcessingException y relánzala como RuntimeException.
        return "";
    }

    /**
     * Deserializa una lista de líneas desde un array JSON.
     *
     * @param jsonArray texto como {@code [{"producto":"x","cantidad":2}]}
     * @return lista de Linea
     */
    public static List<Linea> lineasFromJson(String jsonArray) {
        // TODO 6: para colecciones genéricas necesitas un TypeReference (borrado de tipos).
        // TODO 7: crea new TypeReference<List<Linea>>(){}.
        // TODO 8: usa MAPPER.readValue(jsonArray, eseTypeReference).
        // TODO 9: devuelve la lista resultante.
        // TODO 10: captura JsonProcessingException y relánzala como RuntimeException.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(toJson(new Pedido(1L, List.of(new Linea("cafe", 2)))));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: abre try alrededor de la serialización.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: usa MAPPER.writeValueAsString(p).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: el array "lineas" debe anidarse automáticamente (Jackson recorre el record).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: devuelve la cadena.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: captura JsonProcessingException y relánzala como RuntimeException.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: para colecciones genéricas necesitas un TypeReference (borrado de tipos).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: crea new TypeReference<List<Linea>>(){}.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: usa MAPPER.readValue(jsonArray, eseTypeReference).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: devuelve la lista resultante.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: captura JsonProcessingException y relánzala como RuntimeException.
    }

}
