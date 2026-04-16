import java.util.Scanner;

import Clases.DepositoAgua;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Scanner scString = new Scanner(System.in);

        double capacidadDeposito;
        boolean continuar = true;
        String respuestaMenu;
        double respuestaDeposito;

        
        System.out.println("¿De cuánto quieres que sea tu depósito?");
        capacidadDeposito = sc.nextDouble();
        DepositoAgua tanque = new DepositoAgua(capacidadDeposito);

        do {
            System.out.println("""
                    MENU
                    ----
    
                    1) MOSTRAR NIVEL ACTUAL
                    2) LLENAR DEPÓSITO
                    3) VACIAR DEPÓSITO
                    4) VACIAR COMPLETAMENTE
                    5) COMPROBAR SI ESTÁ VACÍO
                    6) COMPROBAR SI ESTÁ LLENO
    
                    S) SALIR
                    """);
                    respuestaMenu = scString.nextLine().toUpperCase();
                    switch (respuestaMenu) {
                        case "1" -> {
                            System.out.println("Nivel actual es de " + tanque.nivelActual() + "L");
                        }
                        case "2" -> {
                            try {
                                System.out.println("¿Cuántos litros quieres añadir al depósito?");
                                respuestaDeposito = sc.nextDouble();
                                tanque.llenar(respuestaDeposito);
                                
                            } catch (Exception e) {
                                // TODO: handle exception
                                System.out.println("Introduce una cantidad válida");
                            }
                        }
                        case "3" -> {
                            System.out.println("¿Cúantos litros quieres quitar al depósito?");
                            respuestaDeposito = sc.nextDouble();
                            tanque.vaciar(respuestaDeposito);
                        }
                        case "4" -> {
                            tanque.vaciarCompleto();
                        }
                        case "5" -> {
                            if (tanque.estaVacio()) {
                                System.out.println("El tanque está vacío");
                            } else {
                                System.out.println("El tanque no está vacío");
                            }
                        }
                        case "6" -> {
                            if (tanque.estaLleno()) {
                                System.out.println("El tanque está lleno");
                            } else {
                                System.out.println("El tanque no está lleno");
                            }
                        }
                        case "S" -> {
                            System.out.println("Saliendo del programa...");
                            continuar = false;
                        }
                    
                        default -> {
                            System.out.println("Selecciona una opción válida del menú");
                        }
                    }
            
        } while (continuar);
    }
}
