/*
 * Escribe un programa de java que pida cuantos coches tienes
 * Si tienes uno : Muestra felicidades
 * Si tienes dos : eres un máquina
 * Si tienes tres : fantástico
 * Seguirá preguntando cuántos coches tienes si la respuesta es distinta a uno dos o tres
 * Obligatorio switch - case
 */

import java.nio.channels.Pipe.SourceChannel;
import java.util.Scanner;

public class extra2 {
    public static void main(String[] args) throws Exception {
        System.out.print("\033[H\033[2J");
        int num;

        Scanner numScan = new Scanner(System.in);
        do{
            
            System.out.println("""
                    ¿CUÁNTOS COCHES TIENES?
                    -------------------
                    1, 2 o 3
                    
                    Elige una opción
                    """);
            num = numScan.nextInt();

            switch (num) {
                case 1:
                    System.out.print("\033[H\033[2J");
                    System.out.println("Muestra felicidades");
                    
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    System.out.println("eres un máquina");
                    
                    break;
                case 3:
                    System.out.print("\033[H\033[2J");
                    System.out.println("fantástico");
                    break;
            
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.println("Elige una opción válida");
                    break;
            }
        }while(num < 1 || num > 3);
    }
}
