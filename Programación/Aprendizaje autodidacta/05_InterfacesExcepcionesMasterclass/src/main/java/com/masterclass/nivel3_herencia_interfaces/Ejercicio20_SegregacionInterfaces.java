package com.masterclass.nivel3_herencia_interfaces;

/**
 * ============================================================================
 *  EJERCICIO 20 — SEGREGACION DE INTERFACES / ISP (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: El Principio de Segregacion de Interfaces (ISP) dice:
 * "Ninguna clase debe verse obligada a implementar metodos que no usa".
 *
 * En vez de una interfaz GORDA con muchos metodos, crea interfaces PEQUENAS
 * y especificas. Cada clase implementa solo las que necesita.
 *
 * Lee primero: teoria/03_Herencia_de_Interfaces.md
 */
public class Ejercicio20_SegregacionInterfaces {

    public interface Leible { String leer(); }
    public interface Escribible { void escribir(String contenido); }
    public interface Borrable { void borrar(); }

    /**
     * TODO: Crea un objeto que sea SOLO Leible. Siempre devuelve el mismo contenido.
     */
    public static Leible crearSoloLectura(String contenido) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un objeto que sea Leible Y Escribible.
     * - leer() devuelve el contenido actual
     * - escribir(s) reemplaza el contenido
     * Empieza con el contenido inicial dado.
     */
    public static Object crearLecturaEscritura(String contenidoInicial) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un objeto que sea Leible, Escribible Y Borrable.
     * - borrar() establece el contenido a "" (string vacio)
     */
    public static Object crearCompleto(String contenidoInicial) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Si el objeto es Escribible, escribe el nuevo contenido.
     * Si no es Escribible, no hace nada.
     * Devuelve true si pudo escribir, false si no.
     */
    public static boolean intentarEscribir(Object objeto, String contenido) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
