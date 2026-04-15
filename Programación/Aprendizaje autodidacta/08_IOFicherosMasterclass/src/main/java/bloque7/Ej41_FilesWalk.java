package bloque7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * EJERCICIO 41 — Files.walk: recorrido recursivo de arboles
 * Teoria: teoria/07_NIO2.md (seccion 4)
 *
 * Contexto: El sistema del supermercado almacena datos en multiples
 * subdirectorios (por sucursal, fecha, etc.) y necesita recorrerlos.
 */
public class Ej41_FilesWalk {

    /**
     * Lista recursivamente TODAS las rutas (ficheros y directorios).
     *
     * @param dir ruta raiz
     * @return lista de rutas como String
     * @throws IOException si hay error
     */
    public static List<String> listarTodo(String dir) throws IOException {
        // TODO 1: Usar Files.walk() con try-with-resources.
        //         Recoger todos los Path como toString().
        return new ArrayList<>();
    }

    /**
     * Busca ficheros cuyo nombre contenga una palabra (case insensitive).
     *
     * @param dir     ruta raiz
     * @param palabra palabra a buscar en el nombre
     * @return lista de rutas encontradas
     * @throws IOException si hay error
     */
    public static List<String> buscarPorNombre(String dir, String palabra) throws IOException {
        // TODO 2: Files.walk(). Filtrar regulares cuyo getFileName()
        //         contenga la palabra (toLowerCase).
        return new ArrayList<>();
    }

    /**
     * Calcula el tamano total (bytes) de todos los ficheros del arbol.
     *
     * @param dir ruta raiz
     * @return tamano total en bytes
     * @throws IOException si hay error
     */
    public static long tamanoTotalRecursivo(String dir) throws IOException {
        // TODO 3: Files.walk(). Filtrar regulares. Sumar Files.size().
        return 0;
    }

    /**
     * Cuenta subdirectorios (no incluye el raiz).
     *
     * @param dir ruta raiz
     * @return numero de subdirectorios
     * @throws IOException si hay error
     */
    public static long contarSubdirectorios(String dir) throws IOException {
        // TODO 4: Files.walk(). Filtrar Files::isDirectory.
        //         Excluir el propio directorio raiz. count().
        return 0;
    }

    /**
     * Encuentra el fichero mas profundo (mayor numero de niveles).
     *
     * @param dir ruta raiz
     * @return ruta del fichero mas profundo, o null si no hay ficheros
     * @throws IOException si hay error
     */
    public static String ficheroMasProfundo(String dir) throws IOException {
        // TODO 5: Files.walk(). Filtrar regulares.
        //         Comparar por getNameCount(). Devolver max.
        return null;
    }

    /**
     * Lista solo ficheros a una profundidad maxima dada (relativa al raiz).
     *
     * @param dir           ruta raiz
     * @param maxProfundidad profundidad maxima
     * @return lista de nombres de ficheros
     * @throws IOException si hay error
     */
    public static List<String> listarConProfundidad(String dir, int maxProfundidad)
            throws IOException {
        // TODO 6: Usar Files.walk(path, maxProfundidad).
        //         Filtrar regulares. Recoger getFileName().
        return new ArrayList<>();
    }

    /**
     * Genera un informe de un arbol: ficheros, directorios, tamano total.
     * Formato: "Ficheros: [f] | Directorios: [d] | Tamano: [t] bytes"
     *
     * @param dir ruta raiz
     * @return informe formateado
     * @throws IOException si hay error
     */
    public static String informeArbol(String dir) throws IOException {
        // TODO 7: Contar ficheros, directorios y tamano total.
        //         Formatear con String.format.
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 41: Files.walk ===\n");

        Path dir = Paths.get("temp", "bloque7", "arbol");
        Files.createDirectories(dir.resolve("sub1"));
        Files.createDirectories(dir.resolve("sub2/sub3"));
        Files.writeString(dir.resolve("a.txt"), "A");
        Files.writeString(dir.resolve("sub1/b.txt"), "BB");
        Files.writeString(dir.resolve("sub2/sub3/c.txt"), "CCC");

        System.out.println("Todo: " + listarTodo(dir.toString()));
        System.out.println("Buscar 'b': " + buscarPorNombre(dir.toString(), "b"));
        System.out.println("Subdirectorios: " + contarSubdirectorios(dir.toString()));
        System.out.println(informeArbol(dir.toString()));
    }
}
