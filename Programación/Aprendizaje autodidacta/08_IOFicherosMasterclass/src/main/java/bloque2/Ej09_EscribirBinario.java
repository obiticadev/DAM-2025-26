package bloque2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * EJERCICIO 09 — Escribir Datos Binarios
 * Teoria: teoria/02_Texto_vs_Binario.md (seccion 3)
 *
 * Contexto: El sistema de seguridad del hotel almacena capturas de las camaras
 * como datos binarios. Tu tarea es crear funciones para escribir estos datos
 * en ficheros binarios de forma correcta.
 */
public class Ej09_EscribirBinario {

    /**
     * Escribe una cabecera de fichero binario personalizada.
     * La cabecera consta de: 4 bytes de "firma" + 4 bytes con el tamano del payload.
     * El tamano se escribe en formato big-endian (byte mas significativo primero).
     *
     * @param ruta    ruta del fichero destino
     * @param firma   4 bytes de firma (debe tener exactamente longitud 4)
     * @param tamano  tamano del payload que se escribira despues
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si la firma no tiene exactamente 4 bytes
     */
    public static void escribirCabecera(String ruta, byte[] firma, int tamano) throws IOException {
        // TODO 1: Validar que firma.length == 4.
        //         Crear FileOutputStream. Escribir los 4 bytes de firma.
        //         Descomponer tamano en 4 bytes big-endian:
        //           byte 0: (tamano >> 24) & 0xFF
        //           byte 1: (tamano >> 16) & 0xFF
        //           byte 2: (tamano >> 8) & 0xFF
        //           byte 3: tamano & 0xFF
        //         Escribir los 4 bytes del tamano. Cerrar stream.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Genera un fichero binario con un patron repetido de bytes.
     * Util para simular datos de imagen o audio.
     *
     * @param ruta    ruta del fichero destino
     * @param patron  patron de bytes a repetir
     * @param veces   cuantas veces repetir el patron
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si patron es null/vacio o veces < 0
     */
    public static void generarPatron(String ruta, byte[] patron, int veces) throws IOException {
        // TODO 2: Validar parametros. Crear FileOutputStream.
        //         Escribir el patron 'veces' veces usando write(byte[]).
        //         Cerrar stream.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Escribe un array de enteros como bytes en un fichero.
     * Cada int se reduce a su byte menos significativo (valor & 0xFF).
     *
     * @param ruta   ruta del fichero destino
     * @param valores array de enteros
     * @throws IOException si hay error de escritura
     */
    public static void escribirIntComoBytes(String ruta, int[] valores) throws IOException {
        // TODO 3: Crear FileOutputStream. Para cada int del array,
        //         escribir solo el byte bajo con write(valor & 0xFF) o write(int).
        //         Cerrar stream.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Anade datos binarios al final de un fichero existente.
     *
     * @param ruta  ruta del fichero
     * @param datos bytes a anadir
     * @throws IOException si hay error de escritura
     */
    public static void anadirDatos(String ruta, byte[] datos) throws IOException {
        // TODO 4: Crear FileOutputStream en modo append.
        //         Escribir los datos. Cerrar stream.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Genera un fichero con una secuencia de bytes que representan un gradiente:
     * desde valorInicio hasta valorFin, incrementando de forma lineal.
     * La longitud del fichero es (valorFin - valorInicio + 1) bytes.
     *
     * @param ruta        ruta del fichero destino
     * @param valorInicio primer byte del gradiente (0-255)
     * @param valorFin    ultimo byte del gradiente (0-255)
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si valorInicio > valorFin o fuera de rango 0-255
     */
    public static void generarGradiente(String ruta, int valorInicio, int valorFin) throws IOException {
        // TODO 5: Validar que ambos valores esten en [0, 255] y valorInicio <= valorFin.
        //         Crear FileOutputStream. Escribir cada byte del gradiente.
        //         Cerrar stream.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Escribe un string como bytes usando una codificacion especifica.
     *
     * @param ruta         ruta del fichero destino
     * @param texto        texto a escribir
     * @param charsetNombre nombre del charset (ej: "UTF-8", "ISO-8859-1")
     * @throws IOException si hay error de escritura
     */
    public static void escribirConCharset(String ruta, String texto, String charsetNombre) throws IOException {
        // TODO 6: Convertir texto a byte[] con texto.getBytes(charsetNombre).
        //         Crear FileOutputStream y escribir los bytes.
        //         Cerrar stream.
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Calcula y devuelve un checksum simple (suma de todos los bytes modulo 256)
     * del array proporcionado.
     *
     * @param datos array de bytes
     * @return checksum (0-255)
     */
    public static int checksumSimple(byte[] datos) {
        // TODO 7: Sumar todos los bytes del array (como int sin signo: b & 0xFF).
        //         Devolver suma % 256.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 09: Escribir Binario ===\n");

        String dir = "temp/bloque2";
        new File(dir).mkdirs();

        byte[] firma = {0x43, 0x41, 0x4D, 0x00}; // "CAM\0"
        escribirCabecera(dir + "/captura.bin", firma, 1024);
        System.out.println("Cabecera escrita (8 bytes)");

        generarPatron(dir + "/patron.bin", new byte[]{(byte) 0xAA, (byte) 0xBB}, 5);
        System.out.println("Patron escrito: " + new File(dir + "/patron.bin").length() + " bytes");

        escribirIntComoBytes(dir + "/ints.bin", new int[]{65, 300, 66, 67});
        System.out.println("Ints como bytes escritos");

        generarGradiente(dir + "/gradiente.bin", 0, 255);
        System.out.println("Gradiente: " + new File(dir + "/gradiente.bin").length() + " bytes");

        escribirConCharset(dir + "/utf8.bin", "Hola cafe", "UTF-8");
        escribirConCharset(dir + "/latin1.bin", "Hola cafe", "ISO-8859-1");
        System.out.println("UTF-8: " + new File(dir + "/utf8.bin").length() + " bytes");
        System.out.println("Latin1: " + new File(dir + "/latin1.bin").length() + " bytes");

        System.out.println("Checksum de {10,20,30}: " + checksumSimple(new byte[]{10, 20, 30}));
    }
}
