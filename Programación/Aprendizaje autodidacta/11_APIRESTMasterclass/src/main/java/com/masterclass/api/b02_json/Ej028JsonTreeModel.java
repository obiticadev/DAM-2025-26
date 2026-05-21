package com.masterclass.api.b02_json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Ejercicio 028 · Árbol JSON (JsonNode) para lectura dinámica.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.4).
 */
public final class Ej028JsonTreeModel {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Ej028JsonTreeModel() {
    }

    /**
     * Extrae el id del primer elemento del array "datos".
     *
     * @param json texto tipo {@code {"datos":[{"id":7},{"id":9}]}}
     * @return el id (7 en el ejemplo); -1 si la ruta no existe
     */
    public static int primerIdDeDatos(String json) {
        // TODO 1: parsea el texto con MAPPER.readTree(json) (envuelve la checked).
        // TODO 2: obtén el nodo "datos" con root.get("datos").
        // TODO 3: si ese nodo es null o no es array, devuelve -1.
        // TODO 4: accede al primer elemento con get(0); si es null, -1.
        // TODO 5: obtén el nodo "id" de ese elemento; si es null, -1.
        // TODO 6: devuelve su valor con asInt().
        try {
            if (json == null || json.strip().isEmpty()) {
                return -1;
            }
            JsonNode root = MAPPER.readTree(json);
            JsonNode datos = root.get("datos");
            if (datos == null || !datos.isArray() || datos.size() == 0) {
                return -1;
            }
            JsonNode primerElem = datos.get(0);
            if (primerElem == null) {
                return -1;
            }
            JsonNode idNode = primerElem.get("id");
            if (idNode == null) {
                return -1;
            }
            return idNode.asInt();
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new RuntimeException("Error al parsear árbol JSON", e);
        }
    }

    /**
     * Cuenta cuántos elementos tiene el array "datos".
     *
     * @param json texto JSON
     * @return tamaño del array, o 0 si no existe o no es array
     */
    public static int tamanioDatos(String json) {
        // TODO 7: parsea con readTree (reutiliza el patrón anterior).
        // TODO 8: localiza el nodo "datos".
        // TODO 9: si es null o no isArray(), devuelve 0.
        // TODO 10: si es array, devuelve su size().
        try {
            if (json == null || json.strip().isEmpty()) {
                return 0;
            }
            JsonNode root = MAPPER.readTree(json);
            JsonNode datos = root.get("datos");
            if (datos == null || !datos.isArray()) {
                return 0;
            }
            return datos.size();
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new RuntimeException("Error al obtener tamaño del nodo datos", e);
        }
    }

    // --- MÉTODOS DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Busca un valor textual dentro de un JSON utilizando notación de puntos.
     */
    public static String buscarPorRuta(String json, String ruta) {
        // TODO extra: Busca la ruta dada en el JSON (ej. "usuario.direccion.ciudad").
        // Devuelve el valor textual, o una cadena vacía si no existe.
        return "";
    }

    /**
     * Reto Extra 2: Crea un JSON estructurado de forma programática.
     */
    public static String crearJsonProgramatico(String nombre, int edad, java.util.List<String> aficiones) {
        // TODO extra: Construye un árbol JSON con ObjectNode y ArrayNode, y devuélvelo como string.
        return "";
    }

    /**
     * Reto Extra 3: Añade o actualiza dinámicamente un atributo en un JSON.
     */
    public static String inyectarAtributo(String jsonOriginal, String clave, Object valor) {
        // TODO extra: Parsea el JSON original a ObjectNode, inyecta la clave con el valor adecuado
        // según su tipo dinámico (String, Integer, Boolean, Double, Long, etc.) y serialízalo.
        return "";
    }

    /**
     * Reto Extra 4: Elimina dinámicamente un atributo en un JSON.
     */
    public static String eliminarAtributo(String jsonOriginal, String clave) {
        // TODO extra: Parsea el JSON original a ObjectNode, elimina la clave y serialízalo.
        return "";
    }

    /**
     * Reto Extra 5: Determina el tipo de datos de un nodo específico por su clave.
     */
    public static String inspeccionarTipoDeNodo(String json, String clave) {
        // TODO extra: Parsea el JSON y devuelve una cadena que represente el tipo del nodo "clave".
        // Valores posibles: "OBJECT", "ARRAY", "TEXTUAL", "NUMBER", "BOOLEAN", "NULL", "MISSING" o "UNKNOWN".
        return "";
    }

    /**
     * Reto Extra 6: Extrae una lista de valores asociados a un campo específico desde un array JSON.
     */
    public static java.util.List<String> extraerValoresDeArray(String json, String nombreCampo) {
        // TODO extra: Parsea el JSON (que es un array) y extrae el valor textual de "nombreCampo" en cada elemento.
        // Omitir nulos o ausentes. Devuelve una lista vacía si no hay elementos o falla.
        return java.util.List.of();
    }

    /**
     * Reto Extra 7: Fusiona dos JSONs planos o estructurados sobreescribiendo claves.
     */
    public static String fusionarArboles(String jsonA, String jsonB) {
        // TODO extra: Fusiona en profundidad (deep merge) jsonB sobre jsonA, de forma que jsonB
        // sobreescriba claves en jsonA y devuelva el JSON fusionado resultante.
        return "";
    }

    /**
     * Reto Extra 8: Busca un campo de forma recursiva a cualquier nivel de profundidad en el JSON.
     */
    public static String buscarClaveRecursiva(String json, String claveBuscada) {
        // TODO extra: Realiza una búsqueda recursiva del primer nodo que contenga la clave "claveBuscada"
        // a cualquier nivel de profundidad y devuelve su valor como texto.
        return "";
    }

    /**
     * Reto Extra 9: Extrae un subárbol a partir de una ruta e instáncialo directamente a un DTO.
     */
    public static <T> T convertirSubarbolADto(String json, String rutaSubarbol, Class<T> claseDestino) {
        // TODO extra: Encuentra el nodo correspondiente a "rutaSubarbol" (ej. "responsable") y
        // conviértelo al DTO de tipo "claseDestino" (ej. Persona.class) usando treeToValue.
        return null;
    }

    /**
     * Reto Extra 10: Suma y calcula el promedio numérico de los campos dentro de un array JSON anidado.
     */
    public static double calcularPromedioDeCampo(String json, String rutaArray, String nombreCampoNumerico) {
        // TODO extra: Encuentra el array en "rutaArray" (ej. "tienda.ventas") y calcula el promedio
        // de los valores numéricos correspondientes al campo "nombreCampoNumerico" de sus elementos.
        return 0.0;
    }

}
