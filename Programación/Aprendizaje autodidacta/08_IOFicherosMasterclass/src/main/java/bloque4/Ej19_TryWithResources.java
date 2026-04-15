package bloque4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EJERCICIO 19 — try-with-resources Basico
 * Teoria: teoria/04_Gestion_Segura_Recursos.md (secciones 1-3)
 *
 * Contexto: Una clinica veterinaria necesita registrar visitas de mascotas
 * en ficheros de texto. Todos los metodos DEBEN usar try-with-resources
 * para garantizar que los ficheros se cierran correctamente.
 */
public class Ej19_TryWithResources {

    /**
     * Escribe un texto en un fichero usando try-with-resources.
     *
     * @param ruta  ruta del fichero
     * @param texto texto a escribir
     * @throws IOException si hay error de escritura
     */
    public static void escribirSeguro(String ruta, String texto) throws IOException {
        // TODO 1: Usar try-with-resources con BufferedWriter(new FileWriter(ruta)).
        //         Escribir el texto. El cierre es automatico.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee todo el contenido de un fichero usando try-with-resources.
     *
     * @param ruta ruta del fichero
     * @return contenido completo
     * @throws IOException si hay error de lectura
     */
    public static String leerSeguro(String ruta) throws IOException {
        // TODO 2: Usar try-with-resources con BufferedReader(new FileReader(ruta)).
        //         Leer con readLine() y acumular en StringBuilder.
        //         Devolver el resultado.
        return "";
    }

    /**
     * Copia un fichero de texto usando try-with-resources con dos recursos.
     *
     * @param origen  ruta del fichero origen
     * @param destino ruta del fichero destino
     * @return numero de lineas copiadas
     * @throws IOException si hay error de I/O
     */
    public static int copiarSeguro(String origen, String destino) throws IOException {
        // TODO 3: Usar try-with-resources declarando BufferedReader Y BufferedWriter
        //         en el mismo parentesis (separados por ;).
        //         Copiar linea a linea. Devolver total de lineas.
        return 0;
    }

    /**
     * Genera un registro de visita veterinaria y lo escribe en un fichero.
     * Formato:
     * --- VISITA VETERINARIA ---
     * Mascota: [mascota]
     * Dueno: [dueno]
     * Motivo: [motivo]
     * Fecha: [fecha]
     * --------------------------
     *
     * @param ruta    ruta del fichero
     * @param mascota nombre de la mascota
     * @param dueno   nombre del dueno
     * @param motivo  motivo de la visita
     * @param fecha   fecha como String
     * @throws IOException si hay error de escritura
     */
    public static void registrarVisita(String ruta, String mascota, String dueno,
                                        String motivo, String fecha) throws IOException {
        // TODO 4: Usar try-with-resources. Construir el registro con el formato indicado.
        //         Escribir usando BufferedWriter.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Anade una linea al final de un fichero de forma segura.
     *
     * @param ruta  ruta del fichero
     * @param linea linea a anadir
     * @throws IOException si hay error de escritura
     */
    public static void anadirLineaSeguro(String ruta, String linea) throws IOException {
        // TODO 5: Usar try-with-resources con FileWriter en modo append,
        //         envuelto en BufferedWriter.
        //         Anadir newLine() y luego la linea.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Lee un fichero y devuelve solo las lineas que empiezan con un prefijo dado.
     * Usa try-with-resources.
     *
     * @param ruta    ruta del fichero
     * @param prefijo prefijo a buscar (case insensitive)
     * @return String con las lineas filtradas unidas por '\n'
     * @throws IOException si hay error de lectura
     */
    public static String filtrarPorPrefijo(String ruta, String prefijo) throws IOException {
        // TODO 6: Usar try-with-resources con BufferedReader.
        //         Leer linea a linea. Si la linea (en minusculas) empieza con
        //         el prefijo (en minusculas), anadirla al resultado.
        //         Unir con '\n'. Devolver resultado.
        return "";
    }

    /**
     * Cuenta el numero de ficheros que se pueden leer correctamente
     * de un array de rutas. Si un fichero no existe o da error, no cuenta.
     * Usa try-with-resources con catch.
     *
     * @param rutas array de rutas de fichero
     * @return numero de ficheros legibles
     */
    public static int contarLegibles(String[] rutas) {
        // TODO 7: Recorrer las rutas. Para cada una, intentar abrir
        //         un BufferedReader con try-with-resources.
        //         Si se abre sin excepcion, incrementar contador.
        //         Usar catch para ignorar IOExceptions.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 19: try-with-resources ===\n");

        String dir = "temp/bloque4";
        new File(dir).mkdirs();

        escribirSeguro(dir + "/nota.txt", "Primera nota segura");
        System.out.println("Leido: " + leerSeguro(dir + "/nota.txt"));

        int copiadas = copiarSeguro(dir + "/nota.txt", dir + "/copia.txt");
        System.out.println("Lineas copiadas: " + copiadas);

        registrarVisita(dir + "/visita.txt", "Luna", "Ana Garcia",
                "Vacunacion", "2024-03-15");
        System.out.println("Visita registrada.");

        anadirLineaSeguro(dir + "/nota.txt", "Segunda linea anadida");
        System.out.println("Tras anadir: " + leerSeguro(dir + "/nota.txt"));

        System.out.println("Legibles: " + contarLegibles(
                new String[]{dir + "/nota.txt", dir + "/noexiste.txt", dir + "/visita.txt"}));
    }
}
