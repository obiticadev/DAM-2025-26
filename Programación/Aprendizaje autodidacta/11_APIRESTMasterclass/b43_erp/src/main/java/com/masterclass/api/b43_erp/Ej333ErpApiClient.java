package com.masterclass.api.b43_erp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

/**
 * Ejercicio 333 · Consumir la API del ERP (Odoo JSON-RPC): construir peticiones y leer respuestas.
 *
 * <p>Teoría: {@code teoria/43_SGE_Integracion.md} (sección 3).
 *
 * <p>Odoo expone su API por <b>JSON-RPC</b>: envías un JSON con {@code {"jsonrpc":"2.0","method":
 * "call","params":{...}}} y recibes {@code {"result": ...}} o {@code {"error": ...}}. El
 * <em>transporte</em> (la llamada HTTP) ya lo sabes hacer (b06, {@code java.net.http}); aquí lo
 * nuevo es <b>construir el cuerpo correcto y parsear la respuesta</b>. Por eso los cores son
 * lógica pura con Jackson (b02), SIN red: deterministas y testeables. La llamada real contra una
 * instancia Odoo se prueba a mano con el MCP de Odoo del entorno (ver teoría).
 */
public final class Ej333ErpApiClient {

    /** ObjectMapper reutilizable (crear uno por llamada es caro; en Spring sería un bean). */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Ej333ErpApiClient() {
    }

    /**
     * Construye el cuerpo JSON-RPC de una llamada a un modelo de Odoo.
     *
     * @param modelo modelo destino (p.ej. {@code "res.partner"})
     * @param metodo método a invocar (p.ej. {@code "search_read"})
     * @return JSON {@code {"jsonrpc":"2.0","method":"call","params":{"model":...,"method":...}}}
     *         o null si modelo/método son null o en blanco
     */
    public static String construirLlamada(String modelo, String metodo) {
        // TODO 1: si modelo o metodo son null/blank -> devuelve null.
        // TODO 2: crea el nodo raíz con MAPPER.createObjectNode().
        // TODO 3: pon "jsonrpc" = "2.0" y "method" = "call".
        // TODO 4: crea un nodo "params" (objeto) hijo del raíz.
        // TODO 5: en params pon "model" = modelo y "method" = metodo.
        // TODO 6: serializa el raíz con MAPPER.writeValueAsString(raiz).
        // TODO 7: writeValueAsString lanza JsonProcessingException (checked): captúrala
        //         y reláznala como RuntimeException (aquí nunca debería ocurrir).
        // TODO 8: devuelve la cadena JSON.
        return null;
    }

    /**
     * Cuenta los registros del array {@code result} de una respuesta JSON-RPC.
     *
     * @param json respuesta JSON-RPC completa
     * @return número de elementos del array {@code result}; -1 si hay error, no hay result,
     *         result no es un array, o el JSON es inválido
     */
    public static int contarRegistrosRespuesta(String json) {
        // TODO 1: si json es null/blank -> -1.
        // TODO 2: parsea con MAPPER.readTree(json) (captura la excepción -> -1).
        // TODO 3: si el nodo tiene "error" (has("error")) -> -1.
        // TODO 4: obtén el nodo "result"; si es null o no es array (isArray()) -> -1.
        // TODO 5: devuelve result.size().
        // TODO 6: recuerda: el centinela -1 distingue "falló" de "0 registros" (lista vacía válida).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(construirLlamada("res.partner", "search_read"));
        System.out.println(contarRegistrosRespuesta("{\"result\":[{\"id\":1},{\"id\":2}]}"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Indica si un código de estado HTTP es de éxito (2xx).
     */
    public static boolean esRespuestaHttpOk(int codigo) {
        // GUÍA: teoría 3 (códigos HTTP, viene de b00). 2xx = éxito.
        // PISTA: return codigo >= 200 && codigo < 300;
        // OJO: el test usa 200 -> true y 404 -> false. El rango es [200, 300).
        // CULTURA: misma familia de códigos que clasificabas en b00 (Ej003).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRespuestaHttpOk");
    }

    /**
     * Reto Extra 2: Construye la URL del endpoint JSON-RPC a partir de la URL base.
     */
    public static String construirUrlEndpoint(String base) {
        // GUÍA: teoría 3 (Odoo escucha el JSON-RPC en /jsonrpc).
        // 1. null/blank -> "".
        // 2. quita una posible barra final de 'base' y añade "/jsonrpc".
        // PISTA: String b = base.endsWith("/") ? base.substring(0, base.length()-1) : base;
        //        return b + "/jsonrpc";
        // OJO: el test pasa "http://localhost:8069/" -> "http://localhost:8069/jsonrpc"
        //   (sin doble barra). Normaliza la barra final.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirUrlEndpoint");
    }

    /**
     * Reto Extra 3: Indica si una respuesta JSON-RPC contiene un error.
     */
    public static boolean tieneError(String json) {
        // GUÍA: teoría 3 (Odoo devuelve {"error":{...}} cuando algo falla).
        // 1. null/blank -> false.
        // 2. parsea con readTree (captura -> false) y comprueba node.has("error").
        // PISTA: return MAPPER.readTree(json).has("error"); (envuelto en try/catch).
        // OJO: el test pasa {"error":{"message":"x"}} -> true y {"result":[]} -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneError");
    }

    /**
     * Reto Extra 4: Extrae el mensaje de error de una respuesta JSON-RPC fallida.
     */
    public static String extraerMensajeError(String json) {
        // GUÍA: teoría 3 (el mensaje vive en error.message).
        // 1. null/blank o sin "error" -> "".
        // 2. navega node.get("error").get("message").asText().
        // PISTA: JsonNode err = MAPPER.readTree(json).get("error");
        //        return err == null ? "" : err.path("message").asText("");
        // OJO: el test espera "Access Denied". Usa path()/comprobaciones de null para no
        //   romper con NullPointerException si falta algún nivel.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerMensajeError");
    }

    /**
     * Reto Extra 5: Extrae el uid (entero) del {@code result} de una respuesta de autenticación.
     */
    public static int extraerUid(String json) {
        // GUÍA: teoría 3 (authenticate devuelve {"result": 7} con el id de usuario).
        // 1. null/blank -> -1.
        // 2. lee result; si es entero (isInt()/isNumber()) -> result.asInt(); si no -> -1.
        // PISTA: JsonNode r = MAPPER.readTree(json).get("result");
        //        return (r != null && r.isNumber()) ? r.asInt() : -1;
        // OJO: el test pasa {"result":7} -> 7. Si el login falla, Odoo devuelve
        //   {"result":false}: tu código debe dar -1, no 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerUid");
    }

    /**
     * Reto Extra 6: Construye un dominio de búsqueda de Odoo {@code [["campo","=","valor"]]}.
     */
    public static String construirDominioIgual(String campo, String valor) {
        // GUÍA: teoría 3 (los "domain" de Odoo son listas de triples [campo, operador, valor]).
        // 1. crea un ArrayNode raíz; dentro, otro ArrayNode con campo, "=", valor.
        // PISTA: ArrayNode raiz = MAPPER.createArrayNode();
        //        ArrayNode triple = raiz.addArray(); triple.add(campo).add("=").add(valor);
        //        return MAPPER.writeValueAsString(raiz);
        // OJO: el test re-parsea y comprueba raiz[0][0]="name", raiz[0][1]="=", raiz[0][2]="Acme".
        //   Es una lista DENTRO de una lista (el dominio admite varios triples).
        // CULTURA: este "domain" es el equivalente Odoo a un WHERE de SQL (b11) o a una
        //   Specification de JPA (b15): filtro declarativo de datos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirDominioIgual");
    }

    /**
     * Reto Extra 7: Construye el objeto de paginación {@code {"limit":L,"offset":O}}.
     */
    public static String construirPaginacion(int limit, int offset) {
        // GUÍA: teoría 3 (paginar lecturas grandes del ERP; idea de b15 Pageable).
        // 1. si limit < 0 o offset < 0 -> IllegalArgumentException.
        // 2. crea un ObjectNode con "limit" y "offset" y serialízalo.
        // PISTA: ObjectNode n = MAPPER.createObjectNode(); n.put("limit", limit).put("offset", offset);
        // OJO: el test re-parsea y comprueba limit=10, offset=20. Y que limit=-1 lanza
        //   IllegalArgumentException (un tamaño de página negativo no tiene sentido).
        // CULTURA: offset = page * size; es la MISMA paginación de Spring Data (b15) y del
        //   skip/limit de streams (b01).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirPaginacion");
    }

    /**
     * Reto Extra 8: Extrae la lista de ids enteros del array {@code result}.
     */
    public static List<Integer> parsearIdsResultado(String json) {
        // GUÍA: teoría 3 (un "search" de Odoo devuelve {"result":[1,2,3]} con solo ids).
        // 1. null/blank, error o result no-array -> List.of().
        // 2. recorre el array result y añade cada nodo.asInt() a una lista.
        // PISTA: for (JsonNode n : result) ids.add(n.asInt());
        // OJO: el test pasa {"result":[1,2,3]} -> [1,2,3]. Una respuesta con error o sin
        //   result debe dar lista vacía, NO null ni excepción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parsearIdsResultado");
    }

    /**
     * Reto Extra 9: Extrae un campo de texto de un registro concreto del array {@code result}.
     */
    public static String extraerCampoDeRegistro(String json, int indice, String campo) {
        // GUÍA: teoría 3 (un "search_read" devuelve result=[{...},{...}] con los registros).
        // 1. null/blank, error, sin result o indice fuera de rango -> "".
        // 2. navega result[indice].get(campo).asText().
        // PISTA: JsonNode result = MAPPER.readTree(json).get("result");
        //        if (result==null || !result.isArray() || indice<0 || indice>=result.size()) return "";
        //        return result.get(indice).path(campo).asText("");
        // OJO: el test lee el campo "name" del registro 1 de un result de 2 -> "Globex".
        //   Comprueba el rango del índice ANTES de acceder (o ArrayIndexOutOfBounds).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerCampoDeRegistro");
    }

    /**
     * Reto Extra 10: Construye la llamada {@code execute_kw} completa de Odoo (con credenciales).
     */
    public static String construirExecuteKw(String db, int uid, String pass, String modelo, String metodo) {
        // GUÍA: teoría 3 (la llamada real de Odoo a un modelo es execute_kw sobre el servicio "object").
        //   params = {"service":"object","method":"execute_kw",
        //             "args":[db, uid, pass, modelo, metodo, []]}
        // 1. crea el envelope {"jsonrpc":"2.0","method":"call","params":{...}} (reutiliza la idea del core).
        // 2. el nodo "args" es un ArrayNode con db, uid, pass, modelo, metodo y un array vacío al final.
        // PISTA: ArrayNode a = params.putArray("args"); a.add(db).add(uid).add(pass).add(modelo).add(metodo).add(MAPPER.createArrayNode());
        // OJO: el test re-parsea y comprueba params.service="object", params.method="execute_kw"
        //   y args[1]=uid (entero), args[3]=modelo. El ORDEN de los args es sagrado en Odoo.
        // CULTURA: esto une todo el bloque: credenciales (b30/b18), modelo (Ej331), method-call
        //   (este ejercicio). Es la petición que de verdad manda el MCP de Odoo por debajo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirExecuteKw");
    }
}
