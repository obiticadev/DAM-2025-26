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
        // GUÍA: el token CSRF debe existir y tener longitud mínima.
        // 1. Si token es null/blank -> false.
        // PISTA: return token != null && !token.isBlank() && token.length() >= 4;
        // OJO: el test pasa "csrf123" y espera true. Como en Ej163-01, en producción
        // exigirías más entropía; aquí basta el umbral mínimo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCsrfTokenValido");
    }

    /**
     * RETO EXTRA 02: Genera un token anti-CSRF criptograficamente seguro.
     */
    public static String crearCsrfTokenNuevo() {
        // GUÍA: token aleatorio fuerte (como Ej163-02).
        // PISTA: return java.util.UUID.randomUUID().toString();
        // OJO: el test solo exige assertNotNull. El token CSRF debe ser impredecible
        // (de ahí "criptográficamente seguro"): nunca un valor fijo ni un contador.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearCsrfTokenNuevo");
    }

    /**
     * RETO EXTRA 03: Identifica si es el cabezal HTTP anti-CSRF standard.
     */
    public static boolean esHeaderCsrfCorrecto(String headerName) {
        // GUÍA: nombre de cabecera CSRF estándar.
        // 1. Si headerName es null -> false.
        // PISTA: return "X-CSRF-TOKEN".equalsIgnoreCase(headerName)
        //        || "X-XSRF-TOKEN".equalsIgnoreCase(headerName);
        // OJO: el test pasa "X-CSRF-TOKEN" y espera true. Compara sin distinguir
        // mayúsculas (cabeceras HTTP); admite también la variante X-XSRF-TOKEN.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esHeaderCsrfCorrecto");
    }

    /**
     * RETO EXTRA 04: Valida si el origen de la peticion esta en la lista blanca de CORS.
     */
    public static boolean esOrigenCorsPermitido(String origin, java.util.List<String> permitidos) {
        // GUÍA: allowlist exacta de orígenes (versión List de corsPermitido, 18.10).
        // 1. Si permitidos es null -> false; si origin es null/blank -> false.
        // PISTA: return origin != null && permitidos != null && permitidos.contains(origin);
        // OJO: el test pasa ("https://a.com", List.of("https://a.com")) y espera true.
        // Comparación EXACTA con contains (no startsWith, no comodines): http≠https
        // y el puerto cuenta como parte de la origin.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esOrigenCorsPermitido");
    }

    /**
     * RETO EXTRA 05: Determina si el verbo HTTP esta permitido por CORS de forma general.
     */
    public static boolean esMetodoCorsPermitido(String method) {
        // GUÍA: verbo HTTP dentro del conjunto permitido por CORS.
        // 1. Si method es null -> false.
        // PISTA: return method != null && Set.of("GET","POST","PUT","DELETE",
        //        "PATCH","OPTIONS","HEAD").contains(method.toUpperCase());
        // OJO: el test pasa "GET" y espera true. Normaliza a mayúsculas antes de
        // comparar (el método puede venir en minúsculas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMetodoCorsPermitido");
    }

    /**
     * RETO EXTRA 06: Determina si la excepcion apunta a ataque de falsificacion CSRF.
     */
    public static boolean esExcepcionCsrf(Throwable t) {
        // GUÍA: detectar fallo CSRF por el mensaje.
        // 1. Si t o t.getMessage() son null -> false.
        // PISTA: t != null && t.getMessage() != null
        //        && t.getMessage().toUpperCase().contains("CSRF");
        // OJO: el test pasa SecurityException("CSRF") y espera true. Normaliza a
        // mayúsculas para que "csrf" o "Csrf" también casen.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionCsrf");
    }

    /**
     * RETO EXTRA 07: Genera la notificacion de origen cruzado prohibido.
     */
    public static String crearRespuestaCorsFallo(String origin) {
        // GUÍA: mensaje JSON de origen rechazado.
        // PISTA: return String.format(
        //        "{\"error\":\"CORS\",\"origin\":\"%s\",\"allowed\":false}", origin);
        // OJO: el test pasa "origin" y exige .contains("origin"); incrusta el valor
        // recibido. CUIDADO: no reflejes ese origin en la cabecera real
        // Access-Control-Allow-Origin sin validarlo (vulnerabilidad clásica, 18.10).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRespuestaCorsFallo");
    }

    /**
     * RETO EXTRA 08: Verifica si el encabezado es de control de CORS.
     */
    public static boolean esHeaderCorsSoportado(String name) {
        // GUÍA: cabecera de la familia CORS.
        // 1. Si name es null -> false.
        // PISTA: return name != null
        //        && (name.startsWith("Access-Control-") || name.equalsIgnoreCase("Origin"));
        // OJO: el test pasa "Access-Control-Allow-Origin" y espera true. Todas las
        // cabeceras de respuesta CORS empiezan por "Access-Control-".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esHeaderCorsSoportado");
    }

    /**
     * RETO EXTRA 09: Verifica si la cookie CSRF tiene atributos Secure, HttpOnly y SameSite=Strict.
     */
    public static boolean esCookieCsrfSegura(String cookieDef) {
        // GUÍA: la cookie debe llevar los TRES atributos de seguridad.
        // 1. Si cookieDef es null -> false.
        // PISTA: return cookieDef != null && cookieDef.contains("Secure")
        //        && cookieDef.contains("HttpOnly") && cookieDef.contains("SameSite=Strict");
        // OJO: el test pasa "Secure;HttpOnly;SameSite=Strict" y espera true. Deben
        // estar los tres (AND), no basta con uno. Cada flag cierra un vector:
        // Secure (solo HTTPS), HttpOnly (no accesible por JS), SameSite (anti-CSRF).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCookieCsrfSegura");
    }

    /**
     * RETO EXTRA 10: Determina si la excepcion es por mala configuracion del colector de cors.
     */
    public static boolean esFalloConfiguracionCors(Throwable t) {
        // GUÍA: fallo de configuración CORS por el mensaje.
        // 1. Si t o t.getMessage() son null -> false.
        // PISTA: t != null && t.getMessage() != null
        //        && t.getMessage().toLowerCase().contains("cors");
        // OJO: el test pasa IllegalArgumentException("cors") y espera true. Una mala
        // configuración de CORS es un error del programador (arranque), no un ataque.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloConfiguracionCors");
    }

}