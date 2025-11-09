package com.arrays.muyFaciles.ejercicio2;

import java.util.Scanner;

/*
 * Crea un programa que pida diez números reales por teclado, los almacene en un array, y luego muestre la suma de todos los valores.
 */

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int MAX = 5;
        int[] numArray = new int[MAX];
        String suma = "";
        int contadorSuma = 0;

        System.out.println("Introduce " + MAX + " valores numéricos:");
        for (int i = 0; i < numArray.length; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            numArray[i] = sc.nextInt();
            contadorSuma += numArray[i];
            if (i == (numArray.length - 1)) {
                suma = suma + numArray[i] + " = ";
            } else {
                suma = suma + numArray[i] + " + ";

            }
        }

        System.out.println(suma + contadorSuma);

    }
}
