package com.arrays.arrays_intermedio.ejercicio11;

import java.sql.Array;
import java.util.Arrays;

/*
 * Crea un programa que cree dos arrays de enteros de tamaño 100. Luego introducirá en el primer array todos los valores del 1 al 100. Por último, deberá copiar todos los valores del primer array al segundo array en orden inverso, y mostrar ambos por pantalla.
 */

public class App {
    public static void main(String[] args) {

        final int MAX = 100;
        int maxModificable = MAX;

        int[] numArray = new int[MAX];
        int[] numArrayInverso = new int[MAX];

        for (int i = 0; i < MAX; i++) {
            numArray[i] = i + 1;
        }

        System.out.println(Arrays.toString(numArray));

        for (int i = 0; i < MAX; i++) {
            maxModificable--;
            numArrayInverso[i] = numArray[maxModificable];
        }

        System.out.println(Arrays.toString(numArrayInverso));

    }
}
