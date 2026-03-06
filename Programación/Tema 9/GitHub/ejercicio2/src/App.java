import java.time.LocalTime;
import java.util.Scanner;

import Clases.Coche;
import Clases.VehiculoAparcado;
import DAO.DAOParking;
import Excepciones.MiExcepcion;

public class App {
    private static DAOParking dao;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String respuestaMenu;
        boolean continuar = false;

        try {
            dao = new DAOParking();
        } catch (MiExcepcion e) {
            System.out.println("Revisar la matricula: " + e.getMatricula() + ", con la hora de entrada a las "
                    + e.getHoraEntrada());
        }

        do {
            System.out.println("=== MENU ===");
            System.out.println("1. Registrar entrada");
            System.out.println("2. Registrar salida");
            System.out.println("3. Lavar vehículo");
            System.out.println("4. Mostrar vehículos");
            System.out.println("5. Mostrar ingresos aculados");
            System.out.println("6. Salir");

            respuestaMenu = sc.nextLine();

            switch (respuestaMenu) {
                case "1" -> {
                    registrarEntrada();
                }
                case "2" -> {

                }
                case "3" -> {

                }
                case "4" -> {

                }
                case "5" -> {

                }
                case "6" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = true;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (!continuar);

    }

    private static void registrarEntrada() {

    }

    private static Object capturarRespuesta(String pregunta) {
        return pregunta;

    }
}
