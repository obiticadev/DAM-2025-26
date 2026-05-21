package com.masterclass.api.b02_json;

/**
 * Ejercicio 023 · El modelo de tipos JSON.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.1).
 *
 * <p>Sin librerías: clasifica un literal JSON simple por su tipo según su sintaxis.
 */
public final class Ej023JsonModel {

    private Ej023JsonModel() {
    }

    /**
     * Determina el tipo JSON de un literal ya recortado (sin espacios sobrantes).
     *
     * @param literal texto como {@code "true"}, {@code "42"}, {@code "\"hola\""}, {@code "null"},
     *                {@code "{...}"} o {@code "[...]"}
     * @return uno de: "objeto", "array", "string", "numero", "booleano", "null", "desconocido"
     */
    public static String tipo(String literal) {
        // TODO 1: si literal es null o vacío -> "desconocido".
        // TODO 2: si empieza por '{' -> "objeto".
        // TODO 3: si empieza por '[' -> "array".
        // TODO 4: si empieza por '"' -> "string".
        // TODO 5: si es exactamente "true" o "false" -> "booleano".
        // TODO 6: si es exactamente "null" -> "null".
        // TODO 7: intenta parsear como número (Double.parseDouble en try/catch).
        // TODO 8: si el parseo tiene éxito -> "numero".
        // TODO 9: si lanza NumberFormatException, no es número.
        // TODO 10: si nada de lo anterior aplica -> "desconocido".
        return "";
    }

    public static void main(String[] args) {
        for (String s : new String[]{"{}", "[1]", "\"x\"", "42", "true", "null", "??"}) {
            System.out.println(s + " -> " + tipo(s));
        }
    }

    /**
     * Reto Extra 1: Verificación de objeto vacío.
     * Comprueba si el literal JSON representa exactamente el objeto vacío `{}` (ignorando espacios en blanco).
     *
     * @param json literal de texto JSON
     * @return true si es un objeto vacío
     */
    public static boolean esObjetoVacio(String json) {
        // TODO extra: limpia espacios y comprueba si es exactamente "{}"
        return false;
    }

    /**
     * Reto Extra 2: Verificación de array vacío.
     * Comprueba si el literal JSON representa exactamente el array vacío `[]` (ignorando espacios en blanco).
     *
     * @param json literal de texto JSON
     * @return true si es un array vacío
     */
    public static boolean esArrayVacio(String json) {
        // TODO extra: limpia espacios y comprueba si es exactamente "[]"
        return false;
    }

    /**
     * Reto Extra 3: Validación sintáctica de cadena JSON.
     * Comprueba si un texto cumple las reglas de un literal de cadena JSON (inicia y termina con comillas dobles
     * y tiene al menos 2 caracteres).
     *
     * @param json literal de texto JSON
     * @return true si cumple con las comillas obligatorias de un String JSON
     */
    public static boolean esStringValido(String json) {
        // TODO extra: verifica que no sea nulo, su longitud sea >= 2, e inicie y termine con comillas dobles
        return false;
    }

    /**
     * Reto Extra 4: Validación de número entero.
     * Determina si el número JSON representa un valor entero válido sin parte decimal.
     *
     * @param json literal de texto JSON
     * @return true si es un entero válido
     */
    public static boolean esNumeroEntero(String json) {
        // TODO extra: valida si es número usando Integer.parseInt o mediante regex (ej. ^-?\\d+$)
        return false;
    }

    /**
     * Reto Extra 5: Extracción de valor de cadena JSON.
     * Extrae el contenido textual de una cadena JSON eliminando las comillas del principio y final,
     * e interpretando el escape básico de comillas interiores (`\"`).
     *
     * @param json literal de texto JSON que representa un String válido
     * @return texto crudo procesado, o null si el JSON es inválido o nulo
     */
    public static String extraerTextoString(String json) {
        // TODO extra: si es String válido, remueve las comillas externas y reemplaza \" por "
        return null;
    }

    /**
     * Reto Extra 6: Recuento de elementos en array JSON simple.
     * Cuenta cuántos elementos planos (separados por comas) existen en el array JSON simple provisto.
     * Asume un formato básico plano tipo `[1, "dos", null]` sin objetos ni arrays anidados en su interior.
     *
     * @param json literal de texto JSON array
     * @return número de elementos, o 0 si está vacío o es inválido
     */
    public static int contarElementosArraySimple(String json) {
        // TODO extra: limpia los corchetes externos, separa por comas si no está vacío, y cuenta los elementos
        return 0;
    }

    /**
     * Reto Extra 7: Normalización sintáctica de espacios.
     * Elimina todos los espacios en blanco que se encuentren fuera de las comillas dobles de los literales String.
     *
     * @param json texto JSON a normalizar
     * @return JSON normalizado libre de espacios externos irrelevantes
     */
    public static String limpiarEspaciosJson(String json) {
        // TODO extra: recorre la cadena caracter a caracter omitiendo espacios excepto si estás dentro de comillas
        return null;
    }

    /**
     * Reto Extra 8: Validación de booleano falso.
     * Comprueba si el literal JSON es exactamente el booleano falso.
     *
     * @param json literal de texto JSON
     * @return true si es exactamente "false"
     */
    public static boolean esBooleanoFalso(String json) {
        // TODO extra: verifica si tras recortar la cadena es idéntica a "false"
        return false;
    }

    /**
     * Reto Extra 9: Validación de tipo nulo en JSON.
     * Comprueba si el literal JSON es exactamente el valor null de JSON.
     *
     * @param json literal de texto JSON
     * @return true si es exactamente "null"
     */
    public static boolean esNullJson(String json) {
        // TODO extra: verifica si tras recortar la cadena es idéntica a "null"
        return false;
    }

    /**
     * Reto Extra 10: Contingencia de literal JSON.
     * Devuelve el literal JSON original si corresponde a un tipo reconocido por el sistema,
     * o un literal de cadena JSON por defecto si no es reconocido o es nulo.
     *
     * @param json    literal original
     * @param defecto valor por defecto (ej. "\"desconocido\"")
     * @return literal original o de contingencia
     */
    public static String obtenerLiteralDefecto(String json, String defecto) {
        // TODO extra: si tipo(json) es "desconocido", devuelve defecto, de lo contrario json
        return null;
    }

}
