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
        // TODO extra: RETO EXTRA 1: Obtener la familia de respuesta HTTP.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: RETO EXTRA 2: Normalización de cabeceras estándar.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearCabeceraEstandar");
    }

    /**
     * RETO EXTRA 3: Fecha HTTP en formato IMF-fixdate (RFC 7231).
     * Toda respuesta HTTP debe llevar una cabecera "Date" en formato GMT estándar.
     * 
     * @return la fecha y hora actual formateada como "Sun, 06 Nov 1994 08:49:37 GMT"
     */
    public static String generarCabeceraFechaActual() {
        // TODO extra: RETO EXTRA 3: Fecha HTTP en formato IMF-fixdate (RFC 7231).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: RETO EXTRA 4: Validación de cabecera Location.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: RETO EXTRA 5: Longitud de cuerpo en bytes UTF-8.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: RETO EXTRA 6: Inyección automática de Content-Length.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: RETO EXTRA 7: Construcción defensiva de la primera línea de estado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: RETO EXTRA 8: Negociación de tipo de contenido y charset.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: RETO EXTRA 9: Restricciones de cuerpo (204 No Content / 304 Not Modified).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: RETO EXTRA 10: Prevención de HTTP Response Splitting.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSeguroContraResponseSplitting");
    }

}
