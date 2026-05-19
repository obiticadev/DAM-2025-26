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
}

/** Metadato de un endpoint a documentar. */
record EndpointMeta177(String path, String metodo, String descripcion) {
}
