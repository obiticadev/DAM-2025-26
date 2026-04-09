package com.masterclass.nivel8_try_with_resources;

/**
 * ============================================================================
 *  EJERCICIO 60 — SUPPRESSED EXCEPTIONS (SIN GUIA)
 * ============================================================================
 */
public class Ejercicio60_SuppressedExceptions {

    public static class RecursoProblematico implements AutoCloseable {
        private final boolean fallarEnUso;
        private final boolean fallarAlCerrar;

        public RecursoProblematico(boolean fallarEnUso, boolean fallarAlCerrar) {
            this.fallarEnUso = fallarEnUso; this.fallarAlCerrar = fallarAlCerrar;
        }

        public String usar() {
            if (fallarEnUso) throw new RuntimeException("Error en uso");
            return "OK";
        }

        @Override public void close() {
            if (fallarAlCerrar) throw new RuntimeException("Error al cerrar");
        }
    }

    /**
     * TODO: Usa el recurso con try-with-resources. Si falla, captura la excepcion
     * y devuelve un String con: "Error: {msg}. Suppressed: {numSuppressed}"
     * Si OK, devuelve el resultado.
     */
    public static String usarRecurso(boolean fallarEnUso, boolean fallarAlCerrar) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
