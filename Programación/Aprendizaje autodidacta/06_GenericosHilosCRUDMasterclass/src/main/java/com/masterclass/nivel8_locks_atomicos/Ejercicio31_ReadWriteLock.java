package com.masterclass.nivel8_locks_atomicos;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * EJERCICIO 31: Múltiples Lectores, Un Escritor (ReadWriteLock)
 * 
 * OBJETIVO: 
 * Desatascar el acceso general a variables seguras en escenarios de solo-lectura, aislando el estrangulamiento
 * general exclusivamente a la fase mutadora de los datos.
 */
public class Ejercicio31_ReadWriteLock {

    private String estadoConfiguracion = "BOOTING";
    
    // TODO 1: Observa esta instanciación de un candado bidireccional.
    // Desgrana por qué es excepcionalmente potente tener llaves distribuidas en tu memoria.
    private final ReadWriteLock cerrojoRW = new ReentrantReadWriteLock();

    // TODO 2: Fija la lógica del método leyendo asumiendo seguridad compartida:
    // Pide estricta garantía asíncrona pero apúntala al 'readLock()'. Recuerda aplicarlo mediante un try-finally.
    public void leerEstado() {
        // ... pide cerrojo LECTOR ...
        try {
            System.out.println(Thread.currentThread().getName() + " procesando estado puro -> " + estadoConfiguracion);
            Thread.sleep(100); // Simulando retardo analitico
        } catch (Exception e) {} 
        finally {
            // ... expulsa cerrojo LECTOR ...
        }
    }

    // TODO 3: Implementa el método inyector:
    // Este exige acceso mutador, pide puramente el 'writeLock()'. Esto detendrá cualquier thread de lectura 
    // hasta que el forzado finalice su proceso lógico interno. Controla el try/finally con sumo rigor.
    public void escribirEstadoNuevo(String nuevoExt) {
        // ... pide cerrojo ESCRITOR ... (try catch finally)
        this.estadoConfiguracion = nuevoExt;
        System.out.println(">> ESCRITOR COMPLETÓ LA MUTACION CRITICA: " + nuevoExt);
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EL CONTROLADOR V/W EN RED ---");
        Ejercicio31_ReadWriteLock db = new Ejercicio31_ReadWriteLock();
        
        // TODO 4: Instancia y emite la carga de unos 5 Threads (Hilos_Lectores_1->5) pasándoles el 
        // método lógico leerEstado mediante lambda pura (Ej 28).

        // TODO 5: Instancia al mismo tiempo 1 hilo letal mutador (Hilo_Escritor_Absoluto) apuntando 
        // a invocar a tu escribirEstadoNuevo("CORRUPTO-99").
        
        // TODO 6 (PRUEBA): Arranque nativo global de todos los componentes asíncronos instanciados en VSC.
        // Observa como las lecturas corren en paralelo super rápido, pero al entrar el Escritor, todo el sistema 
        // se frena un milisegundo hasta que culmina y luego libera la cola analítica nuevamente.
    }
}
