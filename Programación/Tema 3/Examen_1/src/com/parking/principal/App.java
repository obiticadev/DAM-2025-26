package com.parking.principal;
import java.util.Scanner;
import com.parking.utilidades.Utilidades;

public class App {
    public static void main(String[] args) throws Exception {

        // Declaración de variables
        final int NUM_PLAZAS = 25;
        int respuestaScan;
        boolean esCoche = true;
        double numAdultos;
        int numMenores;
        int numCoches = 0;
        int edad;
        int numAdultosContados = 0;
        int numMenoresContados = 0;
        int numPersonas = 0;
        int contadorEdad = 0;
        double media;


        // Inicializamos Scanner
        Scanner lineScan = new Scanner(System.in);

        System.out.println("""
                \n\n\nESTADÍSTICA DEL PARKING
                -----------------------
                """);

                //  Aqui preguntamos por los coches

        for (int i = 1; i <= NUM_PLAZAS; i++) {
            System.out.print(
                    "Entra el vehículo " + i + "\n¿Es un coche?\n\n1) Sí\n2) No\n\nTeclea una opción numérica: ");
            respuestaScan = lineScan.nextInt();
            switch (respuestaScan) {
                case 1 -> {
                    // Se suma al contador si es si
                    System.out.println("¡Es un coche!\n");
                    numCoches = numCoches + 1;
                }
                case 2 -> {
                    System.out.println("Guardada la respuesta, no se generará estadísticas de este vehículo\n\n");
                    esCoche = false;
                }
                default -> {
                    System.out.println("Opción no válida, se repite la pregunta\n\n");
                    i--;
                }
            }
            if (esCoche) {
                System.out.println("\n¿Cuántos adultos hay?");
                numAdultos = lineScan.nextDouble();
                numAdultosContados = numAdultosContados + ( (int) numAdultos);

                System.out.println("\n¿Cuántos menores?");
                numMenores = lineScan.nextInt();
                numMenoresContados = numMenoresContados + numMenores;

                numPersonas = numMenoresContados + numAdultosContados;

                for (int j = 1; j <= numAdultos; j++) {
                    edad = Utilidades.devolverEdadValida(numAdultos);
                    contadorEdad = contadorEdad + edad;
                }
                media = (contadorEdad / numAdultos)
            }

        }

        // Se calcula la media para sacar el porcentaje
        int porcentaje = (numCoches / NUM_PLAZAS) *100;
        if (porcentaje > 50) {
            System.out.println("PARKING CON MUCHA AFLUENCIA");
            
        }

        System.out.println("Total de plazas ocupadas por coches: " + numCoches);
        System.out.println("Total de personas: " + numPersonas);
        System.out.println("Total de adultos: " + numAdultosContados);
        System.out.println("Total de menores: " + numMenoresContados);
        System.out.println("Media de edad de los adultos: " + media);




    }
}
