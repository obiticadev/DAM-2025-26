package bloque4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 20 — Multiples Recursos en un Solo try
 * Teoria: teoria/04_Gestion_Segura_Recursos.md (secciones 3, 6)
 *
 * Contexto: La clinica veterinaria necesita procesar varios ficheros
 * simultaneamente: leer registros de un fichero y escribir informes en otro,
 * todo con la garantia de que ambos se cierran correctamente.
 */
public class Ej20_MultiRecurso {

    /**
     * Lee dos ficheros de texto y escribe en un tercero la union de ambos,
     * con un separador "---" entre ellos.
     *
     * @param ruta1   primer fichero
     * @param ruta2   segundo fichero
     * @param destino fichero destino
     * @return total de lineas escritas (incluyendo el separador)
     * @throws IOException si hay error de I/O
     */
    public static int unirFicheros(String ruta1, String ruta2, String destino) throws IOException {
        // TODO 1: Usar try-with-resources con 3 recursos: 2 BufferedReaders y 1 BufferedWriter.
        //         Leer todas las lineas de ruta1, escribirlas.
        //         Escribir "---" como separador.
        //         Leer todas las lineas de ruta2, escribirlas.
        //         Devolver total de lineas escritas.
        return 0;
    }

    /**
     * Lee un fichero de registros veterinarios y genera un informe con estadisticas.
     * Cada linea del fichero es un registro con formato: "mascota;dueno;motivo"
     * El informe tiene formato:
     * "Total registros: [n]
     * Mascotas unicas: [m]
     * Motivos: [lista de motivos separados por ', ']"
     *
     * @param rutaRegistros ruta del fichero de registros
     * @param rutaInforme   ruta del fichero de informe destino
     * @throws IOException si hay error de I/O
     */
    public static void generarInforme(String rutaRegistros, String rutaInforme) throws IOException {
        // TODO 2: Usar try-with-resources con BufferedReader para leer.
        //         Parsear cada linea con split(";").
        //         Contar registros, coleccionar mascotas unicas y motivos.
        //         Usar otro try-with-resources con BufferedWriter para escribir el informe.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Copia un fichero filtrando lineas que contienen una palabra,
     * y escribe las lineas descartadas en un fichero de rechazados.
     *
     * @param origen     fichero origen
     * @param aceptados  fichero con lineas aceptadas
     * @param rechazados fichero con lineas rechazadas
     * @param filtro     palabra filtro (case insensitive)
     * @return array de 2 elementos: [numAceptadas, numRechazadas]
     * @throws IOException si hay error de I/O
     */
    public static int[] separarLineas(String origen, String aceptados,
                                       String rechazados, String filtro) throws IOException {
        // TODO 3: Usar try-with-resources con 1 BufferedReader y 2 BufferedWriters.
        //         Leer linea a linea. Si contiene filtro -> aceptados, si no -> rechazados.
        //         Devolver array con conteos.
        return new int[]{0, 0};
    }

    /**
     * Lee un fichero y escribe dos versiones: una en mayusculas y otra en minusculas.
     *
     * @param origen   fichero origen
     * @param destMay  fichero destino en mayusculas
     * @param destMin  fichero destino en minusculas
     * @return numero de lineas procesadas
     * @throws IOException si hay error de I/O
     */
    public static int duplicarConTransformacion(String origen, String destMay,
                                                 String destMin) throws IOException {
        // TODO 4: Usar try-with-resources con 1 reader y 2 writers.
        //         Para cada linea, escribir toUpperCase en destMay y toLowerCase en destMin.
        //         Devolver total de lineas.
        return 0;
    }

    /**
     * Genera un log de operaciones usando PrintWriter con try-with-resources.
     * Cada operacion se escribe con formato: "[indice] [operacion]"
     *
     * @param ruta        ruta del fichero
     * @param operaciones array de operaciones
     * @throws IOException si hay error de escritura
     */
    public static void logOperaciones(String ruta, String[] operaciones) throws IOException {
        // TODO 5: Usar try-with-resources con PrintWriter(new BufferedWriter(new FileWriter(ruta))).
        //         Usar printf o println para cada operacion.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Lee un fichero de texto y devuelve las lineas como List de String,
     * usando try-with-resources. Metodo utilitario.
     *
     * @param ruta ruta del fichero
     * @return lista de lineas
     * @throws IOException si hay error de lectura
     */
    public static List<String> leerComoLista(String ruta) throws IOException {
        // TODO 6: Usar try-with-resources con BufferedReader.
        //         Leer linea a linea hasta null. Devolver ArrayList.
        return new ArrayList<>();
    }

    /**
     * Escribe una List de String como lineas en un fichero,
     * usando try-with-resources.
     *
     * @param ruta   ruta del fichero
     * @param lineas lista de lineas
     * @throws IOException si hay error de escritura
     */
    public static void escribirDesdeLista(String ruta, List<String> lineas) throws IOException {
        // TODO 7: Usar try-with-resources con BufferedWriter.
        //         Escribir cada linea separada por newLine().
        throw new UnsupportedOperationException("TODO 7 no implementado");
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 20: Multi-Recurso ===\n");

        String dir = "temp/bloque4";
        new File(dir).mkdirs();

        // Crear ficheros de prueba
        escribirDesdeLista(dir + "/f1.txt", List.of("Luna;Ana;Vacunacion", "Max;Pedro;Revision"));
        escribirDesdeLista(dir + "/f2.txt", List.of("Coco;Maria;Urgencia"));

        int total = unirFicheros(dir + "/f1.txt", dir + "/f2.txt", dir + "/union.txt");
        System.out.println("Lineas unidas: " + total);

        generarInforme(dir + "/f1.txt", dir + "/informe.txt");
        System.out.println("Informe: " + leerComoLista(dir + "/informe.txt"));

        int[] sep = separarLineas(dir + "/union.txt", dir + "/acept.txt",
                dir + "/rech.txt", "vacunacion");
        System.out.println("Aceptadas: " + sep[0] + ", Rechazadas: " + sep[1]);
    }
}
