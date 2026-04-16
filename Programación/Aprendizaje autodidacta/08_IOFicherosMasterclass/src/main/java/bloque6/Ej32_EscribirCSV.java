package bloque6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * EJERCICIO 32 — Escribir un Fichero CSV
 * Teoria: teoria/06_Procesamiento_CSV.md (seccion 3)
 *
 * Contexto: El sistema central del supermercado necesita generar ficheros
 * CSV para exportar inventarios, informes de ventas y listas de precios.
 */
public class Ej32_EscribirCSV {

    /**
     * Escribe un CSV con cabecera y datos de productos.
     * Formato: "nombre;precio;stock"
     *
     * @param ruta     ruta del fichero
     * @param nombres  array de nombres
     * @param precios  array de precios
     * @param stocks   array de stocks
     * @throws IOException              si hay error
     * @throws IllegalArgumentException si los arrays tienen distinto tamano
     */
    public static void escribirProductos(String ruta, String[] nombres,
                                          double[] precios, int[] stocks) throws IOException {
        // TODO 1: Validar que los 3 arrays tienen el mismo tamano.
        //         Escribir cabecera "nombre;precio;stock".
        //         Escribir cada registro con BufferedWriter y try-with-resources.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Genera un CSV a partir de una lista de String[] (cada String[] es un registro).
     *
     * @param ruta        ruta del fichero
     * @param cabecera    array con los nombres de las columnas
     * @param registros   lista de registros (cada uno es un String[])
     * @param delimitador delimitador a usar
     * @throws IOException si hay error
     */
    public static void escribirGenerico(String ruta, String[] cabecera,
                                         List<String[]> registros, String delimitador)
            throws IOException {
        // TODO 2: Escribir cabecera unida por delimitador.
        //         Para cada registro, unir sus campos con el delimitador.
        //         Usar BufferedWriter con try-with-resources.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Anade un registro al final de un CSV existente.
     *
     * @param ruta     ruta del fichero CSV existente
     * @param registro linea CSV a anadir (ya formateada)
     * @throws IOException si hay error
     */
    public static void anadirRegistro(String ruta, String registro) throws IOException {
        // TODO 3: Abrir FileWriter en modo append con BufferedWriter.
        //         Escribir newLine() + registro.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Genera un CSV con una tabla de multiplicar (filas x columnas).
     * Cabecera: ";1;2;3;...;n"
     * Fila i:   "i;i*1;i*2;...;i*n"
     *
     * @param ruta ruta del fichero
     * @param n    dimension de la tabla (1..n)
     * @throws IOException si hay error
     */
    public static void tablaMultiplicarCSV(String ruta, int n) throws IOException {
        // TODO 4: Generar cabecera con ";" como separador.
        //         Generar cada fila. Escribir con BufferedWriter.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Convierte un delimitador a otro en un fichero CSV.
     * Lee con el delimitador antiguo y escribe con el nuevo.
     *
     * @param rutaEntrada   fichero CSV de entrada
     * @param rutaSalida    fichero CSV de salida
     * @param delimViejo    delimitador original
     * @param delimNuevo    delimitador nuevo
     * @return numero de lineas convertidas (incluyendo cabecera)
     * @throws IOException si hay error
     */
    public static int convertirDelimitador(String rutaEntrada, String rutaSalida,
                                            String delimViejo, String delimNuevo)
            throws IOException {
        // TODO 5: Leer cada linea del fichero de entrada.
        //         Reemplazar delimViejo por delimNuevo.
        //         Escribir en el fichero de salida.
        //         Devolver total de lineas.
        return 0;
    }

    /**
     * Genera un CSV de resumen con totales.
     * Formato: "metrica;valor"
     * Metricas: "Total productos", "Precio medio", "Stock total"
     *
     * @param ruta         ruta del fichero destino
     * @param totalProductos total de productos
     * @param precioMedio    precio medio
     * @param stockTotal     stock total
     * @throws IOException si hay error
     */
    public static void generarResumen(String ruta, int totalProductos,
                                       double precioMedio, int stockTotal) throws IOException {
        // TODO 6: Escribir cabecera "metrica;valor".
        //         Escribir las 3 lineas con los valores formateados.
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Une los campos de un array en un String CSV con el delimitador dado.
     *
     * @param campos      array de campos
     * @param delimitador delimitador
     * @return string con los campos unidos
     */
    public static String unirCampos(String[] campos, String delimitador) {
        // TODO 7: Usar String.join(delimitador, campos).
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 32: Escribir CSV ===\n");

        String dir = "temp/bloque6";
        new File(dir).mkdirs();

        escribirProductos(dir + "/out.csv",
                new String[]{"Arroz", "Aceite"}, new double[]{1.20, 3.75}, new int[]{50, 20});
        System.out.println("CSV escrito.");

        tablaMultiplicarCSV(dir + "/mult.csv", 5);
        System.out.println("Tabla multiplicar generada.");

        System.out.println("Unir: " + unirCampos(new String[]{"A", "B", "C"}, ";"));
    }
}
