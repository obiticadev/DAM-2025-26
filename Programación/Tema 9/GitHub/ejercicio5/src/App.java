import Clases.Persona;
import Clases.Regalo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static Map<Persona, Set<Regalo>> lista = ;

    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        String respuestaMenu;
        do {
            menu();
            respuestaMenu = sc.nextLine().toUpperCase();
            switch (respuestaMenu) {
                case "1" -> {
                    System.out.println(añadirRegalo());
                }
                case "2" -> {
                    System.out.println(imprimirPersonasConRegalos());
                }
                case "3" -> {

                }
                case "4" -> {

                }
                case "5" -> {

                }
                case "S" -> {
                    System.out.println("\nSaliendo del programa...\n");
                    continuar = false;
                }
                default -> {
                    System.out.println("\nSelecciona una opción válida\n");
                }
            }
        } while (continuar);

    }

    private static void menu() {
        System.out.print("""
                === MENÚ ===
                1. Agregar regalo
                2. Mostrar todos los regalos
                3. Buscar regalos de una persona
                4. Quien tiene más regalos
                5. Contar regalos totales
                6. Mostrar quén ha recibido más valor en regalos

                S. Salir

                Selecciona una opción:\t""");
    }

    private static String preguntaString(String pregunta) {
        System.out.print(pregunta);
        return sc.nextLine();
    }

    private static Persona crearPersona() {
        System.out.println("------ Creación de Persona");
        String dni = preguntaString("Introduce DNI: ");
        String nombre = preguntaString("Introduce nombre: ");
        String apellido = preguntaString("Introduce apellido: ");
        return new Persona(dni, nombre, apellido);
    }

    private static Regalo crearRegalo() {
        System.out.println("------ Creación de Regalo");
        String nombre = preguntaString("Introduce el nombre del regalo: ");
        String motivo = preguntaString("Introduce el motivo del regalo: ");
        return new Regalo(nombre, motivo);
    }

    private static String añadirRegalo() {
        StringBuilder sb = new StringBuilder();
        Persona persona = crearPersona();
        Regalo regalo = crearRegalo();
        Set<Regalo> listaRegalos = new HashSet<>();
        listaRegalos.add(regalo);
        if ((lista.putIfAbsent(persona, listaRegalos)) == null) {
            return "\nAgregado Persona:\n" + persona.toString() + "\nCon el siguiente regalo:\n" + regalo.toString();
        }
        sb.append("\nYa existe ").append(persona.getNombre()).append("\n");
        if (lista.get(persona).add(regalo)) {
            sb.append("Agregado el regalo con\n").append(regalo.toString() + "\n");
        } else {
            sb.append("y el regalo\n" + regalo.toString() + "YA EXISTE");
        }
        return sb.toString();

    }

    private static String imprimirPersonasConRegalos() {
        StringBuilder sb = new StringBuilder();
        sb.append("LISTADO COMPLETO\n----------------\n");
        for (Map.Entry<Persona, Set<Regalo>> tupla : lista.entrySet()) {
            sb.append(tupla.getKey().toString()).append("LISTA REGALOS\n----------------\n");
            for (Regalo regalo : tupla.getValue()) {
                sb.append(regalo.toString()).append("----------------\n");
            }
        }
        sb.append("Fin de la impresión");
        return sb.toString();
    }
}
