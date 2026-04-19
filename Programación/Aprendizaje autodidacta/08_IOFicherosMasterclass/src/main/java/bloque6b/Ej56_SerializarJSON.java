package bloque6b;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.util.*;

/**
 * EJERCICIO 56 — Serializar un Objeto a JSON con Jackson
 * 📋 ENTRA EN EXAMEN — Tema 08 (ObjectMapper, writeValue)
 * Teoria: teoria/06B_JSON_XML.md (secciones 3-4)
 *
 * Contexto: Una tienda online necesita exportar su catalogo de productos
 * en formato JSON para integrarse con su plataforma web.
 */
public class Ej56_SerializarJSON {

    /**
     * Serializa un ProductoJSON a un fichero .json.
     */
    public static void serializarProducto(String ruta, ProductoJSON producto) throws IOException {
        // TODO 1: Crear ObjectMapper. Usar writeValue(new File(ruta), producto).
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Convierte un ProductoJSON a String JSON.
     */
    public static String productoAJsonString(ProductoJSON producto) throws IOException {
        // TODO 2: Crear ObjectMapper. Usar writeValueAsString(producto). Devolver.
        return "";
    }

    /**
     * Serializa una lista de ProductoJSON a un fichero JSON.
     */
    public static void serializarLista(String ruta, List<ProductoJSON> productos) throws IOException {
        // TODO 3: Crear ObjectMapper. Usar writeValue(new File(ruta), productos).
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Serializa un ProductoJSON con formato bonito (indentado).
     */
    public static String serializarBonito(ProductoJSON producto) throws IOException {
        // TODO 4: Crear ObjectMapper. Usar writerWithDefaultPrettyPrinter().writeValueAsString(producto).
        return "";
    }

    /**
     * Serializa un Map<String,Object> a JSON.
     */
    public static void serializarMapa(String ruta, Map<String, Object> mapa) throws IOException {
        // TODO 5: Crear ObjectMapper. Usar writeValue(new File(ruta), mapa).
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Devuelve true si el fichero JSON creado no esta vacio.
     */
    public static boolean ficheroNoVacio(String ruta) {
        // TODO 6: Crear File. Si existe y length() > 0, devolver true.
        return false;
    }

    /**
     * Devuelve el tamano del fichero JSON en bytes.
     */
    public static long tamanoFichero(String ruta) {
        // TODO 7: Crear File. Si existe, devolver length(). Si no, -1.
        return -1;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 56: Serializar JSON ===\n");

        String dir = "temp/bloque6b";
        new File(dir).mkdirs();

        ProductoJSON p = new ProductoJSON("Laptop", 1200.99);
        serializarProducto(dir + "/producto.json", p);
        System.out.println("JSON: " + productoAJsonString(p));
        System.out.println("Bonito:\n" + serializarBonito(p));
        System.out.println("Tamano: " + tamanoFichero(dir + "/producto.json") + " bytes");
    }
}
