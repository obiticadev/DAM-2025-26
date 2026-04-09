package com.masterclass.nivel9_proyecto_integrador;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * ============================================================================
 *  EJERCICIO 66 — REPOSITORIO GENERICO (INTEGRADOR)
 * ============================================================================
 * Combina: generics, interfaces, Optional, excepciones.
 */
public class Ejercicio66_RepositorioGenerico {

    public static class EntidadNoEncontradaException extends RuntimeException {
        public EntidadNoEncontradaException(String id) { super("No encontrado: " + id); }
    }

    public interface Identificable {
        String getId();
    }

    public interface Repositorio<T extends Identificable> {
        void guardar(T entidad);
        Optional<T> buscarPorId(String id);
        T obtenerOLanzar(String id) throws EntidadNoEncontradaException;
        List<T> buscarTodos(Predicate<T> filtro);
        void eliminar(String id);
        int contar();
    }

    /**
     * TODO: Implementa un Repositorio en memoria usando una List interna.
     * - guardar: si ya existe (mismo id), reemplaza. Si entidad null, lanza IllegalArgumentException.
     * - buscarPorId: devuelve Optional.
     * - obtenerOLanzar: si no existe, lanza EntidadNoEncontradaException.
     * - buscarTodos: filtra por el predicado.
     * - eliminar: si no existe, lanza EntidadNoEncontradaException.
     * - contar: numero de entidades.
     */
    public static <T extends Identificable> Repositorio<T> crearRepositorio() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
