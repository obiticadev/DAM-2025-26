package listas;

import java.util.LinkedList;

/**
 * MÓDULO 1.2: LINKEDLIST (Implementación de las interfaces List y Deque)
 * 
 * TEORÍA:
 * LinkedList es una lista doblemente enlazada. Cada elemento tiene una conexión
 * con el anterior y el posterior (nodos sueltos en memoria, no todo junto en un
 * bloque).
 * 
 * PROS:
 * - Insertar o borrar un elemento al principio o en el medio es MUY RÁPIDO
 * (O(1) si ya estamos ahí),
 * porque solo hay que reconectar punteros, no hay que mover 1000 elementos como
 * en un ArrayList.
 * 
 * CONTRAS:
 * - Acceder a un elemento al azar es MUY LENTO (O(n)). Para hacer
 * lista.get(50),
 * la LinkedList empieza desde el inicio y debe recorrer nodo tras nodo hasta
 * llegar al 50.
 * - Consume más memoria (cada nodo guarda el dato, el nodo anterior y nodo
 * siguiente).
 * 
 * ¿CUÁNDO USARLO?
 * Solo si sabes que tu aplicación tratará principalmente de hacer MUCHAS
 * INSERCIONES y
 * ELIMINACIONES que no sean al final de la lista de un tamaño considerable y
 * NUNCA usarás accesos aleatorios con índices (.get(i)).
 */
public class Ejercicio02_LinkedList {

    public static void demostracion() {
        System.out.println("\n--- DEMOSTRACIÓN: LINKEDLIST ---");

        // Podemos declararlo como List, o si queremos usar funciones exclusivas de
        // Deque/LinkedList,
        // (como addFirst y addLast), lo inicializamos directamente como LinkedList (o
        // su interfaz Queue/Deque)
        LinkedList<String> trenes = new LinkedList<>();

        System.out.println("1. Entran trenes por orden:");
        trenes.add("Tren Talgo (Centro)");
        trenes.add("Tren Cercanías (Sur)");
        System.out.println(trenes);

        System.out.println("2. Metemos un tren AVE directamente como cabeza/principio (O(1)):");
        trenes.addFirst("AVE (Madrid-Barcelona)"); // <-- Imposible de hacer ágil en un ArrayList
        System.out.println(trenes);

        System.out.println("3. Y uno nuevo expreso al final:");
        trenes.addLast("Expreso Nocturno");

        System.out.println("Lista de trenes actual:\n" + trenes);
        System.out.println("Despachando el primer tren: " + trenes.removeFirst()); // Extrae el primero borrándolo
        System.out.println("Despachando el último: " + trenes.removeLast());
        System.out.println("--------------------------------\n");
    }

    /**
     * EJERCICIO:
     * Tienes una LinkedList de números enteros que simulan tickets de espera en un
     * sistema.
     * Completa el proceso simulando las entradas y salidas de este sistema estilo
     * "cola pesada".
     */
    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 2: LINKEDLIST ---");

        LinkedList<Integer> tickets = new LinkedList<>();
        tickets.add(101);
        tickets.add(102);

        // TODO: Acaba de llegar un cliente VIP, añádele ticket 99 para que sea el
        // primero usando un método exclusivo de LinkedList.

        // TODO: Ha quedado un cliente rezagado al final. Añádele ticket 103 al final.

        // TODO: Simula que atienes al primer cliente (saca el elemento del principio).
        // Guarda el resultado en 'atendido'.
        // Recuerda que el método para sacar el primero y además borrarlo es
        // removeFirst() o poll().
        int atendido = 0;

        // --- CÓDIGO DE COMPROBACIÓN (NO MODIFICAR) ---
        if (tickets.size() == 3 && atendido == 99 && tickets.getFirst() == 101 && tickets.getLast() == 103) {
            System.out.println(">> ¡CORRECTO! LinkedList dominada.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> ALGO FALLA. \033[0;31m [ERROR]\033[0m Comprueba si añadiste o retiraste al principio o al final adecuadamente.");
            System.out.println("Tickets restantes: " + tickets);
            System.out.println("Atendido resultó ser: " + atendido);
        }
        System.out.println("-------------------------------\n");
    }
}
