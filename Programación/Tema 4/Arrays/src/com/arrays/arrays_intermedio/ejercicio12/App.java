package com.arrays.arrays_intermedio.ejercicio12;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Crea un programa que cree un array de 10 enteros y luego muestre el siguiente menú con distintas opciones:

a. Mostrar valores. 
b. Introducir valor. 
c. Salir.
La opción ‘a’ mostrará todos los valores por pantalla. 
La opción ‘b’ pedirá un valor V y una posición P, luego escribirá V en la posición P del array. El menú se repetirá indefinidamente hasta que el usuario elija la opción ‘c’ que terminará el programa.
 */

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        char respuesta;
        final int MAX = 10;
        int[] numArray = new int[MAX];
        int valor1;
        int valor2;

        do {
            System.out.println("""
                    MENU
                    ----

                    A) Mostrar valores
                    B) Introducir valores
                    C) Salir
                    """);
            respuesta = sc.next().charAt(0);
            switch (respuesta) {
                case 'a' -> {
                    System.out.println(Arrays.toString(numArray));
                }

                case 'b' -> {
                    System.out.println("Introduce un valor que quieras agregar: ");
                    valor1 = sc.nextInt();
                    System.out.println("Introduce la posición en la que la quieres guardar (1 - 10): ");
                    valor2 = sc.nextInt();

                    numArray[valor2 - 1] = valor1;

                }

                case 'c' -> {
                    System.out.println("Saliendo del programa");
                    continuar = false;
                }

                default -> {

                }

            }
        } while (continuar);

    }
}
