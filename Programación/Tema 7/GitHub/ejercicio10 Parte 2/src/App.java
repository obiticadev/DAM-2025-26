import java.util.ArrayList;
import java.util.Scanner;

import Clases.DeporteInvierno;
import Clases.EquipoAlpino;
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
                    3. Mostrar todo sobre patinaje y equipos
                    4. 

                    S. Salir
                    
                    Selecciona una opción:\t""");
                    respuesta = sc.nextLine().toUpperCase();
                    switch (respuesta) {
                        case "1" -> {
                            deportes.add(agregarPatinajeArtistico());
                        }
                        case "2" -> {
                            deportes.add(agregarEquipoAlpino());
                        }
                        case "3" -> {
                            for (int i = 0; i < deportes.size(); i++) {
                                System.out.println(deportes.get(i).mostrarInfo().toString());
                            }
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
    
    public static int preguntaConRangoInt(String pregunta, int rangoInferior, int rangoSuperir){
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

    public static double preguntaConRangoDouble(String pregunta, int rangoInferior, int rangoSuperir){
        double numParticipantes = 0;
        boolean continuar = true;
        do {
            System.out.print(pregunta + ": ");
            String numParticipantesString = sc.nextLine();
            if (validarEntrada(numParticipantesString)) {
                numParticipantes = Double.parseDouble(numParticipantesString);
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
        int numParticipantes = preguntaConRangoInt("Introduce el número de participantes", 1, 10);
        int dificultad = preguntaConRangoInt("Introduce la dificultad", 1, 10);
        int ejecucion = preguntaConRangoInt("Introduce la ejecución", 1, 2);
        
        return new PatinajeArtistico(nombre, numParticipantes, dificultad, ejecucion);
        
    }

    public static DeporteInvierno agregarEquipoAlpino(){

        System.out.print("Introduce nombre: ");
        String nombre = sc.nextLine();
        int numParticipantes = preguntaConRangoInt("Introduce el número de participantes", 1, 10);
        double tiempoSegundos = preguntaConRangoDouble("Introduce el tiempo en segundos", 0, 30);
        int penalizaciones = preguntaConRangoInt("Introduce el número de penalizaciones", 0, 4);
        
        return new EquipoAlpino(nombre, numParticipantes, tiempoSegundos, penalizaciones);
        
    }


    
}
