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

        /**
     * RETO EXTRA 01: Comprueba si la ruta debe estar abierta de forma predeterminada (ej. login, swagger).
     */
    public static boolean esRutaPublica(String path) {
        // GUÍA: teoría 18.1 (rutas abiertas de la SecurityFilterChain).
        // 1. Si path es null/blank -> false (no es pública por defecto).
        // 2. Define una allowlist de prefijos públicos: "/login", "/swagger-ui",
        //    "/v3/api-docs", "/auth/" ...
        // 3. Devuelve true si el path empieza por (o coincide con) alguno.
        // PISTA: List.of("/login","/swagger-ui","/v3/api-docs")
        //        .stream().anyMatch(path::startsWith);
        // OJO: el test solo manda "/login" y espera true; basta con que esa
        // ruta entre en la allowlist. Es la versión "rápida" de esPublica.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRutaPublica");
    }

    /**
     * RETO EXTRA 02: Verifica la presencia de encabezados de origen cruzado en peticiones.
     */
    public static boolean esFiltroCorsActivo(String header) {
        // GUÍA: teoría 18.10 (CORS lo dispara la cabecera Origin).
        // 1. Si header es null/blank -> false.
        // 2. La cabecera que delata una petición cross-origin es "Origin"
        //    (y la familia "Access-Control-*").
        // PISTA: header.equalsIgnoreCase("Origin")
        //        || header.toLowerCase().startsWith("access-control");
        // OJO: el test manda exactamente "Origin" y espera true. Compara sin
        // distinguir mayúsculas (las cabeceras HTTP son case-insensitive).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFiltroCorsActivo");
    }

    /**
     * RETO EXTRA 03: Determina si es un encabezado standard de proteccion (HSTS, XSS).
     */
    public static boolean esCabeceraSegura(String name, String value) {
        // GUÍA: teoría 18.10 (cabeceras de endurecimiento que Spring Security añade).
        // 1. Si name es null/blank o value es null/blank -> false.
        // 2. Mantén un conjunto de nombres de cabecera de seguridad conocidos:
        //    X-Frame-Options, Strict-Transport-Security, X-XSS-Protection,
        //    X-Content-Type-Options, Content-Security-Policy.
        // 3. Devuelve true si name está en ese conjunto y trae un valor no vacío.
        // PISTA: Set.of("X-Frame-Options", ...).contains(name) && !value.isBlank();
        // OJO: el test pasa name="X-Frame-Options", value="DENY" y espera true.
        // CULTURA: estas cabeceras las pone .headers(...) en la SecurityFilterChain
        // real; protegen contra clickjacking (X-Frame-Options) y MIME sniffing.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCabeceraSegura");
    }

    /**
     * RETO EXTRA 04: Verifica que solo se acepte HTTPS.
     */
    public static boolean esProtocoloSeguro(String scheme) {
        // GUÍA: una línea — el único esquema seguro es https.
        // return scheme != null && scheme.equalsIgnoreCase("https");
        // OJO: el test manda "https" y espera true; "http" debe dar false.
        // Usa equalsIgnoreCase (la URL puede venir en mayúsculas) y null-safe.
        // CULTURA: en producción esto lo fuerza requiresChannel().requiresSecure()
        // o un HSTS, no un if a mano; aquí practicas la comprobación pura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esProtocoloSeguro");
    }

    /**
     * RETO EXTRA 05: Determina si el usuario no esta autenticado.
     */
    public static boolean esUsuarioAnonimo(String principal) {
        // GUÍA: teoría 18.7 (el principal anónimo).
        // 1. Un usuario sin autenticar es null, blank o el literal de Spring
        //    "anonymousUser".
        // PISTA: principal == null || principal.isBlank()
        //        || principal.equals("anonymousUser");
        // OJO: el test pasa "anonymousUser" y espera true. Ese es el nombre EXACTO
        // que Spring asigna al AnonymousAuthenticationToken; compáralo con equals.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUsuarioAnonimo");
    }

    /**
     * RETO EXTRA 06: Normaliza el rol removiendo el prefijo ROLE_.
     */
    public static String extraerRolDeAutoridad(String authority) {
        // GUÍA: teoría 18.2 (autoridad "ROLE_ADMIN" -> rol "ADMIN").
        // 1. Si authority es null -> devuelve null (o "" según prefieras; el test
        //    no lo cubre, pero sé null-safe).
        // 2. Si empieza por "ROLE_", quita ese prefijo; si no, devuélvelo igual.
        // PISTA: authority.startsWith("ROLE_") ? authority.substring(5) : authority;
        // OJO: el test pasa "ROLE_ADMIN" y espera exactamente "ADMIN". El prefijo
        // tiene 5 caracteres (R-O-L-E-_).
        // CULTURA: hasRole("ADMIN") en Spring añade ese prefijo internamente; aquí
        // haces la operación inversa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerRolDeAutoridad");
    }

    /**
     * RETO EXTRA 07: Determina si la excepcion proviene del stack de autenticacion de Spring Security.
     */
    public static boolean esExcepcionDeSeguridadFiltro(Throwable t) {
        // GUÍA: comprobación de tipo de excepción (instanceof con binding, teoría 1.8).
        // 1. Si t es null -> false.
        // 2. Devuelve true si t es del stack de seguridad. Aquí lo modelamos con
        //    SecurityException del JDK (en Spring sería AuthenticationException).
        // PISTA: return t instanceof SecurityException;
        // OJO: el test pasa new SecurityException() y espera true. No mires el
        // mensaje, mira el TIPO (es más robusto que comparar strings).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeSeguridadFiltro");
    }

    /**
     * RETO EXTRA 08: Identifica si la ruta requiere credenciales de administracion.
     */
    public static boolean esRutaExclusivaAdmin(String path) {
        // GUÍA: teoría 18.7 (rutas que exigen ROLE_ADMIN).
        // 1. Si path es null/blank -> false.
        // 2. Devuelve true si el path pertenece al área de administración.
        // PISTA: return path != null && path.startsWith("/admin");
        // OJO: el test manda "/admin/console" y espera true; usa startsWith para
        // cubrir todas las subrutas (/admin/**), no equals.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRutaExclusivaAdmin");
    }

    /**
     * RETO EXTRA 09: Genera el JSON de error de acceso prohibido.
     */
    public static String generarMensajeAccesoDenegado(String path) {
        // GUÍA: construir un cuerpo de error JSON (como el de b09).
        // 1. Si path es null -> usa "" para no imprimir "null".
        // 2. Devuelve un JSON que INCLUYA el path solicitado.
        // PISTA: return "{\"error\":\"Forbidden\",\"path\":\"" + path + "\"}";
        //        o String.format("{\"status\":403,\"path\":\"%s\"}", path);
        // OJO: el test solo exige que el resultado .contains(path) ("/api"), así
        // que basta con incrustar el path; el formato exacto es libre.
        // CUIDADO: no metas info sensible (existencia de recursos) en el cuerpo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarMensajeAccesoDenegado");
    }

    /**
     * RETO EXTRA 10: Comprueba si un filtro de seguridad concreto esta activo.
     */
    public static boolean esFiltroHabilitado(String filterName, String activeFilters) {
        // GUÍA: comprobar pertenencia a una lista separada por comas.
        // 1. Si filterName o activeFilters son null/blank -> false.
        // 2. Parte activeFilters por comas y comprueba si filterName está.
        // PISTA: java.util.Arrays.asList(activeFilters.split(",")).contains(filterName);
        // OJO: el test pasa filterName="CsrfFilter", activeFilters="CsrfFilter,CorsFilter"
        // y espera true. Cuidado con un contains() ingenuo sobre la cadena entera:
        // funcionaría aquí, pero "Csrf" daría falso positivo; el split por comas
        // es la comparación correcta token a token.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFiltroHabilitado");
    }

}