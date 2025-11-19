package com.arrays.arrays_intermedio.ejercicio18;

import java.util.Arrays;

/*
 * Crea un programa que cree un array de tamaño 30 y lo rellene con valores aleatorios entre 0 y 9 (utiliza Math.random()*10). Luego ordena los valores del array y los mostrará por pantalla.
 */

public class App {
    public static void main(String[] args) {
        int[] numArray = new int[30];

        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = (int) (Math.random() * 10);
        }
        Arrays.sort(numArray);

        System.out.println(Arrays.toString(numArray));
    }
}
