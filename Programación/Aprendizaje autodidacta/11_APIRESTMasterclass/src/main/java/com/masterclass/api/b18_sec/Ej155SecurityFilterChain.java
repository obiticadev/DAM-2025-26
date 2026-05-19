package com.masterclass.api.b18_sec;

import java.util.Set;

/**
 * Ejercicio 155 · SecurityFilterChain (política de acceso como función pura).
 *
 * <p>Teoría: {@code teoria/18_Seguridad_JWT.md} (sección 18.1).
 *
 * <p>En vez de levantar un {@code SecurityFilterChain} real (Spring), modelamos
 * la POLÍTICA que ese filtro aplicaría como una función decidible en test:
 * dado un método HTTP y una ruta, ¿es pública o requiere autenticación?
 */
public final class Ej155SecurityFilterChain {

    private Ej155SecurityFilterChain() {
    }

    /**
     * Decide si una petición es pública (no requiere autenticación).
     *
     * <p>Reglas: {@code GET} a {@code /public/**} es público; {@code POST}
     * a {@code /auth/login} y {@code /auth/refresh} es público; todo lo demás
     * requiere autenticación.
     *
     * @param metodo método HTTP en mayúsculas (GET, POST, ...); no null/blank
     * @param ruta   ruta solicitada empezando por '/'; no null/blank
     * @return true si la ruta es accesible sin autenticación
     * @throws IllegalArgumentException si metodo o ruta son null/blank
     */
    public static boolean esPublica(String metodo, String ruta) {
        // TODO 1: si metodo es null o blank -> IllegalArgumentException.
        // TODO 2: si ruta es null o blank -> IllegalArgumentException.
        // TODO 3: normaliza el metodo a mayúsculas para comparar de forma estable.
        // TODO 4: define la regla GET sobre el prefijo "/public/" (incluye subrutas).
        // TODO 5: define la regla POST exacta sobre "/auth/login".
        // TODO 6: define la regla POST exacta sobre "/auth/refresh".
        // TODO 7: combina las reglas: si alguna casa -> público (true).
        // TODO 8: por defecto, todo lo no listado es PRIVADO (false) -> deny by default.
        // TODO 9: ten cuidado con rutas con query string: compara solo el path.
        // TODO 10: devuelve la decisión booleana final.
        return false;
    }

    /**
     * Decide si la petición debe ser rechazada con 401 (no autenticado).
     *
     * @param metodo        método HTTP
     * @param ruta          ruta solicitada
     * @param autenticado   true si hay un usuario autenticado en el contexto
     * @return true si procede responder 401 Unauthorized
     */
    public static boolean requiere401(String metodo, String ruta, boolean autenticado) {
        // TODO 1: valida metodo no null/blank.
        // TODO 2: valida ruta no null/blank.
        // TODO 3: si la ruta es pública (reusa esPublica) -> nunca 401.
        // TODO 4: si la ruta es privada y NO está autenticado -> candidato a 401.
        // TODO 5: si está autenticado -> no procede 401 (sería 403 si faltan roles).
        // TODO 6: no confundas 401 (quién eres) con 403 (no tienes permiso).
        // TODO 7: deny by default: ante duda en ruta desconocida, exige auth.
        // TODO 8: documenta que el filtro real haría esto antes del controller.
        // TODO 9: evita filtrar info sensible en el cuerpo del 401.
        // TODO 10: devuelve true solo si privada y no autenticado.
        return false;
    }

    public static void main(String[] args) {
        System.out.println(esPublica("GET", "/public/health"));
        System.out.println(requiere401("GET", "/api/users", false));
    }
}
