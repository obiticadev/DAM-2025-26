// Paquete al que pertenece esta clase
package gestorarchivos;

// Importaciones necesarias para la gestión de ficheros binarios y listas
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Clase que encapsula la lógica de escritura y lectura de listas de Strings
 * en archivos binarios utilizando serialización en Java.
 */
public class GestorFicheroBinario {

    /**
     * Método que guarda una lista de Strings en un archivo binario.
     *
     * @param lista Lista de cadenas de texto que se desea guardar.
     * @param nombreArchivo Nombre del archivo binario donde se guardará la lista.
     */
    public static void guardarLista(List<String> lista, String nombreArchivo) {
        // Se utiliza un ObjectOutputStream para escribir objetos en un archivo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(lista); // Se escribe la lista completa como un único objeto serializado
        } catch (IOException e) {
            // Se captura y muestra cualquier error durante la escritura del archivo
            System.out.println("Error al escribir en el archivo binario: " + e.getMessage());
        }
    }

    /**
     * Método que lee una lista de Strings desde un archivo binario.
     *
     * @param nombreArchivo Nombre del archivo binario desde el cual se desea leer la lista.
     * @return Lista de Strings leída desde el archivo o null si ocurre un error.
     */
    public static List<String> leerLista(String nombreArchivo) {
        // Se utiliza un ObjectInputStream para leer objetos desde un archivo
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            // Se lee el objeto y se convierte de nuevo a List<String> mediante casting
            return (List<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Se captura cualquier error de entrada/salida o si el tipo de clase no es compatible
            System.out.println("Error al leer el archivo binario: " + e.getMessage());
            return null; // En caso de error se devuelve null
        }
    }
}

