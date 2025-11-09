package com.arrays.arrays_intermedio.ejercicio10;

import java.util.Scanner;

/*
 * Crea un programa para realizar cálculos relacionados con la altura (en metros) de personas. Pedirá un valor N y luego almacenará en un array N alturas introducidas por teclado. Luego mostrará la altura media, máxima y mínima así como cuántas personas miden por encima y por debajo de la media.
 */

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        float[] arrayAlturas;
        int num;
        float sumaAltura = 0;
        float media;
        int contadorAltMax = 0;
        int contadorAltMin = 0;
        float maximo = 0;

        System.out.println("¿De cuántas personas vamos a guardar sus datos?\nDame un valor numérico entero:");
        num = sc.nextInt();
        arrayAlturas = new float[num];

        for (int i = 0; i < arrayAlturas.length; i++) {
            System.out.println("Persona " + (i + 1));
            arrayAlturas[i] = sc.nextFloat();
            sumaAltura += arrayAlturas[i];
        }
        media = sumaAltura / arrayAlturas.length;
        System.out.println("La media de las alturas es de " + media);

        for (int i = 0; i < arrayAlturas.length; i++) {
            if (arrayAlturas[i] > media) {
                contadorAltMax++;
            } else {
                contadorAltMin++;
            }
        }

        float minimo = arrayAlturas[0];
        for (int i = 0; i < arrayAlturas.length; i++) {
            maximo = Math.max(maximo, arrayAlturas[i]);
            minimo = Math.min(minimo, arrayAlturas[i]);
        }

        System.out.println("La altura máxima es: " + maximo);
        System.out.println("La altura mínima es: " + minimo);
        System.out.println("Hay " + contadorAltMax + " personas por encima de la media");
        System.out.println("Hay " + contadorAltMin + " personas por debajo de la media");

    }
}
