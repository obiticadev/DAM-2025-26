package bloque3c;

import java.io.*;

/**
 * EJERCICIO 54 — Leer el Ultimo Registro con length()
 * 📋 ENTRA EN EXAMEN — Tema 06 (length(), seek al final)
 * Teoria: teoria/03C_AccesoAleatorio.md (secciones 4-5)
 *
 * Contexto: Un logger binario anade registros al final del fichero.
 * Necesitas leer solo el ultimo registro sin recorrer todo.
 */
public class Ej54_UltimoRegistro {

    /**
     * Lee el ultimo entero de un fichero de enteros.
     */
    public static int ultimoEntero(String ruta) throws IOException {
        // TODO 1: Abrir "r". seek(length() - Integer.BYTES). readInt().
        return 0;
    }

    /**
     * Lee el ultimo double de un fichero de doubles.
     */
    public static double ultimoDouble(String ruta) throws IOException {
        // TODO 2: Abrir "r". seek(length() - Double.BYTES). readDouble().
        return 0.0;
    }

    /**
     * Anade un entero al final del fichero.
     */
    public static void anadirEntero(String ruta, int valor) throws IOException {
        // TODO 3: Abrir "rw". seek(length()) para ir al final. writeInt(valor).
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Elimina el ultimo entero del fichero reduciendolo.
     * Devuelve el valor eliminado.
     */
    public static int eliminarUltimo(String ruta) throws IOException {
        // TODO 4: Abrir "rw". seek(length()-4). readInt() -> valor.
        //         setLength(length()-4) para recortar. Devolver valor.
        return 0;
    }

    /**
     * Devuelve el penultimo entero del fichero.
     */
    public static int penultimoEntero(String ruta) throws IOException {
        // TODO 5: Abrir "r". seek(length() - 2*Integer.BYTES). readInt().
        return 0;
    }

    /**
     * Lee los ultimos N enteros y los devuelve en orden.
     */
    public static int[] ultimosN(String ruta, int n) throws IOException {
        // TODO 6: Abrir "r". Calcular posicion inicio = length() - n*4.
        //         seek(inicio). Leer N enteros en un array.
        return new int[0];
    }

    /**
     * Devuelve true si el fichero tiene al menos un registro de tipo int.
     */
    public static boolean tieneRegistros(String ruta) throws IOException {
        // TODO 7: Abrir "r". Devolver length() >= Integer.BYTES.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 54: Ultimo Registro ===\n");

        String dir = "temp/bloque3c";
        String ruta = dir + "/enteros.dat";

        System.out.println("Ultimo: " + ultimoEntero(ruta));
        anadirEntero(ruta, 12345);
        System.out.println("Tras anadir 12345, ultimo: " + ultimoEntero(ruta));
        int eliminado = eliminarUltimo(ruta);
        System.out.println("Eliminado: " + eliminado);
        System.out.println("Ultimo ahora: " + ultimoEntero(ruta));
    }
}
