package UT02;

import java.util.Scanner;

/**
 * Ejercicio 2.
 * Modifica el ejercicio 4 del ejemplo 3, en el que calculábamos
 * la hipotenusa de un triángulo, ahora el programa debe solicitar
 * el valor de los catetos al usuario, además debe solicitar las 
 * unidades en las que se expresan los valores (centrímetros, metros, 
 * kilómetros, etc.), y el resultado debe indicar dicha unidad.
 */
public class e5e2 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo 5 - Ejercicio 2 ---");
        Scanner sc = new Scanner(System.in);
        double cateto1, cateto2, hipotenusa;
        System.out.print("Introduce el valor del primer cateto: ");
        cateto1 = sc.nextDouble();
        System.out.print("Introduce el valor del segundo cateto: ");
        cateto2 = sc.nextDouble();
        hipotenusa = Math.sqrt(Math.pow(cateto1, 2) + Math.pow(cateto2, 2));
        System.out.println("Para un triangulo rectángulo en el que");
        System.out.println("sus catetos miden " + cateto1 + " y " + cateto2);
        System.out.println("la hipotenusa vale " + hipotenusa);
        sc.close();
    }
}
