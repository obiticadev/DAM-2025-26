package UT02;

import java.util.Scanner;

/**
 * Ejercicio 3.
 * Escribe un programa que a partir del precio de venta y el % de IVA
 * calcule la parte que se queda el comercio y la parte que son impuestos.
 */
public class e5e3 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo X - Ejercicio Y ---");

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el precio de venta: ");
        double precioVenta = sc.nextDouble();

        System.out.print("Ingrese el porcentaje de IVA (por ejemplo, 21 para el 21%): ");
        double porcentajeIVA = sc.nextDouble();

        double parteComercio = precioVenta / (1 + (porcentajeIVA / 100));
        double impuestos = precioVenta - parteComercio;

        System.out.println("Parte que se queda el comercio: " + parteComercio);
        System.out.println("Parte que son impuestos: " + impuestos);

        sc.close();
    }
}
