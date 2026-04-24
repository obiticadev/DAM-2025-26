import java.time.LocalDate;
import java.util.Scanner;

import Clases.Libro;
import Clases.PrestamoEstandar;
import Clases.PrestamoReserva;
import DAO.DAOPrestamos;
import DAO.LibroDAO;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static DAOPrestamos daoPrestamos = new DAOPrestamos();
    private static LibroDAO daoLibro = new LibroDAO();

    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        String respuestaMenu;
        while (continuar) {
            menu();
            respuestaMenu = sc.nextLine();
            switch (respuestaMenu) {
                case "1" -> {
                    prestamoEstandar();
                }
                case "2" -> {
                    prestamoReserva();
                }
                case "3" -> {
                    catalogoLibros();
                }
                case "4" -> {
                    ultimoPrestamo();
                }
                case "0" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }

        }
    }

    private static void menu() {
        System.out.print("""
                ==== MENÚ ====
                1) Préstamo estándar
                2) Préstamo de reserva
                3) Catálogo de libros
                4) Último préstamo de un socio

                0) Salir

                Selecciona una opción:\s""");
    }

    private static String preguntaString(String pregunta) {
        System.out.print(pregunta);
        return sc.nextLine();
    }

    private static void prestamoEstandar() {
        try {
            LocalDate fechaPrestamo = LocalDate.parse(preguntaString("Introduce la fecha de préstamo (yyyy-MM-dd): "));
            LocalDate fechaDevolucion = LocalDate
                    .parse(preguntaString("Introduce la fecha de devolución (yyyy-MM-dd): "));
            String idSocio = preguntaString("Introduce el identificador del socio: ");
            String sala = preguntaString("Introduce la sala de recogida: ");
            daoPrestamos.agregarPrestamo(new PrestamoEstandar(fechaPrestamo, fechaDevolucion, idSocio, sala));
            System.out.println("Préstamo registrado correctamente");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error");
        }

    }

    private static void prestamoReserva() {
        try {
            LocalDate fechaPrestamo = LocalDate.parse(preguntaString("Introduce la fecha de préstamo (yyyy-MM-dd): "));
            LocalDate fechaDevolucion = LocalDate
                    .parse(preguntaString("Introduce la fecha de devolución (yyyy-MM-dd): "));
            String idSocio = preguntaString("Introduce el identificador del socio: ");
            String isbn = preguntaString("Introduce el identificador del libro: ");
            Libro libro = daoLibro.buscarPorSignatura(isbn);
            System.out.println("Libro encontrado: " + libro.toString());
            daoPrestamos.agregarPrestamo(new PrestamoReserva(fechaPrestamo, fechaDevolucion, idSocio, libro));
            System.out.println("Préstamo registrado correctamente");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error");
        }

    }

    private static void catalogoLibros() {
        System.out.println(daoLibro.listarLibros());
    }

    private static void ultimoPrestamo() {
        String idSocio = preguntaString("Introduce el identificador del socio: ");
        System.out.println(daoPrestamos.buscarUltimoPrestamoPorSocio(idSocio).toString());
    }
}
