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
}
