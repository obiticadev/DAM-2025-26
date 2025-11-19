package com.arrays.arrays_intermedio.ejercicio22;

import java.util.Scanner;

/*
 * 
    Liga de Superhéroes Eres el programador encargado de gestionar la lista de superhéroes que forman parte de una liga. Cada superhéroe tiene un nombre y un puntaje de fuerza. Debes escribir un programa en Java que:

Declare y llene dos arrays estáticos:

Un array de tipo String que contenga los nombres de 5 superhéroes.

Un array de tipo int que contenga el puntaje de fuerza correspondiente de cada superhéroe.

Muestre la lista de superhéroes y sus puntuaciones.

Haz una función que encuentre y devuelva al superhéroe más fuerte (el que tiene puntuación más alta)

después muestra su nombre.

Haz una función que calcule y devuelva el promedio de fuerza de todos los superhéroes y después muestra un mensaje indicando si cada superhéroe está por encima o por debajo del promedio.
 */

public class App {
    public static void main(String[] args) {
        Scanner scInt = new Scanner(System.in);
        Scanner scLine = new Scanner(System.in);

        String[] superheroes;
        int[] fuerza;
        int numColeccion;
        int fuerzaMax = 0;
        int indice = 0;
        double sumaFuerza = 0;
        double promedio;

        System.out.println("Introduce cuántos superheroes tiene tu colección: ");
        numColeccion = scInt.nextInt();

        superheroes = new String[numColeccion];
        fuerza = new int[numColeccion];

        // Insertamos nombres y poderes

        for (int i = 0; i < superheroes.length; i++) {
            System.out.print("Introduce el nombre del superheroe " + (i+1) + ": ");
            superheroes[i] = scLine.nextLine();

            System.out.print("Introduce la fuerza de " + superheroes[i] + ": ");
            fuerza[i] = scInt.nextInt();
        }

        // Sacamos la lista con un for
        System.out.println("\nLista de superheroes y sus poderes:\n");
        for (int i = 0; i < superheroes.length; i++) {
            System.out.println(superheroes[i] + " tiene " + fuerza[i] + " de poder");
        }

        // Buscamos el índice que tenga el valor más alto

        for (int i = 0; i < superheroes.length; i++) {
            if (fuerzaMax <= fuerza[i]) {
                fuerzaMax = fuerza[i];
                indice = i;   
            }

        }
        System.out.println("El superheroe más fuerte es " + superheroes[indice] + " con " + fuerza[indice] + " de fuerza");


        // Promedio

        for (int i = 0; i < fuerza.length; i++) {
            sumaFuerza += fuerza[i];
        }

        promedio = sumaFuerza / fuerza.length;

        System.out.println(promedio + " es el promedio de fuerza");

        System.out.println("Los superheroes que superan la media son:");

        for (int i = 0; i < superheroes.length; i++) {
            if (fuerza[i] > promedio) {
                System.out.println(superheroes[i] + " con " + fuerza[i] + " de fuerza");
            }
        }

    }
}
