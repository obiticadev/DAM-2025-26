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
        // GUÍA: versión simplificada de propietarioOAdmin (teoría 18.8, anti-IDOR).
        // PISTA: return isAdmin || (username != null && username.equals(owner));
        // OJO: el test pasa ("ada","ada",false) y espera true: no es admin, pero ES
        // el propietario. Aquí el rol admin ya viene resuelto como boolean, así que
        // no necesitas el Set de roles. OR entre "soy admin" y "soy el dueño".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPropietarioOAdmin");
    }

    /**
     * RETO EXTRA 02: Valida sintaxis SpEL basica de Spring Security.
     */
    public static boolean esExpresionSpelValida(String spel) {
        // GUÍA: forma básica de una expresión SpEL "func(args)".
        // 1. Si spel es null/blank -> false.
        // 2. Debe tener un nombre de función seguido de paréntesis.
        // PISTA: return spel != null && spel.matches("\\w+\\(.*\\)");
        // OJO: el test pasa "hasRole('USER')" y espera true. \\w+ es el nombre,
        // \\(.*\\) los paréntesis con su contenido. CULTURA: hasRole, hasAnyRole,
        // hasAuthority, permitAll son las funciones SpEL típicas de @PreAuthorize.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExpresionSpelValida");
    }

    /**
     * RETO EXTRA 03: Determina si la excepcion es lanzada por la seguridad del metodo (PreAuthorize).
     */
    public static boolean esExcepcionDeMetodo(Throwable t) {
        // GUÍA: comprobación de tipo (como Ej155 reto 07).
        // 1. Si t es null -> false.
        // PISTA: return t instanceof SecurityException;
        // OJO: el test pasa new SecurityException() (sin mensaje) y espera true;
        // por eso aquí miras el TIPO, no el mensaje. En Spring sería
        // AccessDeniedException lanzada por el AuthorizationManager de @PreAuthorize.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeMetodo");
    }

    /**
     * RETO EXTRA 04: Genera el log descriptivo de llamada segura.
     */
    public static String crearLogMetodoSeguro(String m, String user) {
        // GUÍA: log de invocación de método protegido.
        // PISTA: return String.format("método=%s usuario=%s", m, user);
        // OJO: el test pasa ("m","u") y exige .contains("m"); el formato es libre
        // mientras incluya el nombre del método.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearLogMetodoSeguro");
    }

    /**
     * RETO EXTRA 05: Verifica si el usuario es del dominio interno de la organizacion.
     */
    public static boolean esUsuarioInterno(String email) {
        // GUÍA: el correo pertenece al dominio interno.
        // 1. Si email es null -> false.
        // PISTA: return email != null && email.endsWith("@masterclass.com");
        // OJO: el test pasa "a@masterclass.com" y espera true. Usa endsWith (no
        // contains): "x@masterclass.com.attacker.com" debe FALLAR; endsWith con el
        // dominio exacto lo evita.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUsuarioInterno");
    }

    /**
     * RETO EXTRA 06: Determina si el parametro del metodo no es nulo.
     */
    public static boolean esArgumentoSeguro(Object arg) {
        // GUÍA: una línea — el argumento no debe ser null.
        // return arg != null;
        // OJO: el test pasa "arg" y espera true. Un null en un método protegido
        // suele indicar petición malformada; rechazarlo pronto evita NPE más abajo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esArgumentoSeguro");
    }

    /**
     * RETO EXTRA 07: Indica si el metodo esta exceptuado de validacion previa.
     */
    public static boolean esMetodoPublico(String methodName, java.util.List<String> publicMethods) {
        // GUÍA: pertenencia a la lista de métodos públicos (sin @PreAuthorize).
        // 1. Si methodName o publicMethods son null -> false.
        // PISTA: return publicMethods != null && publicMethods.contains(methodName);
        // OJO: el test pasa ("saludar", List.of("saludar")) y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMetodoPublico");
    }

    /**
     * RETO EXTRA 08: Determina si la excepcion es por mala configuracion del PreAuthorize.
     */
    public static boolean esFalloAnotacion(Throwable t) {
        // GUÍA: fallo de configuración SpEL por el mensaje.
        // 1. Si t o t.getMessage() son null -> false.
        // PISTA: t != null && t.getMessage() != null
        //        && t.getMessage().toLowerCase().contains("spel");
        // OJO: el test pasa IllegalArgumentException("spel") y espera true. Es una
        // expresión @PreAuthorize mal escrita (error del programador, no del usuario):
        // distinto de un acceso denegado legítimo (reto 03).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloAnotacion");
    }

    /**
     * RETO EXTRA 09: Comprueba si la expresion SpEL menciona un rol concreto.
     */
    public static boolean esRolePresente(String expression, String rol) {
        // GUÍA: la expresión SpEL menciona el rol.
        // 1. Si expression o rol son null -> false.
        // PISTA: return expression != null && expression.contains(rol);
        // OJO: el test pasa ("hasRole('ADMIN')","ADMIN") y espera true. Aquí
        // contains basta porque buscas la presencia del nombre del rol en la cadena.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRolePresente");
    }

    /**
     * RETO EXTRA 10: Resuelve si es denegacion por defecto o permitAll.
     */
    public static String determinarEstrategiaMetodo(String metadata) {
        // GUÍA: estrategia por defecto = DENY (deny by default, teoría 18.1).
        // 1. Si metadata menciona permitAll -> "PERMIT"; en cualquier otro caso "DENY".
        // PISTA: return metadata != null && metadata.contains("permitAll")
        //        ? "PERMIT" : "DENY";
        // OJO: el test pasa "" (cadena vacía) y espera EXACTAMENTE "DENY". Sin
        // metadatos -> deny by default: lo no autorizado explícitamente, se niega.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para determinarEstrategiaMetodo");
    }

}