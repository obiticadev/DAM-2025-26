package bloque3b;

import java.io.*;

/**
 * EJERCICIO 46 — Copiar un Archivo Binario con Buffer
 * 📋 ENTRA EN EXAMEN — Tema 05 (FileInputStream + FileOutputStream con buffer)
 * Teoria: teoria/03B_ArchivosBinarios.md (seccion 6)
 *
 * Contexto: El sistema de backups necesita copiar ficheros binarios (imagenes,
 * PDFs) de forma eficiente usando un buffer de bytes.
 */
public class Ej46_CopiarBinarioConBuffer {

    /**
     * Copia un fichero binario byte a byte (sin buffer).
     */
    public static void copiarByteAByte(String origen, String destino) throws IOException {
        // TODO 1: Crear FileInputStream y FileOutputStream con try-with-resources.
        //         Leer con read() y escribir con write(int) hasta que read() devuelva -1.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Copia un fichero binario usando un buffer de tamano fijo.
     *
     * @param origen      ruta del fichero original
     * @param destino     ruta del fichero destino
     * @param tamBuffer   tamano del buffer en bytes
     */
    public static void copiarConBuffer(String origen, String destino, int tamBuffer) throws IOException {
        // TODO 2: Crear FileInputStream y FileOutputStream.
        //         Crear byte[] buffer del tamano indicado.
        //         Leer con read(buffer) y escribir con write(buffer, 0, bytesLeidos)
        //         hasta que read devuelva -1.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Copia un fichero y devuelve el numero de iteraciones del bucle de copia.
     *
     * @param origen    ruta origen
     * @param destino   ruta destino
     * @param tamBuffer tamano del buffer
     * @return numero de veces que se llamo a read(buffer)
     */
    public static int copiarContandoIteraciones(String origen, String destino, int tamBuffer) throws IOException {
        // TODO 3: Igual que TODO 2, pero ademas contar cuantas veces se ejecuta
        //         read(buffer) con resultado != -1. Devolver ese contador.
        return 0;
    }

    /**
     * Compara si dos ficheros son identicos byte a byte.
     */
    public static boolean sonIdenticos(String ruta1, String ruta2) throws IOException {
        // TODO 4: Abrir ambos ficheros con FileInputStream.
        //         Leer byte a byte de ambos y comparar. Si alguno difiere, false.
        //         Si ambos llegan a -1 a la vez, true.
        return false;
    }

    /**
     * Devuelve el tamano en bytes de un fichero.
     */
    public static long tamano(String ruta) {
        // TODO 5: Crear File. Si existe, devolver length(). Si no, -1.
        return -1;
    }

    /**
     * Copia usando BufferedInputStream y BufferedOutputStream (decoradores).
     */
    public static void copiarConBuffered(String origen, String destino) throws IOException {
        // TODO 6: Crear BufferedInputStream(new FileInputStream(origen)) y
        //         BufferedOutputStream(new FileOutputStream(destino)).
        //         Leer con read() y escribir con write(int) byte a byte.
        //         El buffer interno de Buffered hace que sea eficiente.
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Copia un fichero y devuelve true si el destino tiene el mismo tamano que el origen.
     */
    public static boolean copiarYVerificar(String origen, String destino, int tamBuffer) throws IOException {
        // TODO 7: Copiar con copiarConBuffer. Luego comparar tamanos de ambos ficheros.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 46: Copiar Binario con Buffer ===\n");

        String dir = "temp/bloque3b";
        new File(dir).mkdirs();

        // Crear fichero de prueba
        Ej44_EscribirDatosBinarios.escribirMultiplesRegistros(dir + "/origen.bin", 100);

        copiarConBuffer(dir + "/origen.bin", dir + "/copia.bin", 1024);
        System.out.println("Copia: " + tamano(dir + "/copia.bin") + " bytes");
        System.out.println("Identicos: " + sonIdenticos(dir + "/origen.bin", dir + "/copia.bin"));
        System.out.println("Iteraciones (1KB): " + copiarContandoIteraciones(dir + "/origen.bin", dir + "/copia2.bin", 1024));
    }
}
