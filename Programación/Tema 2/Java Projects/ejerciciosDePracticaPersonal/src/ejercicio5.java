/*Ejercicio 5: Información Personal
Objetivo: Practicar la lectura de diferentes tipos de datos y solucionar el problema del salto de línea.
Crea un objeto Scanner.
Pide al usuario su edad (un número entero) y léela con nextInt().
Importante: Resuelve el problema del nextLine() pendiente después de leer un número.
Pide al usuario su nombre completo y léelo con nextLine().
Pide al usuario su salario y léelo con nextDouble().
Muestra un resumen en pantalla, convirtiendo el salario (que es double) a String de forma implícita al concatenarlo.
Ejemplo de salida:

Ingresa tu edad: 30
Ingresa tu nombre completo: Ana López
Ingresa tu salario: 2500.75
Resumen:
Nombre: Ana López
Edad: 30
Salario: 2500.75

Punto clave: Este ejercicio te obliga a usar scanner.nextLine() para limpiar el búfer después de leer la edad.*/

import java.util.Scanner;

public class ejercicio5 {
    public static void main(String[] args) throws Exception {
        System.out.println("Ejercicio 5: Información Personal\n");

        // Declaración de variables
        int edad;
        String nombre;
        double salario;

        Scanner numScan = new Scanner(System.in);
        Scanner stringScan = new Scanner(System.in);

        System.out.println("¿Cuál es tu edad?");
        edad = numScan.nextInt();
        System.out.println("¿Cuál es tu nombre?");
        nombre = stringScan.nextLine();
        System.out.println("¿Cuál es tu salario?");
        salario = numScan.nextDouble();

        // Salida a la terminal
        System.out.println("Resumen\n..........");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Salario: " + String.valueOf(salario));
        

    }
}
