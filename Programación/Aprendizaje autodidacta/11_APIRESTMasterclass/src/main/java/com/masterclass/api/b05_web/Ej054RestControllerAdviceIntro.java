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

    // --- MÉTODOS Y DTOs DE RETOS EXTRA ---

    public record ErrorResponseDto(long timestamp, int status, String mensaje, String ruta) {}

    public static class EntidadExistenteException extends RuntimeException {
        public EntidadExistenteException(String m) { super(m); }
    }

    public static class AccesoDenegadoException extends RuntimeException {
        public AccesoDenegadoException(String m) { super(m); }
    }

    public static class ServicioExternoException extends RuntimeException {
        public ServicioExternoException(String m) { super(m); }
    }

    /**
     * Reto Extra 1: Clase centralizada de manejo de excepciones global.
     * TODO extra: anota esta clase interna con @org.springframework.web.bind.annotation.RestControllerAdvice.
     * Puedes restringirla para que sólo aplique a este controlador mediante (assignableTypes = Ej054RestControllerAdviceIntro.class).
     */
    public static class GlobalExceptionHandler {

        /**
         * Reto Extra 2: Manejo de formato o tipos de parámetros incorrectos.
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
        public ResponseEntity<String> manejarFormatoInvalido(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException ex) {
            // TODO extra: devuelve estatus 400 Bad Request con cuerpo "Parametro invalido: " + ex.getName().
            return null;
        }

        /**
         * Reto Extra 3: Manejo de parámetros obligatorios ausentes.
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.web.bind.annotation.MissingServletRequestParameterException.class)
        public ResponseEntity<java.util.Map<String, String>> manejarMissingParam(org.springframework.web.bind.annotation.MissingServletRequestParameterException ex) {
            // TODO extra: devuelve estatus 400 Bad Request con un Map conteniendo la clave "error" y el valor "Parametro ausente: " + ex.getParameterName().
            return null;
        }

        /**
         * Reto Extra 4: Manejo de conflicto por entidad existente.
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(EntidadExistenteException.class)
        public ResponseEntity<String> manejarEntidadExistente(EntidadExistenteException ex) {
            // TODO extra: devuelve estatus 409 Conflict con el mensaje de la excepción.
            return null;
        }

        /**
         * Reto Extra 5: Manejo de errores estructurado con un DTO.
         */
        // TODO extra: define un método para manejar cualquier RuntimeException genérica que no esté mapeada,
        // devolviendo estatus 500 y un cuerpo estructurado ErrorResponseDto.
        public ResponseEntity<ErrorResponseDto> manejarRuntimeExceptionGenerica(RuntimeException ex, jakarta.servlet.http.HttpServletRequest request) {
            // TODO extra: devuelve estatus 500 con una instancia de ErrorResponseDto conteniendo el timestamp actual, 500, el mensaje y request.getRequestURI().
            return null;
        }

        /**
         * Reto Extra 6: Manejo de método HTTP no soportado (405).
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<String> manejarHttpRequestMethodNotSupported(org.springframework.web.HttpRequestMethodNotSupportedException ex) {
            // TODO extra: devuelve estatus 405 Method Not Allowed con cuerpo "Metodo no soportado".
            return null;
        }

        /**
         * Reto Extra 7: Manejo de denegación de acceso (403).
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(AccesoDenegadoException.class)
        public ResponseEntity<String> manejarAccesoDenegado(AccesoDenegadoException ex) {
            // TODO extra: devuelve estatus 403 Forbidden con el cuerpo "Acceso prohibido: " + ex.getMessage().
            return null;
        }

        /**
         * Reto Extra 8: Manejo de tipo de medio no soportado (Content-Type incorrecto) (415).
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.web.HttpMediaTypeNotSupportedException.class)
        public ResponseEntity<String> manejarHttpMediaTypeNotSupported(org.springframework.web.HttpMediaTypeNotSupportedException ex) {
            // TODO extra: devuelve estatus 415 Unsupported Media Type con el cuerpo "Formato no soportado".
            return null;
        }

        /**
         * Reto Extra 9: Manejo de servicio externo caído con cabecera de reintento.
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(ServicioExternoException.class)
        public ResponseEntity<String> manejarServicioExterno(ServicioExternoException ex) {
            // TODO extra: devuelve estatus 503 Service Unavailable, cuerpo "Servicio temporalmente no disponible"
            // y la cabecera "Retry-After" con valor "30".
            return null;
        }
    }

    /**
     * Reto Extra 10: Endpoint auxiliar para simular el lanzamiento de diversas excepciones.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/simular-error")
    public String simularError(@org.springframework.web.bind.annotation.RequestParam("tipo") String tipo) {
        // TODO extra: si tipo es "conflicto", lanza EntidadExistenteException.
        // Si tipo es "denegado", lanza AccesoDenegadoException.
        // Si tipo es "externo", lanza ServicioExternoException.
        // Si tipo es "runtime", lanza una RuntimeException cualquiera.
        return null;
    }

}
