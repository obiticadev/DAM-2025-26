package com.masterclass.nivel4_interfaces_funcionales;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * ============================================================================
 *  EJERCICIO 31 — BIFUNCTION Y BIPREDICATE (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE:
 * - BiFunction<T,U,R>: recibe dos parametros (T,U), devuelve R.
 * - BiPredicate<T,U>: recibe dos parametros (T,U), devuelve boolean.
 *
 * Son versiones de dos argumentos de Function y Predicate.
 *
 * Lee primero: teoria/04_Interfaces_Funcionales.md
 */
public class Ejercicio31_BiFunction {

    /**
     * TODO: Devuelve una BiFunction que concatene dos Strings con un espacio: a + " " + b.
     */
    public static BiFunction<String, String, String> concatenar() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve una BiFunction que calcule la potencia: Math.pow(base, exp).
     */
    public static BiFunction<Double, Double, Double> potencia() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve un BiPredicate que compruebe si a > b.
     */
    public static BiPredicate<Integer, Integer> esMayor() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Dado un Map<String, Integer>, aplica la BiFunction a cada par (clave, valor)
     * y devuelve un Map<String, R> con los resultados.
     */
    public static <R> Map<String, R> transformarMapa(Map<String, Integer> mapa,
                                                      BiFunction<String, Integer, R> funcion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
