package com.bootcamp.nivel13;

import java.util.List;
import java.util.ArrayList;

/**
 * EJERCICIO 13.02 — Navegación LSP en Java
 *
 * Esta clase la usarás para practicar navegación con jdtls:
 *   gd       go to definition
 *   gr       references
 *   K        hover (Javadoc)
 *   <leader>co   organize imports
 *
 * TODOs:
 *   TODO 1 (con pista): Pon el cursor sobre 'sumar' en el main al final
 *     y pulsa gd. Salta a la definición. Vuelve con Ctrl-o.
 *
 *   TODO 2 (con pista): Pulsa K sobre 'List' (en el import o donde aparezca).
 *     Aparece el Javadoc de java.util.List.
 *
 *   TODO 3 (con pista): Pulsa gr sobre 'multiplicar'. Aparecen 2+ referencias.
 *
 *   TODO 4 (LIBRE): Hay un import sin usar (ArrayList). Pulsa <leader>co
 *     (organize imports) o <leader>ca y elige "Organize Imports". El
 *     import desaparece.
 *
 *   TODO 5 (LIBRE): Guarda con :w y sal.
 */
public class Calculadora {

    public int sumar(int a, int b) {
        return a + b;
    }

    public int multiplicar(int a, int b) {
        return a * b;
    }

    public int sumarVarios(int... numeros) {
        int total = 0;
        for (int n : numeros) {
            total = sumar(total, n);
        }
        return total;
    }

    public static void main(String[] args) {
        Calculadora c = new Calculadora();
        System.out.println(c.sumar(2, 3));
        System.out.println(c.multiplicar(4, 5));
        System.out.println(c.multiplicar(6, 7));
        System.out.println(c.sumarVarios(1, 2, 3, 4));
    }
}
