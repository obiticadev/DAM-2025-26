package com.masterclass.nivel4_interfaces_funcionales;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * ============================================================================
 *  EJERCICIO 27 — CONSUMER<T> Y SUPPLIER<T> (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE:
 * - Consumer<T>: recibe un T, no devuelve nada (void). Usa accept().
 *   Consumer<String> imprimir = s -> System.out.println(s);
 *
 * - Supplier<T>: no recibe nada, devuelve un T. Usa get().
 *   Supplier<Double> random = () -> Math.random();
 *
 * Lee primero: teoria/04_Interfaces_Funcionales.md
 */
public class Ejercicio27_ConsumerYSupplier {

    /**
     * TODO: Devuelve un Consumer que anade cada elemento a la lista dada.
     */
    public static <T> Consumer<T> crearColector(List<T> destino) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Aplica el Consumer a cada elemento de la lista origen.
     */
    public static <T> void aplicarATodos(List<T> origen, Consumer<T> accion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve un Supplier que genera numeros secuenciales empezando en inicio.
     * Cada llamada a get() devuelve el siguiente numero.
     * Ejemplo: si inicio=1, get()=1, get()=2, get()=3...
     */
    public static Supplier<Integer> crearContador(int inicio) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Usa el Supplier para generar n elementos y devolverlos en una lista.
     */
    public static <T> List<T> generarLista(Supplier<T> supplier, int n) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
