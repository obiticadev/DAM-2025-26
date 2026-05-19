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
}
