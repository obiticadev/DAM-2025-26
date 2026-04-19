package bloque6b;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.util.*;

/**
 * EJERCICIO 58 — Serializar y Deserializar Listas JSON
 * 📋 ENTRA EN EXAMEN — Tema 08 (TypeReference para colecciones)
 * Teoria: teoria/06B_JSON_XML.md (seccion 6)
 *
 * Contexto: El inventario completo de la tienda se exporta como una lista
 * JSON para generar reportes.
 */
public class Ej58_ListaJSON {

    /**
     * Serializa una lista de productos a un fichero JSON.
     */
    public static void guardarInventario(String ruta, List<ProductoJSON> productos) throws IOException {
        // TODO 1: Crear ObjectMapper. writeValue(new File(ruta), productos).
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Carga el inventario desde un fichero JSON.
     */
    public static List<ProductoJSON> cargarInventario(String ruta) throws IOException {
        // TODO 2: Crear ObjectMapper. readValue con TypeReference<List<ProductoJSON>>.
        return new ArrayList<>();
    }

    /**
     * Anade un producto al inventario (leer, anadir, guardar).
     */
    public static void anadirProducto(String ruta, ProductoJSON nuevo) throws IOException {
        // TODO 3: Cargar la lista actual con cargarInventario.
        //         Anadir el nuevo producto. Guardar con guardarInventario.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Elimina un producto por nombre del inventario.
     * Devuelve true si se elimino.
     */
    public static boolean eliminarProducto(String ruta, String nombre) throws IOException {
        // TODO 4: Cargar lista. Buscar producto con ese nombre.
        //         Si existe, eliminarlo y guardar. Devolver true/false.
        return false;
    }

    /**
     * Busca un producto por nombre. Devuelve el objeto o null.
     */
    public static ProductoJSON buscarPorNombre(String ruta, String nombre) throws IOException {
        // TODO 5: Cargar lista. Recorrer buscando por nombre. Devolver o null.
        return null;
    }

    /**
     * Devuelve cuantos productos hay en el inventario.
     */
    public static int totalProductos(String ruta) throws IOException {
        // TODO 6: Cargar lista. Devolver size().
        return 0;
    }

    /**
     * Calcula el precio total del inventario (suma de precios).
     */
    public static double precioTotal(String ruta) throws IOException {
        // TODO 7: Cargar lista. Sumar precios de todos los productos.
        return 0.0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 58: Lista JSON ===\n");

        String dir = "temp/bloque6b";
        new File(dir).mkdirs();
        String ruta = dir + "/inventario.json";

        List<ProductoJSON> lista = Arrays.asList(
            new ProductoJSON("Laptop", 1200.99),
            new ProductoJSON("Mouse", 29.99),
            new ProductoJSON("Teclado", 79.50)
        );
        guardarInventario(ruta, new ArrayList<>(lista));
        System.out.println("Guardados: " + totalProductos(ruta));
        System.out.println("Precio total: " + precioTotal(ruta));
        System.out.println("Buscar Mouse: " + buscarPorNombre(ruta, "Mouse"));
    }
}
