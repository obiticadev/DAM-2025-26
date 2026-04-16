package com.masterclass.nivel10_futuros;

import java.util.concurrent.CompletableFuture;

/**
 * EJERCICIO 39: Cadena de Bloques de Promesas (ThenApply / ThenAccept)
 * 
 * OBJETIVO: 
 * Acabar de forma definitiva con el bloqueo limitante destructivo del `.get()`.
 * Vamos a forjar el pipeline para que una promesa despierte a otra, y el main muera feliz.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '10_CompletableFutures.md' (Sección: "Operadores Críticos"). Visualiza el FlowChart.
 */
public class Ejercicio39_ThenApply_ThenAccept {

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) throws Exception {
        System.out.println("--- CHAINING PURISTA EN BUCLE ---");
        
        // TODO 1: Inicia el eslabón Root (supplyAsync). Su Lambda simplemente retornará Integer "5".
        
        /*
        CompletableFuture.supplyAsync(() -> {
            return 5;
        })
        */

        // TODO 2: Concatena un '.thenApply()'. Su lambda '(dato) -> { ... }' recibirá tu '5' en la variable 'dato'.
        // Devuelve ese dato pero multiplicado por 10 (Transformación de Integers).
        
        // TODO 3: Concatena, sin romper la línea lógica del punto final de arriba, 
        // la aserción de consumo final '.thenAccept()'. 
        // Su lambda '(ultimoDato) -> { ... }' cogerá tu "50" mutado, e imprimirá un buen SysOut.
        
        System.out.println("Main: La cadena corre, yo me laspiro de inmediato");
        
        // TODO 4: Duerme estúpidamente este Main por 2 segs. Esto es esencial para impedir que la consola VSC
        // mate a las Promesas en red a nivel microsegundo antes de que les dé tiempo de imprimirse en el
        // Thread Secundario no bloqueante.
        
        // TODO 5 (PRUEBA): Corre. Es vital que contemples cómo tu línea asíncrona "Main: La cadena corre..." 
        // se ha disparado MUCHO antes matemáticamente de que termine de salir volando el SysOut inyectado
        // en tu TheAccept final. Acabas de vencer a la latencia cruzada.
    }
}
