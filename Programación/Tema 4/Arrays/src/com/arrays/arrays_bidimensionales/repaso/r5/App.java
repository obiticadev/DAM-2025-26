package com.arrays.arrays_bidimensionales.repaso.r5;

import java.util.Scanner;

/*
 * Crea un programa que cree una matriz de tamaño NxM (tamaño introducido por teclado) e introduzca en ella NxM valores (también introducidos por teclado). Luego deberá recorrer la matriz y al final mostrar por pantalla cuántos valores son mayores que cero, cuántos son menores que cero y cuántos son igual a cero.
 */

public class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int[][] matriz;
        int filas;
        int columnas;
        int contador = 0;
        boolean continuar;
        int mayoresDeCero = 0;
        int menoresDeCero = 0;
        int igualesACero = 0;

        System.out.println("¿Cuántas filas quieres que tenga la matriz?");
        filas = sc.nextInt();
        System.out.println("¿Cuántas columnas quieres que tenga la matriz?");
        columnas = sc.nextInt();

        matriz = new int[filas][columnas];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                contador++;
                do {
                    continuar = true;
                    try {
                        System.out.print("Valor de la posición " + contador + ": ");
                        matriz[i][j] = sc.nextInt();
                        continuar = false;
                        if (matriz[i][j] > 0) {
                            mayoresDeCero++;
                        }if (matriz[i][j] < 0) {
                            menoresDeCero++;
                        }if (matriz[i][j] == 0) {
                            igualesACero++;
                        }

                        
                    } catch (Exception e) {
                        System.out.println("Debes introducir un número entero");
                        sc.next();
                    }
                } while (continuar);
                
            }
        }
        System.out.println();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "\t");
                
            }
            System.out.println();
        }

        System.out.println("\nMayores de cero: " + mayoresDeCero);
        System.out.println("Menores de cero: " + menoresDeCero);
        System.out.println("Iguales a cero: " + igualesACero);


    }
}
