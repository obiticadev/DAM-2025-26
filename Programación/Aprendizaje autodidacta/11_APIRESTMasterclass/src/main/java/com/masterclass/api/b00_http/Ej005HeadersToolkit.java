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
        // TODO extra 1: busca la cabecera ignorando mayúsculas/minúsculas. Si existe,
        // divide su valor por comas ',' y devuelve una lista con cada fragmento recortado (trim).
        return java.util.Collections.emptyList();
    }

    /**
     * RETO EXTRA 2: Extracción robusta de token Bearer.
     * Los clientes pueden enviar múltiples espacios entre "Bearer" y el token (ej. "Bearer    xyz.abc").
     * 
     * @param headers mapa de cabeceras
     * @return el token limpio; "" si no se encuentra o el esquema no es Bearer
     */
    public static String bearerTokenSeguro(Map<String, String> headers) {
        // TODO extra 2: busca la cabecera "Authorization". Si existe, comprueba que comience por "bearer"
        // (case-insensitive) y extrae el resto de la cadena eliminando todos los espacios sobrantes.
        return "";
    }

    /**
     * RETO EXTRA 3: Validar esquema de autorización.
     * 
     * @param headers mapa de cabeceras
     * @param esquema esquema buscado (ej. "Basic", "Bearer", "ApiKey")
     * @return true si la cabecera Authorization está presente y utiliza exactamente dicho esquema (case-insensitive)
     */
    public static boolean esEsquemaDeAutorizacion(Map<String, String> headers, String esquema) {
        // TODO extra 3: valida de forma segura si la cabecera Authorization está presente y el
        // esquema coincide con el parámetro de entrada.
        return false;
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
        // TODO extra 4: crea un nuevo mapa. Recorre las cabeceras originales y guarda
        // cada entrada convirtiendo la clave estrictamente a minúsculas.
        return java.util.Collections.emptyMap();
    }

    /**
     * RETO EXTRA 5: Extracción de cabeceras numéricas de forma segura.
     * 
     * @param headers mapa de cabeceras
     * @param name nombre de la cabecera (ej. "Content-Length" o "X-Rate-Limit")
     * @return el valor convertido a long; -1 si no existe o el formato no es numérico
     */
    public static long obtenerCabeceraNumerica(Map<String, String> headers, String name) {
        // TODO extra 5: busca el valor de la cabecera (case-insensitive). Si existe,
        // trata de parsearlo a long. Si falla o no existe, devuelve -1 de manera controlada.
        return -1;
    }

    /**
     * RETO EXTRA 6: Detección de petición AJAX.
     * Las aplicaciones web clásicas envían una cabecera para distinguirse de navegaciones convencionales.
     * 
     * @param headers mapa de cabeceras
     * @return true si la cabecera "X-Requested-With" es igual a "XMLHttpRequest" (case-insensitive)
     */
    public static boolean esPeticionAjax(Map<String, String> headers) {
        // TODO extra 6: busca la cabecera "X-Requested-With" y comprueba si coincide con el estándar de AJAX.
        return false;
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
        // TODO extra 7: busca de forma priorizada las cabeceras "X-Forwarded-For", "X-Real-IP" o "Client-IP".
        // Devuelve el primer valor encontrado que no esté vacío. Si hay una lista en X-Forwarded-For (ej. "1.1.1.1, 2.2.2.2"),
        // toma el primer elemento (que es el cliente original).
        return "";
    }

    /**
     * RETO EXTRA 8: Identificación de dispositivo móvil.
     * 
     * @param headers mapa de cabeceras
     * @return true si la cabecera "User-Agent" contiene palabras como "Mobile", "Android", "iPhone"
     */
    public static boolean esAgenteMovil(Map<String, String> headers) {
        // TODO extra 8: localiza "User-Agent" (case-insensitive). Si existe, comprueba
        // si contiene cadenas comunes de plataformas móviles.
        return false;
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
        // TODO extra 9: busca la cabecera, parsea su fecha en formato IMF-fixdate (RFC 1123)
        // y devuélvela como java.time.Instant de forma segura dentro de un Optional.
        return Optional.empty();
    }

    /**
     * RETO EXTRA 10: Validación de cabecera de control CORS.
     * 
     * @param headers mapa de cabeceras
     * @return true si la petición contiene la cabecera "Origin" que indica una petición entre dominios
     */
    public static boolean esPeticionCors(Map<String, String> headers) {
        // TODO extra 10: comprueba si la cabecera "Origin" está presente de forma case-insensitive.
        return false;
    }

}
