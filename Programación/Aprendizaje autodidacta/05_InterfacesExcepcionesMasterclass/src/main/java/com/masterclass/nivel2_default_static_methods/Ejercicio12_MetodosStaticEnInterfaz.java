package com.masterclass.nivel2_default_static_methods;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 12 — METODOS STATIC EN INTERFACES (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Las interfaces pueden tener metodos STATIC (desde Java 8).
 * Son metodos utilitarios que pertenecen a la interfaz, no a las instancias.
 * Se llaman como: NombreInterfaz.metodo()
 *
 * NO se heredan ni se sobreescriben. Son como metodos static de una clase.
 * Uso tipico: metodos factory, validadores, utilidades.
 *
 * Lee primero: teoria/02_Metodos_Default_y_Static.md
 */
public class Ejercicio12_MetodosStaticEnInterfaz {

    /**
     * Interfaz Validador con metodos static que crean validadores predefinidos.
     */
    public interface Validador {
        boolean esValido(String dato);

        static Validador noVacio() {
            return dato -> dato != null && !dato.isEmpty();
        }

        static Validador longitudMinima(int min) {
            return dato -> dato != null && dato.length() >= min;
        }

        static Validador soloLetras() {
            return dato -> dato != null && dato.matches("[a-zA-Z]+");
        }

        static Validador contiene(String subcadena) {
            return dato -> dato != null && dato.contains(subcadena);
        }
    }

    /**
     * TODO: Usa Validador.noVacio() para comprobar si un dato no esta vacio.
     */
    public static boolean validarNoVacio(String dato) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Usa Validador.longitudMinima(min) para comprobar si el dato cumple.
     */
    public static boolean validarLongitudMinima(String dato, int min) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Valida que un dato cumpla TODAS las condiciones:
     * no vacio, longitud minima 3, y solo letras.
     * Retorna true solo si las tres se cumplen.
     */
    public static boolean validarCompleto(String dato) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Filtra una lista de Strings devolviendo solo las que cumplen
     * el validador dado. Usa streams.
     */
    public static List<String> filtrarValidos(List<String> datos, Validador validador) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
