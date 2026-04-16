package com.masterclass.nivel6_hilos_basicos;

/**
 * EJERCICIO 23: Entrelazado (Data Races Base)
 * 
 * OBJETIVO:
 * Analizar empíricamente la incapacidad humana de predecir la ejecución concurrente
 * cuando esta depende puramente del 'Scheduler' del Sistema Operativo Windows/Linux.
 */
// TODO 1: Ajusta la firma para convertirla en tarea ejecutable (runnable).
public class Ejercicio23_Entrelazado {

    private String nombreSoldado;

    // TODO 2: Configura el constructor para inicializar al soldado.
    public Ejercicio23_Entrelazado(String nombreSoldado) {
        this.nombreSoldado = nombreSoldado;
    }

    // TODO 3: En el bloque 'run':
    // Crea un for del 1 al 10 e imprime: 
    // nombreSoldado + " está atacando la posición " + i
    // Hint: Usa un Thread.sleep(5) simulado dentro del for para darle oportunidades 
    // de contexto de conmutación (Context Switch) al procesador, manejando su Excepción asociada.


    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- GUERRA DE HILOS (SCHEDULER RACE) ---");
        
        // TODO 4 (PRUEBA): Crea TRES soldados distintos (ej. Alfa, Bravo, Charlie).
        // Envuélvelos en 3 Threads independientes. Lanza los tres llamando al 'start()'.
        
        // Ejecuta el programa. Al mirar la consola observarás que Alfa no termina su for 
        // antes de que Bravo empiece el suyo. El procesador los intercala aleatoriamente.
        // Bienvenido a los dolores de cabeza de servidores ultra-demandados en Backend reales.
    }
}
