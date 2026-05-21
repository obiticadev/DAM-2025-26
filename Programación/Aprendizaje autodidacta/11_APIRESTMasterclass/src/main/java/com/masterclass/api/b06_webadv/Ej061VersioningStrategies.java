package com.masterclass.api.b06_webadv;

/**
 * Ejercicio 061 · Estrategias de versionado de API.
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.4).
 *
 * <p>Lógica pura: resolver la versión efectiva combinando ruta y cabecera.
 */
public final class Ej061VersioningStrategies {

    private Ej061VersioningStrategies() {
    }

    /**
     * Resuelve la versión a usar.
     *
     * <p>Precedencia: la cabecera {@code X-API-Version} pisa la versión de la ruta.
     * Si ninguna está, la versión por defecto es 1.
     *
     * @param path          ruta solicitada, p.ej. "/api/v2/users" o "/api/users"
     * @param headerVersion valor de X-API-Version (puede ser null)
     * @return número de versión efectivo (&gt;= 1)
     * @throws IllegalArgumentException si la versión es no numérica o &lt; 1
     */
    public static int resolveVersion(String path, String headerVersion) {
        // TODO 1: si headerVersion no es null/blank, tiene prioridad.
        // TODO 2: parsea headerVersion a int (try/catch -> IllegalArgumentException).
        // TODO 3: si la versión del header es < 1 -> IllegalArgumentException.
        // TODO 4: si hay header válido, devuélvelo (ignora la ruta).
        // TODO 5: si no hay header, intenta extraer "/v{n}/" de la ruta.
        // TODO 6: localiza el segmento que empiece por 'v' seguido de dígitos.
        // TODO 7: si lo encuentras, parsea el número tras la 'v'.
        // TODO 8: valida que sea >= 1 (si no, IllegalArgumentException).
        // TODO 9: si la ruta no tiene versión, usa la versión por defecto 1.
        // TODO 10: devuelve la versión resuelta.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(resolveVersion("/api/v2/users", null));
        System.out.println(resolveVersion("/api/users", "3"));
    }

    /**
     * Reto Extra 1: Extractor de versión desde cabecera Accept (Content Negotiation).
     * Parsea la cabecera 'Accept' en formato media-type personalizado (ej: "application/vnd.company.app-v2+json")
     * y extrae el número de versión (ej: 2). Si no está definido o no coincide, devuelve -1.
     */
    public static int pasoExtra01(String acceptHeader) {
        // TODO extra: extrae la versión del media type personalizado en la cabecera Accept.
        return -1;
    }

    /**
     * Reto Extra 2: Extractor de versión desde Query Parameter.
     * Parsea los parámetros de consulta de una URI y extrae el valor del parámetro 'api-version'
     * (ej: "/api/users?api-version=3.0" -> "3.0"). Si no está presente, devuelve null.
     */
    public static String pasoExtra02(String uri) {
        // TODO extra: extrae el valor del parámetro de consulta 'api-version'.
        return null;
    }

    /**
     * Reto Extra 3: Validador estricto de segmento de versión en ruta.
     * Comprueba si el segmento de versión en la ruta (ej: "/api/v2/users") es sintácticamente válido
     * y no es un falso positivo (como "/api/av1/users" o "/api/v/users").
     */
    public static boolean pasoExtra03(String uri) {
        // TODO extra: valida sintácticamente el segmento de versión de la URI.
        return false;
    }

    /**
     * Reto Extra 4: Resolutor jerárquico de versiones.
     * Obtiene la versión final combinando múltiples fuentes posibles por orden de prioridad estricto:
     * 1. Cabecera Custom "X-API-Version" (ej: "3")
     * 2. Query parameter "version" (ej: "?version=2")
     * 3. URI Segment "/v{N}/" (ej: "/v1/")
     * Retorna la versión resuelta, o 1 como fallback si no se detecta ninguna.
     */
    public static int pasoExtra04(String customHeader, String queryParam, String uriPath) {
        // TODO extra: resuelve jerárquicamente la versión priorizando las fuentes.
        return 1;
    }

    /**
     * Reto Extra 5: Mapeador de versiones semánticas (SemVer).
     * Parsea una cadena de versión semántica (ej: "1.4.2-beta") y la divide en un array
     * de enteros conteniendo [major, minor, patch]. Retorna null si no tiene formato SemVer válido.
     */
    public static int[] pasoExtra05(String semVerStr) {
        // TODO extra: parsea la versión semántica y devuelve un array con [major, minor, patch].
        return null;
    }

    /**
     * Reto Extra 6: Comprobador de rango SemVer (Rango de compatibilidad).
     * Comprueba si una versión semántica solicitada (ej: "1.5.2") cumple un rango de restricción dado
     * (ej: "^1.0.0" que permite >= 1.0.0 y < 2.0.0).
     */
    public static boolean pasoExtra06(String requestedVersion, String semVerRange) {
        // TODO extra: valida si la versión solicitada entra en el rango permitido.
        return false;
    }

    /**
     * Reto Extra 7: Inyección de cabeceras de API obsoleta (Deprecation / Sunset).
     * Añade cabeceras estandarizadas de deprecación (ej: "Deprecation: true", "Sunset: Wed, 11 Nov 2026 00:00:00 GMT")
     * para advertir a los clientes de que la versión que están llamando está obsoleta y se retirará pronto.
     */
    public static org.springframework.http.ResponseEntity.BodyBuilder pasoExtra07(org.springframework.http.ResponseEntity.BodyBuilder builder, String sunsetDate) {
        // TODO extra: inyecta las cabeceras de obsolescencia Deprecation y Sunset en el builder.
        return null;
    }

    /**
     * Reto Extra 8: Validación contra lista de versiones soportadas.
     * Comprueba si una versión dada ('version') se encuentra dentro de una lista o conjunto
     * de versiones que el backend soporta activamente en producción.
     */
    public static boolean pasoExtra08(int version, java.util.List<Integer> supportedVersions) {
        // TODO extra: valida si la versión es soportada activamente.
        return false;
    }

    /**
     * Reto Extra 9: Generador de ruta limpia (Stripping de versión).
     * Elimina el segmento de versión ("/v1", "/v2", etc.) de la ruta de la petición
     * para obtener la ruta canónica interna del recurso (ej: "/api/v2/users/1" -> "/api/users/1").
     */
    public static String pasoExtra09(String uriPath) {
        // TODO extra: limpia el segmento de versión de la URI y devuelve la ruta simplificada.
        return null;
    }

    /**
     * Reto Extra 10: Generador de enlaces versionados HATEOAS.
     * Añade dinámicamente un prefijo o parámetro de versión a una URI base de recurso
     * según la preferencia del cliente (ej: "/users/5" con versión 3 -> "/v3/users/5").
     */
    public static String pasoExtra10(String baseUri, int version) {
        // TODO extra: construye el enlace versionado dinámicamente.
        return null;
    }

}
