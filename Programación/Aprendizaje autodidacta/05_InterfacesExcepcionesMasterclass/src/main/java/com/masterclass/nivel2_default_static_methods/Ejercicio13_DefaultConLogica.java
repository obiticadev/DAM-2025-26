package com.masterclass.nivel2_default_static_methods;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 13 — DEFAULT QUE DELEGA EN ABSTRACTO (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Un patron muy potente es que los metodos default USEN
 * los metodos abstractos internamente. Asi, quien implementa la interfaz
 * solo define el "nucleo" y obtiene funcionalidad extra gratis.
 *
 * Esto es el "Template Method Pattern" aplicado a interfaces:
 * el default define el esqueleto del algoritmo y delega pasos al abstracto.
 *
 * Lee primero: teoria/02_Metodos_Default_y_Static.md
 */
public class Ejercicio13_DefaultConLogica {

    /**
     * Interfaz procesador de texto con defaults que delegan en transformar().
     */
    public interface ProcesadorTexto {
        String transformar(String texto);

        default String procesarLista(List<String> textos) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < textos.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(transformar(textos.get(i)));
            }
            return sb.toString();
        }

        default String procesarConIndice(List<String> textos) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < textos.size(); i++) {
                sb.append((i + 1)).append(". ").append(transformar(textos.get(i)));
                if (i < textos.size() - 1) sb.append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * TODO: Crea un ProcesadorTexto que convierta texto a mayusculas.
     * procesarLista y procesarConIndice funcionaran automaticamente.
     */
    public static ProcesadorTexto crearProcesadorMayusculas() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un ProcesadorTexto que rodee texto con asteriscos: "*texto*".
     */
    public static ProcesadorTexto crearProcesadorEnfasis() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un ProcesadorTexto que devuelva solo la primera letra de cada texto.
     * Ejemplo: "Hola" -> "H"
     * Si el texto esta vacio, devuelve "".
     */
    public static ProcesadorTexto crearProcesadorInicial() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
