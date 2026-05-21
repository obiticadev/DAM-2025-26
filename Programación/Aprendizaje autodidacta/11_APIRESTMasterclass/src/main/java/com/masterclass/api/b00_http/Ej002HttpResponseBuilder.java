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
        // TODO extra 1: clasifica el código en su familia correspondiente (1xx: Informational,
        // 2xx: Success, 3xx: Redirection, 4xx: Client Error, 5xx: Server Error). Devuelve "Unknown" si no está en rango.
        return "";
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
        // TODO extra 2: normaliza la clave para que la primera letra de cada palabra
        // separada por guiones '-' esté en mayúscula y el resto en minúscula.
        // Ejemplo: "content-type" -> "Content-Type", "x-cache-status" -> "X-Cache-Status".
        return "";
    }

    /**
     * RETO EXTRA 3: Fecha HTTP en formato IMF-fixdate (RFC 7231).
     * Toda respuesta HTTP debe llevar una cabecera "Date" en formato GMT estándar.
     * 
     * @return la fecha y hora actual formateada como "Sun, 06 Nov 1994 08:49:37 GMT"
     */
    public static String generarCabeceraFechaActual() {
        // TODO extra 3: genera la fecha actual del sistema formateada estrictamente
        // bajo la zona horaria GMT usando el estándar RFC 7231 / IMF-fixdate.
        // Tip: puedes usar java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.
        return "";
    }

    /**
     * RETO EXTRA 4: Validación de cabecera Location.
     * Ciertos códigos de estado de redirección requieren obligatoriamente la cabecera "Location".
     * 
     * @param status código de estado HTTP
     * @return true si es 301, 302, 307 o 308 (redirecciones que exigen Location)
     */
    public static boolean requiereCabeceraLocation(int status) {
        // TODO extra 4: valida si el código HTTP pasado pertenece al grupo de
        // redirecciones que requieren indicar a dónde redirigir al cliente.
        return false;
    }

    /**
     * RETO EXTRA 5: Longitud de cuerpo en bytes UTF-8.
     * En HTTP, el Content-Length representa el tamaño del cuerpo en bytes, no en caracteres de texto.
     * 
     * @param body cuerpo del mensaje
     * @return tamaño del cuerpo en bytes usando la codificación UTF-8
     */
    public static int calcularLongitudEnBytes(String body) {
        // TODO extra 5: calcula la longitud real del body en bytes utilizando la codificación UTF-8.
        // Ten cuidado: caracteres especiales como acentos o emojis ocupan más de 1 byte.
        return 0;
    }

    /**
     * RETO EXTRA 6: Inyección automática de Content-Length.
     * 
     * @param headers mapa de cabeceras original
     * @param body cuerpo del mensaje
     * @return un nuevo mapa (o el mapa modificado) que contenga la cabecera "Content-Length" recalculada en bytes.
     */
    public static Map<String, String> inyectarContentLength(Map<String, String> headers, String body) {
        // TODO extra 6: crea un mapa mutable a partir de headers e inyecta la cabecera "Content-Length" 
        // con el tamaño en bytes de 'body'. Si body es null o vacío, no inyectes Content-Length (o ponle 0).
        return headers;
    }

    /**
     * RETO EXTRA 7: Construcción defensiva de la primera línea de estado.
     * 
     * @param status código de estado HTTP
     * @return la línea de estado HTTP (ej. "HTTP/1.1 200 OK")
     * @throws IllegalArgumentException si el estado no está en el rango estándar [100, 599]
     */
    public static String generarLineaEstadoSegura(int status) {
        // TODO extra 7: valida que el código esté en el rango de códigos HTTP válidos [100, 599].
        // Si no lo está, lanza una excepción IllegalArgumentException. Si es válido, construye la línea de estado.
        return "";
    }

    /**
     * RETO EXTRA 8: Negociación de tipo de contenido y charset.
     * 
     * @param headers mapa de cabeceras
     * @return el charset extraído de "Content-Type" (ej. de "text/html; charset=UTF-8" extrae "UTF-8");
     *         devuelve "ISO-8859-1" (default histórico HTTP) si no se especifica.
     */
    public static String extraerCharset(Map<String, String> headers) {
        // TODO extra 8: busca la cabecera "Content-Type" (case-insensitive). Si contiene
        // un parámetro "charset=", extrae su valor. De lo contrario, devuelve "ISO-8859-1".
        return "";
    }

    /**
     * RETO EXTRA 9: Restricciones de cuerpo (204 No Content / 304 Not Modified).
     * Ciertos códigos HTTP prohíben estrictamente retornar un body y cabeceras de longitud.
     * 
     * @param status código HTTP
     * @return true si el código prohíbe tener cuerpo por especificación (1xx, 204, 304)
     */
    public static boolean esRespuestaSinCuerpo(int status) {
        // TODO extra 9: determina si bajo ninguna circunstancia el código de estado dado puede
        // contener cuerpo (familia 1xx, código 204 o código 304).
        return false;
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
        // TODO extra 10: recorre todas las claves y valores del mapa de cabeceras. Si alguna
        // contiene un carácter de salto de línea (\r o \n), devuelve false inmediatamente.
        return true;
    }

}
