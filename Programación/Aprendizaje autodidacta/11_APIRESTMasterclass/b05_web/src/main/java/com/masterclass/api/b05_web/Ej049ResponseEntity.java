package com.masterclass.api.b05_web;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 049 · Control fino con ResponseEntity (status + headers).
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.3).
 *
 * <p>El test espera {@code GET /api/teapot} con status 418 y cabecera
 * {@code X-Powered-By: masterclass} y body "no coffee".
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api").
public class Ej049ResponseEntity {

    /**
     * Devuelve una respuesta con status y cabecera personalizados.
     *
     * @return ResponseEntity con 418, header X-Powered-By y body "no coffee"
     */
    // TODO 3: anota el método con @GetMapping("/teapot").
    public ResponseEntity<String> teapot() {
        // TODO 4: parte de ResponseEntity.status(418) (I'm a teapot).
        // TODO 5: añade la cabecera con .header("X-Powered-By", "masterclass").
        // TODO 6: el body debe ser exactamente "no coffee".
        // TODO 7: usa .body("no coffee") para fijar el cuerpo.
        // TODO 8: NO uses ResponseEntity.ok() (eso forzaría 200).
        // TODO 9: el status 418 debe llegar literal al cliente.
        // TODO 10: devuelve la ResponseEntity construida.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej049ResponseEntity().teapot());
    }

    // --- MÉTODOS Y RECORD DE RETOS EXTRA ---

    public record ErrorResponse(int codigo, String detalle) {}

    /**
     * Reto Extra 1: Respuesta vacía con estatus HTTP 204 No Content.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/no-content")
    public org.springframework.http.ResponseEntity<Void> respuestaVaciaConStatus() {
        // GUÍA: teoría 5.3 — 204 No Content = "hecho, no hay cuerpo que devolver".
        // 1. Una línea: return ResponseEntity.noContent().build();
        //    build() cierra el builder SIN body (por eso el genérico es <Void>).
        // OJO: el test exige status 204 y body EXACTAMENTE "" (vacío).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 2: Respuesta con cabecera Cache-Control.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/cached")
    public org.springframework.http.ResponseEntity<String> respuestaConCache() {
        // GUÍA: teoría 5.3 — Cache-Control le dice al cliente cuánto puede cachear.
        // 1. return ResponseEntity.ok()
        //        .header("Cache-Control", "max-age=3600")
        //        .body("cacheado");
        // OJO: el test compara Cache-Control == "max-age=3600" (literal) y body
        //   "cacheado". Si usaras CacheControl.maxAge(1, HOURS) el valor saldría
        //   distinto; aquí pon la cadena exacta a mano.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 3: Descarga de archivo adjunto CSV.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/descargar")
    public org.springframework.http.ResponseEntity<String> respuestaDescarga() {
        // GUÍA: teoría 5.3 — Content-Disposition: attachment fuerza la "descarga"
        //   en el navegador; el Content-Type indica el formato.
        // 1. return ResponseEntity.ok()
        //        .header("Content-Disposition", "attachment; filename=\"datos.csv\"")
        //        .contentType(MediaType.parseMediaType("text/csv"))
        //        .body("id,nombre\n1,cafe\n");
        // OJO: el test compara Content-Disposition literal (con las comillas escapadas
        //   alrededor de datos.csv), contentType compatible con text/csv y el body
        //   EXACTO "id,nombre\n1,cafe\n" (incluidos los saltos de línea).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 4: Respuesta condicional basada en ETag (304 Not Modified).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/recurso-etag")
    public org.springframework.http.ResponseEntity<String> respuestaCondicional(
    @org.springframework.web.bind.annotation.RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch) {
        // GUÍA: teoría 5.3 — caché condicional: si el cliente ya tiene la versión
        //   actual (ETag), respondemos 304 y nos ahorramos reenviar el cuerpo.
        // 1. La versión actual del recurso es el ETag "\"v1\"". Si ifNoneMatch es
        //    igual a "\"v1\"" → devuelve 304 (ResponseEntity.status(HttpStatus.NOT_MODIFIED).build()).
        // 2. Si no coincide → 200 con .eTag("\"v1\"") (o header "ETag") y body
        //    "contenido fresco".
        // OJO: el test manda If-None-Match "\"v1\"" → 304 y "\"v2\"" → 200 con ETag
        //   "\"v1\"". Las comillas forman parte del valor del ETag.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 5: Incorporación de Cookie de respuesta en cabeceras.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/cookie")
    public org.springframework.http.ResponseEntity<String> respuestaConCookie() {
        // GUÍA: teoría 5.3 — una cookie viaja en la cabecera Set-Cookie.
        // 1. return ResponseEntity.ok()
        //        .header("Set-Cookie", "session-id=xyz123; Path=/; HttpOnly")
        //        .body("cookie configurada");
        // OJO: el test solo exige que Set-Cookie CONTENGA "session-id=xyz123" y el
        //   body sea "cookie configurada"; los atributos extra (Path, HttpOnly) son
        //   libres. PISTA: en apps reales usarías ResponseCookie.from("session-id","xyz123").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 6: Respuestas alternativas condicionales de error estructurado.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/error-detalle")
    public org.springframework.http.ResponseEntity<?> respuestaErrorDetallado(
            @org.springframework.web.bind.annotation.RequestParam(required = false) Integer code) {
        // GUÍA: teoría 5.3 — el tipo de retorno ResponseEntity<?> permite devolver
        //   un cuerpo distinto según la rama (DTO de error o String).
        // 1. Según 'code' (puede ser null):
        //    - 1 → 400 con body new ErrorResponse(101, "Parametro invalido").
        //    - 2 → 403 con body new ErrorResponse(102, "Acceso prohibido").
        //    - null/otro → 200 con body "ok".
        //    PISTA: ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(...)).
        // OJO: el test verifica $.codigo y $.detalle en los dos errores y body "ok"
        //   cuando no se manda code. ErrorResponse(int,String) ya está declarado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 7: Redirección HTTP (302 Found).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/redirect")
    public org.springframework.http.ResponseEntity<Void> respuestaRedireccion() {
        // GUÍA: teoría 5.3 — un 302 Found redirige poniendo el destino en Location.
        // 1. return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/api/hello")).build();
        //    (import java.net.URI). location(...) fija la cabecera Location.
        // OJO: el test exige status 302 (isFound) y Location == "/api/hello".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 8: Respuesta creada (201) sin URI de localización.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/creado-vacio")
    public org.springframework.http.ResponseEntity<java.util.Map<String, String>> respuestaCreadoSinUri() {
        // GUÍA: teoría 5.3 — no todo 201 trae Location; a veces solo confirmas.
        // 1. return ResponseEntity.status(HttpStatus.CREATED).body(new java.util.HashMap<>());
        //    (un mapa vacío → objeto JSON "{}").
        // OJO: el test exige 201, que NO exista la cabecera Location y que $ esté
        //   VACÍO. No uses created(uri) aquí (añadiría Location).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 9: Respuesta con código de estado personalizado y mapa de errores.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/custom")
    public org.springframework.http.ResponseEntity<java.util.Map<String, String>> respuestaConCustomStatusAndBody() {
        // GUÍA: teoría 5.3 — status arbitrario + cuerpo JSON construido a mano.
        // 1. return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        //        .body(java.util.Map.of("error", "entidad no procesable"));
        //    (Map.of vale aquí porque la clave y el valor no son null).
        // OJO: el test exige 422 y $.error == "entidad no procesable".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 10: Respuesta HTML explícita.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping(value = "/html", produces = org.springframework.http.MediaType.TEXT_HTML_VALUE)
    public org.springframework.http.ResponseEntity<String> respuestaHtml() {
        // GUÍA: teoría 5.2/5.3 — produces = TEXT_HTML_VALUE (ya en la anotación)
        //   marca la salida como HTML, no como texto plano ni JSON.
        // 1. return ResponseEntity.ok("<h1>Hola</h1>");
        //    (el produces de la anotación ya fija el Content-Type text/html).
        // OJO: el test exige Content-Type compatible con text/html y body
        //   "<h1>Hola</h1>". El HTML "de verdad" llega en el bloque 25 (Thymeleaf).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

}
