package com.masterclass.nivel8_try_with_resources;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 59 — AUTOCLOSEABLE CUSTOM (CON GUIA)
 * ============================================================================
 */
public class Ejercicio59_CustomAutoCloseable {

    /**
     * TODO: Implementa un Temporizador que registra el tiempo transcurrido al cerrarse.
     * - Al crear, registra System.nanoTime() como inicio.
     * - close() calcula duracion = nanoTime - inicio y la guarda.
     * - getDuracionNanos() devuelve la duracion (0 si no se ha cerrado).
     */
    public static class Temporizador implements AutoCloseable {
        private final long inicio;
        private long duracionNanos = 0;

        public Temporizador() { this.inicio = System.nanoTime(); }

        @Override
        public void close() {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }

        public long getDuracionNanos() { return duracionNanos; }
    }

    /**
     * TODO: Implementa un Pool de objetos simple con AutoCloseable.
     * - Constructor recibe capacidad y crea esa cantidad de Strings "Obj-0", "Obj-1", etc.
     * - adquirir() toma un objeto del pool. Si vacio, lanza IllegalStateException.
     * - devolver(obj) lo devuelve al pool.
     * - close() limpia el pool.
     */
    public static class Pool implements AutoCloseable {
        private final List<String> disponibles = new ArrayList<>();

        public Pool(int capacidad) {
            for (int i = 0; i < capacidad; i++) disponibles.add("Obj-" + i);
        }

        public String adquirir() {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }

        public void devolver(String obj) {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }

        public int disponibles() { return disponibles.size(); }

        @Override
        public void close() {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }
    }
}
