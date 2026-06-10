package com.masterclass.api.b00_http;

import java.util.Map;

/**
 * Ejercicio 002 · Constructor de respuestas HTTP.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.2).
 *
 * <p>Operación inversa al 001: a partir de status, headers y body debes serializar
 * una respuesta HTTP/1.1 textual válida, byte a byte.
 */
public final class Ej002HttpResponseBuilder {

    private Ej002HttpResponseBuilder() {
    }

    /**
     * Devuelve la frase de motivo estándar para un código de estado.
     *
     * @param status código HTTP (200, 201, 404, 500, ...)
     * @return frase de motivo, p.ej. {@code "OK"} para 200; {@code "Unknown"} si no se conoce
     */
    public static String reasonPhrase(int status) {
        // TODO 1: mapea 200 -> "OK" y 201 -> "Created".
        // TODO 2: mapea 204 -> "No Content".
        // TODO 3: mapea 400 -> "Bad Request" y 401 -> "Unauthorized".
        // TODO 4: mapea 404 -> "Not Found".
        // TODO 5: mapea 500 -> "Internal Server Error".
        // TODO 6: cualquier otro código -> "Unknown".
        return "";
    }

    /**
     * Serializa una respuesta HTTP/1.1 completa.
     *
     * @param status  código de estado
     * @param headers cabeceras a incluir (puede estar vacío)
     * @param body    cuerpo; si es null o vacío, no se añade body
     * @return texto con la respuesta: línea de estado + headers + línea en blanco + body
     */
    public static String build(int status, Map<String, String> headers, String body) {
        // TODO 7: escribe la primera línea "HTTP/1.1 <status> <reasonPhrase(status)>".
        // TODO 8: añade una línea "Clave: Valor" por cada cabecera, en orden de iteración.
        // TODO 9: añade SIEMPRE una línea en blanco tras las cabeceras (separador).
        // TODO 10: si body no es null ni vacío, concaténalo después de la línea en blanco.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(build(200, Map.of("Content-Type", "application/json"), "{\"ok\":true}"));
    }

    /**
     * RETO EXTRA 1: Obtener la familia de respuesta HTTP.
     * 
     * @param status código HTTP
     * @return el nombre de la familia (ej. "Success" para 2xx, "Client Error" para 4xx, etc.)
     */
    public static String obtenerFamiliaDeRespuesta(int status) {
        // GUÍA: teoría 0.4 (el primer dígito define la familia).
        // 1. La familia se obtiene con división entera: status / 100 → 2, 3, 4, 5...
        // 2. Mapea: 1→"Informational", 2→"Success", 3→"Redirection",
        //    4→"Client Error", 5→"Server Error"; cualquier otro → "Unknown".
        // 3. Fíjate en el test: 99 → "Unknown" (99/100 = 0, fuera de las familias).
        // PISTA: un switch sobre (status / 100) queda de una limpieza ejemplar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerFamiliaDeRespuesta");
    }

    /**
     * RETO EXTRA 2: Normalización de cabeceras estándar.
     * Las cabeceras HTTP de respuesta deben tener una capitalización canónica (ej. "content-type" -> "Content-Type").
     * 
     * @param clave nombre de la cabecera
     * @param valor valor de la cabecera
     * @return la cabecera serializada en formato canónico "Nombre-Cabecera: Valor"
     */
    public static String formatearCabeceraEstandar(String clave, String valor) {
        // GUÍA: teoría 0.6.
        // 1. Divide la clave por '-': "x-cache-status" → ["x","cache","status"].
        // 2. Capitaliza cada trozo: primera letra mayúscula + resto minúsculas
        //    (substring(0,1).toUpperCase() + substring(1).toLowerCase()).
        // 3. Vuelve a unirlos con '-' → "X-Cache-Status" (String.join o StringBuilder).
        // 4. Devuelve "Clave: Valor" (ojo: espacio después de los dos puntos).
        // CUIDADO: un trozo podría ser vacío si llega "x--y"; protege el substring.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearCabeceraEstandar");
    }

    /**
     * RETO EXTRA 3: Fecha HTTP en formato IMF-fixdate (RFC 7231).
     * Toda respuesta HTTP debe llevar una cabecera "Date" en formato GMT estándar.
     * 
     * @return la fecha y hora actual formateada como "Sun, 06 Nov 1994 08:49:37 GMT"
     */
    public static String generarCabeceraFechaActual() {
        // GUÍA: java.time hace todo el trabajo.
        // 1. Obtén el instante actual EN GMT: ZonedDateTime.now(ZoneOffset.UTC)
        //    (o ZoneId.of("GMT")).
        // 2. Java ya trae el formateador exacto de HTTP:
        //    DateTimeFormatter.RFC_1123_DATE_TIME.
        // 3. Formatea y devuelve. El test solo exige que termine en "GMT";
        //    con ZoneId.of("GMT") el sufijo sale solo.
        // CULTURA: este formato ("Sun, 06 Nov 1994 08:49:37 GMT") es obligatorio
        // en la cabecera Date de toda respuesta de un servidor serio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarCabeceraFechaActual");
    }

    /**
     * RETO EXTRA 4: Validación de cabecera Location.
     * Ciertos códigos de estado de redirección requieren obligatoriamente la cabecera "Location".
     * 
     * @param status código de estado HTTP
     * @return true si es 301, 302, 307 o 308 (redirecciones que exigen Location)
     */
    public static boolean requiereCabeceraLocation(int status) {
        // GUÍA: teoría 0.4 (familia 3xx).
        // 1. Compara contra el conjunto cerrado {301, 302, 307, 308}.
        // PISTA: switch con caso múltiple (case 301, 302, 307, 308 -> true)
        //    o Set.of(301, 302, 307, 308).contains(status).
        // CULTURA: 303 y 304 son 3xx pero NO exigen Location; por eso no vale
        //    responder "es 3xx" sin más.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para requiereCabeceraLocation");
    }

    /**
     * RETO EXTRA 5: Longitud de cuerpo en bytes UTF-8.
     * En HTTP, el Content-Length representa el tamaño del cuerpo en bytes, no en caracteres de texto.
     * 
     * @param body cuerpo del mensaje
     * @return tamaño del cuerpo en bytes usando la codificación UTF-8
     */
    public static int calcularLongitudEnBytes(String body) {
        // GUÍA: bytes ≠ caracteres.
        // 1. null → 0.
        // 2. body.getBytes(StandardCharsets.UTF_8).length — y ya está.
        // POR QUÉ EXISTE ESTE RETO: "🚀".length() devuelve 2 (chars UTF-16 de Java)
        // pero ocupa 4 BYTES en UTF-8. Si calculas Content-Length con length(),
        // el cliente cortará el body a mitad de emoji/ñ/tilde. Bug clásico real.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularLongitudEnBytes");
    }

    /**
     * RETO EXTRA 6: Inyección automática de Content-Length.
     * 
     * @param headers mapa de cabeceras original
     * @param body cuerpo del mensaje
     * @return un nuevo mapa (o el mapa modificado) que contenga la cabecera "Content-Length" recalculada en bytes.
     */
    public static Map<String, String> inyectarContentLength(Map<String, String> headers, String body) {
        // GUÍA: combina los retos 2 y 5.
        // 1. OJO: el test pasa un Map.of(...), que es INMUTABLE. Si haces
        //    headers.put(...) directamente, explota con UnsupportedOperationException.
        //    Crea una copia: new LinkedHashMap<>(headers).
        // 2. Calcula la longitud reutilizando calcularLongitudEnBytes(body).
        // 3. Guarda "Content-Length" → String.valueOf(longitud) (el mapa es
        //    <String,String>: el test espera el texto "4", no el número).
        // 4. Devuelve la copia.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inyectarContentLength");
    }

    /**
     * RETO EXTRA 7: Construcción defensiva de la primera línea de estado.
     * 
     * @param status código de estado HTTP
     * @return la línea de estado HTTP (ej. "HTTP/1.1 200 OK")
     * @throws IllegalArgumentException si el estado no está en el rango estándar [100, 599]
     */
    public static String generarLineaEstadoSegura(int status) {
        // GUÍA: teoría 0.3 (línea de estado = VERSIÓN CÓDIGO FRASE).
        // 1. Si status < 100 o status > 599 → throw new IllegalArgumentException(
        //    "Status fuera de rango: " + status). Falla PRONTO y con mensaje claro.
        // 2. Si es válido, devuelve "HTTP/1.1 " + status + " " + reasonPhrase(status).
        //    Reutiliza reasonPhrase: ya la implementaste arriba.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarLineaEstadoSegura");
    }

    /**
     * RETO EXTRA 8: Negociación de tipo de contenido y charset.
     * 
     * @param headers mapa de cabeceras
     * @return el charset extraído de "Content-Type" (ej. de "text/html; charset=UTF-8" extrae "UTF-8");
     *         devuelve "ISO-8859-1" (default histórico HTTP) si no se especifica.
     */
    public static String extraerCharset(Map<String, String> headers) {
        // GUÍA: teoría 0.7 (media type = tipo/subtipo;parámetros).
        // 1. headers null o sin "Content-Type" → "ISO-8859-1" (default histórico).
        // 2. Busca en el valor la subcadena "charset=" (ignora mayúsculas:
        //    pásalo a lowercase para BUSCAR, pero devuelve el valor ORIGINAL).
        // 3. Si no aparece → "ISO-8859-1".
        // 4. Si aparece, toma lo que va detrás del '=' hasta el siguiente ';'
        //    (o el final) y devuélvelo con trim() → "UTF-8".
        // PISTA: indexOf("charset=") + 8 te posiciona justo en el valor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerCharset");
    }

    /**
     * RETO EXTRA 9: Restricciones de cuerpo (204 No Content / 304 Not Modified).
     * Ciertos códigos HTTP prohíben estrictamente retornar un body y cabeceras de longitud.
     * 
     * @param status código HTTP
     * @return true si el código prohíbe tener cuerpo por especificación (1xx, 204, 304)
     */
    public static boolean esRespuestaSinCuerpo(int status) {
        // GUÍA: teoría 0.3 (regla de oro: 204 y 304 jamás llevan body) y error común nº4.
        // 1. true si: toda la familia 1xx (status >= 100 && status < 200),
        //    o status == 204, o status == 304.
        // 2. Cualquier otro → false.
        // Es una sola expresión booleana; no necesitas if-else en cascada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRespuestaSinCuerpo");
    }

    /**
     * RETO EXTRA 10: Prevención de HTTP Response Splitting.
     * Un atacante puede inyectar saltos de línea (\r o \n) en los valores de las cabeceras para
     * inyectar una respuesta HTTP falsa completa (Web Cache Poisoning).
     * 
     * @param headers mapa de cabeceras
     * @return true si todas las cabeceras son seguras (ninguna clave ni valor contiene \r ni \n)
     */
    public static boolean esSeguroContraResponseSplitting(Map<String, String> headers) {
        // GUÍA: seguridad real, no teoría.
        // EL ATAQUE: si serializas "X-Injected: Bad\nLocation: http://evil.com",
        // el '\n' del VALOR genera una línea de header nueva que tú nunca
        // escribiste. Así se envenenan cachés y se redirige a usuarios.
        // PASOS:
        // 1. headers null → decide una política y documéntala (true es razonable:
        //    "sin cabeceras no hay nada inyectable").
        // 2. Recorre cada entry: si la CLAVE o el VALOR contienen "\r" o "\n"
        //    → return false inmediatamente.
        // 3. Si terminas el bucle sin sustos → true.
        // PISTA: contains("\r") || contains("\n"), cuatro comprobaciones por entry.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSeguroContraResponseSplitting");
    }

}
