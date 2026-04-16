package bloque6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 31 — Leer un Fichero CSV
 * Teoria: teoria/06_Procesamiento_CSV.md (secciones 1-2, 5-6)
 *
 * Contexto: Una cadena de supermercados recibe un fichero CSV con el
 * inventario de productos. Formato: "nombre;precio;stock" con cabecera.
 */
public class Ej31_LeerCSV {

    /**
     * Lee un CSV y devuelve todas las lineas de datos (sin cabecera).
     *
     * @param ruta ruta del fichero CSV
     * @return lista de lineas de datos
     * @throws IOException si hay error de lectura
     */
    public static List<String> leerLineasDatos(String ruta) throws IOException {
        // TODO 1: Abrir con BufferedReader y try-with-resources.
        //         Saltar la primera linea (cabecera).
        //         Leer el resto y devolverlas como lista.
        return new ArrayList<>();
    }

    /**
     * Lee un CSV y devuelve los nombres de las columnas (cabecera).
     *
     * @param ruta       ruta del fichero CSV
     * @param delimitador delimitador usado (ej: ";")
     * @return array con los nombres de las columnas
     * @throws IOException si hay error
     */
    public static String[] leerCabecera(String ruta, String delimitador) throws IOException {
        // TODO 2: Leer solo la primera linea. Hacer split(delimitador).
        //         Hacer trim() de cada campo. Devolver el array.
        return new String[0];
    }

    /**
     * Cuenta el numero de registros (sin contar la cabecera).
     *
     * @param ruta ruta del fichero CSV
     * @return numero de registros
     * @throws IOException si hay error
     */
    public static int contarRegistros(String ruta) throws IOException {
        // TODO 3: Usar leerLineasDatos() y devolver el tamano.
        return 0;
    }

    /**
     * Lee un CSV de productos (nombre;precio;stock) y devuelve la suma de stock.
     *
     * @param ruta ruta del fichero CSV
     * @return suma total de stock
     * @throws IOException si hay error
     */
    public static int sumaStock(String ruta) throws IOException {
        // TODO 4: Leer lineas de datos. Para cada una, split(";"),
        //         parsear el campo [2] como int y sumar.
        //         Usar try-catch para manejar NumberFormatException.
        return 0;
    }

    /**
     * Busca un producto por nombre (case insensitive) y devuelve su linea CSV.
     *
     * @param ruta   ruta del fichero CSV
     * @param nombre nombre a buscar
     * @return la linea CSV encontrada o null si no existe
     * @throws IOException si hay error
     */
    public static String buscarProducto(String ruta, String nombre) throws IOException {
        // TODO 5: Leer lineas de datos. Para cada una, split(";").
        //         Si campo[0].trim() coincide (case insensitive), devolver la linea.
        return null;
    }

    /**
     * Devuelve el producto mas caro del CSV.
     *
     * @param ruta ruta del fichero CSV
     * @return nombre del producto mas caro, o null si esta vacio
     * @throws IOException si hay error
     */
    public static String productoMasCaro(String ruta) throws IOException {
        // TODO 6: Leer lineas de datos. Parsear precios.
        //         Encontrar el maximo. Devolver su nombre.
        return null;
    }

    /**
     * Valida un fichero CSV: comprueba que todas las lineas tienen
     * exactamente el mismo numero de campos que la cabecera.
     *
     * @param ruta        ruta del fichero CSV
     * @param delimitador delimitador
     * @return numero de lineas invalidas (0 si todo es correcto)
     * @throws IOException si hay error
     */
    public static int validarCSV(String ruta, String delimitador) throws IOException {
        // TODO 7: Leer cabecera y contar columnas esperadas.
        //         Leer cada linea de datos. Si el split no tiene
        //         el mismo numero de campos, incrementar contador.
        //         Devolver el total de lineas invalidas.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 31: Leer CSV ===\n");

        String dir = "temp/bloque6";
        new File(dir).mkdirs();

        // Crear CSV de prueba
        try (var bw = new java.io.BufferedWriter(new java.io.FileWriter(dir + "/productos.csv"))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\n");
            bw.write("Aceite;3.75;20\n");
            bw.write("Sal;0.80;100\n");
        }

        System.out.println("Registros: " + contarRegistros(dir + "/productos.csv"));
        System.out.println("Suma stock: " + sumaStock(dir + "/productos.csv"));
        System.out.println("Buscar 'arroz': " + buscarProducto(dir + "/productos.csv", "arroz"));
        System.out.println("Mas caro: " + productoMasCaro(dir + "/productos.csv"));
    }
}
