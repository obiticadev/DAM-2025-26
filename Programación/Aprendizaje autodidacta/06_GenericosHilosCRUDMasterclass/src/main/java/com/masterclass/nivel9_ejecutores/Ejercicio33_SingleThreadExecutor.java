package com.masterclass.nivel9_ejecutores;

/**
 * EJERCICIO 33: Adiós al "new Thread" (SingleThreadExecutor)
 * 
 * OBJETIVO: 
 * Desterrar el paradigma básico y arrancar el motor asíncrono utilizando
 * el Ejecutor más simple disponible.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '09_Ejecutores_Pools.md' (Sección: "Ejecutores Clásicos")
 */
// TODO 1: Configura la abstracción ejecutable asíncrona de base.
public class Ejercicio33_SingleThreadExecutor {

    // TODO 2: Sobrescribe el código concurrente ciego.
    // HINT: Duerme el bloque usando el genérico (Thread.sleep) por 1 segundo, luego imprime: "Tarea Procesada por Ejecutor Central"

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PRIMER POOL AISLADO ---");
        
        // TODO 3: Utiliza la factoría oficial `java.util.concurrent.Executors` para
        // generar el executor de un solo hilo y recíbelo en una variable del interfaz global `ExecutorService`.
        /* ExecutorService pool = ... */

        // TODO 4: Inyecta TRES instancias de tu clase abstracta arriba configurada.
        // HINT: En lugar del obsoleto 'start()', la centralizadora requiere el uso nativo de su método '.submit(...)'.
        
        // TODO 5: Las centrales multihilo sobreviven impidiendo pasivamente que se muera el Main JVM.
        // Manda apagar la chimenea invocando obligatoriamente el método '.shutdown()' del servicio global.
        
        // TODO 6 (PRUEBA): Ejecuta el Run. Comprobarás cómo el servidor invierte 3 segundos ordenados
        // sacando uno a uno del embudo. Y gracias al shutdown, el programa finaliza pulcramente al acabar.
        // System.out.println("Solicitudes Submit enviadas y apagado asíncrono programado. El main termina aquí...");
    }
}
