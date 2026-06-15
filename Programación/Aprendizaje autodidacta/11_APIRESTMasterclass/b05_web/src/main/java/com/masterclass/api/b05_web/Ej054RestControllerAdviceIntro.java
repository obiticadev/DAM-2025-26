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
     *
     * <p>GUÍA: teoría 5.3 — un @RestControllerAdvice es una clase que recoge los
     * @ExceptionHandler de TODOS los controladores en un único sitio (deja de
     * repetir el try/catch en cada endpoint).
     * <ol>
     *   <li>Anota ESTA clase interna con
     *       {@code @org.springframework.web.bind.annotation.RestControllerAdvice}.
     *       Puedes acotarla a este controlador con
     *       {@code (assignableTypes = Ej054RestControllerAdviceIntro.class)}.</li>
     *   <li>Dentro viven los handlers de los retos 2 a 9 (cada uno con su
     *       {@code @ExceptionHandler}).</li>
     * </ol>
     * OJO: el test {@code globalAdviceAnnotation} comprueba con reflexión que la
     * anotación @RestControllerAdvice está PRESENTE; y monta el MockMvc con
     * {@code .setControllerAdvice(new GlobalExceptionHandler())}.
     */
    // TODO extra: anota la clase con @RestControllerAdvice (ver guía de arriba).
    public static class GlobalExceptionHandler {

        /**
         * Reto Extra 2: Manejo de formato o tipos de parámetros incorrectos.
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
        public ResponseEntity<String> manejarFormatoInvalido(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException ex) {
            // GUÍA: teoría 5.3 — esta excepción la lanza Spring cuando un parámetro
            //   no se puede convertir al tipo esperado (p.ej. "abc" para un int).
            // 1. Devuelve 400 con body "Parametro invalido: " + ex.getName().
            //    PISTA: ResponseEntity.badRequest().body("Parametro invalido: " + ex.getName()).
            // OJO: el test pega a /api/div?a=no-es-un-numero (a es int) y espera que
            //   el body CONTENGA "Parametro invalido: a". ex.getName() da el nombre
            //   del parámetro que falló ("a").
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ExceptionHandler");
        }

        /**
         * Reto Extra 3: Manejo de parámetros obligatorios ausentes.
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
        public ResponseEntity<java.util.Map<String, String>> manejarMissingParam(org.springframework.web.bind.MissingServletRequestParameterException ex) {
            // GUÍA: teoría 5.3 — se lanza cuando falta un @RequestParam obligatorio.
            // 1. Devuelve 400 con un Map "error" = "Parametro ausente: " + ex.getParameterName().
            //    PISTA: ResponseEntity.badRequest().body(Map.of("error", "Parametro ausente: " + ex.getParameterName())).
            // OJO: el test pega a /api/div sin 'a' y espera $.error que CONTENGA
            //   "Parametro ausente: a".
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ExceptionHandler");
        }

        /**
         * Reto Extra 4: Manejo de conflicto por entidad existente.
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(EntidadExistenteException.class)
        public ResponseEntity<String> manejarEntidadExistente(EntidadExistenteException ex) {
            // GUÍA: teoría 5.3 — excepción de NEGOCIO propia → 409 Conflict.
            // 1. ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()).
            //    (import org.springframework.http.HttpStatus).
            // OJO: el test lanza esta excepción vía /api/simular-error?tipo=conflicto
            //   (reto 10) y espera 409.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ExceptionHandler");
        }

        /**
         * Reto Extra 5: Manejo de errores estructurado con un DTO.
         */
        // TODO extra: anota con @ExceptionHandler(RuntimeException.class) para capturar
        //   cualquier RuntimeException NO cubierta por un handler más específico.
        public ResponseEntity<ErrorResponseDto> manejarRuntimeExceptionGenerica(RuntimeException ex, jakarta.servlet.http.HttpServletRequest request) {
            // GUÍA: teoría 5.3 — el "cajón de sastre": 500 con cuerpo de error
            //   ESTRUCTURADO (ErrorResponseDto) en vez de un texto suelto.
            // 1. Devuelve 500 con new ErrorResponseDto(System.currentTimeMillis(),
            //    500, ex.getMessage(), request.getRequestURI()).
            //    PISTA: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(...).
            // OJO: el test (tipo=runtime) espera status 500, $.status==500,
            //   $.mensaje existe y $.ruta=="/api/simular-error" (de getRequestURI()).
            // CUIDADO: como las otras excepciones (Entidad/Acceso/Externo) extienden
            //   RuntimeException, este handler genérico SOLO actúa cuando no hay uno
            //   más específico; Spring elige siempre el más concreto.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para manejarRuntimeExceptionGenerica");
        }

        /**
         * Reto Extra 6: Manejo de método HTTP no soportado (405).
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<String> manejarHttpRequestMethodNotSupported(org.springframework.web.HttpRequestMethodNotSupportedException ex) {
            // GUÍA: teoría 5.3 — Spring lanza esto si el verbo no está mapeado (POST
            //   a un endpoint solo-GET) → 405 Method Not Allowed.
            // 1. ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Metodo no soportado").
            // OJO: el test hace POST a /api/div (que es GET) y espera 405 con body
            //   EXACTO "Metodo no soportado".
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ExceptionHandler");
        }

        /**
         * Reto Extra 7: Manejo de denegación de acceso (403).
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(AccesoDenegadoException.class)
        public ResponseEntity<String> manejarAccesoDenegado(AccesoDenegadoException ex) {
            // GUÍA: teoría 5.3 — excepción de negocio → 403 Forbidden.
            // 1. ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso prohibido: " + ex.getMessage()).
            // OJO: el test (tipo=denegado) espera 403 y body que CONTENGA
            //   "Acceso prohibido:".
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ExceptionHandler");
        }

        /**
         * Reto Extra 8: Manejo de tipo de medio no soportado (Content-Type incorrecto) (415).
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.web.HttpMediaTypeNotSupportedException.class)
        public ResponseEntity<String> manejarHttpMediaTypeNotSupported(org.springframework.web.HttpMediaTypeNotSupportedException ex) {
            // GUÍA: teoría 5.3 — Content-Type de entrada no aceptado → 415.
            // 1. ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Formato no soportado").
            // OJO: el test llama DIRECTAMENTE handler.manejarHttpMediaTypeNotSupported(...)
            //   y exige getStatusCode()==415 y getBody()=="Formato no soportado" (exacto).
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ExceptionHandler");
        }

        /**
         * Reto Extra 9: Manejo de servicio externo caído con cabecera de reintento.
         */
        // TODO extra: anota con @org.springframework.web.bind.annotation.ExceptionHandler(ServicioExternoException.class)
        public ResponseEntity<String> manejarServicioExterno(ServicioExternoException ex) {
            // GUÍA: teoría 5.3 — 503 Service Unavailable + cabecera Retry-After dice
            //   al cliente cuántos segundos esperar antes de reintentar.
            // 1. ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            //        .header("Retry-After", "30")
            //        .body("Servicio temporalmente no disponible").
            // OJO: el test (tipo=externo) espera 503, header Retry-After=="30" y body
            //   EXACTO "Servicio temporalmente no disponible".
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ExceptionHandler");
        }
    }

    /**
     * Reto Extra 10: Endpoint auxiliar para simular el lanzamiento de diversas excepciones.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/simular-error")
    public String simularError(@org.springframework.web.bind.annotation.RequestParam("tipo") String tipo) {
        // GUÍA: teoría 5.3 — este endpoint NO captura nada: lanza la excepción según
        //   'tipo' y deja que el @RestControllerAdvice (retos 2-9) la traduzca a HTTP.
        // 1. switch (tipo):
        //    "conflicto" -> throw new EntidadExistenteException("...")   (→ 409, reto 4)
        //    "denegado"  -> throw new AccesoDenegadoException("...")     (→ 403, reto 7)
        //    "externo"   -> throw new ServicioExternoException("...")    (→ 503, reto 9)
        //    "runtime"   -> throw new RuntimeException("...")            (→ 500, reto 5)
        //    (otro)      -> return "ok" (o lo que prefieras).
        // OJO: el test ejercita los cuatro tipos y comprueba 409/403/503/500. El
        //   mensaje de cada excepción es libre salvo donde el test mira "containsString".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

}
