package bloque1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * EJERCICIO 03 — Copiar un Fichero Byte a Byte
 * Teoria: teoria/01_Flujos_De_Datos.md (secciones 1-5)
 *
 * Contexto: El almacen necesita un sistema de backup. Tu tarea es crear una
 * utilidad que copie ficheros binarios. Empezaras con la forma mas basica
 * (byte a byte) para entender la mecanica de combinar Input + Output.
 */
public class Ej03_CopiarFichero {

    /**
     * Copia un fichero byte a byte. Este metodo es intencionalmente ineficiente
     * para que entiendas por que se necesitan buffers (Bloque 3).
     *
     * @param origen  ruta del fichero a copiar
     * @param destino ruta del fichero destino
     * @return cantidad de bytes copiados
     * @throws IOException si hay error de lectura o escritura
     */
    public static long copiarByteAByte(String origen, String destino) throws IOException {
        // TODO 1: Abrir FileInputStream para el origen y FileOutputStream para el destino.
        //         Leer byte a byte del origen con read().
        //         Escribir cada byte en el destino con write(int).
        //         Contar los bytes copiados. Cerrar ambos streams.
        //         Devolver el total de bytes copiados.
        return 0;
    }

    /**
     * Copia un fichero usando un buffer de tamano fijo.
     * Mucho mas eficiente que copiar byte a byte.
     *
     * @param origen      ruta del fichero a copiar
     * @param destino     ruta del fichero destino
     * @param tamanoBuffer tamano del buffer en bytes
     * @return cantidad de bytes copiados
     * @throws IOException              si hay error de lectura o escritura
     * @throws IllegalArgumentException si tamanoBuffer <= 0
     */
    public static long copiarConBuffer(String origen, String destino, int tamanoBuffer) throws IOException {
        // TODO 2: Validar tamanoBuffer > 0. Crear byte[] del tamano indicado.
        //         Abrir FileInputStream y FileOutputStream.
        //         Leer con read(buffer, 0, buffer.length) en un bucle.
        //         Escribir con write(buffer, 0, bytesLeidos) — IMPORTANTE: solo
        //         los bytes realmente leidos, no todo el buffer.
        //         Acumular total. Cerrar ambos streams. Devolver total.
        return 0;
    }

    /**
     * Mide el tiempo en milisegundos que tarda en copiar un fichero byte a byte.
     *
     * @param origen  ruta del fichero a copiar
     * @param destino ruta del fichero destino
     * @return tiempo en milisegundos
     * @throws IOException si hay error de lectura o escritura
     */
    public static long medirTiempoByteAByte(String origen, String destino) throws IOException {
        // TODO 3: Capturar System.nanoTime() antes y despues de llamar a copiarByteAByte.
        //         Calcular la diferencia y convertir a milisegundos (dividir entre 1_000_000).
        //         Devolver el tiempo.
        return 0;
    }

    /**
     * Mide el tiempo en milisegundos que tarda en copiar un fichero con buffer.
     *
     * @param origen       ruta del fichero a copiar
     * @param destino      ruta del fichero destino
     * @param tamanoBuffer tamano del buffer
     * @return tiempo en milisegundos
     * @throws IOException si hay error de lectura o escritura
     */
    public static long medirTiempoConBuffer(String origen, String destino, int tamanoBuffer) throws IOException {
        // TODO 4: Igual que TODO 3, pero llamando a copiarConBuffer.
        return 0;
    }

    /**
     * Genera un fichero de prueba con el tamano indicado en bytes,
     * rellenandolo con bytes aleatorios (valores 0-255).
     *
     * @param ruta   ruta del fichero a crear
     * @param tamano tamano en bytes
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si tamano es negativo
     */
    public static void generarFicheroPrueba(String ruta, int tamano) throws IOException {
        // TODO 5: Validar tamano >= 0. Crear FileOutputStream.
        //         En un bucle, escribir bytes con valores (i % 256) para llenar el fichero.
        //         Cerrar el stream.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Verifica que dos ficheros son copias identicas comparando tamano
     * y contenido byte a byte.
     *
     * @param ruta1 ruta del primer fichero
     * @param ruta2 ruta del segundo fichero
     * @return true si son identicos en tamano y contenido
     * @throws IOException si hay error de lectura
     */
    public static boolean verificarCopia(String ruta1, String ruta2) throws IOException {
        // TODO 6: Comparar tamanos con File.length(). Si difieren, devolver false.
        //         Abrir dos FileInputStream y comparar byte a byte.
        //         Cerrar ambos streams. Devolver true si son identicos.
        return false;
    }

    /**
     * Devuelve un String con un resumen de la comparacion de rendimiento:
     * "Byte a byte: X ms | Buffer (Y bytes): Z ms | Speedup: W.Wx"
     *
     * @param tiempoBaB    tiempo byte a byte en ms
     * @param tiempoBuffer tiempo con buffer en ms
     * @param tamanoBuffer tamano del buffer usado
     * @return resumen formateado
     */
    public static String resumenRendimiento(long tiempoBaB, long tiempoBuffer, int tamanoBuffer) {
        // TODO 7: Calcular el speedup (tiempoBaB / tiempoBuffer) como double.
        //         Si tiempoBuffer es 0, usar 1 para evitar division por cero.
        //         Formatear con String.format:
        //         "Byte a byte: %d ms | Buffer (%d bytes): %d ms | Speedup: %.1fx"
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 03: Copiar Fichero ===\n");

        String dir = "temp/bloque1";
        new File(dir).mkdirs();

        System.out.println("Generando fichero de prueba (100 KB)...");
        generarFicheroPrueba(dir + "/grande.bin", 100_000);

        System.out.println("Copiando byte a byte...");
        long t1 = medirTiempoByteAByte(dir + "/grande.bin", dir + "/copia_bab.bin");

        System.out.println("Copiando con buffer 4096...");
        long t2 = medirTiempoConBuffer(dir + "/grande.bin", dir + "/copia_buf.bin", 4096);

        System.out.println(resumenRendimiento(t1, t2, 4096));

        System.out.println("Verificando copia byte a byte: " +
                verificarCopia(dir + "/grande.bin", dir + "/copia_bab.bin"));
        System.out.println("Verificando copia con buffer: " +
                verificarCopia(dir + "/grande.bin", dir + "/copia_buf.bin"));
    }
}
