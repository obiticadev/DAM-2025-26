package com.masterclass.nivel6_hilos_basicos;

/**
 * EJERCICIO 22: Comandos de Detención Temporal y Excepciones
 * 
 * OBJETIVO:
 * Entender por qué en Java "dormir" un hilo está catalogado como un riesgo. 
 * Conquistar la 'InterruptedException' y la latencia.
 * 
 * APOYO TEÓRICO: 
 * Revisa '06_Hilos_Basicos.md' (Sección: "El Ciclo de Vida")
 */
// TODO 1: Configura la aserción base requerida de ejecución concurrente.
public class Ejercicio22_CicloDeVida {

    // TODO 2: Sobrescribe el bloque 'run'.
    // /* public void... {
        
        // TODO 3: Imprime "Hilo entra a dormir."
        // Aplica el temporizador de 'sleep' de la clase base 'Thread' exigiendo 2000 milisegundos (2s).
        
        // TODO 4: Rodea inmediatamente ese sleep con el bloque Try/Catch obligatorio 
        // que Java exige debido al ataque de tipo (InterruptedException).
        
        // Imprime en el catch: "Me han despertado del sueño a bofetadas!".
        // Imprime tras el catch: "Hilo despertó naturalemente y termina."
        
    // } */
    

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- TEST CICLO DE SUEÑO ---");
        
        // TODO 5 (PRUEBA): Escribe la invocación instanciadora, la envoltura en un Thread, 
        // y dispara el start(). Evalúa con Run en VSC cómo el compilador espera los 2 segundos exactos
        // sin bloquear la interfaz general.
    }
}
