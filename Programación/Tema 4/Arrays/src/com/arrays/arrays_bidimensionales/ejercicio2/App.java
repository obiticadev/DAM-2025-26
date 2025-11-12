package com.arrays.arrays_bidimensionales.ejercicio2;

/*
 * El objetivo de este ejercicio es crear una sopa de letras, (un array bidimensional). Usando una colección de palabras, debes crear el array bidimensional de 10x10, completándolo con caracteres aleatorios. Puedes usar como tema la fotosíntesis.

Después se colocarán las palabras de la colección, de forma horizontal, y con lectura de izquierda a derecha (tradicional), emplezando en una posición aleatoria.
 */
public class App {
    public static void main(String[] args) {
        final int MIN = 65;
        final int MAX = 90;
        char[][] sopa = new char[10][10];
        String[] palabras = {"Mariposa", "Sol", "Luz", "Flor"};
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                sopa[i][j] = (char) (Math.random() * (MAX - MIN + 1) + MIN);
            }
        }
        // Ejemplo para imprimir la sopa y ver el resultado
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                System.out.print(sopa[i][j] + " ");
            }
            System.out.println();


        }
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < palabras.length; j++) {
                
            }
        }


    }
}
