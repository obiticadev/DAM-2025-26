package com.masterclass.api.b19_test;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Ejercicio 169 · Aserciones sobre JSON (mini matcher con Jackson).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.6).
 *
 * <p>Comparar respuestas JSON por String es frágil (orden de claves,
 * espacios). Implementa un comparador semántico de árboles Jackson:
 * dos JSON son iguales si sus nodos son equivalentes, sin importar el
 * orden de las claves de objeto.
 */
public final class Ej169JsonAssertions {

    private Ej169JsonAssertions() {
    }

    /**
     * Parsea dos cadenas JSON y decide si son semánticamente equivalentes.
     *
     * @param jsonEsperado JSON esperado (no null)
     * @param jsonReal     JSON real (no null)
     * @return true si representan el mismo árbol (orden de claves irrelevante)
     * @throws IllegalArgumentException si alguno es null o no es JSON válido
     */
    public static boolean jsonIguales(String jsonEsperado, String jsonReal) {
        // TODO 1: si jsonEsperado o jsonReal son null -> IllegalArgumentException.
        // TODO 2: crea un ObjectMapper (Jackson ya está en el pom).
        // TODO 3: parsea ambas cadenas a JsonNode (readTree).
        // TODO 4: si el parseo falla (JsonProcessingException) -> IllegalArgumentException.
        // TODO 5: usa JsonNode.equals: compara estructura y valores, no texto.
        // TODO 6: el orden de claves de un objeto NO debe afectar el resultado.
        // TODO 7: el orden de elementos de un array SÍ importa (semántica de lista).
        // TODO 8: tipos distintos (número vs string) -> no iguales.
        // TODO 9: no compares por String.equals de las cadenas crudas.
        // TODO 10: devuelve el booleano de equivalencia.
        return false;
    }

    /**
     * Extrae el valor textual de un campo de primer nivel de un JSON objeto.
     *
     * @param json  JSON objeto (no null)
     * @param campo nombre del campo (no null/blank)
     * @return valor del campo como texto, o null si no existe
     * @throws IllegalArgumentException si json/campo inválidos o json no parseable
     */
    public static String valorCampo(String json, String campo) {
        // TODO 1: si json es null -> IllegalArgumentException.
        // TODO 2: si campo es null o blank -> IllegalArgumentException.
        // TODO 3: parsea json a JsonNode con ObjectMapper.
        // TODO 4: si el parseo falla -> IllegalArgumentException.
        // TODO 5: si el nodo raíz no es un objeto -> devuelve null.
        // TODO 6: obtén el nodo hijo con get(campo).
        // TODO 7: si el campo no existe (null) -> devuelve null.
        // TODO 8: usa asText() para obtener su representación textual.
        // TODO 9: no lances excepción por campo ausente: contrato devuelve null.
        // TODO 10: devuelve el texto del campo.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(jsonIguales("{\"a\":1,\"b\":2}", "{\"b\":2,\"a\":1}"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si jsonEsperado o jsonReal son null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: crea un ObjectMapper (Jackson ya está en el pom).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: parsea ambas cadenas a JsonNode (readTree).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si el parseo falla (JsonProcessingException) -> IllegalArgumentException.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: usa JsonNode.equals: compara estructura y valores, no texto.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: el orden de claves de un objeto NO debe afectar el resultado.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: el orden de elementos de un array SÍ importa (semántica de lista).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: tipos distintos (número vs string) -> no iguales.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: no compares por String.equals de las cadenas crudas.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el booleano de equivalencia.
    }

}
