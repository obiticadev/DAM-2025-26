package com.masterclass.nivel8_try_with_resources;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 61 — TRANSACCION SIMULADA (CON GUIA)
 * ============================================================================
 */
public class Ejercicio61_TransaccionSimulada {

    public static class Transaccion implements AutoCloseable {
        private final List<String> operaciones = new ArrayList<>();
        private boolean committed = false;

        public void ejecutar(String op) { operaciones.add(op); }
        public void commit() { committed = true; operaciones.add("COMMIT"); }

        @Override
        public void close() {
            if (!committed) { operaciones.add("ROLLBACK"); }
            operaciones.add("CLOSED");
        }

        public List<String> getOperaciones() { return operaciones; }
        public boolean isCommitted() { return committed; }
    }

    /**
     * TODO: Ejecuta todas las operaciones en la transaccion.
     * Si exito es true, hace commit. Si false, no hace commit (el close hara rollback).
     * Devuelve la transaccion para inspeccionar.
     */
    public static Transaccion ejecutarTransaccion(List<String> operaciones, boolean exito) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
