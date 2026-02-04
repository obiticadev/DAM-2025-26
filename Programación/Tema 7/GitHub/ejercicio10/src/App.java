import java.util.ArrayList;
import java.util.Scanner;

import Clases.DeporteInvierno;
import Clases.PatinajeArtistico;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<DeporteInvierno> deportes = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        boolean continuar = true;
        String respuesta;
        
        do {
            System.out.print("""
                    === MENU ===
                    
                    1. Agregar patinaje artístico
                    2. Agregar equipo alpino
                    3. Mostrar todo

                    S. Salir
                    
                    Selecciona una opción:\t""");
                    respuesta = sc.nextLine().toUpperCase();
                    switch (respuesta) {
                        case "1" -> {

                        }
                        case "2" -> {

                        }
                        case "3" -> {

                        }
                        case "S" -> {
                            continuar = false;
                            System.out.println("Saliendo del programa...");
                        }
                    
                        default -> {
                            System.out.println("Selecciona una opción válida");
                        }
                    }
        } while (continuar);
        
    }

    public static boolean validarEntrada(String entrada){
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static int preguntaConRango(String pregunta, int rangoInferior, int rangoSuperir){
        int numParticipantes = 0;
        boolean continuar = true;
        do {
            System.out.print(pregunta + ": ");
            String numParticipantesString = sc.nextLine();
            if (validarEntrada(numParticipantesString)) {
                numParticipantes = Integer.parseInt(numParticipantesString);
                if (numParticipantes >= rangoInferior && numParticipantes <= rangoSuperir) {
                    continuar = false;
                } else {
                    System.out.println("Introduce un valor entre el " + rangoInferior + " y " + rangoSuperir);
                }
            } else {
                System.out.println("Introduce un valor entero");
            }

        } while (continuar);
        return numParticipantes;
    }
    
    public static DeporteInvierno agregarPatinajeArtistico(){
        
        System.out.print("Introduce nombre: ");
        String nombre = sc.nextLine();
        int numParticipantes = preguntaConRango("Introduce el número de participantes", 1, 10);
        int dificultad = preguntaConRango("Introduce la dificultad", 1, 10);
        int ejecucion = preguntaConRango("Introduce la ejecución", 1, 2);
        
        return new PatinajeArtistico(nombre, numParticipantes, dificultad, ejecucion);
        
    }
    
}
