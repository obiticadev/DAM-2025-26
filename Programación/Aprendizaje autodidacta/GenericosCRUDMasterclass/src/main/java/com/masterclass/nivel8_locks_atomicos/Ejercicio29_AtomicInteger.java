package com.masterclass.nivel8_locks_atomicos;

/**
 * EJERCICIO 29: El poder del Compare-And-Swap (Atomicidad Física)
 * 
 * OBJETIVO: 
 * Descubrir cómo el hardware puro de la CPU nos permite garantizar la estricta métrica
 * de las variables primitivas sin utilizar pesadas herramientas lógicas de sincronización de software.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '08_Locks_Atomicos.md' (Sección: "Operaciones Atómicas")
 */
// TODO 1: Configura la clase inyectando la rúbrica ejecutora para su consumo por Hilos.
public class Ejercicio29_AtomicInteger {

    // TODO 2: Transmuta la variable int base por la clase contenedora thread-safe
    // perteneciente al paquete utilitario de concurrencia nativa 'java.util.concurrent.atomic'.
    // Iníciala con el primitivo base en cero.
    private int stockInventario = 0;

    // TODO 3: Implementa la aserción abstracta que te demanda rellenar la JVM.
    // HINT: En su interior vas a iterar iterativamente (for) unas 20,000 muescas.
    // Ojo: En vez de un peligroso `var++`, emplea el método de la variable Atómica que
    // incrementa y accede mediante micro-pulsos asegurando latencia 0 en choques (`incrementAndGet`).

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- RESOLUCIÓN CAS: ATOMICIDAD ---");
        
        // TODO 4: Inicializa la clase arquitectónica principal que rige nuestro objeto.
        // TODO 5: Prepara dos Hilos idénticos que la envuelvan.
        
        // TODO 6: Arranca ambos hilos para generar la colisión intencionada.
        // TODO 7: Detén la manguera y haz que absorba la espera asíncrona de los dos threads.
        
        // TODO 8 (PRUEBA): Evalúa la variable atómica para leer sin temor a corrupciones. Comprueba con VSC su consistencia.
        // System.out.println("El total absoluto sin candados restrictivos es: " + objeto.stockInventario.get());
    }
}
