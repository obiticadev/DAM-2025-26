package com.masterclass.api.b02_json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * Ejercicio 026 · Objetos anidados y colecciones en JSON.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.2).
 */
public final class Ej026NestedAndCollections {

    public record Linea(String producto, int cantidad) {
    }

    public record Pedido(Long id, List<Linea> lineas) {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Ej026NestedAndCollections() {
    }

    /**
     * Serializa un pedido con sus líneas anidadas.
     *
     * @param p pedido
     * @return JSON con array "lineas"
     */
    public static String toJson(Pedido p) {
        // TODO 1: abre try alrededor de la serialización.
        // TODO 2: usa MAPPER.writeValueAsString(p).
        // TODO 3: el array "lineas" debe anidarse automáticamente (Jackson recorre el record).
        // TODO 4: devuelve la cadena.
        // TODO 5: captura JsonProcessingException y relánzala como RuntimeException.
        return "";
    }

    /**
     * Deserializa una lista de líneas desde un array JSON.
     *
     * @param jsonArray texto como {@code [{"producto":"x","cantidad":2}]}
     * @return lista de Linea
     */
    public static List<Linea> lineasFromJson(String jsonArray) {
        // TODO 6: para colecciones genéricas necesitas un TypeReference (borrado de tipos).
        // TODO 7: crea new TypeReference<List<Linea>>(){}.
        // TODO 8: usa MAPPER.readValue(jsonArray, eseTypeReference).
        // TODO 9: devuelve la lista resultante.
        // TODO 10: captura JsonProcessingException y relánzala como RuntimeException.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(toJson(new Pedido(1L, List.of(new Linea("cafe", 2)))));
    }

    /**
     * Reto Extra 1: Mapeo de colecciones genéricas de mapas.
     * Deserializa un array JSON a una lista de mapas genéricos.
     *
     * @param json JSON array de objetos
     * @return lista de mapas deserializada
     */
    public static List<java.util.Map<String, Object>> mapearListaDeMapas(String json) {
        // GUÍA: teoría 2.4 (colección genérica -> TypeReference, por el borrado de tipos).
        // 1. Dentro de try, usa readValue con un TypeReference que capture el tipo completo:
        //    new TypeReference<List<Map<String,Object>>>(){}.
        // 2. Captura JsonProcessingException -> RuntimeException.
        // PISTA: return MAPPER.readValue(json, new TypeReference<List<Map<String,Object>>>(){});
        // OJO: el test comprueba result.get(0).get("id") == 10, y 10 es un Integer (un número
        //   JSON entero llega como Integer a un Map<String,Object>, error nº 8 del bloque).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearListaDeMapas");
    }

    /**
     * Reto Extra 2: Serialización masiva de colecciones.
     * Convierte una lista de pedidos a un array JSON.
     *
     * @param pedidos lista de pedidos
     * @return JSON conteniendo el array de pedidos
     */
    public static String serializarListaDePedidos(List<Pedido> pedidos) {
        // GUÍA: teoría 2.4 (serializar una lista NO necesita TypeReference; solo leer).
        // 1. Una línea dentro de try: return MAPPER.writeValueAsString(pedidos);
        // PISTA: para SERIALIZAR, Jackson conoce el tipo de cada objeto en runtime, así que
        //   writeValueAsString(lista) basta. El TypeReference solo hace falta al LEER.
        // OJO: el test comprueba que el JSON contiene "Azucar", "Sal" y "lineas" -> es un
        //   array de pedidos, cada uno con su array de líneas anidado automáticamente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarListaDePedidos");
    }

    /**
     * Reto Extra 3: Deserialización y saneamiento de colecciones.
     * Deserializa un pedido y filtra defensivamente omitiendo cualquier línea de producto nula de la lista.
     *
     * @param json JSON del pedido
     * @return objeto Pedido saneado
     */
    public static Pedido deserializarPedidoConNulosIgnorados(String json) {
        // GUÍA: teoría 2.4 + streams (deserializar y luego filtrar líneas null).
        // 1. Deserializa el JSON a Pedido: MAPPER.readValue(json, Pedido.class) dentro de try.
        // 2. Filtra los null de la lista de líneas con un stream:
        //    p.lineas().stream().filter(Objects::nonNull).toList()  (teoría 1.3).
        // 3. Reconstruye un Pedido nuevo con esa lista saneada (el record es inmutable):
        //    new Pedido(p.id(), lineasSaneadas).
        // PISTA: .filter(Objects::nonNull)  ← import java.util.Objects;
        // OJO: el JSON del test mete un null literal en medio del array "lineas"; Jackson lo
        //   deserializa como un elemento null de la lista. El test espera 2 líneas (Agua,
        //   Zumo) e id 100L -> hay que quitar ese null, no dejarlo pasar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarPedidoConNulosIgnorados");
    }

    /**
     * Reto Extra 4: Extracción de mapa clave-valor de cadenas.
     * Deserializa un JSON de propiedades simples a un mapa plano de strings.
     *
     * @param json objeto JSON simple
     * @return mapa de claves y valores textuales
     */
    public static java.util.Map<String, String> extraerMapaClaveValor(String json) {
        // GUÍA: teoría 2.4 (Map tipado -> TypeReference<Map<String,String>>).
        // 1. Una línea dentro de try:
        //    return MAPPER.readValue(json, new TypeReference<Map<String,String>>(){});
        // PISTA: new TypeReference<Map<String,String>>(){}
        // OJO: en el JSON del test TODOS los valores van entre comillas ("8080", "true"),
        //   así que llegan como String. Si alguno fuera un número JSON sin comillas, este
        //   TypeReference fallaría al forzar String -> aquí no pasa, son todos textos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerMapaClaveValor");
    }

    /**
     * Reto Extra 5: Mapeo de tipo complejo anidado.
     * Deserializa un mapa asociativo complejo donde los valores son a su vez listas de DTOs.
     *
     * @param json JSON asociativo (ej. {"categoria1":[{"producto":"café","cantidad":3}]})
     * @return mapa complejo deserializado
     */
    public static java.util.Map<String, List<Linea>> mapearTipoComplejo(String json) {
        // GUÍA: teoría 2.4 (TypeReference anidado: Map cuyos valores son List<Linea>).
        // 1. Una línea dentro de try:
        //    return MAPPER.readValue(json, new TypeReference<Map<String,List<Linea>>>(){});
        // PISTA: new TypeReference<Map<String,List<Linea>>>(){}  ← captura los DOS niveles
        //   de genéricos (Map y List) de golpe; Jackson sabe deserializar cada DTO Linea.
        // OJO: el test espera menu.get("desayuno").size() == 2 y menu.get("cena").get(0)
        //   .producto() == "Sopa". Un único TypeReference resuelve toda la estructura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearTipoComplejo");
    }

    /**
     * Reto Extra 6: Serialización binaria de colecciones.
     * Serializa una lista de líneas directamente a una matriz de bytes.
     *
     * @param lineas lista de líneas de pedido
     * @return array de bytes
     */
    public static byte[] escribirComoArrayDeBytes(List<Linea> lineas) {
        // GUÍA: teoría 2.4/2.2 (writeValueAsBytes también vale para colecciones).
        // 1. Una línea dentro de try: return MAPPER.writeValueAsBytes(lineas);
        // PISTA: writeValueAsBytes(lineas)
        // OJO: el test reconstruye un String con los bytes y comprueba que contiene "Galletas".
        //   Serializar a bytes no necesita TypeReference (solo leer lo necesita).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirComoArrayDeBytes");
    }

    /**
     * Reto Extra 7: Deserialización binaria de colecciones.
     * Deserializa una lista de líneas de pedido a partir de una matriz de bytes.
     *
     * @param bytes array de bytes
     * @return lista de líneas deserializada
     */
    public static List<Linea> leerColeccionDeBytes(byte[] bytes) {
        // GUÍA: teoría 2.4 (al LEER bytes a una lista tipada, vuelve el TypeReference).
        // 1. Una línea dentro de try:
        //    return MAPPER.readValue(bytes, new TypeReference<List<Linea>>(){});
        // 2. readValue(byte[], ...) lanza IOException -> captura IOException, no solo
        //    JsonProcessingException.
        // PISTA: new TypeReference<List<Linea>>(){}
        // OJO: el test pasa los bytes de [{"producto":"Leche","cantidad":3}] y espera 1
        //   línea con producto "Leche" y cantidad 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerColeccionDeBytes");
    }

    /**
     * Reto Extra 8: Recuento analítico sobre colecciones anidadas.
     * Suma las cantidades totales de todos los productos incluidos en las líneas del pedido.
     *
     * @param p pedido
     * @return suma acumulada de las cantidades de productos
     */
    public static int contarTotalProductos(Pedido p) {
        // GUÍA: streams numéricos (teoría 1.3/1.4) — esto no es Jackson, es agregar.
        // 1. Recorre las líneas y suma sus cantidades:
        //    p.lineas().stream().mapToInt(Linea::cantidad).sum().
        // PISTA: return p.lineas().stream().mapToInt(Linea::cantidad).sum();
        // OJO: el test suma 3+7+2 y espera 12. mapToInt da un IntStream con .sum() directo,
        //   más limpio que un reduce manual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarTotalProductos");
    }

    /**
     * Reto Extra 9: Serialización filtrada.
     * Genera un JSON de Pedido donde se omiten por completo las líneas con cantidades no válidas (menor o igual a cero).
     *
     * @param p pedido original
     * @return JSON del pedido filtrado
     */
    public static String serializarSoloLineasValidas(Pedido p) {
        // GUÍA: streams + teoría 2.4 (filtrar líneas inválidas, reconstruir y serializar).
        // 1. Filtra las líneas con cantidad > 0:
        //    p.lineas().stream().filter(l -> l.cantidad() > 0).toList().
        // 2. Construye un Pedido nuevo con esa lista (record inmutable): new Pedido(p.id(), validas).
        // 3. Serialízalo: reutiliza toJson del propio fichero o MAPPER.writeValueAsString.
        // PISTA: return toJson(new Pedido(p.id(), validas));
        // OJO: el test pone cantidades 5, 0, -2, 3; espera que el JSON contenga "Valido" y
        //   "Valido2" pero NO "Invalido1" (cantidad 0) ni "Invalido2" (cantidad -2). El filtro
        //   es ESTRICTAMENTE mayor que cero (0 también se descarta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarSoloLineasValidas");
    }

    /**
     * Reto Extra 10: Deserialización de mapa dinámico heterogéneo.
     * Deserializa un JSON de estructura desconocida a un mapa genérico de objetos.
     *
     * @param json cadena JSON
     * @return mapa dinámico heterogéneo
     */
    public static java.util.Map<String, Object> deserializarMapGenerico(String json) {
        // GUÍA: teoría 2.4 (estructura desconocida -> Map<String,Object>).
        // 1. Una línea dentro de try:
        //    return MAPPER.readValue(json, new TypeReference<Map<String,Object>>(){});
        // PISTA: new TypeReference<Map<String,Object>>(){}
        // OJO: el test comprueba los tipos Java inferidos: "Juan" -> String, 25 -> Integer,
        //   true -> Boolean, y "intereses" -> List. Jackson elige el tipo Java según el tipo
        //   JSON de cada valor (tabla de la teoría 2.4). Por eso assertEquals(25, ...) compara
        //   contra un Integer, no un Long.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarMapGenerico");
    }

}
