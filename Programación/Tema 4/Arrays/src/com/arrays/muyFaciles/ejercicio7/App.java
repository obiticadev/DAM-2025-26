package com.arrays.muyFaciles.ejercicio7;

import java.util.Scanner;

/*
 * Crea un programa que pida dos valores enteros P y Q, luego cree un array que contenga todos los valores desde P hasta Q, y lo muestre por pantalla. CFGS.
 */

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numA;
        int numB;
        int diferencia;
        int[] arrayDiferencia;
        String salida = "";
        
        System.out.println("Introduce dos valores para mostrarte por pantalla los valores contenidos\n");

        System.out.print("Introduce el primer valor: ");
        numA = sc.nextInt();
        System.out.print("Introduce el segundo valor: ");
        numB = sc.nextInt();
        

        diferencia = (numA < numB) ? numB - numA : numA - numB;

        arrayDiferencia = new int[diferencia];

        for (int i = (numA+1); i < numB; i++) {
            if (i != (numB-1)) {
                salida = salida + i + ", ";
            }else{
                salida = salida + "y " + i;
            }
        }

        System.out.println(salida);



    }
}
