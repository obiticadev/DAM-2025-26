import java.util.Scanner;

public class Suma_de_tres_numeros_1 {

    /*
     * Pide al usuario tres números enteros y muestra su suma, su producto y su
     * promedio.
     * 
     */
    public static void main(String[] args) {

        // Declaración de variables
        int num1;
        int num2;
        int num3;
        int suma;
        int producto;
        int promedio;

        System.out.println("INTRODUCE TRES NÚMEROS:");

        Scanner numScan = new Scanner(System.in);
        System.out.println("Primer número: ");
        num1 = numScan.nextInt();
        System.out.println("Segundo número: ");
        num2 = numScan.nextInt();
        System.out.println("Tercer número: ");
        num3 = numScan.nextInt();

        System.out.println("Los números que has elegido son: " + num1 + ", " + num2 + " y " + num3);

        suma = num1 + num2 + num3;
        producto = num1 * num2 * num3;
        promedio = suma / 3;

        System.out.println("La suma de los números es: " + suma + ",");
        System.out.println("el producto de los número es: " + producto + " y");
        System.out.println("el promedio de los números es de: " + promedio);

    }
}
