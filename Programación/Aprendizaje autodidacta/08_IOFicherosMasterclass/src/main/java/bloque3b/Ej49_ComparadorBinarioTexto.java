package bloque3b;

import java.io.*;

/**
 * EJERCICIO 49 — Comparador Binario vs Texto
 * 📋 ENTRA EN EXAMEN — Tema 05 (ventajas del formato binario)
 * Teoria: teoria/03B_ArchivosBinarios.md (seccion 7)
 *
 * Contexto: El arquitecto del sistema quiere demostrar a su equipo que guardar
 * datos en binario ocupa menos espacio que en texto.
 */
public class Ej49_ComparadorBinarioTexto {

    /**
     * Escribe N enteros en un fichero TEXTO (uno por linea).
     */
    public static void escribirEnTexto(String ruta, int[] datos) throws IOException {
        // TODO 1: Crear BufferedWriter (con FileWriter).
        //         Para cada int del array, escribir Integer.toString(dato) + newLine().
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Escribe N enteros en un fichero BINARIO.
     */
    public static void escribirEnBinario(String ruta, int[] datos) throws IOException {
        // TODO 2: Crear DataOutputStream (con FileOutputStream).
        //         Para cada int del array, writeInt(dato).
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Devuelve el tamano en bytes de un fichero.
     */
    public static long tamano(String ruta) {
        // TODO 3: Crear File. Devolver length() si existe, -1 si no.
        return -1;
    }

    /**
     * Compara el tamano del fichero texto vs binario y devuelve el ahorro en bytes.
     * ahorro = tamanoTexto - tamanoBinario
     */
    public static long calcularAhorro(String rutaTexto, String rutaBinario) {
        // TODO 4: Obtener tamano de ambos ficheros. Devolver la diferencia.
        return 0;
    }

    /**
     * Lee los enteros del fichero de texto y los devuelve como array.
     */
    public static int[] leerDesdeTexto(String ruta) throws IOException {
        // TODO 5: Usar BufferedReader. Leer linea a linea.
        //         Parsear cada linea con Integer.parseInt(linea.trim()).
        //         Acumular en ArrayList y convertir a int[].
        return new int[0];
    }

    /**
     * Lee los enteros del fichero binario y los devuelve como array.
     */
    public static int[] leerDesdeBinario(String ruta) throws IOException {
        // TODO 6: Usar DataInputStream. Leer con readInt() hasta EOFException.
        //         Acumular en ArrayList y convertir a int[].
        return new int[0];
    }

    /**
     * Verifica que ambos ficheros contienen los mismos datos.
     */
    public static boolean mismosDatos(String rutaTexto, String rutaBinario) throws IOException {
        // TODO 7: Leer desde texto y binario. Comparar los arrays elemento a elemento.
        //         Si tienen la misma longitud y todos los elementos coinciden, true.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 49: Comparador Binario vs Texto ===\n");

        String dir = "temp/bloque3b";
        new File(dir).mkdirs();

        int[] datos = new int[100];
        for (int i = 0; i < datos.length; i++) datos[i] = i * 1000;

        escribirEnTexto(dir + "/datos.txt", datos);
        escribirEnBinario(dir + "/datos.bin", datos);

        System.out.println("Tamano texto:   " + tamano(dir + "/datos.txt") + " bytes");
        System.out.println("Tamano binario: " + tamano(dir + "/datos.bin") + " bytes");
        System.out.println("Ahorro: " + calcularAhorro(dir + "/datos.txt", dir + "/datos.bin") + " bytes");
        System.out.println("Mismos datos: " + mismosDatos(dir + "/datos.txt", dir + "/datos.bin"));
    }
}
