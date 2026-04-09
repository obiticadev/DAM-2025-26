package com.masterclass.nivel9_ejecutores;

/**
 * EJERCICIO 34: El Patrón FixedThreadPool (Servidor Robusto)
 * 
 * OBJETIVO: 
 * Evidenciar matemáticamente de forma visual la inmensa escalabilidad de una 
 * arquitectura de hilo-reutilizable inyectando una horda de tareas sobre una piscina acotada.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '09_Ejecutores_Pools.md' (Sección: "Thread Pool Diagram")
 */
// TODO 1: Garantiza el tipo ejecutable para inyección.
public class Ejercicio34_FixedThreadPool {

    private final int idTarea;
    public Ejercicio34_FixedThreadPool(int id) { this.idTarea = id; }

    // TODO 2: Redacta la fase run inyectando primero 200 ms de sleep simulando red.
    // LUEGO, imprime: "Procesado " + idTarea + " | Hilo encargo: " + Thread.currentThread().getName()
    

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PRUEBA ESTRÉS FIXED POOL ---");
        
        // TODO 3: Pídele a la factoría de Executors una fábrica estática restringida ('newFixedThreadPool')
        // Inyecta el parámetro de creación para indicarle que tendrá 4 threads en todo momento.

        // TODO 4: Utilizando un ciclo For del 1 al 100, genera inyeciones (submits) 
        // pasándole al executor tus cargas (new Ejercicio34_FixedThreadPool(iterador)).

        // TODO 5: Dispara el requerimiento lógico abstracto de cierre maestro ('shutdown').
        
        // TODO 6 (PRUEBA): Arranca (Run). Fíjate exahustivamente en el nombre del hilo en consola.
        // Verás "pool-1-thread-1", "pool-1-thread-2", etc. ¡Nunca habrá un hilo 5! Los 4 hilos están haciendo
        // las 100 misiones reusando su RAM. Patrón backend consolidado con éxito total.
    }
}
