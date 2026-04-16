package bloque2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * EJERCICIO 10 — Leer Datos Binarios
 * Teoria: teoria/02_Texto_vs_Binario.md (seccion 3)
 *
 * Contexto: Las capturas de las camaras de seguridad del hotel estan
 * almacenadas como ficheros binarios con cabecera. Tu tarea es leerlos
 * y verificar su integridad.
 */
public class Ej10_LeerBinario {

    /**
     * Lee la firma (primeros 4 bytes) de un fichero binario.
     *
     * @param ruta ruta del fichero
     * @return array de 4 bytes con la firma
     * @throws IOException si hay error de lectura o el fichero tiene menos de 4 bytes
     */
    public static byte[] leerFirma(String ruta) throws IOException {
        // TODO 1: Abrir FileInputStream. Crear byte[4].
        //         Leer 4 bytes con read(byte[]). Si lee menos de 4, lanzar IOException.
        //         Cerrar stream. Devolver el array.
        return null;
    }

    /**
     * Lee un entero de 4 bytes en formato big-endian a partir de la posicion indicada.
     * Descarta los bytes anteriores leyendolos.
     *
     * @param ruta     ruta del fichero
     * @param posicion posicion del primer byte del entero (0-indexed)
     * @return valor entero reconstruido
     * @throws IOException si hay error de lectura
     */
    public static int leerIntBigEndian(String ruta, int posicion) throws IOException {
        // TODO 2: Abrir FileInputStream. Saltar 'posicion' bytes con read().
        //         Leer 4 bytes consecutivos.
        //         Reconstruir el int: (b0 << 24) | (b1 << 16) | (b2 << 8) | b3
        //         Cerrar stream. Devolver el int.
        return 0;
    }

    /**
     * Lee todo el contenido de un fichero binario y lo devuelve como array de bytes.
     *
     * @param ruta ruta del fichero
     * @return array con todos los bytes
     * @throws IOException si hay error de lectura
     */
    public static byte[] leerTodo(String ruta) throws IOException {
        // TODO 3: Obtener tamano con File.length(). Crear byte[] de ese tamano.
        //         Abrir FileInputStream. Leer todo con read(byte[]).
        //         Cerrar stream. Devolver array.
        return null;
    }

    /**
     * Compara los primeros n bytes de un fichero con un array esperado.
     *
     * @param ruta     ruta del fichero
     * @param esperado bytes esperados
     * @return true si los primeros bytes coinciden
     * @throws IOException si hay error de lectura
     */
    public static boolean verificarInicio(String ruta, byte[] esperado) throws IOException {
        // TODO 4: Abrir FileInputStream. Crear buffer del tamano de 'esperado'.
        //         Leer ese numero de bytes. Comparar con Arrays.equals.
        //         Cerrar stream. Devolver resultado.
        return false;
    }

    /**
     * Busca un byte concreto en un fichero y devuelve la posicion de su primera
     * aparicion (0-indexed), o -1 si no se encuentra.
     *
     * @param ruta       ruta del fichero
     * @param byteBusca  byte a buscar (0-255)
     * @return posicion (0-indexed) o -1
     * @throws IOException si hay error de lectura
     */
    public static int buscarByte(String ruta, int byteBusca) throws IOException {
        // TODO 5: Abrir FileInputStream. Leer byte a byte llevando un contador.
        //         Si el byte leido coincide, cerrar y devolver posicion.
        //         Si se llega al final, cerrar y devolver -1.
        return -1;
    }

    /**
     * Extrae un rango de bytes de un fichero (ambos indices inclusivos, 0-indexed).
     *
     * @param ruta  ruta del fichero
     * @param desde indice del primer byte (inclusivo)
     * @param hasta indice del ultimo byte (inclusivo)
     * @return array con los bytes del rango
     * @throws IOException              si hay error de lectura
     * @throws IllegalArgumentException si desde > hasta o indices negativos
     */
    public static byte[] extraerRango(String ruta, int desde, int hasta) throws IOException {
        // TODO 6: Validar parametros. Abrir FileInputStream.
        //         Saltar 'desde' bytes. Leer (hasta - desde + 1) bytes.
        //         Cerrar stream. Devolver array.
        return null;
    }

    /**
     * Calcula el checksum (suma de todos los bytes modulo 256) de un fichero.
     *
     * @param ruta ruta del fichero
     * @return checksum (0-255)
     * @throws IOException si hay error de lectura
     */
    public static int checksumFichero(String ruta) throws IOException {
        // TODO 7: Abrir FileInputStream. Leer byte a byte.
        //         Acumular suma (cada byte como valor sin signo: b & 0xFF).
        //         Cerrar stream. Devolver suma % 256.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 10: Leer Binario ===\n");

        String dir = "temp/bloque2";
        new File(dir).mkdirs();

        // Crear fichero de prueba con cabecera
        try (FileOutputStream fos = new FileOutputStream(dir + "/test_bin.dat")) {
            fos.write(new byte[]{0x43, 0x41, 0x4D, 0x00}); // firma "CAM\0"
            fos.write(new byte[]{0x00, 0x00, 0x04, 0x00}); // tamano: 1024
            fos.write(new byte[]{10, 20, 30, 40, 50});      // payload
        }

        byte[] firma = leerFirma(dir + "/test_bin.dat");
        if (firma != null) System.out.println("Firma: " + Arrays.toString(firma));

        System.out.println("Tamano (int en pos 4): " + leerIntBigEndian(dir + "/test_bin.dat", 4));

        byte[] todo = leerTodo(dir + "/test_bin.dat");
        if (todo != null) System.out.println("Total bytes: " + todo.length);

        System.out.println("Verifica firma CAM: " +
                verificarInicio(dir + "/test_bin.dat", new byte[]{0x43, 0x41, 0x4D, 0x00}));

        System.out.println("Posicion del byte 30: " + buscarByte(dir + "/test_bin.dat", 30));

        byte[] rango = extraerRango(dir + "/test_bin.dat", 8, 12);
        if (rango != null) System.out.println("Rango [8,12]: " + Arrays.toString(rango));

        System.out.println("Checksum: " + checksumFichero(dir + "/test_bin.dat"));
    }
}
