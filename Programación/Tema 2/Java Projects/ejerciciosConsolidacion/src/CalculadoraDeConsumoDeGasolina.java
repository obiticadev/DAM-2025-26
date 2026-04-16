import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class CalculadoraDeConsumoDeGasolina {

/*
 * Los litros necesarios para realizar el viaje.
 * El coste total del viaje.
 * conversión implícita En el cálculo, deja que Java convierta automáticamente un int a double o float si es necesario.
 * Deberás usar operadores como la multiplicación y división para obtener el consumo y el coste.
 * Muestra los resultados personalizados para el ususario usando printf para dar formato de la siguiente forma:
 * Los litros necesarios con 2 decimales.
 * El coste total con 2 decimales y el símbolo €.
 * 
 */
    
    public static void main(String[] args) throws Exception {
        
        // Declaración de variables
        String user;
        int distViaje;
        float consumoMedio;
        double precioLitro;
        final int KM_POR_100 = 100;

        // Declaración de variables para guardar las operaciones pedidas
        double litrosTotales;
        double costeTotal;

        // Arrancamos la librería Scanner
        Scanner stringScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        Scanner floatScanner = new Scanner(System.in);
        Scanner doubleScanner = new Scanner(System.in);
        
        System.out.println("RECOGIDA DE DATOS:");

        System.out.println("¿Cuál es tu nombre?");
        user = stringScanner.nextLine();
        System.out.println("¿Cuál es la distancia en kilómetros?");
        distViaje = intScanner.nextInt();
        System.out.println("¿Cuál es el consumo medio del coche por cada 100km?");
        consumoMedio = floatScanner.nextFloat();
        System.out.println("¿Cuál es el precio del litro de gasolina?");
        precioLitro = doubleScanner.nextDouble();

        litrosTotales = distViaje * (consumoMedio / KM_POR_100);
        System.out.println("Necesitarás " + String.format("%.2f", litrosTotales) + " Litros para completar el viaje");

        costeTotal = litrosTotales * precioLitro;
        System.out.println("El coste total del viaje es de " + String.format("%.2f", costeTotal) + "€");

        
        
    }
}
