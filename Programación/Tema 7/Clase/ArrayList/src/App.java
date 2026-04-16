import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> lista = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        boolean continuar = true;
        String respuesta;


        do {
            System.out.print("""
                    === MENU ===
                    1. Introduce un elemento al array
                    2. Eliminar un elemento del array
                    3. Mostrar elementos del array

                    S. Salir
                
                    Selecciona una opción:\t""");
                    respuesta = sc.nextLine().toUpperCase();
                    switch (respuesta) {
                        case "1" -> {
                            añadir();
                        }
                        case "2" -> {
                            eliminar();
                        }
                        case "3" -> {
                            System.out.println(mostrar());
                        }
                        case "S" -> {
                            continuar = false;
                            System.out.println("Saliendo del programa...");
                        }
                    
                        default -> {
                            System.out.println("Selecciona una opción válida");
                        }
                    }
        } while (continuar);
    }

    private static void añadir(){
        System.out.print("Introduce lo que quieres añadir: ");
        String respuesta = sc.nextLine();
        lista.add(respuesta);
    }
    private static void eliminar(){
        lista.remove(lista.size()-1);
    }
    private static String mostrar(){
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            salida.append(i+1).append(". ").append(lista.get(i)).append("\n");
        }
        return salida.toString();
    }
}
