package colas;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * MÓDULO 4: COLAS (Queue) Y COLAS DOBLES / PILAS (Deque)
 * 
 * TEORÍA:
 * 1. Queue (Ej. PriorityQueue): Colecciones diseñadas para guardar elementos
 * ANTES de ser procesados.
 * Generalmente siguen la regla FIFO (First-In-First-Out, el primero en llegar
 * es el primero en salir).
 * 
 * 2. Deque (Ej. ArrayDeque): Es una Cola Doble ("Double Ended Queue"). Puedes
 * meter y sacar elementos
 * tanto por el inicio (Head) como por el final (Tail).
 * Por lo tanto, puede actuar como Cola (FIFO) O como Pila/Stack (LIFO - Last In
 * First Out).
 * 
 * ¿CUÁNDO USAR ARRAYDEQUE?
 * Siempre que necesites una PILA (Stack) para "Deshacer" cosas (Ctrl+Z)
 * navegadores (Atrás/Adelante)
 * o una COLA pura. Es el reemplazo moderno y mucho más rápido de la vieja clase
 * `Stack`.
 * 
 * ¿CUÁNDO USAR PRIORITYQUEUE?
 * Cuando necesitas procesar elementos NO por orden de llegada, sino por SU
 * IMPORTANCIA / PRIORIDAD
 * (usando Comparable). Ejemplo: Triaje en un hospital, la gente llega y los
 * graves pasan antes
 * aunque llegaran los últimos.
 */
public class Ejercicio07_ColasYDeques {

    public static void demostracion() {
        System.out.println("\n--- DEMOSTRACIÓN 1: ARRAYDEQUE COMO PILA (LIFO) ---");

        // Lo usamos como Pila (Stack). Meter arriba de la pila, sacar arriba de la
        // pila.
        Deque<String> historialNavegador = new ArrayDeque<>();

        // push() mete un valor al PRINCIPIO de la pila
        historialNavegador.push("google.com");
        historialNavegador.push("youtube.com/java-tutorial");
        historialNavegador.push("stackoverflow.com/error-java");

        System.out.println("Historial actual (el más reciente está arriba/primero): " + historialNavegador);

        // pop() saca (y borra) el valor del principio
        String botonAtras = historialNavegador.pop();
        System.out.println("He pulsado ATRÁS volviendo de: " + botonAtras);
        System.out.println("Ahora visito: " + historialNavegador.peek()); // peek() mira el primero SIN sacarlo

        System.out.println("\n--- DEMOSTRACIÓN 2: PRIORITYQUEUE (PRIORIDAD) ---");

        // Para que funciones con String alfabéticamente se usará el compareTo
        // automático (A va antes que Z).
        // Si fuera Integers, el 1 antes que 10.
        Queue<String> salaEmergencias = new PriorityQueue<>();

        // Ojo, vamos a meter elementos en distinto orden. PriorityQueue siempre sacará
        // el alfabéticamente menor
        salaEmergencias.add("Z - Paciente con tos");
        salaEmergencias.add("A - Paciente infartado");
        salaEmergencias.add("H - Paciente con rotura brazo");

        System.out.println("1. Pacientes en la sala. ¡Ojo! El System.out de una PriorityQueue no garantiza");
        System.out.println("enseñarlos ordenados, SOLO garantiza sacarlos ordenados uno a uno.");
        System.out.println(salaEmergencias);

        System.out.println("2. El doctor llama al siguiente (poll()). Fíjate quién es aunque entrara el segundo:");
        System.out.println("-> Entra a consulta: " + salaEmergencias.poll());
        System.out.println("-> Siguiente listo: " + salaEmergencias.poll());
        System.out.println("--------------------------------\n");
    }

    /**
     * EJERCICIO:
     * Tienes que crear una Pila de platos vacíos para fregar usando ArrayDeque.
     * Añade 3 platos manchados y saca el primero para fregar.
     */
    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 7: ARRAYDEQUE (Stack) ---");

        // TODO: Crea un Deque de Strings llamado 'pilaPlatos' inicializado como un
        // ArrayDeque.
        Deque<String> pilaPlatos = null;

        // TODO: Añade 3 platos a la pila usando push() (recuerda usar push() si lo
        // simulamos como Pila/LIFO).
        // Platos: "Plato hondo de sopa", "Plato llano macarrones", "Plato de postre
        // tarta".

        // TODO: Coge el plato superior sacándolo de la pila (usando pop()) y guárdalo
        // en la String 'fregando'.
        String fregando = "";

        // --- CÓDIGO DE COMPROBACIÓN (NO MODIFICAR) ---
        if (pilaPlatos != null && pilaPlatos.size() == 2 && "Plato de postre tarta".equals(fregando)) {
            System.out.println(">> ¡CORRECTO! Has entendido cómo funciona el modo Pila (LIFO).\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> ALGO FALLA. \033[0;31m [ERROR]\033[0m Revisa si usaste `push()` y `pop()`. En LIFO el último metido debe ser el primero sacado.");
            System.out.println("Te ha tocado limpiar primero: " + fregando);
        }
        System.out.println("-------------------------------\n");
    }
}
