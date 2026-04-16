package bossfinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import bloque5.Producto;

/**
 * BOSS FINAL — Gestion Integral de Inventario
 *
 * Integra TODOS los bloques del bootcamp:
 *   B1: Flujos de datos (leer/escribir bytes)
 *   B2: Texto vs Binario (FileWriter/FileReader vs streams)
 *   B3: Bufferizacion (BufferedReader/Writer)
 *   B4: Gestion segura de recursos (try-with-resources)
 *   B5: Serializacion (ObjectOutputStream/ObjectInputStream)
 *   B6: Procesamiento CSV (split, parseo, validacion)
 *   B7: NIO.2 (Path, Files, walk)
 *
 * Escenario: El supermercado recibe un CSV de inventario de un proveedor,
 * lo importa como objetos serializados, genera informes, hace backups,
 * y organiza todo en un arbol de directorios.
 */
public class Ej43_GestionInventario {

    /**
     * TODO 1 — Importar CSV a lista de Producto.
     * Lee un CSV (nombre;precio;stock con cabecera) y devuelve lista de Producto.
     * Usa BufferedReader + try-with-resources. Valida campos numericos.
     * Lineas invalidas se ignoran.
     *
     * @param rutaCSV ruta del CSV
     * @return lista de Producto importados
     * @throws IOException si hay error
     */
    public static List<Producto> importarCSV(String rutaCSV) throws IOException {
        // TODO 1: BufferedReader, skip cabecera, split(";"), parsear,
        //         crear Producto, anadir a lista. Manejar NumberFormatException.
        return new ArrayList<>();
    }

    /**
     * TODO 2 — Serializar inventario.
     * Guarda la lista de productos como objeto serializado (.dat).
     *
     * @param ruta      ruta del fichero .dat
     * @param productos lista de productos
     * @throws IOException si hay error
     */
    public static void serializarInventario(String ruta, List<Producto> productos)
            throws IOException {
        // TODO 2: ObjectOutputStream con try-with-resources. writeObject(productos).
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * TODO 3 — Deserializar inventario.
     *
     * @param ruta ruta del fichero .dat
     * @return lista de productos
     * @throws IOException si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    @SuppressWarnings("unchecked")
    public static List<Producto> deserializarInventario(String ruta)
            throws IOException, ClassNotFoundException {
        // TODO 3: ObjectInputStream con try-with-resources. readObject() + cast.
        return new ArrayList<>();
    }

    /**
     * TODO 4 — Exportar a CSV.
     * Escribe la lista de productos como CSV con cabecera (nombre;precio;stock).
     *
     * @param ruta      ruta del CSV destino
     * @param productos lista de productos
     * @throws IOException si hay error
     */
    public static void exportarCSV(String ruta, List<Producto> productos) throws IOException {
        // TODO 4: BufferedWriter con try-with-resources. Cabecera + lineas.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * TODO 5 — Hacer backup con NIO.2.
     * Copia un fichero a una carpeta de backups, anadiendo sufijo "_bak".
     *
     * @param rutaOriginal ruta del fichero original
     * @param dirBackup    directorio de backup (se crea si no existe)
     * @return ruta del backup creado
     * @throws IOException si hay error
     */
    public static String hacerBackup(String rutaOriginal, String dirBackup) throws IOException {
        // TODO 5: Files.createDirectories(). Obtener nombre del fichero.
        //         Anadir "_bak" antes de la extension. Files.copy() con REPLACE_EXISTING.
        return "";
    }

    /**
     * TODO 6 — Generar informe de inventario.
     * Formato:
     * "=== INFORME INVENTARIO ===
     * Productos: [n]
     * Valor total: [v]
     * Producto mas caro: [nombre] ([precio])
     * Stock total: [s]"
     *
     * @param productos lista de productos
     * @return informe formateado
     */
    public static String generarInforme(List<Producto> productos) {
        // TODO 6: Calcular totales, buscar el mas caro, formatear con String.format.
        return "";
    }

    /**
     * TODO 7 — Organizar ficheros en arbol.
     * Dado un directorio con ficheros .csv y .dat mezclados,
     * mueve los .csv a un subdirectorio "csv/" y los .dat a "dat/".
     * Usa NIO.2 (Files.list, Files.move, Files.createDirectories).
     *
     * @param dirRaiz directorio raiz
     * @return array [csvMovidos, datMovidos]
     * @throws IOException si hay error
     */
    public static int[] organizarFicheros(String dirRaiz) throws IOException {
        // TODO 7: Files.createDirectories para csv/ y dat/.
        //         Files.list(). Filtrar por extension. Files.move().
        return new int[]{0, 0};
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   BOSS FINAL - Gestion Inventario   ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        Path dir = Paths.get("temp", "bossfinal");
        Files.createDirectories(dir);

        // 1. Crear CSV de prueba
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(dir.resolve("proveedor.csv").toString()))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\n");
            bw.write("Aceite;3.75;20\n");
            bw.write("Sal;0.80;100\n");
            bw.write("Pan;1.50;30\n");
            bw.write("Leche;0.95;80\n");
        }

        // 2. Importar
        List<Producto> inv = importarCSV(dir + "/proveedor.csv");
        System.out.println("Importados: " + inv.size() + " productos");

        // 3. Serializar
        serializarInventario(dir + "/inventario.dat", inv);
        System.out.println("Serializado.");

        // 4. Deserializar
        List<Producto> inv2 = deserializarInventario(dir + "/inventario.dat");
        System.out.println("Deserializado: " + inv2.size());

        // 5. Exportar
        exportarCSV(dir + "/export.csv", inv2);
        System.out.println("Exportado a CSV.");

        // 6. Backup
        String bak = hacerBackup(dir + "/inventario.dat", dir + "/backups");
        System.out.println("Backup en: " + bak);

        // 7. Informe
        System.out.println("\n" + generarInforme(inv));

        // 8. Organizar
        int[] org = organizarFicheros(dir.toString());
        System.out.println("\nOrganizados: " + org[0] + " CSV, " + org[1] + " DAT");
    }
}
