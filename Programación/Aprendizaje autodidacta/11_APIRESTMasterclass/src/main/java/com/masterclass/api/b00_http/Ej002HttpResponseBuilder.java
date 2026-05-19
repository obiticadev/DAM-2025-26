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
}
