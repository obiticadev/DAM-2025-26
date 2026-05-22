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

        /**
     * RETO EXTRA 01: Valida rol Admin.
     */
    public static boolean esRolAdmin(String r) {
        // TODO extra: RETO EXTRA 01: Valida rol Admin.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRolAdmin");
    }

    /**
     * RETO EXTRA 02: Valida rol User.
     */
    public static boolean esRolUser(String r) {
        // TODO extra: RETO EXTRA 02: Valida rol User.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRolUser");
    }

    /**
     * RETO EXTRA 03: Crea un set con dos roles.
     */
    public static java.util.Set<String> crearSetRoles(String r1, String r2) {
        // TODO extra: RETO EXTRA 03: Crea un set con dos roles.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearSetRoles");
    }

    /**
     * RETO EXTRA 04: Valida si el token esta ausente.
     */
    public static boolean esTokenVacio(String token) {
        // TODO extra: RETO EXTRA 04: Valida si el token esta ausente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTokenVacio");
    }

    /**
     * RETO EXTRA 05: Verifica si se cumple algun rol.
     */
    public static boolean contieneAlgunRol(java.util.Set<String> userRoles, java.util.Set<String> reqRoles) {
        // TODO extra: RETO EXTRA 05: Verifica si se cumple algun rol.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneAlgunRol");
    }

    /**
     * RETO EXTRA 06: Comprueba si hay token.
     */
    public static boolean esAutenticado(boolean tokenPresente) {
        // TODO extra: RETO EXTRA 06: Comprueba si hay token.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAutenticado");
    }

    /**
     * RETO EXTRA 07: Valida si el usuario es Admin.
     */
    public static boolean esAdmin(java.util.Set<String> roles) {
        // TODO extra: RETO EXTRA 07: Valida si el usuario es Admin.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAdmin");
    }

    /**
     * RETO EXTRA 08: Comprueba autenticacion y rol.
     */
    public static boolean tienePermiso(boolean tokenPresente, java.util.Set<String> roles, String rol) {
        // TODO extra: RETO EXTRA 08: Comprueba autenticacion y rol.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tienePermiso");
    }

    /**
     * RETO EXTRA 09: Comprueba si es anonimo.
     */
    public static boolean esAnonimo(boolean tokenPresente) {
        // TODO extra: RETO EXTRA 09: Comprueba si es anonimo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAnonimo");
    }

    /**
     * RETO EXTRA 10: Codigo HTTP de exito.
     */
    public static int codigoAutorizado() {
        // TODO extra: RETO EXTRA 10: Codigo HTTP de exito.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para codigoAutorizado");
    }

}
