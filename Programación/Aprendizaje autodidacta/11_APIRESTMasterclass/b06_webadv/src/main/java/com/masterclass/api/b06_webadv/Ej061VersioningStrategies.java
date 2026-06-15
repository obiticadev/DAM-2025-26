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
        // GUÍA: teoría 6.7 (versionado por Accept) + 6.1 (vnd.). Extrae el número
        // tras "-v" del media-type vendor-specific.
        // 1. Si acceptHeader es null -> -1.
        // 2. Busca el patrón "-v" seguido de dígitos con una regex:
        //    Matcher m = Pattern.compile("-v(\\d+)").matcher(acceptHeader);
        //    return m.find() ? Integer.parseInt(m.group(1)) : -1;
        // OJO: el test pasa "application/vnd.company.app-v2+json" y espera 2.
        //      Si no encuentra el patrón -> -1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * Reto Extra 2: Extractor de versión desde Query Parameter.
     * Parsea los parámetros de consulta de una URI y extrae el valor del parámetro 'api-version'
     * (ej: "/api/users?api-version=3.0" -> "3.0"). Si no está presente, devuelve null.
     */
    public static String pasoExtra02(String uri) {
        // GUÍA: teoría 6.7 (versionado por query param). Extrae 'api-version' de
        // la query string.
        // 1. Si uri es null o no tiene '?' -> null.
        // 2. Toma la query (lo de tras '?'), pártela por '&' y busca el par cuya
        //    clave sea "api-version"; devuelve su valor.
        // PISTA: String query = uri.substring(uri.indexOf('?') + 1);
        //        for (String par : query.split("&")) {
        //            String[] kv = par.split("=", 2);
        //            if (kv.length == 2 && kv[0].equals("api-version")) return kv[1];
        //        }
        //        return null;
        // OJO: el test pasa "/api/users?api-version=3.0" y espera "3.0" (String,
        //      con el punto; NO lo conviertas a int aquí).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * Reto Extra 3: Validador estricto de segmento de versión en ruta.
     * Comprueba si el segmento de versión en la ruta (ej: "/api/v2/users") es sintácticamente válido
     * y no es un falso positivo (como "/api/av1/users" o "/api/v/users").
     */
    public static boolean pasoExtra03(String uri) {
        // GUÍA: teoría 6.7 ("error común nº 9": /av2/ NO es versión). Valida un
        // segmento /v{dígitos}/ de verdad, anclado entre barras.
        // 1. Si uri es null -> false.
        // 2. Busca el patrón "/v\\d+(/|$)" (v + dígitos, seguido de '/' o fin):
        //    return Pattern.compile("/v\\d+(/|$)").matcher(uri).find();
        // OJO: el test exige "/api/v2/users" -> true y "/api/av2/users" -> false.
        //      La clave es que la 'v' vaya JUSTO tras '/'. Un contains("v2") o un
        //      patrón sin anclar dejaría colar "av2" -> bug clásico.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
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
        // GUÍA: teoría 6.7 (precedencia de estrategias). Orden ESTRICTO:
        // 1º cabecera, 2º query param, 3º segmento /v{n}/, y 1 como fallback.
        // 1. Si customHeader no es null/blank -> parséalo a int y devuélvelo.
        // 2. Si no, si queryParam no es null/blank -> parséalo a int.
        // 3. Si no, intenta extraer /v{n}/ de uriPath (reutiliza la regla de
        //    pasoExtra03/resolveVersion).
        // 4. Si nada hay -> 1.
        //    Protege cada parseInt con try/catch (no es objeto del test, pero es lo
        //    correcto).
        // OJO: el test pasa ("3","2","/v1/") y espera 3: aunque las TRES fuentes
        //      traen versión, gana la cabecera por precedencia. Comprueba el orden.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * Reto Extra 5: Mapeador de versiones semánticas (SemVer).
     * Parsea una cadena de versión semántica (ej: "1.4.2-beta") y la divide en un array
     * de enteros conteniendo [major, minor, patch]. Retorna null si no tiene formato SemVer válido.
     */
    public static int[] pasoExtra05(String semVerStr) {
        // GUÍA: teoría 6.7 (SemVer MAJOR.MINOR.PATCH). Parte la versión en 3 ints.
        // 1. Si semVerStr es null -> null.
        // 2. Descarta el sufijo de pre-release tras '-' (p.ej. "-beta"):
        //    String core = semVerStr.split("-")[0];   // "1.4.2"
        // 3. Pártelo por '.'; deben salir 3 trozos numéricos:
        //    String[] p = core.split("\\.");
        //    if (p.length != 3) return null;
        //    return new int[]{ Integer.parseInt(p[0]), parseInt(p[1]), parseInt(p[2]) };
        //    Envuelve los parseInt en try/catch -> null si algún trozo no es número.
        // OJO: el test pasa "1.4.2-beta" y espera {1,4,2}: el "-beta" se DESCARTA
        //      antes de partir por puntos. Recuerda escapar el punto: split("\\.").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * Reto Extra 6: Comprobador de rango SemVer (Rango de compatibilidad).
     * Comprueba si una versión semántica solicitada (ej: "1.5.2") cumple un rango de restricción dado
     * (ej: "^1.0.0" que permite >= 1.0.0 y < 2.0.0).
     */
    public static boolean pasoExtra06(String requestedVersion, String semVerRange) {
        // GUÍA: teoría 6.7 (rango "caret" ^X.Y.Z = >=X.Y.Z y <(X+1).0.0).
        // Apóyate en pasoExtra05 para parsear ambas versiones.
        // 1. El rango "^1.0.0" -> quita el '^' y parsea con pasoExtra05 -> base.
        //    Parsea también requestedVersion -> req.
        // 2. Compatible si: req.major == base.major  Y  req >= base
        //    (compara major; si igual, minor; si igual, patch).
        // 3. Si algo no parsea -> false.
        // OJO: el test pasa ("1.5.2","^1.0.0") -> true: mismo major (1) y
        //      1.5.2 >= 1.0.0. Una "2.0.0" daría false (cambió el major).
        // CULTURA: es justo la regla que usa npm/Maven para decidir qué
        //          actualización es "segura" (no rompe la API).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * Reto Extra 7: Inyección de cabeceras de API obsoleta (Deprecation / Sunset).
     * Añade cabeceras estandarizadas de deprecación (ej: "Deprecation: true", "Sunset: Wed, 11 Nov 2026 00:00:00 GMT")
     * para advertir a los clientes de que la versión que están llamando está obsoleta y se retirará pronto.
     */
    public static org.springframework.http.ResponseEntity.BodyBuilder pasoExtra07(org.springframework.http.ResponseEntity.BodyBuilder builder, String sunsetDate) {
        // GUÍA: teoría 6.7 (avisar de que una versión va a retirarse). El builder
        // es fluido.
        // return builder
        //     .header("Deprecation", "true")
        //     .header("Sunset", sunsetDate);
        // - Deprecation: marca el endpoint como obsoleto.
        // - Sunset: fecha (RFC 1123) a partir de la cual dejará de existir.
        // OJO: el test pasa la fecha "Wed, 11 Nov 2026 00:00:00 GMT" y solo pide
        //      assertNotNull; devuelve el builder, no null.
        // CULTURA: dar a los clientes una fecha de "sunset" por cabecera es la
        //          forma educada de retirar una versión sin romper de golpe.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * Reto Extra 8: Validación contra lista de versiones soportadas.
     * Comprueba si una versión dada ('version') se encuentra dentro de una lista o conjunto
     * de versiones que el backend soporta activamente en producción.
     */
    public static boolean pasoExtra08(int version, java.util.List<Integer> supportedVersions) {
        // GUÍA: una línea defensiva.
        // return supportedVersions != null && supportedVersions.contains(version);
        // CUIDADO: contains sobre List<Integer> hace autoboxing de 'version' a
        //      Integer y compara con equals (no por ==): correcto aquí. El test
        //      pasa (2, [1,2,3]) -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * Reto Extra 9: Generador de ruta limpia (Stripping de versión).
     * Elimina el segmento de versión ("/v1", "/v2", etc.) de la ruta de la petición
     * para obtener la ruta canónica interna del recurso (ej: "/api/v2/users/1" -> "/api/users/1").
     */
    public static String pasoExtra09(String uriPath) {
        // GUÍA: teoría 6.7 (ruta canónica interna sin la versión). Quita el
        // segmento "/v{n}" de la ruta.
        // 1. Si uriPath es null -> null.
        // 2. Reemplaza "/v\\d+" por "" con replaceAll:
        //    return uriPath.replaceFirst("/v\\d+", "");
        //    (replaceFirst para no tocar un hipotético "/v9" dentro de un id).
        // OJO: el test pasa "/api/v2/users/1" y espera "/api/users/1": solo
        //      desaparece "/v2", el resto de la ruta intacto.
        // PISTA: es el inverso de pasoExtra10 (que AÑADE la versión).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * Reto Extra 10: Generador de enlaces versionados HATEOAS.
     * Añade dinámicamente un prefijo o parámetro de versión a una URI base de recurso
     * según la preferencia del cliente (ej: "/users/5" con versión 3 -> "/v3/users/5").
     */
    public static String pasoExtra10(String baseUri, int version) {
        // GUÍA: teoría 6.7 (inverso del reto 9: AÑADIR el prefijo de versión).
        // 1. return "/v" + version + baseUri;
        //    (defensa: asegúrate de que baseUri empiece por '/').
        // OJO: el test pasa ("/users/5", 3) y espera "/v3/users/5".
        // CULTURA: en HATEOAS la API te devuelve enlaces ya construidos a los
        //          recursos relacionados; versionarlos así mantiene la coherencia.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
