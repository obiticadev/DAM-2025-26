import java.util.Scanner;

import Clases.SesionSala;
import DAO.DAOsalas;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static DAOsalas dao = new DAOsalas();

    public static void main(String[] args) {
        boolean continuar = true;

        do {
            menu();
            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1" -> mostrarEstadoSala();
                case "2" -> reservarAsiento();
                case "3" -> liberarAsiento();
                case "4" -> verRecaudacion();
                case "0" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }
                default -> System.out.println("Selecciona una opción válida.");
            }
        } while (continuar);

        sc.close();
    }

    private static void menu() {
        System.out.print("""
                
                ========= GESTIÓN CINE =========
                1) Mostrar estado de la sala
                2) Reservar asiento
                3) Liberar asiento
                4) Ver la recaudación de la sala
                0) Salir
                ================================
                Selecciona una opción:\s""");
    }

    // ── Opciones del menú ──

    private static void mostrarEstadoSala() {
        int numSesion = preguntaInt("Introduce el número de sesión: ");
        SesionSala sala = dao.buscarPorSesion(numSesion);
        if (sala == null) {
            System.out.println("No existe una sala con la sesión " + numSesion);
        } else {
            System.out.println(sala.mostrarSala());
        }
    }

    private static void reservarAsiento() {
        int numSesion = preguntaInt("Introduce el número de sesión: ");
        SesionSala sala = dao.buscarPorSesion(numSesion);
        if (sala == null) {
            System.out.println("No existe una sala con la sesión " + numSesion);
            return;
        }
        int fila = preguntaInt("Introduce la fila: ") - 1;
        int columna = preguntaInt("Introduce el número de asiento: ") - 1;
        if (sala.reservarAsiento(fila, columna)) {
            System.out.println("Asiento reservado con éxito.");
        } else {
            System.out.println("No se pudo reservar el asiento (fuera de rango o ya ocupado).");
        }
    }

    private static void liberarAsiento() {
        int idReserva = preguntaInt("Introduce el número de reserva: ");
        if (dao.liberarAsientoGlobal(idReserva)) {
            System.out.println("Reserva liberada con éxito.");
        } else {
            System.out.println("No se encontró una reserva con id " + idReserva);
        }
    }

    private static void verRecaudacion() {
        int numSesion = preguntaInt("Introduce el número de sesión: ");
        SesionSala sala = dao.buscarPorSesion(numSesion);
        if (sala == null) {
            System.out.println("No existe una sala con la sesión " + numSesion);
        } else {
            System.out.printf("Recaudación de la sesión %d: %.2f €%n", numSesion, sala.mostrarRecaudacion());
        }
    }

    // ── Utilidades de entrada ──

    private static int preguntaInt(String pregunta) {
        while (true) {
            try {
                System.out.print(pregunta);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

}
