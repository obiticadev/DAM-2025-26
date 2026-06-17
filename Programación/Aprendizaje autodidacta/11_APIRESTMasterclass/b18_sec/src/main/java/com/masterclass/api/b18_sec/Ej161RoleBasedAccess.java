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

        /**
     * RETO EXTRA 01: Determina si el rol del usuario supera o iguala el nivel de acceso requerido.
     */
    public static boolean esAccesoPermitidoRol(String rolUsuario, String rolRequerido) {
        // GUÍA: jerarquía de roles por NIVEL numérico (teoría 18.7/18.8).
        // 1. Asigna un rango a cada rol: ADMIN=3, USER=2, GUEST=1 (Map o switch).
        // 2. El acceso se concede si rango(usuario) >= rango(requerido).
        // PISTA: define int rango(String rol) y compara rango(rolUsuario) >= rango(rolRequerido).
        // OJO: el test pasa ("ADMIN","USER") y espera true: ADMIN(3) >= USER(2).
        // Esto es lo que en Spring hace un RoleHierarchy ("ADMIN > USER"); reutiliza
        // este rango en tieneJerarquiaMayor (reto 04).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAccesoPermitidoRol");
    }

    /**
     * RETO EXTRA 02: Verifica si la cadena empieza por el prefijo standard 'ROLE_'.
     */
    public static boolean esRolPrefijoCorrecto(String roleName) {
        // GUÍA: una línea — el prefijo estándar de autoridad es "ROLE_".
        // return roleName != null && roleName.startsWith("ROLE_");
        // OJO: el test pasa "ROLE_USER" y espera true. Es la comprobación previa a
        // extraerRolDeAutoridad (Ej155 reto 06), que quita ese mismo prefijo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRolPrefijoCorrecto");
    }

    /**
     * RETO EXTRA 03: Valida la sintaxis de la jerarquia de roles.
     */
    public static boolean esJerarquiaValida(String hierarchyDef) {
        // GUÍA: validar la sintaxis "ROL > ROL" con regex.
        // 1. Si hierarchyDef es null -> false.
        // 2. Debe haber dos identificadores separados por '>'.
        // PISTA: return hierarchyDef != null
        //        && hierarchyDef.matches("\\w+\\s*>\\s*\\w+");
        // OJO: el test pasa "ADMIN > USER" y espera true. \\s* tolera los espacios
        // alrededor del '>'. CULTURA: es el formato del bean RoleHierarchy de Spring.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esJerarquiaValida");
    }

    /**
     * RETO EXTRA 04: Determina si r1 tiene mayor nivel que r2.
     */
    public static boolean tieneJerarquiaMayor(String r1, String r2) {
        // GUÍA: usa el mismo rango que el reto 01 pero con > estricto.
        // PISTA: return rango(r1) > rango(r2);  (ADMIN=3, USER=2, GUEST=1)
        // OJO: el test pasa ("ADMIN","USER") y espera true: 3 > 2. Diferencia clave
        // con el reto 01: allí era >= (igual o superior), aquí es > (estrictamente
        // mayor). Extrae el método rango a un helper privado y reutilízalo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneJerarquiaMayor");
    }

    /**
     * RETO EXTRA 05: Determina si la excepcion es de tipo AccessDeniedException.
     */
    public static boolean esExcepcionDeDenegacion(Throwable t) {
        // GUÍA: detectar acceso denegado por el mensaje.
        // 1. Si t o t.getMessage() son null -> false.
        // 2. En Spring sería AccessDeniedException -> 403; aquí va por mensaje.
        // PISTA: t != null && t.getMessage() != null
        //        && t.getMessage().toLowerCase().contains("denied");
        // OJO: el test pasa SecurityException("Access denied") y espera true.
        // Recuerda: denegación de acceso = 403, no 401 (teoría 18.7).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeDenegacion");
    }

    /**
     * RETO EXTRA 06: Genera el JSON de error 403.
     */
    public static String crearJsonAccesoProhibido(String r, String msg) {
        // GUÍA: cuerpo JSON de error 403 (como en b09).
        // 1. Incluye el status 403, el rol y el mensaje.
        // PISTA: return String.format(
        //        "{\"status\":403,\"role\":\"%s\",\"message\":\"%s\"}", r, msg);
        // OJO: el test pasa ("r","msg") y exige que .contains("msg"); el formato es
        // libre mientras incluya el mensaje. No reveles existencia de recursos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearJsonAccesoProhibido");
    }

    /**
     * RETO EXTRA 07: Indica si la ruta esta reservada exclusivamente a administradores.
     */
    public static boolean esRutaSoloAdmin(String path) {
        // GUÍA: la ruta toca el área de administración.
        // 1. Si path es null -> false.
        // PISTA: return path != null && path.contains("/admin");
        // OJO: el test pasa "/api/admin/db" y espera true. AQUÍ usa contains (no
        // startsWith) porque "/admin" no está al principio: va detrás de "/api".
        // Compara con Ej155 reto 08, donde sí era startsWith.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRutaSoloAdmin");
    }

    /**
     * RETO EXTRA 08: Comprueba sintaxis de grupos organizativos en el rol.
     */
    public static boolean esGrupoValido(String groupName) {
        // GUÍA: los grupos llevan el prefijo "GRP_".
        // 1. Si groupName es null -> false.
        // PISTA: return groupName != null && groupName.startsWith("GRP_");
        // OJO: el test pasa "GRP_ADMIN" y espera true. Misma idea que el prefijo
        // ROLE_ (reto 02) pero para autoridades de tipo grupo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esGrupoValido");
    }

    /**
     * RETO EXTRA 09: Determina correspondencia minima.
     */
    public static boolean esUsuarioValidoRol(String u, String r) {
        // GUÍA: correspondencia mínima usuario+rol: ambos presentes.
        // PISTA: return u != null && !u.isBlank() && r != null && !r.isBlank();
        // OJO: el test pasa ("ada","ADMIN") y espera true. Es una validación básica
        // de que el par (usuario, rol) está completo antes de evaluar permisos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUsuarioValidoRol");
    }

    /**
     * RETO EXTRA 10: Genera log de auditoria de accesos denegados/permitidos.
     */
    public static String formatearLogAcceso(String u, String path, boolean ok) {
        // GUÍA: línea de auditoría de acceso.
        // 1. Mapea el booleano: ok ? "ALLOWED" : "DENIED".
        // PISTA: return String.format("%s user=%s path=%s", ok ? "ALLOWED" : "DENIED", u, path);
        // OJO: el test pasa ("u","p",true) y exige .contains("ALLOWED"); literal en
        // MAYÚSCULAS. Compara con Ej158 reto 09 (SUCCESS/FAILURE).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearLogAcceso");
    }

}