package com.masterclass.nivel3_herencia_interfaces;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 22 — BOUNDED TYPE PARAMETERS (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Puedes restringir los genericos con extends:
 *   <T extends Comparable<T>> obliga a que T sea comparable.
 *   <T extends Serializable & Comparable<T>> obliga a que T cumpla ambas.
 *
 * Lee primero: teoria/03_Herencia_de_Interfaces.md
 */
public class Ejercicio22_BoundedGenerics {

    /**
     * Interfaz generica con restriccion: T debe ser Comparable.
     */
    public interface Ordenable<T extends Comparable<T>> {
        List<T> ordenar(List<T> elementos);
        T maximo(List<T> elementos);
        T minimo(List<T> elementos);
    }

    /**
     * TODO: Crea un Ordenable que ordene en orden ASCENDENTE (natural).
     * - ordenar: devuelve nueva lista ordenada (no modifica la original)
     * - maximo: devuelve el mayor
     * - minimo: devuelve el menor
     * Devuelve null en maximo/minimo si la lista esta vacia.
     */
    public static <T extends Comparable<T>> Ordenable<T> crearOrdenadorNatural() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un Ordenable que ordene en orden DESCENDENTE (inverso al natural).
     */
    public static <T extends Comparable<T>> Ordenable<T> crearOrdenadorInverso() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
