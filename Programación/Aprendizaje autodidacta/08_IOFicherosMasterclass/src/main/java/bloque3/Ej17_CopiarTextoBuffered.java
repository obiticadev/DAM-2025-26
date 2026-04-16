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
 * EJERCICIO 17 — Copia y Transformacion de Texto con Buffered
 * Teoria: teoria/03_Bufferizacion.md (secciones 3-4)
 *
 * Contexto: El restaurante necesita transformar sus menus de texto:
 * poner en mayusculas, numerar lineas, filtrar platos y generar versiones
 * para distintos idiomas. Todo con BufferedReader/BufferedWriter.
 */
public class Ej17_CopiarTextoBuffered {

    /**
     * Copia un fichero de texto convirtiendo cada linea a mayusculas.
     *
     * @param origen  ruta del fichero origen
     * @param destino ruta del fichero destino
     * @return numero de lineas copiadas
     * @throws IOException si hay error de I/O
     */
    public static int copiarEnMayusculas(String origen, String destino) throws IOException {
        // TODO 1: Crear BufferedReader y BufferedWriter.
        //         Leer con readLine(), convertir a mayusculas con toUpperCase().
        //         Escribir con write() y newLine(). Contar lineas.
        //         Cerrar ambos. Devolver total.
        return 0;
    }

    /**
     * Copia un fichero anadiendo numeros de linea.
     * Formato: "%4d | %s" (numero alineado a 4 cifras, 1-indexed).
     *
     * @param origen  ruta del fichero origen
     * @param destino ruta del fichero destino
     * @return numero de lineas
     * @throws IOException si hay error de I/O
     */
    public static int numerarLineas(String origen, String destino) throws IOException {
        // TODO 2: Crear BufferedReader y BufferedWriter.
        //         Leer linea a linea. Escribir con formato "%4d | %s".
        //         Separar con newLine(). Cerrar ambos. Devolver total.
        return 0;
    }

    /**
     * Copia solo las lineas que contienen la palabra filtro (case insensitive).
     *
     * @param origen  ruta del fichero origen
     * @param destino ruta del fichero destino
     * @param filtro  palabra a buscar
     * @return numero de lineas que pasaron el filtro
     * @throws IOException si hay error de I/O
     */
    public static int filtrarLineas(String origen, String destino, String filtro) throws IOException {
        // TODO 3: Crear BufferedReader y BufferedWriter.
        //         Leer linea a linea. Si contiene filtro (case insensitive), escribir.
        //         Cerrar ambos. Devolver total de lineas escritas.
        return 0;
    }

    /**
     * Lee un fichero de texto y reemplaza todas las ocurrencias de una palabra
     * por otra, escribiendo el resultado en un fichero destino.
     *
     * @param origen    ruta del fichero origen
     * @param destino   ruta del fichero destino
     * @param buscar    palabra a buscar
     * @param reemplazo palabra de reemplazo
     * @return numero de reemplazos realizados
     * @throws IOException si hay error de I/O
     */
    public static int reemplazar(String origen, String destino,
                                  String buscar, String reemplazo) throws IOException {
        // TODO 4: Crear BufferedReader y BufferedWriter.
        //         Leer linea a linea. Contar ocurrencias de 'buscar' en la linea.
        //         Usar replace() para reemplazar. Escribir la linea transformada.
        //         Cerrar ambos. Devolver total de reemplazos.
        return 0;
    }

    /**
     * Invierte el orden de las lineas de un fichero.
     *
     * @param origen  ruta del fichero origen
     * @param destino ruta del fichero destino
     * @return numero de lineas
     * @throws IOException si hay error de I/O
     */
    public static int invertirLineas(String origen, String destino) throws IOException {
        // TODO 5: Crear BufferedReader. Leer todas las lineas a un List.
        //         Crear BufferedWriter. Recorrer la lista de atras hacia adelante.
        //         Escribir cada linea con newLine() entre ellas.
        //         Cerrar ambos. Devolver tamano de la lista.
        return 0;
    }

    /**
     * Une dos ficheros de texto en uno solo (concatenacion).
     * El segundo fichero se anade despues del primero, separado por una linea vacia.
     *
     * @param ruta1   primer fichero
     * @param ruta2   segundo fichero
     * @param destino fichero destino
     * @return total de lineas escritas
     * @throws IOException si hay error de I/O
     */
    public static int unirFicheros(String ruta1, String ruta2, String destino) throws IOException {
        // TODO 6: Crear BufferedWriter para destino.
        //         Leer todas las lineas de ruta1, escribirlas.
        //         Escribir una linea vacia como separador.
        //         Leer todas las lineas de ruta2, escribirlas.
        //         Cerrar todo. Devolver total de lineas escritas (incluyendo separador).
        return 0;
    }

    /**
     * Cuenta cuantas palabras tiene un fichero de texto.
     * Una palabra se define como una secuencia separada por espacios.
     * Se usa split("\\s+") para dividir cada linea.
     * Lineas vacias no cuentan palabras.
     *
     * @param ruta ruta del fichero
     * @return numero total de palabras
     * @throws IOException si hay error de lectura
     */
    public static int contarPalabras(String ruta) throws IOException {
        // TODO 7: Crear BufferedReader. Leer linea a linea.
        //         Si la linea no esta vacia, hacer split("\\s+") y sumar length.
        //         Cerrar reader. Devolver total.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 17: Copiar Texto Buffered ===\n");

        String dir = "temp/bloque3";
        new File(dir).mkdirs();

        // Crear menu de prueba
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dir + "/menu.txt"))) {
            bw.write("Paella valenciana - 12.50"); bw.newLine();
            bw.write("Ensalada cesar - 8.00"); bw.newLine();
            bw.write("Agua mineral - 2.00"); bw.newLine();
            bw.write("Flan casero - 4.50"); bw.newLine();
            bw.write("Cafe solo - 1.50");
        }

        copiarEnMayusculas(dir + "/menu.txt", dir + "/menu_upper.txt");
        System.out.println("Menu en mayusculas generado.");

        numerarLineas(dir + "/menu.txt", dir + "/menu_num.txt");
        System.out.println("Menu numerado generado.");

        int filtradas = filtrarLineas(dir + "/menu.txt", dir + "/bebidas.txt", "agua");
        System.out.println("Lineas con 'agua': " + filtradas);

        int reemplazos = reemplazar(dir + "/menu.txt", dir + "/menu_mod.txt",
                "casero", "artesanal");
        System.out.println("Reemplazos: " + reemplazos);

        invertirLineas(dir + "/menu.txt", dir + "/menu_inv.txt");
        System.out.println("Menu invertido generado.");

        System.out.println("Palabras en menu: " + contarPalabras(dir + "/menu.txt"));
    }
}
