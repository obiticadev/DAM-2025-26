package com.arrays.arrays_intermedio.ejercicio9;

import java.util.Scanner;

/*
 * Crea un programa que cree un array de enteros de tamaño 100 y lo rellene con valores enteros aleatorios entre 1 y 10 (utiliza 1 + Math.random()*10). Luego pedirá un valor N y mostrará en qué posiciones del array aparece N.
 */

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int MAX_NUM_ARRAY = 100;
        int[] numArray = new int[MAX_NUM_ARRAY];
        int contador = 0;
        int respuestaUser;

        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = (int) (1 + Math.random() * 10);
        }
        System.out.println("Dame un número y comprobaremos si se encuentra en el array y en qué posición");
        respuestaUser = sc.nextInt();

        for (int i = 0; i < numArray.length; i++) {
            if (respuestaUser == numArray[i]) {
                contador++;
                System.out.println("En la posición " + (i + 1));
            }
        }
        System.out.println("Con un total de " + contador + " coincidencias");

    }
}
