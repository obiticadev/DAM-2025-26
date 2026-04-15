package bloque1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * EJERCICIO 04 — Copiar con Buffer Manual y Multiples Tamanos
 * Teoria: teoria/01_Flujos_De_Datos.md (seccion 5)
 *
 * Contexto: El equipo de sistemas del almacen quiere optimizar las copias de backup.
 * Te piden experimentar con distintos tamanos de buffer para encontrar el optimo
 * y generar un informe comparativo.
 */
public class Ej04_CopiarConBufferManual {

    /**
     * Copia un fichero usando read(byte[], 0, len) y write(byte[], 0, len).
     * Devuelve el numero de "viajes al disco" (cuantas veces se llamo a read con exito).
     *
     * @param origen      ruta del fichero origen
     * @param destino     ruta del fichero destino
     * @param tamBuffer   tamano del buffer en bytes
     * @return numero de lecturas realizadas (viajes al disco)
     * @throws IOException              si hay error de I/O
     * @throws IllegalArgumentException si tamBuffer <= 0
     */
    public static int copiarContandoViajes(String origen, String destino, int tamBuffer) throws IOException {
        // TODO 1: Validar tamBuffer > 0. Crear buffer byte[].
        //         Abrir FileInputStream y FileOutputStream.
        //         Leer bloques con read(buffer, 0, buffer.length) en bucle.
        //         Escribir con write(buffer, 0, bytesLeidos).
        //         Contar cada lectura exitosa. Cerrar streams. Devolver contador.
        return 0;
    }

    /**
     * Calcula cuantos viajes se necesitarian teoricamente para copiar un fichero
     * con un buffer del tamano dado (redondeando hacia arriba).
     *
     * @param tamanoFichero tamano del fichero en bytes
     * @param tamBuffer     tamano del buffer en bytes
     * @return numero teorico de viajes (ceil division)
     * @throws IllegalArgumentException si alguno de los parametros es <= 0
     */
    public static int viajesTeoricos(long tamanoFichero, int tamBuffer) {
        // TODO 2: Validar ambos parametros > 0.
        //         Calcular ceil(tamanoFichero / tamBuffer) usando aritmetica entera:
        //         (tamanoFichero + tamBuffer - 1) / tamBuffer
        //         Devolver como int.
        return 0;
    }

    /**
     * Mide el tiempo en nanosegundos de copiar un fichero con un tamano de buffer dado.
     *
     * @param origen    ruta del fichero origen
     * @param destino   ruta del fichero destino
     * @param tamBuffer tamano del buffer
     * @return tiempo en nanosegundos
     * @throws IOException si hay error de I/O
     */
    public static long medirTiempoNanos(String origen, String destino, int tamBuffer) throws IOException {
        // TODO 3: Capturar System.nanoTime() antes y despues de copiarContandoViajes.
        //         Devolver la diferencia.
        return 0;
    }

    /**
     * Ejecuta la copia con varios tamanos de buffer y devuelve un informe formateado.
     * Formato por linea: "Buffer %6d bytes | Viajes: %4d | Tiempo: %8d ns"
     * Las lineas se separan con salto de linea.
     *
     * @param origen      ruta del fichero origen
     * @param prefijoDest prefijo para los ficheros destino (se anade _tamano.bin)
     * @param tamanos     array con los distintos tamanos de buffer a probar
     * @return informe con una linea por tamano
     * @throws IOException si hay error de I/O
     */
    public static String informeComparativo(String origen, String prefijoDest, int[] tamanos) throws IOException {
        // TODO 4: Usar StringBuilder. Para cada tamano en el array:
        //         - Construir nombre destino: prefijoDest + "_" + tamano + ".bin"
        //         - Llamar a copiarContandoViajes para obtener viajes reales.
        //         - Llamar a medirTiempoNanos para obtener el tiempo.
        //         - Formatear con String.format y anadir al StringBuilder.
        //         Devolver el String sin salto de linea al final.
        return "";
    }

    /**
     * Dado un array de tiempos en nanosegundos, devuelve el indice del menor.
     *
     * @param tiempos array de tiempos
     * @return indice del tiempo menor
     * @throws IllegalArgumentException si el array es null o vacio
     */
    public static int indiceMasRapido(long[] tiempos) {
        // TODO 5: Validar array no null ni vacio.
        //         Recorrer buscando el minimo. Devolver su indice.
        return -1;
    }

    /**
     * Genera un fichero de prueba con el tamano indicado, rellenando
     * con el patron repetido del byte (i % 256).
     *
     * @param ruta   ruta del fichero
     * @param tamano tamano en bytes
     * @throws IOException si hay error de escritura
     */
    public static void generarFichero(String ruta, int tamano) throws IOException {
        // TODO 6: Crear FileOutputStream. Escribir tamano bytes con valor (i % 256).
        //         Cerrar stream.
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Devuelve un resumen de una linea indicando cual es el mejor buffer.
     * Formato: "Mejor buffer: X bytes (Y ns)"
     *
     * @param tamanos  array de tamanos probados
     * @param tiempos  array de tiempos medidos
     * @return resumen del mejor resultado
     */
    public static String resumenMejor(int[] tamanos, long[] tiempos) {
        // TODO 7: Usar indiceMasRapido para encontrar el indice del mejor.
        //         Formatear: "Mejor buffer: %d bytes (%d ns)"
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 04: Buffer Manual y Comparativa ===\n");

        String dir = "temp/bloque1";
        new File(dir).mkdirs();

        generarFichero(dir + "/test_buffer.bin", 50_000);

        int[] tamanos = {1, 16, 256, 1024, 4096, 8192};
        System.out.println("Comparativa de buffers:");
        System.out.println(informeComparativo(dir + "/test_buffer.bin", dir + "/copia", tamanos));

        System.out.println("\nViajes teoricos para 50000 bytes:");
        for (int t : tamanos) {
            System.out.printf("  Buffer %5d -> %d viajes%n", t, viajesTeoricos(50_000, t));
        }
    }
}
