package com.masterclass.api.b19_test;

import java.util.Set;

/**
 * Ejercicio 173 · Testing de seguridad (endpoint protegido como función pura).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.10).
 *
 * <p>Un test de seguridad verifica que un endpoint responde 401/403/200
 * según las credenciales. Modelamos esa decisión como función pura para
 * poder afirmarla con JUnit sin {@code @SpringBootTest} ni filtros reales.
 */
public final class Ej173SecurityTesting {

    private Ej173SecurityTesting() {
    }

    /**
     * Decide el status HTTP de un endpoint protegido que exige cierto rol.
     *
     * @param tokenPresente true si la petición trae credenciales válidas
     * @param rolesUsuario  roles del usuario autenticado (no null; vacío si anónimo)
     * @param rolRequerido  rol exigido por el endpoint (no null/blank)
     * @return 401 sin token, 403 con token sin rol, 200 con rol suficiente
     * @throws IllegalArgumentException si rolesUsuario es null o rolRequerido inválido
     */
    public static int statusEndpointProtegido(boolean tokenPresente, Set<String> rolesUsuario, String rolRequerido) {
        // TODO 1: si rolesUsuario es null -> IllegalArgumentException.
        // TODO 2: si rolRequerido es null o blank -> IllegalArgumentException.
        // TODO 3: si !tokenPresente -> 401 (no autenticado, da igual el rol).
        // TODO 4: con token, comprueba si rolesUsuario contiene rolRequerido.
        // TODO 5: si NO contiene el rol -> 403 (autenticado pero sin permiso).
        // TODO 6: si SÍ contiene el rol -> 200.
        // TODO 7: 401 != 403: no los confundas (distinción de seguridad clave).
        // TODO 8: no muta el Set de roles de entrada.
        // TODO 9: no devuelvas 5xx para decisiones de autorización.
        // TODO 10: devuelve el código HTTP entero.
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(statusEndpointProtegido(true, Set.of("ROLE_ADMIN"), "ROLE_ADMIN"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si rolesUsuario es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si rolRequerido es null o blank -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si !tokenPresente -> 401 (no autenticado, da igual el rol).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: con token, comprueba si rolesUsuario contiene rolRequerido.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si NO contiene el rol -> 403 (autenticado pero sin permiso).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si SÍ contiene el rol -> 200.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: 401 != 403: no los confundas (distinción de seguridad clave).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: no muta el Set de roles de entrada.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: no devuelvas 5xx para decisiones de autorización.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el código HTTP entero.
    }

}
