/*
 * Conversión de monedas Pide al usuario una cantidad de euros y convierte a dólares y yenes 
 * usando constantes de tipo entero (por ejemplo, 1 € = 2 $ y 1 € = 150 ¥).
 */

import java.util.Scanner;

public class Con_constantes_1 {
    public static void main(String[] args) {

        // Variables a usar
        final float converDolar = 0.85f;
        final float converYen = 0.0057f;
        float yenes;
        float dolar;
        float euro;

        // Arrancamos la librería de Scanner
        Scanner euScan = new Scanner(System.in);

        System.out.println("Esto es un conversor de euros a $ y ¥");
        System.out.print("\nIntroduce el dinero a convertir: ");
        euro = euScan.nextFloat();

        // Operamos las conversiones
        yenes = euro / converYen;
        dolar = euro / converDolar;

        // Mostramos la salida por pantalla
        System.out.println("\n" + euro + " € son = " + dolar + " $");
        System.out.println("y " + euro + " € son = " + yenes + " ¥");
    }
}
