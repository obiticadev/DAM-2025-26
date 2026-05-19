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
}
