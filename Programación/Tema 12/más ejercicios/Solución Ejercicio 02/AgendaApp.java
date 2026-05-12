import java.util.Scanner;

public class AgendaApp {
    public static void main(String[] args) {
        int opcion;
        do {
            GestorAgenda.mostrarMenu();
            opcion = GestorAgenda.leerOpcion();

            switch (opcion) {
                case 1:
                    GestorAgenda.cargarDesdeArchivoTexto();
                    break;
                case 2:
                    GestorAgenda.agregarNuevoContacto();
                    break;
                case 3:
                    GestorAgenda.mostrarContactos();
                    break;
                case 4:
                    GestorAgenda.guardarSerializado();
                    break;
                case 5:
                    GestorAgenda.cargarSerializado();
                    break;
                case 6:
                    System.out.println("Saliendo del programa. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }
}
