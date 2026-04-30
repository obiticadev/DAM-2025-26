package bloque4;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 64 — Lectura Secuencial Completa: Pipeline de Transformación
 * 📋 ENTRA EN EXAMEN — Pilar 3 (Acceso Secuencial) + Pilar 1 (IO + try-with-resources)
 * Teoria: teoria/04_Gestion_Segura_Recursos.md + teoria/03B_ArchivosBinarios.md
 *
 * Contexto: Un sistema de procesamiento de datos recibe ficheros que deben
 * procesarse COMPLETAMENTE de principio a fin (acceso secuencial). No hay
 * saltos ni acceso aleatorio. Se lee todo, se transforma y se escribe el resultado.
 *
 * Este ejercicio refuerza:
 *   - Lectura secuencial completa (sin seek)
 *   - try-with-resources con múltiples recursos
 *   - Pipeline: leer → transformar → escribir
 *   - Combinación de texto y binario
 */
public class Ej64_LecturaSecuencialCompleta {

    /**
     * Lee un fichero de texto línea a línea, convierte todas las líneas
     * a mayúsculas y las escribe en un fichero de salida.
     * Ambos ficheros se manejan con try-with-resources.
     *
     * @param rutaEntrada ruta del fichero de texto origen
     * @param rutaSalida  ruta del fichero de texto destino
     * @return número de líneas procesadas
     * @throws IOException si hay error
     */
    public static int transformarAMayusculas(String rutaEntrada, String rutaSalida) throws IOException {
        // TODO 1: Abrir BufferedReader + BufferedWriter con try-with-resources (un solo try).
        //         Leer con readLine() en bucle hasta null.
        //         Escribir cada línea convertida a mayúsculas + newLine().
        //         Contar y devolver las líneas procesadas.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee un fichero de texto con números (uno por línea), los filtra
     * quedándose solo con los positivos, y los escribe en un fichero binario
     * usando DataOutputStream.
     *
     * @param rutaTexto   ruta del fichero de texto con números
     * @param rutaBinario ruta del fichero binario destino
     * @return número de valores positivos escritos
     * @throws IOException si hay error
     */
    public static int filtrarPositivosABinario(String rutaTexto, String rutaBinario) throws IOException {
        // TODO 2: Abrir BufferedReader para lectura de texto.
        //         Abrir DataOutputStream para escritura binaria.
        //         Leer cada línea, parsear a double.
        //         Si el valor es > 0, escribirlo con writeDouble().
        //         Manejar NumberFormatException (ignorar líneas inválidas).
        //         Devolver cuántos valores se escribieron.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Lee un fichero binario de doubles (DataInputStream) secuencialmente,
     * calcula la suma total y devuelve el resultado.
     * Usa EOFException para detectar el fin de fichero.
     *
     * @param rutaBinario ruta del fichero binario
     * @return suma de todos los doubles del fichero
     * @throws IOException si hay error
     */
    public static double sumarBinario(String rutaBinario) throws IOException {
        // TODO 3: Abrir DataInputStream con try-with-resources.
        //         Bucle while(true): leer readDouble(), acumular en suma.
        //         Capturar EOFException para terminar. Devolver la suma.
        return 0.0;
    }

    /**
     * Lee un fichero de texto con formato "clave=valor" (una por línea),
     * filtra las claves que empiezan por un prefijo dado, y escribe
     * solo esas líneas en el fichero de salida.
     *
     * @param rutaEntrada ruta del fichero de configuración
     * @param rutaSalida  ruta del fichero filtrado
     * @param prefijo     prefijo de las claves a conservar (ej: "db.")
     * @return número de líneas que pasaron el filtro
     * @throws IOException si hay error
     */
    public static int filtrarPorPrefijo(String rutaEntrada, String rutaSalida, String prefijo)
            throws IOException {
        // TODO 4: Leer con BufferedReader. Escribir con PrintWriter (o BufferedWriter).
        //         Para cada línea: extraer la clave (antes del '=').
        //         Si la clave empieza por el prefijo, escribir la línea completa.
        //         Contar y devolver las líneas escritas.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Lee un fichero de texto con registros CSV (nombre;nota) secuencialmente,
     * calcula la nota media y escribe un resumen en otro fichero.
     * El resumen contiene: total alumnos, nota media, aprobados y suspensos.
     *
     * @param rutaCSV     ruta del fichero CSV de notas
     * @param rutaResumen ruta del fichero de resumen
     * @return la nota media calculada, o 0.0 si no hay datos
     * @throws IOException si hay error
     */
    public static double generarResumenNotas(String rutaCSV, String rutaResumen) throws IOException {
        // TODO 5: Leer el CSV con BufferedReader (saltar cabecera).
        //         Acumular: total, sumaNotas, aprobados (>=5), suspensos (<5).
        //         Escribir con PrintWriter en el fichero de resumen:
        //           "Total alumnos: X"
        //           "Nota media: X.XX"
        //           "Aprobados: X"
        //           "Suspensos: X"
        //         Devolver la nota media.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Copia un fichero binario completo de forma secuencial usando un buffer de bytes.
     * Cuenta y devuelve el total de bytes copiados.
     *
     * @param rutaOrigen  ruta del fichero origen
     * @param rutaDestino ruta del fichero destino
     * @return total de bytes copiados
     * @throws IOException si hay error
     */
    public static long copiarConConteo(String rutaOrigen, String rutaDestino) throws IOException {
        // TODO 6: Abrir FileInputStream + FileOutputStream con try-with-resources.
        //         Usar buffer de 1024 bytes. Leer con read(buffer).
        //         Escribir con write(buffer, 0, bytesLeidos).
        //         Acumular y devolver el total de bytes.
        return 0;
    }

    /**
     * Lee un fichero de texto y devuelve estadísticas: líneas, palabras, caracteres.
     *
     * @param ruta ruta del fichero de texto
     * @return array [líneas, palabras, caracteres]
     * @throws IOException si hay error
     */
    public static int[] estadisticasFichero(String ruta) throws IOException {
        // TODO 7: Leer con BufferedReader línea a línea.
        //         Contar líneas (cada readLine no null).
        //         Contar palabras (split("\\s+") por línea, ignorar líneas vacías).
        //         Contar caracteres (longitud de cada línea + 1 por el salto de línea).
        //         Devolver {lineas, palabras, caracteres}.
        return new int[]{0, 0, 0};
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCIÓN — Pulsa Run aquí
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 64: Lectura Secuencial Completa ===\n");

        String dir = "temp/bloque4";
        new File(dir).mkdirs();

        // Preparar fichero de texto
        try (PrintWriter pw = new PrintWriter(new FileWriter(dir + "/entrada.txt"))) {
            pw.println("Hola Mundo");
            pw.println("Java es genial");
            pw.println("Acceso secuencial");
        }

        int lineas = transformarAMayusculas(dir + "/entrada.txt", dir + "/mayusculas.txt");
        System.out.println("Líneas transformadas: " + lineas);

        // Preparar fichero de números
        try (PrintWriter pw = new PrintWriter(new FileWriter(dir + "/numeros.txt"))) {
            pw.println("3.14");
            pw.println("-2.5");
            pw.println("7.0");
            pw.println("invalido");
            pw.println("-1.0");
            pw.println("10.5");
        }

        int positivos = filtrarPositivosABinario(dir + "/numeros.txt", dir + "/positivos.bin");
        System.out.println("Positivos escritos: " + positivos);
        System.out.printf("Suma binario: %.2f%n", sumarBinario(dir + "/positivos.bin"));

        // Config
        try (PrintWriter pw = new PrintWriter(new FileWriter(dir + "/config.txt"))) {
            pw.println("db.host=localhost");
            pw.println("db.port=3306");
            pw.println("app.name=MiApp");
            pw.println("db.user=admin");
            pw.println("app.version=1.0");
        }
        int filtradas = filtrarPorPrefijo(dir + "/config.txt", dir + "/db_config.txt", "db.");
        System.out.println("Líneas db.*: " + filtradas);

        // Notas
        try (PrintWriter pw = new PrintWriter(new FileWriter(dir + "/notas.csv"))) {
            pw.println("nombre;nota");
            pw.println("Ana;8.5");
            pw.println("Luis;4.0");
            pw.println("María;6.0");
            pw.println("Carlos;3.5");
        }
        double media = generarResumenNotas(dir + "/notas.csv", dir + "/resumen.txt");
        System.out.printf("Nota media: %.2f%n", media);

        int[] stats = estadisticasFichero(dir + "/entrada.txt");
        System.out.printf("Estadísticas: %d líneas, %d palabras, %d caracteres%n",
                stats[0], stats[1], stats[2]);
    }
}
