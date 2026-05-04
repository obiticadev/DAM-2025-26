package com.masterclass.collections.nivel03_linkedlist;

import java.util.LinkedList;

/**
 * EJERCICIO 07 — LinkedList como Deque (cola doble)
 * ==================================================
 * Teoría de referencia: teoria/02_LinkedList_Estructura_y_Deque.md  (§ 3 — LinkedList como Deque)
 *
 * Objetivo: Usar LinkedList a través de la interfaz Deque para operar
 * en ambos extremos de la colección con coste O(1).
 *
 * Restricción: Para los métodos que dicen "usa X", debes usar exactamente ese método
 * de la API Deque, no la alternativa de List (ej: no usar remove(0) en lugar de pollFirst).
 */
public class Ejercicio07_LinkedListComoDeque {

    // TODO 1: Implementa `crearDeque`.
    //   - Recibe un varargs de String y retorna un LinkedList<String> con esos elementos.
    //   - Los elementos deben quedar en el mismo orden que el varargs (el primero queda al frente).
    //   - Usa addLast() para insertar cada elemento en orden.
    public static LinkedList<String> crearDeque(String... elementos) {
        return null;
    }

    // TODO 2: Implementa `insertarAlFrente`.
    //   - Inserta `elemento` al principio de la deque (posición 0).
    //   - Usa addFirst().
    public static void insertarAlFrente(LinkedList<String> deque, String elemento) {
        // implementa aquí
    }

    // TODO 3: Implementa `insertarAlFinal`.
    //   - Inserta `elemento` al final de la deque.
    //   - Usa addLast().
    public static void insertarAlFinal(LinkedList<String> deque, String elemento) {
        // implementa aquí
    }

    // TODO 4: Implementa `consultarFrente`.
    //   - Retorna el elemento del frente SIN extraerlo.
    //   - Usa peekFirst() (retorna null si está vacía, no lanza excepción).
    public static String consultarFrente(LinkedList<String> deque) {
        return null;
    }

    // TODO 5: Implementa `consultarFinal`.
    //   - Retorna el elemento del final SIN extraerlo.
    //   - Usa peekLast().
    public static String consultarFinal(LinkedList<String> deque) {
        return null;
    }

    // TODO 6: Implementa `extraerDelFrente`.
    //   - Elimina y retorna el elemento del frente.
    //   - Usa pollFirst() (retorna null si está vacía).
    public static String extraerDelFrente(LinkedList<String> deque) {
        return null;
    }

    // TODO 7: Implementa `extraerDelFinal`.
    //   - Elimina y retorna el elemento del final.
    //   - Usa pollLast().
    public static String extraerDelFinal(LinkedList<String> deque) {
        return null;
    }

    // TODO 8 (desafío): Implementa `invertirConDeque`.
    //   - Recibe un LinkedList<String> y retorna un NUEVO LinkedList<String>
    //     con el orden invertido, usando SOLO operaciones Deque (addFirst/pollLast).
    //   - No usar Collections.reverse() ni acceso por índice.
    //   - Algoritmo: vaciando con pollLast() e insertando con addLast() en la nueva deque.
    public static LinkedList<String> invertirConDeque(LinkedList<String> original) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run para comprobar visualmente
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 07 — LinkedList como Deque ===\n");

        LinkedList<String> deque = crearDeque("B", "C", "D");
        System.out.println("Deque inicial: " + deque);

        insertarAlFrente(deque, "A");
        System.out.println("Tras insertarAlFrente('A'): " + deque);

        insertarAlFinal(deque, "E");
        System.out.println("Tras insertarAlFinal('E'): " + deque);

        System.out.println("\nConsultar frente (sin extraer): " + consultarFrente(deque));
        System.out.println("Consultar final  (sin extraer): " + consultarFinal(deque));
        System.out.println("Deque sin cambios: " + deque);

        String primerExtraido = extraerDelFrente(deque);
        System.out.println("\nExtraído del frente: " + primerExtraido);
        System.out.println("Deque tras extraer frente: " + deque);

        String ultimoExtraido = extraerDelFinal(deque);
        System.out.println("Extraído del final: " + ultimoExtraido);
        System.out.println("Deque tras extraer final: " + deque);

        // -- invertirConDeque --
        LinkedList<String> original = crearDeque("1", "2", "3", "4", "5");
        LinkedList<String> invertida = invertirConDeque(original);
        System.out.println("\nOriginal: " + original);
        System.out.println("Invertida: " + invertida);
    }
}
