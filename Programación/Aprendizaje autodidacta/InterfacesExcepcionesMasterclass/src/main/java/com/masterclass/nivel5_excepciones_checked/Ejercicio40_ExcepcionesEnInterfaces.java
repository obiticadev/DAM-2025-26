package com.masterclass.nivel5_excepciones_checked;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 40 — EXCEPCIONES EN INTERFACES (SIN GUIA)
 * ============================================================================
 * Lee primero: teoria/05_Excepciones_Checked.md
 */
public class Ejercicio40_ExcepcionesEnInterfaces {

    public static class ParseException extends Exception {
        public ParseException(String msg) { super(msg); }
    }

    /**
     * Interfaz que declara throws en su contrato.
     */
    public interface Parser<T> {
        T parse(String input) throws ParseException;
    }

    /**
     * TODO: Crea un Parser<Integer> que parsee Strings a Integer.
     * Si falla, lanza ParseException con mensaje descriptivo.
     */
    public static Parser<Integer> crearParserEntero() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un Parser<Boolean> que acepte "true"/"false" (ignorando mayusculas).
     * Cualquier otro valor lanza ParseException.
     */
    public static Parser<Boolean> crearParserBooleano() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Parsea todos los elementos de la lista con el parser dado.
     * Si alguno falla, captura y lo sustituye por valorPorDefecto.
     */
    public static <T> List<T> parsearTodos(List<String> textos, Parser<T> parser, T valorPorDefecto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
