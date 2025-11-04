package com.arrays.muyFaciles.ejercicio4;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int MAX = 5;
        int[] num = new int[MAX];
        int contadorNegativos = 0;
        int contadorPositivos = 0;
        

        System.out.println("Introduce " + MAX + " valores numéricos, positivos y negativos");
        
        for (int i = 0; i < num.length; i++) {
            System.out.println("Introduce el número " + (i+1) + ": ");
            num[i] = sc.nextInt();
        }

        for (int i = 0; i < num.length; i++) {
            if (num[i] < 0){
                contadorNegativos += num[i];
            }else{
                contadorPositivos += num[i];
            }

        }
        System.out.println("La suma de los valores negativos es de " + contadorNegativos);
        System.out.println("La suma de los valores positivos es de " + contadorPositivos);
        
    }
}
