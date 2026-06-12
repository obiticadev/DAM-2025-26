package com.masterclass.api.b06_webadv;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 056 · Política CORS.
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.2).
 */
public final class Ej056CorsConfiguration {

    private Ej056CorsConfiguration() {
    }

    /**
     * Calcula las cabeceras CORS a devolver para un origen dado.
     *
     * @param origin          valor de la cabecera Origin de la petición
     * @param allowedOrigins  orígenes permitidos (puede contener "*")
     * @return mapa de cabeceras CORS; vacío si el origen NO está permitido
     */
    public static Map<String, String> corsHeaders(String origin, List<String> allowedOrigins) {
        // TODO 1: si origin es null/blank, no es petición cross-origin -> mapa vacío.
        // TODO 2: si allowedOrigins es null/vacío, nada permitido -> mapa vacío.
        // TODO 3: si allowedOrigins contiene "*", se permite cualquier origen.
        // TODO 4: si no, comprueba si 'origin' está exactamente en la lista.
        // TODO 5: si NO está permitido, devuelve mapa vacío (el navegador bloqueará).
        // TODO 6: si está permitido y se usó "*", Allow-Origin = "*".
        // TODO 7: si está permitido por lista, Allow-Origin = el propio 'origin' (eco).
        // TODO 8: añade "Access-Control-Allow-Methods" = "GET,POST,PUT,DELETE".
        // TODO 9: añade "Access-Control-Allow-Headers" = "Content-Type,Authorization".
        // TODO 10: devuelve el mapa con las 3 cabeceras.
        return Map.of();
    }

    public static void main(String[] args) {
        System.out.println(corsHeaders("https://app.com", List.of("https://app.com")));
    }

    /**
     * Reto Extra 1: Soporte de comodines en subdominios para orígenes permitidos.
     * Comprueba si el origen ('origin') coincide con algún patrón de la lista 'allowedOrigins',
     * la cual puede contener patrones con comodín de subdominio (ej: "https://*.company.com").
     */
    public static boolean pasoExtra01(String origin, List<String> allowedOrigins) {
        // GUÍA: teoría 6.2. Un patrón "https://*.company.com" debe casar con
        // cualquier subdominio, pero NO con el dominio pelado ni con otro dominio.
        // 1. Si origin o allowedOrigins son null -> false.
        // 2. Para cada patrón permitido:
        //    - si NO tiene "*", compara con equals (coincidencia exacta).
        //    - si tiene "*", conviértelo en sufijo: quita "*" y comprueba que el
        //      origin TERMINE en lo que queda (".company.com"). Así "sub.company.com"
        //      pasa y "company.com" no (le falta el punto del subdominio).
        // PISTA: String sufijo = patron.replace("*", "");  // "https://.company.com"
        //        origin.endsWith(sufijo)  // cuidado: ver OJO
        // OJO: el test exige que "https://company.com" -> false con el patrón
        //      "https://*.company.com". Si haces replace("*","") obtienes
        //      "https://.company.com" y "https://company.com" NO termina así
        //      (falta el punto) -> false. ¡Justo lo que quieres! Verifica que
        //      "https://app.com" (exacto en la lista) -> true por la rama equals.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * Reto Extra 2: Identificación de peticiones Preflight.
     * Determina si una petición HTTP entrante es un "Preflight Request" de CORS,
     * basándose en que su método sea "OPTIONS" y que incluya cabeceras clave como
     * "Access-Control-Request-Method".
     */
    public static boolean pasoExtra02(String httpMethod, Map<String, String> headers) {
        // GUÍA: teoría 6.2 (diagrama de preflight). Es preflight si y solo si:
        //   método == OPTIONS  Y  headers contiene "Access-Control-Request-Method".
        // 1. Si method o headers son null -> false.
        // 2. return "OPTIONS".equalsIgnoreCase(httpMethod)
        //           && headers.containsKey("Access-Control-Request-Method");
        // OJO: el test castiga los dos fallos por separado:
        //   ("GET", {ACRM:POST}) -> false  (no es OPTIONS)
        //   ("OPTIONS", {})      -> false  (no trae la cabecera)
        //   ("OPTIONS", {ACRM:POST}) -> true.
        // CULTURA: ese OPTIONS "fantasma" que ves en la pestaña Red del navegador
        //          antes de un DELETE es justo esto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * Reto Extra 3: Modificación segura de cabeceras para habilitar credenciales.
     * Añade o modifica las cabeceras CORS del mapa 'currentHeaders' para permitir credenciales
     * ("Access-Control-Allow-Credentials" = "true"). Si 'allowCredentials' es true, recuerda
     * que NO puedes usar "*" en "Access-Control-Allow-Origin"; en ese caso, debes inyectar
     * el 'origin' específico.
     */
    public static Map<String, String> pasoExtra03(Map<String, String> currentHeaders, boolean allowCredentials, String origin) {
        // GUÍA: teoría 6.2, "la trampa nº 1": "*" y credenciales son INCOMPATIBLES.
        // 1. Copia currentHeaders a un mapa MUTABLE (los Map.of son inmutables):
        //    var h = new HashMap<>(currentHeaders);
        // 2. Si allowCredentials:
        //    - pon "Access-Control-Allow-Credentials" = "true".
        //    - si el Allow-Origin actual es "*", SUSTITÚYELO por el 'origin'
        //      concreto (con credenciales no se puede el comodín).
        // 3. Devuelve el mapa.
        // OJO: el test entra con {Allow-Origin: "*"}, allowCredentials=true,
        //      origin="https://client.com" y espera Allow-Credentials="true" Y
        //      Allow-Origin="https://client.com" (el "*" reemplazado).
        // CULTURA: este bug real abre la API a CUALQUIER web con las cookies del
        //          usuario; los navegadores hoy lo bloquean por eso mismo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * Reto Extra 4: Cálculo y acotamiento de la cabecera Max-Age.
     * Evalúa el valor solicitado de Max-Age y devuelve el número acotado entre un mínimo de "60"
     * y un máximo de "1800" (30 minutos) por políticas de seguridad del servidor.
     */
    public static String pasoExtra04(String requestMaxAge) {
        // GUÍA: teoría 6.2 (Max-Age cachea el preflight). Acota a [60, 1800].
        // 1. Parsea requestMaxAge a int dentro de try/catch.
        // 2. Si NO es numérico -> devuelve "1800" (el máximo, ver OJO).
        // 3. Si es válido: si < 60 -> "60"; si > 1800 -> "1800"; si no, su valor.
        //    Truco limpio: Math.max(60, Math.min(1800, n)).
        // 4. Devuelve SIEMPRE un String (la cabecera es texto).
        // OJO: el test fija "10"->"60", "3600"->"1800", "600"->"600" y, atención,
        //      "invalid"->"1800" (el inválido cae al MÁXIMO, no al mínimo).
        // PISTA: catch (NumberFormatException e) { return "1800"; }
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * Reto Extra 5: Validación de cabeceras personalizadas en Preflight.
     * Comprueba si todas las cabeceras solicitadas por el cliente ('requestedHeaders') están
     * autorizadas por el servidor dentro de la lista de permitidas ('allowedHeaders') de forma
     * insensible a mayúsculas/minúsculas.
     */
    public static boolean pasoExtra05(List<String> requestedHeaders, List<String> allowedHeaders) {
        // GUÍA: teoría 6.2. TODAS las cabeceras pedidas deben estar en las
        // permitidas, comparando SIN distinguir mayúsculas (HTTP es case-insensitive
        // en nombres de cabecera).
        // 1. Si requestedHeaders es null/vacío -> true (no pide nada raro).
        // 2. Normaliza las permitidas a minúsculas una sola vez (Set para O(1)):
        //    var permitidas = allowedHeaders.stream().map(String::toLowerCase)
        //                        .collect(Collectors.toSet());
        // 3. requestedHeaders.stream().allMatch(h -> permitidas.contains(h.toLowerCase()));
        // OJO: el test pasa ["content-type"] (minúsculas) y ["CONTENT-TYPE",
        //      "AUTHORIZATION"] contra ["Content-Type","Authorization"] -> true,
        //      y ["X-Custom-Header"] -> false. La insensibilidad es lo evaluado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * Reto Extra 6: Validación de orígenes locales de desarrollo.
     * Comprueba si el origen ('origin') corresponde a una dirección local localhost
     * con puerto dinámico (ej: "http://localhost:3000" o "http://127.0.0.1:8080").
     */
    public static boolean pasoExtra06(String origin) {
        // GUÍA: teoría 6.2. Un origen local de desarrollo es http:// + localhost o
        // 127.0.0.1 + ':' + puerto. Una regex lo expresa exacto.
        // 1. Si origin es null -> false.
        // 2. return origin.matches("http://(localhost|127\\.0\\.0\\.1):\\d+");
        // OJO: el test es muy puntilloso:
        //   "http://localhost:3000"   -> true
        //   "http://127.0.0.1:8080"   -> true
        //   "https://localhost"       -> false  (es https Y sin puerto)
        //   "http://localhostcompany.com" -> false  (no es "localhost" exacto)
        //   Por eso anclas con regex (no un contains("localhost"), que dejaría
        //   pasar "localhostcompany").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * Reto Extra 7: Configuración de cabeceras expuestas.
     * Añade la cabecera "Access-Control-Expose-Headers" al mapa 'currentHeaders' con los nombres
     * de cabeceras en 'exposedHeaders' para permitir que el frontend del cliente lea cabeceras
     * de respuesta customizadas.
     */
    public static Map<String, String> pasoExtra07(Map<String, String> currentHeaders, List<String> exposedHeaders) {
        // GUÍA: teoría 6.2. Por defecto el JS del cliente solo puede leer un puñado
        // de cabeceras; Expose-Headers amplía esa lista.
        // 1. Copia currentHeaders a un HashMap mutable (Map.of es inmutable).
        // 2. Une exposedHeaders con ", " (coma y espacio) y mételo en
        //    "Access-Control-Expose-Headers".
        //    PISTA: String.join(", ", exposedHeaders);
        // 3. Devuelve el mapa.
        // OJO: el test espera EXACTAMENTE "X-Total-Count, X-Limit" (con el espacio
        //      tras la coma). Y currentHeaders entra como Map.of() inmutable: si
        //      intentas .put sobre él directamente -> UnsupportedOperationException.
        // CULTURA: sin esto, un front no puede leer tu cabecera de paginación
        //          "X-Total-Count" aunque la envíes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * Reto Extra 8: Saneamiento y validación defensiva de la cabecera Origin.
     * Limpia y sanea la cabecera Origin para evitar ataques de inyección de cabeceras HTTP (CRLF).
     * Si detecta saltos de línea (\r, \n) o caracteres maliciosos, devuelve null.
     */
    public static String pasoExtra08(String origin) {
        // GUÍA: defensa contra HTTP Response Splitting / header injection (CRLF).
        // Si el origin trae un salto de línea, un atacante podría "inyectar" una
        // cabecera nueva en la respuesta. Regla: si hay \r o \n -> rechaza (null).
        // 1. Si origin es null -> null.
        // 2. if (origin.contains("\r") || origin.contains("\n")) return null;
        // 3. Si está limpio -> devuélvelo tal cual.
        // OJO: el test pasa "https://safe.com" -> se devuelve igual, y
        //      "https://safe.com\r\nHeader-Injection: evil" -> null.
        // CULTURA: nunca metas en una cabecera de respuesta un valor de entrada
        //          sin sanear; es una familia entera de vulnerabilidades.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * Reto Extra 9: Comprobación de métodos CORS simples.
     * Evalúa si un método HTTP califica como "CORS Simple Method" (GET, HEAD, POST).
     */
    public static boolean pasoExtra09(String method) {
        // GUÍA: teoría 6.2. Los métodos "simples" (sin preflight) son GET, HEAD y
        // POST. Cualquier otro (PUT, DELETE, PATCH) dispara preflight.
        // 1. Si method es null -> false.
        // 2. return List.of("GET", "HEAD", "POST").contains(method.toUpperCase());
        //    (o un switch). Compara en mayúsculas para tolerar "post".
        // OJO: el test exige PUT y DELETE -> false; GET/POST/HEAD -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * Reto Extra 10: Comprobación de cabeceras CORS simples.
     * Evalúa si una cabecera HTTP y su valor califican como "CORS Simple Header"
     * (ej: Accept, Accept-Language, Content-Language, o Content-Type con valores específicos).
     */
    public static boolean pasoExtra10(String headerName, String headerValue) {
        // GUÍA: teoría 6.2. Son "cabeceras simples": Accept, Accept-Language,
        // Content-Language y Content-Type SOLO con ciertos valores
        // (text/plain, multipart/form-data, application/x-www-form-urlencoded).
        // 1. Si headerName es null -> false.
        // 2. Si el nombre (ignore case) es Accept / Accept-Language /
        //    Content-Language -> true (sin mirar el valor).
        // 3. Si es Content-Type -> true SOLO si el valor está en esos tres MIME.
        // 4. Cualquier otro nombre -> false.
        // OJO: el test:
        //   ("Accept","text/html") -> true
        //   ("Content-Type","application/x-www-form-urlencoded") -> true
        //   ("Content-Type","application/json") -> FALSE (json NO es simple)
        //   ("X-Custom","value") -> false
        // CULTURA: que Content-Type: application/json NO sea "simple" es el motivo
        //          por el que tus POST de JSON disparan un preflight OPTIONS.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
