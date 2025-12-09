package com.arrays.arrays_bidimensionales.R11;

/**

Haz el código que cree un array bidimensional de 5x5
que se rellenará en las filas pares con un 1
y en las filas impares con un 2
Muestra el array completo
*/

public class r11 {
    public static void main(String[] args) {

        int[][] matriz = new int[5][5];

        for (int i = 0; i < matriz.length; i++) {
            if (((i+1)%2) == 1) {
                for (int j = 0; j < matriz[i].length; j++) {
                    matriz[i][j] = 2;
                    System.out.print(matriz[i][j]);
                }
                System.out.println();
            } else {
                for (int j = 0; j < matriz[i].length; j++) {
                    matriz[i][j] = 1;
                    System.out.print(matriz[i][j]);
                }
                System.out.println();
            }
        }

        
    }
}
