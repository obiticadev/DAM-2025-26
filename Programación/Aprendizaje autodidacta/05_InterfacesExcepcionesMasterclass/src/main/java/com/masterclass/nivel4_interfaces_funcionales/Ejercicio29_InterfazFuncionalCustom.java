package com.masterclass.nivel4_interfaces_funcionales;

/**
 * ============================================================================
 *  EJERCICIO 29 — INTERFAZ FUNCIONAL CUSTOM (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Puedes crear tus propias interfaces funcionales con @FunctionalInterface.
 * Deben tener exactamente UN metodo abstracto (SAM: Single Abstract Method).
 * Pueden tener metodos default y static adicionales.
 *
 *   @FunctionalInterface
 *   public interface Transformador<T, R> {
 *       R transformar(T entrada);  // Unico abstracto
 *       default Transformador<T, R> siNoNull() { ... }  // Default OK
 *   }
 *
 * Lee primero: teoria/04_Interfaces_Funcionales.md
 */
public class Ejercicio29_InterfazFuncionalCustom {

    @FunctionalInterface
    public interface Validador<T> {
        boolean validar(T valor);

        default Validador<T> and(Validador<T> otro) {
            return valor -> this.validar(valor) && otro.validar(valor);
        }

        default Validador<T> or(Validador<T> otro) {
            return valor -> this.validar(valor) || otro.validar(valor);
        }

        default Validador<T> negate() {
            return valor -> !this.validar(valor);
        }
    }

    /**
     * TODO: Devuelve un Validador<String> que verifica que el String no sea null ni vacio.
     */
    public static Validador<String> noNuloNiVacio() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve un Validador<Integer> que verifica que el numero sea positivo (> 0).
     */
    public static Validador<Integer> esPositivo() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Combina noNuloNiVacio() AND un validador de longitud minima 3
     * usando el metodo default and().
     */
    public static Validador<String> validadorCompleto() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
