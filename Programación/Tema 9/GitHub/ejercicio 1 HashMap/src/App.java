import java.util.HashMap;
import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        String respuestaMenu;
        do {
            respuestaMenu = menu();
            switch (respuestaMenu) {
                case "1" -> {
                    agregarContacto();
                }
                case "2" -> {
                    consultarContacto();
                }
                case "3" -> {
                    mostrarAgenda();
                }
                case "S" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (continuar);

    }

    private static String menu() {

        System.out.print("""
                === MENÚ ===
                1. Insertar contacto
                2. Consultar contacto (Número)
                3. Mostrar todos

                S. Salir

                Introduce una opción:
                """);
        return sc.nextLine().toUpperCase();

    }

    private static String preguntaString(String pregunta) {
        System.out.print(pregunta);
        return sc.nextLine();
    }

    private static void agregarContacto() {
        String num = preguntaString("Introduce el número de teléfono: ");
        String nombre = preguntaString("Introduce el nombre de contacto: ");
        map.put(num, nombre);
    }

    private static void consultarContacto() {
        String numero = preguntaString("Introduce el número de teléfono que quieres consultar: ");
        if (map.containsKey(numero)) {
            System.out.println(map.get(numero));
        } else {
            System.out.println("No existe ese número de teléfono en tu agenda");
        }
    }

    private static void mostrarAgenda() {
        for (String numString : map.keySet()) {
            System.out.println("Para la clave " + numString);
            System.out.println("El valor es " + map.get(numString));
        }
    }

}
