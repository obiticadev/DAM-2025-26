package com.arrays.reapsoyampliacion.ejercicio22;

public class ejercicio22 {
    public static void main(String[] args) {
        final int MAX = 10;
        int[] arrayNum = new int[MAX];

        int acumuladorSuma = 0;
        
        for (int i = 0; i < arrayNum.length; i++) {
            arrayNum[i] += i;
            System.out.print(arrayNum[i] + "\t");
        }
        System.out.println("\n");
        for (int i = 0; i < arrayNum.length; i++) {
            acumuladorSuma += arrayNum[i];
        }
        System.out.println(acumuladorSuma);

        
    }
}
