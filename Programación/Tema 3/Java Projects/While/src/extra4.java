/*
 * MUESTRA UN MENÚ EN EL QUE EL USUARIO ELIGE UNA OPCIÓN
 * Solamente se sale del menú puslando 3
 * Si el usuario pulsa la opción 1 le preguntaremos la edad.
 * Si es mayor o igual de 18, mostraremos el mensaje "No hay descuento"
 * Si es menor de 18 y mayor de 8, mostraremos el mensaje "Descuento del 50%"
 * Si es menor o igual a 8, mostraremos el mensaje "Gratis"
 * 
 * Si pulsa la opción 2: "En construcción"
 * OBLIGATORIO: Las opciones de menú deben hacerse usando un switch - case
 * OBLIGATORIO: Usar nextLine() para la captura por pantalla
 */

import java.util.Scanner;

public class extra4 {
    public static void main(String[] args) {
        System.out.println("\033[H\033[2J");

        String num;
        String edad;
        boolean bypass = true;

        Scanner numScan = new Scanner(System.in);
        do { 
            System.out.println("MENÚ");
            System.out.println("1. Calcular descuento por edad");
            System.out.println("2. En construcción");
            System.out.println("3. Salir");
            System.out.println("\nElige una opción:");
            
            num = numScan.nextLine();
            switch (num) {
                case "1": {
                    System.out.println("¿Qué edad tienes?");
                    edad = numScan.nextLine();
                    if (Integer.parseInt(edad) == 18 || Integer.parseInt(edad) > 18){
                        System.out.println("No hay descuento");

                    }else if (Integer.parseInt(edad) > 8 && Integer.parseInt(edad) < 18){
                        System.out.println("Descuento del 50%");
                    }else if (Integer.parseInt(edad) > 0 && Integer.parseInt(edad) <=8){
                        System.out.println("Gratis");
                    }else{
                        System.out.println("Introduce un valor correcto");
                    }
                    break;
                }
                case "2": {
                    System.out.println("En construcción");
                    break;
                }
                case "3": {
                    
                    break;
                }
                    
                    
            
                default:
                    break;
            }
        } while (!num.equals("3"));
        
    }
}
