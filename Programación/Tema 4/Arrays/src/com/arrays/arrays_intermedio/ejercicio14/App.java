package com.arrays.arrays_intermedio.ejercicio14;

import java.util.Arrays;

/*
 * Crea un programa que cree un array de enteros e introduzca la siguiente secuencia de valores: 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, etc. hasta introducir 10 diez veces, y luego la muestre por pantalla. NOTA : Utiliza los métodos de la clase ‘Arrays’ para ayudarte a resolver los siguientes ejercicios.
 */

public class App {
    public static void main(String[] args) {
        int[] numArray;
        String numString = "";

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= i; j++) {
                numString = numString + i + " ";
            }
        }

        numArray = Arrays.stream(numString.split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(numString);
        System.out.println(Arrays.toString(numArray));
        /*
         * Arrays.stream(texto.split(" ")): Divide el texto igual que antes, pero
         * inmediatamente convierte el array de String en un
         * "Stream" (un flujo de datos).
         * 
         * .mapToInt(Integer::parseInt): A cada elemento del flujo (cada String), le
         * aplica la función Integer.parseInt() para convertirlo en un int. El resultado
         * es un flujo de enteros.
         * 
         * .toArray(): Convierte el flujo de enteros de vuelta a un array de tipo int[].
         */
    }
}
