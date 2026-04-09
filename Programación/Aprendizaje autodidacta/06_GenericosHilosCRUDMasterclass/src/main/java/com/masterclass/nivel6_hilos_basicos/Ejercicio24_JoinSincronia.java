package com.masterclass.nivel6_hilos_basicos;

/**
 * EJERCICIO 24: Sincronización Primaria (.join)
 * 
 * OBJETIVO:
 * Recuperar el orden puro del caos. Utilizar los bloqueos asíncronos arquitecturales
 * de espera de la JVM para forzar a un hilo (el Main, en este caso) a esperar a que termine su hijo.
 * 
 * APOYO TEÓRICO: 
 * Revisa '06_Hilos_Basicos.md' (Sección: "El Caos del entrelazado")
 */
// TODO 1: Configura la abstracción ejecutable y desarrolla el método asíncrono asumiendo
// una tarea tardía de por lo menos 3 segundos de Sleep (Thread.sleep(3000)), finalizando 
// con un log "Base de datos remota sincronizada".
public class Ejercicio24_JoinSincronia {


    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EL MAIN SE DETIENE ---");
        
        // TODO 2: Configura la instancia y la encapsulación envolvente en Thread.
        // TODO 3: Detona la ramificación de ejecución inyectando la tarea a la CPU.
        
        /*
        Thread hiloCargador = ...;
        // invocar detonación de contexto.
        */
        
        System.out.println("El MAIN, ignorando todo, va a cerrarse y terminar la app sin sentido...");

        // TODO 4: Utiliza el método asíncrono invocado directo sobre el objeto de hilo (hiloCargador)
        // que fuerza a que esta manguera de Main se pause y aguante hasta que el 'hiloCargador' termine sus 3s.
        // HINT: Este invocador obliga al entorno Try/Catch de manera similar al .sleep(). Adquiere interrupciones.
        
        // TODO 5 (PRUEBA): Ejecuta y fíjate meticulosamente en la consola. "PROGRAMA FINALIZADO EXITO" 
        // ya no debería salir disparado a los pocos milisegundos, debería verse ahogado esperando forzosamente 
        // a que la "BBDD Sincronice".
        
        System.out.println("PROGRAMA FINALIZADO CON EXITO TOTAL.");
    }
}
