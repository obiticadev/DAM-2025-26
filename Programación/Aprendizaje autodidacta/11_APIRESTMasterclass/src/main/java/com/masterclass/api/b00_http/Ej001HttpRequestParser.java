package com.masterclass.api.b00_http;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Ejercicio 001 · Parser de peticiones HTTP crudas.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (secciones 0.1 y 0.2).
 *
 * <p>Recibes una petición HTTP/1.1 como texto plano y debes descomponerla en sus
 * cuatro partes: método, ruta, mapa de headers y body. No uses librerías HTTP;
 * el objetivo es entender el formato del protocolo carácter a carácter.
 */
public final class Ej001HttpRequestParser {

    private Ej001HttpRequestParser() {
    }

    /**
     * Extrae el método HTTP (GET, POST, ...) de la línea de petición.
     *
     * @param raw petición HTTP completa como texto, con líneas separadas por "\n"
     * @return el método en mayúsculas, p.ej. {@code "GET"}; cadena vacía si raw es null/vacío
     */
    public static String method(String raw) {
        // TODO 1: si raw es null o está en blanco, devuelve "" (defensa de entrada).
        // TODO 2: aísla la primera línea (todo lo anterior al primer '\n').
        // TODO 3: divide esa línea por espacios; el token 0 es el método.
        // TODO 4: normaliza el método a mayúsculas antes de devolverlo.
        return "";
    }

    /**
     * Extrae la ruta solicitada (incluida la query string si la hay).
     *
     * @param raw petición HTTP completa como texto
     * @return la ruta, p.ej. {@code "/productos?page=2"}; "" si no se puede parsear
     */
    public static String path(String raw) {
        // TODO 5: reutiliza la lógica de obtener la primera línea (no dupliques método).
        // TODO 6: el segundo token de la línea de petición es la ruta; devuélvelo tal cual.
        return "";
    }

    /**
     * Construye el mapa de cabeceras preservando el orden de aparición.
     *
     * @param raw petición HTTP completa como texto
     * @return mapa Clave→Valor; vacío si no hay headers
     */
    public static Map<String, String> headers(String raw) {
        Map<String, String> result = new LinkedHashMap<>();
        // TODO 7: recorre las líneas desde la 2ª hasta encontrar la primera línea vacía
        //         (esa línea en blanco separa headers de body: detén el bucle ahí).
        // TODO 8: parte cada cabecera por el PRIMER ':' (el valor puede contener ':').
        // TODO 9: recorta espacios de clave y valor antes de guardarlos en 'result'.
        return result;
    }

    /**
     * Devuelve el cuerpo de la petición (lo que sigue a la línea en blanco).
     *
     * @param raw petición HTTP completa como texto
     * @return el body, o cadena vacía si no existe
     */
    public static String body(String raw) {
        // TODO 10: localiza la primera línea vacía y devuelve TODO lo que va después
        //          de ella; si no hay línea en blanco o no hay body, devuelve "".
        return "";
    }

    public static void main(String[] args) {
        String raw = "POST /usuarios HTTP/1.1\n"
                + "Host: api.demo\n"
                + "Content-Type: application/json\n"
                + "\n"
                + "{\"nombre\":\"Ana\"}";
        System.out.println("Método : " + method(raw));
        System.out.println("Ruta   : " + path(raw));
        System.out.println("Headers: " + headers(raw));
        System.out.println("Body   : " + body(raw));
    }
}
