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
        // GUÍA: teoría 2.6 (navegar por ruta con path, que no rompe la cadena).
        // 1. Parsea: JsonNode nodo = MAPPER.readTree(json); (dentro de try).
        // 2. Parte la ruta por puntos: ruta.split("\\.")  (el punto es regex -> escápalo).
        // 3. Recorre cada tramo descendiendo con path: for (String t : tramos) nodo = nodo.path(t);
        // 4. Devuelve nodo.asText("")  (texto, o "" si el nodo es missing).
        // PISTA: usa path(), NO get(): si una clave no existe, get devuelve null y el
        //   siguiente get peta con NPE (error nº 5 del bloque); path devuelve nodo missing.
        // OJO: el test pide "usuario.direccion.ciudad" -> "Madrid", "...cp" -> "28001"
        //   (asText convierte el número a texto) y "...pais" (inexistente) -> "" (cadena vacía).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarPorRuta");
    }

    /**
     * Reto Extra 2: Crea un JSON estructurado de forma programática.
     */
    public static String crearJsonProgramatico(String nombre, int edad, java.util.List<String> aficiones) {
        // GUÍA: teoría 2.6 (construir JSON con ObjectNode/ArrayNode).
        // 1. ObjectNode raiz = MAPPER.createObjectNode();
        // 2. raiz.put("nombre", nombre); raiz.put("edad", edad);
        // 3. ArrayNode arr = raiz.putArray("aficiones"); for (String a : aficiones) arr.add(a);
        // 4. Serializa: return MAPPER.writeValueAsString(raiz);  (dentro de try).
        // PISTA: import com.fasterxml.jackson.databind.node.ObjectNode / ArrayNode;
        // OJO: el test comprueba que contiene "nombre":"Ana", "edad":30 (número, sin comillas)
        //   y "aficiones":["cine","viajes"]. put distingue solo: String->con comillas, int->sin.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearJsonProgramatico");
    }

    /**
     * Reto Extra 3: Añade o actualiza dinámicamente un atributo en un JSON.
     */
    public static String inyectarAtributo(String jsonOriginal, String clave, Object valor) {
        // GUÍA: teoría 2.6 (modificar un árbol: castear a ObjectNode y poner).
        // 1. JsonNode root = MAPPER.readTree(jsonOriginal);
        // 2. Castea a ObjectNode (solo él tiene put/set): ObjectNode obj = (ObjectNode) root.
        // 3. Inserta el valor según su tipo. Lo más general:
        //    obj.set(clave, MAPPER.valueToTree(valor));   // valor es Object (true, 99, ...)
        // 4. return MAPPER.writeValueAsString(obj);
        // PISTA: valueToTree convierte cualquier Object al JsonNode adecuado (boolean->true,
        //   int->99) sin que tú mires el tipo.
        // OJO: el test inyecta "activo"=true (-> "activo":true) y "puntos"=99 (-> "puntos":99),
        //   manteniendo el "nombre":"Pedro" original. CUIDADO: put/set NO existen en JsonNode,
        //   solo en ObjectNode -> el cast es obligatorio (error nº 9 del bloque).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inyectarAtributo");
    }

    /**
     * Reto Extra 4: Elimina dinámicamente un atributo en un JSON.
     */
    public static String eliminarAtributo(String jsonOriginal, String clave) {
        // GUÍA: teoría 2.6 (inverso del reto 3: ObjectNode.remove).
        // 1. ObjectNode obj = (ObjectNode) MAPPER.readTree(jsonOriginal);
        // 2. obj.remove(clave);
        // 3. return MAPPER.writeValueAsString(obj);
        // PISTA: remove también es exclusivo de ObjectNode -> castea primero.
        // OJO: el test elimina "edad" y comprueba que el JSON ya NO contiene "edad" pero
        //   sí conserva "nombre":"Pedro" y "activo":true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para eliminarAtributo");
    }

    /**
     * Reto Extra 5: Determina el tipo de datos de un nodo específico por su clave.
     */
    public static String inspeccionarTipoDeNodo(String json, String clave) {
        // GUÍA: teoría 2.6 (inspeccionar el tipo de un nodo con los métodos isXxx()).
        // 1. JsonNode nodo = MAPPER.readTree(json).path(clave);  // path -> missing si no existe.
        // 2. Devuelve la etiqueta según el tipo, comprobando en este orden:
        //    isMissingNode()->"MISSING", isTextual()->"TEXTUAL", isNumber()->"NUMBER",
        //    isArray()->"ARRAY", isObject()->"OBJECT", isBoolean()->"BOOLEAN", isNull()->"NULL".
        // PISTA: comprueba isMissingNode() PRIMERO (con path, un campo ausente es missing, no null).
        // OJO/CUIDADO: el test espera "TEXTUAL" para un string. NO sirve nodo.getNodeType()
        //   .toString(), que devolvería "STRING": tienes que mapear con isTextual() a mano.
        //   El campo "vacio":null debe dar "NULL" y el ausente "inexistente" debe dar "MISSING"
        //   -> por eso path() y no get() (get daría null y no distinguirías NULL de MISSING).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inspeccionarTipoDeNodo");
    }

    /**
     * Reto Extra 6: Extrae una lista de valores asociados a un campo específico desde un array JSON.
     */
    public static java.util.List<String> extraerValoresDeArray(String json, String nombreCampo) {
        // GUÍA: teoría 2.6 (recorrer un array y extraer un campo si existe).
        // 1. JsonNode array = MAPPER.readTree(json);  // el json del test ES un array raíz.
        // 2. Crea una List<String> y recorre el array con for-each (JsonNode es iterable):
        //    for (JsonNode elem : array) { ... }
        // 3. Por cada elemento, comprueba si TIENE el campo: if (elem.has(nombreCampo))
        //    lista.add(elem.get(nombreCampo).asText());
        // PISTA: elem.has(nombreCampo) evita meter valores de los elementos que no lo tienen.
        // OJO: el tercer elemento {"id":3} NO tiene "nombre"; el test espera solo 2 nombres
        //   (Ramon, Maria). Sin el has() añadirías un "" o petarías -> hay que saltarlo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerValoresDeArray");
    }

    /**
     * Reto Extra 7: Fusiona dos JSONs planos o estructurados sobreescribiendo claves.
     */
    public static String fusionarArboles(String jsonA, String jsonB) {
        // GUÍA: teoría 2.6 (fusión profunda con readerForUpdating).
        // 1. Parsea A como árbol base: JsonNode base = MAPPER.readTree(jsonA);
        // 2. Vuelca B SOBRE la base (merge recursivo):
        //    MAPPER.readerForUpdating(base).readValue(jsonB);
        //    -> las claves de B sobreescriben/añaden, y los objetos anidados se fusionan.
        // 3. return MAPPER.writeValueAsString(base);
        // PISTA: readerForUpdating es la forma idiomática de hacer "merge" en Jackson; evita
        //   recorrer los dos árboles a mano.
        // OJO: el test exige fusión PROFUNDA: config.puerto pasa de 80 a 8080 (B) y ADEMÁS
        //   config.tls:true (nuevo en B) debe quedar -> no vale un put plano que reemplazaría
        //   el objeto config entero perdiendo claves. Espera también version "1.1" y activo:true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fusionarArboles");
    }

    /**
     * Reto Extra 8: Busca un campo de forma recursiva a cualquier nivel de profundidad en el JSON.
     */
    public static String buscarClaveRecursiva(String json, String claveBuscada) {
        // GUÍA: teoría 2.6 (Jackson ya trae búsqueda recursiva: findValue).
        // 1. JsonNode root = MAPPER.readTree(json);
        // 2. JsonNode encontrado = root.findValue(claveBuscada);  // busca a cualquier profundidad.
        // 3. return encontrado == null ? null : encontrado.asText();
        // PISTA: findValue recorre TODO el árbol (también dentro de arrays) y devuelve el primer
        //   nodo cuya clave coincida -> no necesitas escribir la recursión a mano.
        // OJO: el test la busca a 4 niveles de profundidad ("encontrado") y también dentro de
        //   un array de objetos ("secreto"->"12345"). findValue cubre ambos casos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarClaveRecursiva");
    }

    /**
     * Reto Extra 9: Extrae un subárbol a partir de una ruta e instáncialo directamente a un DTO.
     */
    public static <T> T convertirSubarbolADto(String json, String rutaSubarbol, Class<T> claseDestino) {
        // GUÍA: teoría 2.6 + 2.2 (navegar a un subárbol y convertirlo a DTO).
        // 1. JsonNode root = MAPPER.readTree(json);
        // 2. Navega hasta el subárbol con path (reutiliza la idea de buscarPorRuta, reto 1):
        //    JsonNode sub = root; for (String t : rutaSubarbol.split("\\.")) sub = sub.path(t);
        // 3. Convierte ese nodo al DTO: return MAPPER.treeToValue(sub, claseDestino);
        // PISTA: treeToValue(nodo, Clase.class) es el puente entre el modelo árbol y el binding
        //   a una clase -> deserializa solo el trozo que te interesa.
        // OJO: el test navega a "responsable" y espera una Persona con nombre "Carlos" y edad 45;
        //   el campo "empresa" del nivel raíz se ignora porque solo conviertes el subárbol.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convertirSubarbolADto");
    }

    /**
     * Reto Extra 10: Suma y calcula el promedio numérico de los campos dentro de un array JSON anidado.
     */
    public static double calcularPromedioDeCampo(String json, String rutaArray, String nombreCampoNumerico) {
        // GUÍA: teoría 2.6 (navegar a un array anidado y promediar un campo).
        // 1. JsonNode root = MAPPER.readTree(json);
        // 2. Navega a la ruta del array con path (split por "."): "tienda.ventas".
        // 3. Si el nodo no es array o está vacío -> return 0 (evita dividir por cero).
        // 4. Recorre el array sumando elem.path(nombreCampoNumerico).asDouble() y cuenta los
        //    elementos; devuelve suma / cantidad.
        // PISTA: asDouble() lee el número del nodo; lleva un double suma y un int n.
        // OJO: el test navega "tienda.ventas", promedia "monto" sobre 10.0/20.0/30.0 y espera
        //   20.0 (con tolerancia 0.0001). Cuida la división: usa double, no int.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularPromedioDeCampo");
    }

}
