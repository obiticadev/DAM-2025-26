package com.masterclass.nivel5_excepciones_checked;

/**
 * ============================================================================
 *  EJERCICIO 34 — MULTIPLE CATCH Y MULTI-CATCH (SIN GUIA)
 * ============================================================================
 * Lee primero: teoria/05_Excepciones_Checked.md
 */
public class Ejercicio34_MultipleCatch {

    /**
     * TODO: Intenta:
     * 1. Parsear texto a entero
     * 2. Acceder al array en esa posicion
     * 3. Devolver el elemento encontrado
     *
     * Si NumberFormatException -> devuelve "ERROR_FORMATO"
     * Si ArrayIndexOutOfBoundsException -> devuelve "ERROR_INDICE"
     * Si cualquier otra excepcion -> devuelve "ERROR_DESCONOCIDO"
     */
    public static String operacionMultiple(String texto, String[] datos) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Usa multi-catch (catch(TipoA | TipoB e)) para capturar
     * NumberFormatException y ArithmeticException con un solo bloque catch.
     * Devuelve el mensaje de la excepcion, o "OK:{resultado}" si no hay error.
     *
     * La operacion es: Integer.parseInt(texto) / divisor
     */
    public static String operacionMultiCatch(String texto, int divisor) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
