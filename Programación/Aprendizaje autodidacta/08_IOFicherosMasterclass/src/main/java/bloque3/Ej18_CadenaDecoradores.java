package bloque3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EJERCICIO 18 — Cadena de Decoradores y DataStreams
 * Teoria: teoria/03_Bufferizacion.md (seccion 6)
 *
 * Contexto: El restaurante guarda datos numericos de facturacion (enteros y decimales)
 * en ficheros binarios. Necesitas usar DataInputStream/DataOutputStream
 * (decoradores de alto nivel) para leer y escribir tipos primitivos directamente.
 */
public class Ej18_CadenaDecoradores {

    /**
     * Escribe un array de enteros en un fichero binario usando DataOutputStream.
     * Cada int ocupa 4 bytes en el fichero.
     *
     * @param ruta    ruta del fichero destino
     * @param valores array de enteros
     * @throws IOException si hay error de escritura
     */
    public static void escribirEnteros(String ruta, int[] valores) throws IOException {
        // TODO 1: Crear cadena: DataOutputStream -> BufferedOutputStream -> FileOutputStream.
        //         Escribir cada int con writeInt().
        //         Cerrar el stream mas externo.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee N enteros de un fichero binario usando DataInputStream.
     *
     * @param ruta ruta del fichero
     * @param n    numero de enteros a leer
     * @return array con los enteros leidos
     * @throws IOException si hay error de lectura
     */
    public static int[] leerEnteros(String ruta, int n) throws IOException {
        // TODO 2: Crear cadena: DataInputStream -> BufferedInputStream -> FileInputStream.
        //         Leer N enteros con readInt(). Almacenar en array.
        //         Cerrar el stream. Devolver array.
        return new int[0];
    }

    /**
     * Escribe una factura binaria con formato:
     * - 1 int: numero de factura
     * - 1 int: numero de items
     * - N doubles: precio de cada item
     * - 1 double: total (suma de precios)
     *
     * @param ruta    ruta del fichero
     * @param numFact numero de factura
     * @param precios array de precios
     * @throws IOException si hay error de escritura
     */
    public static void escribirFactura(String ruta, int numFact, double[] precios) throws IOException {
        // TODO 3: Crear cadena DataOutputStream -> BufferedOutputStream -> FileOutputStream.
        //         Escribir numFact con writeInt().
        //         Escribir precios.length con writeInt().
        //         Escribir cada precio con writeDouble().
        //         Calcular y escribir el total con writeDouble().
        //         Cerrar stream.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Lee una factura binaria y devuelve un resumen formateado:
     * "Factura #[num] | Items: [n] | Total: [total] EUR"
     *
     * @param ruta ruta del fichero
     * @return resumen formateado
     * @throws IOException si hay error de lectura
     */
    public static String leerFactura(String ruta) throws IOException {
        // TODO 4: Crear cadena DataInputStream -> BufferedInputStream -> FileInputStream.
        //         Leer numFact y numItems con readInt().
        //         Leer precios con readDouble() (numItems veces).
        //         Leer total con readDouble().
        //         Cerrar stream. Formatear con String.format.
        return "";
    }

    /**
     * Escribe pares clave-valor en un fichero binario.
     * Formato por par: writeUTF(clave) + writeDouble(valor).
     * Antes de los pares, escribe el numero de pares con writeInt.
     *
     * @param ruta    ruta del fichero
     * @param claves  array de claves
     * @param valores array de valores
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si los arrays tienen distinta longitud
     */
    public static void escribirMapaBinario(String ruta, String[] claves,
                                            double[] valores) throws IOException {
        // TODO 5: Validar longitudes. Crear cadena DataOutputStream.
        //         Escribir claves.length con writeInt().
        //         Para cada par, writeUTF(clave) y writeDouble(valor).
        //         Cerrar stream.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Lee el mapa binario y devuelve un String con todas las claves y valores.
     * Formato por linea: "[clave]: [valor]"
     *
     * @param ruta ruta del fichero
     * @return contenido formateado
     * @throws IOException si hay error de lectura
     */
    public static String leerMapaBinario(String ruta) throws IOException {
        // TODO 6: Crear cadena DataInputStream. Leer numero de pares.
        //         Leer cada clave con readUTF() y valor con readDouble().
        //         Formatear cada par como "[clave]: [valor]".
        //         Unir con '\n'. Cerrar stream. Devolver String.
        return "";
    }

    /**
     * Calcula el tamano esperado en bytes de un fichero con N enteros.
     * Cada int de DataOutputStream ocupa 4 bytes.
     *
     * @param numEnteros numero de enteros
     * @return tamano esperado en bytes
     */
    public static int tamanoEsperadoEnteros(int numEnteros) {
        // TODO 7: Devolver numEnteros * 4.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 18: Cadena de Decoradores ===\n");

        String dir = "temp/bloque3";
        new File(dir).mkdirs();

        int[] datos = {100, 200, 300, 400, 500};
        escribirEnteros(dir + "/enteros.bin", datos);
        System.out.println("Tamano: " + new File(dir + "/enteros.bin").length() +
                " bytes (esperado: " + tamanoEsperadoEnteros(5) + ")");

        int[] leidos = leerEnteros(dir + "/enteros.bin", 5);
        System.out.print("Leidos: ");
        for (int v : leidos) System.out.print(v + " ");
        System.out.println();

        escribirFactura(dir + "/factura.bin", 1001, new double[]{12.50, 8.00, 4.50});
        System.out.println(leerFactura(dir + "/factura.bin"));

        escribirMapaBinario(dir + "/mapa.bin",
                new String[]{"Paella", "Agua", "Flan"},
                new double[]{12.50, 2.00, 4.50});
        System.out.println(leerMapaBinario(dir + "/mapa.bin"));
    }
}
