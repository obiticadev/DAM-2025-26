package com.masterclass.nivel17_concurrencia_sql;

/**
 * EJERCICIO 66: Paro Cardítico (Deadlock SQL)
 * 
 * OBJETIVO: 
 * Forzar asincronamente un Abrazo Mortal, pero a nivel físico sobre el disco duro 
 * del Motor SQLite cruzando transacciones inversas.
 */
public class Ejercicio66_DeadlockDB {

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- INCEPCIÓN (DEADLOCK BASE DE DATOS) ---");
        
        // TODO 1: Configurar Hilo ALPHA:
        // Abre una Transaccion Manual. Lanza un update a la fila ID=1 (La Lockea). Duerme(200ms).
        // Lanza otro Update cruzado ciego a la fila ID=2 (Exige el Lock 2). 

        // TODO 2: Configurar el Hilo BETA:
        // Transaccion Manual pura. Lanza un Update en fila ID=2 (Adquiere Lock 2). Duerme(200ms).
        // Lanza un letal update a la fila ID=1!.

        // TODO 3 (PRUEBA): Levanta a Beta y Alpha concurrentemente con 'start()'. 
        // Contemplarás atónito el milagro: El motor Subyacente (Driver), para impedir un colapso inmanejable de RAM,
        // interceptará el DeadLock de motor y expulsará a base de fuerza bruta a uno de los hilos arrojando a pantalla completa en rojo la 
        // mítica Excepción `SQLiteException: [SQLITE_BUSY] database is locked`. Las DDBB se defienden.
    }
}
