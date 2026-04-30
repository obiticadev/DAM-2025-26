package bloque7;

import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * EJERCICIO 62 — IO vs NIO: Comparativa de Examen
 * 📋 ENTRA EN EXAMEN — Pilar 1 (IO Clásico vs NIO Moderno)
 * Teoria: teoria/07_NIO2.md (secciones 0, 1-6) + teoria/01_Flujos_De_Datos.md
 *
 * Contexto: En el examen te pueden pedir que resuelvas la MISMA operación
 * usando java.io (File, FileWriter, FileReader) y java.nio.file (Path, Files).
 * Este ejercicio te obliga a codificar ambas soluciones lado a lado para que
 * interiorices las dos APIs.
 *
 * Cada operación tiene DOS métodos: uno con sufijo "IO" (java.io) y otro
 * con sufijo "NIO" (java.nio.file). Ambos deben producir el MISMO resultado.
 */
public class Ej62_IOvsNIO_Examen {

    // ═══════════════════════════════════════════════
    //  OPERACIÓN 1: Crear un fichero vacío
    // ═══════════════════════════════════════════════

    /**
     * Crea un fichero vacío usando java.io.File.
     * Si el fichero ya existe, no hace nada.
     *
     * @param ruta ruta del fichero a crear
     * @return true si se creó, false si ya existía
     * @throws IOException si hay error
     */
    public static boolean crearFicheroIO(String ruta) throws IOException {
        // TODO 1: Usar new File(ruta).createNewFile().
        //         Asegúrate de crear los directorios padre con mkdirs() si no existen.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Crea un fichero vacío usando java.nio.file.
     * Si el fichero ya existe, no hace nada.
     *
     * @param ruta ruta del fichero a crear
     * @return true si se creó, false si ya existía
     * @throws IOException si hay error
     */
    public static boolean crearFicheroNIO(String ruta) throws IOException {
        // TODO 2: Usar Paths.get(ruta). Files.createDirectories(padre).
        //         Si !Files.exists(p), entonces Files.createFile(p) y devolver true.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    // ═══════════════════════════════════════════════
    //  OPERACIÓN 2: Escribir texto en un fichero
    // ═══════════════════════════════════════════════

    /**
     * Escribe una lista de líneas en un fichero usando java.io.
     *
     * @param ruta   ruta del fichero
     * @param lineas lista de líneas a escribir
     * @throws IOException si hay error
     */
    public static void escribirTextoIO(String ruta, List<String> lineas) throws IOException {
        // TODO 3: Usar BufferedWriter(new FileWriter(ruta)) con try-with-resources.
        //         Iterar las líneas, escribir cada una con bw.write() + bw.newLine().
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Escribe una lista de líneas en un fichero usando java.nio.file.
     *
     * @param ruta   ruta del fichero
     * @param lineas lista de líneas a escribir
     * @throws IOException si hay error
     */
    public static void escribirTextoNIO(String ruta, List<String> lineas) throws IOException {
        // TODO 4: Usar Files.write(Paths.get(ruta), lineas).
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    // ═══════════════════════════════════════════════
    //  OPERACIÓN 3: Leer texto completo de un fichero
    // ═══════════════════════════════════════════════

    /**
     * Lee todas las líneas de un fichero usando java.io.
     *
     * @param ruta ruta del fichero
     * @return lista de líneas leídas
     * @throws IOException si hay error
     */
    public static List<String> leerTextoIO(String ruta) throws IOException {
        // TODO 5: Usar BufferedReader(new FileReader(ruta)) con try-with-resources.
        //         Leer con readLine() en bucle, acumular en una ArrayList y devolver.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Lee todas las líneas de un fichero usando java.nio.file.
     *
     * @param ruta ruta del fichero
     * @return lista de líneas leídas
     * @throws IOException si hay error
     */
    public static List<String> leerTextoNIO(String ruta) throws IOException {
        // TODO 6: Usar Files.readAllLines(Paths.get(ruta)).
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    // ═══════════════════════════════════════════════
    //  OPERACIÓN 4: Verificar si un fichero existe
    // ═══════════════════════════════════════════════

    /**
     * Verifica si un fichero existe usando java.io.
     *
     * @param ruta ruta del fichero
     * @return true si existe
     */
    public static boolean existeIO(String ruta) {
        // TODO 7: Usar new File(ruta).exists().
        return false;
    }

    /**
     * Verifica si un fichero existe usando java.nio.file.
     *
     * @param ruta ruta del fichero
     * @return true si existe
     */
    public static boolean existeNIO(String ruta) {
        // TODO 8: Usar Files.exists(Paths.get(ruta)).
        return false;
    }

    // ═══════════════════════════════════════════════
    //  ZONA DE EJECUCIÓN — Pulsa Run aquí
    // ═══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 62: IO vs NIO — Examen ===\n");

        String dir = "temp/bloque7";
        new File(dir).mkdirs();

        // Crear
        System.out.println("[CREAR]");
        System.out.println("  IO:  " + crearFicheroIO(dir + "/io_test.txt"));
        System.out.println("  NIO: " + crearFicheroNIO(dir + "/nio_test.txt"));

        // Escribir
        List<String> datos = List.of("Línea 1", "Línea 2", "Línea 3");
        escribirTextoIO(dir + "/io_test.txt", datos);
        escribirTextoNIO(dir + "/nio_test.txt", datos);
        System.out.println("[ESCRIBIR] OK");

        // Leer
        System.out.println("[LEER]");
        System.out.println("  IO:  " + leerTextoIO(dir + "/io_test.txt"));
        System.out.println("  NIO: " + leerTextoNIO(dir + "/nio_test.txt"));

        // Existe
        System.out.println("[EXISTE]");
        System.out.println("  IO:  " + existeIO(dir + "/io_test.txt"));
        System.out.println("  NIO: " + existeNIO(dir + "/nio_test.txt"));
        System.out.println("  IO (no existe):  " + existeIO(dir + "/fantasma.txt"));
        System.out.println("  NIO (no existe): " + existeNIO(dir + "/fantasma.txt"));
    }
}
