package com.masterclass.api.b09_err;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 077 · @RestControllerAdvice global.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.1).
 *
 * <p>El test usa MockMvc standalone con este controller + su advice y comprueba
 * que una excepción de negocio se traduce a 404 (no a 500).
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api").
public class Ej077GlobalExceptionHandler {

    public static class RecursoNoEncontrado extends RuntimeException {
        public RecursoNoEncontrado(String m) {
            super(m);
        }
    }

    /**
     * @param id id del recurso
     * @return el recurso o lanza si id no existe (id par = no existe, para el test)
     */
    // TODO 2: anota con @GetMapping("/recurso/{id}") y 'id' con @PathVariable.
    public String obtener(long id) {
        // TODO 3: si id es par, lanza RecursoNoEncontrado("no existe: " + id).
        // TODO 4: si es impar, devuelve "recurso-" + id.
        // TODO 5: NO captures la excepción aquí (que la gestione el advice).
        return "";
    }

    /**
     * Manejador centralizado.
     *
     * @param ex excepción de negocio
     * @return 404 con el mensaje
     */
    // TODO 6: anota con @ExceptionHandler(RecursoNoEncontrado.class).
    public ResponseEntity<String> manejarNoEncontrado(RecursoNoEncontrado ex) {
        // TODO 7: usa ResponseEntity.status(HttpStatus.NOT_FOUND) (404).
        // TODO 8: el cuerpo debe ser ex.getMessage().
        // TODO 9: este handler aplica a TODOS los endpoints del controller (centralizado).
        // TODO 10: devuelve la ResponseEntity 404.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej077GlobalExceptionHandler().obtener(3));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: anota la clase con @RestController y @RequestMapping("/api").
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: anota con @GetMapping("/recurso/{id}") y 'id' con @PathVariable.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si id es par, lanza RecursoNoEncontrado("no existe: " + id).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si es impar, devuelve "recurso-" + id.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: NO captures la excepción aquí (que la gestione el advice).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: anota con @ExceptionHandler(RecursoNoEncontrado.class).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: usa ResponseEntity.status(HttpStatus.NOT_FOUND) (404).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: el cuerpo debe ser ex.getMessage().
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: este handler aplica a TODOS los endpoints del controller (centralizado).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la ResponseEntity 404.
    }

}
