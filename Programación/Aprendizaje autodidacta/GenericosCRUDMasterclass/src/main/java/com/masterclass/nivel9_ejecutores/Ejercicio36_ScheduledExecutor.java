package com.masterclass.nivel9_ejecutores;

/**
 * EJERCICIO 36: Tareas Cron (ScheduledExecutor)
 * 
 * OBJETIVO: 
 * Comprender la naturaleza abstracta de una tarea Cron (que se ejecuta sola en segundo plano
 * con ciertos delays o intervalos).
 */
public class Ejercicio36_ScheduledExecutor {

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- TIMER EXECUTOR CRON ---");
        
        // TODO 1: Desglosa la factoría en un `java.util.concurrent.ScheduledExecutorService`.
        // Solícita el motor estandar `.newScheduledThreadPool(1)`
        
        // TODO 2: Llama en la manguera abstracta a `.schedule(...)`.
        // Este requiere una lambda, una unidad int de retraso, y su especificación abstracta (`TimeUnit.SECONDS`).
        // Haz que la lambda (Runnable) imprima "¡BOMBA DETONADA DESPUES DE 4 SEGUNDOS!".
        
        // TODO 3: A diferencia de todos los anteriores, si mandas un .shutdown() ahora, no esperará
        // e intentará matar tu cron antes del timeout en la mayoría de contextos lógicos.
        // Fíjalo todo, y cuando pruebes, añade un 'Thread.sleep(5000)' al main para aguantar la bomba.
        // ¡Al acabar pon el shutdown!
        
        // TODO 4 (PRUEBA): Corrobora en VSC que el main carga velozmente, se para 4 segundos y finalmente,
        // la lambda escupe letalmente el Sysout programado. Un programador ha nacido.
    }
}
