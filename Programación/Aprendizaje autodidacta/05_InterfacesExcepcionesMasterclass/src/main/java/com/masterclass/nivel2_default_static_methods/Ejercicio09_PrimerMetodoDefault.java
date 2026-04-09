package com.masterclass.nivel2_default_static_methods;

/**
 * ============================================================================
 *  EJERCICIO 09 — PRIMER METODO DEFAULT (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Un metodo DEFAULT en una interfaz tiene implementacion.
 * Las clases que implementen la interfaz lo HEREDAN gratis, sin necesidad
 * de sobreescribirlo. Fue introducido en Java 8 para poder evolucionar
 * interfaces sin romper implementaciones existentes.
 *
 *   public interface Logger {
 *       void log(String msg);                          // Abstracto
 *       default void logError(String msg) {            // Default (tiene cuerpo)
 *           log("[ERROR] " + msg);                     // Usa el abstracto
 *       }
 *   }
 *
 * Quien implemente Logger solo necesita definir log(). logError() viene gratis.
 *
 * Lee primero: teoria/02_Metodos_Default_y_Static.md
 */
public class Ejercicio09_PrimerMetodoDefault {

    /**
     * Interfaz con un metodo abstracto y dos metodos default.
     * Los default USAN el metodo abstracto internamente.
     */
    public interface Formateador {
        String transformar(String texto);

        default String transformarConPrefijo(String texto, String prefijo) {
            return prefijo + ": " + transformar(texto);
        }

        default String transformarYRecortar(String texto, int maxLong) {
            String resultado = transformar(texto);
            return resultado.length() > maxLong
                    ? resultado.substring(0, maxLong) + "..."
                    : resultado;
        }
    }

    /**
     * TODO: Crea un Formateador que convierta el texto a MAYUSCULAS.
     * Los metodos default (transformarConPrefijo y transformarYRecortar)
     * funcionaran automaticamente sin que tengas que tocarlos.
     */
    public static Formateador crearFormateadorMayusculas() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un Formateador que envuelva el texto entre corchetes: "[texto]".
     */
    public static Formateador crearFormateadorCorchetes() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
