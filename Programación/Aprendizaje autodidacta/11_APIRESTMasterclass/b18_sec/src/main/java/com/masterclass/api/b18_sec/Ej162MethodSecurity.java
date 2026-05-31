package com.masterclass.api.b18_sec;

import java.util.Set;

/**
 * Ejercicio 162 · Seguridad a nivel de método (SpEL de @PreAuthorize, pura).
 *
 * <p>Teoría: {@code teoria/18_Seguridad_JWT.md} (sección 18.8).
 *
 * <p>Modelamos expresiones típicas de method security:
 * {@code hasRole}, {@code hasAnyRole} y la regla "propietario o admin"
 * (equivalente a {@code @PostAuthorize("returnObject.owner == principal")}).
 */
public final class Ej162MethodSecurity {

    private Ej162MethodSecurity() {
    }

    /**
     * Equivalente a {@code hasRole(rol)} / {@code hasAnyRole(...)}.
     *
     * @param rolesUsuario roles del principal (no null)
     * @param rolesPermitidos roles que la anotación exige (no null, no vacío)
     * @return true si hay intersección
     * @throws IllegalArgumentException si argumentos inválidos
     */
    public static boolean hasAnyRole(Set<String> rolesUsuario, Set<String> rolesPermitidos) {
        // TODO 1: si rolesUsuario es null -> IllegalArgumentException.
        // TODO 2: si rolesPermitidos es null o vacío -> IllegalArgumentException.
        // TODO 3: normaliza prefijo ROLE_ en ambos conjuntos de forma consistente.
        // TODO 4: calcula la intersección sin mutar las entradas.
        // TODO 5: si la intersección es no vacía -> true.
        // TODO 6: si el usuario no tiene roles -> false.
        // TODO 7: documenta que esto evalúa ANTES de ejecutar el método (@PreAuthorize).
        // TODO 8: no confundas hasRole (uno) con hasAnyRole (varios).
        // TODO 9: respeta deny by default ante ambigüedad.
        // TODO 10: devuelve el booleano.
        return false;
    }

    /**
     * Equivalente a {@code @PostAuthorize}: el usuario accede a un recurso
     * solo si es su propietario o tiene ROLE_ADMIN.
     *
     * @param usuarioActual   username del principal (no null/blank)
     * @param propietario     username dueño del recurso (no null/blank)
     * @param rolesUsuario    roles del principal (no null)
     * @return true si es propietario o admin
     * @throws IllegalArgumentException si argumentos inválidos
     */
    public static boolean propietarioOAdmin(String usuarioActual, String propietario,
                                            Set<String> rolesUsuario) {
        // TODO 1: si usuarioActual es null o blank -> IllegalArgumentException.
        // TODO 2: si propietario es null o blank -> IllegalArgumentException.
        // TODO 3: si rolesUsuario es null -> IllegalArgumentException.
        // TODO 4: compara usuarioActual con propietario (igualdad exacta tras normalizar).
        // TODO 5: si coinciden -> true (es el dueño del recurso).
        // TODO 6: si no, comprueba si rolesUsuario contiene ROLE_ADMIN.
        // TODO 7: admin puede acceder a recursos ajenos (override controlado).
        // TODO 8: combina ambas condiciones con OR.
        // TODO 9: documenta que esto evita IDOR (Insecure Direct Object Reference).
        // TODO 10: devuelve el booleano final.
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasAnyRole(Set.of("ROLE_USER"), Set.of("ROLE_USER")));
        System.out.println(propietarioOAdmin("ana", "ana", Set.of()));
    }

        /**
     * RETO EXTRA 01: Determina si el usuario tiene permiso sobre el recurso (es el dueño o es admin).
     */
    public static boolean esPropietarioOAdmin(String username, String owner, boolean isAdmin) {
        // TODO extra: RETO EXTRA 01: Determina si el usuario tiene permiso sobre el recurso (es el dueño o es admin).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPropietarioOAdmin");
    }

    /**
     * RETO EXTRA 02: Valida sintaxis SpEL basica de Spring Security.
     */
    public static boolean esExpresionSpelValida(String spel) {
        // TODO extra: RETO EXTRA 02: Valida sintaxis SpEL basica de Spring Security.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExpresionSpelValida");
    }

    /**
     * RETO EXTRA 03: Determina si la excepcion es lanzada por la seguridad del metodo (PreAuthorize).
     */
    public static boolean esExcepcionDeMetodo(Throwable t) {
        // TODO extra: RETO EXTRA 03: Determina si la excepcion es lanzada por la seguridad del metodo (PreAuthorize).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeMetodo");
    }

    /**
     * RETO EXTRA 04: Genera el log descriptivo de llamada segura.
     */
    public static String crearLogMetodoSeguro(String m, String user) {
        // TODO extra: RETO EXTRA 04: Genera el log descriptivo de llamada segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearLogMetodoSeguro");
    }

    /**
     * RETO EXTRA 05: Verifica si el usuario es del dominio interno de la organizacion.
     */
    public static boolean esUsuarioInterno(String email) {
        // TODO extra: RETO EXTRA 05: Verifica si el usuario es del dominio interno de la organizacion.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUsuarioInterno");
    }

    /**
     * RETO EXTRA 06: Determina si el parametro del metodo no es nulo.
     */
    public static boolean esArgumentoSeguro(Object arg) {
        // TODO extra: RETO EXTRA 06: Determina si el parametro del metodo no es nulo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esArgumentoSeguro");
    }

    /**
     * RETO EXTRA 07: Indica si el metodo esta exceptuado de validacion previa.
     */
    public static boolean esMetodoPublico(String methodName, java.util.List<String> publicMethods) {
        // TODO extra: RETO EXTRA 07: Indica si el metodo esta exceptuado de validacion previa.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMetodoPublico");
    }

    /**
     * RETO EXTRA 08: Determina si la excepcion es por mala configuracion del PreAuthorize.
     */
    public static boolean esFalloAnotacion(Throwable t) {
        // TODO extra: RETO EXTRA 08: Determina si la excepcion es por mala configuracion del PreAuthorize.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloAnotacion");
    }

    /**
     * RETO EXTRA 09: Comprueba si la expresion SpEL menciona un rol concreto.
     */
    public static boolean esRolePresente(String expression, String rol) {
        // TODO extra: RETO EXTRA 09: Comprueba si la expresion SpEL menciona un rol concreto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRolePresente");
    }

    /**
     * RETO EXTRA 10: Resuelve si es denegacion por defecto o permitAll.
     */
    public static String determinarEstrategiaMetodo(String metadata) {
        // TODO extra: RETO EXTRA 10: Resuelve si es denegacion por defecto o permitAll.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para determinarEstrategiaMetodo");
    }

}