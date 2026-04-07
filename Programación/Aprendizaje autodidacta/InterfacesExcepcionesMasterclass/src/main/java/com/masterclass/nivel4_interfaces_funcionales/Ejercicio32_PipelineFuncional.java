package com.masterclass.nivel4_interfaces_funcionales;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * ============================================================================
 *  EJERCICIO 32 — PIPELINE FUNCIONAL COMPLETO (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Combina todo lo aprendido para crear un pipeline:
 * filtrar -> transformar -> reducir. Es la base de los Streams de Java.
 *
 * Lee primero: teoria/04_Interfaces_Funcionales.md
 */
public class Ejercicio32_PipelineFuncional {

    /**
     * TODO: Implementa un pipeline que:
     * 1. Filtre la lista con el predicado
     * 2. Transforme cada elemento con la funcion
     * 3. Devuelva la lista de resultados
     */
    public static <T, R> List<R> pipeline(List<T> lista, Predicate<T> filtro,
                                           Function<T, R> transformar) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Dado una lista de nombres (Strings):
     * 1. Filtra los que tienen longitud >= 4
     * 2. Convierte a mayusculas
     * 3. Devuelve la lista resultante
     */
    public static List<String> procesarNombres(List<String> nombres) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Dado una lista de enteros:
     * 1. Filtra los pares
     * 2. Multiplica cada uno por 3
     * 3. Devuelve la lista resultante
     */
    public static List<Integer> procesarNumeros(List<Integer> numeros) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
