package com.arrays.muyFaciles.ejercicio3;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Crea un programa que pida diez números reales por teclado, los almacene en un array, y luego lo recorra para averiguar el máximo y mínimo y mostrarlos por pantalla.
 */

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int MAX = 5;
        int[] num = new int[MAX];
        int numMax;
        int numMin;

        System.out.println("Introduce números para sacar el valor máximo y mínimo");

        for (int i = 0; i < num.length; i++) {
            System.out.println("Introduce el número: " + (i + 1) + ": ");
            num[i] = sc.nextInt();
        }

        Arrays.sort(num);

        numMax = num[num.length - 1];
        numMin = num[0];

        System.out.println("Valor máximo: " + numMax);
        System.out.println("Valor mínimo: " + numMin);

    }
}
