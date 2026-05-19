package com.masterclass.api.b06_webadv;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 060 · Caché HTTP con ETag (304 Not Modified).
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.1)
 * y {@code teoria/00_Fundamentos_HTTP_Web.md} (0.6).
 *
 * <p>El test: 1ª petición sin If-None-Match -> 200 + ETag; 2ª con ese ETag -> 304.
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api").
public class Ej060HttpCacheEtag {

    // Recurso fijo para el test.
    private static final String RECURSO = "{\"v\":1}";

    /**
     * Devuelve el recurso o 304 si el cliente ya tiene la versión vigente.
     *
     * @param ifNoneMatch cabecera If-None-Match (puede ser null)
     * @return 200 con ETag y body, o 304 sin body
     */
    // TODO 2: anota con @GetMapping("/recurso").
    // TODO 3: anota 'ifNoneMatch' con @RequestHeader(value="If-None-Match", required=false).
    public ResponseEntity<String> get(String ifNoneMatch) {
        // TODO 4: calcula el ETag del recurso (p.ej. comillas + hashCode en hex).
        // TODO 5: si ifNoneMatch no es null y coincide con el ETag actual...
        // TODO 6: ...devuelve 304 (ResponseEntity.status(NOT_MODIFIED)) con header ETag y SIN body.
        // TODO 7: si no coincide (o es null), prepara una respuesta 200.
        // TODO 8: añade el header "ETag" con el valor calculado.
        // TODO 9: pon RECURSO como body.
        // TODO 10: devuelve la ResponseEntity adecuada según el caso.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej060HttpCacheEtag().get(null));
    }
}
