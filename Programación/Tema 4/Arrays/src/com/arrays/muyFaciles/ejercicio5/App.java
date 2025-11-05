package com.arrays.muyFaciles.ejercicio5;

import java.util.Scanner;

/*
 * Crea un programa que pida veinte números reales por teclado, los almacene en un array y luego lo recorra para calcular y mostrar la media: (suma de valores) / nº de valores.
 */


public class App {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        final int MAX = 5;
        int[] num = new int[MAX];
        double contadorSuma = 0;
        double media;
        
        System.out.println("Introduce " + MAX + " valores numéricos para calcular su media");


        for (int i = 0; i < num.length; i++) {
            System.out.print("Introduce el número " + (i+1) + ": ");
            num[i] = sc.nextInt();
            contadorSuma += num[i];
        }

        media = contadorSuma / MAX;

        System.out.println("La media de los valores numéricos introducidos es de " + media);
        
    }
}
