/*
Kilómetros recorridos

Pide al usuario los kilómetros recorridos en tres viajes. Usa una constante para el precio por kilómetro y calcula el gasto total.
 */
import java.util.Scanner;


public class Con_constantes_3 {
   public static void main(String[] args) {

        // Declaración de variables a usar
        float km1;
        float km2;
        float km3;
        final float PRECIO_KM = 1.24f; 
        float gasto;
        
        System.out.println("CALCULADORA DE GASTO POR KM RECORRIDO");
        Scanner numScanner = new Scanner(System.in);
        System.out.println("Introduce los km del primer trayecto");
        km1 = numScanner.nextFloat();
        System.out.println("Introduce los km del segundo trayecto");
        km2 = numScanner.nextFloat();
        System.out.println("Introduce los km del tercer trayecto");
        km3 = numScanner.nextFloat();

        gasto = (km1 + km2 + km3) * PRECIO_KM;

        System.out.println("El gasto total es de " + gasto + " €");
   } 
}
