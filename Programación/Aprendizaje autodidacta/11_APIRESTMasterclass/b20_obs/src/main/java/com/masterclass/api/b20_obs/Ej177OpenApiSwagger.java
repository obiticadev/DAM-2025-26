package com.masterclass.api.b20_obs;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 177 · OpenAPI / Swagger con springdoc.
 *
 * <p>Teoria: {@code teoria/20_Observabilidad_Documentacion.md} (seccion 20.1).
 *
 * <p>springdoc-openapi ({@link org.springdoc.core.models.GroupedOpenApi}) genera
 * el documento OpenAPI a partir de los controladores. Aqui modelamos esa
 * generacion como una funcion pura: a partir de metadatos de endpoints
 * construimos un mini documento OpenAPI (Map serializable a JSON) con la
 * estructura {@code paths -> path -> metodo -> operacion}.
 */
public final class Ej177OpenApiSwagger {

    private Ej177OpenApiSwagger() {
    }

    /**
     * Construye un mini documento OpenAPI a partir de metadatos de endpoints.
     *
     * @param titulo    titulo de la API (info.title); no vacio
     * @param version   version de la API (info.version); no vacia
     * @param endpoints lista de endpoints a documentar; no null
     * @return mapa con claves "openapi", "info" y "paths" segun OpenAPI 3
     * @throws IllegalArgumentException si titulo/version vacios o endpoints null
     */
    public static Map<String, Object> construirDocumento(String titulo, String version,
                                                         List<EndpointMeta177> endpoints) {
        // TODO 1: si endpoints es null -> IllegalArgumentException.
        // TODO 2: si titulo es null o en blanco -> IllegalArgumentException.
        // TODO 3: si version es null o en blanco -> IllegalArgumentException.
        // TODO 4: crea el nodo "info" como Map con "title"=titulo y "version"=version.
        // TODO 5: crea un Map "paths" vacio que mantenga orden de insercion.
        // TODO 6: por cada endpoint, obten (o crea) el Map de su 'path'.
        // TODO 7: dentro del path, indexa por metodo HTTP en minusculas (get, post...).
        // TODO 8: cada operacion es un Map con "summary"=descripcion del endpoint.
        // TODO 9: ensambla el documento raiz: "openapi"="3.0.1", "info", "paths".
        // TODO 10: devuelve el documento (no debe ser inmutable vacio).
        return Map.of();
    }

    public static void main(String[] args) {
        System.out.println(construirDocumento("Demo", "1.0",
                List.of(new EndpointMeta177("/users", "GET", "Lista usuarios"))));
    }

        /**
     * RETO EXTRA 01: Comprueba ruta valida.
     */
    public static boolean esRutaValida(String p) {
        // GUÍA: teoría 20.1 (las rutas de OpenAPI empiezan por "/").
        // 1. Si p es null -> false (sin NPE).
        // 2. Una ruta válida no está en blanco y empieza por "/".
        // PISTA: return p != null && !p.isBlank() && p.startsWith("/");
        // OJO: el test solo manda "/users" (true), pero la regla debe cubrir null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRutaValida");
    }

    /**
     * RETO EXTRA 02: Comprueba metodo HTTP.
     */
    public static boolean esMetodoValido(String m) {
        // GUÍA: teoría 20.1 (métodos HTTP del estándar).
        // 1. Si m es null -> false.
        // 2. Normaliza a mayúsculas (trim + toUpperCase) y comprueba pertenencia
        //    a un conjunto de métodos válidos: GET, POST, PUT, PATCH, DELETE...
        // PISTA: Set.of("GET","POST","PUT","PATCH","DELETE","HEAD","OPTIONS")
        //        .contains(m.trim().toUpperCase()).
        // OJO: el test manda "GET" en mayúsculas, pero normalizar deja pasar "get".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMetodoValido");
    }

    /**
     * RETO EXTRA 03: Crea metadato.
     */
    public static EndpointMeta177 crearMeta(String p, String m, String d) {
        // GUÍA: una línea — factoría del record.
        // return new EndpointMeta177(p, m, d);
        // El test solo comprueba que no sea null (assertNotNull). Puedes reusar
        // esRutaValida/esMetodoValido para validar antes si quieres robustez,
        // pero no es obligatorio para pasar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearMeta");
    }

    /**
     * RETO EXTRA 04: Extrae ruta.
     */
    public static String extraerRuta(EndpointMeta177 meta) {
        // GUÍA: una línea — accesor del record (teoría 1.1: los records dan
        // accesores SIN get, se llaman como el campo).
        // return meta.path();
        // (Defensa opcional: si meta es null podrías lanzar IllegalArgumentException.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerRuta");
    }

    /**
     * RETO EXTRA 05: Extrae metodo.
     */
    public static String extraerMetodo(EndpointMeta177 meta) {
        // GUÍA: una línea — accesor del record. Reutiliza el patrón de extraerRuta.
        // return meta.metodo();
        // OJO: el test espera "GET" tal cual (sin tocar mayúsculas) — NO normalices.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerMetodo");
    }

    /**
     * RETO EXTRA 06: Extrae descripcion.
     */
    public static String extraerDescripcion(EndpointMeta177 meta) {
        // GUÍA: una línea — accesor del record.
        // return meta.descripcion();
        // El test manda EndpointMeta177("/a","GET","d") y espera "d".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerDescripcion");
    }

    /**
     * RETO EXTRA 07: Comprueba version semantica.
     */
    public static boolean esVersionSemantica(String v) {
        // GUÍA: versión semántica = MAJOR.MINOR.PATCH, tres números con puntos.
        // 1. Si v es null -> false.
        // 2. Valídalo con una expresión regular de tres grupos de dígitos.
        // PISTA: return v != null && v.matches("\\d+\\.\\d+\\.\\d+");
        // OJO: el test manda "1.0.0" (true). "1.0" (dos partes) NO sería semántica.
        // CULTURA: es el esquema de versionado de Maven, npm y casi todo el mundo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVersionSemantica");
    }

    /**
     * RETO EXTRA 08: Comprueba campos no nulos.
     */
    public static boolean esMetaCompleto(EndpointMeta177 meta) {
        // GUÍA: "completo" = el meta existe y sus tres campos no son null.
        // 1. Si meta es null -> false.
        // 2. Comprueba path, metodo y descripcion != null.
        // PISTA: return meta != null && meta.path() != null
        //        && meta.metodo() != null && meta.descripcion() != null;
        // OJO: el test manda los tres campos rellenos (true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMetaCompleto");
    }

    /**
     * RETO EXTRA 09: Formatea ruta.
     */
    public static String formatearRuta(String p) {
        // GUÍA: limpiar espacios sobrantes de la ruta.
        // 1. Si p es null -> devuelve null (o "" si prefieres; el test no lo prueba).
        // 2. Aplica trim() para quitar los espacios de los extremos.
        // PISTA: return p == null ? null : p.trim();
        // OJO: el test manda " /a " (con espacios) y espera exactamente "/a".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearRuta");
    }

    /**
     * RETO EXTRA 10: Comprueba lista no vacia.
     */
    public static boolean esColeccionEndpoint(java.util.List<EndpointMeta177> list) {
        // GUÍA: lista de endpoints "usable" = no null y no vacía.
        // 1. Si list es null -> false.
        // 2. Comprueba que tenga al menos un elemento.
        // PISTA: return list != null && !list.isEmpty();
        // OJO: el test manda una lista con un EndpointMeta177 (true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esColeccionEndpoint");
    }

}

/** Metadato de un endpoint a documentar. */
record EndpointMeta177(String path, String metodo, String descripcion) {
}
