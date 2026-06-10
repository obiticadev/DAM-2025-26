package com.masterclass.api.b00_http;

import java.util.Map;
import java.util.Optional;

/**
 * Ejercicio 005 · Caja de herramientas de cabeceras HTTP.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.2).
 *
 * <p>Las cabeceras HTTP son case-insensitive en el nombre. Implementa búsquedas
 * robustas que no dependan de mayúsculas/minúsculas.
 */
public final class Ej005HeadersToolkit {

    private Ej005HeadersToolkit() {
    }

    /**
     * Busca el valor de una cabecera ignorando mayúsculas/minúsculas en el nombre.
     *
     * @param headers mapa de cabeceras tal cual llegaron
     * @param name    nombre buscado (p.ej. "content-type")
     * @return Optional con el valor, o vacío si no existe
     */
    public static Optional<String> get(Map<String, String> headers, String name) {
        // TODO 1: si headers es null o name es null, devuelve Optional.empty().
        // TODO 2: recorre las entradas del mapa.
        // TODO 3: compara cada clave con 'name' usando equalsIgnoreCase.
        // TODO 4: al primer match, devuelve Optional.of(valor).
        // TODO 5: si ninguna clave coincide, devuelve Optional.empty().
        return Optional.empty();
    }

    /**
     * Indica si una cabecera está presente (case-insensitive).
     *
     * @param headers mapa de cabeceras
     * @param name    nombre buscado
     * @return true si existe
     */
    public static boolean has(Map<String, String> headers, String name) {
        // TODO 6: reutiliza get(...) y devuelve isPresent() (no reimplementes el bucle).
        return false;
    }

    /**
     * Extrae el token Bearer de la cabecera Authorization.
     *
     * @param headers mapa de cabeceras
     * @return el token sin el prefijo "Bearer ", o cadena vacía si no hay
     */
    public static String bearerToken(Map<String, String> headers) {
        // TODO 7: usa get(headers, "Authorization"); si está ausente devuelve "".
        // TODO 8: si el valor NO empieza por "Bearer " devuelve "" (esquema no soportado).
        // TODO 9: recorta el prefijo "Bearer " (7 caracteres) para quedarte con el token.
        // TODO 10: devuelve el token resultante sin espacios sobrantes.
        return "";
    }

    public static void main(String[] args) {
        var h = Map.of("Content-Type", "application/json", "Authorization", "Bearer abc.def");
        System.out.println(get(h, "content-type"));
        System.out.println(bearerToken(h));
    }

    /**
     * RETO EXTRA 1: Parsear cabeceras con múltiples valores.
     * En HTTP, una cabecera puede contener múltiples valores separados por comas.
     * Ejemplo: "Accept: text/html, application/xhtml+xml, application/xml"
     * 
     * @param headers mapa de cabeceras
     * @param name nombre de la cabecera a buscar
     * @return lista de valores separados y recortados; lista vacía si no existe
     */
    public static java.util.List<String> obtenerMultiplesValores(Map<String, String> headers, String name) {
        // GUÍA: teoría 0.6.
        // 1. Busca el valor con get(headers, name) (case-insensitive gratis).
        //    Si no existe → List.of() (lista vacía).
        // 2. Divide por comas: split(",").
        // 3. trim() a cada trozo y descarta los vacíos.
        // PISTA con streams:
        //   Arrays.stream(valor.split(",")).map(String::trim)
        //         .filter(s -> !s.isEmpty()).toList();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMultiplesValores");
    }

    /**
     * RETO EXTRA 2: Extracción robusta de token Bearer.
     * Los clientes pueden enviar múltiples espacios entre "Bearer" y el token (ej. "Bearer    xyz.abc").
     * 
     * @param headers mapa de cabeceras
     * @return el token limpio; "" si no se encuentra o el esquema no es Bearer
     */
    public static String bearerTokenSeguro(Map<String, String> headers) {
        // GUÍA: versión endurecida de bearerToken().
        // 1. get(headers, "Authorization"); ausente → "".
        // 2. Comprueba que empieza por "Bearer" (puedes tolerar mayúsculas).
        // 3. EL TRUCO del test ("Bearer    customTokenHere   "): hay VARIOS
        //    espacios entre el esquema y el token, y espacios al final.
        //    - Quita el prefijo "Bearer" y haz trim() al resto, o
        //    - split("\\s+") y toma el segundo token (regex \s+ = 1 o más espacios).
        // 4. Devuelve el token limpio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para bearerTokenSeguro");
    }

    /**
     * RETO EXTRA 3: Validar esquema de autorización.
     * 
     * @param headers mapa de cabeceras
     * @param esquema esquema buscado (ej. "Basic", "Bearer", "ApiKey")
     * @return true si la cabecera Authorization está presente y utiliza exactamente dicho esquema (case-insensitive)
     */
    public static boolean esEsquemaDeAutorizacion(Map<String, String> headers, String esquema) {
        // GUÍA: el esquema es la PRIMERA palabra del valor de Authorization
        // ("Bearer abc.def" → esquema "Bearer").
        // 1. get(headers, "Authorization"); ausente → false.
        // 2. Toma la primera palabra: valor.trim().split("\\s+")[0].
        // 3. Compárala con 'esquema' usando equalsIgnoreCase (el test prueba
        //    "Bearer" y "bearer", y "Basic" debe dar false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEsquemaDeAutorizacion");
    }

    /**
     * RETO EXTRA 4: Normalización completa de cabeceras.
     * En frameworks de producción, es común trabajar con un mapa donde todas las claves estén en minúsculas
     * para optimizar búsquedas.
     * 
     * @param headers mapa original
     * @return un nuevo mapa con todas las claves en minúsculas y sus valores originales
     */
    public static Map<String, String> normalizarClaves(Map<String, String> headers) {
        // GUÍA: así lo hacen los frameworks de verdad — normalizan UNA vez al
        // entrar la petición y luego todas las búsquedas son O(1) sin
        // equalsIgnoreCase en bucle.
        // 1. null → Map.of().
        // 2. Crea un mapa nuevo; por cada entry guarda (clave.toLowerCase(), valor).
        // PISTA bucle: forEach((k, v) -> resultado.put(k.toLowerCase(), v));
        // PISTA streams: Collectors.toMap(e -> e.getKey().toLowerCase(), Map.Entry::getValue)
        // (con streams, cuidado si hubiera claves duplicadas tras normalizar:
        // toMap lanza excepción; el merge function (a, b) -> b lo resuelve).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarClaves");
    }

    /**
     * RETO EXTRA 5: Extracción de cabeceras numéricas de forma segura.
     * 
     * @param headers mapa de cabeceras
     * @param name nombre de la cabecera (ej. "Content-Length" o "X-Rate-Limit")
     * @return el valor convertido a long; -1 si no existe o el formato no es numérico
     */
    public static long obtenerCabeceraNumerica(Map<String, String> headers, String name) {
        // GUÍA: el patrón "parseo defensivo" que usarás mil veces.
        // 1. get(headers, name); ausente → -1.
        // 2. try { return Long.parseLong(valor.trim()); }
        //    catch (NumberFormatException e) { return -1; }
        // El test mete "abc" en X-Limit a propósito: el catch ES el ejercicio.
        // Nunca dejes que una cabecera basura tumbe tu servidor con una excepción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCabeceraNumerica");
    }

    /**
     * RETO EXTRA 6: Detección de petición AJAX.
     * Las aplicaciones web clásicas envían una cabecera para distinguirse de navegaciones convencionales.
     * 
     * @param headers mapa de cabeceras
     * @return true si la cabecera "X-Requested-With" es igual a "XMLHttpRequest" (case-insensitive)
     */
    public static boolean esPeticionAjax(Map<String, String> headers) {
        // GUÍA: combina get() + comparación case-insensitive del valor.
        // get(headers, "X-Requested-With") y compara con "XMLHttpRequest"
        // usando equalsIgnoreCase. Ausente → false.
        // PISTA con Optional: .map(v -> v.equalsIgnoreCase("XMLHttpRequest")).orElse(false)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPeticionAjax");
    }

    /**
     * RETO EXTRA 7: Resolución de la dirección IP real del cliente (Proxy / Cloudflare).
     * Cuando nuestra API está detrás de un balanceador de carga o red CDN, la IP del socket es del proxy.
     * La IP real del cliente se inyecta en cabeceras especiales.
     * 
     * @param headers mapa de cabeceras
     * @return IP real detectada; "" si no se encuentra ninguna cabecera proxy
     */
    public static String resolverDireccionIpCliente(Map<String, String> headers) {
        // GUÍA: la cabecera estándar de facto es "X-Forwarded-For" y acumula la
        // cadena de saltos: "ipCliente, ipProxy1, ipProxy2". La IP REAL es la
        // PRIMERA de la lista.
        // 1. get(headers, "X-Forwarded-For"); ausente → "".
        // 2. Divide por comas y devuelve el primer trozo con trim()
        //    ("192.168.1.1, 10.0.0.1" → "192.168.1.1").
        // PISTA: reutiliza obtenerMultiplesValores y toma el índice 0.
        // CULTURA: en producción esta cabecera la puede FALSIFICAR el cliente;
        // solo es fiable si tu proxy la sobrescribe. No la uses para seguridad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverDireccionIpCliente");
    }

    /**
     * RETO EXTRA 8: Identificación de dispositivo móvil.
     * 
     * @param headers mapa de cabeceras
     * @return true si la cabecera "User-Agent" contiene palabras como "Mobile", "Android", "iPhone"
     */
    public static boolean esAgenteMovil(Map<String, String> headers) {
        // GUÍA:
        // 1. get(headers, "User-Agent"); ausente → false.
        // 2. Pasa el valor a minúsculas y comprueba si CONTIENE alguna de:
        //    "mobile", "android", "iphone".
        // PISTA: ua.contains("mobile") || ua.contains("android") || ua.contains("iphone")
        // (el test manda "...iPhone; CPU iPhone OS 14_0..." → por eso minúsculas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAgenteMovil");
    }

    /**
     * RETO EXTRA 9: Extracción y parseo de cabeceras de Fecha.
     * Cabeceras como If-Modified-Since contienen fechas que debemos comparar.
     * 
     * @param headers mapa de cabeceras
     * @param name nombre de la cabecera de fecha
     * @return un Optional con el Instant correspondiente, o vacío si no existe o es inválida
     */
    public static Optional<java.time.Instant> obtenerCabeceraFecha(Map<String, String> headers, String name) {
        // GUÍA: el formato de fecha HTTP ("Sun, 06 Nov 1994 08:49:37 GMT") ya lo
        // viste en Ej002; ahora lo PARSEAS en vez de generarlo.
        // 1. get(headers, name); ausente → Optional.empty().
        // 2. Dentro de un try:
        //    ZonedDateTime zdt = ZonedDateTime.parse(valor,
        //        DateTimeFormatter.RFC_1123_DATE_TIME);
        //    return Optional.of(zdt.toInstant());
        // 3. catch (DateTimeParseException e) → Optional.empty()
        //    (una fecha corrupta no debe tumbar el servidor: mismo patrón
        //    defensivo que en obtenerCabeceraNumerica).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCabeceraFecha");
    }

    /**
     * RETO EXTRA 10: Validación de cabecera de control CORS.
     * 
     * @param headers mapa de cabeceras
     * @return true si la petición contiene la cabecera "Origin" que indica una petición entre dominios
     */
    public static boolean esPeticionCors(Map<String, String> headers) {
        // GUÍA: una línea — return has(headers, "Origin");
        // CULTURA: el navegador añade "Origin" automáticamente cuando un script
        // de un dominio llama a otro dominio. El servidor decide si lo permite
        // respondiendo Access-Control-Allow-Origin. Esto es CORS, y lo
        // configurarás en serio en el bloque de seguridad (b18).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPeticionCors");
    }

}
