package com.masterclass.nivel7_excepciones_custom;

import java.util.function.Supplier;

/**
 * ============================================================================
 *  EJERCICIO 53 — PATRON RETRY (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/07_Excepciones_Custom_Patrones.md
 */
public class Ejercicio53_RetryPattern {

    /**
     * TODO: Ejecuta el supplier hasta maxIntentos veces.
     * Si tiene exito, devuelve el resultado.
     * Si falla todos los intentos, lanza la ultima excepcion.
     */
    public static <T> T reintentar(Supplier<T> operacion, int maxIntentos) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Igual que reintentar pero devuelve valorPorDefecto si falla todos los intentos.
     */
    public static <T> T reintentarODefault(Supplier<T> operacion, int maxIntentos, T valorPorDefecto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
