package bloque7;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * EJERCICIO 40 — Listar Directorios con NIO.2
 * Teoria: teoria/07_NIO2.md (seccion 4)
 *
 * Contexto: El sistema del supermercado necesita explorar carpetas
 * de datos para encontrar ficheros especificos.
 */
public class Ej40_ListarDirectorios {

    /**
     * Lista los nombres de ficheros y directorios de un directorio.
     *
     * @param dir ruta del directorio
     * @return lista de nombres
     * @throws IOException si hay error
     */
    public static List<String> listarContenido(String dir) throws IOException {
        // TODO 1: Usar Files.newDirectoryStream() con try-with-resources.
        //         Recoger getFileName().toString() de cada Path.
        return new ArrayList<>();
    }

    /**
     * Lista solo los ficheros regulares (no directorios) de un directorio.
     *
     * @param dir ruta del directorio
     * @return lista de nombres de ficheros
     * @throws IOException si hay error
     */
    public static List<String> listarSoloFicheros(String dir) throws IOException {
        // TODO 2: Usar Files.list() con try-with-resources.
        //         Filtrar con Files::isRegularFile.
        return new ArrayList<>();
    }

    /**
     * Lista ficheros que coincidan con un patron glob (ej: "*.txt").
     *
     * @param dir  ruta del directorio
     * @param glob patron glob
     * @return lista de nombres
     * @throws IOException si hay error
     */
    public static List<String> listarPorGlob(String dir, String glob) throws IOException {
        // TODO 3: Usar Files.newDirectoryStream(path, glob).
        return new ArrayList<>();
    }

    /**
     * Cuenta recursivamente todos los ficheros regulares en un arbol de directorios.
     *
     * @param dir ruta raiz
     * @return total de ficheros regulares
     * @throws IOException si hay error
     */
    public static long contarFicherosRecursivo(String dir) throws IOException {
        // TODO 4: Usar Files.walk() con try-with-resources.
        //         Filtrar con Files::isRegularFile. count().
        return 0;
    }

    /**
     * Busca recursivamente ficheros por extension.
     *
     * @param dir       ruta raiz
     * @param extension extension sin punto (ej: "txt")
     * @return lista de rutas (toString) de ficheros encontrados
     * @throws IOException si hay error
     */
    public static List<String> buscarPorExtension(String dir, String extension) throws IOException {
        // TODO 5: Usar Files.walk(). Filtrar ficheros regulares cuyo nombre
        //         termina en "." + extension.
        return new ArrayList<>();
    }

    /**
     * Calcula el tamano total (bytes) de todos los ficheros en un directorio (no recursivo).
     *
     * @param dir ruta del directorio
     * @return tamano total en bytes
     * @throws IOException si hay error
     */
    public static long tamanoTotalDirectorio(String dir) throws IOException {
        // TODO 6: Usar Files.list(). Filtrar ficheros regulares.
        //         Sumar Files.size() de cada uno.
        return 0;
    }

    /**
     * Devuelve el fichero mas grande de un directorio (no recursivo).
     *
     * @param dir ruta del directorio
     * @return nombre del fichero mas grande, o null si esta vacio
     * @throws IOException si hay error
     */
    public static String ficheroMasGrande(String dir) throws IOException {
        // TODO 7: Usar Files.list(). Filtrar regulares.
        //         Encontrar el de mayor Files.size(). Devolver getFileName().
        return null;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 40: Listar Directorios ===\n");

        Path dir = Paths.get("temp", "bloque7", "explorar");
        Files.createDirectories(dir);
        Files.writeString(dir.resolve("a.txt"), "AAA");
        Files.writeString(dir.resolve("b.csv"), "BBB");
        Files.writeString(dir.resolve("c.txt"), "CCCC");

        System.out.println("Todo: " + listarContenido(dir.toString()));
        System.out.println("Solo ficheros: " + listarSoloFicheros(dir.toString()));
        System.out.println("*.txt: " + listarPorGlob(dir.toString(), "*.txt"));
        System.out.println("Total recursivo: " + contarFicherosRecursivo(dir.toString()));
    }
}
