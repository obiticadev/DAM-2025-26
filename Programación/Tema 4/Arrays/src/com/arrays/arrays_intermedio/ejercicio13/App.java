package com.arrays.arrays_intermedio.ejercicio13;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Crea un programa que permita al usuario almacenar una secuencia aritmética en un array y luego mostrarla. Una secuencia aritmética es una serie de números que comienza por un valor inicial V, y continúa con incrementos de I. Por ejemplo, con V=1 e I=2, la secuencia sería 1, 3, 5, 7, 9… Con V=7 e I=10, la secuencia sería 7, 17, 27, 37… El programa solicitará al usuario V, I además de N (nº de valores a crear).
 */

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numArray;
        int valorInicial;
        int valorIncremento;
        int rangoArray;

        System.out.println("¿Cómo de grande quieres la secuencia?");
        rangoArray = sc.nextInt();
        System.out.println("¿Por qué valor quieres iniciar?");
        valorInicial = sc.nextInt();
        System.out.println("¿Cuál quieres que sea el incremento?");
        valorIncremento = sc.nextInt();

        numArray = new int[rangoArray];

        for (int i = 0; i < numArray.length; i++) {
            if (i == 0) {
                numArray[i] = valorInicial;

            } else {
                numArray[i] = numArray[i - 1] + valorIncremento;
            }
        }

        System.out.println(Arrays.toString(numArray));
    }
}
