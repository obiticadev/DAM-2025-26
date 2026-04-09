package com.masterclass.nivel6_excepciones_unchecked;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 42 — ILLEGALSTATEEXCEPTION (SIN GUIA)
 * ============================================================================
 * Lee primero: teoria/06_Excepciones_Unchecked.md
 */
public class Ejercicio42_IllegalState {

    /**
     * TODO: Implementa una Pila (Stack) simple con capacidad maxima.
     * - push: si esta llena, lanza IllegalStateException("Pila llena")
     * - pop: si esta vacia, lanza IllegalStateException("Pila vacia")
     * - peek: si esta vacia, lanza IllegalStateException("Pila vacia")
     * - size: devuelve numero de elementos
     */
    public static class Pila<T> {
        private final List<T> elementos = new ArrayList<>();
        private final int capacidad;

        public Pila(int capacidad) { this.capacidad = capacidad; }

        public void push(T elemento) {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }

        public T pop() {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }

        public T peek() {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }

        public int size() {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }
    }
}
