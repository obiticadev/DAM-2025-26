package com.arrays.arrays_intermedio.ejercicio8;

import java.util.Scanner;

/*
 * Crea un programa que cree un array con 100 números enteros aleatorios entre 0 y 200, utilizando Math.random()- o la clase Random, y luego le pida al usuario un valor entero E. Por último, mostrará cuántos valores del array son igual o superiores a E.
 */

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numSelect;
        int contador = 0;

        int min = 0;
        int max = 200;

        /*
         * (max - min + 1)
         * Esta parte calcula el tamaño del rango, es decir, cuántos números posibles
         * queremos.
         * En tu ejemplo: 100 - 1 + 1 = 100.
         * Esto significa que queremos generar uno de los 100 números posibles (1, 2, 3,
         * ..., 100). El +1 es crucial para asegurar que el número máximo (100) se
         * incluya en el rango posible.
         * 
         * ... + min
         * Finalmente, le sumamos el valor mínimo de nuestro rango (min, que es 1).
         * Esto desplaza el rango de números desde 0-99 a 1-100, que es lo que
         * queríamos.
         * Ejemplo A: 25 + 1 = 26.
         * Ejemplo B: 99 + 1 = 100.
         * El valor más bajo posible sería 0 + 1 = 1.
         */

        int[] numRandomArray = new int[100];
        for (int i = 0; i < numRandomArray.length; i++) {
            numRandomArray[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
        System.out.print("Introduce un valor numérico: ");
        numSelect = sc.nextInt();

        for (int i = 0; i < numRandomArray.length; i++) {
            if (numSelect <= numRandomArray[i]) {
                contador++;
                System.out.println("El número " + numRandomArray[i] + " ubicada en la posición " + i
                        + " es más grande o igual que " + numSelect);
            }
        }
        System.out.println("\nEn total hay " + contador + " números que son iguales o mayores que " + numSelect);
    }
}
