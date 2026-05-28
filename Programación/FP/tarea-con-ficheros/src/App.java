import java.util.Scanner;

import Clases.GestorTareas;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static GestorTareas gestor = new GestorTareas();

    public static void main(String[] args) throws Exception {
        String respuestaMenu;
        boolean continuar = true;
        while (continuar) {
            menu();
            respuestaMenu = sc.nextLine().trim();
            switch (respuestaMenu) {
                case "1" -> {
                    System.out.println(gestor.mostrarTareas());
                }
                case "2" -> {
                    System.out.println(gestor.mostrarImportantes());
                }
                case "3" -> {
                    System.out.print("Nombre del fichero: ");
                    String fichero = sc.nextLine();
                    gestor.guardar(fichero);
                }
                case "4" -> {
                    System.out.print("Nombre del fichero: ");
                    String fichero = sc.nextLine();
                    gestor.cargar(fichero);
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

    public static void menu() {
        System.out.print("""
                === MENÚ ===
                1. Mostrar tareas
                2. Mostrar con prioridad
                3. Guardar tareas
                4. Cargar tareas

                0. Salir

                selecciona una opción:\s""");
    }
}
