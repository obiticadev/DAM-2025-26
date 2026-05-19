package com.masterclass.api.b01_java;

import java.util.List;

/**
 * Ejercicio 013 · Streams básicos: filter/map/collect.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.3).
 */
public final class Ej013StreamsBasics {

    private Ej013StreamsBasics() {
    }

    /**
     * Devuelve los nombres en mayúsculas de los productos cuyo precio supera un umbral.
     *
     * @param precios lista de pares [nombre, precio] ya emparejados como entradas
     * @param umbral  precio mínimo estricto
     * @return lista de nombres en mayúsculas, en el mismo orden
     */
    public static List<String> nombresCarosEnMayus(List<java.util.Map.Entry<String, Double>> precios, double umbral) {
        // TODO 1: abre un stream sobre 'precios'.
        // TODO 2: filtra las entradas cuyo getValue() sea > umbral (estricto).
        // TODO 3: mapea cada entrada a su getKey().
        // TODO 4: transforma cada clave a mayúsculas.
        // TODO 5: recoge a List preservando el orden y devuélvela.
        return List.of();
    }

    /**
     * Suma total de una lista de importes.
     *
     * @param importes valores a sumar
     * @return suma; 0.0 si la lista está vacía
     */
    public static double total(List<Double> importes) {
        // TODO 6: usa mapToDouble(Double::doubleValue) sobre el stream.
        // TODO 7: aplica sum() (el caso lista vacía debe dar 0.0 de forma natural).
        return -1;
    }

    /**
     * Cuenta cuántos elementos cumplen ser pares.
     *
     * @param numeros lista de enteros
     * @return cantidad de pares
     */
    public static long contarPares(List<Integer> numeros) {
        // TODO 8: abre stream sobre 'numeros'.
        // TODO 9: filtra los que cumplan n % 2 == 0.
        // TODO 10: devuelve count().
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(total(List.of(10.0, 20.0, 5.5)));
        System.out.println(contarPares(List.of(1, 2, 3, 4)));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: abre un stream sobre 'precios'.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: filtra las entradas cuyo getValue() sea > umbral (estricto).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: mapea cada entrada a su getKey().
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: transforma cada clave a mayúsculas.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: recoge a List preservando el orden y devuélvela.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: usa mapToDouble(Double::doubleValue) sobre el stream.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: aplica sum() (el caso lista vacía debe dar 0.0 de forma natural).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: abre stream sobre 'numeros'.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: filtra los que cumplan n % 2 == 0.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve count().
    }

}
