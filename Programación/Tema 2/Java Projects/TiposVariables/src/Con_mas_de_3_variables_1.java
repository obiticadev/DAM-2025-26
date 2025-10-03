import java.util.Scanner;

/*
 * Pide al usuario 4 notas enteras y muestra la nota promedio.
 */

public class Con_mas_de_3_variables_1 {
    public static void main(String[] args) {
        
        // Declaración de variables
        float num1;
        float num2;
        float num3;
        float num4;
        float promedio;

        // Habilitamos la librería de Scanner
        Scanner numScanner = new Scanner(System.in);

        System.out.println("TE HAGO EL PROMEDIO DE CUATRO NÚMEROS:");
        System.out.println("Introduce el primer número:");
        num1 = numScanner.nextFloat();
        System.out.println("Introduce el segundo número:");
        num2 = numScanner.nextFloat();
        System.out.println("Introduce el tercer número:");
        num3 = numScanner.nextFloat();
        System.out.println("Introduce el cuarto número:");
        num4 = numScanner.nextFloat();

        // Operamos el promedio
        promedio = (num1 + num2 + num3 + num4) / 4;

        System.out.println("El promedio de " + num1 + ", " + num2 + ", " + num3 + " y " + num4 + " es " + promedio);
    }
}
