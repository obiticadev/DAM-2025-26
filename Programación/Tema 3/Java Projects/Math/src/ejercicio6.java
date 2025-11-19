/*
 * PIEDRA PAPEL O TIJERA
 * Comenzamos la partida,
 * Definimos cuál es el valor por defecto de la jugada de la máquina
 * PIEDRA, PAPEL O TIJERA
 * 
 * Comprobamos que la jugada que introduce el usuario es válida
 * si no es válida, seguiremos pidiendo su jugada
 * El juego continuará, hasta que el usuario adivine el resultado
 */

import java.util.Scanner;

public class ejercicio6 {
    public static void main(String[] args) throws Exception {


        // Declaración de variables
        final String MAQUINA = "PAPEL";
        String jugada;

        Scanner lineScan = new Scanner(System.in);
        System.out.println("Elige: PIEDRA, PAPEL O TIJERA:");
        jugada = lineScan.nextLine();

        do{
            switch (jugada) {
                case "PAPEL":
                    System.out.println("JUGADOR: " + jugada + "\nMÁQUINA: " + MAQUINA + "\n¡EMPATE!" );
                    break;
                case "PIEDRA":
                    System.out.println("JUGADOR: " + jugada + "\nMÁQUINA: " + MAQUINA + "\nGANA : " + MAQUINA);
                    break;
                case "TIJERA":
                    System.out.println("JUGADOR: " + jugada + "\nMÁQUINA: " + MAQUINA + "\nGANA : " + jugada);
                    
                    break;
            
                default:
                    break;
            }
        }while (!jugada.equals("PIEDRA") && !jugada.equals("PAPEL") && !jugada.equals("TIJERA"));
        
        
    }
}
