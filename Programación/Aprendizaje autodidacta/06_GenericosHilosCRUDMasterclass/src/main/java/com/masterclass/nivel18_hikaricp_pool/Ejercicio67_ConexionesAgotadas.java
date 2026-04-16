package com.masterclass.nivel18_hikaricp_pool;

/**
 * EJERCICIO 67: El Colapso de Conexiones Crudas
 * 
 * OBJETIVO: 
 * Provocar empíricamente un bloqueo masivo o lentitud letal para comprender 
 * el problema de las conexiones tradicionales a nivel Driver y su retardo instanciador.
 */
public class Ejercicio67_ConexionesAgotadas {

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PRUEBA ESTRÉS DriverManager (NO OPTIMIZADO) ---");
        
        // TODO 1: Para contar tiempo global, guarda al principio del método con un long el valor purista:
        // 'long startMill = System.currentTimeMillis();'
        
        // TODO 2: Inicia un colosal For loop de un tope de al menos 500 (1 a 500).
        // En él, llama a the obligatory DriverManager.getConnection(), pide una query chorra vacia rapida "SELECT 1" (try-with ref).
        
        // TODO 3: Al finalizar totalmente tu ciclo For, imprime tu latencia:
        // '(System.currentTimeMillis() - startMill)'. Logea "Milisegundos totales gastados: ".
        
        // TODO 4 (PRUEBA): Observa asustado el peso de hardware que requiere tu disco y tu jvm instanciando puramente el driver.
        // Anota el tiempo para el ejercicio comparativo que asoma.
    }
}
