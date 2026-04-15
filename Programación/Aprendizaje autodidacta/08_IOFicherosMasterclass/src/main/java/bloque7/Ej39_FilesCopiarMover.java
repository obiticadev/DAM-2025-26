package bloque7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * EJERCICIO 39 — Files: copiar, mover y borrar
 * Teoria: teoria/07_NIO2.md (seccion 3)
 *
 * Contexto: Operaciones de mantenimiento de ficheros del supermercado:
 * backups, reorganizacion y limpieza.
 */
public class Ej39_FilesCopiarMover {

    /**
     * Copia un fichero. Sobreescribe si el destino existe.
     *
     * @param origen  ruta origen
     * @param destino ruta destino
     * @throws IOException si hay error
     */
    public static void copiar(String origen, String destino) throws IOException {
        // TODO 1: Usar Files.copy() con REPLACE_EXISTING.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Mueve un fichero. Sobreescribe si el destino existe.
     *
     * @param origen  ruta origen
     * @param destino ruta destino
     * @throws IOException si hay error
     */
    public static void mover(String origen, String destino) throws IOException {
        // TODO 2: Usar Files.move() con REPLACE_EXISTING.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Borra un fichero si existe.
     *
     * @param ruta ruta del fichero
     * @return true si se borro, false si no existia
     * @throws IOException si hay error
     */
    public static boolean borrar(String ruta) throws IOException {
        // TODO 3: Usar Files.deleteIfExists(). Devolver el resultado.
        return false;
    }

    /**
     * Crea un directorio (y todos los padres) si no existe.
     *
     * @param ruta ruta del directorio
     * @throws IOException si hay error
     */
    public static void crearDirectorios(String ruta) throws IOException {
        // TODO 4: Usar Files.createDirectories().
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Hace backup de un fichero: lo copia con extension .bak
     *
     * @param ruta ruta del fichero original
     * @return ruta del backup
     * @throws IOException si hay error
     */
    public static String hacerBackup(String ruta) throws IOException {
        // TODO 5: Copiar ruta a ruta + ".bak". Devolver la ruta del backup.
        return "";
    }

    /**
     * Copia un fichero a un directorio destino, manteniendo el nombre.
     *
     * @param rutaFichero ruta del fichero
     * @param dirDestino  directorio destino
     * @return ruta del fichero copiado
     * @throws IOException si hay error
     */
    public static String copiarADirectorio(String rutaFichero, String dirDestino) throws IOException {
        // TODO 6: Obtener nombre del fichero con getFileName().
        //         Resolver la ruta destino. Copiar.
        return "";
    }

    /**
     * Indica si una ruta es un directorio.
     *
     * @param ruta ruta a comprobar
     * @return true si es directorio
     */
    public static boolean esDirectorio(String ruta) {
        // TODO 7: Usar Files.isDirectory().
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 39: Files Copiar/Mover ===\n");

        Path dir = Paths.get("temp", "bloque7");
        Files.createDirectories(dir);

        Files.writeString(dir.resolve("orig.txt"), "Contenido original");
        copiar(dir + "/orig.txt", dir + "/copia.txt");
        System.out.println("Copiado. Existe copia: " + Files.exists(dir.resolve("copia.txt")));

        String bak = hacerBackup(dir + "/orig.txt");
        System.out.println("Backup en: " + bak);
        System.out.println("Es directorio 'temp': " + esDirectorio("temp"));
    }
}
