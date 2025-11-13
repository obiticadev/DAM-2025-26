package com.arrays.arrays_bidimensionales.repaso.r3;

/*
 * Crea un programa que cree una matriz de 10x10 e introduzca los valores de las tablas de multiplicar del 1 al 10 (cada tabla en una fila). Luego mostrará la matriz por pantalla.
 */

public class App {
    public static void main(String[] args) {
        final int FILAS = 10;
        final int COLUMNAS = 10;
        
        int[][] matriz = new int[FILAS][COLUMNAS];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = ((i+1) * (j+1));
            }
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
