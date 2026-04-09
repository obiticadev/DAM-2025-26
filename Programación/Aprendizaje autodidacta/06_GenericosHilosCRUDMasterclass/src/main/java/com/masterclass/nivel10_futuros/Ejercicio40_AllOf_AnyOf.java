package com.masterclass.nivel10_futuros;

import java.util.concurrent.CompletableFuture;

/**
 * EJERCICIO 40: Multi-API y Sincronicidad Híbrida (AllOf)
 * 
 * OBJETIVO: 
 * Si estás descargando 3 Microservicios a la vez, quieres una promesa final
 * que no se active SÓLO HASTA QUE que las tres terminen con éxito en plano simultáneo.
 */
public class Ejercicio40_AllOf_AnyOf {

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) throws Exception {
        System.out.println("--- ESPERA GENERALIZADA DE DESCARGAS ---");
        
        // TODO 1: Configura tres CompletableFuture 'supplyAsync' o 'runAsync' distintos (A, B y C).
        // En ellos simula latencias (sleep 1s, 2s y 1s) simulando consultas de Backend.
        
        // TODO 2: Llama estáticamente a la clase Matrix central (CompletableFuture.allOf(...)) e inyecta 
        // las tres promesas usando comas de inter-demanda (tuA, tuB, tuC).
        // Asigna esto a un 'CompletableFuture<Void> todasListas = ...'.
        
        // TODO 3: Aplica la reacción final atada sobre tu 'todasListas' mediante la inyección consumidora
        // .thenRun(() -> { // Sysout alertando que los 3 microservicios han finalizado }).
        
        // TODO 4: Por la arquitectura Daemónica forzosa de la JVM, lanza un letargo general de base de al
        // menos unos 4 segs asíncronos atando al Main, para dar tiempo a culminar la tríada.
        
        // TODO 5 (PRUEBA): Corrobora en los Sysouts y depurador que el log enlazado final del thenRun no se
        // imprime bajo ninguna circunstancia hasta que el retrasado Microservicio B de 2 segundos termina.
        // Con este ejercicio final, estás listo para dominar todos los ecosistemas del Concurrency.
    }
}
