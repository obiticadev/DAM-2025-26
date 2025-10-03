/*
 * Área de un rectángulo Pide la base y la altura de un rectángulo (dos enteros) y muestra su área y perímetro.
 */

import java.util.Scanner;

public class Suma_de_tres_numeros_3 {
    public static void main(String[] args) {

        // Declaramos las variables
        int base;
        int altura;
        int area;
        int ladoA;
        int ladoB;
        int ladoC;
        int perimetro;


        // Arrancamos la librería para escanear las entradas en la terminal
        Scanner numScanner = new Scanner(System.in);

        System.out.println("CALCULADORA DE SUPERFICIES TRIANGULARES Y SU PERÍMETRO\n");
        System.out.println("¿Cuánto mide la base?");
        base = numScanner.nextInt();
        System.out.println("\n¿Cuánto mide la altura?");
        altura = numScanner.nextInt();
        System.out.println("\n¿Cuánto mide el primer lado del triángulo?");
        ladoA = numScanner.nextInt();
        System.out.println("\n¿Cuánto mide el segundo lado del triángulo?");
        ladoB = numScanner.nextInt();
        System.out.println("\n¿Cuánto mide el tercer lado del triángulo?");
        ladoC = numScanner.nextInt();

        // Calculamos el área del triángulo y su perímetro
        area = base * altura / 2;
        perimetro = ladoA + ladoB + ladoC;
        // Mostramos el resultado
        System.out.println("\nEl área del triángulo con base " + base + " y altura " + altura + " es de " + area + " unidades cuadradas,");
        System.out.println("y su perímetro es de " + perimetro);
    }
}
