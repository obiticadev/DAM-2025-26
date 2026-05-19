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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: anota la clase con @RestController y @RequestMapping("/api").
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: anota con @GetMapping("/div") y 'a','b' con @RequestParam.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si b == 0, lanza DivisionInvalidaException("division por cero").
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si b != 0, calcula a / b.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: devuelve el resultado como String.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: NO captures aquí la excepción: deja que la gestione el handler.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: anota con @ExceptionHandler(DivisionInvalidaException.class).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: usa ResponseEntity.badRequest() (400).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: el body debe ser ex.getMessage().
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve esa ResponseEntity (centraliza el error, no en el endpoint).
    }

}
