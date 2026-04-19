package bloque3c;

import java.io.*;

/**
 * EJERCICIO 51 — Lectura con Seek en RandomAccessFile
 * 📋 ENTRA EN EXAMEN — Tema 06 (seek(), calculo de posiciones)
 * Teoria: teoria/03C_AccesoAleatorio.md (secciones 3, 5)
 *
 * Contexto: El sistema de inventario necesita acceder directamente a
 * registros especificos sin leer todo el fichero.
 */
public class Ej51_LecturaConSeek {

    /**
     * Lee el entero en la posicion n-esima (0-based) usando seek.
     */
    public static int leerEnteroConSeek(String ruta, int indice) throws IOException {
        // TODO 1: Crear RandomAccessFile "r". seek(indice * 4). readInt().
        return 0;
    }

    /**
     * Lee un double en la posicion n-esima de un fichero de doubles.
     */
    public static double leerDoubleConSeek(String ruta, int indice) throws IOException {
        // TODO 2: Crear RandomAccessFile "r". seek(indice * Double.BYTES). readDouble().
        return 0.0;
    }

    /**
     * Lee el entero del medio del fichero (si hay 10 registros, lee el 5to).
     */
    public static int leerDelMedio(String ruta) throws IOException {
        // TODO 3: Abrir "r". Calcular cantidad = length()/4.
        //         Calcular indice medio = cantidad / 2.
        //         seek(indice * 4). readInt().
        return 0;
    }

    /**
     * Lee enteros en posiciones pares (0, 2, 4, ...) y los devuelve como array.
     */
    public static int[] leerPosicionesPares(String ruta) throws IOException {
        // TODO 4: Abrir "r". Calcular cantidad = length()/4.
        //         Crear array de tamano (cantidad+1)/2.
        //         Bucle: para i = 0, 2, 4, ... seek(i*4), readInt().
        return new int[0];
    }

    /**
     * Lee el primer y el ultimo entero y devuelve su suma.
     */
    public static int sumaPrimeroYUltimo(String ruta) throws IOException {
        // TODO 5: Abrir "r". seek(0), readInt() -> primero.
        //         seek(length()-4), readInt() -> ultimo.
        //         Devolver primero + ultimo.
        return 0;
    }

    /**
     * Busca un valor en el fichero de enteros. Devuelve su indice o -1 si no existe.
     */
    public static int buscarValor(String ruta, int valorBuscado) throws IOException {
        // TODO 6: Abrir "r". Calcular cantidad. Bucle: seek(i*4), readInt().
        //         Si el valor leido == valorBuscado, devolver i.
        //         Si termina sin encontrar, devolver -1.
        return -1;
    }

    /**
     * Devuelve la posicion en bytes donde empieza el registro n-esimo.
     */
    public static long calcularPosicion(int indice, int bytesRegistro) {
        // TODO 7: Devolver (long) indice * bytesRegistro.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 51: Lectura con Seek ===\n");

        String dir = "temp/bloque3c";
        String ruta = dir + "/enteros.dat";

        System.out.println("Posicion 0: " + leerEnteroConSeek(ruta, 0));
        System.out.println("Posicion 5: " + leerEnteroConSeek(ruta, 5));
        System.out.println("Del medio: " + leerDelMedio(ruta));
        System.out.println("Suma primero+ultimo: " + sumaPrimeroYUltimo(ruta));
        System.out.println("Buscar 40: indice " + buscarValor(ruta, 40));
        System.out.println("Buscar 999: indice " + buscarValor(ruta, 999));
    }
}
