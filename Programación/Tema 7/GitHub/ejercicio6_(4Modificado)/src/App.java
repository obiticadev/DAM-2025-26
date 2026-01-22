import Clases.Categoria;
import Clases.Estado;
import Clases.Tarea;
import Clases.TodoList;

import java.awt.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static TodoList lista = new TodoList();
    public static void main(String[] args) throws Exception {


        boolean continuar = true;
        String respuestaMenu;
        
        do {
            System.out.println("""
                    === MENU ===
    
                    1) Añadir tareas
                    2) Modificar tareas
                    3) Listar tareas por fecha
    
                    S) Salir
                    """);
                    respuestaMenu = sc.nextLine();
                    switch (respuestaMenu) {
                        case "1" -> {
                            System.out.println("Puedes crear " + (lista.getLONGITUD_MAX()-lista.getContador()) + "tareas");
                            System.out.print("Introduce el número de tareas que deseas crear: ");
                            String respuesta = sc.nextLine();
                            if (validarEntrada(respuesta)) {
                                if (Integer.parseInt(respuesta) > 0 && Integer.parseInt(respuesta) <= (lista.getLONGITUD_MAX()-lista.getContador())) {
                                    añadirTareaMenu();
                                } else {
                                    System.out.println("Introduce un valor numérico dentro del rango marcado");
                                }
                            } else {
                                System.out.println("Introduce un valor numérico");
                            }
                            
                        }

                        case "2" -> {

                        }

                        case "3" -> {

                        }
                    
                        default -> {

                        }
                    }

                    
        } while (continuar);

        

        mostrarDatos(lista.getTarea());
        System.out.println("\n=== ANALIZANDO TAREAS VENCIDAS ===\n");
        for (int j = 0; j < lista.getTarea().length; j++) {
            System.out.println("La tarea " + (j + 1) + ", ¿tiene la fecha vencida? " + lista.getTarea()[j].tareaVencida());
        }
        System.out.println("¿Hay alguna tarea igual a otra?");
        for (int j = 0; j < lista.getTarea().length; j++) {
            System.out.println("\n Tarea " + (j + 1) + ":");
            for (int j2 = 0; j2 < lista.getTarea().length; j2++) {
                if (j == lista.getTarea().length - 1 && j2 == lista.getTarea().length) {
                    break;
                }
                if (j == j2) {
                    continue;
                }
                System.out.println("¿Tarea " + (j + 1) + "; " + lista.getTarea()[j].getTitulo() + ", es igual a Tarea " + (j2 + 1)
                        + "; " + lista.getTarea()[j2].getTitulo() + "?\n" + lista.getTarea()[j].equals(lista.getTarea()[j2]));
            }
        }

    }

    public static void añadirTareaMenu(){
        for (int i = 0; i < lista.getTarea().length; i++) {

            String nombreTarea;

            Categoria categoriaElegida = null;
            String categoriaString;

            Estado estadoElegido = null;
            String estadoString;

            LocalDate fechaElegida = null;
            int diasVencimiento;

            LocalTime horaElegida = null;

            LocalDateTime fechaCompleta = null;

            System.out.print("\n=== TÍTULO " + (i + 1) + " ===\nIntroduce el título de la tarea " + (i + 1) + ": ");
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
            while (fechaCompleta == null) {

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
                                horaElegida = LocalTime.of(Integer.parseInt(entradaHora), Integer.parseInt(entradaMin),
                                        0);

                            } catch (Exception e) {

                                System.out.println("\nIntroduce una fecha válida\n");
                            }
                        }

                    }
                }
                fechaCompleta = LocalDateTime.of(fechaElegida.getYear(), fechaElegida.getMonth(), fechaElegida.getDayOfMonth(), horaElegida.getHour(), horaElegida.getMinute(), horaElegida.getSecond());
                if (!lista.validarFechaCompleta(fechaCompleta)) {
                    fechaCompleta = null;
                    System.out.println("Fecha duplicada");
                }
            }
            Tarea tareaRecopilada = new Tarea(nombreTarea, categoriaElegida, estadoElegido, fechaElegida, horaElegida);
            lista.añadirTarea(tareaRecopilada);

            System.out.println("\nTarea guadada con éxito\n");

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
