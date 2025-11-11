package com.arrays.arrays_bidimensionales.ejercicio1;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Inicializamos el Scanner
        Scanner sc = new Scanner(System.in);

        // Declaración de variables
        String[] modulos = { "IPE1", "PR", "BD", "SI", "ED", "LM", "CN" };
        int numAlumnos;

        // Necesitamos saber el número de alumnos para delimitar nuestro array bidimensional
        System.out.println("Introduce el número de alumnos a registrar");
        numAlumnos = sc.nextInt();
        int[][] arrayAlumnosModulo = new int[numAlumnos][modulos.length];

        for (int i = 0; i < numAlumnos; i++) {
            for (int j = 0; j < modulos.length; j++) {
                System.out.println("¿Cuál es la nota en " + modulos[j] + " de alumno" + (i + 1) + "?");
                arrayAlumnosModulo[i][j] = sc.nextInt();
                if (arrayAlumnosModulo[i][j] < 0 || arrayAlumnosModulo[i][j] > 10 ) {
                    System.out.println("Debes introducir una cantidad de entre 0 y 10");
                    j--;
                }
            }
        }

        for (int i = 0; i < numAlumnos; i++) {
            System.out.println("Alumno " + (i + 1));
            System.out.println("---------\n");
            for (int j = 0; j < modulos.length; j++) {
                System.out.println("En " + modulos[j] + " tiene un " + arrayAlumnosModulo[i][j]);
            }
            System.out.println("");
        }

        System.out.println("""
                MENÚ DE OPCIONES
                ----------------

                1) Suma y promedio de cada alumno (fila)
                2) Suma y promedio de cada módulo (columna)
                3) Nota media general del grupo

                """);


    }
}
