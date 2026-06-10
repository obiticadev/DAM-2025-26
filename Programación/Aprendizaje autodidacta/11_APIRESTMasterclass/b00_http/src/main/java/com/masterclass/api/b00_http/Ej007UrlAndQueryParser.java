package com.masterclass.api.b00_http;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Ejercicio 007 · Parser de URL y query string.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.2).
 */
public final class Ej007UrlAndQueryParser {

    private Ej007UrlAndQueryParser() {
    }

    /**
     * Devuelve la parte de ruta de una URL relativa (sin la query).
     *
     * @param url ruta tipo {@code "/productos/12?expand=true&lang=es"}
     * @return solo la ruta, p.ej. {@code "/productos/12"}
     */
    public static String pathOnly(String url) {
        // TODO 1: si url es null, devuelve "" (defensa de entrada).
        // TODO 2: localiza el índice del primer '?'.
        // TODO 3: si no hay '?', devuelve la url tal cual (no tiene query).
        // TODO 4: si hay '?', devuelve la subcadena anterior a esa posición.
        return "";
    }

    /**
     * Parsea la query string a un mapa preservando el orden.
     *
     * @param url URL relativa con o sin query
     * @return mapa clave→valor; vacío si no hay query; valor "" si la clave no trae '='
     */
    public static Map<String, String> queryParams(String url) {
        Map<String, String> result = new LinkedHashMap<>();
        // TODO 5: si url es null o no contiene '?', devuelve el mapa vacío.
        // TODO 6: extrae la subcadena posterior al primer '?'.
        // TODO 7: separa esa subcadena por '&' en pares.
        // TODO 8: para cada par, divide por el PRIMER '=' (el valor puede contener '=').
        // TODO 9: si el par no tiene '=', guarda la clave con valor "".
        // TODO 10: inserta en 'result' respetando el orden de aparición (LinkedHashMap ya lo hace).
        return result;
    }

    public static void main(String[] args) {
        String u = "/productos/12?expand=true&lang=es";
        System.out.println(pathOnly(u));
        System.out.println(queryParams(u));
    }

    /**
     * RETO EXTRA 1: Extracción de múltiples valores para una misma clave.
     * En las consultas HTTP es común repetir la misma clave de consulta para enviar una lista
     * de valores (ej. "/buscar?tag=java&tag=rest&tag=web").
     *
     * @param url la URL completa o relativa
     * @param clave la clave de la que queremos extraer los valores
     * @return la lista de todos los valores encontrados para esa clave, en orden de aparición; 
     *         lista vacía si no existe la clave, si la URL es nula o no tiene query.
     */
    public static java.util.List<String> extraerValoresMultiplesQuery(String url, String clave) {
        // GUÍA: teoría 0.8 (una clave puede repetirse).
        // OJO: NO puedes reutilizar queryParams() aquí: un Map machaca los
        // duplicados (te quedarías solo con el último "tag"). Hay que iterar.
        // 1. url o clave null, o sin '?' → List.of().
        // 2. Toma la query (lo posterior al '?') y divide por '&'.
        // 3. Para cada par "k=v": divide por el primer '=' y, si k.equals(clave),
        //    añade v a la lista (en orden).
        // 4. Devuelve la lista (vacía si ninguna coincidió).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerValoresMultiplesQuery");
    }

    /**
     * RETO EXTRA 2: Decodificación segura de parámetros codificados en porcentaje (URL-encoding).
     * Los parámetros de consulta en una URL suelen estar codificados para soportar caracteres
     * especiales, espacios (como '+' o '%20') y tildes (ej. "nombre=Juan+P%C3%A9rez").
     *
     * @param url la URL que contiene la query string
     * @return un mapa de claves y valores completamente decodificados en UTF-8;
     *         mapa vacío si no hay query o la entrada es nula.
     */
    public static Map<String, String> queryParamsDecodificados(String url) {
        // GUÍA: teoría 0.8 (percent-encoding) y error común nº6.
        // 1. Parte de la lógica de queryParams(): separa por '&' y por el primer '='.
        // 2. DESPUÉS de separar, decodifica clave y valor con:
        //    URLDecoder.decode(s, StandardCharsets.UTF_8)
        //    (decode ya convierte '+' en espacio y %C3%A9 en 'é').
        // ORDEN SAGRADO: primero split, luego decode. Si decodificas antes, un
        // valor que contenga %26 (el '&' escapado) rompería tu split.
        // El test: "Juan+P%C3%A9rez" → "Juan Pérez", "citt%C3%A0" → "città".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para queryParamsDecodificados");
    }

    /**
     * RETO EXTRA 3: Validación robusta del esquema seguro HTTPS.
     * En arquitecturas REST modernas, forzar HTTPS es primordial para asegurar el canal.
     *
     * @param url URL a analizar
     * @return true si la URL es absoluta e inicia con el protocolo https:// (fácilmente extensible, insensible a mayúsculas)
     */
    public static boolean esHttpsEsquema(String url) {
        // GUÍA:
        // 1. null → false.
        // 2. true si url.toLowerCase().startsWith("https://")
        //    (el test manda "HTTPS://..." en mayúsculas: por eso el toLowerCase).
        // OJO: "http://" debe dar false; el startsWith con "https://" completo
        // (barras incluidas) ya lo descarta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esHttpsEsquema");
    }

    /**
     * RETO EXTRA 4: Extracción de Host (dominio/IP) de una URL absoluta.
     * Es crucial para identificar de dónde proviene la petición o validar dominios de confianza (CORS/CSRF).
     *
     * @param url URL absoluta (ej. "https://api.example.com:8080/v1/usuarios")
     * @return el host extraído (ej. "api.example.com"), o cadena vacía si es relativa o inválida
     */
    public static String extraerHost(String url) {
        // GUÍA: hazlo a mano (es el objetivo del bloque), por anatomía de URL (0.8):
        // 1. null o sin "://" → "" (es relativa: el test pasa "/v1/usuarios").
        // 2. Salta el esquema: todo lo posterior a "://".
        // 3. El host termina en el primer ':' (puerto), '/' (ruta) o '?' (query),
        //    lo que aparezca antes; si no aparece ninguno, es todo el resto.
        // PISTA: busca los tres índices con indexOf y quédate con el menor >= 0.
        // ALTERNATIVA "de producción": new java.net.URI(url).getHost() — pruébala
        // después para comprobar tu versión manual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerHost");
    }

    /**
     * RETO EXTRA 5: Extracción de puerto con fallback predeterminado.
     * Los servidores pueden declarar un puerto explícito, o bien usar los puertos por defecto de la especificación web.
     *
     * @param url URL absoluta
     * @return el puerto explícito si existe en la URL; si no existe, devuelve 443 para HTTPS, 80 para HTTP; -1 si no se reconoce
     */
    public static int extraerPuertoConPredeterminados(String url) {
        // GUÍA:
        // 1. null o sin "://" → -1.
        // 2. Aísla la parte "host[:puerto]" (reutiliza la lógica de extraerHost,
        //    pero esta vez te interesa lo que hay DESPUÉS del ':').
        // 3. Si hay ':' tras el host → parsea el número (try/catch → -1 si basura).
        // 4. Si NO hay puerto explícito: https → 443, http → 80, otro esquema → -1.
        // Tests: "http://localhost:8080/v1" → 8080; "https://example.com" → 443;
        // "http://example.com" → 80.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerPuertoConPredeterminados");
    }

    /**
     * RETO EXTRA 6: Normalización de barras diagonales redundantes de ruta.
     * Las APIs REST son sensibles a la duplicación de barras en el path (ej. "///v1//productos//").
     *
     * @param path la ruta sucia a limpiar
     * @return la ruta normalizada con barras simples y sin barra final (a menos que sea la raíz "/")
     */
    public static String normalizarBarrasDeRuta(String path) {
        // GUÍA:
        // 1. null o vacía → "/".
        // 2. Colapsa barras repetidas: path.replaceAll("/+", "/")
        //    ("///v1//productos//" → "/v1/productos/").
        // 3. Si termina en '/' Y no es la raíz "/", quita esa barra final.
        // Tests: "///v1//productos//" → "/v1/productos"; "/" → "/" (intacta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarBarrasDeRuta");
    }

    /**
     * RETO EXTRA 7: Obtener segmento individual de ruta por su posición.
     * Utilizado para extraer IDs o recursos de rutas de plantilla como "/v1/usuarios/{id}/pedidos".
     *
     * @param url la URL completa o relativa
     * @param index índice base cero del segmento deseado (ej. para "/v1/usuarios/12", 0 es "v1", 1 es "usuarios")
     * @return el segmento de la ruta en la posición indicada; o vacío si no existe o el índice está fuera de rango
     */
    public static String obtenerSegmentoRuta(String url, int index) {
        // GUÍA: así resuelve Spring los @PathVariable por debajo.
        // 1. null o index < 0 → "".
        // 2. Quédate solo con el path (reutiliza pathOnly: fuera la query).
        // 3. Divide por '/'. CUIDADO: "/v1/usuarios".split("/") produce un
        //    PRIMER ELEMENTO VACÍO ("", "v1", "usuarios") porque la ruta empieza
        //    por '/'. Filtra los vacíos o ajusta el índice.
        // 4. Si index >= número de segmentos → ""; si no, devuelve el segmento.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerSegmentoRuta");
    }

    /**
     * RETO EXTRA 8: Construcción de Query String determinista y ordenada.
     * Para maximizar el acierto de caché en servidores proxy (CDN), los parámetros de consulta
     * deben construirse siempre ordenados alfabéticamente por clave y con codificación UTF-8.
     *
     * @param params mapa de parámetros multi-valor
     * @return la query string resultante (sin el '?' inicial), ej. "activo=true&tag=java&tag=rest"
     */
    public static String construirQueryStringAlfabetica(Map<String, java.util.List<String>> params) {
        // GUÍA: la operación INVERSA a parsear — construir.
        // 1. null o vacío → "".
        // 2. Ordena las claves alfabéticamente (el test pasa Map.of, cuyo orden
        //    es aleatorio: new TreeMap<>(params) las ordena solo).
        // 3. Por cada clave, y por cada valor de su lista EN ORDEN, añade "clave=valor".
        // 4. Une todos los pares con '&' (StringJoiner o String.join).
        // Test: {b:[2], a:[1,3]} → "a=1&a=3&b=2".
        // CULTURA: las CDN cachean por URL exacta; "?a=1&b=2" y "?b=2&a=1" serían
        // DOS entradas de caché distintas. Orden determinista = más aciertos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirQueryStringAlfabetica");
    }

    /**
     * RETO EXTRA 9: Validación de URL absoluta y segura.
     * Prevenir ataques de Carriage Return Line Feed (CRLF Injection) o redirecciones abiertas en cabeceras de ubicación.
     *
     * @param url URL a auditar
     * @return true si la URL es sintácticamente bien formada y no contiene saltos de línea (\r, \n) ni caracteres peligrosos
     */
    public static boolean esUrlAbsolutaSegura(String url) {
        // GUÍA: el mismo ataque CRLF que viste en Ej002 reto 10, ahora en URLs.
        // (Escenario: tu API pone esta URL en una cabecera Location; si contiene
        // \r\n, el atacante inyecta cabeceras falsas.)
        // 1. null o en blanco → false.
        // 2. Si contiene "\r", "\n" o espacios → false.
        // 3. Debe ser absoluta: empieza por "http://" o "https://" (ignora mayúsculas).
        // Tests: "https://example.com" ✔; con "\r\nLocation:..." inyectado ✘.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUrlAbsolutaSegura");
    }

    /**
     * RETO EXTRA 10: Eliminación selectiva de un parámetro de la query string.
     * Permite limpiar URLs de rastreo (como 'utm_source') antes de procesarlas en la API.
     *
     * @param url URL de entrada
     * @param claveAEliminar clave del parámetro a remover por completo
     * @return la URL reconstruida sin el parámetro especificado ni sus valores, conservando el resto intacto
     */
    public static String eliminarParametroConsulta(String url, String claveAEliminar) {
        // GUÍA: parsear → filtrar → reconstruir.
        // 1. Si url es null o no tiene '?' → devuélvela tal cual.
        // 2. Separa path (pathOnly) y query.
        // 3. Divide la query por '&' y descarta los pares cuya clave (lo
        //    anterior al primer '=') sea claveAEliminar. OJO: elimina TODAS las
        //    apariciones ("a=1&b=2&a=3" pierde las dos "a").
        // 4. Reconstruye: si quedan pares → path + "?" + String.join("&", restantes);
        //    si no queda ninguno → solo el path SIN '?' (test: "/p?a=1" → "/p").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para eliminarParametroConsulta");
    }

}
