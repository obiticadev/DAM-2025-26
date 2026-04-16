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
 * EJERCICIO 35 — Unir (Join) Dos Ficheros CSV
 * Teoria: teoria/06_Procesamiento_CSV.md (seccion 7)
 *
 * Contexto: El supermercado tiene dos CSVs:
 *   productos.csv: "nombre;precio;stock"
 *   proveedores.csv: "producto;proveedor;plazoEntrega"
 * Necesita combinarlos por el campo en comun (nombre de producto).
 */
public class Ej35_UnirCSV {

    /**
     * Carga un CSV en un Map donde la clave es el primer campo (lowercase).
     * El valor es la linea completa.
     *
     * @param ruta ruta del CSV
     * @return mapa campo1 -> linea completa (sin cabecera)
     * @throws IOException si hay error
     */
    public static Map<String, String> cargarComoMapa(String ruta) throws IOException {
        // TODO 1: Leer CSV, saltar cabecera.
        //         Para cada linea, split(";"). Clave = campo[0].trim().toLowerCase().
        //         Valor = la linea completa.
        //         Almacenar en LinkedHashMap para preservar orden.
        return new LinkedHashMap<>();
    }

    /**
     * Hace un inner join de dos CSVs por el primer campo.
     * Resultado: las lineas que existen en AMBOS ficheros, combinadas.
     * Formato salida: campos de fichero1 + campos de fichero2 (sin repetir la clave).
     *
     * @param ruta1       primer CSV
     * @param ruta2       segundo CSV
     * @param rutaDestino CSV destino
     * @return numero de registros en el resultado
     * @throws IOException si hay error
     */
    public static int innerJoin(String ruta1, String ruta2, String rutaDestino)
            throws IOException {
        // TODO 2: Cargar ambos como mapas. Recorrer las claves de mapa1.
        //         Si la clave tambien esta en mapa2, combinar los campos.
        //         Escribir cabecera combinada y los registros.
        return 0;
    }

    /**
     * Hace un left join: todos los registros del primer CSV, con datos
     * del segundo si existen (o campos vacios si no).
     *
     * @param ruta1       CSV izquierdo (principal)
     * @param ruta2       CSV derecho (secundario)
     * @param rutaDestino CSV destino
     * @return numero de registros en el resultado
     * @throws IOException si hay error
     */
    public static int leftJoin(String ruta1, String ruta2, String rutaDestino)
            throws IOException {
        // TODO 3: Cargar mapa2. Leer ruta1 linea a linea.
        //         Si la clave esta en mapa2, combinar.
        //         Si no, anadir campos vacios del CSV derecho.
        return 0;
    }

    /**
     * Encuentra los productos que NO tienen proveedor (estan en CSV1 pero no en CSV2).
     *
     * @param ruta1 CSV de productos
     * @param ruta2 CSV de proveedores
     * @return lista de nombres de productos sin proveedor
     * @throws IOException si hay error
     */
    public static java.util.List<String> sinProveedor(String ruta1, String ruta2) throws IOException {
        // TODO 4: Cargar mapa2. Recorrer claves de mapa1.
        //         Si la clave no esta en mapa2, anadir a la lista.
        return new java.util.ArrayList<>();
    }

    /**
     * Combina dos CSVs verticalmente (union). Ambos deben tener la misma cabecera.
     * El resultado tiene una sola cabecera y todos los datos de ambos.
     *
     * @param ruta1       primer CSV
     * @param ruta2       segundo CSV
     * @param rutaDestino CSV destino
     * @return total de registros (sin cabecera)
     * @throws IOException si hay error
     */
    public static int unionVertical(String ruta1, String ruta2, String rutaDestino)
            throws IOException {
        // TODO 5: Leer cabecera de ruta1. Escribir en destino.
        //         Leer datos de ruta1 y escribirlos.
        //         Saltar cabecera de ruta2. Leer datos y escribirlos.
        //         Devolver total.
        return 0;
    }

    /**
     * Cuenta los registros comunes entre dos CSVs (por primer campo).
     *
     * @param ruta1 primer CSV
     * @param ruta2 segundo CSV
     * @return numero de registros en comun
     * @throws IOException si hay error
     */
    public static int registrosComunes(String ruta1, String ruta2) throws IOException {
        // TODO 6: Cargar ambos mapas. Contar claves que estan en ambos.
        return 0;
    }

    /**
     * Indica si dos CSVs tienen la misma cabecera.
     *
     * @param ruta1 primer CSV
     * @param ruta2 segundo CSV
     * @return true si las cabeceras son identicas
     * @throws IOException si hay error
     */
    public static boolean mismaCabecera(String ruta1, String ruta2) throws IOException {
        // TODO 7: Leer primera linea de ambos. Comparar.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 35: Unir CSV ===\n");

        String dir = "temp/bloque6";
        new File(dir).mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dir + "/prod.csv"))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\nAceite;3.75;20\nSal;0.80;100\n");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dir + "/prov.csv"))) {
            bw.write("producto;proveedor;plazo\n");
            bw.write("Arroz;ProvA;3\nSal;ProvB;5\n");
        }

        System.out.println("Inner join: " + innerJoin(dir + "/prod.csv", dir + "/prov.csv", dir + "/inner.csv"));
        System.out.println("Sin proveedor: " + sinProveedor(dir + "/prod.csv", dir + "/prov.csv"));
        System.out.println("Comunes: " + registrosComunes(dir + "/prod.csv", dir + "/prov.csv"));
    }
}
