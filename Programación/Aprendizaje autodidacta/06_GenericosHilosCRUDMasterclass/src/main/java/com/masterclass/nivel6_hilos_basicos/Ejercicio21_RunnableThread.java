package com.masterclass.nivel6_hilos_basicos;

/**
 * EJERCICIO 21: Desdoblamiento de Hardware (Runnable vs Thread)
 * 
 * OBJETIVO:
 * Abandonar la secuencialidad estricta y arrancar tu primer proceso 
 * asíncrono utilizando el paradigma arquitectónico nativo de Java.
 * 
 * APOYO TEÓRICO: 
 * Revisa '06_Hilos_Basicos.md' (Sección: "Arquitectura Base")
 */
// TODO 1: Esta clase de lógica de negocio (TareaPesada) debe habilitarse para 
// que un 'Thread' la acepte. Implementa la interfaz correcta de un solo método.
public class Ejercicio21_RunnableThread {

    // TODO 2: Al aplicar el paso 1, el IDE exigirá que sobreescribas el método 
    // abstracto transversal asociado a dicha interfaz. Escríbelo.
    // HINT: En su interior, programa un bucle que cuente bloqueando la ejecución
    // 5 veces, con impresiones Sysout (ej "Procesando tarea asíncrona...").
    
    

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- MOTOR DE HILOS ARRANQUE TIPO 1 ---");
        
        // TODO 3: Inyecta la instancia de tu TareaPesada (Ejercicio21_RunnableThread)
        // dentro del constructor base de la clase envoltorio genérica 'Thread'.
        
        /* 
        Ejercicio21_RunnableThread logicaMision = new Ejercicio21_RunnableThread();
        // Genera aqui tu "new Thread(...)" pasándole tu logicaMision
        */

        // TODO 4: Emite la señal arquitectónica (método especial) que le informa al 
        // Sistema Operativo (y a la JVM) que debe ramificar el Thread actual y dispararlo.
        // HINT: No llames al método del algoritmo directamente o te quedarás bloqueado en Main.
        
        System.out.println("El MAIN (Thread Principal) ha llegado al final imparable.");
        
        // TODO 5 (PRUEBA): Ejecuta el código (Run). Deberías ver cómo el Main termina 
        // DE INMEDIATO, pero tu terminal sigue escupiendo "Procesando tarea..." en segundo plano.
    }
}
