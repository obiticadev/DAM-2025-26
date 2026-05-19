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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si pedidoId es null o vacío -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si lineas es null -> IllegalArgumentException (lista vacía sí es válida).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: crea un LinkedHashMap para el documento padre.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: pon pedidoId bajo la clave "_id".
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: convierte cada Linea152 en un Map {producto, cantidad}.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: agrupa esos sub-mapas en una List y ponla bajo la clave "lineas".
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: embebido = UNA sola lectura trae el pedido completo (sin joins).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: advierte (en comentario) que crecer sin límite hincha el documento.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: devuelve el mapa de forma inmodificable.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: retorna el documento embebido.
    }

}

/** Subdocumento embebido: una línea de pedido. */
record Linea152(String producto, int cantidad) {
}
