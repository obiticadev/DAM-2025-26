package com.masterclass.nivel6_excepciones_unchecked;

/**
 * ============================================================================
 *  EJERCICIO 43 — EXCEPCION UNCHECKED CUSTOM (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/06_Excepciones_Unchecked.md
 */
public class Ejercicio43_UncheckedCustom {

    public static class ConfiguracionException extends RuntimeException {
        private final String clave;
        public ConfiguracionException(String clave, String mensaje) { super(mensaje); this.clave = clave; }
        public String getClave() { return clave; }
    }

    /**
     * TODO: Busca la clave en los pares clave-valor. Si no existe, lanza
     * ConfiguracionException con la clave y mensaje "Clave no encontrada: {clave}".
     * Los pares se dan como array de Strings: ["clave1=valor1", "clave2=valor2", ...]
     */
    public static String obtenerConfiguracion(String[] pares, String clave) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Intenta obtener la configuracion. Si lanza ConfiguracionException,
     * devuelve valorPorDefecto.
     */
    public static String obtenerODefault(String[] pares, String clave, String valorPorDefecto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
