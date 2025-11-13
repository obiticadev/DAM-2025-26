package com.arrays.arrays_bidimensionales.ejercicio2;

import java.util.Arrays;

/*
 * El objetivo de este ejercicio es crear una sopa de letras, (un array bidimensional). Usando una colección de palabras, debes crear el array bidimensional de 10x10, completándolo con caracteres aleatorios. Puedes usar como tema la fotosíntesis.

Después se colocarán las palabras de la colección, de forma horizontal, y con lectura de izquierda a derecha (tradicional), emplezando en una posición aleatoria.
 */
public class App {
    public static void main(String[] args) {
        final int MIN = 65;
        final int MAX = 90;
        char[][] sopa = new char[20][20];
        String[] palabras = { "Mariposa", "Sol", "Luz", "Flor" };
        // Variables para inserción de palabras en la sopa de letras
        int insertEnFila;
        int insertEnColumna;
        int[] filaUsada = new int[palabras.length];
        boolean estaDentro;

        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                sopa[i][j] = (char) (Math.random() * (MAX - MIN + 1) + MIN);
            }
        }

        // Se necesita insertar las palabras en filas aleatorias y que la palabra
        // comience en una columna donde le permita insertar caber en los huecos que
        // deja.
        for (int i = 0; i < palabras.length; i++) {
            do {
                estaDentro = false;
                insertEnFila = (int) (Math.random() * (sopa.length));
                for (int j = 0; j < filaUsada.length; j++) {
                    if (insertEnFila == filaUsada[j]) {
                        estaDentro = true;
                    }
                }

            } while (estaDentro);
            insertEnColumna = (int) (Math.random() * (sopa[i].length - palabras[i].length()));
            for (int j = 0; j < palabras[i].length(); j++) {
                // En este punto ya sabemos qué fila y qué columna colocar la palabra y en este
                // for anidado empezamos a insertar el contenido
                sopa[insertEnFila][insertEnColumna] = palabras[i].charAt(j);
                insertEnColumna++;
            }
            filaUsada[i] = insertEnFila;

        }

        // Ejemplo para imprimir la sopa y ver el resultado
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                System.out.print(Character.toUpperCase(sopa[i][j]) + " ");
            }
            System.out.println();

        }
        System.out.println("Las soluciones se encuentran en: " + Arrays.toString(filaUsada));
    }
}
