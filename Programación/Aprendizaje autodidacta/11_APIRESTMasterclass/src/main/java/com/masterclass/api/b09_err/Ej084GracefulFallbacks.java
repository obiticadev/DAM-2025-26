package com.masterclass.api.b09_err;

import java.util.function.Supplier;

/**
 * Ejercicio 084 · Degradación controlada (graceful fallback).
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.3).
 *
 * <p>Si una dependencia falla, a veces es mejor devolver un valor degradado
 * que un 500. Implementa esa política con registro de fallos.
 */
public class Ej084GracefulFallbacks {

    private int fallos = 0;

    /**
     * Intenta la operación principal; si lanza, devuelve el fallback y registra el fallo.
     *
     * @param principal operación que puede fallar
     * @param fallback  valor seguro a devolver si la principal falla
     * @param <T>       tipo de resultado
     * @return resultado de la principal, o el fallback
     */
    public <T> T conFallback(Supplier<T> principal, T fallback) {
        // TODO 1: valida que 'principal' no sea null.
        // TODO 2: abre un try alrededor de principal.get().
        // TODO 3: si tiene éxito, devuelve su resultado directamente.
        // TODO 4: NO incrementes el contador de fallos si fue bien.
        // TODO 5: captura cualquier RuntimeException.
        // TODO 6: en el catch, incrementa 'fallos'.
        // TODO 7: registra (conceptual) que se degradó (aquí basta el contador).
        // TODO 8: devuelve 'fallback' como valor degradado (no relances).
        // TODO 9: el fallback puede ser null si así se decidió (no fuerces no-null).
        // TODO 10: el método nunca debe propagar la excepción original.
        return null;
    }

    /**
     * @return número de veces que se activó el fallback
     */
    public int fallos() {
        return fallos;
    }

    public static void main(String[] args) {
        var g = new Ej084GracefulFallbacks();
        System.out.println(g.conFallback(() -> {
            throw new RuntimeException("down");
        }, "cache"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: valida que 'principal' no sea null.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: abre un try alrededor de principal.get().
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si tiene éxito, devuelve su resultado directamente.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: NO incrementes el contador de fallos si fue bien.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: captura cualquier RuntimeException.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: en el catch, incrementa 'fallos'.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: registra (conceptual) que se degradó (aquí basta el contador).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: devuelve 'fallback' como valor degradado (no relances).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: el fallback puede ser null si así se decidió (no fuerces no-null).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: el método nunca debe propagar la excepción original.
    }

}
