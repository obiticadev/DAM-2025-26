package com.masterclass.nivel5_excepciones_checked;

/**
 * ============================================================================
 *  EJERCICIO 33 — PRIMER TRY-CATCH (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/05_Excepciones_Checked.md
 */
public class Ejercicio33_PrimerTryCatch {

    /**
     * TODO: Intenta parsear el String a entero con Integer.parseInt.
     * Si falla (NumberFormatException), devuelve valorPorDefecto.
     */
    public static int parsearSeguro(String texto, int valorPorDefecto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Intenta dividir a / b.
     * Si b es 0, devuelve 0 en vez de lanzar ArithmeticException.
     */
    public static int dividirSeguro(int a, int b) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Intenta acceder al elemento en la posicion dada del array.
     * Si la posicion no existe, devuelve null.
     */
    public static String accederSeguro(String[] array, int posicion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
