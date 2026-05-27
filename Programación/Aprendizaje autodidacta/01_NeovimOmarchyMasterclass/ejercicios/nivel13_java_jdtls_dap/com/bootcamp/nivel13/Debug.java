package com.bootcamp.nivel13;

/**
 * EJERCICIO 13.05 — Debugger con DAP
 *
 * Este programa tiene un bug INTENCIONADO: factorial(5) no devuelve 120.
 * Tu trabajo: ponerle un breakpoint, ejecutarlo con debug, ver el estado
 * en cada iteración y descubrir el fallo.
 *
 * TODOs:
 *   TODO 1 (con pista): Ponte en la línea del 'return acc' (dentro del bucle).
 *     Pulsa <leader>db para poner BREAKPOINT. Verás un círculo rojo.
 *
 *   TODO 2 (con pista): Pulsa <leader>dc (continue) — te pide elegir
 *     configuración. Elige "Launch Debug.main()" o similar. El programa
 *     arranca y se PARA en el breakpoint.
 *
 *   TODO 3 (con pista): Pulsa <leader>du para abrir dap-ui. Ves Variables
 *     (i, acc, n). Pulsa <leader>dc varias veces para avanzar iteraciones.
 *     Observa cómo cambia 'acc'.
 *
 *   TODO 4 (LIBRE): Encuentra el bug. Está en el bucle. Termina la sesión
 *     con <leader>dt. Edita el código para arreglarlo. (Pista del bug: la
 *     condición del for ignora el último valor; debería ser i <= n.)
 *
 *   TODO 5 (LIBRE): Ejecuta sin debug con :!mvn exec:java -Dexec.mainClass
 *     o lanza el test (no hay test aquí; usa el main). Verifica que
 *     factorial(5) imprime 120. Guarda y sal.
 */
public class Debug {

    public static int factorial(int n) {
        int acc = 1;
        for (int i = 1; i < n; i++) {   // BUG: debería ser i <= n
            acc = acc * i;
        }
        return acc;
    }

    public static void main(String[] args) {
        System.out.println("factorial(5) = " + factorial(5));
        System.out.println("factorial(6) = " + factorial(6));
    }
}
