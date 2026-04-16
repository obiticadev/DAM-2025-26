package com.masterclass.nivel7_excepciones_custom;

/**
 * ============================================================================
 *  EJERCICIO 49 — JERARQUIA DE EXCEPCIONES CUSTOM (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/07_Excepciones_Custom_Patrones.md
 */
public class Ejercicio49_JerarquiaExcepciones {

    public static class AppException extends Exception {
        private final String codigo;
        public AppException(String codigo, String msg) { super(msg); this.codigo = codigo; }
        public String getCodigo() { return codigo; }
    }

    public static class NotFoundException extends AppException {
        public NotFoundException(String recurso) { super("NOT_FOUND", recurso + " no encontrado"); }
    }

    public static class ValidationException extends AppException {
        public ValidationException(String campo, String detalle) { super("VALIDATION", campo + ": " + detalle); }
    }

    /**
     * TODO: Busca usuario por id. Si id <= 0, lanza ValidationException("id", "debe ser positivo").
     * Si id > 100, lanza NotFoundException("Usuario " + id).
     * Si no, devuelve "Usuario-" + id.
     */
    public static String buscarUsuario(int id) throws AppException {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Llama a buscarUsuario. Si lanza AppException, devuelve "[{codigo}] {mensaje}".
     * Si OK, devuelve el resultado.
     */
    public static String buscarSeguro(int id) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
