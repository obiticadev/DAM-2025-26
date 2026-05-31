package com.masterclass.api.b06_webadv;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 056 · Política CORS.
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.2).
 */
public final class Ej056CorsConfiguration {

    private Ej056CorsConfiguration() {
    }

    /**
     * Calcula las cabeceras CORS a devolver para un origen dado.
     *
     * @param origin          valor de la cabecera Origin de la petición
     * @param allowedOrigins  orígenes permitidos (puede contener "*")
     * @return mapa de cabeceras CORS; vacío si el origen NO está permitido
     */
    public static Map<String, String> corsHeaders(String origin, List<String> allowedOrigins) {
        // TODO 1: si origin es null/blank, no es petición cross-origin -> mapa vacío.
        // TODO 2: si allowedOrigins es null/vacío, nada permitido -> mapa vacío.
        // TODO 3: si allowedOrigins contiene "*", se permite cualquier origen.
        // TODO 4: si no, comprueba si 'origin' está exactamente en la lista.
        // TODO 5: si NO está permitido, devuelve mapa vacío (el navegador bloqueará).
        // TODO 6: si está permitido y se usó "*", Allow-Origin = "*".
        // TODO 7: si está permitido por lista, Allow-Origin = el propio 'origin' (eco).
        // TODO 8: añade "Access-Control-Allow-Methods" = "GET,POST,PUT,DELETE".
        // TODO 9: añade "Access-Control-Allow-Headers" = "Content-Type,Authorization".
        // TODO 10: devuelve el mapa con las 3 cabeceras.
        return Map.of();
    }

    public static void main(String[] args) {
        System.out.println(corsHeaders("https://app.com", List.of("https://app.com")));
    }

    /**
     * Reto Extra 1: Soporte de comodines en subdominios para orígenes permitidos.
     * Comprueba si el origen ('origin') coincide con algún patrón de la lista 'allowedOrigins',
     * la cual puede contener patrones con comodín de subdominio (ej: "https://*.company.com").
     */
    public static boolean pasoExtra01(String origin, List<String> allowedOrigins) {
        // TODO extra: Reto Extra 1: Soporte de comodines en subdominios para orígenes permitidos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * Reto Extra 2: Identificación de peticiones Preflight.
     * Determina si una petición HTTP entrante es un "Preflight Request" de CORS,
     * basándose en que su método sea "OPTIONS" y que incluya cabeceras clave como
     * "Access-Control-Request-Method".
     */
    public static boolean pasoExtra02(String httpMethod, Map<String, String> headers) {
        // TODO extra: Reto Extra 2: Identificación de peticiones Preflight.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * Reto Extra 3: Modificación segura de cabeceras para habilitar credenciales.
     * Añade o modifica las cabeceras CORS del mapa 'currentHeaders' para permitir credenciales
     * ("Access-Control-Allow-Credentials" = "true"). Si 'allowCredentials' es true, recuerda
     * que NO puedes usar "*" en "Access-Control-Allow-Origin"; en ese caso, debes inyectar
     * el 'origin' específico.
     */
    public static Map<String, String> pasoExtra03(Map<String, String> currentHeaders, boolean allowCredentials, String origin) {
        // TODO extra: Reto Extra 3: Modificación segura de cabeceras para habilitar credenciales.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * Reto Extra 4: Cálculo y acotamiento de la cabecera Max-Age.
     * Evalúa el valor solicitado de Max-Age y devuelve el número acotado entre un mínimo de "60"
     * y un máximo de "1800" (30 minutos) por políticas de seguridad del servidor.
     */
    public static String pasoExtra04(String requestMaxAge) {
        // TODO extra: Reto Extra 4: Cálculo y acotamiento de la cabecera Max-Age.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * Reto Extra 5: Validación de cabeceras personalizadas en Preflight.
     * Comprueba si todas las cabeceras solicitadas por el cliente ('requestedHeaders') están
     * autorizadas por el servidor dentro de la lista de permitidas ('allowedHeaders') de forma
     * insensible a mayúsculas/minúsculas.
     */
    public static boolean pasoExtra05(List<String> requestedHeaders, List<String> allowedHeaders) {
        // TODO extra: Reto Extra 5: Validación de cabeceras personalizadas en Preflight.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * Reto Extra 6: Validación de orígenes locales de desarrollo.
     * Comprueba si el origen ('origin') corresponde a una dirección local localhost
     * con puerto dinámico (ej: "http://localhost:3000" o "http://127.0.0.1:8080").
     */
    public static boolean pasoExtra06(String origin) {
        // TODO extra: Reto Extra 6: Validación de orígenes locales de desarrollo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * Reto Extra 7: Configuración de cabeceras expuestas.
     * Añade la cabecera "Access-Control-Expose-Headers" al mapa 'currentHeaders' con los nombres
     * de cabeceras en 'exposedHeaders' para permitir que el frontend del cliente lea cabeceras
     * de respuesta customizadas.
     */
    public static Map<String, String> pasoExtra07(Map<String, String> currentHeaders, List<String> exposedHeaders) {
        // TODO extra: Reto Extra 7: Configuración de cabeceras expuestas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * Reto Extra 8: Saneamiento y validación defensiva de la cabecera Origin.
     * Limpia y sanea la cabecera Origin para evitar ataques de inyección de cabeceras HTTP (CRLF).
     * Si detecta saltos de línea (\r, \n) o caracteres maliciosos, devuelve null.
     */
    public static String pasoExtra08(String origin) {
        // TODO extra: Reto Extra 8: Saneamiento y validación defensiva de la cabecera Origin.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * Reto Extra 9: Comprobación de métodos CORS simples.
     * Evalúa si un método HTTP califica como "CORS Simple Method" (GET, HEAD, POST).
     */
    public static boolean pasoExtra09(String method) {
        // TODO extra: Reto Extra 9: Comprobación de métodos CORS simples.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * Reto Extra 10: Comprobación de cabeceras CORS simples.
     * Evalúa si una cabecera HTTP y su valor califican como "CORS Simple Header"
     * (ej: Accept, Accept-Language, Content-Language, o Content-Type con valores específicos).
     */
    public static boolean pasoExtra10(String headerName, String headerValue) {
        // TODO extra: Reto Extra 10: Comprobación de cabeceras CORS simples.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
