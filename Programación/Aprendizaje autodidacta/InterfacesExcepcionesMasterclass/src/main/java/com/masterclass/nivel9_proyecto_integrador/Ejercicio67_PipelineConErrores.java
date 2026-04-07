package com.masterclass.nivel9_proyecto_integrador;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * ============================================================================
 *  EJERCICIO 67 — PIPELINE CON MANEJO DE ERRORES (INTEGRADOR)
 * ============================================================================
 * Combina: Function composition, exception handling, generics.
 */
public class Ejercicio67_PipelineConErrores {

    public static class PipelineResult<T> {
        private final T value;
        private final List<String> errores;
        public PipelineResult(T value, List<String> errores) { this.value = value; this.errores = errores; }
        public T getValue() { return value; }
        public List<String> getErrores() { return errores; }
        public boolean hasErrors() { return !errores.isEmpty(); }
    }

    /**
     * TODO: Procesa cada elemento de la lista a traves del pipeline de funciones.
     * Si un paso falla, registra el error y usa el valor anterior para el siguiente paso.
     * Devuelve PipelineResult con la lista de resultados y la lista de errores.
     */
    public static <T> PipelineResult<List<T>> procesarPipeline(List<T> datos, List<Function<T, T>> pasos) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
