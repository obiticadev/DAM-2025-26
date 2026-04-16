package com.arrays.Examen.Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        String[] preguntas = Utilidades.devolverPreguntas();
        Scanner sc = new Scanner(System.in);
        int[] num = new int[preguntas.length];
        Arrays.fill(num, 0);
        int notaAcumulada = 0;

        System.out.println("ENCUESTA DE EVALUACIÓN DOCENTE\n");

        for (int i = 0; i < preguntas.length; i++) {
            System.out.print(preguntas[i] + ": ");
            try {
                num[i] = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Introduce un valor correcto");
                i--;
            }
            if (num[i] > 0 && num[i] < 6) {
                preguntas[i] = preguntas[i] + ": " + num[i];
                notaAcumulada += num[i];
            } else {
                System.out.println("Solo valores entre 1 y 5");
                i--;
            }

        }

        System.out.println("\nEstas son las preguntas contestadas:\n----------------------------------------");
        mostrarResultados(preguntas, notaAcumulada);

        System.out.println("Segunda Parte\n");
        int[][] notas = Utilidades.getEncuestas();
        double[] arrayNotaMedia = new double[notas[0].length];
        Arrays.fill(arrayNotaMedia, 0);

        for (int i = 0; i < notas[0].length; i++) {
            for (int j = 0; j < notas.length; j++) {
                arrayNotaMedia[j] += notas[j][i];
            }

            System.out.println();

        }
        for (int i = 0; i < arrayNotaMedia.length; i++) {
            arrayNotaMedia[i] = arrayNotaMedia[i] / notas.length;
        }
        for (int i = 0; i < notas[0].length; i++) {
            for (int j = 0; j < notas.length; j++) {
                System.out.print(notas[j][i] + " ");
            }

            System.out.println(arrayNotaMedia[i]);

        }

    }

    public static void mostrarResultados(String[] preguntas, double notaAcumulada) {
        double notaMedia;
        notaMedia = notaAcumulada / preguntas.length;
        for (int i = 0; i < preguntas.length; i++) {
            System.out.println(preguntas[i]);
        }
        System.out.println("\nNota media: " + notaMedia);

    }

}
