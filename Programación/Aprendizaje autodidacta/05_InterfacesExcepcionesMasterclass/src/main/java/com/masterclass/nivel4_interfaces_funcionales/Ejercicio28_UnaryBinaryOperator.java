package com.masterclass.nivel4_interfaces_funcionales;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.function.BinaryOperator;

/**
 * ============================================================================
 *  EJERCICIO 28 — UNARYOPERATOR Y BINARYOPERATOR (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE:
 * - UnaryOperator<T> = Function<T,T> (mismo tipo entrada y salida)
 * - BinaryOperator<T> = BiFunction<T,T,T> (dos entradas y salida del mismo tipo)
 *
 * Lee primero: teoria/04_Interfaces_Funcionales.md
 */
public class Ejercicio28_UnaryBinaryOperator {

    /**
     * TODO: Devuelve un UnaryOperator que convierta a mayusculas.
     */
    public static UnaryOperator<String> mayusculas() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve un UnaryOperator que duplique un entero.
     */
    public static UnaryOperator<Integer> duplicar() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve un BinaryOperator que sume dos enteros.
     */
    public static BinaryOperator<Integer> sumar() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Reduce una lista a un unico valor usando el BinaryOperator y un valor inicial.
     * Ejemplo: reducir([1,2,3], sumar(), 0) = 0+1+2+3 = 6
     */
    public static <T> T reducir(List<T> lista, BinaryOperator<T> operador, T valorInicial) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Aplica un UnaryOperator a cada elemento de la lista y devuelve nueva lista.
     */
    public static <T> List<T> aplicarATodos(List<T> lista, UnaryOperator<T> operador) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
