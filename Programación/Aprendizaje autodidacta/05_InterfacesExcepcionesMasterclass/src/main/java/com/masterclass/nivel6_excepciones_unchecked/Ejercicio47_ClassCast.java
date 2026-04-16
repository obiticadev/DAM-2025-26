package com.masterclass.nivel6_excepciones_unchecked;

/**
 * ============================================================================
 *  EJERCICIO 47 — CLASSCASTEXCEPTION Y CASTING SEGURO (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/06_Excepciones_Unchecked.md
 */
public class Ejercicio47_ClassCast {

    /**
     * TODO: Si obj es del tipo clazz, haz cast y devuelve el resultado.
     * Si no, devuelve valorPorDefecto.
     * Usa instanceof para verificar antes del cast.
     */
    @SuppressWarnings("unchecked")
    public static <T> T castSeguro(Object obj, Class<T> clazz, T valorPorDefecto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve la representacion String del objeto.
     * Si es Integer, devuelve "Entero: {valor}".
     * Si es String, devuelve "Texto: {valor}".
     * Si es Double, devuelve "Decimal: {valor}".
     * Cualquier otro, devuelve "Desconocido: {obj.toString()}".
     */
    public static String describir(Object obj) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
