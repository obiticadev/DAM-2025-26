package bloque2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EJERCICIO 11 — Corrupcion Cruzada: Mezclar Texto y Binario
 * Teoria: teoria/02_Texto_vs_Binario.md (seccion 4)
 *
 * Contexto: Un becario del hotel copio ficheros de las camaras usando
 * FileReader en vez de FileInputStream. Los ficheros se corrompieron.
 * Tu mision es demostrar POR QUE ocurre y crear herramientas de deteccion.
 */
public class Ej11_CorrupcionCruzada {

    /**
     * Crea un fichero binario de prueba con los bytes proporcionados.
     *
     * @param ruta  ruta del fichero
     * @param datos bytes a escribir
     * @throws IOException si hay error de escritura
     */
    public static void crearFicheroBinario(String ruta, byte[] datos) throws IOException {
        // TODO 1: Crear FileOutputStream. Escribir los datos. Cerrar stream.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Copia un fichero usando FileReader + FileWriter (MAL para binarios).
     * Esto demuestra la corrupcion al tratar binario como texto.
     *
     * @param origen  ruta del fichero origen (binario)
     * @param destino ruta del fichero destino (copia corrupta)
     * @return numero de caracteres "leidos" (no bytes)
     * @throws IOException si hay error de I/O
     */
    public static int copiarConReader(String origen, String destino) throws IOException {
        // TODO 2: Crear FileReader y FileWriter.
        //         Leer caracter a caracter con read().
        //         Escribir cada caracter con write(int).
        //         Contar caracteres. Cerrar ambos. Devolver total.
        return 0;
    }

    /**
     * Copia un fichero usando FileInputStream + FileOutputStream (CORRECTO para binarios).
     *
     * @param origen  ruta del fichero origen
     * @param destino ruta del fichero destino
     * @return numero de bytes copiados
     * @throws IOException si hay error de I/O
     */
    public static int copiarConStream(String origen, String destino) throws IOException {
        // TODO 3: Crear FileInputStream y FileOutputStream.
        //         Leer byte a byte con read().
        //         Escribir cada byte con write(int).
        //         Contar bytes. Cerrar ambos. Devolver total.
        return 0;
    }

    /**
     * Compara dos ficheros byte a byte y devuelve el numero de bytes diferentes.
     *
     * @param ruta1 primer fichero
     * @param ruta2 segundo fichero
     * @return numero de posiciones donde difieren (0 si son iguales)
     * @throws IOException si hay error de lectura
     */
    public static int contarDiferencias(String ruta1, String ruta2) throws IOException {
        // TODO 4: Abrir FileInputStream para ambos ficheros.
        //         Leer byte a byte de cada uno en paralelo.
        //         Contar cuantos pares son diferentes.
        //         Si uno es mas largo, cada byte extra cuenta como diferencia.
        //         Cerrar ambos. Devolver total.
        return 0;
    }

    /**
     * Intenta detectar si un fichero es "probablemente texto" o "probablemente binario".
     * Heuristica: si mas del 10% de los bytes estan fuera del rango imprimible
     * (0-8, 14-31, 127+) excluyendo TAB(9), LF(10), CR(13), es binario.
     *
     * @param ruta ruta del fichero
     * @return "TEXTO" o "BINARIO"
     * @throws IOException si hay error de lectura
     */
    public static String detectarTipoContenido(String ruta) throws IOException {
        // TODO 5: Abrir FileInputStream. Leer todos los bytes.
        //         Contar cuantos bytes estan fuera del rango imprimible
        //         (byte < 9 || (byte > 13 && byte < 32) || byte == 127).
        //         No contar TAB(9), LF(10), CR(13) como no imprimibles.
        //         Si (noImprimibles * 100 / total) > 10, devolver "BINARIO".
        //         Si no, devolver "TEXTO". Cerrar stream.
        return "";
    }

    /**
     * Devuelve un informe comparando el resultado de copiar con Reader vs Stream.
     * Formato:
     * "Original: [tamOrig] bytes
     * Copia Reader: [tamReader] bytes ([difReader] bytes diferentes)
     * Copia Stream: [tamStream] bytes ([difStream] bytes diferentes)
     * Corrupto: [si/no]"
     *
     * @param rutaOriginal  ruta del fichero original
     * @param rutaCopiaReader ruta de la copia hecha con Reader
     * @param rutaCopiaStream ruta de la copia hecha con Stream
     * @return informe formateado
     * @throws IOException si hay error de I/O
     */
    public static String informeCorrupcion(String rutaOriginal, String rutaCopiaReader,
                                            String rutaCopiaStream) throws IOException {
        // TODO 6: Obtener tamano de los 3 ficheros con File.length().
        //         Contar diferencias del original con cada copia.
        //         Determinar si la copia Reader es corrupta (diferencias > 0).
        //         Formatear con StringBuilder.
        return "";
    }

    /**
     * Devuelve true si el fichero podria ser una imagen JPEG
     * (empieza con los bytes FF D8 FF).
     *
     * @param ruta ruta del fichero
     * @return true si los 3 primeros bytes son FF D8 FF
     * @throws IOException si hay error de lectura
     */
    public static boolean esJPEG(String ruta) throws IOException {
        // TODO 7: Abrir FileInputStream. Leer 3 bytes.
        //         Comprobar si son 0xFF, 0xD8, 0xFF.
        //         Cerrar stream. Devolver resultado.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 11: Corrupcion Cruzada ===\n");

        String dir = "temp/bloque2";
        new File(dir).mkdirs();

        // Simular fichero binario tipo JPEG
        byte[] binario = new byte[256];
        binario[0] = (byte) 0xFF;
        binario[1] = (byte) 0xD8;
        binario[2] = (byte) 0xFF;
        for (int i = 3; i < 256; i++) binario[i] = (byte) i;

        crearFicheroBinario(dir + "/original.bin", binario);
        System.out.println("Es JPEG: " + esJPEG(dir + "/original.bin"));
        System.out.println("Tipo: " + detectarTipoContenido(dir + "/original.bin"));

        copiarConReader(dir + "/original.bin", dir + "/copia_reader.bin");
        copiarConStream(dir + "/original.bin", dir + "/copia_stream.bin");

        System.out.println("\n" + informeCorrupcion(
                dir + "/original.bin", dir + "/copia_reader.bin", dir + "/copia_stream.bin"));
    }
}
