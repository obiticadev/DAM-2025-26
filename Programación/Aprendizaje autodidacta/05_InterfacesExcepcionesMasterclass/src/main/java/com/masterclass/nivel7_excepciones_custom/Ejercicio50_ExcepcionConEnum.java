package com.masterclass.nivel7_excepciones_custom;

/**
 * ============================================================================
 *  EJERCICIO 50 — EXCEPCION CON ENUM DE CODIGOS (SIN GUIA)
 * ============================================================================
 * Lee primero: teoria/07_Excepciones_Custom_Patrones.md
 */
public class Ejercicio50_ExcepcionConEnum {

    public enum ErrorCode { INVALID_INPUT, NOT_FOUND, UNAUTHORIZED, INTERNAL_ERROR }

    public static class BusinessException extends RuntimeException {
        private final ErrorCode code;
        public BusinessException(ErrorCode code, String msg) { super(msg); this.code = code; }
        public ErrorCode getCode() { return code; }
    }

    /**
     * TODO: Si nombre es null o vacio, lanza BusinessException(INVALID_INPUT, "Nombre requerido").
     * Si nombre es "admin", lanza BusinessException(UNAUTHORIZED, "Acceso denegado").
     * Si no, devuelve "Bienvenido, " + nombre.
     */
    public static String login(String nombre) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
