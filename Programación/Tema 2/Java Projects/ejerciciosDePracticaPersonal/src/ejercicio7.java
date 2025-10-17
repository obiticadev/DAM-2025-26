/*
Enunciado:

Crea un programa que pida al usuario una lista de tres notas en formato String, separadas por comas (por ejemplo: "7.5,9,8.2"). El programa debe procesar esta cadena, calcular el promedio de las notas y mostrar en pantalla dos resultados:
El promedio exacto con decimales.
La parte entera del promedio (la nota final sin decimales).

Ejemplo de Salida:

Ingresa las tres notas separadas por comas:
8.5,9.5,6
El promedio exacto es: 8.0
La nota final entera es: 8*/

import java.util.Scanner;

public class ejercicio7 {
    public static void main(String[] args) throws Exception {
        // Declaración de variables
        String salida;
        double nota1;
        double nota2;
        double nota3;
        double promedio;
        int notaFinal;
        final int NUM_NOTAS = 3;

        
        Scanner scanLine = new Scanner(System.in);
        System.out.println("Ingresa las tres notas:");
        salida = scanLine.nextLine();

        Scanner lector = new Scanner(salida);
        lector.useDelimiter("\s");

        nota1 = lector.nextDouble();
        nota2 = lector.nextDouble();
        nota3 = lector.nextDouble();

        promedio = (nota1 + nota2 + nota3)/NUM_NOTAS;
        notaFinal = (int) promedio;
        
        System.out.println("El promedio exacto es: " + promedio);
        System.out.print("La nota final entera es: " + notaFinal);
        
        
    }
}
