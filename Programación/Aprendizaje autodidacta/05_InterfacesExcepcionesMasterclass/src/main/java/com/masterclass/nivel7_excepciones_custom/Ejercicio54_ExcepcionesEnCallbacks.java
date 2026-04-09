package com.masterclass.nivel7_excepciones_custom;

import java.util.List;
import java.util.function.Consumer;

/**
 * ============================================================================
 *  EJERCICIO 54 — EXCEPCIONES EN CALLBACKS (SIN GUIA)
 * ============================================================================
 */
public class Ejercicio54_ExcepcionesEnCallbacks {

    public static class CallbackException extends RuntimeException {
        public CallbackException(String msg, Throwable causa) { super(msg, causa); }
    }

    /**
     * TODO: Ejecuta accion sobre cada elemento. Si alguno lanza excepcion,
     * envuelvela en CallbackException y relanzala.
     */
    public static <T> void ejecutarConCallback(List<T> elementos, Consumer<T> accion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Igual pero ignora excepciones individuales y continua procesando.
     * Devuelve el numero de elementos procesados exitosamente.
     */
    public static <T> int ejecutarIgnorandoErrores(List<T> elementos, Consumer<T> accion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
