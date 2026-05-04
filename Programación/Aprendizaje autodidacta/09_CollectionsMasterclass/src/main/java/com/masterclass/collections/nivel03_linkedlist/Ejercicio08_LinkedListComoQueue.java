package com.masterclass.collections.nivel03_linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * EJERCICIO 08 — LinkedList como Queue (cola FIFO)
 * ==================================================
 * Teoría de referencia: teoria/02_LinkedList_Estructura_y_Deque.md  (§ 4 — LinkedList como Queue)
 *
 * Objetivo: Usar LinkedList como cola FIFO usando los métodos de Queue:
 * offer(), poll(), peek(). Entender la diferencia con los equivalentes
 * que lanzan excepción (add/remove/element).
 *
 * Restricción: Para la simulación de cola usa SOLO los métodos de Queue
 * (offer, poll, peek). NO uses addFirst/addLast/removeFirst directamente.
 */
public class Ejercicio08_LinkedListComoQueue {

    // TODO 1: Implementa `encolar`.
    //   - Añade `elemento` al final de la cola usando offer().
    //   - offer() retorna true si tiene éxito (en LinkedList siempre es true).
    public static void encolar(LinkedList<String> cola, String elemento) {
        // implementa aquí
    }

    // TODO 2: Implementa `desencolar`.
    //   - Extrae y retorna el elemento del frente de la cola usando poll().
    //   - poll() retorna null si la cola está vacía (no lanza excepción).
    public static String desencolar(LinkedList<String> cola) {
        return null;
    }

    // TODO 3: Implementa `consultarFrente`.
    //   - Retorna el elemento del frente SIN extraerlo usando peek().
    //   - Retorna null si la cola está vacía.
    public static String consultarFrente(LinkedList<String> cola) {
        return null;
    }

    // TODO 4: Implementa `procesarTodos`.
    //   - Vacía la cola desencol ando todos sus elementos con poll().
    //   - Retorna un ArrayList<String> con los elementos en el orden en que fueron extraídos.
    //   - Tras este método, la cola debe quedar vacía.
    public static ArrayList<String> procesarTodos(LinkedList<String> cola) {
        return null;
    }

    // TODO 5: Implementa `transferir`.
    //   - Transfiere todos los elementos de `origen` a `destino` en el mismo orden FIFO.
    //   - Desencola de `origen` con poll() y encola en `destino` con offer().
    //   - Al terminar: `origen` vacía, `destino` con todos los elementos en el mismo orden.
    public static void transferir(LinkedList<String> origen, LinkedList<String> destino) {
        // implementa aquí
    }

    // TODO 6 (desafío): Implementa `simularServidor`.
    //   - Recibe una LinkedList<String> de peticiones (nombres de clientes).
    //   - Procesa las peticiones de dos en dos: imprime cada pareja como:
    //     "Procesando: [cliente1] y [cliente2]"
    //   - Si queda una sola petición al final (impar), imprímela como:
    //     "Procesando: [cliente] (último)"
    //   - Retorna el número total de impresiones realizadas (rondas de procesamiento).
    //   - La cola debe quedar vacía al terminar.
    public static int simularServidor(LinkedList<String> peticiones) {
        return -1;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run para comprobar visualmente
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 08 — LinkedList como Queue ===\n");

        LinkedList<String> cola = new LinkedList<>();

        // -- encolar --
        encolar(cola, "Cliente-A");
        encolar(cola, "Cliente-B");
        encolar(cola, "Cliente-C");
        System.out.println("Cola tras encolar 3 clientes: " + cola);

        // -- consultarFrente --
        System.out.println("Frente (sin extraer): " + consultarFrente(cola));

        // -- desencolar --
        System.out.println("Desencolar: " + desencolar(cola));
        System.out.println("Cola tras desencolar: " + cola);

        // -- procesarTodos --
        LinkedList<String> cola2 = new LinkedList<>();
        encolar(cola2, "P1");
        encolar(cola2, "P2");
        encolar(cola2, "P3");
        ArrayList<String> procesados = procesarTodos(cola2);
        System.out.println("\nProcesados: " + procesados);
        System.out.println("Cola vacía tras procesarTodos: " + cola2.isEmpty());

        // -- transferir --
        LinkedList<String> origen = new LinkedList<>();
        LinkedList<String> destino = new LinkedList<>();
        origen.offer("X");
        origen.offer("Y");
        origen.offer("Z");
        transferir(origen, destino);
        System.out.println("\nOrigen (vacía): " + origen);
        System.out.println("Destino: " + destino);

        // -- simularServidor --
        LinkedList<String> peticiones = new LinkedList<>();
        for (String c : new String[]{"Ana", "Luis", "Marta", "Pedro", "Eva"}) {
            peticiones.offer(c);
        }
        System.out.println("\n--- Simulación de servidor ---");
        int rondas = simularServidor(peticiones);
        System.out.println("Total de rondas: " + rondas);
        System.out.println("Cola vacía: " + peticiones.isEmpty());
    }
}
