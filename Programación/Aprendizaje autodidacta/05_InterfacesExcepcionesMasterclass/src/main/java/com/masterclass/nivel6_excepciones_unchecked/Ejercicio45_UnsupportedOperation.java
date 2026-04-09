package com.masterclass.nivel6_excepciones_unchecked;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 45 — UNSUPPORTEDOPERATIONEXCEPTION (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/06_Excepciones_Unchecked.md
 */
public class Ejercicio45_UnsupportedOperation {

    public interface Coleccion<T> {
        void agregar(T elemento);
        T obtener(int indice);
        int tamano();
        default void eliminar(int indice) {
            throw new UnsupportedOperationException("Operacion no soportada");
        }
    }

    /**
     * TODO: Crea una Coleccion que soporte agregar, obtener y tamano.
     * eliminar() debe lanzar UnsupportedOperationException (ya lo hace el default).
     */
    public static <T> Coleccion<T> crearColeccionSoloLectura() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
