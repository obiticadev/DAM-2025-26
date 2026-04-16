package com.masterclass.nivel2_default_static_methods;

import java.util.List;
import java.util.ArrayList;

/**
 * ============================================================================
 *  EJERCICIO 16 — EVOLUCION DE INTERFACES SIN ROMPER CODIGO (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Los defaults permiten anadir funcionalidad nueva a interfaces
 * existentes SIN romper las implementaciones que ya existen.
 * Antes de Java 8, esto era imposible.
 *
 * Imagina que tienes una interfaz Repositorio con millones de clases que la
 * implementan. Quieres anadir un metodo buscarTodos(). Con default, lo anades
 * sin que nadie tenga que modificar su codigo.
 *
 * Lee primero: teoria/02_Metodos_Default_y_Static.md
 */
public class Ejercicio16_EvolucionInterfaces {

    /**
     * Interfaz Repositorio "evolucionada": tiene metodos abstractos originales
     * Y metodos default anadidos despues. Los default usan los abstractos.
     */
    public interface Repositorio<T> {
        void guardar(T elemento);
        T buscarPorId(int id);
        List<T> obtenerTodos();

        default void guardarTodos(List<T> elementos) {
            for (T elem : elementos) {
                guardar(elem);
            }
        }

        default int contar() {
            return obtenerTodos().size();
        }

        default boolean existeId(int id) {
            return buscarPorId(id) != null;
        }
    }

    /**
     * TODO: Implementa un Repositorio<String> en memoria (usa un ArrayList interno).
     * - guardar(s): anade el String a la lista
     * - buscarPorId(id): devuelve el elemento en el indice dado, o null si fuera de rango
     * - obtenerTodos(): devuelve la lista completa
     *
     * Los metodos default (guardarTodos, contar, existeId) funcionaran solos.
     */
    public static Repositorio<String> crearRepositorioEnMemoria() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
