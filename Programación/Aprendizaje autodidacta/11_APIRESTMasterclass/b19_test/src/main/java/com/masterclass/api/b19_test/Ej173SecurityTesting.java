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
        // GUÍA: teoría 19.10 — una línea: return "ROLE_ADMIN".equals(r);
        // El test ("ROLE_ADMIN") espera true. CULTURA: Spring Security prefija
        // los roles con "ROLE_" por convención (@PreAuthorize("hasRole('ADMIN')")
        // compara contra "ROLE_ADMIN" internamente).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRolAdmin");
    }

    /**
     * RETO EXTRA 02: Valida rol User.
     */
    public static boolean esRolUser(String r) {
        // GUÍA: teoría 19.10 — una línea: return "ROLE_USER".equals(r);
        // El test ("ROLE_USER") espera true. Simétrico a esRolAdmin.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRolUser");
    }

    /**
     * RETO EXTRA 03: Crea un set con dos roles.
     */
    public static java.util.Set<String> crearSetRoles(String r1, String r2) {
        // GUÍA: teoría 19.10 — una línea: return Set.of(r1, r2);
        // El test ("A", "B") espera size()==2. OJO: Set.of NO admite duplicados;
        // si r1 y r2 fueran iguales lanzaría IllegalArgumentException — el test
        // pasa dos distintos, así que aquí no hay problema.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearSetRoles");
    }

    /**
     * RETO EXTRA 04: Valida si el token esta ausente.
     */
    public static boolean esTokenVacio(String token) {
        // GUÍA: teoría 19.10 — token ausente = null o en blanco.
        // return token == null || token.isBlank();
        // El test ("") espera true: la cadena vacía cuenta como ausente. Un token
        // así → 401 en statusEndpointProtegido. Comprueba null ANTES de isBlank
        // para no provocar NPE.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTokenVacio");
    }

    /**
     * RETO EXTRA 05: Verifica si se cumple algun rol.
     */
    public static boolean contieneAlgunRol(java.util.Set<String> userRoles, java.util.Set<String> reqRoles) {
        // GUÍA: teoría 19.10 — ¿hay intersección entre los roles del usuario y
        // los exigidos? Sin mutar ninguno:
        //   return userRoles.stream().anyMatch(reqRoles::contains);
        // El test ({A}, {A,B}) espera true (A está en ambos). PISTA: anyMatch
        // corta en cuanto encuentra uno. ⚠ NO uses userRoles.retainAll(reqRoles):
        // mutaría el set de entrada (error común nº 7).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneAlgunRol");
    }

    /**
     * RETO EXTRA 06: Comprueba si hay token.
     */
    public static boolean esAutenticado(boolean tokenPresente) {
        // GUÍA: teoría 19.10 — autenticado = trae token.
        // return tokenPresente;
        // El test (true) espera true. Es el complemento exacto de esAnonimo
        // (reto 9): autenticado == !anónimo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAutenticado");
    }

    /**
     * RETO EXTRA 07: Valida si el usuario es Admin.
     */
    public static boolean esAdmin(java.util.Set<String> roles) {
        // GUÍA: teoría 19.10 — una línea: return roles.contains("ROLE_ADMIN");
        // El test ({ROLE_ADMIN}) espera true. Aquí compruebas pertenencia en el
        // SET (reto 1 comprobaba un único String).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAdmin");
    }

    /**
     * RETO EXTRA 08: Comprueba autenticacion y rol.
     */
    public static boolean tienePermiso(boolean tokenPresente, java.util.Set<String> roles, String rol) {
        // GUÍA: teoría 19.10 — permiso = autenticado Y con el rol.
        // return tokenPresente && roles.contains(rol);
        // El test (true, {ROLE_USER}, "ROLE_USER") espera true. Es justo la
        // condición que separa el 200 del 401/403 en statusEndpointProtegido,
        // pero devuelta como boolean.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tienePermiso");
    }

    /**
     * RETO EXTRA 09: Comprueba si es anonimo.
     */
    public static boolean esAnonimo(boolean tokenPresente) {
        // GUÍA: teoría 19.10 — anónimo = sin token.
        // return !tokenPresente;
        // El test (false) espera true. La negación de esAutenticado (reto 6).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAnonimo");
    }

    /**
     * RETO EXTRA 10: Codigo HTTP de exito.
     */
    public static int codigoAutorizado() {
        // GUÍA: teoría 19.10 — una línea: return 200;
        // El test espera 200 (OK). Es el código que statusEndpointProtegido
        // devuelve cuando el usuario está autenticado Y tiene el rol.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para codigoAutorizado");
    }

}
