package UT02;

import java.util.Scanner;

/**
 * Ejercicio 1.
 * Realiza un programa que solicite dos números al usuario y 
 * devuelva la suma, la resta, la multiplicación y la división
 * de esos números, el resultado debe mostrar las operaciones
 * completas (Ejemplo: 7 * 2 = 14)
 */
public class e5e1 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo 5 - Ejercicio 1 ---");
        float a, b;
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el primer número: ");
        a = sc.nextFloat();
        System.out.print("Introduzca el segundo número: ");
        b = sc.nextFloat();
        System.out.println(a + " + " + b + " = " + (a + b));
        System.out.println(a + " - " + b + " = " + (a - b));
        System.out.println(a + " x " + b + " = " + (a * b));
        System.out.println(a + " / " + b + " = " + (a / b));
        sc.close();
    }
}
