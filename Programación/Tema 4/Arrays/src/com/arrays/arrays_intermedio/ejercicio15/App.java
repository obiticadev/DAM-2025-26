package com.arrays.arrays_intermedio.ejercicio15;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Crea un programa que pida la usuario dos valores N y M y luego cree un array de tamaño N que contenga M en todas sus posiciones. Luego muestra el array por pantalla.
 */

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rangoArray;
        String contenedor;

        System.out.println("Introduce el rango del array:");
        rangoArray = sc.nextInt();

        System.out.println("Introduce lo que quieras guardar en cada posición: ");
        contenedor = sc.nextLine();
        contenedor = sc.nextLine();

        String[] arrayString = new String[rangoArray];

        for (int i = 0; i < arrayString.length; i++) {
            arrayString[i] = contenedor;

        }
        System.out.println(Arrays.toString(arrayString));

    }
}
