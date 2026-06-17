package com.masterclass.api.b17_nosql;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 152 · Modelado embebido vs referencias.
 *
 * <p>Teoría: {@code teoria/17_NoSQL_MongoDB.md} (sección 17.4).
 *
 * <p>Practicamos las dos estrategias: serializar un pedido con sus líneas
 * EMBEBIDAS dentro del documento, y resolver una REFERENCIA por id contra una
 * "colección" de clientes en memoria.
 */
public final class Ej152EmbeddedVsReferences {

    private Ej152EmbeddedVsReferences() {
    }

    /**
     * Estrategia embebida: documento con la lista de líneas dentro.
     *
     * @param pedidoId id del pedido
     * @param lineas   líneas del pedido (subdocumentos)
     * @return mapa {@code { _id, lineas: [ {producto, cantidad}, ... ] }}
     * @throws IllegalArgumentException si pedidoId es null/vacío o lineas es null
     */
    public static Map<String, Object> documentoEmbebido(String pedidoId, List<Linea152> lineas) {
        // TODO 1: si pedidoId es null o vacío -> IllegalArgumentException.
        // TODO 2: si lineas es null -> IllegalArgumentException (lista vacía sí es válida).
        // TODO 3: crea un LinkedHashMap para el documento padre.
        // TODO 4: pon pedidoId bajo la clave "_id".
        // TODO 5: convierte cada Linea152 en un Map {producto, cantidad}.
        // TODO 6: agrupa esos sub-mapas en una List y ponla bajo la clave "lineas".
        // TODO 7: embebido = UNA sola lectura trae el pedido completo (sin joins).
        // TODO 8: advierte (en comentario) que crecer sin límite hincha el documento.
        // TODO 9: devuelve el mapa de forma inmodificable.
        // TODO 10: retorna el documento embebido.
        return Map.of();
    }

    /**
     * Estrategia por referencia: resuelve el clienteId contra la colección.
     *
     * @param clienteId id referenciado dentro del pedido
     * @param clientes   "colección" clientes: id -&gt; nombre
     * @return nombre del cliente referenciado
     * @throws IllegalArgumentException si clientes es null
     * @throws java.util.NoSuchElementException si el id no existe (referencia rota)
     */
    public static String resolverReferencia(String clienteId, Map<String, String> clientes) {
        // TODO 1: si clientes es null -> IllegalArgumentException.
        // TODO 2: si clienteId es null -> NoSuchElementException (referencia inválida).
        // TODO 3: busca clienteId como clave en el mapa 'clientes'.
        // TODO 4: una referencia es solo el id: hay que hacer una 2ª lectura ($lookup).
        // TODO 5: si la clave no existe la referencia está ROTA.
        // TODO 6: en ese caso lanza NoSuchElementException con mensaje claro.
        // TODO 7: si existe, devuelve el nombre asociado.
        // TODO 8: referencia evita duplicar el cliente en cada pedido.
        // TODO 9: el coste es una consulta extra por referencia (vs embebido: 0).
        // TODO 10: retorna el nombre resuelto.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(documentoEmbebido("p1", List.of(new Linea152("café", 2))));
        System.out.println(resolverReferencia("c1", Map.of("c1", "Ana")));
    }

        /**
     * RETO EXTRA 01: Determina si la relacion se modela con documentos embebidos.
     */
    public static boolean esRelacionEmbebida(String typeConfig) {
        // GUÍA: teoría 17.4 (clasifica la estrategia de modelado por su nombre).
        // 1. Si typeConfig es null -> false.
        // 2. Es embebida si la config dice "embedded" (ignora mayúsculas).
        // PISTA: return "embedded".equalsIgnoreCase(typeConfig);
        //    (o typeConfig.toLowerCase().contains("embed")).
        // OJO: el test manda "embedded" y espera true.
        // CULTURA: embebido = hijos dentro del padre, 1 sola lectura (17.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRelacionEmbebida");
    }

    /**
     * RETO EXTRA 02: Determina si la relacion se modela mediante DBRef o ID de referencia.
     */
    public static boolean esRelacionReferenciada(String typeConfig) {
        // GUÍA: el espejo del reto 1 (¿es por referencia?).
        // 1. Si typeConfig es null -> false.
        // 2. Es referenciada si la config dice "referenced".
        // PISTA: return "referenced".equalsIgnoreCase(typeConfig);
        // OJO: el test manda "referenced" y espera true.
        // CULTURA: referencia = guardas el id, exige 2ª lectura/$lookup (17.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRelacionReferenciada");
    }

    /**
     * RETO EXTRA 03: Genera la estructura de un DBRef standard.
     */
    public static String crearReferenciaDbRef(String collection, String id) {
        // GUÍA: teoría 17.4 (un DBRef es {"$ref":"coleccion","$id":"valor"}).
        // 1. Construye esa cadena con collection e id.
        // PISTA: return "{\"$ref\":\"" + collection + "\",\"$id\":\"" + id + "\"}";
        // OJO: el test SOLO exige que .contains("$ref"); aun así dale la forma
        //      completa con $ref y $id para que case con esReferenciaValida (reto 4).
        // CULTURA: este formato { $ref, $id } es el DBRef estándar de Mongo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearReferenciaDbRef");
    }

    /**
     * RETO EXTRA 04: Comprueba la estructura de un DBRef.
     */
    public static boolean esReferenciaValida(String dbRefStr) {
        // GUÍA: un DBRef válido tiene AMBAS claves: $ref y $id.
        // 1. Si dbRefStr es null -> false.
        // 2. Debe contener "$ref" Y "$id" (las dos).
        // PISTA: return dbRefStr != null && dbRefStr.contains("$ref") && dbRefStr.contains("$id");
        // OJO: el test manda {"$ref":"u","$id":"1"} y espera true; con una sola
        //      de las dos claves NO debería valer.
        // CULTURA: es lo que produce crearReferenciaDbRef (reto 3): consistencia.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esReferenciaValida");
    }

    /**
     * RETO EXTRA 05: Advierte si el documento excede el limite teorico seguro (e.g. 16MB) de MongoDB BSON.
     */
    public static boolean esDocumentoGrande(long bytes) {
        // GUÍA: teoría 17.4 (Mongo tiene un tope DURO de 16 MB por documento).
        // 1. Calcula el límite: 16 * 1024 * 1024 bytes.
        // 2. Devuelve true si bytes lo supera.
        // PISTA: return bytes > 16L * 1024 * 1024;  (la L evita overflow de int).
        // OJO: el test manda 20*1024*1024 (20 MB) y espera true (excede los 16).
        // CULTURA: por eso embeber arrays que crecen sin freno es peligroso (17.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDocumentoGrande");
    }

    /**
     * RETO EXTRA 06: Verifica si la lista de referencias tiene ids repetidos.
     */
    public static boolean contieneIdsDuplicados(java.util.List<String> ids) {
        // GUÍA: detectar ids duplicados en una lista de referencias.
        // 1. Si ids es null o vacío -> false (no hay duplicados).
        // 2. Truco clásico: mete todo en un Set; si el Set tiene MENOS
        //    elementos que la lista, hubo repetidos.
        // PISTA: return new java.util.HashSet<>(ids).size() < ids.size();
        // OJO: el test manda ["1","1"] (dos iguales) y espera true.
        // CULTURA: referencias duplicadas inflan el documento y rompen invariantes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneIdsDuplicados");
    }

    /**
     * RETO EXTRA 07: Valida que la profundidad del arbol embebido no sature lecturas (maximo 5 niveles).
     */
    public static boolean esEmbeddedSeguroNivel(int profundidad) {
        // GUÍA: validar la profundidad de anidamiento embebido (máx 5 niveles).
        // 1. profundidad debe ser positiva (>= 1).
        // 2. Y no pasar de 5 (regla del enunciado).
        // PISTA: return profundidad >= 1 && profundidad <= 5;
        // OJO: el test manda 3 y espera true (3 cae dentro de [1, 5]).
        // CULTURA: anidar demasiado complica las queries y el mantenimiento (17.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEmbeddedSeguroNivel");
    }

    /**
     * RETO EXTRA 08: Obtiene la coleccion destino de una referencia DBRef.
     */
    public static String extraerNombreDeColeccionReferenciada(String dbRef) {
        // GUÍA: extraer el valor de "$ref" de un DBRef {"$ref":"users"}.
        // 1. Si dbRef es null o no contiene "$ref" -> "" o null.
        // 2. Localiza el valor entre comillas que sigue a "$ref":.
        // 3. Mismo patrón que extraerCampoObjectId (Ej150 reto 5): partes por
        //    "$ref" y coges lo que hay entre el siguiente par de comillas.
        // PISTA: regex "\"\\$ref\":\"([^\"]+)\"" + Matcher.group(1) (ojo: $ se
        //    escapa en regex como \\$).
        // OJO: el test manda {"$ref":"users"} y espera EXACTAMENTE "users".
        // CULTURA: la colección destino es a la que harías el $lookup.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerNombreDeColeccionReferenciada");
    }

    /**
     * RETO EXTRA 09: Determina si el error apunta a una referencia no resuelta.
     */
    public static boolean esExcepcionReferenciaRota(Throwable t) {
        // GUÍA: clasificar una excepción de "referencia rota" (por mensaje).
        // 1. Si t es null -> false.
        // 2. Criterio: el mensaje contiene "reference" (ignorando case).
        // PISTA: msg != null && msg.toLowerCase().contains("reference");
        //    mismo patrón que esExcepcionMongo (Ej149 reto 8).
        // OJO: el test pasa new IllegalArgumentException("reference") y espera true.
        // CULTURA: una referencia rota es un id que apunta a nada (resolverReferencia
        //    lanza NoSuchElementException en este mismo fichero).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionReferenciaRota");
    }

    /**
     * RETO EXTRA 10: Genera el formato simple de clave ajena referenciada.
     */
    public static String crearJsonReferencia(String id) {
        // GUÍA: una línea — referencia simple solo con $id.
        // PISTA: return "{\"$id\":\"" + id + "\"}";
        // OJO: el test compara EXACTO con {"$id":"5"} (id="5"): solo $id, con el
        //      valor entre comillas, sin $ref ni espacios.
        // CULTURA: versión ligera del DBRef del reto 3 cuando la colección es
        //    implícita (siempre la misma).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearJsonReferencia");
    }

}

/** Subdocumento embebido: una línea de pedido. */
record Linea152(String producto, int cantidad) {
}