package com.arrays.arrays_bidimensionales.R13;

import java.util.Arrays;

/**
 * 
 * Haz un ejercicio que tenga una función para sumar las filas del array
 * bidimensional
 * Y otra para sumar las columnas
 * Muestra los resultados
 */

public class r13 {

    public static void main(String[] args) {

        final int FILAS = 7;
        final int COLUMNAS = 9;

        int[][] matriz = new int[FILAS][COLUMNAS];
        int[] arraySuma = new int[FILAS];
        int[] arraySumaVolteado = new int [COLUMNAS];
        Arrays.fill(arraySuma, 0);
        Arrays.fill(arraySumaVolteado, 0);

        for (int i = 0; i < matriz.length; i++) {
            if (((i + 1) % 2) == 1) {
                for (int j = 0; j < matriz[i].length; j++) {
                    matriz[i][j] = 2;
                    arraySuma[i] += matriz[i][j];
                    System.out.print(matriz[i][j]);
                }
                System.out.println(" = " + arraySuma[i]);
            } else {
                for (int j = 0; j < matriz[i].length; j++) {
                    matriz[i][j] = 1;
                    arraySuma[i] += matriz[i][j];

                    System.out.print(matriz[i][j]);
                }
                System.out.println(" = " + arraySuma[i]);
            }
        }
        System.out.println();
        for (int i = 0; i < matriz[0].length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                arraySumaVolteado[i] += matriz[j][i];
                System.out.print(matriz[j][i]);
            }
            System.out.println(" = " + arraySumaVolteado[i]);
        }

    }

}
