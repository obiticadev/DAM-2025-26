package com.masterclass.nivel1_interfaces_basicas;

/**
 * ============================================================================
 * EJERCICIO 04 — INTERFAZ COMO TIPO DE RETORNO / FACTORY (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Un metodo puede devolver una INTERFAZ como tipo de retorno.
 * Esto se llama el patron FACTORY: un metodo que "fabrica" objetos sin que
 * el llamador sepa exactamente que clase concreta esta recibiendo.
 *
 * Formateador f = crearFormateador("mayusculas");
 * // El llamador sabe que f es un Formateador, pero no sabe la clase interna.
 *
 * Ventaja: puedes cambiar la implementacion interna sin afectar al llamador.
 *
 * Lee primero: teoria/01_Interfaces_Basicas.md
 */
public class Ejercicio04_InterfazComoRetorno {

    /**
     * Interfaz que define un formateador de texto.
     */
    public interface Formateador {
        String formatear(String texto);
    }

    /**
     * TODO: Metodo factory que devuelve un Formateador segun el tipo recibido:
     *
     * - "mayusculas" -> convierte el texto a mayusculas (toUpperCase)
     * - "minusculas" -> convierte el texto a minusculas (toLowerCase)
     * - "invertir" -> invierte el texto (ej: "Hola" -> "aloH")
     * - "censurar" -> reemplaza todas las vocales (aeiouAEIOU) por '*'
     *
     * Si el tipo no es ninguno de los anteriores, devuelve un Formateador
     * que retorne el texto sin cambios (identidad).
     */
    public static Formateador crearFormateador(String tipo) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Recibe un texto y una lista de tipos de formateadores (varargs).
     * Aplica cada formateador en secuencia y devuelve el resultado final.
     *
     * Ejemplo: aplicarCadena("Hola", "mayusculas", "invertir") -> "ALOH"
     * Primero "Hola" -> "HOLA" (mayusculas), luego "HOLA" -> "ALOH" (invertir)
     */
    public static String aplicarCadena(String texto, String... tipos) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
