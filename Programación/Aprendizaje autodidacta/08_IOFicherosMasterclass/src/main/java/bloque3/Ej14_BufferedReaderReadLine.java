package bloque3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 14 — BufferedReader y readLine()
 * Teoria: teoria/03_Bufferizacion.md (seccion 4)
 *
 * Contexto: El restaurante recibe pedidos en ficheros de texto (uno por linea).
 * Necesitas leer esos ficheros linea a linea para procesarlos eficientemente.
 */
public class Ej14_BufferedReaderReadLine {

    /**
     * Lee todas las lineas de un fichero y las devuelve como List de String.
     *
     * @param ruta ruta del fichero
     * @return lista con todas las lineas
     * @throws IOException si hay error de lectura
     */
    public static List<String> leerTodasLineas(String ruta) throws IOException {
        // TODO 1: Crear BufferedReader envolviendo FileReader.
        //         Leer con readLine() en bucle hasta null.
        //         Anadir cada linea a un ArrayList.
        //         Cerrar reader. Devolver la lista.
        return new ArrayList<>();
    }

    /**
     * Cuenta el numero de lineas de un fichero usando BufferedReader.
     *
     * @param ruta ruta del fichero
     * @return numero de lineas
     * @throws IOException si hay error de lectura
     */
    public static int contarLineas(String ruta) throws IOException {
        // TODO 2: Crear BufferedReader. Leer con readLine() hasta null.
        //         Incrementar contador por cada linea leida.
        //         Cerrar reader. Devolver contador.
        return 0;
    }

    /**
     * Lee un fichero y devuelve solo las lineas que no estan vacias
     * (despues de hacer trim()).
     *
     * @param ruta ruta del fichero
     * @return lista de lineas no vacias
     * @throws IOException si hay error de lectura
     */
    public static List<String> leerLineasNoVacias(String ruta) throws IOException {
        // TODO 3: Crear BufferedReader. Leer linea a linea.
        //         Si linea.trim() no es vacio, anadir a la lista.
        //         Cerrar reader. Devolver lista.
        return new ArrayList<>();
    }

    /**
     * Busca lineas que contengan una palabra clave (case insensitive)
     * y devuelve una lista con formato: "[numLinea]: [contenido]" (1-indexed).
     *
     * @param ruta    ruta del fichero
     * @param palabra palabra a buscar
     * @return lista de coincidencias formateadas
     * @throws IOException si hay error de lectura
     */
    public static List<String> buscarPalabra(String ruta, String palabra) throws IOException {
        // TODO 4: Crear BufferedReader. Llevar contador de linea (1-indexed).
        //         Por cada linea, comprobar si contiene la palabra (case insensitive).
        //         Si coincide, anadir "[numLinea]: [linea]" a la lista.
        //         Cerrar reader. Devolver lista.
        return new ArrayList<>();
    }

    /**
     * Lee un fichero y devuelve la linea mas larga.
     * Si hay empate, devuelve la primera encontrada.
     * Si el fichero esta vacio, devuelve null.
     *
     * @param ruta ruta del fichero
     * @return la linea mas larga o null
     * @throws IOException si hay error de lectura
     */
    public static String lineaMasLarga(String ruta) throws IOException {
        // TODO 5: Crear BufferedReader. Llevar referencia a la linea mas larga.
        //         Comparar length() de cada linea con la mas larga actual.
        //         Cerrar reader. Devolver resultado.
        return null;
    }

    /**
     * Lee un fichero de texto y genera un resumen de estadisticas:
     * "Lineas: [n] | Vacias: [v] | No vacias: [nv] | Mas larga: [len] chars"
     *
     * @param ruta ruta del fichero
     * @return resumen formateado
     * @throws IOException si hay error de lectura
     */
    public static String resumen(String ruta) throws IOException {
        // TODO 6: Crear BufferedReader. Contar lineas totales, vacias y no vacias.
        //         Encontrar la longitud de la linea mas larga.
        //         Cerrar reader. Formatear con String.format.
        return "";
    }

    /**
     * Devuelve las primeras N lineas de un fichero.
     * Si el fichero tiene menos de N lineas, devuelve todas las que haya.
     *
     * @param ruta ruta del fichero
     * @param n    numero maximo de lineas
     * @return lista con hasta N lineas
     * @throws IOException              si hay error de lectura
     * @throws IllegalArgumentException si n < 0
     */
    public static List<String> primerasLineas(String ruta, int n) throws IOException {
        // TODO 7: Validar n >= 0. Crear BufferedReader.
        //         Leer hasta N lineas o hasta null.
        //         Cerrar reader. Devolver lista.
        return new ArrayList<>();
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 14: BufferedReader y readLine() ===\n");

        String dir = "temp/bloque3";
        new File(dir).mkdirs();

        // Crear fichero de prueba
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dir + "/pedidos.txt"))) {
            bw.write("Paella valenciana"); bw.newLine();
            bw.write(""); bw.newLine();
            bw.write("Agua mineral"); bw.newLine();
            bw.write("Flan casero"); bw.newLine();
            bw.write(""); bw.newLine();
            bw.write("Cafe con leche");
        }

        System.out.println("Todas: " + leerTodasLineas(dir + "/pedidos.txt"));
        System.out.println("Total lineas: " + contarLineas(dir + "/pedidos.txt"));
        System.out.println("No vacias: " + leerLineasNoVacias(dir + "/pedidos.txt"));
        System.out.println("Buscar 'cafe': " + buscarPalabra(dir + "/pedidos.txt", "cafe"));
        System.out.println("Mas larga: " + lineaMasLarga(dir + "/pedidos.txt"));
        System.out.println(resumen(dir + "/pedidos.txt"));
        System.out.println("Primeras 3: " + primerasLineas(dir + "/pedidos.txt", 3));
    }
}
