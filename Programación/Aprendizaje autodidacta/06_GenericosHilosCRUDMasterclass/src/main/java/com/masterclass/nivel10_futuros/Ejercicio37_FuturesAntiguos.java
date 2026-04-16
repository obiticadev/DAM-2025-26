package com.masterclass.nivel10_futuros;

import java.util.concurrent.CompletableFuture;

/**
 * EJERCICIO 37: Declaración Asíncrona Funcional Básica
 * 
 * OBJETIVO: 
 * Abandonar de lleno los ExecutorServices burocráticos y lanzar asincronía 
 * pura orientada a Funciones (Lambdas).
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '10_CompletableFutures.md' (Sección: "CompletableFuture")
 */
// TODO 1: Esta clase no requiere aserción ni estado interno, seremos funcionales 100%.
public class Ejercicio37_FuturesAntiguos {

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) throws Exception {
        System.out.println("--- PRIMERA PROMESA (RUN ASYNC) ---");
        
        // TODO 2: Inyecta el método estático base '.runAsync' de CompletableFuture.
        // Éste exigirá nativamente un 'Runnable' (que no devuelve nada). Ofrécelesel a través de una lambda '() -> { ... }'.
        /*
        CompletableFuture<Void> procesoVacio = CompletableFuture.runAsync(() -> { ...
        */

        // TODO 3: En la lambda forzada de arriba, duerme el sistema nativo del hilo (Thread.sleep) por 1 seg.
        // Y arroja un Sysout: "Carga finalizada en plano oscilobatiente".
        
        System.out.println("El MAIN se ha terminado. No esperó a nadie.");

        // TODO 4: Las promesas en los threads comunes sufren de estado "Daemon" intrínseco.
        // Esto significa que si muere Main, ellas mueren. 
        // Force un '.get()' sobre 'procesoVacio' debajo de esta orden o Main matará despiadadamente tu hilo al milisegundo.
        
        // TODO 5 (PRUEBA): Corre el sistema. Todo debería compilar y lanzarse como un cohete moderno de Java 8+.
    }
}
