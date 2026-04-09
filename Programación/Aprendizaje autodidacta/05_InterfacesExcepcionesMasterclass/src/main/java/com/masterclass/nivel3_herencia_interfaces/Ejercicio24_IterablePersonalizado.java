package com.masterclass.nivel3_herencia_interfaces;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ============================================================================
 *  EJERCICIO 24 — ITERABLE PERSONALIZADO (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: La interfaz Iterable<T> permite que tu clase se use en for-each:
 *   for (String s : miObjeto) { ... }
 *
 * Para ello debes implementar iterator() que devuelve un Iterator<T>.
 * Iterator<T> tiene: hasNext() y next().
 *
 * Lee primero: teoria/03_Herencia_de_Interfaces.md
 */
public class Ejercicio24_IterablePersonalizado {

    /**
     * TODO: Crea un RangoNumerico que implemente Iterable<Integer>.
     * Debe iterar desde "inicio" hasta "fin" (inclusive).
     *
     * Ejemplo: new RangoNumerico(1, 5) itera: 1, 2, 3, 4, 5
     */
    public static class RangoNumerico implements Iterable<Integer> {
        private final int inicio;
        private final int fin;

        public RangoNumerico(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        public int getInicio() { return inicio; }
        public int getFin() { return fin; }

        @Override
        public Iterator<Integer> iterator() {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }
    }

    /**
     * TODO: Suma todos los valores de un RangoNumerico usando for-each.
     */
    public static int sumarRango(RangoNumerico rango) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Cuenta cuantos elementos hay en el rango.
     */
    public static int contarElementos(RangoNumerico rango) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
