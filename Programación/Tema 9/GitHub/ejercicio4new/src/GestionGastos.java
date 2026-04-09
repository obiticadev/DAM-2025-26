import java.util.List;
import java.util.Scanner;

import Clases.Persona;
import DAO.DAOparticipantes;

// OLIVER Y RUBÉN
public class GestionGastos {
    private static DAOparticipantes dao = new DAOparticipantes();
    private static List<Persona> listaPersonas = dao.devolverDatos();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        String respuestaMenu;
        do {
            menu();
            respuestaMenu = sc.nextLine();
            switch (respuestaMenu) {
                case "1" -> {
                    for (Persona persona : listaPersonas) {
                        System.out.println(persona.toString() + "\n-------\n");
                    }
                }
                case "2" -> {

                }
                case "3" -> {

                }
                case "4" -> {

                }
                case "5" -> {

                }
                case "S" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (continuar);
    }

    public static void menu() {
        System.out.print("""
                === MENÚ ===
                1. Listar participantes
                2. Listar transacciones de ingresos
                3. Listar transacciones de gastos
                4. Agregar transacción de ingreso
                5. Agregar transacción de gasto

                S. Salir

                Selecciona una opción: """);
    }

}
