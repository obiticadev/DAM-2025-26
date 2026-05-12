import java.io.*;
import java.util.*;
import java.util.Scanner;


public class GestorAgenda {
	
	// Colección ordenada que mantiene los contactos por nombre de forma automática
    public static TreeSet<Contacto> agenda = new TreeSet<>((c1, c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre()));
    private static final String ARCHIVO_CONTACTOS = "contactos.txt";
    private static final String ARCHIVO_SERIAL = "contactos_agenda.dat";

    public static void mostrarMenu() {
        System.out.println("\n--- AGENDA DE CONTACTOS ---");
        System.out.println("1. Cargar contactos desde archivo de texto");
        System.out.println("2. Agregar nuevo contacto");
        System.out.println("3. Mostrar todos los contactos");
        System.out.println("4. Guardar contactos serializados");
        System.out.println("5. Cargar contactos desde archivo serializado");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }


    public static int leerOpcion() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, introduzca un número válido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    public static void cargarDesdeArchivoTexto() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CONTACTOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" - ");
                if (partes.length == 2) {
                    agenda.add(new Contacto(partes[0], partes[1]));
                    System.out.println("Añadido: " + partes[0]);
                }
            }
            System.out.println("Contactos cargados desde " + ARCHIVO_CONTACTOS);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se iniciará con lista vacía.");
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }
    }

    public static void agregarNuevoContacto() {
        Scanner sc = new Scanner(System.in);
        char continuar = 's';

        while (continuar == 's' || continuar == 'S') {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Teléfono: ");
            String telefono = sc.nextLine();

            agenda.add(new Contacto(nombre, telefono));
            System.out.println("Contacto añadido.");

            System.out.print("¿Desea añadir otro contacto? (s/n): ");
            continuar = sc.nextLine().charAt(0);
        }
    }

    public static void mostrarContactos() {
        if (agenda.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }

        System.out.println("\n--- Lista de contactos ---");
        for (Contacto c : agenda) {
            System.out.println(c);
        }
    }

    public static void guardarSerializado() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_SERIAL))) {
            oos.writeObject(agenda);
            System.out.println("Lista guardada correctamente en " + ARCHIVO_SERIAL);
        } catch (IOException e) {
            System.out.println("Error escribiendo archivo serializado: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void cargarSerializado() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_SERIAL))) {
            agenda = (TreeSet<Contacto>) ois.readObject();
            System.out.println("Lista recuperada correctamente desde " + ARCHIVO_SERIAL);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo archivo serializado: " + e.getMessage());
        }
    }
   
}
