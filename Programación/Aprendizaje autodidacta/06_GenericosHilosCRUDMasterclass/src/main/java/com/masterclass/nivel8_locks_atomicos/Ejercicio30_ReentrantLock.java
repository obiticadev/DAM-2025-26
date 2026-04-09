package com.masterclass.nivel8_locks_atomicos;

/**
 * EJERCICIO 30: El Cerrojo Reentrante (ReentrantLock)
 * 
 * OBJETIVO: 
 * Abandonar `synchronized` y emular bloqueos profesionales manuales. Esto
 * protege el servidor aportando un control excepcional del inicio y fin de sección crítica.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '08_Locks_Atomicos.md' (Sección: "ReentrantLock")
 */
// TODO 1: Garantiza la inyección funcional de Hilos sobre la clase.
public class Ejercicio30_ReentrantLock {

    private int visitasServidor = 0;
    
    // TODO 2: Declara e inicializa tu cerrojo explícito asíncrono 'java.util.concurrent.locks.ReentrantLock'
    // private final ... cerrojoGlobal = ...

    // TODO 3: Implementa el marco ejecutable forzoso (run). 
    // HINT: Incrementa un for muy ancho (Ej 25.000). Pero ALTO. El body del for debe estar 
    // regido por la regla the oro del backend concurrente descrita en la teoría.
    // 1. Demanda la llave del lock explícitamente.
    // 2. Abre un bloque 'try'. Accede y suma a tus visitas.
    // 3. Implanta el bloque crítico 'finally' y purga de forma absoluta y garantizada la llave con '.unlock()'.


    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- RESOLUCIÓN LOCK EXPLÍCITO ---");
        
        // TODO 4 (PRUEBA): Implanta el modelo cruzado de dos hilos bombardeando esta misma
        // central y haz que el hilo madre aguarde a su finalización natural (con join).
        // Si todo va bien, al correr tu aplicación con Run, la RAM respetará tus incrementos perfectos.
        
        // System.out.println("Consolidación final de visitas de red tras el bombardeo: " + ...");
    }
}
