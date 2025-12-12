import Clases.ParkingBicis;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ParkingBicis bici = new ParkingBicis();
        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        String respuestaMenu;
        String respuesta;

        do {
            try {
                System.out.println("""
                        MENU
                                ----

                                1) MOSTRAR BICICLETAS EN EL PARKING
                                2) MOSTRAR NÚMERO TOTAL
                                3) REGISTRAR ENTRADA
                                4) REGISTRAR SALIDA

                                S) SALIR
                                """);
                respuestaMenu = sc.nextLine().toUpperCase();

                switch (respuestaMenu) {
                    case "1" -> {
                        System.out.println("Bicicletas en el parking: " + bici.mostrarBicicletas());
                    }
                    case "2" -> {
                        System.out.println("Total de bicicletas: " + bici.totalBicicletas());
                    }
                    case "3" -> {
                        System.out.print("Introduce el código de tu bici: ");
                        respuesta = sc.nextLine();
                        // **CORRECCIÓN:** Se comprueba el valor de retorno para dar feedback.
                        if (bici.registrarEntrada(respuesta)) {
                            System.out.println("ÉXITO: Bicicleta " + respuesta + " registrada.");
                        } else {
                            System.out.println(
                                    "ERROR: No se pudo registrar la entrada. (Parking lleno o código ya existe).");
                        }
                    }
                    case "4" -> {
                        System.out.print("Introduce el código de tu bici: ");
                        respuesta = sc.nextLine();
                        // **CORRECCIÓN:** Se comprueba el valor de retorno para dar feedback.
                        if (bici.registrarSalida(respuesta)) {
                            System.out.println("ÉXITO: Salida de bicicleta " + respuesta + " registrada.");
                        } else {
                            System.out
                                    .println("ERROR: No se pudo registrar la salida. (Código de bici no encontrado).");
                        }
                    }
                    case "S" -> {
                        System.out.println("Saliendo del programa...");
                        continuar = false;
                    }

                    default -> {
                        System.out.println("Introduce una opción válida");
                    }
                }
            } catch (Exception e) {
                // Se mantiene el catch, pero se podría refinar para errores específicos.
                System.out.println("Ha ocurrido un error inesperado. Inténtalo de nuevo.");
            }

        } while (continuar);

        sc.close(); // Buena práctica: cerrar el Scanner al salir.
    }
}