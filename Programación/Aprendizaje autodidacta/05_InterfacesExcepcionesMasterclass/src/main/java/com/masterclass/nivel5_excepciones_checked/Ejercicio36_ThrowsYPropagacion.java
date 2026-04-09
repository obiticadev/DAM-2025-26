package com.masterclass.nivel5_excepciones_checked;

/**
 * ============================================================================
 *  EJERCICIO 36 — THROWS Y PROPAGACION (SIN GUIA)
 * ============================================================================
 * Lee primero: teoria/05_Excepciones_Checked.md
 */
public class Ejercicio36_ThrowsYPropagacion {

    /**
     * Excepcion checked personalizada.
     */
    public static class EdadInvalidaException extends Exception {
        public EdadInvalidaException(String mensaje) { super(mensaje); }
    }

    /**
     * TODO: Si edad < 0 o > 150, lanza EdadInvalidaException con mensaje descriptivo.
     * Si es valida, devuelve "Edad valida: {edad}".
     */
    public static String validarEdad(int edad) throws EdadInvalidaException {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Llama a validarEdad. Si lanza excepcion, captura y devuelve
     * "Error: " + mensaje de la excepcion. Si no, devuelve el resultado normal.
     */
    public static String procesarEdad(int edad) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
