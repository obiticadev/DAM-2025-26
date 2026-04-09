package com.masterclass.nivel7_sincronizacion;

/**
 * EJERCICIO 28: Emisión de Señales (El Productor / Consumidor Antiguo)
 * 
 * OBJETIVO:
 * Interconectar hilos para que interactúen inteligentemente en vez de solaparse.
 * Usaremos las primitivas de Root base .wait() y .notify().
 */
public class Ejercicio28_WaitNotify {

    private String mensaje = null;
    private boolean yaProducido = false;

    // TODO 1: Sincroniza operativamente la firma. Este hilo será el Productor.
    // HINT: Chequea con 'while' la variable 'yaProducido'. Si es cierta, haz que el hilo 
    // entregue el control y se duerma usando el método heredado atómico nativo asociado a Objects (`wait()`).
    // Al final del método cambia la variable a true, pon un texto en 'mensaje', y manda un toque 
    // a los dormidos simulando un 'notify()'.
    public void depositarDatos() throws InterruptedException {
        // Tu algoritmo restrictivo aquí
    }

    // TODO 2: Sincroniza operativamente esta firma. Será el Consumidor.
    // HINT: Ahora el while comprueba '!yaProducido'. Si no hay nada, el hilo debe dormirse nativamente.
    // Al finalizar, consume el texto y aplica la campana notificadora.
    public void recogerDatos() throws InterruptedException {
        // Tu algoritmo restrictivo inverso aquí
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PING PONG SINCRONIZADO ---");
        
        Ejercicio28_WaitNotify core = new Ejercicio28_WaitNotify();

        // TODO 3: Implementa la instanciación in-line en forma lambda "() -> { ... }"
        // rodeando tu Try/Catch para invocar a 'depositarDatos()' desde el hilo A.
        Thread productor = new Thread(() -> {
            /* try { core.depositarDatos(); } catch (Exception e){} */
        });

        // TODO 4: Exactamente lo mismo, llamando a 'recogerDatos()' en el hilo B.
        // HINT: Ambos hilos colaboran, el consumidor no estallará dando null por los bloqueos del wait.

        // TODO 5 (PRUEBA): Lanza ambos de forma concurrente con el start nativo y observa su magia.
    }
}
