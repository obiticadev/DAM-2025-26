package bloque3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EJERCICIO 13 — BufferedWriter Basico
 * Teoria: teoria/03_Bufferizacion.md (secciones 1-3)
 *
 * Contexto: Un restaurante necesita generar tickets de pedido en ficheros de texto.
 * Los tickets se generan con alta frecuencia, por lo que la eficiencia de escritura
 * es importante. Usaras BufferedWriter para optimizar la escritura.
 */
public class Ej13_BufferedWriterBasico {

    /**
     * Escribe un array de lineas en un fichero usando BufferedWriter.
     * Cada linea se separa con newLine(). No anade salto tras la ultima.
     *
     * @param ruta   ruta del fichero destino
     * @param lineas array de lineas a escribir
     * @throws IOException si hay error de escritura
     */
    public static void escribirLineas(String ruta, String[] lineas) throws IOException {
        // TODO 1: Crear BufferedWriter envolviendo un FileWriter.
        //         Recorrer el array. Escribir cada linea con write(String).
        //         Usar newLine() ENTRE lineas (no despues de la ultima).
        //         Cerrar el BufferedWriter.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Genera un ticket de restaurante y lo escribe en un fichero.
     * Formato:
     * ================================
     * TICKET #[numero]
     * ================================
     * [plato1] ........... [precio1] EUR
     * [plato2] ........... [precio2] EUR
     * --------------------------------
     * TOTAL:              [total] EUR
     * ================================
     *
     * @param ruta    ruta del fichero destino
     * @param numero  numero del ticket
     * @param platos  array de nombres de platos
     * @param precios array de precios (misma longitud que platos)
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si platos y precios tienen distinta longitud
     */
    public static void generarTicket(String ruta, int numero, String[] platos,
                                      double[] precios) throws IOException {
        // TODO 2: Validar longitudes iguales. Crear BufferedWriter.
        //         Calcular total sumando precios.
        //         Construir el ticket con el formato indicado.
        //         Usar write() y newLine() para cada linea.
        //         Cerrar writer.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Escribe una tabla numerada en un fichero.
     * Formato por linea: "[indice]. [elemento]"
     * Ejemplo: "1. Primer elemento"
     *
     * @param ruta      ruta del fichero
     * @param elementos array de strings
     * @throws IOException si hay error de escritura
     */
    public static void escribirListaNumerada(String ruta, String[] elementos) throws IOException {
        // TODO 3: Crear BufferedWriter. Recorrer el array.
        //         Escribir cada elemento precedido de su numero (1-indexed).
        //         Separar con newLine(). Cerrar writer.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Anade lineas al final de un fichero existente usando BufferedWriter en modo append.
     *
     * @param ruta   ruta del fichero
     * @param lineas array de lineas a anadir
     * @throws IOException si hay error de escritura
     */
    public static void anadirLineas(String ruta, String[] lineas) throws IOException {
        // TODO 4: Crear FileWriter en modo append, envuelto en BufferedWriter.
        //         Escribir cada linea precedida de newLine() (para separar del contenido previo).
        //         Cerrar writer.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Escribe un fichero con el contenido proporcionado, haciendo flush()
     * explicitamente despues de cada linea (para demostrar flush manual).
     *
     * @param ruta   ruta del fichero
     * @param lineas array de lineas
     * @throws IOException si hay error de escritura
     */
    public static void escribirConFlush(String ruta, String[] lineas) throws IOException {
        // TODO 5: Crear BufferedWriter. Por cada linea:
        //         Escribir la linea, anadir newLine(), llamar a flush().
        //         Cerrar writer.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Genera un fichero con N lineas de texto, cada una con formato:
     * "Linea [i]: [texto repetido i veces]"
     * Util para crear ficheros grandes de prueba.
     *
     * @param ruta       ruta del fichero
     * @param numLineas  numero de lineas a generar
     * @param texto      texto base a repetir
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si numLineas < 0
     */
    public static void generarFicheroGrande(String ruta, int numLineas, String texto) throws IOException {
        // TODO 6: Validar numLineas >= 0. Crear BufferedWriter.
        //         Generar cada linea con el formato indicado.
        //         Separar con newLine(). Cerrar writer.
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Devuelve cuantos caracteres tiene un String[] si se unieran con saltos de linea.
     * No cuenta un salto de linea tras el ultimo elemento.
     *
     * @param lineas array de lineas
     * @return total de caracteres (incluidos los '\n' entre lineas)
     */
    public static int calcularCaracteres(String[] lineas) {
        // TODO 7: Sumar la longitud de cada linea.
        //         Sumar (lineas.length - 1) por los saltos entre ellas.
        //         Si el array esta vacio, devolver 0.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 13: BufferedWriter Basico ===\n");

        String dir = "temp/bloque3";
        new File(dir).mkdirs();

        escribirLineas(dir + "/lineas.txt", new String[]{"Hola", "Mundo", "Buffered"});
        System.out.println("Lineas escritas.");

        generarTicket(dir + "/ticket.txt", 42,
                new String[]{"Paella", "Agua", "Flan"},
                new double[]{12.50, 2.00, 4.50});
        System.out.println("Ticket generado.");

        escribirListaNumerada(dir + "/lista.txt",
                new String[]{"Compilar", "Testear", "Desplegar"});

        generarFicheroGrande(dir + "/grande.txt", 100, "X");
        System.out.println("Fichero grande: " + new File(dir + "/grande.txt").length() + " bytes");

        System.out.println("Caracteres calculados: " +
                calcularCaracteres(new String[]{"ABC", "DE", "F"}));
    }
}
