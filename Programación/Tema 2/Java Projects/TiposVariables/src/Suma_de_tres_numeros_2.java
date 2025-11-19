import java.util.Scanner;

/*
 * Edad futura Pide al usuario su edad actual y calcula cuántos años tendrá dentro de 5, 10 y 20 años (usa constantes para esos intervalos).
 */

public class Suma_de_tres_numeros_2 {
    
    
    
    public static void main(String[] args) {
        // Declaración de variables fijas
        final int enCinco = 5;
        final int enDiez = 10;
        final int enVeinte = 20;
        // Declaración de variables
        int edadActual;
        int resultadoCinco;
        int resultadoDiez;
        int resultadoVeinte;

        Scanner numScanner = new Scanner(System.in);
        System.out.println("¿Cuántos años tienes?");
        edadActual = numScanner.nextInt();

        resultadoCinco = edadActual + enCinco;
        resultadoDiez = edadActual + enDiez;
        resultadoVeinte = edadActual + enVeinte;
        
        System.out.println("Tienes " + edadActual + " años");
        System.out.println("En cinco años tendrás: " + resultadoCinco + " años");
        System.out.println("En diez años tendrás: " + resultadoDiez + " años");
        System.out.println("En veinte años tendrás: " + resultadoVeinte + " años");
        
    }
}
