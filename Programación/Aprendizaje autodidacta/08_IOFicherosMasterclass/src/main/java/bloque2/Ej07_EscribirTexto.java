package bloque2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EJERCICIO 07 — Escribir Texto con FileWriter
 * Teoria: teoria/02_Texto_vs_Binario.md (secciones 1-2)
 *
 * Contexto: Un hotel necesita generar fichas de registro para sus huespedes.
 * Cada ficha se guarda como fichero de texto plano. Tu tarea es crear las
 * funciones de escritura de texto.
 */
public class Ej07_EscribirTexto {

    /**
     * Escribe un texto en un fichero nuevo. Si el fichero existe, lo sobrescribe.
     *
     * @param ruta  ruta del fichero destino
     * @param texto contenido a escribir
     * @throws IOException si hay error de escritura
     */
    public static void escribir(String ruta, String texto) throws IOException {
        // TODO 1: Crear FileWriter con la ruta.
        //         Escribir el texto con write(String).
        //         Cerrar el writer.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Escribe un array de lineas en un fichero, cada linea terminada en '\n'.
     * La ultima linea tambien termina en '\n'.
     *
     * @param ruta   ruta del fichero destino
     * @param lineas array de lineas
     * @throws IOException si hay error de escritura
     */
    public static void escribirLineasConSalto(String ruta, String[] lineas) throws IOException {
        // TODO 2: Crear FileWriter. Recorrer el array.
        //         Escribir cada linea seguida de '\n'.
        //         Cerrar el writer.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Genera una ficha de huesped formateada y la escribe en un fichero.
     * Formato:
     * ============================
     * FICHA DE HUESPED
     * ============================
     * Nombre: [nombre]
     * DNI: [dni]
     * Habitacion: [habitacion]
     * Noches: [noches]
     * ============================
     *
     * @param ruta       ruta del fichero destino
     * @param nombre     nombre del huesped
     * @param dni        DNI del huesped
     * @param habitacion numero de habitacion
     * @param noches     numero de noches
     * @throws IOException si hay error de escritura
     */
    public static void generarFichaHuesped(String ruta, String nombre, String dni,
                                            int habitacion, int noches) throws IOException {
        // TODO 3: Construir el String de la ficha con StringBuilder.
        //         Usar el formato exacto indicado en el Javadoc.
        //         Escribir el resultado con el metodo escribir.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Escribe texto al final de un fichero existente (modo append).
     *
     * @param ruta  ruta del fichero
     * @param texto texto a anadir
     * @throws IOException si hay error de escritura
     */
    public static void anadirAlFinal(String ruta, String texto) throws IOException {
        // TODO 4: Crear FileWriter en modo append (segundo parametro true).
        //         Escribir el texto. Cerrar el writer.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Genera un fichero con una tabla de precios formateada.
     * Formato de cada linea: "| %-20s | %8.2f EUR |"
     * Con cabecera y separadores:
     * +----------------------+-------------- +
     * | Servicio             |   Precio      |
     * +----------------------+---------------+
     * | [servicio]           |  [precio] EUR |
     * ...
     * +----------------------+---------------+
     *
     * @param ruta      ruta del fichero destino
     * @param servicios array de nombres de servicio
     * @param precios   array de precios (misma longitud que servicios)
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si los arrays tienen distinta longitud
     */
    public static void generarTablaPrecios(String ruta, String[] servicios,
                                            double[] precios) throws IOException {
        // TODO 5: Validar que ambos arrays tienen la misma longitud.
        //         Construir la tabla con StringBuilder usando String.format.
        //         Escribir con el metodo escribir.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Escribe un texto repitiendo cada caracter n veces.
     * Ejemplo: escribirRepetido(ruta, "AB", 3) escribe "AAABBB"
     *
     * @param ruta  ruta del fichero destino
     * @param texto texto original
     * @param veces numero de repeticiones por caracter
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si veces < 1
     */
    public static void escribirRepetido(String ruta, String texto, int veces) throws IOException {
        // TODO 6: Validar veces >= 1. Usar StringBuilder.
        //         Recorrer cada caracter del texto y anadirlo 'veces' veces.
        //         Escribir el resultado en el fichero.
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Devuelve cuantos caracteres se han escrito en un fichero.
     * Usa File.length() y asume 1 byte por caracter (ASCII).
     * Para este ejercicio basico, esa aproximacion es suficiente.
     *
     * @param ruta ruta del fichero
     * @return tamano en bytes (aproximacion de caracteres)
     */
    public static long caracteresEscritos(String ruta) {
        // TODO 7: Crear File con la ruta. Devolver length() si existe, 0 si no.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 07: Escribir Texto ===\n");

        String dir = "temp/bloque2";
        new File(dir).mkdirs();

        escribir(dir + "/simple.txt", "Hola Hotel!");
        System.out.println("Caracteres escritos: " + caracteresEscritos(dir + "/simple.txt"));

        escribirLineasConSalto(dir + "/lineas.txt",
                new String[]{"Check-in: 14:00", "Check-out: 12:00", "WiFi: incluido"});

        generarFichaHuesped(dir + "/ficha.txt", "Ana Garcia", "12345678A", 301, 3);
        System.out.println("Ficha generada: " + caracteresEscritos(dir + "/ficha.txt") + " bytes");

        anadirAlFinal(dir + "/simple.txt", "\nSegunda linea");

        generarTablaPrecios(dir + "/precios.txt",
                new String[]{"Habitacion doble", "Desayuno", "Parking"},
                new double[]{89.50, 12.00, 15.75});

        escribirRepetido(dir + "/repetido.txt", "AB", 3);
        System.out.println("Repetido: " + caracteresEscritos(dir + "/repetido.txt") + " bytes");
    }
}
