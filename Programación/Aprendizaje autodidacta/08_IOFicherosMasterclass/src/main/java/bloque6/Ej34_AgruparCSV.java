package bloque6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * EJERCICIO 34 — Agrupar Datos de un CSV
 * Teoria: teoria/06_Procesamiento_CSV.md (seccion 7)
 *
 * Contexto: El supermercado tiene un CSV de ventas con formato:
 * "producto;categoria;cantidad;precioUnitario"
 * Necesita agrupar y resumir datos por categoria.
 */
public class Ej34_AgruparCSV {

    /**
     * Agrupa las ventas por categoria y devuelve el total de cantidad vendida
     * por cada una.
     *
     * @param ruta ruta del CSV de ventas
     * @return mapa categoria -> cantidad total
     * @throws IOException si hay error
     */
    public static Map<String, Integer> ventasPorCategoria(String ruta) throws IOException {
        // TODO 1: Leer CSV (saltar cabecera). Para cada linea, split(";").
        //         campo[1] = categoria, campo[2] = cantidad.
        //         Acumular en un Map. Devolver.
        return new LinkedHashMap<>();
    }

    /**
     * Calcula el ingreso total (cantidad * precioUnitario) por categoria.
     *
     * @param ruta ruta del CSV de ventas
     * @return mapa categoria -> ingreso total
     * @throws IOException si hay error
     */
    public static Map<String, Double> ingresosPorCategoria(String ruta) throws IOException {
        // TODO 2: Leer CSV. Para cada linea, calcular cantidad * precioUnitario.
        //         Acumular por categoria en un Map.
        return new LinkedHashMap<>();
    }

    /**
     * Cuenta cuantos productos distintos tiene cada categoria.
     *
     * @param ruta ruta del CSV de ventas
     * @return mapa categoria -> numero de productos distintos
     * @throws IOException si hay error
     */
    public static Map<String, Integer> productosPorCategoria(String ruta) throws IOException {
        // TODO 3: Leer CSV. Para cada categoria, llevar un Set de productos.
        //         Devolver mapa con el tamano de cada Set.
        return new LinkedHashMap<>();
    }

    /**
     * Genera un CSV de resumen por categoria.
     * Formato salida: "categoria;totalVentas;ingresoTotal"
     *
     * @param rutaOrigen  CSV de ventas original
     * @param rutaDestino CSV de resumen
     * @throws IOException si hay error
     */
    public static void generarResumenPorCategoria(String rutaOrigen, String rutaDestino)
            throws IOException {
        // TODO 4: Obtener ventasPorCategoria e ingresosPorCategoria.
        //         Escribir CSV con cabecera y una linea por categoria.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Encuentra la categoria con mayor ingreso total.
     *
     * @param ruta ruta del CSV de ventas
     * @return nombre de la categoria con mas ingresos, o null si vacio
     * @throws IOException si hay error
     */
    public static String categoriaMasRentable(String ruta) throws IOException {
        // TODO 5: Usar ingresosPorCategoria(). Buscar el maximo. Devolver la clave.
        return null;
    }

    /**
     * Calcula el precio medio por categoria (ingreso / cantidad).
     *
     * @param ruta ruta del CSV de ventas
     * @return mapa categoria -> precio medio
     * @throws IOException si hay error
     */
    public static Map<String, Double> precioMedioPorCategoria(String ruta) throws IOException {
        // TODO 6: Usar ventasPorCategoria e ingresosPorCategoria.
        //         Dividir ingreso / cantidad para cada categoria.
        return new LinkedHashMap<>();
    }

    /**
     * Cuenta el total de registros (lineas de datos) del CSV.
     *
     * @param ruta ruta del CSV
     * @return total de registros
     * @throws IOException si hay error
     */
    public static int totalRegistros(String ruta) throws IOException {
        // TODO 7: Leer lineas saltando cabecera. Contar.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 34: Agrupar CSV ===\n");

        String dir = "temp/bloque6";
        new File(dir).mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dir + "/ventas.csv"))) {
            bw.write("producto;categoria;cantidad;precioUnitario\n");
            bw.write("Arroz;Alimentacion;10;1.20\n");
            bw.write("Aceite;Alimentacion;5;3.75\n");
            bw.write("Jabon;Limpieza;8;2.00\n");
            bw.write("Lejia;Limpieza;12;1.50\n");
        }

        System.out.println("Ventas: " + ventasPorCategoria(dir + "/ventas.csv"));
        System.out.println("Ingresos: " + ingresosPorCategoria(dir + "/ventas.csv"));
        System.out.println("Mas rentable: " + categoriaMasRentable(dir + "/ventas.csv"));
    }
}
