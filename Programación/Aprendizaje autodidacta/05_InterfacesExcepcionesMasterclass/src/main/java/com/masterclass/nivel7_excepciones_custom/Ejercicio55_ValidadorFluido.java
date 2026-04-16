package com.masterclass.nivel7_excepciones_custom;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 55 — VALIDADOR FLUIDO CON EXCEPCIONES (CON GUIA)
 * ============================================================================
 */
public class Ejercicio55_ValidadorFluido {

    public static class ValidationResult {
        private final List<String> errores = new ArrayList<>();
        public ValidationResult addError(String error) { errores.add(error); return this; }
        public boolean isValid() { return errores.isEmpty(); }
        public List<String> getErrores() { return errores; }
    }

    /**
     * TODO: Valida un registro de usuario. Acumula TODOS los errores sin detenerse:
     * - nombre null o vacio -> "Nombre requerido"
     * - email null, vacio o sin @ -> "Email invalido"
     * - edad < 0 o > 150 -> "Edad fuera de rango"
     * Devuelve un ValidationResult con todos los errores.
     */
    public static ValidationResult validarUsuario(String nombre, String email, int edad) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
