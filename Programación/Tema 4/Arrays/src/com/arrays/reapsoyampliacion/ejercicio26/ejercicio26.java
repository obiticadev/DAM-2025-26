package com.arrays.reapsoyampliacion.ejercicio26;

import java.util.Scanner;

public class ejercicio26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAX = 10;
        int[] arrayNum = new int[MAX];

        for (int i = 0; i < arrayNum.length; i++) {
            arrayNum[i] += i + 1;
            System.out.print(arrayNum[i] + "\t");
        }
        System.out.print("\nRotamos array, ¿cuántas veces quieres rotarlo?:");
        int respuesta = sc.nextInt();
        for (int i = 0; i < respuesta; i++) {
            rotar(arrayNum);
        }

        for (int i = 0; i < arrayNum.length; i++) {
            System.out.print(arrayNum[i] + "\t");
        }
        System.out.println();
        
        
    }

    public static void rotar(int[] array){

        int temporal = array[array.length-1];;

        for (int i = array.length - 1; i >= 0; i--) {
            if (i != 0) {
                array[i] = array[i - 1];
            } else {
                array[i] = temporal;
            }
        }
    }
}
