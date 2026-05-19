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
}
