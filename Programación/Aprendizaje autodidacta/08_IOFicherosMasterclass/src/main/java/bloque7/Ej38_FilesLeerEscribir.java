package bloque7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * EJERCICIO 38 — Files: leer y escribir con NIO.2
 * Teoria: teoria/07_NIO2.md (seccion 3)
 *
 * Contexto: Reescribimos las operaciones clasicas de lectura y escritura
 * usando la API moderna de Files.
 */
public class Ej38_FilesLeerEscribir {

    /**
     * Escribe un texto en un fichero usando Files.writeString().
     *
     * @param ruta     ruta del fichero
     * @param contenido texto a escribir
     * @throws IOException si hay error
     */
    public static void escribirTexto(String ruta, String contenido) throws IOException {
        // TODO 1: Usar Files.writeString(Paths.get(ruta), contenido).
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee todo el contenido de un fichero como String.
     *
     * @param ruta ruta del fichero
     * @return contenido del fichero
     * @throws IOException si hay error
     */
    public static String leerTexto(String ruta) throws IOException {
        // TODO 2: Usar Files.readString(Paths.get(ruta)).
        return "";
    }

    /**
     * Escribe una lista de lineas en un fichero.
     *
     * @param ruta   ruta del fichero
     * @param lineas lista de lineas
     * @throws IOException si hay error
     */
    public static void escribirLineas(String ruta, List<String> lineas) throws IOException {
        // TODO 3: Usar Files.write(Paths.get(ruta), lineas).
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Lee todas las lineas de un fichero.
     *
     * @param ruta ruta del fichero
     * @return lista de lineas
     * @throws IOException si hay error
     */
    public static List<String> leerLineas(String ruta) throws IOException {
        // TODO 4: Usar Files.readAllLines(Paths.get(ruta)).
        return List.of();
    }

    /**
     * Lee un fichero como bytes y devuelve el tamano.
     *
     * @param ruta ruta del fichero
     * @return tamano en bytes
     * @throws IOException si hay error
     */
    public static long tamanoFichero(String ruta) throws IOException {
        // TODO 5: Usar Files.size(Paths.get(ruta)).
        return 0;
    }

    /**
     * Verifica que un fichero existe.
     *
     * @param ruta ruta del fichero
     * @return true si existe
     */
    public static boolean existe(String ruta) {
        // TODO 6: Usar Files.exists(Paths.get(ruta)).
        return false;
    }

    /**
     * Escribe un texto y luego verifica que al leerlo se obtiene lo mismo.
     *
     * @param ruta      ruta temporal
     * @param contenido texto de prueba
     * @return true si ida y vuelta OK
     * @throws IOException si hay error
     */
    public static boolean verificarIdaVuelta(String ruta, String contenido) throws IOException {
        // TODO 7: Escribir. Leer. Comparar con equals().
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 38: Files Leer/Escribir ===\n");

        Path dir = Paths.get("temp", "bloque7");
        Files.createDirectories(dir);

        escribirTexto(dir + "/nota.txt", "Hola NIO.2!");
        System.out.println("Leido: " + leerTexto(dir + "/nota.txt"));
        System.out.println("Existe: " + existe(dir + "/nota.txt"));
        System.out.println("Tamano: " + tamanoFichero(dir + "/nota.txt"));
    }
}
