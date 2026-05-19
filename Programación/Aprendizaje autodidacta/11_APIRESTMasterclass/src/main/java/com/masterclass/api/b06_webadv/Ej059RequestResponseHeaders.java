package com.masterclass.api.b06_webadv;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 059 · Lectura y escritura de cabeceras.
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.5).
 *
 * <p>El test envía {@code GET /api/trace} con header {@code X-Request-Id: abc}
 * y espera 200, body "ok" y header de respuesta {@code X-Correlation-Id: abc}.
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api").
public class Ej059RequestResponseHeaders {

    /**
     * Lee un header de la petición y lo refleja como header de respuesta.
     *
     * @param requestId valor de X-Request-Id (opcional)
     * @return ResponseEntity 200 con body "ok" y X-Correlation-Id
     */
    // TODO 2: anota el método con @GetMapping("/trace").
    // TODO 3: anota 'requestId' con @RequestHeader(value="X-Request-Id", required=false).
    public ResponseEntity<String> trace(String requestId) {
        // TODO 4: si requestId es null, genera uno (p.ej. "gen-" + algo) como fallback.
        // TODO 5: ese id será el "correlation id" que devolvemos.
        // TODO 6: parte de ResponseEntity.ok().
        // TODO 7: añade header "X-Correlation-Id" con el valor resuelto.
        // TODO 8: el body debe ser exactamente "ok".
        // TODO 9: usa .body("ok").
        // TODO 10: devuelve la ResponseEntity.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej059RequestResponseHeaders().trace("abc"));
    }
}
