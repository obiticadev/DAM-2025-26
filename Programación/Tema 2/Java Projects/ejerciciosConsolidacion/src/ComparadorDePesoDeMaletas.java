import java.util.Scanner;

public class ComparadorDePesoDeMaletas {

    /*
    Comparador de peso de maletas

    Deberás escribir un pequeño programa de facturación de equipaje en un aeropuerto que debe comprobar si una maleta cumple con el peso máximo permitido.
    
    El peso máximo permitido es = 23.2 (en kg).
    
    Entrada de datos: Pide al usuario su código de billete (puede contener letras y números) Pide al usuario el peso de su maleta (puede tener decimales).
    
    El probrama calculará :
    
    El peso aproximado en kilos (entero - usando conversión explícita).
    
    Evalúa, usando operadores relacionales, si el peso de la maleta:
    
    Es igual al máximo.
    
    Es menor al máximo.
    
    Es mayor al máximo.

        Introduce el peso de tu maleta (kg): 24.7

        Ejemplo de funcionamiento del programa
        Matela para embarque con código: A3204
        Peso real: 24.70 kg
        Peso aproximado (entero): 24 kg
        ¿Es igual al máximo? false
        ¿Es menor al máximo? false
        ¿Es mayor al máximo? true

    */
    
    public static void main(String[] args) {
        
        // Declaración de variables
        final float PESO_MALETA_MAX = 23.2f;
        String codigoVuelo;
        double pesoMaleta;
        int pesoAproxMaleta;
        boolean igualMax;
        boolean menorMax;
        boolean mayorMax;

        // Arrancamos la librería de Scanner
        Scanner letraScanner = new Scanner(System.in);
        Scanner numScanner = new Scanner(System.in);

        System.out.println("ENTREDA DE AEROPUERTO");
        System.out.print("\nIntroduce el código de tu billete: ");
        codigoVuelo = letraScanner.nextLine();
        System.out.print("Introduce el peso de tu maleta (kg): ");
        pesoMaleta = numScanner.nextDouble();

        pesoAproxMaleta = (int) pesoMaleta;
        System.out.println("Peso aproximado (entero): " + pesoAproxMaleta);

        igualMax = pesoAproxMaleta == PESO_MALETA_MAX;
        menorMax = pesoAproxMaleta < PESO_MALETA_MAX;
        mayorMax = pesoAproxMaleta > PESO_MALETA_MAX;

        System.out.println("¿Es igual al máximo? " + igualMax);
        System.out.println("¿Es menor al máximo? " + menorMax);
        System.out.println("¿Es mayor al máximo? " + mayorMax);

        
    }
}
