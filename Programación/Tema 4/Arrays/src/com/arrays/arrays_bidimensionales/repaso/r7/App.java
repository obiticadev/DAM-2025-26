package com.arrays.arrays_bidimensionales.repaso.r7;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Necesitamos crear un programa para almacenar las notas de 4 alumnos (llamados “Alumno 1”, “Alumno 2”, etc.) y 5 asignaturas. El usuario introducirá las notas por teclado y luego el programa mostrará la nota mínima, máxima y media de cada alumno.
 */

public class App {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        double[][] matriz = new double[4][5];
        double[] basuraFila = new double[matriz[0].length];
        boolean continuar;
        double[] minArray = new double[matriz.length];
        double[] maxArray = new double[matriz.length];
        double notaAcumulada;
        double[] media = new double[matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            notaAcumulada = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                do {
                    continuar = true;
                    try {

                        System.out.print("Introduce la nota de la asignatura " + (j + 1) + ": ");
                        matriz[i][j] = sc.nextDouble();
                        continuar = false;
                        notaAcumulada += matriz[i][j];



                    } catch (Exception e) {
                        System.out.println("Introduce un valor correcto");
                        sc.next();
                    }

                } while (continuar);

            }
            System.out.println();
            System.arraycopy(matriz[i], 0, basuraFila, 0, basuraFila.length);
            Arrays.sort(basuraFila);
            System.out.println(Arrays.toString(basuraFila));
            minArray[i] = basuraFila[0];
            maxArray[i] = basuraFila[basuraFila.length];
            media[i] = notaAcumulada / matriz[i].length;


            

        }

        System.out.println(Arrays.toString(minArray));
        System.out.println(Arrays.toString(maxArray));
        System.out.println(Arrays.toString(media));

    }
}
