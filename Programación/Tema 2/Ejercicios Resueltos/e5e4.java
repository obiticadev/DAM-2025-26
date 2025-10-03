package UT02;

import java.util.Scanner;

/**
 * Ejercicio 4.
 * Realiza un programa que a partir de tres números indique cual es el 
 * mayor, cual es el menor o si son iguales.
 */
public class e5e4 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo 5 - Ejercicio 4 ---");
        int a, b, c;
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el primer número (a): ");
        a = sc.nextInt();
        System.out.print("Introduzca el segundo número (b): ");
        b = sc.nextInt();
        System.out.print("Introduzca el tercer número (c): ");
        c = sc.nextInt();

        boolean aMayor, bMayor, cMayor, aMenor, bMenor, cMenor, iguales;
        iguales = (a == b) && (b == c);
        aMayor = (a >= b) && (a >= c);
        bMayor = (b >= a) && (b >= c);
        cMayor = (c >= b) && (c >= a);
        aMenor = (a <= b) && (a <= c);
        bMenor = (b <= a) && (b <= c);
        cMenor = (c <= b) && (c <= a);
        System.out.println("¿Son iguales? " + iguales);
        System.out.println("El mayor es a? " + aMayor);
        System.out.println("El mayor es b? " + bMayor);
        System.out.println("El mayor es c? " + cMayor);
        System.out.println("El menor es a? " + aMenor);
        System.out.println("El menor es b? " + bMenor);
        System.out.println("El menor es c? " + cMenor);
        sc.close();
    }
}
