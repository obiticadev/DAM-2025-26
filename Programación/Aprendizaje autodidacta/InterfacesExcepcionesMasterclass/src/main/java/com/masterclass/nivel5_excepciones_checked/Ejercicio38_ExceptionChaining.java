package com.masterclass.nivel5_excepciones_checked;

/**
 * ============================================================================
 *  EJERCICIO 38 — EXCEPTION CHAINING (CAUSA RAIZ) (SIN GUIA)
 * ============================================================================
 * Lee primero: teoria/05_Excepciones_Checked.md
 */
public class Ejercicio38_ExceptionChaining {

    public static class ServicioException extends Exception {
        public ServicioException(String msg, Throwable causa) { super(msg, causa); }
    }

    /**
     * TODO: Intenta parsear "texto" a Integer. Si falla, lanza ServicioException
     * con mensaje "Error procesando dato" y la NumberFormatException como causa.
     * Si funciona, devuelve el entero.
     */
    public static int procesarDato(String texto) throws ServicioException {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Llama a procesarDato. Si lanza ServicioException, devuelve un String:
     * "Error: {mensaje}. Causa: {causa.getMessage()}"
     * Si OK, devuelve "Resultado: {valor}"
     */
    public static String procesarConInfo(String texto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
