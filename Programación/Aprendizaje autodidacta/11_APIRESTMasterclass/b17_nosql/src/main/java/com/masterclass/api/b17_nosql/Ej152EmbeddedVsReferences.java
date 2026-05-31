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
        // TODO extra: RETO EXTRA 01: Determina si la relacion se modela con documentos embebidos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRelacionEmbebida");
    }

    /**
     * RETO EXTRA 02: Determina si la relacion se modela mediante DBRef o ID de referencia.
     */
    public static boolean esRelacionReferenciada(String typeConfig) {
        // TODO extra: RETO EXTRA 02: Determina si la relacion se modela mediante DBRef o ID de referencia.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRelacionReferenciada");
    }

    /**
     * RETO EXTRA 03: Genera la estructura de un DBRef standard.
     */
    public static String crearReferenciaDbRef(String collection, String id) {
        // TODO extra: RETO EXTRA 03: Genera la estructura de un DBRef standard.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearReferenciaDbRef");
    }

    /**
     * RETO EXTRA 04: Comprueba la estructura de un DBRef.
     */
    public static boolean esReferenciaValida(String dbRefStr) {
        // TODO extra: RETO EXTRA 04: Comprueba la estructura de un DBRef.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esReferenciaValida");
    }

    /**
     * RETO EXTRA 05: Advierte si el documento excede el limite teorico seguro (e.g. 16MB) de MongoDB BSON.
     */
    public static boolean esDocumentoGrande(long bytes) {
        // TODO extra: RETO EXTRA 05: Advierte si el documento excede el limite teorico seguro (e.g. 16MB) de MongoDB BSON.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDocumentoGrande");
    }

    /**
     * RETO EXTRA 06: Verifica si la lista de referencias tiene ids repetidos.
     */
    public static boolean contieneIdsDuplicados(java.util.List<String> ids) {
        // TODO extra: RETO EXTRA 06: Verifica si la lista de referencias tiene ids repetidos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneIdsDuplicados");
    }

    /**
     * RETO EXTRA 07: Valida que la profundidad del arbol embebido no sature lecturas (maximo 5 niveles).
     */
    public static boolean esEmbeddedSeguroNivel(int profundidad) {
        // TODO extra: RETO EXTRA 07: Valida que la profundidad del arbol embebido no sature lecturas (maximo 5 niveles).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEmbeddedSeguroNivel");
    }

    /**
     * RETO EXTRA 08: Obtiene la coleccion destino de una referencia DBRef.
     */
    public static String extraerNombreDeColeccionReferenciada(String dbRef) {
        // TODO extra: RETO EXTRA 08: Obtiene la coleccion destino de una referencia DBRef.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerNombreDeColeccionReferenciada");
    }

    /**
     * RETO EXTRA 09: Determina si el error apunta a una referencia no resuelta.
     */
    public static boolean esExcepcionReferenciaRota(Throwable t) {
        // TODO extra: RETO EXTRA 09: Determina si el error apunta a una referencia no resuelta.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionReferenciaRota");
    }

    /**
     * RETO EXTRA 10: Genera el formato simple de clave ajena referenciada.
     */
    public static String crearJsonReferencia(String id) {
        // TODO extra: RETO EXTRA 10: Genera el formato simple de clave ajena referenciada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearJsonReferencia");
    }

}

/** Subdocumento embebido: una línea de pedido. */
record Linea152(String producto, int cantidad) {
}