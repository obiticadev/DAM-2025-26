package com.masterclass.nivel8_try_with_resources;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 58 — MULTIPLES RECURSOS (SIN GUIA)
 * ============================================================================
 */
public class Ejercicio58_MultipleResources {

    public static class Lector implements AutoCloseable {
        private final List<String> log;
        public Lector(List<String> log) { this.log = log; log.add("LECTOR_ABIERTO"); }
        public String leer() { return "datos"; }
        @Override public void close() { log.add("LECTOR_CERRADO"); }
    }

    public static class Escritor implements AutoCloseable {
        private final List<String> log;
        public Escritor(List<String> log) { this.log = log; log.add("ESCRITOR_ABIERTO"); }
        public void escribir(String dato) { log.add("ESCRITO:" + dato); }
        @Override public void close() { log.add("ESCRITOR_CERRADO"); }
    }

    /**
     * TODO: Usa try-with-resources con Lector y Escritor. Lee del lector, escribe en escritor.
     * Ambos deben cerrarse. Devuelve el log completo.
     */
    public static List<String> copiar(List<String> log) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
