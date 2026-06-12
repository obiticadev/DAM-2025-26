package com.masterclass.api.b06_webadv;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 059 · Lectura y escritura de cabeceras.
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.5).
 *
 * <p>El test envía {@code GET /api/trace} con header {@code X-Request-Id: abc}
 * y espera 200, body "ok" y header de respuesta {@code X-Correlation-Id: abc}.
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api").
public class Ej059RequestResponseHeaders {

    /**
     * Lee un header de la petición y lo refleja como header de respuesta.
     *
     * @param requestId valor de X-Request-Id (opcional)
     * @return ResponseEntity 200 con body "ok" y X-Correlation-Id
     */
    // TODO 2: anota el método con @GetMapping("/trace").
    // TODO 3: anota 'requestId' con @RequestHeader(value="X-Request-Id", required=false).
    public ResponseEntity<String> trace(String requestId) {
        // TODO 4: si requestId es null, genera uno (p.ej. "gen-" + algo) como fallback.
        // TODO 5: ese id será el "correlation id" que devolvemos.
        // TODO 6: parte de ResponseEntity.ok().
        // TODO 7: añade header "X-Correlation-Id" con el valor resuelto.
        // TODO 8: el body debe ser exactamente "ok".
        // TODO 9: usa .body("ok").
        // TODO 10: devuelve la ResponseEntity.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej059RequestResponseHeaders().trace("abc"));
    }

    /**
     * Reto Extra 1: Extracción del idioma preferente (Accept-Language).
     * Parsea la cabecera 'Accept-Language' (ej: "es-ES,es;q=0.9,en;q=0.8") y devuelve
     * la primera etiqueta de idioma (ej: "es-ES"), que representa la de mayor prioridad.
     */
    public static String pasoExtra01(String acceptLanguage) {
        // GUÍA: teoría 6.5. Accept-Language es como Accept (6.1): lista por comas
        // con q-values. La primera etiqueta (sin ;q) es la preferente.
        // 1. Si acceptLanguage es null/blank -> decide ("" o un default).
        // 2. Coge el primer token: split(",")[0], y quítale el ;q: split(";")[0].
        // 3. trim() y devuelve.
        // PISTA: return acceptLanguage.split(",")[0].split(";")[0].trim();
        // OJO: el test pasa "es-ES,es;q=0.9" y espera "es-ES" (la primera, sin más).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * Reto Extra 2: Detección de petición AJAX.
     * Comprueba si la petición es de tipo AJAX analizando si la cabecera 'X-Requested-With'
     * contiene exactamente el valor "XMLHttpRequest" (ignora mayúsculas/minúsculas).
     */
    public static boolean pasoExtra02(String xRequestedWith) {
        // GUÍA: una línea. Las peticiones AJAX (jQuery/XHR) mandan
        // X-Requested-With: XMLHttpRequest.
        // return "XMLHttpRequest".equalsIgnoreCase(xRequestedWith);
        // El equalsIgnoreCase tolera null en el ARGUMENTO (no en el literal) y
        // cubre variaciones de caso. El test pasa "XMLHttpRequest" -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * Reto Extra 3: Decodificador de credenciales Basic Auth.
     * Extrae y decodifica las credenciales en Base64 contenidas en la cabecera 'Authorization'
     * con formato "Basic [base64(usuario:contraseña)]", devolviendo un array con [usuario, contraseña].
     * Si el formato es inválido o no es Basic, devuelve null.
     */
    public static String[] pasoExtra03(String authorizationHeader) {
        // GUÍA: teoría 6.5 (Basic = base64(usuario:contraseña)).
        // 1. Si es null o no empieza por "Basic " -> null.
        // 2. Quita el prefijo: authorizationHeader.substring(6).
        // 3. Decodifica base64: new String(Base64.getDecoder().decode(b64),
        //        StandardCharsets.UTF_8)  -> "usuario:contraseña".
        // 4. Parte por el PRIMER ':': split(":", 2) -> [usuario, contraseña].
        // PISTA: el "dXNlcjpwYXNz" del test decodifica a "user:pass" -> ["user","pass"].
        // OJO/CUIDADO: usa split(":", 2), NO split(":"): si la contraseña tiene
        //      ':' (p.ej. "a:b:c"), con el límite 2 obtienes ["a","b:c"]; sin
        //      límite la trocearías mal. El test solo pide assertNotNull, pero
        //      hazlo bien.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * Reto Extra 4: Extractor de Token Bearer JWT.
     * Extrae el token JWT de la cabecera 'Authorization' con formato "Bearer <token>".
     * Debe verificar el prefijo de forma insensible a mayúsculas y retornar el token limpio.
     * Si no cumple el formato, devuelve null.
     */
    public static String pasoExtra04(String authorizationHeader) {
        // GUÍA: teoría 6.5 (Bearer <token>). Es el formato de los JWT (bloque 18).
        // 1. Si es null -> null.
        // 2. Comprueba el prefijo "Bearer " de forma INSENSIBLE a mayúsculas:
        //    if (authorizationHeader.length() < 7
        //        || !authorizationHeader.substring(0,7).equalsIgnoreCase("Bearer ")) return null;
        // 3. Devuelve el resto: authorizationHeader.substring(7).trim().
        // OJO: el test pasa "Bearer token123" y espera "token123" (sin el prefijo).
        // CULTURA: este es literalmente el primer paso de un filtro de seguridad
        //          JWT que verás en b18_sec.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * Reto Extra 5: Extractor de IP real del cliente (X-Forwarded-For).
     * Obtiene la dirección IP real del cliente a partir de la cabecera 'X-Forwarded-For'.
     * Dado que puede contener múltiples IPs separadas por coma debido a proxies intermediarios,
     * se debe retornar la primera IP de la lista. Si es nula o vacía, devuelve "unknown".
     */
    public static String pasoExtra05(String xForwardedFor) {
        // GUÍA: teoría 6.5. X-Forwarded-For = "ip-cliente, proxy1, proxy2".
        // La IP real del cliente es la PRIMERA de la lista.
        // 1. Si es null/blank -> "unknown".
        // 2. return xForwardedFor.split(",")[0].trim();
        // OJO: el test pasa "192.168.1.1, 10.0.0.1" y espera "192.168.1.1".
        // CULTURA: tras un balanceador, request.getRemoteAddr() te da la IP del
        //          PROXY, no la del usuario; por eso se lee esta cabecera.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * Reto Extra 6: Categorización de dispositivo desde User-Agent.
     * Analiza la cabecera 'User-Agent' para clasificar el dispositivo de origen en
     * "Mobile", "Tablet" o "Desktop".
     */
    public static String pasoExtra06(String userAgent) {
        // GUÍA: teoría 6.5. Clasifica el User-Agent en Mobile/Tablet/Desktop por
        // palabras clave. Devuelve SIEMPRE algo no nulo.
        // 1. Si userAgent es null -> "Desktop" (default razonable).
        // 2. Pásalo a minúsculas y comprueba en orden:
        //    - contiene "tablet" o "ipad" -> "Tablet"
        //    - contiene "mobile" o "android" o "iphone" -> "Mobile"
        //    - si no -> "Desktop".
        // OJO: el test solo pide assertNotNull con "Mozilla/5.0" (cae en Desktop):
        //      basta no devolver null, pero cubre las tres ramas por claridad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * Reto Extra 7: Inyección aditiva de cabeceras de seguridad.
     * Inyecta cabeceras recomendadas de seguridad (como "X-Content-Type-Options: nosniff"
     * y "X-Frame-Options: DENY") en un ResponseEntity.BodyBuilder.
     */
    public static ResponseEntity.BodyBuilder pasoExtra07(ResponseEntity.BodyBuilder builder) {
        // GUÍA: teoría 6.5 (cabeceras de seguridad casi gratis). El builder es
        // fluido: cada .header(...) devuelve el builder.
        // return builder
        //     .header("X-Content-Type-Options", "nosniff")
        //     .header("X-Frame-Options", "DENY");
        // - nosniff: impide que el navegador "adivine" el Content-Type.
        // - DENY: prohíbe meter tu página en un <iframe> (anti clickjacking).
        // OJO: el test solo pide assertNotNull; devuelve el builder, no null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * Reto Extra 8: Comprobación de soporte de compresión (Accept-Encoding).
     * Analiza la cabecera 'Accept-Encoding' para determinar si el cliente soporta compresión GZIP
     * comprobando si el valor "gzip" está presente (ignora mayúsculas/minúsculas).
     */
    public static boolean pasoExtra08(String acceptEncoding) {
        // GUÍA: teoría 6.5. Accept-Encoding lista las compresiones que el cliente
        // acepta ("gzip, deflate, br"). ¿Soporta gzip?
        // 1. Si es null -> false.
        // 2. return acceptEncoding.toLowerCase().contains("gzip");
        // OJO: el test pasa "gzip, deflate" -> true. Compara en minúsculas por si
        //      llega "GZIP". Enlaza con Ej058.pasoExtra08 (la compresión real).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * Reto Extra 9: Parseo de cabeceras multivalor avanzadas.
     * Procesa una cabecera personalizada multivalor separada por comas (ej: "tag1, tag2, tag3")
     * limpiando espacios y devolviendo una lista de cadenas con cada valor independiente.
     */
    public static java.util.List<String> pasoExtra09(String customHeaderValue) {
        // GUÍA: teoría 1.3 (streams) sobre una cabecera multivalor "a, b, c".
        // 1. Si es null/blank -> List.of() (lista vacía).
        // 2. Parte por comas, recorta espacios y descarta vacíos:
        //    return Arrays.stream(customHeaderValue.split(","))
        //              .map(String::trim).filter(s -> !s.isEmpty()).toList();
        // OJO: el test pasa "admin, user" -> ["admin","user"] (sin el espacio).
        //      Solo comprueba assertNotNull, pero limpiar espacios es lo correcto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * Reto Extra 10: Validación de cabecera de firma (X-Signature).
     * Valida la firma de integridad 'X-Signature' de una petición contra la firma esperada,
     * calculada como el hash MD5 en hexadecimal del cuerpo del mensaje 'body'.
     * Si las firmas coinciden, devuelve true, de lo contrario false.
     */
    public static boolean pasoExtra10(String signature, String body) {
        // GUÍA: integridad de petición: la firma esperada es el MD5(body) en hex.
        // 1. Calcula el MD5 hex de 'body' (mismo método que Ej057.pasoExtra05;
        //    plantéate extraer un helper md5Hex(String) reutilizable).
        // 2. Compáralo con 'signature' (equals; o equalsIgnoreCase por si el hex
        //    viene en mayúsculas).
        // 3. Si signature o body son null -> false.
        // OJO: el test pasa ("wrongsignature","hello"): MD5("hello") es
        //      "5d41402..." ≠ "wrongsignature" -> false. Asegúrate de NO
        //      devolver true por error (no inviertas la comparación).
        // CULTURA: los webhooks (Stripe, GitHub) firman así su payload para que
        //          verifiques que el mensaje no se ha manipulado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
