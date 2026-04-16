package com.masterclass.nivel4_interfaces_funcionales;

import java.util.List;
import java.util.function.Predicate;

/**
 * ============================================================================
 *  EJERCICIO 25 — PREDICATE<T> (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Predicate<T> es una interfaz funcional que recibe un T
 * y devuelve boolean. Es el "filtro" por excelencia.
 *
 *   Predicate<String> esLargo = s -> s.length() > 5;
 *   esLargo.test("Hola");  // false
 *   esLargo.test("Hola Mundo"); // true
 *
 * Metodos utiles: and(), or(), negate()
 *
 * Lee primero: teoria/04_Interfaces_Funcionales.md
 */
public class Ejercicio25_PredicateBasico {

    /**
     * TODO: Devuelve un Predicate que comprueba si un numero es par.
     */
    public static Predicate<Integer> esPar() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve un Predicate que comprueba si un String tiene longitud > min.
     */
    public static Predicate<String> longitudMayorQue(int min) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Combina dos predicates con AND usando .and()
     */
    public static <T> Predicate<T> combinarAnd(Predicate<T> p1, Predicate<T> p2) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Filtra una lista devolviendo solo los elementos que cumplen el predicado.
     */
    public static <T> List<T> filtrar(List<T> lista, Predicate<T> predicado) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve un Predicate que es la NEGACION del predicado dado.
     */
    public static <T> Predicate<T> negar(Predicate<T> predicado) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
