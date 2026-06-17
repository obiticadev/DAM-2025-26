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

        /**
     * RETO EXTRA 01: Crea un nodo JSON simple.
     */
    public static com.fasterxml.jackson.databind.JsonNode crearJsonNodo(String key, String value) {
        // GUÍA: teoría 19.6 — construye un nodo objeto con Jackson.
        // 1. ObjectMapper mapper = new ObjectMapper();
        // 2. return mapper.createObjectNode().put(key, value);
        // El test solo hace assertNotNull, así que basta devolver un ObjectNode
        // con un campo. PISTA: createObjectNode() crea un objeto mutable; .put
        // añade un par clave/valor y lo devuelve encadenable.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearJsonNodo");
    }

    /**
     * RETO EXTRA 02: Comprueba si contiene el campo.
     */
    public static boolean contieneCampo(String json, String c) {
        // GUÍA: teoría 19.6 — parsea y pregunta por la clave.
        // 1. JsonNode raiz = new ObjectMapper().readTree(json);  (en try/catch)
        // 2. return raiz.has(c);
        // El test ({"a":1}, "a") espera true. OJO: readTree lanza la checked
        // JsonProcessingException; envuélvela en try/catch (puedes relanzar
        // IllegalArgumentException si el JSON es inválido). JsonNode.has(campo)
        // dice si el campo existe sin traer su valor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneCampo");
    }

    /**
     * RETO EXTRA 03: Extrae valor entero del campo.
     */
    public static int obtenerCampoEntero(String json, String c) {
        // GUÍA: teoría 19.6 — navega al campo y léelo como entero.
        // JsonNode raiz = new ObjectMapper().readTree(json);   (try/catch)
        // return raiz.get(c).asInt();
        // El test ({"a":10}, "a") espera 10. asInt() convierte el nodo numérico
        // a int; get(c) devuelve el hijo (null si no existe, ojo con NPE).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCampoEntero");
    }

    /**
     * RETO EXTRA 04: Determina si el JSON es un array.
     */
    public static boolean esArrayJson(String json) {
        // GUÍA: teoría 19.6 — parsea y pregunta el tipo del nodo raíz.
        // return new ObjectMapper().readTree(json).isArray();   (try/catch)
        // El test ("[1,2]") espera true. isArray() distingue [..] de {..}; un
        // array conserva el orden (es una lista), un objeto no (reto 6 lo cruza).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esArrayJson");
    }

    /**
     * RETO EXTRA 05: Obtiene el tamaño del array JSON.
     */
    public static int tamanioArrayJson(String json) {
        // GUÍA: teoría 19.6 — una línea: return readTree(json).size();
        // El test ("[1,2]") espera 2. JsonNode.size() devuelve el nº de
        // elementos de un array (o de campos si fuera objeto). Recuerda el
        // try/catch de readTree.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanioArrayJson");
    }

    /**
     * RETO EXTRA 06: Determina si el JSON es un objeto.
     */
    public static boolean esObjetoJson(String json) {
        // GUÍA: teoría 19.6 — una línea: return readTree(json).isObject();
        // El test ({"a":1}) espera true. Simétrico a esArrayJson (reto 4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esObjetoJson");
    }

    /**
     * RETO EXTRA 07: Extrae valor booleano del campo.
     */
    public static boolean obtenerCampoBooleano(String json, String c) {
        // GUÍA: teoría 19.6 — navega y lee como booleano.
        // return readTree(json).get(c).asBoolean();   (try/catch)
        // El test ({"a":true}, "a") espera true. asBoolean() es el primo de
        // asInt/asText para nodos booleanos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCampoBooleano");
    }

    /**
     * RETO EXTRA 08: Formatea el JSON de forma compacta.
     */
    public static String formatoJsonLimpio(String json) {
        // GUÍA: teoría 19.6 — parsear y volver a serializar normaliza/compacta.
        // return readTree(json).toString();   (try/catch)
        // El test ({"a": 1}) solo exige que el resultado no esté vacío.
        // toString() de un JsonNode produce JSON compacto (sin los espacios del
        // original): "{\"a\":1}". Es la forma canónica para comparar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatoJsonLimpio");
    }

    /**
     * RETO EXTRA 09: Obtiene nombres de todos los campos.
     */
    public static java.util.List<String> obtenerNombresCampos(String json) {
        // GUÍA: teoría 19.6 — recoge los nombres de campo en una lista.
        // 1. JsonNode raiz = readTree(json);   (try/catch)
        // 2. List<String> nombres = new ArrayList<>();
        //    raiz.fieldNames().forEachRemaining(nombres::add);
        //    return nombres;
        // El test ({"a":1}) espera size()==1. fieldNames() devuelve un
        // Iterator<String>; forEachRemaining lo vuelca a la lista. (Alternativa
        // moderna: raiz.properties().stream().map(Map.Entry::getKey).toList().)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombresCampos");
    }

    /**
     * RETO EXTRA 10: Valida si el JSON es sintacticamente correcto.
     */
    public static boolean esJsonValido(String json) {
        // GUÍA: teoría 19.6 — el parseo ES la validación.
        // try { new ObjectMapper().readTree(json); return true; }
        // catch (Exception e) { return false; }
        // El test ("{}") espera true. Aquí NO relances la excepción: el contrato
        // es devolver boolean. "Si parsea, es válido" — el patrón clásico de
        // "intentar y capturar" para validar formato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esJsonValido");
    }

}
