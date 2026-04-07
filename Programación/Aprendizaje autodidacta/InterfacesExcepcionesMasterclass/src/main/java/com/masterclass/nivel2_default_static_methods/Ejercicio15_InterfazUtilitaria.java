package com.masterclass.nivel2_default_static_methods;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 15 — INTERFAZ UTILITARIA: STATIC + DEFAULT (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Una interfaz puede combinar metodos static (factories)
 * con metodos default (comportamiento heredable) para crear una API completa
 * y autocontenida.
 *
 * Los static crean instancias. Los default dan funcionalidad gratis.
 * Es como una "mini-libreria" dentro de una interfaz.
 *
 * Lee primero: teoria/02_Metodos_Default_y_Static.md
 */
public class Ejercicio15_InterfazUtilitaria {

    /**
     * Interfaz Comparador que combina static factories con defaults.
     */
    public interface Comparador<T> {
        int comparar(T a, T b);

        default boolean esMayor(T a, T b) {
            return comparar(a, b) > 0;
        }

        default boolean esMenor(T a, T b) {
            return comparar(a, b) < 0;
        }

        default boolean sonIguales(T a, T b) {
            return comparar(a, b) == 0;
        }

        default T maximo(T a, T b) {
            return esMayor(a, b) ? a : b;
        }

        default T minimo(T a, T b) {
            return esMenor(a, b) ? a : b;
        }
    }

    /**
     * TODO: Crea un Comparador<Integer> que compare enteros naturalmente
     * (el mayor tiene valor mas alto).
     */
    public static Comparador<Integer> comparadorEnteros() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un Comparador<String> que compare por longitud de string.
     * El string mas largo es "mayor".
     */
    public static Comparador<String> comparadorPorLongitud() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Dado un Comparador y una lista, devuelve el elemento maximo.
     * Usa el metodo default maximo() del Comparador iterando la lista.
     * Devuelve null si la lista esta vacia.
     */
    public static <T> T encontrarMaximo(List<T> lista, Comparador<T> comparador) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
