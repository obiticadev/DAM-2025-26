import java.util.Scanner;

import Clases.Tarea;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        final int MAX_LISTA = 20;
        Tarea[] toDoList = new Tarea[MAX_LISTA];

        boolean continuar = true;
        String respuestaMenu;
        int contador = 0;
        int dias = 0;

        
        do {
            System.out.println("""
                    ----------
                    TO DO LIST
                    ----------

                    1) Introducir un elemento
                    2) Mostrar elementos

                    S) Salir 
                    """);
                    respuestaMenu = sc.nextLine();
                    switch (respuestaMenu) {
                        case "1" -> {
                            if (contador <= 20) {
                                System.out.println("Introduce el nombre de la tarea: ");
                                String nombre = sc.nextLine();
                                System.out.println("Introduce la descripción de la tarea: ");
                                String descripcion = sc.nextLine();
                                
                                try {
                                    System.out.println("Introduce el número de días que tienes de plazo para completar la tarea");
                                    dias = Integer.parseInt(sc.nextLine());
                                } catch (Exception e) {
                                    // TODO: handle exception
                                    System.out.println("No has introducido un número de días enteros válido, prueba a intentarlo de nuevo");
                                    sc.next();
                                    continuar = false;
                                }

                                if (continuar) {
                                    toDoList[contador] = new Tarea(nombre, descripcion, dias);
                                    contador++;
                                }
                            }   
                        }
                        case "2" -> {
                            for (Tarea tarea : toDoList) {
                                if (tarea != null) {
                                    System.out.println(tarea);
                                    
                                }
                            }
                        }
                        
                        case "S" -> {
                            System.out.println("Saliendo del programa...");
                            continuar = false;
                        }
                    
                        default -> {

                        }
                    }
        } while (continuar);
    }
        

}
