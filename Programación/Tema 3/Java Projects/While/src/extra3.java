/*
 * Escribe un menú con 3 opciones
 * 1 - 2 - 3 (ENTEROS)
 * opción 1. Muestra el mensaje opcion 1
 * opcion 2. Muestra el mensaje opcion 2
 * opcion 3. Salir
 * El programa continuará mostrando el menú, hasta que se pulse la opcion
 */

import java.util.Scanner;

public class extra3 {
    public static void main(String[] args) throws Exception {
        String caracter;

        Scanner caracterScan = new Scanner(System.in);
        do{
            
            System.out.println("""
                    MENÚ CON 3 OPCIONES
                    -------------------
                    1. Opción 1
                    2. Opción 2
                    3. Salir


                    Elige una opción
                    """);
            caracter = caracterScan.nextLine();

            switch (caracter) {
                case "1":
                    System.out.print("\033[H\033[2J");
                    System.out.println("Opción 1");
                    
                    break;
                case "2":
                    System.out.print("\033[H\033[2J");
                    System.out.println("Opción 2");
                    
                    break;
                case "3":

                    break;
            
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }while(!caracter.equals("3"));
    }
}
