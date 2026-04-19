package bloque3c;

import java.io.*;

/**
 * EJERCICIO 52 — Actualizar Registros In-Place con RandomAccessFile
 * 📋 ENTRA EN EXAMEN — Tema 06 (actualizacion in-place, seek+write)
 * Teoria: teoria/03C_AccesoAleatorio.md (seccion 7)
 *
 * Contexto: El sistema de notas permite corregir la nota de un alumno
 * sin reescribir todo el fichero.
 */
public class Ej52_ActualizarRegistro {

    /**
     * Actualiza el entero en la posicion indicada con un nuevo valor.
     */
    public static void actualizar(String ruta, int indice, int nuevoValor) throws IOException {
        // TODO 1: Crear RandomAccessFile "rw". seek(indice * 4). writeInt(nuevoValor).
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee el valor actual, lo duplica y lo escribe de nuevo en la misma posicion.
     * Devuelve el nuevo valor.
     */
    public static int duplicarValor(String ruta, int indice) throws IOException {
        // TODO 2: Abrir "rw". seek(indice * 4). readInt() -> valor.
        //         seek(indice * 4) otra vez (el puntero avanzo 4 bytes).
        //         writeInt(valor * 2). Devolver valor * 2.
        return 0;
    }

    /**
     * Intercambia los valores de dos posiciones.
     */
    public static void intercambiar(String ruta, int indice1, int indice2) throws IOException {
        // TODO 3: Abrir "rw". Leer ambos valores con seek+readInt.
        //         Escribir cada valor en la posicion contraria con seek+writeInt.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Pone todos los registros a cero.
     */
    public static void resetearTodos(String ruta) throws IOException {
        // TODO 4: Abrir "rw". Calcular cantidad = length()/4.
        //         seek(0). Bucle: writeInt(0) para cada registro.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Incrementa el valor de un registro en 1 y devuelve el nuevo valor.
     */
    public static int incrementar(String ruta, int indice) throws IOException {
        // TODO 5: Abrir "rw". seek, read, seek, write(valor+1). Devolver valor+1.
        return 0;
    }

    /**
     * Reemplaza todos los registros que tengan valorViejo por valorNuevo.
     * Devuelve cuantos se reemplazaron.
     */
    public static int reemplazarTodos(String ruta, int valorViejo, int valorNuevo) throws IOException {
        // TODO 6: Abrir "rw". Bucle por todos los registros.
        //         Para cada uno: seek, readInt. Si == valorViejo, seek atras, writeInt(valorNuevo).
        //         Contar reemplazos.
        return 0;
    }

    /**
     * Lee todos los enteros del fichero y los devuelve como array.
     */
    public static int[] leerTodos(String ruta) throws IOException {
        // TODO 7: Abrir "r". Calcular cantidad. Crear array. seek(0). Bucle readInt.
        return new int[0];
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 52: Actualizar Registros ===\n");

        String dir = "temp/bloque3c";
        String ruta = dir + "/enteros.dat";

        // Preparar datos
        Ej50_EscrituraSecuencialRAF.escribirSecuencia(ruta, 10);
        System.out.print("Original: ");
        for (int v : leerTodos(ruta)) System.out.print(v + " ");
        System.out.println();

        actualizar(ruta, 0, 999);
        System.out.print("Tras actualizar posicion 0 a 999: ");
        for (int v : leerTodos(ruta)) System.out.print(v + " ");
        System.out.println();

        intercambiar(ruta, 0, 9);
        System.out.print("Tras intercambiar 0 y 9: ");
        for (int v : leerTodos(ruta)) System.out.print(v + " ");
        System.out.println();
    }
}
