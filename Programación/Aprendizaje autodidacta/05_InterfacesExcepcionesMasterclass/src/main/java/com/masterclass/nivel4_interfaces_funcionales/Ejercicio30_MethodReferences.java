package com.masterclass.nivel4_interfaces_funcionales;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * ============================================================================
 *  EJERCICIO 30 — METHOD REFERENCES (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Method references son atajos para lambdas:
 *   Clase::metodoStatic     ->  (x) -> Clase.metodoStatic(x)
 *   objeto::metodoInstancia ->  (x) -> objeto.metodoInstancia(x)
 *   Clase::metodoInstancia  ->  (x) -> x.metodoInstancia()
 *   Clase::new              ->  (x) -> new Clase(x)
 *
 * Lee primero: teoria/04_Interfaces_Funcionales.md
 */
public class Ejercicio30_MethodReferences {

    /**
     * TODO: Usa String::toUpperCase como method reference para crear una Function.
     */
    public static Function<String, String> aMayusculas() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Usa String::isEmpty como method reference para crear un Predicate.
     */
    public static Predicate<String> esVacio() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Usa Integer::parseInt como method reference para crear una Function.
     */
    public static Function<String, Integer> aEntero() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Filtra una lista de Strings eliminando los vacios usando esVacio().negate().
     * Luego convierte a mayusculas. Devuelve la lista resultante.
     */
    public static List<String> filtrarYMayusculas(List<String> lista) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
