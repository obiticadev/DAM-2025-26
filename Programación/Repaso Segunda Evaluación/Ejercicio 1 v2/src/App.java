import java.util.Scanner;

import Clases.SesionSala;
import DAO.DAOsalas;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static DAOsalas dao = new DAOsalas();

    public static void main(String[] args) throws Exception {
        System.out.println();
        String respuestaMenu;
        boolean continuar = true;
        while (continuar) {
            menu();
            respuestaMenu = sc.nextLine().trim();
            switch (respuestaMenu) {
                case "1" -> mostrarEstado();
                case "2" -> reservarAsiento();
                case "3" -> liberarAsiento();
                case "4" -> verRecaudacion();
                case "0" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }

                default -> System.out.println("Selecciona una opción válida");
            }
        }
    }

    // UTILIDADES

    private static int preguntaInt(String pregunta) {
        while (true) {
            try {
                System.out.print(pregunta);
                return Integer.parseInt(sc.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido");
            }
        }
    }

    private static void menu() {
        System.out.print("""
                ======== GESTIÓN CINE ========
                1) Mostrar estado de la sala
                2) Reservar asiento
                3) Liberar asiento
                4) Ver la recaudación de la sala
                0) Salir
                ==============================
                Selecciona una opción:\s""");
    }

    // OPCIONES MENÚ

    private static void mostrarEstado() {
        int sesion = preguntaInt("Sesión [1.." + dao.getListaSalas().size() + "]: ");
        SesionSala sala = dao.buscarPorSesion(sesion);
        if (sala == null) {
            System.out.println("No existe una sala con la sesión " + sesion);
        } else {
            System.out.println(sala.mostrarAsiento());
        }
    }

    private static void reservarAsiento() {
        int sesion = preguntaInt("Sesión [1.." + dao.getListaSalas().size() + "]: ");
        SesionSala sala = dao.buscarPorSesion(sesion);
        if (sala == null) {
            System.out.println("No existe una sala con la sesión " + sesion);
            return;
        }
        int fila = preguntaInt("Fila [1.." + (sala.getAsientos().length) + "]: ") - 1;
        int columna = preguntaInt(
                "Columna [1.." + (sala.getAsientos()[0].length) + "]: ") - 1;
        if (sala.reservarAsiento(fila, columna)) {
            System.out.println("Asiento reservado");
        } else {
            System.out.println("No se puede reservar el asiento (Fuera de rango o ya ocupado)");
        }
    }

    private static void liberarAsiento() {
        int idReserva = preguntaInt("idReserva: ");
        if (dao.liberarAsientoGlobal(idReserva)) {
            System.out.println("Reserva liberada con éxito");
        } else {
            System.out.println("No se encontró una reserva con id " + idReserva);
        }

    }

    private static void verRecaudacion() {
        int sesion = preguntaInt("Sesión [1.." + dao.getListaSalas().size() + "]: ");
        SesionSala sala = dao.buscarPorSesion(sesion);
        if (sala == null) {
            System.out.println("No existe una sala con la sesión " + sesion);
            return;
        }
        double recaudacion = sala.mostrarRecaudacion();
        System.out.println("La recaudación fue de " + recaudacion);
    }
}
