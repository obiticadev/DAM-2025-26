package bloque3b;

import java.io.*;

/**
 * EJERCICIO 48 — Tamano de Archivo Binario y Analisis de Bytes
 * 📋 ENTRA EN EXAMEN — Tema 05 (tamano de tipos primitivos en binario)
 * Teoria: teoria/03B_ArchivosBinarios.md (seccion 3)
 *
 * Contexto: El equipo de QA necesita verificar que los ficheros binarios
 * tienen el tamano esperado segun los tipos de datos escritos.
 */
public class Ej48_TamanoBinario {

    /**
     * Escribe un int y devuelve el tamano del fichero (deberia ser 4).
     */
    public static long tamanoDeInt(String ruta) throws IOException {
        // TODO 1: Crear DataOutputStream. Escribir writeInt(42). Cerrar.
        //         Devolver new File(ruta).length().
        return 0;
    }

    /**
     * Escribe un double y devuelve el tamano del fichero (deberia ser 8).
     */
    public static long tamanoDeDouble(String ruta) throws IOException {
        // TODO 2: Crear DataOutputStream. Escribir writeDouble(3.14). Cerrar.
        //         Devolver tamano.
        return 0;
    }

    /**
     * Escribe un boolean y devuelve el tamano del fichero (deberia ser 1).
     */
    public static long tamanoDeBoolean(String ruta) throws IOException {
        // TODO 3: Crear DataOutputStream. Escribir writeBoolean(true). Cerrar.
        //         Devolver tamano.
        return 0;
    }

    /**
     * Escribe un String con writeUTF y devuelve el tamano del fichero.
     * Sera 2 + longitud en bytes UTF-8 del texto.
     */
    public static long tamanoDeUTF(String ruta, String texto) throws IOException {
        // TODO 4: Crear DataOutputStream. Escribir writeUTF(texto). Cerrar.
        //         Devolver tamano.
        return 0;
    }

    /**
     * Escribe int + double + boolean en un fichero y devuelve el tamano total.
     * Deberia ser 4 + 8 + 1 = 13.
     */
    public static long tamanoCombinado(String ruta) throws IOException {
        // TODO 5: Crear DataOutputStream. Escribir writeInt, writeDouble, writeBoolean.
        //         Devolver tamano del fichero.
        return 0;
    }

    /**
     * Escribe N enteros y devuelve el tamano (deberia ser N * 4).
     */
    public static long tamanoDeNEnteros(String ruta, int n) throws IOException {
        // TODO 6: Crear DataOutputStream. En un bucle, writeInt(i) N veces.
        //         Devolver tamano.
        return 0;
    }

    /**
     * Calcula cuantos registros de tipo int caben en un fichero dado su tamano.
     */
    public static int registrosQueCaben(String ruta) {
        // TODO 7: Obtener tamano del fichero. Dividir entre Integer.BYTES (4).
        //         Devolver el resultado. Si el fichero no existe, devolver 0.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 48: Tamano Binario ===\n");

        String dir = "temp/bloque3b";
        new File(dir).mkdirs();

        System.out.println("int:     " + tamanoDeInt(dir + "/int.bin") + " bytes");
        System.out.println("double:  " + tamanoDeDouble(dir + "/double.bin") + " bytes");
        System.out.println("boolean: " + tamanoDeBoolean(dir + "/bool.bin") + " bytes");
        System.out.println("UTF \"Hola\": " + tamanoDeUTF(dir + "/utf.bin", "Hola") + " bytes");
        System.out.println("Combinado: " + tamanoCombinado(dir + "/combo.bin") + " bytes");
        System.out.println("10 enteros: " + tamanoDeNEnteros(dir + "/nint.bin", 10) + " bytes");
        System.out.println("Registros que caben: " + registrosQueCaben(dir + "/nint.bin"));
    }
}
