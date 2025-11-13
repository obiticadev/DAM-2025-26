package com.arrays.extras.ejercicio2;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        final int FILAS = 6;
        final int COLUMNAS = 3;
        int contador = 0;
        boolean estaEnArray = false;

        Scanner sc = new Scanner(System.in);

        String[][] clase = new String[FILAS][COLUMNAS];

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                contador++;
                clase[i][j] = "Alumno" + contador;
            }
        }
        System.out.println("Introduce el alumno buscado:");
        String respuesta = sc.nextLine();
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(clase[i][j] + " ");
                if (respuesta.equals(clase[i][j])) {
                    estaEnArray = true;
                }
            }
            System.out.println();
        }
        System.out.println("Está o no está? " + estaEnArray);
    }
}
