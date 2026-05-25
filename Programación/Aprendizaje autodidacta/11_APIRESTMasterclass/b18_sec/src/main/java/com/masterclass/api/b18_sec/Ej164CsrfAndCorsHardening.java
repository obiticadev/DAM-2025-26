package com.masterclass.api.b18_sec;

import java.util.Set;

/**
 * Ejercicio 164 · Endurecimiento CSRF y CORS (predicados puros).
 *
 * <p>Teoría: {@code teoria/18_Seguridad_JWT.md} (sección 18.10).
 *
 * <p>Modelamos las decisiones de CSRF y CORS como predicados decidibles:
 * ¿necesita esta petición token CSRF? ¿este Origin está permitido?
 */
public final class Ej164CsrfAndCorsHardening {

    private Ej164CsrfAndCorsHardening() {
    }

    /**
     * Decide si una petición requiere validación CSRF.
     *
     * <p>Con JWT en header {@code Authorization} (stateless) NO se necesita CSRF;
     * con autenticación basada en cookie de sesión, los métodos que mutan
     * (POST/PUT/PATCH/DELETE) SÍ la requieren. GET/HEAD/OPTIONS nunca.
     *
     * @param metodo    método HTTP (no null/blank)
     * @param usaCookieSesion true si la auth va por cookie de sesión
     * @return true si debe exigirse token CSRF
     * @throws IllegalArgumentException si metodo es null/blank
     */
    public static boolean requiereCsrf(String metodo, boolean usaCookieSesion) {
        // TODO 1: si metodo es null o blank -> IllegalArgumentException.
        // TODO 2: normaliza el metodo a mayúsculas.
        // TODO 3: GET, HEAD, OPTIONS y TRACE son "safe methods": nunca CSRF.
        // TODO 4: si NO se usa cookie de sesión (JWT stateless) -> nunca CSRF.
        // TODO 5: si usa cookie de sesión y el método muta -> requiere CSRF.
        // TODO 6: documenta por qué JWT en header es inmune a CSRF clásico.
        // TODO 7: no confundas CSRF (estado del navegador) con CORS (origen).
        // TODO 8: deny by default solo aplica a autorización, aquí razona por método.
        // TODO 9: cubre todos los métodos mutadores (POST/PUT/PATCH/DELETE).
        // TODO 10: devuelve el booleano.
        return false;
    }

    /**
     * Decide si un {@code Origin} está permitido por la política CORS.
     *
     * @param origin           cabecera Origin de la petición (puede ser null)
     * @param origenesPermitidos allowlist exacta (no null)
     * @return true si el origin está explícitamente permitido
     * @throws IllegalArgumentException si origenesPermitidos es null
     */
    public static boolean corsPermitido(String origin, Set<String> origenesPermitidos) {
        // TODO 1: si origenesPermitidos es null -> IllegalArgumentException.
        // TODO 2: si origin es null o blank -> false (petición sin Origin: no es CORS).
        // TODO 3: NUNCA uses "*" junto con credenciales: documenta el riesgo.
        // TODO 4: la comparación debe ser EXACTA contra la allowlist (sin comodines).
        // TODO 5: no normalices a la baja (http vs https importa: distinta origin).
        // TODO 6: el puerto forma parte de la origin: no lo ignores.
        // TODO 7: allowlist vacía -> ningún origin permitido (deny by default).
        // TODO 8: no reflejes el Origin entrante sin validar (vulnerabilidad clásica).
        // TODO 9: documenta que CORS lo aplica el navegador, no protege el servidor.
        // TODO 10: devuelve true solo si origin está en la allowlist.
        return false;
    }

    public static void main(String[] args) {
        System.out.println(requiereCsrf("POST", false));
        System.out.println(corsPermitido("https://app.com", Set.of("https://app.com")));
    }

        /**
     * RETO EXTRA 01: Comprueba que cumpla la estructura standard de token anti-CSRF.
     */
    public static boolean esCsrfTokenValido(String token) {
        // TODO extra: RETO EXTRA 01: Comprueba que cumpla la estructura standard de token anti-CSRF.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCsrfTokenValido");
    }

    /**
     * RETO EXTRA 02: Genera un token anti-CSRF criptograficamente seguro.
     */
    public static String crearCsrfTokenNuevo() {
        // TODO extra: RETO EXTRA 02: Genera un token anti-CSRF criptograficamente seguro.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearCsrfTokenNuevo");
    }

    /**
     * RETO EXTRA 03: Identifica si es el cabezal HTTP anti-CSRF standard.
     */
    public static boolean esHeaderCsrfCorrecto(String headerName) {
        // TODO extra: RETO EXTRA 03: Identifica si es el cabezal HTTP anti-CSRF standard.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esHeaderCsrfCorrecto");
    }

    /**
     * RETO EXTRA 04: Valida si el origen de la peticion esta en la lista blanca de CORS.
     */
    public static boolean esOrigenCorsPermitido(String origin, java.util.List<String> permitidos) {
        // TODO extra: RETO EXTRA 04: Valida si el origen de la peticion esta en la lista blanca de CORS.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esOrigenCorsPermitido");
    }

    /**
     * RETO EXTRA 05: Determina si el verbo HTTP esta permitido por CORS de forma general.
     */
    public static boolean esMetodoCorsPermitido(String method) {
        // TODO extra: RETO EXTRA 05: Determina si el verbo HTTP esta permitido por CORS de forma general.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMetodoCorsPermitido");
    }

    /**
     * RETO EXTRA 06: Determina si la excepcion apunta a ataque de falsificacion CSRF.
     */
    public static boolean esExcepcionCsrf(Throwable t) {
        // TODO extra: RETO EXTRA 06: Determina si la excepcion apunta a ataque de falsificacion CSRF.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionCsrf");
    }

    /**
     * RETO EXTRA 07: Genera la notificacion de origen cruzado prohibido.
     */
    public static String crearRespuestaCorsFallo(String origin) {
        // TODO extra: RETO EXTRA 07: Genera la notificacion de origen cruzado prohibido.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRespuestaCorsFallo");
    }

    /**
     * RETO EXTRA 08: Verifica si el encabezado es de control de CORS.
     */
    public static boolean esHeaderCorsSoportado(String name) {
        // TODO extra: RETO EXTRA 08: Verifica si el encabezado es de control de CORS.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esHeaderCorsSoportado");
    }

    /**
     * RETO EXTRA 09: Verifica si la cookie CSRF tiene atributos Secure, HttpOnly y SameSite=Strict.
     */
    public static boolean esCookieCsrfSegura(String cookieDef) {
        // TODO extra: RETO EXTRA 09: Verifica si la cookie CSRF tiene atributos Secure, HttpOnly y SameSite=Strict.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCookieCsrfSegura");
    }

    /**
     * RETO EXTRA 10: Determina si la excepcion es por mala configuracion del colector de cors.
     */
    public static boolean esFalloConfiguracionCors(Throwable t) {
        // TODO extra: RETO EXTRA 10: Determina si la excepcion es por mala configuracion del colector de cors.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloConfiguracionCors");
    }

}