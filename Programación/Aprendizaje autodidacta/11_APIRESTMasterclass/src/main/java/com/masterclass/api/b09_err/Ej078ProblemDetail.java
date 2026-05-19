package com.masterclass.api.b09_err;

import org.springframework.http.ProblemDetail;
import org.springframework.http.HttpStatus;

/**
 * Ejercicio 078 · ProblemDetail (RFC 7807).
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.2).
 */
public final class Ej078ProblemDetail {

    private Ej078ProblemDetail() {
    }

    /**
     * Construye un ProblemDetail conforme a RFC 7807.
     *
     * @param status   código HTTP
     * @param detail   descripción humana del problema
     * @param instance ruta donde ocurrió (p.ej. "/api/users/7")
     * @return el ProblemDetail poblado
     * @throws IllegalArgumentException si status no es de error (&lt; 400)
     */
    public static ProblemDetail build(int status, String detail, String instance) {
        // TODO 1: valida que status >= 400 (un ProblemDetail describe un error).
        // TODO 2: si no, lanza IllegalArgumentException.
        // TODO 3: crea el ProblemDetail con ProblemDetail.forStatus(status).
        // TODO 4: fija el 'detail' con setDetail(detail).
        // TODO 5: deriva el 'title' a partir de HttpStatus.valueOf(status).getReasonPhrase().
        // TODO 6: fija el title con setTitle(...).
        // TODO 7: fija la 'instance' con setInstance(URI.create(instance)) si no es null.
        // TODO 8: añade una propiedad extra "timestamp" con setProperty (Instant.now()).
        // TODO 9: el 'type' por defecto es about:blank: déjalo así.
        // TODO 10: devuelve el ProblemDetail.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(build(404, "Usuario 7 no existe", "/api/users/7"));
    }
}
