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
        // TODO extra: devuelve una ResponseEntity vacía (ResponseEntity<Void>) con estatus 204.
        return null;
    }

    /**
     * Reto Extra 2: Respuesta con cabecera Cache-Control.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/cached")
    public org.springframework.http.ResponseEntity<String> respuestaConCache() {
        // TODO extra: devuelve estatus 200, cuerpo "cacheado" y cabecera "Cache-Control: max-age=3600".
        // Utiliza org.springframework.http.CacheControl para construir la cabecera.
        return null;
    }

    /**
     * Reto Extra 3: Descarga de archivo adjunto CSV.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/descargar")
    public org.springframework.http.ResponseEntity<String> respuestaDescarga() {
        // TODO extra: devuelve estatus 200, cuerpo "id,nombre\n1,cafe\n",
        // Content-Type "text/csv" y cabecera "Content-Disposition" con valor "attachment; filename=\"datos.csv\"".
        return null;
    }

    /**
     * Reto Extra 4: Respuesta condicional basada en ETag (304 Not Modified).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/recurso-etag")
    public org.springframework.http.ResponseEntity<String> respuestaCondicional(
            @org.springframework.web.bind.annotation.RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch) {
        // TODO extra: si ifNoneMatch es igual a "\"v1\"", devuelve estatus 304 (Not Modified) sin cuerpo.
        // Si no coincide, devuelve estatus 200 con cuerpo "contenido fresco" y cabecera "ETag: \"v1\"".
        return null;
    }

    /**
     * Reto Extra 5: Incorporación de Cookie de respuesta en cabeceras.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/cookie")
    public org.springframework.http.ResponseEntity<String> respuestaConCookie() {
        // TODO extra: devuelve estatus 200, cuerpo "cookie configurada" y cabecera "Set-Cookie" con "session-id=xyz123; Path=/; HttpOnly".
        return null;
    }

    /**
     * Reto Extra 6: Respuestas alternativas condicionales de error estructurado.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/error-detalle")
    public org.springframework.http.ResponseEntity<?> respuestaErrorDetallado(
            @org.springframework.web.bind.annotation.RequestParam(required = false) Integer code) {
        // TODO extra: si code es 1, devuelve estatus 400 con un cuerpo ErrorResponse(101, "Parametro invalido").
        // Si code es 2, devuelve estatus 403 con un cuerpo ErrorResponse(102, "Acceso prohibido").
        // Si no es ninguno, devuelve estatus 200 con cuerpo "ok".
        return null;
    }

    /**
     * Reto Extra 7: Redirección HTTP (302 Found).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/redirect")
    public org.springframework.http.ResponseEntity<Void> respuestaRedireccion() {
        // TODO extra: devuelve estatus 302 (Found) redirigiendo a la ruta "/api/hello" a través de la cabecera Location.
        return null;
    }

    /**
     * Reto Extra 8: Respuesta creada (201) sin URI de localización.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/creado-vacio")
    public org.springframework.http.ResponseEntity<java.util.Map<String, String>> respuestaCreadoSinUri() {
        // TODO extra: devuelve estatus 201 y un cuerpo vacío (Map.of()) sin cabecera Location.
        return null;
    }

    /**
     * Reto Extra 9: Respuesta con código de estado personalizado y mapa de errores.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/custom")
    public org.springframework.http.ResponseEntity<java.util.Map<String, String>> respuestaConCustomStatusAndBody() {
        // TODO extra: devuelve estatus 422 (UNPROCESSABLE_ENTITY) y un cuerpo conteniendo un Map con clave "error" y valor "entidad no procesable".
        return null;
    }

    /**
     * Reto Extra 10: Respuesta HTML explícita.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping(value = "/html", produces = org.springframework.http.MediaType.TEXT_HTML_VALUE)
    public org.springframework.http.ResponseEntity<String> respuestaHtml() {
        // TODO extra: devuelve estatus 200 y cuerpo "<h1>Hola</h1>".
        return null;
    }

}
