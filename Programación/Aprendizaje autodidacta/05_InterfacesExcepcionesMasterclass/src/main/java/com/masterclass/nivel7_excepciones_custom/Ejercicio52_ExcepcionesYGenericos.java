package com.masterclass.nivel7_excepciones_custom;

import java.util.List;
import java.util.function.Function;

/**
 * ============================================================================
 *  EJERCICIO 52 — EXCEPCIONES Y GENERICOS (SIN GUIA)
 * ============================================================================
 * Lee primero: teoria/07_Excepciones_Custom_Patrones.md
 */
public class Ejercicio52_ExcepcionesYGenericos {

    @FunctionalInterface
    public interface CheckedFunction<T, R> {
        R apply(T t) throws Exception;
    }

    /**
     * TODO: Aplica la funcion checked a cada elemento. Si un elemento falla,
     * lo sustituye por valorPorDefecto.
     */
    public static <T, R> List<R> mapearSeguro(List<T> lista, CheckedFunction<T, R> funcion, R valorPorDefecto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Envuelve una CheckedFunction en una Function normal.
     * Si la CheckedFunction lanza excepcion, la envuelve en RuntimeException.
     */
    public static <T, R> Function<T, R> envolver(CheckedFunction<T, R> funcion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
