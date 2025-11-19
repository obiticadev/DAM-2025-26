package com.parking.utilidades;

import java.util.Scanner;

public class Utilidades {
    public static int devolverEdadValida(double numAdultos) {
        // Declaración de variables
        boolean continuar = true;
        int edad;

        // Convertimos en int el valor double
        int numAdultosInt = (int) numAdultos;

        // Inicializamos Scanner
        Scanner sc = new Scanner(System.in);
        // Aqui preguntamos hasta que la edad este entre 0 y 100
        do {

            System.out.println("\n¿Cual es la edad?");
            edad = sc.nextInt();
            if (numAdultosInt > 0 && numAdultosInt < 100) {

            } else {
                System.out.println("Dame una edad entre 0 y 100\n");
                continuar = false;
            }
        } while (continuar);
        // si se cumple devuelve el valor

        return edad;
    }
}
