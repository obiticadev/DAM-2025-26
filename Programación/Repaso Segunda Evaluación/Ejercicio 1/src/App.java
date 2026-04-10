import java.util.Scanner;

import Clases.SesionSala;
import DAO.DAOsalas;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static DAOsalas dao = new DAOsalas();

    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        String respuestaMenu;

        do {
            menu();
            respuestaMenu = sc.nextLine();
            switch (respuestaMenu) {
                case "1" -> {
                    startSesion();
                }
                case "2" -> {
                    subMenu();
                }
                case "3" -> {
                    if (reservarAsiento()) {
                        System.out.println("Agregado con éxito");
                    } else {
                        System.out.println("Algo ha fallado");
                    }
                }
                case "4" -> {
                    if (liberarAsiento()) {
                        System.out.println("Liberado con éxito");
                    } else {
                        System.out.println("Algo ha fallado");
                    }
                }
                case "5" -> {

                }
                case "0" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (continuar);
    }

    private static void menu() {
        System.out.print("""
                MENÚ

                1) Agregar sala
                2) Mostrar estado de la sala
                3) Reservar asiento
                4) Liberar asiento
                5) Ver la recaudación de la sala

                0) Salir

                Selecciona una opción: \t""");
    }

    private static void subMenu() {
        System.out.println("SALAS\n");
        for (int i = 0; i < dao.getListaSalas().size(); i++) {
            System.out.println("Sala " + (i + 1) + ":\n" + dao.getListaSalas().get(i).mostrarSala());
        }
    }

    private static double preguntaDouble(String preguna) {
        boolean continuar = true;
        double respuestaDouble = -1;
        do {
            try {
                System.out.print(preguna);
                String respuestaString = sc.nextLine().trim();
                respuestaDouble = Double.parseDouble(respuestaString);
                continuar = false;
            } catch (Exception e) {
                System.out.println("Introduce un número válido");
            }
        } while (continuar);
        return respuestaDouble;
    }

    private static boolean startSesion() {
        boolean continuar = true;
        do {
            try {
                int fila = (int) preguntaDouble("Introduce el número de filas: ");
                int columnas = (int) preguntaDouble("Introduce el número de columnas: ");
                double precioEntrada = preguntaDouble("Introduce el precio de entrada: ");
                int numSesion = (int) preguntaDouble("Introduce el número de sesión");
                dao.agregarSesion(fila, columnas, precioEntrada, numSesion);
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Algo raro ha ocurrido");
            }

        } while (continuar);
        return false;
    }

    private static int seleccionarSala() {
        int sala = (int) preguntaDouble("Selecciona una sala del 1 al " + (dao.getListaSalas().size() + 1) + ": ") - 1;
        if (sala < 0) {
            sala = 0;
        } else if (sala > dao.getListaSalas().size() - 1) {
            sala = dao.getListaSalas().size() - 1;
        }
        return sala;
    }

    private static boolean reservarAsiento() {
        int fila = (int) preguntaDouble("Introduce una fila: ");
        int columna = (int) preguntaDouble("Introduce una columna: ");
        return dao.getListaSalas().get(seleccionarSala()).reservarAsiento(fila, columna);
    }

    private static boolean liberarAsiento() {
        int idReserva = (int) preguntaDouble("Introduce un id de reserva: ");
        return dao.getListaSalas().get(seleccionarSala()).liberarAsiento(idReserva);
    }

    private static void recaudacionTotal() {

    }

}
