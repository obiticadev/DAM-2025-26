package bloque6b;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.util.*;

/**
 * EJERCICIO 57 — Deserializar JSON a Objetos con Jackson
 * 📋 ENTRA EN EXAMEN — Tema 08 (readValue, TypeReference)
 * Teoria: teoria/06B_JSON_XML.md (secciones 5-6)
 *
 * Contexto: La plataforma web envia datos de productos en JSON que
 * necesitas convertir a objetos Java para procesarlos.
 */
public class Ej57_DeserializarJSON {

    /**
     * Deserializa un ProductoJSON desde un fichero .json.
     */
    public static ProductoJSON deserializarProducto(String ruta) throws IOException {
        // TODO 1: Crear ObjectMapper. Usar readValue(new File(ruta), ProductoJSON.class).
        return null;
    }

    /**
     * Deserializa un ProductoJSON desde un String JSON.
     */
    public static ProductoJSON desdeString(String json) throws IOException {
        // TODO 2: Crear ObjectMapper. Usar readValue(json, ProductoJSON.class).
        return null;
    }

    /**
     * Deserializa una lista de ProductoJSON desde un fichero.
     */
    public static List<ProductoJSON> deserializarLista(String ruta) throws IOException {
        // TODO 3: Crear ObjectMapper.
        //         Usar readValue(new File(ruta), new TypeReference<List<ProductoJSON>>() {}).
        return new ArrayList<>();
    }

    /**
     * Verifica la integridad: serializa, deserializa y compara con equals.
     */
    public static boolean verificarIntegridad(String ruta, ProductoJSON original) throws IOException {
        // TODO 4: Serializar el producto con writeValue.
        //         Deserializar con readValue. Comparar con equals().
        return false;
    }

    /**
     * Deserializa un Map<String,Object> desde un fichero JSON.
     */
    public static Map<String, Object> deserializarMapa(String ruta) throws IOException {
        // TODO 5: Crear ObjectMapper.
        //         Usar readValue(new File(ruta), new TypeReference<Map<String,Object>>() {}).
        return new HashMap<>();
    }

    /**
     * Intenta deserializar un fichero invalido. Devuelve el nombre
     * de la excepcion o "OK".
     */
    public static String intentarDeserializar(String ruta) {
        // TODO 6: Intentar deserializar con try-catch.
        //         Si funciona, "OK". Si lanza Exception, e.getClass().getSimpleName().
        return "";
    }

    /**
     * Cuenta cuantos productos tiene una lista guardada en JSON.
     */
    public static int contarProductos(String ruta) throws IOException {
        // TODO 7: Deserializar la lista con TypeReference. Devolver su size().
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 57: Deserializar JSON ===\n");

        String dir = "temp/bloque6b";

        ProductoJSON p = deserializarProducto(dir + "/producto.json");
        System.out.println("Deserializado: " + p);

        ProductoJSON p2 = desdeString("{\"nombre\":\"Mouse\",\"precio\":29.99}");
        System.out.println("Desde String: " + p2);
    }
}
