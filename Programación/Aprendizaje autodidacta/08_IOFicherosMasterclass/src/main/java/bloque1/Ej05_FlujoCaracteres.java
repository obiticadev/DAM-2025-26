package bloque1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EJERCICIO 05 — Flujo de Caracteres Basico
 * Teoria: teoria/01_Flujos_De_Datos.md (seccion 6)
 *
 * Contexto: El almacen necesita generar informes de texto legible para los
 * operarios. Usaras FileWriter y FileReader para crear y leer ficheros
 * de texto, entendiendo la diferencia con los flujos de bytes.
 */
public class Ej05_FlujoCaracteres {

    /**
     * Escribe un array de Strings en un fichero de texto, cada uno en una linea.
     * No anade salto de linea tras la ultima linea.
     *
     * @param ruta   ruta del fichero destino
     * @param lineas array de lineas a escribir
     * @throws IOException si hay error de escritura
     */
    public static void escribirLineas(String ruta, String[] lineas) throws IOException {
        // TODO 1: Crear FileWriter. Recorrer el array.
        //         Escribir cada linea con write(String).
        //         Anadir "\n" ENTRE lineas (no despues de la ultima).
        //         Cerrar el writer.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee todo el contenido de un fichero de texto y lo devuelve como String.
     *
     * @param ruta ruta del fichero a leer
     * @return contenido completo del fichero
     * @throws IOException si hay error de lectura
     */
    public static String leerTodo(String ruta) throws IOException {
        // TODO 2: Crear FileReader. Usar StringBuilder.
        //         Leer caracter a caracter con read() hasta que devuelva -1.
        //         Hacer cast a (char) antes de anadir al StringBuilder.
        //         Cerrar reader. Devolver el String.
        return "";
    }

    /**
     * Cuenta el numero de caracteres de un fichero de texto (excluyendo EOF).
     *
     * @param ruta ruta del fichero
     * @return numero total de caracteres
     * @throws IOException si hay error de lectura
     */
    public static int contarCaracteres(String ruta) throws IOException {
        // TODO 3: Crear FileReader. Leer caracter a caracter.
        //         Incrementar contador por cada caracter leido.
        //         Cerrar reader. Devolver el contador.
        return 0;
    }

    /**
     * Cuenta cuantas veces aparece un caracter concreto en un fichero de texto.
     *
     * @param ruta    ruta del fichero
     * @param objetivo caracter a buscar
     * @return numero de apariciones
     * @throws IOException si hay error de lectura
     */
    public static int contarCaracter(String ruta, char objetivo) throws IOException {
        // TODO 4: Crear FileReader. Leer caracter a caracter.
        //         Comparar cada caracter leido (tras cast) con el objetivo.
        //         Cerrar reader. Devolver el contador.
        return 0;
    }

    /**
     * Anade un texto al final de un fichero de texto existente (modo append).
     * Si el fichero no existe, lo crea.
     *
     * @param ruta  ruta del fichero
     * @param texto texto a anadir
     * @throws IOException si hay error de escritura
     */
    public static void anadirTexto(String ruta, String texto) throws IOException {
        // TODO 5: Crear FileWriter en modo APPEND (segundo parametro true).
        //         Escribir el texto. Cerrar writer.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Copia un fichero de texto caracter a caracter.
     *
     * @param origen  ruta del fichero origen
     * @param destino ruta del fichero destino
     * @return numero de caracteres copiados
     * @throws IOException si hay error de I/O
     */
    public static int copiarTexto(String origen, String destino) throws IOException {
        // TODO 6: Abrir FileReader (origen) y FileWriter (destino).
        //         Leer caracter a caracter del reader.
        //         Escribir cada caracter en el writer.
        //         Contar caracteres. Cerrar ambos. Devolver total.
        return 0;
    }

    /**
     * Devuelve un String informativo con las estadisticas del fichero:
     * "Caracteres: X | Lineas: Y | Tamano fichero: Z bytes"
     * (Las lineas se cuentan como numero de '\n' + 1).
     *
     * @param ruta ruta del fichero
     * @return resumen de estadisticas
     * @throws IOException si hay error de lectura
     */
    public static String estadisticas(String ruta) throws IOException {
        // TODO 7: Usar contarCaracteres para el total de caracteres.
        //         Usar contarCaracter(ruta, '\n') para contar saltos y sumar 1.
        //         Obtener tamano con new File(ruta).length().
        //         Formatear con String.format.
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 05: Flujo de Caracteres ===\n");

        String dir = "temp/bloque1";
        new File(dir).mkdirs();

        String[] lineas = {"Primera linea", "Segunda linea", "Tercera linea"};
        escribirLineas(dir + "/informe.txt", lineas);

        System.out.println("Contenido del fichero:");
        System.out.println(leerTodo(dir + "/informe.txt"));

        System.out.println("\nCaracteres: " + contarCaracteres(dir + "/informe.txt"));
        System.out.println("Ocurrencias de 'a': " + contarCaracter(dir + "/informe.txt", 'a'));

        anadirTexto(dir + "/informe.txt", "\nCuarta linea");
        System.out.println("\nTras anadir linea:");
        System.out.println(leerTodo(dir + "/informe.txt"));

        copiarTexto(dir + "/informe.txt", dir + "/informe_copia.txt");
        System.out.println("\n" + estadisticas(dir + "/informe.txt"));
    }
}
