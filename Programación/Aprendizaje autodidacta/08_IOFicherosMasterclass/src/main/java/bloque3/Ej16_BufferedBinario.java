package bloque3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * EJERCICIO 16 — Buffered Streams para Datos Binarios
 * Teoria: teoria/03_Bufferizacion.md (seccion 6)
 *
 * Contexto: El restaurante necesita un backup diario de los ficheros binarios
 * de su sistema de punto de venta. Los ficheros pueden ser grandes y la
 * eficiencia en la copia es crucial.
 */
public class Ej16_BufferedBinario {

    /**
     * Copia un fichero binario usando BufferedInputStream/BufferedOutputStream.
     *
     * @param origen  ruta del fichero origen
     * @param destino ruta del fichero destino
     * @return numero de bytes copiados
     * @throws IOException si hay error de I/O
     */
    public static long copiarBuffered(String origen, String destino) throws IOException {
        // TODO 1: Crear BufferedInputStream envolviendo FileInputStream.
        //         Crear BufferedOutputStream envolviendo FileOutputStream.
        //         Leer byte a byte con read() y escribir con write().
        //         Contar bytes. Cerrar ambos. Devolver total.
        return 0;
    }

    /**
     * Copia un fichero binario usando BufferedStreams con buffer personalizado.
     *
     * @param origen     ruta del fichero origen
     * @param destino    ruta del fichero destino
     * @param tamBuffer  tamano del buffer en bytes
     * @return numero de bytes copiados
     * @throws IOException              si hay error de I/O
     * @throws IllegalArgumentException si tamBuffer <= 0
     */
    public static long copiarConTamBuffer(String origen, String destino, int tamBuffer) throws IOException {
        // TODO 2: Validar tamBuffer > 0.
        //         Crear BufferedInputStream con tamano personalizado.
        //         Crear BufferedOutputStream con tamano personalizado.
        //         Crear byte[] manual del tamano indicado.
        //         Leer con read(byte[]) y escribir con write(byte[], 0, n).
        //         Contar bytes totales. Cerrar ambos. Devolver total.
        return 0;
    }

    /**
     * Compara el tiempo de copia con y sin buffer (en nanosegundos).
     *
     * @param origen ruta del fichero origen
     * @param dir    directorio donde crear las copias temporales
     * @return array de 2 elementos: [tiempoSinBuffer, tiempoConBuffer]
     * @throws IOException si hay error de I/O
     */
    public static long[] compararRendimiento(String origen, String dir) throws IOException {
        // TODO 3: Copiar sin buffer (FileInputStream/FileOutputStream directos byte a byte).
        //         Medir tiempo con System.nanoTime().
        //         Copiar con buffer (BufferedInputStream/BufferedOutputStream).
        //         Medir tiempo. Devolver array con ambos tiempos.
        return new long[]{0, 0};
    }

    /**
     * Genera un fichero binario de prueba del tamano indicado.
     * Rellena con bytes secuenciales (i % 256).
     *
     * @param ruta   ruta del fichero
     * @param tamano tamano en bytes
     * @throws IOException si hay error de escritura
     */
    public static void generarFicheroPrueba(String ruta, int tamano) throws IOException {
        // TODO 4: Crear BufferedOutputStream. Escribir 'tamano' bytes.
        //         Cada byte es (i % 256). Cerrar stream.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Verifica si dos ficheros binarios son identicos usando BufferedInputStream.
     *
     * @param ruta1 primer fichero
     * @param ruta2 segundo fichero
     * @return true si son byte-a-byte identicos
     * @throws IOException si hay error de lectura
     */
    public static boolean sonIdenticos(String ruta1, String ruta2) throws IOException {
        // TODO 5: Si tamanos distintos, devolver false directamente.
        //         Crear BufferedInputStream para cada fichero.
        //         Leer byte a byte en paralelo. Si algun par difiere, devolver false.
        //         Si se llega al final sin diferencias, devolver true.
        //         Cerrar ambos.
        return false;
    }

    /**
     * Genera un informe de rendimiento comparando distintos tamanos de buffer.
     * Formato por linea: "Buffer %6d bytes -> %d ns"
     *
     * @param origen   ruta del fichero origen
     * @param dir      directorio para copias temporales
     * @param tamanos  array de tamanos de buffer a probar
     * @return informe formateado
     * @throws IOException si hay error de I/O
     */
    public static String informeRendimiento(String origen, String dir, int[] tamanos) throws IOException {
        // TODO 6: Para cada tamano, medir copiarConTamBuffer con System.nanoTime().
        //         Formatear cada linea con String.format.
        //         Unir con '\n'. Devolver informe.
        return "";
    }

    /**
     * Calcula la mejora porcentual de un tiempo rapido respecto a uno lento.
     * Formula: ((lento - rapido) / lento) * 100
     *
     * @param tiempoLento  tiempo mas lento en nanosegundos
     * @param tiempoRapido tiempo mas rapido en nanosegundos
     * @return porcentaje de mejora (0-100+)
     * @throws IllegalArgumentException si tiempoLento <= 0
     */
    public static double mejoraPorcentual(long tiempoLento, long tiempoRapido) {
        // TODO 7: Validar tiempoLento > 0.
        //         Calcular y devolver ((lento - rapido) / (double) lento) * 100.
        return 0.0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 16: Buffered Binario ===\n");

        String dir = "temp/bloque3";
        new File(dir).mkdirs();

        generarFicheroPrueba(dir + "/grande.bin", 100_000);
        System.out.println("Fichero de prueba: " + new File(dir + "/grande.bin").length() + " bytes");

        long bytes = copiarBuffered(dir + "/grande.bin", dir + "/copia_buf.bin");
        System.out.println("Copiados: " + bytes + " bytes");

        System.out.println("Identicos: " +
                sonIdenticos(dir + "/grande.bin", dir + "/copia_buf.bin"));

        long[] tiempos = compararRendimiento(dir + "/grande.bin", dir);
        System.out.printf("Sin buffer: %d ns%n", tiempos[0]);
        System.out.printf("Con buffer: %d ns%n", tiempos[1]);
        System.out.printf("Mejora: %.1f%%%n", mejoraPorcentual(tiempos[0], tiempos[1]));
    }
}
