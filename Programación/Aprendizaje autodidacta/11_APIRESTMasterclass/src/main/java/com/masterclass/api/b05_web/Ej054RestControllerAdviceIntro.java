package com.masterclass.api.b05_web;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 054 · Introducción al manejo central de errores.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.3).
 *
 * <p>El test espera {@code GET /api/div?a=10&b=0} -> 400 con body "division por cero",
 * y {@code a=10&b=2} -> 200 "5".
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api").
public class Ej054RestControllerAdviceIntro {

    /** Excepción de dominio para entradas inválidas. */
    public static class DivisionInvalidaException extends RuntimeException {
        public DivisionInvalidaException(String m) {
            super(m);
        }
    }

    /**
     * Divide a entre b.
     *
     * @param a dividendo
     * @param b divisor
     * @return el cociente entero como texto
     */
    // TODO 2: anota con @GetMapping("/div") y 'a','b' con @RequestParam.
    public String dividir(int a, int b) {
        // TODO 3: si b == 0, lanza DivisionInvalidaException("division por cero").
        // TODO 4: si b != 0, calcula a / b.
        // TODO 5: devuelve el resultado como String.
        // TODO 6: NO captures aquí la excepción: deja que la gestione el handler.
        return "";
    }

    /**
     * Manejador local de la excepción de dominio.
     *
     * @param ex excepción capturada
     * @return 400 con el mensaje de la excepción como cuerpo
     */
    // TODO 7: anota con @ExceptionHandler(DivisionInvalidaException.class).
    public ResponseEntity<String> manejar(DivisionInvalidaException ex) {
        // TODO 8: usa ResponseEntity.badRequest() (400).
        // TODO 9: el body debe ser ex.getMessage().
        // TODO 10: devuelve esa ResponseEntity (centraliza el error, no en el endpoint).
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej054RestControllerAdviceIntro().dividir(10, 2));
    }
}
