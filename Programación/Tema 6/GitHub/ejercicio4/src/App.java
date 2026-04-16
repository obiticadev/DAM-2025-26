import Clases.Categoria;
import Clases.Estado;
import Clases.Tarea;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int numTareas = 0;
        while (numTareas <= 0) {
            try {
                System.out.println("¿Cuántas tareas quieres crear?");
                numTareas = Integer.parseInt(sc.nextLine());
                if (numTareas <= 0) {
                    System.out.println("Solo valores positivos");
                }
                System.out.println();

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("\nIntroduce un valor numérico entero\n");
            }

        }

        Tarea[] canva = new Tarea[numTareas];

        for (int i = 0; i < canva.length; i++) {

            String nombreTarea;
            Categoria categoriaElegida = null;
            String entradaString;
            Estado estadoElegido = null;
            String estadoString;

            System.out.print("Introduce el título de la tarea: ");
            nombreTarea = sc.nextLine();
            while (categoriaElegida == null) {
                System.out.println("Introduce la categoría de la tarea: ");
                System.out.println("> TRABAJO\n" + //
                        "> ESTUDIO\n" + //
                        "> PERSONAL\n" + //
                        "> OTRO\n");
                try {
                    entradaString = sc.nextLine().toUpperCase().trim();
                    categoriaElegida = Categoria.valueOf(entradaString);
                    System.out.println();
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("\nCategoría no válida, inténtalo de nuevo\n");
                }

            }

            while (estadoElegido == null) {
                System.out.println("Introduce el estado de la tarea:");
                System.out.println("PENDIENTE\n" + //
                        "EN_PROGRESO\n" + //
                        "COMPLETADA\n" + //
                        "CANCELADA\n");
                try {
                    estadoString = sc.nextLine().toUpperCase().trim();
                    estadoElegido = Estado.valueOf(estadoString);
                    System.out.println();
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("\nEstado no válido, inténtalo de nuevo\n");
                }
            }

            canva[i] = new Tarea(nombreTarea, categoriaElegida, estadoElegido);

            System.out.println("\nTarea guadada con éxito\n");

        }

        mostrarDatos(canva);

    }

    public static void mostrarDatos(Tarea[] tarea) {
        System.out.println("""
                === CANVAS ===
                """);
        for (int i = 0; i < tarea.length; i++) {
            System.out.println((i + 1) + ") " + tarea[i].toString());
        }
    }
}
