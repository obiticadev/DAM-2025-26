import Clases.Categoria;
import Clases.Estado;
import Clases.Tarea;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int numTareas = 0;
        while (numTareas <= 0) {
            try {
                System.out.print("¿Cuántas tareas quieres crear? : ");
                numTareas = Integer.parseInt(sc.nextLine());
                if (numTareas <= 0) {
                    System.out.println("\nSolo valores positivos\n");
                }
                System.out.println();

            } catch (Exception e) {
                
                System.out.println("\nIntroduce un valor numérico entero\n");
            }

        }

        Tarea[] canva = new Tarea[numTareas];

        for (int i = 0; i < canva.length; i++) {

            String nombreTarea;

            Categoria categoriaElegida = null;
            String categoriaString;

            Estado estadoElegido = null;
            String estadoString;

            LocalDate fechaElegida = null;
            int diasVencimiento;

            LocalTime horaElegida = null;

            System.out.print("\n=== TÍTULO " + (i+1) + " ===\nIntroduce el título de la tarea "  + (i+1) + ": ");
            nombreTarea = sc.nextLine();
            while (categoriaElegida == null) {
                System.out.println("\n=== CATEGORÍA ===");
                System.out.println("Introduce la categoría de la tarea: ");
                System.out.println("> TRABAJO\n" + //
                        "> ESTUDIO\n" + //
                        "> PERSONAL\n" + //
                        "> OTRO\n");
                        System.out.print(" > ");
                try {
                    categoriaString = sc.nextLine().toUpperCase().trim();
                    categoriaElegida = Categoria.valueOf(categoriaString);
                    System.out.println();
                } catch (Exception e) {
                    
                    System.out.println("\nCategoría no válida, inténtalo de nuevo\n");
                }

            }

            while (estadoElegido == null) {
                System.out.println("\n=== ESTADO ===");
                System.out.println("Introduce el estado de la tarea:");
                System.out.println("PENDIENTE\n" + //
                        "EN_PROGRESO\n" + //
                        "COMPLETADA\n" + //
                        "CANCELADA\n");
                        System.out.print(" > ");
                try {
                    estadoString = sc.nextLine().toUpperCase().trim();
                    estadoElegido = Estado.valueOf(estadoString);
                    System.out.println();
                } catch (Exception e) {
                    
                    System.out.println("\nEstado no válido, inténtalo de nuevo\n");
                }
            }
            while (fechaElegida == null) {
                System.out.println("=== DÍAS ===");
                System.out.print("Introduce cuántos días tienes para completarlo: ");
                String entrada = sc.nextLine();
                if (validarEntrada(entrada)) {
                    diasVencimiento = Integer.parseInt(entrada);
                    fechaElegida = LocalDate.now().plusDays(diasVencimiento);
                } else {
                    System.out.println("\nIntroduce un número entero\n");
                }
            }

            while (horaElegida == null) {
                System.out.println("\n=== HORA ===");
                System.out.print("Introduce la hora de vencimiento: ");
                String entradaHora = sc.nextLine();
                if (validarEntrada(entradaHora)) {
                    System.out.print("\nIntroduce los minutos de vencimiento: ");
                    String entradaMin = sc.nextLine();
                    if (validarEntrada(entradaMin)) {
                        try {
                            horaElegida = LocalTime.of(Integer.parseInt(entradaHora), Integer.parseInt(entradaMin), 0);

                        } catch (Exception e) {
                            
                            System.out.println("\nIntroduce una fecha válida\n");
                        }
                    }

                }
            }
            canva[i] = new Tarea(nombreTarea, categoriaElegida, estadoElegido, fechaElegida, horaElegida);

            System.out.println("\nTarea guadada con éxito\n");

        }

        mostrarDatos(canva);
        System.out.println("\n=== ANALIZANDO TAREAS VENCIDAS ===\n");
        for (int j = 0; j < canva.length; j++) {
            System.out.println("La tarea " + (j+1) + ", ¿tiene la fecha vencida? " + canva[j].tareaVencida());
        }
        System.out.println("¿Hay alguna tarea igual a otra?");
        for (int j = 0; j < canva.length; j++) {
            System.out.println("\n Tarea " + (j+1) + ":\n");
            for (int j2 = 0; j2 < canva.length; j2++) {
                if (j == canva.length -1 && j2 == canva.length) {
                    break;
                }
                if (j == j2) {
                    continue;
                }
                System.out.println("¿Tarea " + (j+1) + ": " + canva[j].getTitulo() + ", es igual a Tarea " + (j2 + 1) + ": " + canva[j2].getTitulo() + "? " + canva[j].equals(canva[j2]));
            }
        }

    }

    public static void mostrarDatos(Tarea[] tarea) {
        System.out.println("""
                === TAREAS ===
                """);
        for (int i = 0; i < tarea.length; i++) {
            System.out.println((i + 1) + ") " + tarea[i].toString());
        }
    }

    public static boolean validarEntrada(String entrada) {

        try {
            Integer.parseInt(entrada);
            return true;
        } catch (Exception e) {
            
            return false;
        }

    }
}
