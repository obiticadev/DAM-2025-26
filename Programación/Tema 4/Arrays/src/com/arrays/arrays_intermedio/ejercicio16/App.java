package com.arrays.arrays_intermedio.ejercicio16;

import java.util.Arrays;

/*
 * Crea un programa que cree un array de enteros e introduzca la siguiente secuencia de valores: 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, etc. hasta introducir 10 diez veces, y luego la muestre por pantalla. En esta ocasión has de utilizar Arrays.fill().
 */

public class App {
    public static void main(String[] args) {
        int rango = 10;
        int rangoArray = conteoDeRango(rango);
        int[] numArray = new int[rangoArray];
        int desde;
        int hasta;
        int posicionActual = 0;

        for (int i = 1; i <= rango; i++) {

            // Calculamos el índice "desde" y "hasta" para Arrays.fill()
            desde = posicionActual;
            hasta = posicionActual + i;

            // Rellenamos la porción del array.
            // Por ejemplo, para i=3, rellenará 3 posiciones con el valor 3.
            Arrays.fill(numArray, desde, hasta, i);

            // Actualizamos la posición para la siguiente vuelta del bucle.
            posicionActual = hasta;

        }
        System.out.println(Arrays.toString(numArray));

    }

    public static int conteoDeRango(int rango) {

        int rangoArray = 0;
        for (int i = 10; i > 0; i--) {
            rangoArray += i;
        }

        return rangoArray;
    }
}
