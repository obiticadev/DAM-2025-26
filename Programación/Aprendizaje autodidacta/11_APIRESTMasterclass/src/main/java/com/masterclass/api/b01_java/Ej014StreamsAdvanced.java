package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 014 · Streams avanzados: reduce, flatMap, Collectors.groupingBy.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.3).
 */
public final class Ej014StreamsAdvanced {

    private Ej014StreamsAdvanced() {
    }

    /**
     * Aplana una lista de listas en una sola lista preservando el orden.
     *
     * @param listas lista de sublistas
     * @return todos los elementos concatenados
     */
    public static List<Integer> aplanar(List<List<Integer>> listas) {
        // TODO 1: abre stream sobre 'listas' (cada elemento es una sublista).
        // TODO 2: usa flatMap(List::stream) para fundir las sublistas en un solo flujo.
        // TODO 3: recoge a List respetando el orden de aparición y devuélvela.
        return List.of();
    }

    /**
     * Agrupa palabras por su primera letra.
     *
     * @param palabras lista de palabras no vacías
     * @return mapa letra→lista de palabras que empiezan por esa letra
     */
    public static Map<Character, List<String>> agruparPorInicial(List<String> palabras) {
        // TODO 4: abre stream sobre 'palabras'.
        // TODO 5: define la clave de agrupación: p -> p.charAt(0).
        // TODO 6: usa Collectors.groupingBy con esa función clave.
        // TODO 7: el value por defecto de groupingBy ya es una List que preserva orden: devuélvelo.
        return Map.of();
    }

    /**
     * Concatena nombres separados por coma usando reduce/joining.
     *
     * @param nombres lista de nombres
     * @return "a, b, c"; cadena vacía si la lista está vacía
     */
    public static String unirConComas(List<String> nombres) {
        // TODO 8: abre stream sobre 'nombres'.
        // TODO 9: usa Collectors.joining(", ") (el caso vacío produce "" naturalmente).
        // TODO 10: devuelve la cadena resultante.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(aplanar(List.of(List.of(1, 2), List.of(3))));
        System.out.println(unirConComas(List.of("a", "b", "c")));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: abre stream sobre 'listas' (cada elemento es una sublista).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: usa flatMap(List::stream) para fundir las sublistas en un solo flujo.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: recoge a List respetando el orden de aparición y devuélvela.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: abre stream sobre 'palabras'.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: define la clave de agrupación: p -> p.charAt(0).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: usa Collectors.groupingBy con esa función clave.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: el value por defecto de groupingBy ya es una List que preserva orden: devuélvelo.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: abre stream sobre 'nombres'.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: usa Collectors.joining(", ") (el caso vacío produce "" naturalmente).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la cadena resultante.
    }

}
