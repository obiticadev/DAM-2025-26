package bloque7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * EJERCICIO 37 — Path y Paths: operaciones basicas
 * Teoria: teoria/07_NIO2.md (secciones 2, 5)
 *
 * Contexto: Un sistema de gestion de ficheros necesita manipular rutas
 * de forma segura y multiplataforma usando NIO.2.
 */
public class Ej37_PathBasico {

    /**
     * Crea un Path a partir de partes y devuelve su representacion String.
     *
     * @param partes partes de la ruta (ej: "temp", "bloque7", "datos.txt")
     * @return la ruta como String
     */
    public static String construirRuta(String... partes) {
        // TODO 1: Usar Paths.get() con las partes. Devolver toString().
        return "";
    }

    /**
     * Devuelve el nombre del fichero de una ruta.
     *
     * @param ruta ruta completa
     * @return nombre del fichero (ej: "datos.txt")
     */
    public static String nombreFichero(String ruta) {
        // TODO 2: Crear Path con Paths.get(). Devolver getFileName().toString().
        return "";
    }

    /**
     * Devuelve el directorio padre de una ruta.
     *
     * @param ruta ruta completa
     * @return directorio padre como String, o null si no tiene
     */
    public static String directorioPadre(String ruta) {
        // TODO 3: Crear Path. getParent(). Si null, devolver null.
        //         Si no, devolver toString().
        return null;
    }

    /**
     * Resuelve una ruta relativa sobre una ruta base.
     *
     * @param base     ruta base
     * @param relativa ruta relativa
     * @return ruta resuelta como String
     */
    public static String resolver(String base, String relativa) {
        // TODO 4: Paths.get(base).resolve(relativa).toString().
        return "";
    }

    /**
     * Convierte una ruta relativa a absoluta.
     *
     * @param ruta ruta relativa
     * @return ruta absoluta como String
     */
    public static String aAbsoluta(String ruta) {
        // TODO 5: Paths.get(ruta).toAbsolutePath().toString().
        return "";
    }

    /**
     * Cuenta el numero de elementos (niveles) de una ruta.
     *
     * @param ruta ruta
     * @return numero de elementos (ej: "a/b/c.txt" -> 3)
     */
    public static int contarNiveles(String ruta) {
        // TODO 6: Paths.get(ruta).getNameCount().
        return 0;
    }

    /**
     * Normaliza una ruta eliminando . y ..
     *
     * @param ruta ruta con posibles . y ..
     * @return ruta normalizada
     */
    public static String normalizar(String ruta) {
        // TODO 7: Paths.get(ruta).normalize().toString().
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 37: Path Basico ===\n");

        System.out.println("Ruta: " + construirRuta("temp", "bloque7", "datos.txt"));
        System.out.println("Nombre: " + nombreFichero("temp/bloque7/datos.txt"));
        System.out.println("Padre: " + directorioPadre("temp/bloque7/datos.txt"));
        System.out.println("Resolver: " + resolver("temp/bloque7", "datos.txt"));
        System.out.println("Absoluta: " + aAbsoluta("temp/bloque7"));
        System.out.println("Niveles: " + contarNiveles("a/b/c/d.txt"));
        System.out.println("Normalizar: " + normalizar("a/b/../c/./d.txt"));
    }
}
