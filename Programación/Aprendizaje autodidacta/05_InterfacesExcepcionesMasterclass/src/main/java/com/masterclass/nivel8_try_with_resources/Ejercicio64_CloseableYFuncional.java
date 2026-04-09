package com.masterclass.nivel8_try_with_resources;

import java.util.function.Function;

/**
 * ============================================================================
 *  EJERCICIO 64 — CLOSEABLE Y FUNCIONAL (SIN GUIA)
 * ============================================================================
 */
public class Ejercicio64_CloseableYFuncional {

    /**
     * TODO: Ejecuta la funcion con el recurso y garantiza que se cierra despues.
     * Si la funcion o el close lanzan, propaga la excepcion.
     */
    public static <R extends AutoCloseable, T> T usarRecurso(R recurso, Function<R, T> funcion) throws Exception {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
