package com.masterclass.nivel7_excepciones_custom;

/**
 * ============================================================================
 *  EJERCICIO 51 — PATRON RESULT (ALTERNATIVA A EXCEPCIONES) (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/07_Excepciones_Custom_Patrones.md
 */
public class Ejercicio51_PatronResult {

    public static class Result<T> {
        private final T value;
        private final String error;
        private final boolean success;

        private Result(T value, String error, boolean success) {
            this.value = value; this.error = error; this.success = success;
        }

        public static <T> Result<T> ok(T value) { return new Result<>(value, null, true); }
        public static <T> Result<T> error(String error) { return new Result<>(null, error, false); }
        public T getValue() { return value; }
        public String getError() { return error; }
        public boolean isSuccess() { return success; }
    }

    /**
     * TODO: Divide a / b. Si b == 0, devuelve Result.error("Division por cero").
     * Si no, devuelve Result.ok(a / b).
     */
    public static Result<Double> dividir(double a, double b) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Parsea texto a Integer. Si falla, Result.error("No es un numero: {texto}").
     * Si OK, Result.ok(valor).
     */
    public static Result<Integer> parsear(String texto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
