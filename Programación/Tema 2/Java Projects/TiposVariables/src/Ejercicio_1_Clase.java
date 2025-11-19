import java.util.Scanner;

public class Ejercicio_1_Clase {
/*
 * Pide al usuario los siguientes datos
 * Nombre (Cadena de caracteres)
 * Apellidos (Cadena de caracteres)
 * Código Postal (Es un número entero)
 * Dirección
 * - calle (Cadena de caracteres)
 * - número (Número entero)
 * - piso (Número entero)
 * - letra (Cadena de caracteres)
 * - localidad (cadena de caracteres)
 * Se imprimirá por consola el resultado de la dirección en el siguiente formato:
 * NOMBRE Y PELLIDOS: XXXXXX CÓDIGO POSTAL: XXXXXX DIRECCIÓN: calle, número, piso, letra LOCALIDAD:XXXX
 */
    public static void main(String[] args) {

        // Declaración de variables
        String nombre;
        String calle;
        int postal;
        int numero;
        int piso;
        String letra;
        String localidad;

        // Llamadas de tipos de entradas en la terminal
        Scanner numScan = new Scanner(System.in);
        Scanner letraScan = new Scanner (System.in);

        System.out.println("INTRODUCE TUS DATOS A CONTINUACIÓN:");

        System.out.print("Nombre y Apellido: ");
        nombre = letraScan.nextLine();
        System.out.print("Calle: ");
        calle = letraScan.nextLine();
        System.out.print("Número: ");
        numero = numScan.nextInt();
        System.out.print("Piso: ");
        piso = numScan.nextInt();
        System.out.print("Letra: ");
        letra = letraScan.nextLine();
        System.out.print("Localidad: ");
        localidad = letraScan.nextLine();
        System.out.print("Código Postal: ");
        postal = numScan.nextInt();

        System.out.println("\nTus datos son los siguientes:\n\nNombre: " + nombre + ", CP: " + postal + ",\nDirección: " + calle + ", Número: " + numero + ", Piso: " + piso + ", Letra: " + letra + ",\ny Localidad en: " + localidad);
    }
}