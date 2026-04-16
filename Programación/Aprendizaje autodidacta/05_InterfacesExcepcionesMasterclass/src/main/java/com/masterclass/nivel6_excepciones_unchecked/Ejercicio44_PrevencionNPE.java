package com.masterclass.nivel6_excepciones_unchecked;

import java.util.List;
import java.util.Objects;

/**
 * ============================================================================
 *  EJERCICIO 44 — PREVENCION DE NULLPOINTEREXCEPTION (SIN GUIA)
 * ============================================================================
 * Lee primero: teoria/06_Excepciones_Unchecked.md
 */
public class Ejercicio44_PrevencionNPE {

    /**
     * TODO: Usa Objects.requireNonNull para validar que nombre no sea null.
     * Devuelve "Hola, " + nombre.
     */
    public static String saludar(String nombre) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Filtra nulls de la lista y devuelve una nueva lista sin nulls.
     */
    public static <T> List<T> filtrarNulls(List<T> lista) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve el primer elemento no-null de la lista, o valorPorDefecto si todos son null.
     */
    public static <T> T primerNoNull(List<T> lista, T valorPorDefecto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
