package com.masterclass.nivel6_excepciones_unchecked;

import java.util.List;
import java.util.Map;

/**
 * ============================================================================
 *  EJERCICIO 48 — PATRON DEFENSIVO COMPLETO (SIN GUIA)
 * ============================================================================
 * Lee primero: teoria/06_Excepciones_Unchecked.md
 */
public class Ejercicio48_PatronDefensivo {

    /**
     * TODO: Valida parametros y realiza la operacion de forma defensiva:
     * - datos no puede ser null -> NullPointerException
     * - datos no puede estar vacio -> IllegalArgumentException("Lista vacia")
     * - clave no puede ser null ni vacia -> IllegalArgumentException("Clave invalida")
     * Si todo OK, devuelve un Map con:
     *   "clave" -> clave
     *   "total" -> String.valueOf(suma de datos)
     *   "promedio" -> String.valueOf(promedio de datos)
     */
    public static Map<String, String> procesarDatos(List<Integer> datos, String clave) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
