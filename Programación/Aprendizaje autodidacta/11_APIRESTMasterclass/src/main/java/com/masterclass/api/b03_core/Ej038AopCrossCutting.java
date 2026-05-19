package com.masterclass.api.b03_core;

import java.util.function.Supplier;

/**
 * Ejercicio 038 · AOP conceptual: lógica transversal alrededor de una llamada.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.5).
 *
 * <p>Un "around advice" mínimo: cuenta invocaciones y mide sin tocar la lógica.
 */
public class Ej038AopCrossCutting {

    private int invocaciones = 0;

    /**
     * Ejecuta la acción contando la invocación (advice "before") y devolviendo
     * su resultado (la acción no se modifica).
     *
     * @param accion lógica de negocio a envolver
     * @param <T>    tipo de retorno
     * @return el valor que devuelve la acción
     */
    public <T> T alrededor(Supplier<T> accion) {
        // TODO 1: valida que 'accion' no sea null.
        // TODO 2: el "advice before" se ejecuta ANTES de la lógica de negocio.
        // TODO 3: incrementa el contador 'invocaciones' como parte del advice before.
        // TODO 4: ejecuta la lógica real con accion.get() y guarda el resultado.
        // TODO 5: NO modifiques el resultado (un around transparente lo devuelve igual).
        // TODO 6: (conceptual) aquí iría un "advice after" si lo hubiera.
        // TODO 7: devuelve el resultado obtenido.
        // TODO 8: el contador debe reflejar el nº de veces invocado, no de éxitos.
        // TODO 9: ten presente que si 'accion' lanza, el contador ya se incrementó (before).
        // TODO 10: mantén el método genérico (<T>) para envolver cualquier tipo de retorno.
        return null;
    }

    /**
     * @return número de veces que se ha invocado {@link #alrededor}
     */
    public int invocaciones() {
        return invocaciones;
    }

    public static void main(String[] args) {
        var aop = new Ej038AopCrossCutting();
        aop.alrededor(() -> "a");
        aop.alrededor(() -> "b");
        System.out.println(aop.invocaciones());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: valida que 'accion' no sea null.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: el "advice before" se ejecuta ANTES de la lógica de negocio.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: incrementa el contador 'invocaciones' como parte del advice before.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: ejecuta la lógica real con accion.get() y guarda el resultado.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: NO modifiques el resultado (un around transparente lo devuelve igual).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: (conceptual) aquí iría un "advice after" si lo hubiera.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: devuelve el resultado obtenido.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: el contador debe reflejar el nº de veces invocado, no de éxitos.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: ten presente que si 'accion' lanza, el contador ya se incrementó (before).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: mantén el método genérico (<T>) para envolver cualquier tipo de retorno.
    }

}
