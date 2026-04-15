package bloque7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * EJERCICIO 42 — Comparar java.io vs java.nio.file
 * Teoria: teoria/07_NIO2.md (seccion 6)
 *
 * Contexto: Ejercicio de recapitulacion donde reescribes operaciones
 * clasicas de java.io usando NIO.2 y comparas la legibilidad.
 */
public class Ej42_NIOvsIO {

    /**
     * Escribe un texto con java.io (BufferedWriter + FileWriter).
     *
     * @param ruta      ruta del fichero
     * @param contenido texto a escribir
     * @throws IOException si hay error
     */
    public static void escribirIO(String ruta, String contenido) throws IOException {
        // TODO 1: Usar try-with-resources con BufferedWriter(new FileWriter(ruta)).
        //         Escribir contenido con write().
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Escribe un texto con NIO.2 (Files.writeString).
     *
     * @param ruta      ruta del fichero
     * @param contenido texto a escribir
     * @throws IOException si hay error
     */
    public static void escribirNIO(String ruta, String contenido) throws IOException {
        // TODO 2: Usar Files.writeString(Paths.get(ruta), contenido).
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Lee todo el contenido con java.io.
     *
     * @param ruta ruta del fichero
     * @return contenido leido
     * @throws IOException si hay error
     */
    public static String leerIO(String ruta) throws IOException {
        // TODO 3: Usar BufferedReader con try-with-resources.
        //         Leer linea a linea con StringBuilder.
        return "";
    }

    /**
     * Lee todo el contenido con NIO.2.
     *
     * @param ruta ruta del fichero
     * @return contenido leido
     * @throws IOException si hay error
     */
    public static String leerNIO(String ruta) throws IOException {
        // TODO 4: Usar Files.readString().
        return "";
    }

    /**
     * Copia un fichero usando java.io (byte a byte con buffer).
     *
     * @param origen  ruta origen
     * @param destino ruta destino
     * @throws IOException si hay error
     */
    public static void copiarIO(String origen, String destino) throws IOException {
        // TODO 5: Usar FileInputStream/FileOutputStream con buffer de 1024 bytes.
        //         try-with-resources con ambos streams.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Copia un fichero usando NIO.2.
     *
     * @param origen  ruta origen
     * @param destino ruta destino
     * @throws IOException si hay error
     */
    public static void copiarNIO(String origen, String destino) throws IOException {
        // TODO 6: Usar Files.copy() con REPLACE_EXISTING.
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Cuenta las lineas de codigo necesarias para cada enfoque (conceptual).
     * Devuelve un resumen comparativo.
     *
     * @return resumen "IO: mas verbose | NIO.2: mas conciso"
     */
    public static String resumenComparativo() {
        // TODO 7: Devolver un String con la comparacion:
        //         "IO: mas verbose | NIO.2: mas conciso"
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 42: NIO vs IO ===\n");

        Path dir = Paths.get("temp", "bloque7");
        Files.createDirectories(dir);

        String texto = "Texto de prueba para comparar enfoques.";

        escribirIO(dir + "/io.txt", texto);
        escribirNIO(dir + "/nio.txt", texto);
        System.out.println("IO: " + leerIO(dir + "/io.txt"));
        System.out.println("NIO: " + leerNIO(dir + "/nio.txt"));
        System.out.println(resumenComparativo());
    }
}
