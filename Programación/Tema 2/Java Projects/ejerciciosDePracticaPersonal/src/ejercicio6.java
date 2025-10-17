
import java.util.Scanner;

/*Ejercicio 6: Adivina el Código ASCII
Objetivo: Combinar la lectura de un carácter con el casting explícito.
Usando Scanner, pide al usuario que ingrese un solo carácter.
Lee el carácter (recuerda que se hace con scanner.next().charAt(0)).
Realiza una conversión explícita (casting) de la variable char a int para obtener su valor ASCII/Unicode.
Imprime en pantalla un mensaje que muestre tanto el carácter ingresado como su valor numérico.
Ejemplo de salida:

Ingresa un carácter: @
El valor ASCII del carácter '@' es: 64*/


public class ejercicio6 {
    public static void main(String[] args) throws Exception {
        System.out.println("Ejercicio 6: Adivina el Código ASCII");

        // Declaración de variables
        String primeraSalida;
        char caracter;
        
        Scanner scanLine = new Scanner(System.in);
        System.out.print("Ingresa un carácter: ");
        
        // PRIMERA OPCIÓN:
        // primeraSalida = scanLine.nextLine();
        // caracter = primeraSalida.charAt(0);

        // SEGUNDA OPCIÓN:
        caracter = scanLine.next().charAt(0);
        int asciiCaracter = caracter;

        // Salida a la terminal
        System.out.println("El valor ASCII del carácter '" + caracter + "' es: " + asciiCaracter);
 
    }
}
