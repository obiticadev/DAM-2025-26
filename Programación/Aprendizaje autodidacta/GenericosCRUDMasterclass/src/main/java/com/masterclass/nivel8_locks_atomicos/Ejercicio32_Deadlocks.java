package com.masterclass.nivel8_locks_atomicos;

/**
 * EJERCICIO 32: Provocar un Abrazo Mortal (Deadlock)
 * 
 * OBJETIVO: 
 * Comprobar que en Java, pedir prestadas llaves múltiples del monitor o del cerrojo 
 * puede tumbar de forma permanente y fatal cualquier programa entero, matando tu desarrollo.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '08_Locks_Atomicos.md' (Sección: "Deadlocks")
 */
public class Ejercicio32_Deadlocks {

    // Objetos estancos vírgenes asumiendo una representación pasiva física (Monitores abstractos).
    private static final Object RECURSO_A_LLAVE = new Object();
    private static final Object RECURSO_B_LLAVE = new Object();

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PROVOCANDO ABRAZO MORTAL (DEADLOCK) ---");
        
        // TODO 1: Configura un hilo abstracto (Thread Alfa). 
        // Dentro de el asume la directriz asíncrona del control estricto (synchronized) de la LLAVE_A.
        // En su interior, duérmele 50 misilsegundos puramente simulativos para dejar la red libre al otro hilo.
        // Luego, intenta incrustar otro 'synchronized' anidado demandado violentamente la LLAVE_B.
        
        Thread hiloAlfa = new Thread(() -> {
            // ... lock A ...
                // ... sleep(50);
                // ... lock B anidado dentro de A
                System.out.println("Alfa obtuvo ambos recursos (Jamás deberia imprimirse)");
        });

        // TODO 2: Configura un hilo inverso opuesto en su polaridad (Thread Beta).
        // Enmudece la red obligándole a restringir fuertemente (synchronized) sobre la LLAVE_B base primero.
        // Hazle dormir unos MS. Luego obliga arquitectónicamente a pedir internamente la LLAVE_A anidada.
        
        Thread hiloBeta = new Thread(() -> {
            // ... lock B ...
                // ... sleep(50);
                // ... lock A anidado dentro de B ...
        });

        // TODO 3: Implanta los detornadores de hilo. 'alfa.start()' y 'beta.start()'.  

        // TODO 4 (PRUEBA FINAL): Corre el test. Visualiza cómo el IDE VS Code parpadeará indefinidamente y 
        // el proceso se quedará parado paralizando eternamente el proceso, requiriendo un parón forzoso Stop (Cubo Rojo). 
        // Esto ilustra matemáticamente por qué jamás debes demandar Locks asíncronos inversos cruzados.
        
        System.out.println("El MAIN terminó su lectura e inicializó, pero observa la JVM...");
    }
}
