package com.arrays.reapsoyampliacion.ejercicio23;

import java.util.Arrays;
import java.util.Random;

public class ejercicio23 {
    public static void main(String[] args) {
        final int MAX = 15;
        int[] arrayNum = new int[MAX];

        for (int i = 0; i < arrayNum.length; i++) {
            arrayNum[i] = i + 1;
            System.out.print(arrayNum[i] + " ");
        }
        System.out.println();
        mezclar(arrayNum);

    }

    private static void mezclar(int[] array){
        Random rd = new Random();
        int resultado;
        int temporal;

        for (int i = array.length-1; i >= 0; i--) {
            resultado = rd.nextInt(15);
            temporal = array[i];
            array[i] = array[resultado];
            array[resultado] = temporal;
        }
    }

    private static int minimo(int[] array){
        int numMin = 0;
        
        Arrays.sort(array);
        
        return numMin;
    }
    
}
