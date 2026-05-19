package com.masterclass.api.b01_java;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Ejercicio 021 · Concurrencia mínima con CompletableFuture.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.6).
 */
public final class Ej021ConcurrencyBasics {

    private Ej021ConcurrencyBasics() {
    }

    /**
     * Ejecuta una tarea de forma asíncrona y devuelve el future.
     *
     * @param valor valor a producir
     * @return CompletableFuture que completará con 'valor' en mayúsculas
     */
    public static CompletableFuture<String> asincronoMayus(String valor) {
        // TODO 1: usa CompletableFuture.supplyAsync (ejecuta en otro hilo).
        // TODO 2: la lambda debe devolver valor.toUpperCase().
        // TODO 3: devuelve el future SIN bloquear (no llames join aquí).
        return null;
    }

    /**
     * Combina dos futures sumando sus resultados enteros.
     *
     * @param a primer future
     * @param b segundo future
     * @return future con la suma
     */
    public static CompletableFuture<Integer> sumar(CompletableFuture<Integer> a, CompletableFuture<Integer> b) {
        // TODO 4: combina ambos con a.thenCombine(b, ...).
        // TODO 5: la BiFunction debe sumar los dos enteros (Integer::sum).
        // TODO 6: devuelve el future combinado (sigue siendo asíncrono).
        return null;
    }

    /**
     * Espera todos los futures y devuelve sus resultados en orden.
     *
     * @param futures lista de futures de String
     * @return lista con los valores ya resueltos
     */
    public static List<String> esperarTodos(List<CompletableFuture<String>> futures) {
        // TODO 7: usa CompletableFuture.allOf(...) con el array de futures para esperar a todos.
        // TODO 8: invoca .join() sobre ese allOf para bloquear hasta que terminen.
        // TODO 9: recorre 'futures' en orden y haz join() de cada uno.
        // TODO 10: recoge los resultados a una List y devuélvela.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(asincronoMayus("hola").join());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: usa CompletableFuture.supplyAsync (ejecuta en otro hilo).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: la lambda debe devolver valor.toUpperCase().
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: devuelve el future SIN bloquear (no llames join aquí).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: combina ambos con a.thenCombine(b, ...).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: la BiFunction debe sumar los dos enteros (Integer::sum).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: devuelve el future combinado (sigue siendo asíncrono).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: usa CompletableFuture.allOf(...) con el array de futures para esperar a todos.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: invoca .join() sobre ese allOf para bloquear hasta que terminen.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: recorre 'futures' en orden y haz join() de cada uno.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: recoge los resultados a una List y devuélvela.
    }

}
