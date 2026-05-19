package com.masterclass.api.b01_java;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Ejercicio 017 · Interfaces funcionales: Function, Predicate, Supplier.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.3).
 */
public final class Ej017FunctionalInterfaces {

    private Ej017FunctionalInterfaces() {
    }

    /**
     * Aplica una transformación a cada elemento.
     *
     * @param entrada lista origen
     * @param f       función de mapeo
     * @param <T>     tipo de entrada
     * @param <R>     tipo de salida
     * @return lista transformada
     */
    public static <T, R> List<R> transformar(List<T> entrada, Function<T, R> f) {
        // TODO 1: abre stream sobre 'entrada'.
        // TODO 2: aplica .map(f) para transformar cada elemento.
        // TODO 3: recoge a List preservando el orden y devuélvela.
        return List.of();
    }

    /**
     * Filtra por un predicado.
     *
     * @param entrada lista origen
     * @param p       condición
     * @return elementos que cumplen p
     */
    public static <T> List<T> filtrar(List<T> entrada, Predicate<T> p) {
        // TODO 4: abre stream sobre 'entrada'.
        // TODO 5: aplica .filter(p).
        // TODO 6: recoge a List y devuélvela.
        return List.of();
    }

    /**
     * Devuelve el valor del supplier, o un fallback si lanza excepción.
     *
     * @param s        proveedor que puede fallar
     * @param fallback valor por defecto
     * @return s.get() o fallback si s lanza
     */
    public static <T> T seguroOrElse(Supplier<T> s, T fallback) {
        // TODO 7: abre un bloque try alrededor de s.get().
        // TODO 8: si tiene éxito, devuelve ese valor.
        // TODO 9: captura cualquier RuntimeException sin propagarla.
        // TODO 10: en el catch, devuelve 'fallback'.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(transformar(List.of(1, 2, 3), x -> x * x));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: abre stream sobre 'entrada'.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: aplica .map(f) para transformar cada elemento.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: recoge a List preservando el orden y devuélvela.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: abre stream sobre 'entrada'.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: aplica .filter(p).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: recoge a List y devuélvela.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: abre un bloque try alrededor de s.get().
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: si tiene éxito, devuelve ese valor.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: captura cualquier RuntimeException sin propagarla.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: en el catch, devuelve 'fallback'.
    }

}
