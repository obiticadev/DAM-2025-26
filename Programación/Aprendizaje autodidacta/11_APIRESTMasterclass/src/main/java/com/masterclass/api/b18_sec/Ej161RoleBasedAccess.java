package com.masterclass.api.b18_sec;

import java.util.Map;
import java.util.Set;

/**
 * Ejercicio 161 · Acceso basado en roles ({@code @PreAuthorize} como función pura).
 *
 * <p>Teoría: {@code teoria/18_Seguridad_JWT.md} (sección 18.7).
 *
 * <p>Modelamos la política de autorización por ruta+rol como una función
 * decidible {@code permitido(metodo, ruta, roles)} en lugar de anotaciones.
 */
public final class Ej161RoleBasedAccess {

    private Ej161RoleBasedAccess() {
    }

    /**
     * Decide si un conjunto de roles permite acceder a una ruta protegida.
     *
     * <p>Reglas: {@code /admin/**} exige {@code ROLE_ADMIN}; {@code /api/**}
     * exige {@code ROLE_USER} o {@code ROLE_ADMIN}; resto deny by default.
     *
     * @param metodo método HTTP (no null/blank)
     * @param ruta   ruta solicitada (no null/blank)
     * @param roles  roles del usuario autenticado (no null)
     * @return true si los roles autorizan la ruta
     * @throws IllegalArgumentException si metodo/ruta/roles son inválidos
     */
    public static boolean permitido(String metodo, String ruta, Set<String> roles) {
        // TODO 1: si metodo es null o blank -> IllegalArgumentException.
        // TODO 2: si ruta es null o blank -> IllegalArgumentException.
        // TODO 3: si roles es null -> IllegalArgumentException (usa Set.of() para anónimo).
        // TODO 4: regla "/admin/": requiere que roles contenga ROLE_ADMIN.
        // TODO 5: regla "/api/": requiere ROLE_USER o ROLE_ADMIN.
        // TODO 6: normaliza el prefijo ROLE_ para comparar de forma consistente.
        // TODO 7: si la ruta no casa ninguna regla conocida -> false (deny by default).
        // TODO 8: ROLE_ADMIN no implica automáticamente todo: respeta la regla por ruta.
        // TODO 9: no muta el Set de entrada.
        // TODO 10: devuelve la decisión booleana.
        return false;
    }

    /**
     * Traduce la decisión de acceso a un código HTTP semántico.
     *
     * @param autenticado true si hay usuario autenticado
     * @param autorizado  resultado de {@link #permitido}
     * @return 200 si OK, 401 si no autenticado, 403 si autenticado sin permiso
     */
    public static int statusHttp(boolean autenticado, boolean autorizado) {
        // TODO 1: si no autenticado -> 401 (Unauthorized: no sé quién eres).
        // TODO 2: si autenticado pero no autorizado -> 403 (Forbidden: sé quién eres, no puedes).
        // TODO 3: si autenticado y autorizado -> 200.
        // TODO 4: nunca devuelvas 200 si no autorizado.
        // TODO 5: 401 vs 403 es una distinción de seguridad importante: documenta.
        // TODO 6: no filtres en el cuerpo info sensible (existencia de recursos).
        // TODO 7: este mapeo lo haría el ExceptionTranslationFilter de Spring.
        // TODO 8: cubre las 3 ramas exhaustivamente.
        // TODO 9: evita códigos 5xx para decisiones de autorización.
        // TODO 10: devuelve el código entero.
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(permitido("GET", "/admin/users", Set.of("ROLE_ADMIN")));
        System.out.println(statusHttp(true, false));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si metodo es null o blank -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si ruta es null o blank -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si roles es null -> IllegalArgumentException (usa Set.of() para anónimo).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: regla "/admin/": requiere que roles contenga ROLE_ADMIN.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: regla "/api/": requiere ROLE_USER o ROLE_ADMIN.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: normaliza el prefijo ROLE_ para comparar de forma consistente.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si la ruta no casa ninguna regla conocida -> false (deny by default).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: ROLE_ADMIN no implica automáticamente todo: respeta la regla por ruta.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: no muta el Set de entrada.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la decisión booleana.
    }

}
