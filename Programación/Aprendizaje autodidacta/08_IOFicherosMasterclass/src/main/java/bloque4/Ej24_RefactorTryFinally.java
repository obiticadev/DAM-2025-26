package bloque4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 24 — Refactorizar try-finally a try-with-resources
 * Teoria: teoria/04_Gestion_Segura_Recursos.md (secciones 2-3)
 *
 * Contexto: La clinica tiene codigo legacy con try-finally que necesita
 * modernizarse. Tu tarea es implementar los metodos usando el patron
 * moderno de try-with-resources, mas limpio y seguro.
 * (Los comentarios muestran el codigo legacy que debes refactorizar.)
 */
public class Ej24_RefactorTryFinally {

    /**
     * Lee un fichero completo. Equivalente moderno de:
     * BufferedReader br = null;
     * try { br = ...; ... } finally { if (br != null) br.close(); }
     *
     * @param ruta ruta del fichero
     * @return contenido completo
     * @throws IOException si hay error
     */
    public static String leerModerno(String ruta) throws IOException {
        // TODO 1: Implementar con try-with-resources.
        //         Leer linea a linea con BufferedReader.
        //         Unir con '\n'. Devolver resultado.
        return "";
    }

    /**
     * Escribe un array de lineas. Equivalente moderno del try-finally clasico.
     *
     * @param ruta   ruta del fichero
     * @param lineas array de lineas
     * @throws IOException si hay error
     */
    public static void escribirModerno(String ruta, String[] lineas) throws IOException {
        // TODO 2: Implementar con try-with-resources con BufferedWriter.
        //         Escribir cada linea separada por newLine().
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Copia fichero con dos recursos. Equivalente moderno del doble try-finally.
     *
     * @param origen  fichero origen
     * @param destino fichero destino
     * @return numero de lineas copiadas
     * @throws IOException si hay error
     */
    public static int copiarModerno(String origen, String destino) throws IOException {
        // TODO 3: Implementar con try-with-resources declarando ambos recursos.
        //         Copiar linea a linea. Devolver total.
        return 0;
    }

    /**
     * Genera un fichero con la tabla ASCII imprimible (caracteres 32-126).
     * Formato por linea: "%3d -> '%c'" (codigo -> caracter)
     *
     * @param ruta ruta del fichero
     * @throws IOException si hay error
     */
    public static void tablaASCII(String ruta) throws IOException {
        // TODO 4: Usar try-with-resources con BufferedWriter.
        //         Bucle de 32 a 126. Formatear cada linea.
        //         Separar con newLine().
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Busca una palabra en un fichero y devuelve el numero de linea (1-indexed)
     * donde aparece por primera vez, o -1 si no se encuentra.
     *
     * @param ruta    ruta del fichero
     * @param palabra palabra a buscar (case insensitive)
     * @return numero de linea o -1
     * @throws IOException si hay error
     */
    public static int buscarLinea(String ruta, String palabra) throws IOException {
        // TODO 5: Usar try-with-resources con BufferedReader.
        //         Leer linea a linea con contador (1-indexed).
        //         Si la linea (toLowerCase) contiene la palabra (toLowerCase),
        //         devolver el numero de linea.
        //         Si no se encuentra, devolver -1.
        return -1;
    }

    /**
     * Lee las ultimas N lineas de un fichero. Si tiene menos de N, devuelve todas.
     *
     * @param ruta ruta del fichero
     * @param n    numero de lineas
     * @return lista con las ultimas N lineas
     * @throws IOException              si hay error
     * @throws IllegalArgumentException si n < 0
     */
    public static List<String> ultimasLineas(String ruta, int n) throws IOException {
        // TODO 6: Validar n >= 0. Leer todas las lineas con try-with-resources.
        //         Si hay menos de N, devolver todas.
        //         Si hay N o mas, devolver subList de las ultimas N.
        return new ArrayList<>();
    }

    /**
     * Indica si el patron try-with-resources requiere que el recurso
     * implemente AutoCloseable.
     *
     * @return siempre true (respuesta conceptual)
     */
    public static boolean requiereAutoCloseable() {
        // TODO 7: Devolver true. Este TODO es conceptual:
        //         Java requiere AutoCloseable para try-with-resources.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 24: Refactor try-finally ===\n");

        String dir = "temp/bloque4";
        new File(dir).mkdirs();

        escribirModerno(dir + "/moderno.txt",
                new String[]{"Linea uno", "Linea dos", "Linea tres"});
        System.out.println("Leido: " + leerModerno(dir + "/moderno.txt"));

        int copiadas = copiarModerno(dir + "/moderno.txt", dir + "/copia_mod.txt");
        System.out.println("Copiadas: " + copiadas);

        tablaASCII(dir + "/ascii.txt");
        System.out.println("Tabla ASCII generada.");

        int linea = buscarLinea(dir + "/moderno.txt", "dos");
        System.out.println("'dos' en linea: " + linea);

        List<String> ultimas = ultimasLineas(dir + "/moderno.txt", 2);
        System.out.println("Ultimas 2: " + ultimas);

        System.out.println("Requiere AutoCloseable: " + requiereAutoCloseable());
    }
}
