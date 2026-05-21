package com.masterclass.api.b00_http;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ejercicio 001 · Parser de peticiones HTTP crudas.
 *
 * <p>
 * Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (secciones 0.1 y 0.2).
 *
 * <p>
 * Recibes una petición HTTP/1.1 como texto plano y debes descomponerla en sus
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
     * @return el método en mayúsculas, p.ej. {@code "GET"}; cadena vacía si raw es
     *         null/vacío
     */
    public static String method(String raw) {
        // TODO 1: si raw es null o está en blanco, devuelve "" (defensa de entrada).
        // TODO 2: aísla la primera línea (todo lo anterior al primer '\n').
        // TODO 3: divide esa línea por espacios; el token 0 es el método.
        // TODO 4: normaliza el método a mayúsculas antes de devolverlo.
        if (raw == null || raw.isEmpty()) {
            return "";
        }
        return raw.substring(0, raw.indexOf("\n")).split(" ")[0].toUpperCase();
    }

    /**
     * Extrae la ruta solicitada (incluida la query string si la hay).
     *
     * @param raw petición HTTP completa como texto
     * @return la ruta, p.ej. {@code "/productos?page=2"}; "" si no se puede parsear
     */
    public static String path(String raw) {
        // TODO 5: reutiliza la lógica de obtener la primera línea (no dupliques
        // método).
        // TODO 6: el segundo token de la línea de petición es la ruta; devuélvelo tal
        // cual.
        if (raw == null || raw.isEmpty()) {
            return "";
        }
        return raw.substring(0, raw.indexOf("\n")).split(" ")[1];
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
        // (esa línea en blanco separa headers de body: detén el bucle ahí).
        // TODO 8: parte cada cabecera por el PRIMER ':' (el valor puede contener ':').
        // TODO 9: recorta espacios de clave y valor antes de guardarlos en 'result'.
        return Arrays.asList(raw.substring(raw.indexOf("\n") + 1, raw.indexOf("\n\n")).split("\n"))
                .stream()
                .map(String::trim)
                .filter(linea -> linea.contains(":"))
                .collect(Collectors.toMap(
                        linea -> linea.split(":")[0].trim(),
                        linea -> linea.split(":")[1].trim()));

    }

    /**
     * Devuelve el cuerpo de la petición (lo que sigue a la línea en blanco).
     *
     * @param raw petición HTTP completa como texto
     * @return el body, o cadena vacía si no existe
     */
    public static String body(String raw) {
        // TODO 10: localiza la primera línea vacía y devuelve TODO lo que va después
        // de ella; si no hay línea en blanco o no hay body, devuelve "".
        if (raw == null || raw.isEmpty()) {
            return "";
        }
        int lineaEnBlanco = raw.indexOf("\n\n");
        if (lineaEnBlanco == -1) {
            return "";
        }
        String linea = raw.substring(lineaEnBlanco + 2);
        if (linea.isEmpty()) {
            return "";
        } else {
            return linea;
        }
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

    /**
     * RETO EXTRA 1: Validación defensiva extrema.
     * En APIs, los clientes a veces envían peticiones nulas, vacías o con espacios de control.
     * 
     * @param raw petición HTTP cruda
     * @return true si la petición es nula, vacía o contiene solo caracteres de control/escape (\r, \n, \t, etc.)
     */
    public static boolean esPeticionNulaOVacia(String raw) {
        // TODO extra 1: implementa una validación que devuelva true si raw es null,
        // está vacío, en blanco (sin caracteres imprimibles) o solo contiene espacios de control.
        return false;
    }

    /**
     * RETO EXTRA 2: Tolerancia de saltos de línea (\r\n vs \n).
     * El estándar HTTP exige usar \r\n como separador de líneas (CRLF), pero muchas APIs
     * toleran recibir solo \n (LF).
     * 
     * @param raw petición HTTP cruda
     * @return la primera línea completa sin caracteres \r ni \n al final; "" si no hay primera línea
     */
    public static String extraerPrimeraLineaCompleta(String raw) {
        // TODO extra 2: extrae la primera línea completa. Asegúrate de limpiar cualquier
        // carácter de retorno de carro (\r) que pudiera venir al final de la línea.
        return "";
    }

    /**
     * RETO EXTRA 3: Validación de verbos HTTP estándar.
     * No todos los métodos recibidos son válidos en la especificación HTTP o soportados por nuestra API.
     * 
     * @param raw petición HTTP cruda
     * @return true si el verbo es uno de los estándar: GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD
     */
    public static boolean validarMetodoSoportado(String raw) {
        // TODO extra 3: extrae el método de la primera línea y valida si es un verbo HTTP 
        // estándar y soportado (GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD). Ignora mayúsculas/minúsculas al validar.
        return false;
    }

    /**
     * RETO EXTRA 4: Extracción de la versión del protocolo.
     * La línea de petición HTTP tiene la estructura: METODO RUTA VERSION.
     * 
     * @param raw petición HTTP cruda
     * @return la versión del protocolo (ej. "HTTP/1.1", "HTTP/2.0"), o "" si no tiene el formato correcto
     */
    public static String extraerVersionHttp(String raw) {
        // TODO extra 4: aísla la primera línea, divídela en tokens por espacios y 
        // extrae el tercer token correspondiente a la versión. Si no existe o no tiene 3 tokens, devuelve "".
        return "";
    }

    /**
     * RETO EXTRA 5: Validación de ruta absoluta.
     * En peticiones HTTP directas, la ruta solicitada siempre debe empezar con "/".
     * 
     * @param raw petición HTTP cruda
     * @return true si la ruta empieza por "/"; false en caso contrario
     */
    public static boolean validarRutaAbsoluta(String raw) {
        // TODO extra 5: extrae la ruta del segundo token de la primera línea e
        // identifica si comienza estrictamente con el carácter barra '/'.
        return false;
    }

    /**
     * RETO EXTRA 6: Separar Query String del Path.
     * Al recibir "/buscar?q=java", el path real del recurso es "/buscar", y "q=java" es la query string.
     * 
     * @param raw petición HTTP cruda
     * @return la query string completa (ej. "q=java"), o "" si la ruta no contiene parámetros (sin '?')
     */
    public static String extraerQueryString(String raw) {
        // TODO extra 6: extrae la ruta completa de la petición. Si contiene el carácter '?',
        // devuelve todo el fragmento que se encuentra justo después de él. Si no, devuelve "".
        return "";
    }

    /**
     * RETO EXTRA 7: Búsqueda de cabecera insensible a mayúsculas/minúsculas.
     * Según el estándar, los nombres de cabecera HTTP son case-insensitive.
     * 
     * @param raw petición HTTP cruda
     * @param cabeceraNombre nombre de la cabecera a buscar (ej. "content-type")
     * @return el valor de la cabecera recortado de espacios, o "" si no existe
     */
    public static String extraerCabeceraSegura(String raw, String cabeceraNombre) {
        // TODO extra 7: recorre las cabeceras e identifica aquella cuyo nombre coincida 
        // con 'cabeceraNombre' ignorando mayúsculas/minúsculas. Devuelve su valor sin espacios.
        return "";
    }

    /**
     * RETO EXTRA 8: Detección de conexión persistente vs transaccional.
     * Si la petición solicita explícitamente cerrar la conexión tras procesarse.
     * 
     * @param raw petición HTTP cruda
     * @return true si la cabecera Connection está presente y su valor es "close" (case-insensitive)
     */
    public static boolean esConexionCerrada(String raw) {
        // TODO extra 8: usa la lógica de cabeceras para buscar "Connection". Si su valor 
        // es "close" (ignorando mayúsculas/minúsculas), devuelve true. De lo contrario, devuelve false.
        return false;
    }

    /**
     * RETO EXTRA 9: Detección de transferencia por fragmentos (chunked).
     * En flujos de datos grandes, las APIs usan Transfer-Encoding chunked en vez de Content-Length.
     * 
     * @param raw petición HTTP cruda
     * @return true si la cabecera Transfer-Encoding existe y contiene el valor "chunked" (case-insensitive)
     */
    public static boolean contieneCuerpoChunky(String raw) {
        // TODO extra 9: busca de forma segura la cabecera "Transfer-Encoding". Devuelve true
        // si su valor contiene la palabra "chunked" (ignorando mayúsculas/minúsculas).
        return false;
    }

    /**
     * RETO EXTRA 10: Lectura segura de cuerpo basada en Content-Length.
     * En servidores de producción, leer el body indefinidamente es vulnerable a ataques de denegación 
     * de servicio. Debemos leer exactamente los bytes indicados por la cabecera Content-Length.
     * 
     * @param raw petición HTTP cruda
     * @return el body recortado a la longitud exacta especificada por Content-Length, o el body completo
     *         si no se especifica dicha cabecera. Si Content-Length es mayor que el cuerpo disponible, devuelve el cuerpo disponible.
     */
    public static String obtenerCuerpoSeguroConContentLength(String raw) {
        // TODO extra 10: localiza la cabecera "Content-Length" y parsea su valor a entero.
        // Si existe y es válido, devuelve el cuerpo de la petición recortado a esa longitud máxima.
        // Si no se encuentra la cabecera o su formato no es numérico, devuelve el cuerpo tal cual.
        return "";
    }

}
