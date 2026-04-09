package com.masterclass.nivel6_excepciones_unchecked;

/**
 * ============================================================================
 *  EJERCICIO 41 — ILLEGALARGUMENTEXCEPTION (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/06_Excepciones_Unchecked.md
 */
public class Ejercicio41_IllegalArgument {
    /**
     * TODO: Si edad < 0 o > 150, lanza IllegalArgumentException con mensaje descriptivo.
     * Si no, devuelve "Edad: {edad}".
     */
    public static String validarEdad(int edad) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Si texto es null, lanza NullPointerException("texto no puede ser null").
     * Si texto esta vacio, lanza IllegalArgumentException("texto no puede estar vacio").
     * Si no, devuelve texto.length().
     */
    public static int longitudSegura(String texto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
