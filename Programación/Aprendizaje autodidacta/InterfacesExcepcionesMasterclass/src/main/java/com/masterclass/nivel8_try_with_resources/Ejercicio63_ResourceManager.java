package com.masterclass.nivel8_try_with_resources;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 63 — RESOURCE MANAGER (CON GUIA)
 * ============================================================================
 */
public class Ejercicio63_ResourceManager {

    /**
     * TODO: Implementa un gestor que registra recursos AutoCloseable y los cierra
     * todos al cerrarse. Si alguno falla al cerrarse, captura la excepcion y continua.
     * Devuelve lista de errores al final.
     */
    public static class ResourceManager implements AutoCloseable {
        private final List<AutoCloseable> recursos = new ArrayList<>();
        private final List<String> errores = new ArrayList<>();

        public void registrar(AutoCloseable recurso) {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }

        public List<String> getErrores() { return errores; }

        @Override
        public void close() {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }
    }
}
