package com.masterclass.nivel5_excepciones_checked;

/**
 * ============================================================================
 *  EJERCICIO 39 — VALIDADOR CON EXCEPCIONES (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/05_Excepciones_Checked.md
 */
public class Ejercicio39_ValidadorConExcepciones {

    public static class ValidacionException extends Exception {
        private final String campo;
        public ValidacionException(String campo, String mensaje) {
            super(mensaje);
            this.campo = campo;
        }
        public String getCampo() { return campo; }
    }

    /**
     * TODO: Valida un email. Debe:
     * - No ser null ni vacio -> lanza ValidacionException("email", "Email requerido")
     * - Contener '@' -> lanza ValidacionException("email", "Email debe contener @")
     * - Tener al menos 5 caracteres -> lanza ValidacionException("email", "Email muy corto")
     * Si pasa todas, devuelve "Email valido: {email}"
     */
    public static String validarEmail(String email) throws ValidacionException {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Valida una password. Debe:
     * - No ser null ni vacia -> lanza ValidacionException("password", "Password requerida")
     * - Tener al menos 8 caracteres -> lanza ValidacionException("password", "Password muy corta")
     * Si pasa, devuelve "Password valida"
     */
    public static String validarPassword(String password) throws ValidacionException {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
