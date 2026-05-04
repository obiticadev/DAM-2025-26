package com.masterclass.collections.nivel06_linkedhashmap_treemap;

import com.masterclass.collections.modelos.Producto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * EJERCICIO 20 — LinkedHashMap + TreeMap: Integración con objetos de dominio
 * ============================================================================
 * Teoría de referencia: teoria/04_HashMap_Avanzado_LinkedHashMap_TreeMap.md  (§ 2, 3, 4)
 *
 * Objetivo: Combinar LinkedHashMap y TreeMap con objetos de dominio (Producto)
 * para resolver problemas del mundo real: historial de consultas, catálogos
 * ordenados por precio, etc.
 *
 * Restricción: Usa el tipo de mapa indicado en cada TODO.
 */
public class Ejercicio20_IntegracionMapasOrdenados {

    // TODO 1: Implementa `historialConsultas`.
    //   - Recibe una lista de IDs de producto (String[]) representando consultas
    //     en orden cronológico.
    //   - Retorna un LinkedHashMap<String, Integer> donde la clave es el ID
    //     del producto y el valor es el número de veces que fue consultado.
    //   - El orden del LinkedHashMap debe ser el orden de PRIMERA APARICIÓN
    //     (inserción). Usa computeIfAbsent + compute o merge.
    public static LinkedHashMap<String, Integer> historialConsultas(String[] consultasIds) {
        return null;
    }

    // TODO 2: Implementa `catalogoOrdenadoPorPrecio`.
    //   - Recibe un ArrayList<Producto>.
    //   - Construye un TreeMap<Double, ArrayList<String>> donde la clave es el precio
    //     y el valor es una lista con los NOMBRES de productos con ese precio.
    //   - El TreeMap ordena por precio ascendente de forma natural.
    //   - Si varios productos tienen el mismo precio, se agrupan en la misma lista.
    //   - Usa computeIfAbsent para gestionar las listas internas.
    public static TreeMap<Double, ArrayList<String>> catalogoOrdenadoPorPrecio(
            ArrayList<Producto> productos) {
        return null;
    }

    // TODO 3: Implementa `productosMasBaratosQue`.
    //   - Dado el catálogo del TODO 2 y un precio máximo (double),
    //     retorna un ArrayList<String> con TODOS los nombres de productos
    //     cuyo precio sea estrictamente menor que precioMaximo.
    //   - Usa headMap(precioMaximo, false) del TreeMap para obtener
    //     la vista del rango [0, precioMaximo).
    //   - Itera los valores del submapa y agrega todos los nombres a la lista resultante.
    public static ArrayList<String> productosMasBaratosQue(
            TreeMap<Double, ArrayList<String>> catalogo, double precioMaximo) {
        return null;
    }

    // TODO 4: Implementa `productoMasCaro`.
    //   - Dado el catálogo del TODO 2, retorna el NOMBRE del primer producto
    //     en la lista del precio más alto.
    //   - Usa lastEntry() del TreeMap.
    //   - Si el catálogo está vacío, retorna null.
    public static String productoMasCaro(TreeMap<Double, ArrayList<String>> catalogo) {
        return null;
    }

    // TODO 5: Implementa `convertirHistorialATreeMap`.
    //   - Recibe el LinkedHashMap<String, Integer> del TODO 1 (historial de consultas).
    //   - Retorna un TreeMap<Integer, ArrayList<String>> donde la clave es el número
    //     de consultas y el valor es la lista de IDs de producto con ese conteo.
    //   - El TreeMap ordena de MAYOR a MENOR número de consultas
    //     (usa Comparator.reverseOrder() en el constructor).
    //   - Usa computeIfAbsent para gestionar las listas.
    public static TreeMap<Integer, ArrayList<String>> convertirHistorialATreeMap(
            LinkedHashMap<String, Integer> historial) {
        return null;
    }

    // TODO 6 (desafío): Implementa `topNProductosMasConsultados`.
    //   - Recibe el resultado del TODO 5 y un número N.
    //   - Retorna un ArrayList<String> con los IDs de los N productos
    //     más consultados (en orden descendente de consultas).
    //   - Si hay menos de N productos, retorna todos los que haya.
    //   - Itera el TreeMap (ya en orden desc) y ve añadiendo IDs
    //     hasta alcanzar N.
    public static ArrayList<String> topNProductosMasConsultados(
            TreeMap<Integer, ArrayList<String>> ranking, int n) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 20 — Integración LinkedHashMap + TreeMap ===\n");

        // -- historialConsultas --
        String[] consultas = {"P01", "P03", "P01", "P02", "P03", "P01", "P04", "P02"};
        LinkedHashMap<String, Integer> historial = historialConsultas(consultas);
        System.out.println("Historial de consultas: " + historial);

        // -- catalogoOrdenadoPorPrecio --
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("P01", "Teclado",   45.0, "periféricos", "input"));
        productos.add(new Producto("P02", "Ratón",     25.0, "periféricos", "input"));
        productos.add(new Producto("P03", "Monitor",  299.0, "pantallas",   "output"));
        productos.add(new Producto("P04", "Webcam",    45.0, "periféricos", "input"));
        productos.add(new Producto("P05", "Altavoz",   15.0, "audio",       "output"));

        TreeMap<Double, ArrayList<String>> catalogo = catalogoOrdenadoPorPrecio(productos);
        System.out.println("Catálogo por precio: " + catalogo);

        // -- productosMasBaratosQue --
        System.out.println("Más baratos que 46€: " + productosMasBaratosQue(catalogo, 46.0));

        // -- productoMasCaro --
        System.out.println("Más caro: " + productoMasCaro(catalogo));

        // -- convertirHistorialATreeMap --
        TreeMap<Integer, ArrayList<String>> ranking = convertirHistorialATreeMap(historial);
        System.out.println("\nRanking (desc por consultas): " + ranking);

        // -- topNProductosMasConsultados --
        System.out.println("Top 2 más consultados: " + topNProductosMasConsultados(ranking, 2));
    }
}
