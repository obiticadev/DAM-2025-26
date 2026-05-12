// Paquete al que pertenece esta clase
package gestorarchivos;

// Importaciones necesarias
import java.io.File;               // Para trabajar con rutas y archivos
import java.util.ArrayList;       // Para crear listas dinámicas
import java.util.List;            // Tipo de interfaz List
import java.util.Scanner;         // Para capturar entrada por teclado

/**
 * Clase responsable de acceder a un directorio introducido por el usuario
 * y extraer los nombres de los archivos (sin extensión).
 */
public class GestorDirectorio {

    /**
     * Método estático que solicita al usuario una ruta de directorio válida,
     * lista los archivos que contiene y devuelve sus nombres sin extensión.
     * 
     * @return Lista de nombres de archivos (sin extensión) encontrados en el directorio.
     */
    public static List<String> obtenerNombresSinExtension() {
        // Objeto Scanner para leer entrada del usuario desde la consola
        Scanner scanner = new Scanner(System.in);

        // Se solicita la ruta completa del directorio
        System.out.print("Introduce la ruta de un directorio existente: ");
        String ruta = scanner.nextLine();

        // Se crea un objeto File a partir de la ruta introducida
        File directorio = new File(ruta);

        // Se comprueba si la ruta existe y es un directorio válido
        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("La ruta introducida no es válida.");
            return new ArrayList<>(); // Devuelve una lista vacía si la ruta no es válida
        }

        // Se obtienen solo los archivos del directorio (se excluyen subdirectorios)
        String[] archivos = directorio.list((dir, name) -> new File(dir, name).isFile());

        // Lista donde se almacenarán los nombres de archivo sin extensión
        List<String> nombresSinExtension = new ArrayList<>();

        // Si el directorio no está vacío, se procesan los nombres
        if (archivos != null) {
            for (String nombre : archivos) {
                // Se busca el último punto para separar nombre y extensión
                int punto = nombre.lastIndexOf('.');
                if (punto > 0) {
                    // Se elimina la extensión (por ejemplo, ".txt", ".java", etc.)
                    nombre = nombre.substring(0, punto);
                }
                // Se añade el nombre sin extensión a la lista
                nombresSinExtension.add(nombre);
            }
        }

        // Se devuelve la lista final con los nombres procesados
        return nombresSinExtension;
    }
}

