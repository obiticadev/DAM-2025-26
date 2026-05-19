package com.masterclass.api.b01_java;

import java.util.List;

/**
 * Ejercicio 016 · Comodines y varianza: {@code ? extends} / {@code ? super}.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.4).
 */
public final class Ej016WildcardsVariance {

    private Ej016WildcardsVariance() {
    }

    /**
     * Suma cualquier lista de números (Integer, Double, Long...).
     *
     * @param numeros lista productora; usa {@code ? extends Number}
     * @return suma como double
     */
    public static double sumar(List<? extends Number> numeros) {
        // TODO 1: declara un acumulador double a 0.
        // TODO 2: recorre la lista (es PRODUCTORA: solo lees, ? extends Number).
        // TODO 3: para cada elemento usa doubleValue() para obtener su valor.
        // TODO 4: acumula en el total.
        // TODO 5: devuelve el acumulado.
        return -1;
    }

    /**
     * Añade los enteros 1, 2 y 3 a una lista consumidora.
     *
     * @param destino lista consumidora; usa {@code ? super Integer}
     */
    public static void rellenar(List<? super Integer> destino) {
        // TODO 6: 'destino' es CONSUMIDORA (? super Integer): puedes añadir Integers.
        // TODO 7: añade los valores 1, 2 y 3 en ese orden.
    }

    /**
     * Cuenta cuántos elementos son instancia de la clase dada.
     *
     * @param items lista heterogénea
     * @param tipo  clase a contar
     * @return número de coincidencias
     */
    public static long contarDeTipo(List<?> items, Class<?> tipo) {
        // TODO 8: abre stream sobre 'items' (comodín sin límites: solo Object).
        // TODO 9: filtra con tipo.isInstance(x) (comprobación de tipo en runtime).
        // TODO 10: devuelve count().
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(sumar(List.of(1, 2.5, 3L)));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: declara un acumulador double a 0.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: recorre la lista (es PRODUCTORA: solo lees, ? extends Number).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: para cada elemento usa doubleValue() para obtener su valor.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: acumula en el total.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: devuelve el acumulado.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: 'destino' es CONSUMIDORA (? super Integer): puedes añadir Integers.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: añade los valores 1, 2 y 3 en ese orden.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: abre stream sobre 'items' (comodín sin límites: solo Object).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: filtra con tipo.isInstance(x) (comprobación de tipo en runtime).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve count().
    }

}
