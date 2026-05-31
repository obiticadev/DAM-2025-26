package com.masterclass.api.b05_web;

/**
 * Ejercicio 045 · Primer @RestController.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (secciones 5.1 y 5.2).
 *
 * <p>El test usa MockMvc en modo standalone y espera {@code GET /api/hello}
 * devolviendo 200 con el cuerpo exacto {@code "hola"}.
 */
// TODO 1: anota la clase con @org.springframework.web.bind.annotation.RestController.
// TODO 2: anota la clase con @RequestMapping("/api") para prefijar la ruta.
public class Ej045HelloController {

    /**
     * Responde al saludo.
     *
     * @return el texto "hola" con status 200
     */
    // TODO 3: anota el método con @GetMapping("/hello").
    // TODO 4: asegúrate de que la firma siga devolviendo String (cuerpo de la respuesta).
    public String hello() {
        // TODO 5: por defecto un @RestController serializa el return como cuerpo.
        // TODO 6: NO uses System.out: el valor RETORNADO es la respuesta HTTP.
        // TODO 7: el cuerpo esperado por el test es exactamente "hola" (sin comillas extra).
        // TODO 8: el Content-Type por defecto será text/plain para un String: es correcto aquí.
        // TODO 9: no añadas saltos de línea ni espacios alrededor.
        // TODO 10: devuelve "hola".
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Ej045HelloController().hello());
    }

    // --- MÉTODOS Y DTOs DE RETOS EXTRA ---

    public record MensajeJson(String mensaje, long timestamp) {}

    /**
     * Reto Extra 1: Saludo personalizado leyendo cabecera.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/saludo-custom")
    // y recibe el header "X-User-Name" con @org.springframework.web.bind.annotation.RequestHeader("X-User-Name").
    public String saludoPersonalizado(String nombre) {
        // TODO extra: Reto Extra 1: Saludo personalizado leyendo cabecera.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 2: Respuesta serializada a JSON.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/saludo-json")
    public MensajeJson saludoJson() {
        // TODO extra: Reto Extra 2: Respuesta serializada a JSON.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 3: Código de estado declarativo.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/creado")
    // y con @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.CREATED).
    public String endpointCreado() {
        // TODO extra: Reto Extra 3: Código de estado declarativo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 4: Múltiples rutas de alias.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping(value = {
        // TODO extra: Reto Extra 4: Múltiples rutas de alias.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }).
    public String aliasSaludo() {
        // TODO extra: devuelve "alias".
        return null;
    }

    /**
     * Reto Extra 5: Mapeo genérico multimetodo (GET y POST).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.RequestMapping(value = "/echo-method", method = {
        // TODO extra: Reto Extra 5: Mapeo genérico multimetodo (GET y POST).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para RequestMapping");
    }).
    public String echoMethod(jakarta.servlet.http.HttpServletRequest request) {
        // TODO extra: devuelve el método HTTP de la petición (request.getMethod()).
        return null;
    }

    /**
     * Reto Extra 6: Inspección de cabeceras de petición.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/headers")
    public java.util.Map<String, String> cabecerasPersonalizadas(
            @org.springframework.web.bind.annotation.RequestHeader("User-Agent") String userAgent,
            @org.springframework.web.bind.annotation.RequestHeader("Accept") String accept) {
        // TODO extra: Reto Extra 6: Inspección de cabeceras de petición.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 7: Producción de JSON explícito.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping(value = "/json-only", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public String contentTypeJson() {
        // TODO extra: Reto Extra 7: Producción de JSON explícito.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 8: Consumo de texto plano exclusivo.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping(value = "/text-only", consumes = org.springframework.http.MediaType.TEXT_PLAIN_VALUE)
    public int consumesTextPlain(@org.springframework.web.bind.annotation.RequestBody String cuerpo) {
        // TODO extra: Reto Extra 8: Consumo de texto plano exclusivo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 9: Respuesta directa con ResponseEntity I_AM_A_TEAPOT.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/teapot")
    public org.springframework.http.ResponseEntity<String> teapotStatus() {
        // TODO extra: Reto Extra 9: Respuesta directa con ResponseEntity I_AM_A_TEAPOT.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    private final java.util.concurrent.atomic.AtomicInteger contador = new java.util.concurrent.atomic.AtomicInteger(0);

    /**
     * Reto Extra 10: Contador de visitas en memoria.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/contador")
    public int contadorVisitas() {
        // TODO extra: Reto Extra 10: Contador de visitas en memoria.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

}
