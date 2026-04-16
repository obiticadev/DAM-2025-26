package com.masterclass.nivel7_sincronizacion;

/**
 * EJERCICIO 25: Exponiendo la Mutiación de Datos (Data Race)
 * 
 * OBJETIVO:
 * Comprobar que en Java, las operaciones atómicas matemáticas NO son seguras por defecto.
 * Vas a destruir el conteo de una variable utilizando fuerza bruta de procesador.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '07_Sincronizacion_Monitores.md' (Sección: "Condición de Carrera")
 */
// TODO 1: Implementa la interfaz ejecutable asíncrona estándar de la JVM.
public class Ejercicio25_SeccionCritica {

    // Variable global de memoria compartida
    private int stockInventario = 0;

    // TODO 2: Sobrescribe el bloque de la lógica subyacente. 
    // HINT: Implementa un for gigante (ej. 10.000 iteraciones) que incremente el 'stockInventario'.
    

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- PRUEBA DE DESTRUCCIÓN DE DATOS ---");
        
        // TODO 3: Crea una única instancia central de tu clase Tarea.
        // TODO 4: Inyecta esa ÚNICA instancia dentro de DOS hilos diferentes (Thread t1 y Thread t2).
        
        /* 
        Ejercicio25_SeccionCritica centralDeDatos = new Ejercicio25_SeccionCritica();
        Thread norte = new Thread(centralDeDatos);
        Thread sur = new Thread(...);
        
        // TODO 5: Dispara el arranque asíncrono de ambos hilos A LA VEZ.
        norte.start(); 
        sur.start();
        
        // El main debe esperar forzosamente a que ambos terminen usando '.join()' en cascada.
        norte.join();
        sur.join();
        
        // TODO 6 (PRUEBA): Si el for era de 10.000, el resultado esperado impreso aquí debe ser 20.000 (10k por hilo).
        // Ejecuta esto múltiples veces y analiza la salida. ¿Alguna vez llega a la perfección matemática o hay pérdidas?
        System.out.println("Stock Final Real tras el asedio de dos hilos: " + centralDeDatos.stockInventario);
        */
    }
}
