package bloque2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EJERCICIO 08 — Leer Texto con FileReader
 * Teoria: teoria/02_Texto_vs_Binario.md (secciones 1-2)
 *
 * Contexto: El recepcionista del hotel necesita consultar las fichas de
 * los huespedes almacenadas como ficheros de texto. Tu tarea es crear
 * funciones que lean y procesen texto de ficheros.
 */
public class Ej08_LeerTexto {

    /**
     * Lee todo el contenido de un fichero de texto y lo devuelve como String.
     *
     * @param ruta ruta del fichero a leer
     * @return contenido completo como String
     * @throws IOException si hay error de lectura
     */
    public static String leerTodo(String ruta) throws IOException {
        // TODO 1: Crear FileReader con la ruta. Usar StringBuilder.
        //         Leer caracter a caracter con read() hasta -1.
        //         Cast a (char). Cerrar reader. Devolver String.
        return "";
    }

    /**
     * Cuenta el numero de lineas de un fichero de texto.
     * Una linea se define como el numero de '\n' encontrados + 1.
     * Un fichero vacio tiene 1 linea (la vacia).
     *
     * @param ruta ruta del fichero
     * @return numero de lineas
     * @throws IOException si hay error de lectura
     */
    public static int contarLineas(String ruta) throws IOException {
        // TODO 2: Crear FileReader. Contar '\n' en el contenido.
        //         Devolver cuenta + 1. Cerrar reader.
        return 0;
    }

    /**
     * Devuelve la linea numero 'n' del fichero (0-indexed).
     * Si n es mayor que el numero de lineas, devuelve null.
     *
     * @param ruta ruta del fichero
     * @param n    indice de la linea (0-indexed)
     * @return la linea correspondiente o null si no existe
     * @throws IOException              si hay error de lectura
     * @throws IllegalArgumentException si n es negativo
     */
    public static String obtenerLinea(String ruta, int n) throws IOException {
        // TODO 3: Validar n >= 0. Leer todo el contenido con leerTodo.
        //         Dividir por '\n' con split.
        //         Si n < array.length, devolver la linea. Si no, devolver null.
        return null;
    }

    /**
     * Busca una palabra en un fichero y devuelve true si la encuentra.
     * La busqueda es case-insensitive.
     *
     * @param ruta    ruta del fichero
     * @param palabra palabra a buscar
     * @return true si se encuentra
     * @throws IOException si hay error de lectura
     */
    public static boolean contienePalabra(String ruta, String palabra) throws IOException {
        // TODO 4: Leer todo el contenido. Convertir ambos a minusculas.
        //         Usar contains() para comprobar.
        return false;
    }

    /**
     * Cuenta cuantas veces aparece un caracter en el fichero.
     *
     * @param ruta     ruta del fichero
     * @param objetivo caracter a buscar
     * @return numero de apariciones
     * @throws IOException si hay error de lectura
     */
    public static int contarCaracter(String ruta, char objetivo) throws IOException {
        // TODO 5: Crear FileReader. Leer caracter a caracter.
        //         Comparar con objetivo y contar coincidencias.
        //         Cerrar reader. Devolver contador.
        return 0;
    }

    /**
     * Devuelve un resumen del fichero con formato:
     * "Fichero: [nombre] | Lineas: [n] | Caracteres: [c] | Tamano: [t] bytes"
     *
     * @param ruta ruta del fichero
     * @return resumen formateado
     * @throws IOException si hay error de lectura
     */
    public static String resumenFichero(String ruta) throws IOException {
        // TODO 6: Obtener el nombre del fichero con new File(ruta).getName().
        //         Contar lineas y caracteres usando los metodos anteriores.
        //         Obtener tamano con File.length().
        //         Formatear con String.format.
        return "";
    }

    /**
     * Extrae el valor de un campo en una ficha de texto.
     * Busca una linea que empiece con "campo: " y devuelve el resto.
     * Ejemplo: extraerCampo(ruta, "Nombre") busca "Nombre: " y devuelve el valor.
     *
     * @param ruta  ruta del fichero
     * @param campo nombre del campo (sin los dos puntos)
     * @return valor del campo, o null si no se encuentra
     * @throws IOException si hay error de lectura
     */
    public static String extraerCampo(String ruta, String campo) throws IOException {
        // TODO 7: Leer todo el contenido. Dividir por lineas.
        //         Buscar una linea que empiece con "campo: " (case insensitive).
        //         Si la encuentra, devolver el substring despues de ": ".
        //         Si no la encuentra, devolver null.
        return null;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 08: Leer Texto ===\n");

        String dir = "temp/bloque2";
        new File(dir).mkdirs();

        // Crear fichero de prueba
        try (FileWriter fw = new FileWriter(dir + "/ficha_test.txt")) {
            fw.write("============================\n");
            fw.write("FICHA DE HUESPED\n");
            fw.write("============================\n");
            fw.write("Nombre: Juan Lopez\n");
            fw.write("DNI: 87654321B\n");
            fw.write("Habitacion: 205\n");
            fw.write("Noches: 5\n");
            fw.write("============================\n");
        }

        System.out.println("Contenido:");
        System.out.println(leerTodo(dir + "/ficha_test.txt"));

        System.out.println("Lineas: " + contarLineas(dir + "/ficha_test.txt"));
        System.out.println("Linea 3: " + obtenerLinea(dir + "/ficha_test.txt", 3));
        System.out.println("Contiene 'Juan': " + contienePalabra(dir + "/ficha_test.txt", "Juan"));
        System.out.println("Ocurrencias de '=': " + contarCaracter(dir + "/ficha_test.txt", '='));
        System.out.println(resumenFichero(dir + "/ficha_test.txt"));
        System.out.println("Campo Nombre: " + extraerCampo(dir + "/ficha_test.txt", "Nombre"));
        System.out.println("Campo DNI: " + extraerCampo(dir + "/ficha_test.txt", "DNI"));
    }
}
