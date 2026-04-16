package com.masterclass.nivel2_default_static_methods;

/**
 * ============================================================================
 *  EJERCICIO 14 — ENCADENAMIENTO CON DEFAULTS (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Los metodos default pueden devolver "this" para permitir
 * encadenamiento (fluent interface / builder pattern ligero).
 * Tambien pueden componer comportamientos usando andThen / compose.
 *
 * Lee primero: teoria/02_Metodos_Default_y_Static.md
 */
public class Ejercicio14_EncadenamientoDefaults {

    /**
     * Interfaz que transforma un String paso a paso.
     * Cada metodo default aplica una transformacion y devuelve un nuevo
     * TransformadorTexto que encadena la transformacion anterior con la nueva.
     */
    public interface TransformadorTexto {
        String aplicar(String texto);

        default TransformadorTexto yLuego(TransformadorTexto siguiente) {
            return texto -> siguiente.aplicar(this.aplicar(texto));
        }
    }

    /**
     * TODO: Crea un TransformadorTexto que convierta a mayusculas.
     */
    public static TransformadorTexto mayusculas() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un TransformadorTexto que elimine espacios (replaceAll("\\s", "")).
     */
    public static TransformadorTexto sinEspacios() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un TransformadorTexto que agregue ">>>" al inicio y "<<<" al final.
     */
    public static TransformadorTexto enmarcar() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Encadena los 3 transformadores en orden:
     * mayusculas -> sinEspacios -> enmarcar
     * y aplica el resultado al texto dado.
     *
     * Ejemplo: "hola mundo" -> "HOLAMUNDO" -> ">>>HOLAMUNDO<<<"
     */
    public static String transformarCompleto(String texto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
