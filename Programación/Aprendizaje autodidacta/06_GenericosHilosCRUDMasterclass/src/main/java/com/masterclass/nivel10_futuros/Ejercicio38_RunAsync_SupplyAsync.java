package com.masterclass.nivel10_futuros;

import java.util.concurrent.CompletableFuture;

/**
 * EJERCICIO 38: Inversión de Datos y Devolución T (Supply Async)
 * 
 * OBJETIVO: 
 * Realizar un cálculo pesado pero esta vez utilizando un 'Supplier<T>' 
 * que prometa un dato resolutivo 'T'.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '10_CompletableFutures.md'
 */
public class Ejercicio38_RunAsync_SupplyAsync {

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) throws Exception {
        System.out.println("--- EL SUMINISTRADOR ASÍNCRONO ---");
        
        // TODO 1: A diferencia de 'runAsync', demanda la fábrica de '.supplyAsync'.
        // Ella acepta una Lambda Supplier: () -> { ... return ... }.
        // Configura un Thread.sleep ligero y haz que retorne el String puro: "DATOS-DB-1991A"
        
        /* 
        CompletableFuture<String> tareaExtraccion = CompletableFuture.supplyAsync(...
        */

        // TODO 2: Pide al sistema que devuelva lo que se ha inyectado 
        // usando el viejo candado limitativo '.get()'. Asígna lo extraido a una variable e imprímelo.
        
        // TODO 3 (PRUEBA): Visualiza cómo ahora el Main puede aguantar la arquitectura y recoger 
        // tipos explícitos (String, o incluso Integer si retornases 500) gracias a la Inferencia Genérica 
        // que construiste indirectamente.
    }
}
