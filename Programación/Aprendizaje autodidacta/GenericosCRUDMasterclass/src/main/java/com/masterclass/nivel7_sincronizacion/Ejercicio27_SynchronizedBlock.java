package com.masterclass.nivel7_sincronizacion;

/**
 * EJERCICIO 27: Optimización con Bloques Sincronizados
 * 
 * OBJETIVO:
 * Entender por qué sincronizar el método completo es el "Antipatrón" de la latencia en Backend.
 * Aislaremos únicamente la mutación crítica utilizando un Token/Llave explícito.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '07_Sincronizacion_Monitores.md' (Sección: "Bloques Sincronizados")
 */
// TODO 1: Configura la aserción de tarea.
public class Ejercicio27_SynchronizedBlock {

    private int votosOnline = 0;
    
    // TODO 2: Como marca la buena praxis, instancia un Objeto en crudo 'final' para que 
    // funcione estrictamente de token/cerrojo de seguridad.
    // private final Object llaveCentral = ...

    // TODO 3: Implementa la aserción asíncrona del paso 1.
    // HINT: En el bucle masivo, imprime un log "Procesando petición HTTP...".
    // Esto simula que no necesitas sincronizar toda la función.
    // Luego, embebe el ++ dentro de un bloque restrictivo que solicite tu 'llaveCentral'.

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- LATENCIA OPTIMIZADA ---");
        
        // TODO 4 (PRUEBA): Implanta exactamente el mismo escenario trágico de dos hilos.
        // Evalúa tras su ejecución que el total sigue marcando la máxima estrictez matemática.
        
        /* 
        Ejercicio27_SynchronizedBlock servidor = new Ejercicio27_SynchronizedBlock();
        // Genera los Thread, arráncalos, bloquéalos con join y comprueba la variable.
        */
    }
}
