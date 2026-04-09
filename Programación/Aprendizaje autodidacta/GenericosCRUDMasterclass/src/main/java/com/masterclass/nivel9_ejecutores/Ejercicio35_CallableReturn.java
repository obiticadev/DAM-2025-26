package com.masterclass.nivel9_ejecutores;

/**
 * EJERCICIO 35: Interfaz Callable (Dato de retorno Asíncrono)
 * 
 * OBJETIVO: 
 * Cambiar la mentalidad ciega de Runnable (void) por la arquitectura orientada a cálculo (return).
 * Vas a recibir una caja paramétrica 'Future<T>' que representa un pacto a futuro sobre ese resultado asíncrono.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '09_Ejecutores_Pools.md' (Sección: "Callable vs Runnable")
 */
// TODO 1: Configura la aserción 'Callable<String>'. Acuérdate de la magia de los Genéricos limitantes vistos en el Nivel 1.
public class Ejercicio35_CallableReturn {

    // TODO 2: Sobreescribe la lógica pero esta vez NO será run(). Callable te exigirá .call().
    // HINT: En su interior, duerme 2000 ms, y haz un *return* estricto de la cadena: "Reporte Estadístico 2026 listo".

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) throws Exception {
        System.out.println("--- RESOLVIENDO LLAMADAS CON RETORNO ---");
        
        // TODO 3: Genera una centralita simple, un ExecutorService de hilo único.
        
        // TODO 4: Altere la inyección lógica `.submit()`. Como ahora es Callable, el método mágicamente 
        // te devolverá un Objeto paramétrico `java.util.concurrent.Future<String>`. Asígnalo a una variable.

        System.out.println("Main: La simulación asíncrona está corriendo, yo sigo haciendo otras cosas...");

        // TODO 5: Consumo del trato. Usa `tuCajaFuture.get()` para que el Main se frene y decodifique
        // el dato prometido. Guarda eso suelto e imprímelo aquí abajo. Apaga el executor finalizado.

        // TODO 6 (PRUEBA): Imprime log del final y arranca en IDE. Verás cómo Main imprime instantáneamente, 
        // pero luego se bloquea al hacer '.get()' esperando su ansiado "Reporte Estadístico". 
    }
}
