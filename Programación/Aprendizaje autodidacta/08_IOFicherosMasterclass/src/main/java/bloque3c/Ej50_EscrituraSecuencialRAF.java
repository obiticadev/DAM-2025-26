package bloque3c;

import java.io.*;

/**
 * EJERCICIO 50 — Escritura Secuencial con RandomAccessFile
 * 📋 ENTRA EN EXAMEN — Tema 06 (RandomAccessFile, modos r/rw)
 * Teoria: teoria/03C_AccesoAleatorio.md (secciones 2-6)
 *
 * Contexto: Un sistema de registro necesita almacenar enteros en un fichero
 * de acceso aleatorio para poder modificar cualquier registro despues.
 */
public class Ej50_EscrituraSecuencialRAF {

    /**
     * Escribe N enteros secuencialmente: 0, 10, 20, ..., (N-1)*10.
     */
    public static void escribirSecuencia(String ruta, int n) throws IOException {
        // TODO 1: Crear RandomAccessFile con modo "rw".
        //         En un bucle de 0 a n-1, writeInt(i * 10).
        //         Cerrar con try-with-resources.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee el entero en la posicion indicada (indice 0-based).
     */
    public static int leerEnPosicion(String ruta, int indice) throws IOException {
        // TODO 2: Crear RandomAccessFile con modo "r".
        //         seek(indice * Integer.BYTES).
        //         Leer con readInt() y devolver.
        return 0;
    }

    /**
     * Devuelve cuantos enteros hay en el fichero.
     * Tamano del fichero / 4 (bytes por int).
     */
    public static int contarEnteros(String ruta) throws IOException {
        // TODO 3: Crear RandomAccessFile con modo "r".
        //         Devolver (int)(raf.length() / Integer.BYTES).
        return 0;
    }

    /**
     * Lee todos los enteros del fichero y los devuelve como array.
     */
    public static int[] leerTodos(String ruta) throws IOException {
        // TODO 4: Crear RandomAccessFile. Calcular cantidad con length()/4.
        //         Crear array. seek(0). Bucle leyendo readInt().
        return new int[0];
    }

    /**
     * Escribe un array de enteros en el fichero (sobreescribe si existe).
     */
    public static void escribirArray(String ruta, int[] datos) throws IOException {
        // TODO 5: Crear RandomAccessFile "rw". setLength(0) para vaciar.
        //         Bucle writeInt para cada elemento.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Devuelve el ultimo entero del fichero.
     */
    public static int leerUltimo(String ruta) throws IOException {
        // TODO 6: Crear RandomAccessFile "r".
        //         seek(raf.length() - Integer.BYTES) para ir al ultimo registro.
        //         readInt() y devolver.
        return 0;
    }

    /**
     * Devuelve el tamano del fichero en bytes.
     */
    public static long tamanoFichero(String ruta) throws IOException {
        // TODO 7: Crear RandomAccessFile "r". Devolver raf.length().
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 50: Escritura Secuencial RAF ===\n");

        String dir = "temp/bloque3c";
        new File(dir).mkdirs();
        String ruta = dir + "/enteros.dat";

        escribirSecuencia(ruta, 10);
        System.out.println("Escritos 10 enteros. Tamano: " + tamanoFichero(ruta) + " bytes");
        System.out.println("Posicion 0: " + leerEnPosicion(ruta, 0));
        System.out.println("Posicion 4: " + leerEnPosicion(ruta, 4));
        System.out.println("Ultimo: " + leerUltimo(ruta));
        System.out.println("Total: " + contarEnteros(ruta) + " enteros");
    }
}
