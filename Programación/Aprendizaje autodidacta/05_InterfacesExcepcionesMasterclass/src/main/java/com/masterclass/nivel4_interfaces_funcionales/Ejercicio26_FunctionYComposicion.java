package com.masterclass.nivel4_interfaces_funcionales;

import java.util.List;
import java.util.function.Function;

/**
 * ============================================================================
 *  EJERCICIO 26 — FUNCTION<T,R> Y COMPOSICION (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Function<T,R> transforma un T en un R.
 * Composicion: f.andThen(g) = primero f, luego g.
 *              f.compose(g) = primero g, luego f.
 *
 * Lee primero: teoria/04_Interfaces_Funcionales.md
 */
public class Ejercicio26_FunctionYComposicion {

    /**
     * TODO: Devuelve una Function que calcule la longitud de un String.
     */
    public static Function<String, Integer> longitud() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve una Function que duplique un entero (x * 2).
     */
    public static Function<Integer, Integer> duplicar() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Compone longitud().andThen(duplicar()) para obtener el doble de la longitud.
     */
    public static Function<String, Integer> dobleLongitud() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Aplica una Function a cada elemento de la lista y devuelve la lista transformada.
     */
    public static <T, R> List<R> mapear(List<T> lista, Function<T, R> funcion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
