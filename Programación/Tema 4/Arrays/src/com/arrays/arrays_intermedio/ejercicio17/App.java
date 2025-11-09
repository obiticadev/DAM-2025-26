package com.arrays.arrays_intermedio.ejercicio17;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Crea un programa que pida al usuario 20 valores enteros e introduzca los 10 primeros en un array y los 10 últimos en otro array. Por último, comparará ambos arrays y le dirá al usuario si son iguales o no.
 */

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int MAX = 5;
        int[] numArray1 = new int[MAX];
        int[] numArray2 = new int[MAX];

        System.out.println("Introduce valores y compararemos si las dos cadenas son correctas");

        for (int i = 0; i < MAX; i++) {
            System.out.print("Introduce en la primera cadena el número " + (i + 1) + ": ");
            numArray1[i] = sc.nextInt();
        }
        for (int i = 0; i < MAX; i++) {
            System.out.print("Introduce en la segunda cadena el número " + (i + 1) + ": ");
            numArray2[i] = sc.nextInt();
        }

        System.out.println(Arrays.toString(numArray1));
        System.out.println(Arrays.toString(numArray2));

        boolean resultado = Arrays.equals(numArray1, numArray2);

        System.out.println(resultado);

    }
}
