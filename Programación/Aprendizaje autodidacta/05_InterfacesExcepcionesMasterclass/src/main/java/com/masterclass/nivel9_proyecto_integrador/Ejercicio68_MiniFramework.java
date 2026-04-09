package com.masterclass.nivel9_proyecto_integrador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * ============================================================================
 *  EJERCICIO 68 — MINI FRAMEWORK DE PROCESAMIENTO (INTEGRADOR FINAL)
 * ============================================================================
 * Combina TODO: interfaces, generics, functional interfaces, custom exceptions,
 * try-with-resources, Optional, validation patterns.
 */
public class Ejercicio68_MiniFramework {

    public static class ProcessingException extends Exception {
        private final String step;
        public ProcessingException(String step, String msg, Throwable cause) { super(msg, cause); this.step = step; }
        public String getStep() { return step; }
    }

    public interface Processor<T> extends AutoCloseable {
        String getName();
        T process(T input) throws ProcessingException;
    }

    public static class ProcessingReport {
        private final List<String> stepsExecuted = new ArrayList<>();
        private final List<String> errors = new ArrayList<>();
        private boolean success = true;

        public void addStep(String step) { stepsExecuted.add(step); }
        public void addError(String error) { errors.add(error); success = false; }
        public List<String> getStepsExecuted() { return stepsExecuted; }
        public List<String> getErrors() { return errors; }
        public boolean isSuccess() { return success; }
    }

    /**
     * TODO: Crea un Processor que aplica la funcion dada. El close no hace nada.
     * Si la funcion lanza, envuelve en ProcessingException con el nombre del paso.
     */
    public static <T> Processor<T> crearProcesador(String nombre, Function<T, T> funcion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Ejecuta la cadena de procesadores sobre el input.
     * Usa try-with-resources para cada procesador.
     * Si un procesador falla, registra el error en el report y continua con el valor anterior.
     * Devuelve un Map con "resultado" -> resultado final toString(), "report" -> report.
     */
    public static <T> Map<String, Object> ejecutarCadena(T input, List<Processor<T>> procesadores) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
