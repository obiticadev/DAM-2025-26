import java.util.Scanner;

import Clases.Tarea;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int numTareas = 0;
        
        try {
            System.out.println("¿Cuántas tareas quieres crear?");
            numTareas = Integer.parseInt(sc.nextLine());
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Introduce un valor numérico entero");
        }

        Tarea[] canva = new Tarea[numTareas];

        for (int i = 0; i < canva.length; i++) {

            String nombreTarea;
            String nombreCategoria;

            System.out.print("Introduce el título de la tarea: ");
            nombreTarea = sc.nextLine();
            System.out.println("Introduce la categoría de la tarea: ");
            System.out.println("TRABAJO,\n" + //
                                "ESTUDIO,\n" + //
                                "PERSONAL,\n" + //
                                "OTRO\n");
                                nombreCategoria = sc.nextLine();
                                
        }
        
    }
}
