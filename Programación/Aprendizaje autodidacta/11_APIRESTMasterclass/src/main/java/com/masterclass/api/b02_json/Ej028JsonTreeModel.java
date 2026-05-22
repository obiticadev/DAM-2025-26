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
        // TODO extra: Reto Extra 1: Busca un valor textual dentro de un JSON utilizando notación de puntos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarPorRuta");
    }

    /**
     * Reto Extra 2: Crea un JSON estructurado de forma programática.
     */
    public static String crearJsonProgramatico(String nombre, int edad, java.util.List<String> aficiones) {
        // TODO extra: Reto Extra 2: Crea un JSON estructurado de forma programática.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearJsonProgramatico");
    }

    /**
     * Reto Extra 3: Añade o actualiza dinámicamente un atributo en un JSON.
     */
    public static String inyectarAtributo(String jsonOriginal, String clave, Object valor) {
        // TODO extra: Reto Extra 3: Añade o actualiza dinámicamente un atributo en un JSON.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inyectarAtributo");
    }

    /**
     * Reto Extra 4: Elimina dinámicamente un atributo en un JSON.
     */
    public static String eliminarAtributo(String jsonOriginal, String clave) {
        // TODO extra: Reto Extra 4: Elimina dinámicamente un atributo en un JSON.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para eliminarAtributo");
    }

    /**
     * Reto Extra 5: Determina el tipo de datos de un nodo específico por su clave.
     */
    public static String inspeccionarTipoDeNodo(String json, String clave) {
        // TODO extra: Reto Extra 5: Determina el tipo de datos de un nodo específico por su clave.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inspeccionarTipoDeNodo");
    }

    /**
     * Reto Extra 6: Extrae una lista de valores asociados a un campo específico desde un array JSON.
     */
    public static java.util.List<String> extraerValoresDeArray(String json, String nombreCampo) {
        // TODO extra: Reto Extra 6: Extrae una lista de valores asociados a un campo específico desde un array JSON.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerValoresDeArray");
    }

    /**
     * Reto Extra 7: Fusiona dos JSONs planos o estructurados sobreescribiendo claves.
     */
    public static String fusionarArboles(String jsonA, String jsonB) {
        // TODO extra: Reto Extra 7: Fusiona dos JSONs planos o estructurados sobreescribiendo claves.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fusionarArboles");
    }

    /**
     * Reto Extra 8: Busca un campo de forma recursiva a cualquier nivel de profundidad en el JSON.
     */
    public static String buscarClaveRecursiva(String json, String claveBuscada) {
        // TODO extra: Reto Extra 8: Busca un campo de forma recursiva a cualquier nivel de profundidad en el JSON.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarClaveRecursiva");
    }

    /**
     * Reto Extra 9: Extrae un subárbol a partir de una ruta e instáncialo directamente a un DTO.
     */
    public static <T> T convertirSubarbolADto(String json, String rutaSubarbol, Class<T> claseDestino) {
        // TODO extra: Reto Extra 9: Extrae un subárbol a partir de una ruta e instáncialo directamente a un DTO.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convertirSubarbolADto");
    }

    /**
     * Reto Extra 10: Suma y calcula el promedio numérico de los campos dentro de un array JSON anidado.
     */
    public static double calcularPromedioDeCampo(String json, String rutaArray, String nombreCampoNumerico) {
        // TODO extra: Reto Extra 10: Suma y calcula el promedio numérico de los campos dentro de un array JSON anidado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularPromedioDeCampo");
    }

}
