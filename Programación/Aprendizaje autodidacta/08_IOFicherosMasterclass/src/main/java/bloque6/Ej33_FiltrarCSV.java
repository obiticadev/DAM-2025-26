package bloque6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 33 — Filtrar y Transformar CSV
 * Teoria: teoria/06_Procesamiento_CSV.md (seccion 7)
 *
 * Contexto: El supermercado necesita generar subconjuntos del inventario:
 * productos baratos, con poco stock, de una categoria, etc.
 */
public class Ej33_FiltrarCSV {

    /**
     * Filtra productos cuyo precio sea menor o igual a un maximo.
     * Escribe el resultado (con cabecera) en el fichero destino.
     *
     * @param rutaOrigen  CSV origen (nombre;precio;stock)
     * @param rutaDestino CSV destino
     * @param precioMax   precio maximo
     * @return numero de productos filtrados
     * @throws IOException si hay error
     */
    public static int filtrarPorPrecio(String rutaOrigen, String rutaDestino,
                                        double precioMax) throws IOException {
        // TODO 1: Leer CSV origen con BufferedReader. Escribir cabecera en destino.
        //         Para cada linea de datos, parsear precio.
        //         Si precio <= precioMax, escribir en destino.
        //         Devolver el total filtrado.
        return 0;
    }

    /**
     * Filtra productos cuyo stock sea menor que un minimo (productos a reponer).
     *
     * @param rutaOrigen  CSV origen
     * @param rutaDestino CSV destino
     * @param stockMinimo stock minimo
     * @return numero de productos a reponer
     * @throws IOException si hay error
     */
    public static int productosAReponer(String rutaOrigen, String rutaDestino,
                                         int stockMinimo) throws IOException {
        // TODO 2: Similar al TODO 1 pero filtrando por stock < stockMinimo.
        return 0;
    }

    /**
     * Aplica un descuento porcentual a todos los precios y genera un nuevo CSV.
     *
     * @param rutaOrigen     CSV origen
     * @param rutaDestino    CSV destino
     * @param descuentoPct   porcentaje de descuento (0-100)
     * @throws IOException si hay error
     */
    public static void aplicarDescuento(String rutaOrigen, String rutaDestino,
                                         double descuentoPct) throws IOException {
        // TODO 3: Leer cada producto. Calcular nuevo precio con descuento.
        //         precio * (1 - descuentoPct/100).
        //         Escribir CSV con precios actualizados (formato %.2f).
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Convierte un CSV de productos a mayusculas (solo el nombre).
     *
     * @param rutaOrigen  CSV origen
     * @param rutaDestino CSV destino
     * @return numero de registros convertidos
     * @throws IOException si hay error
     */
    public static int nombresAMayusculas(String rutaOrigen, String rutaDestino) throws IOException {
        // TODO 4: Leer cada producto. Convertir nombre a mayusculas.
        //         Mantener precio y stock sin cambios.
        //         Escribir en destino.
        return 0;
    }

    /**
     * Ordena los registros de un CSV por precio (ascendente).
     * Lee todos los registros, los ordena y los escribe en un nuevo fichero.
     *
     * @param rutaOrigen  CSV origen
     * @param rutaDestino CSV destino
     * @throws IOException si hay error
     */
    public static void ordenarPorPrecio(String rutaOrigen, String rutaDestino) throws IOException {
        // TODO 5: Leer todos los registros en una lista.
        //         Ordenar comparando el campo precio con Comparator.
        //         Escribir cabecera + registros ordenados en destino.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Genera un CSV con el valor (precio * stock) de cada producto.
     * Formato salida: "nombre;valor"
     *
     * @param rutaOrigen  CSV origen (nombre;precio;stock)
     * @param rutaDestino CSV destino (nombre;valor)
     * @throws IOException si hay error
     */
    public static void calcularValores(String rutaOrigen, String rutaDestino) throws IOException {
        // TODO 6: Leer cada registro. Calcular precio * stock.
        //         Escribir "nombre;valor" con formato %.2f.
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Cuenta cuantos productos distintos hay (por nombre unico, case insensitive).
     *
     * @param ruta ruta del CSV
     * @return numero de productos unicos
     * @throws IOException si hay error
     */
    public static int productosUnicos(String ruta) throws IOException {
        // TODO 7: Leer nombres. Almacenar en un Set (en lowercase).
        //         Devolver el tamano del Set.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 33: Filtrar CSV ===\n");

        String dir = "temp/bloque6";
        new File(dir).mkdirs();

        // Crear CSV de prueba
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dir + "/inv.csv"))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\nAceite;3.75;20\nSal;0.80;100\nPan;1.50;5\n");
        }

        System.out.println("Baratos: " + filtrarPorPrecio(dir + "/inv.csv", dir + "/bar.csv", 1.50));
        System.out.println("Reponer: " + productosAReponer(dir + "/inv.csv", dir + "/rep.csv", 10));
        System.out.println("Unicos: " + productosUnicos(dir + "/inv.csv"));
    }
}
