package bloque1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * EJERCICIO 01 — Escribir Bytes a un Fichero
 * Teoria: teoria/01_Flujos_De_Datos.md (secciones 1-4)
 *
 * Contexto: Trabajas en un almacen digital y necesitas crear ficheros binarios
 * con datos de inventario codificados como bytes. Tu primera tarea es aprender
 * a abrir una tuberia de salida y volcar datos al disco.
 */
public class Ej01_EscribirBytes {

    /**
     * Crea el directorio padre del fichero si no existe.
     *
     * @param ruta ruta del fichero cuyo directorio padre se debe crear
     * @return true si el directorio ya existia o se creo correctamente
     */
    public static boolean crearDirectorioPadre(String ruta) {
        // TODO 1: Obtener el directorio padre de la ruta usando new File(ruta).getParentFile().
        //         Si el padre no es null y no existe, crearlo con mkdirs().
        //         Devolver true si ya existia o se creo bien, false en caso contrario.
        return false;
    }

    /**
     * Escribe un unico byte en un fichero nuevo. Si el fichero existe, lo sobrescribe.
     *
     * @param ruta  ruta del fichero destino
     * @param valor byte a escribir (0-255)
     * @throws IOException si hay error de escritura
     */
    public static void escribirUnByte(String ruta, int valor) throws IOException {
        // TODO 2: Crear un FileOutputStream con la ruta recibida.
        //         Escribir el valor con write(int).
        //         Cerrar el stream en un bloque finally o try-finally.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Escribe un array de bytes completo en un fichero nuevo.
     * Si el fichero existe, lo sobrescribe.
     *
     * @param ruta  ruta del fichero destino
     * @param datos array de bytes a escribir
     * @throws IOException si hay error de escritura
     */
    public static void escribirArrayBytes(String ruta, byte[] datos) throws IOException {
        // TODO 3: Crear un FileOutputStream con la ruta.
        //         Escribir todo el array con write(byte[]).
        //         Cerrar el stream.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Escribe un array de bytes al final de un fichero existente (modo append).
     * Si el fichero no existe, lo crea.
     *
     * @param ruta  ruta del fichero destino
     * @param datos array de bytes a anadir
     * @throws IOException si hay error de escritura
     */
    public static void anadirBytes(String ruta, byte[] datos) throws IOException {
        // TODO 4: Crear un FileOutputStream en modo APPEND (segundo parametro true).
        //         Escribir el array de bytes.
        //         Cerrar el stream.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Escribe un String como bytes en un fichero, usando la codificacion
     * por defecto del sistema.
     *
     * @param ruta  ruta del fichero destino
     * @param texto texto a escribir
     * @throws IOException si hay error de escritura
     */
    public static void escribirTextoComoBytes(String ruta, String texto) throws IOException {
        // TODO 5: Convertir el texto a byte[] con texto.getBytes().
        //         Usar FileOutputStream para escribir ese array.
        //         Cerrar el stream.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Escribe una secuencia de numeros enteros (0 a n-1) como bytes en un fichero.
     * Cada numero se escribe como un byte individual.
     *
     * @param ruta ruta del fichero destino
     * @param n    cantidad de bytes a escribir (de 0 a n-1)
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si n es negativo o mayor que 256
     */
    public static void escribirSecuencia(String ruta, int n) throws IOException {
        // TODO 6: Validar que n >= 0 y n <= 256. Si no, lanzar IllegalArgumentException.
        //         Crear FileOutputStream.
        //         En un bucle de 0 a n-1, escribir cada valor con write(int).
        //         Cerrar el stream.
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Devuelve el tamano en bytes de un fichero.
     *
     * @param ruta ruta del fichero
     * @return tamano en bytes, o -1 si el fichero no existe
     */
    public static long obtenerTamano(String ruta) {
        // TODO 7: Crear un objeto File con la ruta.
        //         Si existe, devolver file.length().
        //         Si no existe, devolver -1.
        return -1;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 01: Escribir Bytes ===\n");

        String dir = "temp/bloque1";
        crearDirectorioPadre(dir + "/dummy.txt");

        System.out.println("1. Escribir un byte:");
        escribirUnByte(dir + "/un_byte.bin", 65);
        System.out.println("   Tamano: " + obtenerTamano(dir + "/un_byte.bin") + " bytes");

        System.out.println("2. Escribir array de bytes:");
        escribirArrayBytes(dir + "/array.bin", new byte[]{72, 111, 108, 97});
        System.out.println("   Tamano: " + obtenerTamano(dir + "/array.bin") + " bytes");

        System.out.println("3. Anadir bytes:");
        anadirBytes(dir + "/array.bin", new byte[]{33});
        System.out.println("   Tamano tras anadir: " + obtenerTamano(dir + "/array.bin") + " bytes");

        System.out.println("4. Escribir texto como bytes:");
        escribirTextoComoBytes(dir + "/texto.bin", "Hola Mundo");
        System.out.println("   Tamano: " + obtenerTamano(dir + "/texto.bin") + " bytes");

        System.out.println("5. Escribir secuencia 0-9:");
        escribirSecuencia(dir + "/secuencia.bin", 10);
        System.out.println("   Tamano: " + obtenerTamano(dir + "/secuencia.bin") + " bytes");
    }
}
