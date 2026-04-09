package com.masterclass.nivel7_excepciones_custom;

import java.util.Optional;

/**
 * ============================================================================
 *  EJERCICIO 56 — EXCEPCIONES Y OPTIONAL (SIN GUIA)
 * ============================================================================
 */
public class Ejercicio56_ExcepcionesYOptional {

    /**
     * TODO: Parsea texto a Integer. Si falla, devuelve Optional.empty().
     */
    public static Optional<Integer> parsearOptional(String texto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Parsea texto a Integer. Si falla, lanza IllegalArgumentException.
     * Usa parsearOptional internamente con orElseThrow.
     */
    public static int parsearOLanzar(String texto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Busca en array por indice. Si indice fuera de rango, devuelve Optional.empty().
     */
    public static <T> Optional<T> buscarSeguro(T[] array, int indice) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
