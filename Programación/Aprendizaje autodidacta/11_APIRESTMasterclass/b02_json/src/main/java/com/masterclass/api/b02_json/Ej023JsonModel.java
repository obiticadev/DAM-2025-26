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
        // GUÍA: teoría 2.1 (objeto = empieza por '{'; aquí el objeto VACÍO exacto).
        // 1. Si json es null -> false (no lances NPE).
        // 2. Recorta los espacios de los extremos: json.strip().
        // 3. Compara el resultado con "{}" usando equals.
        // PISTA: return json != null && json.strip().equals("{}");
        // OJO: el test manda " {   } " con espacios INTERIORES y espera true ->
        // por eso strip() NO basta para los espacios de dentro. Tienes que quitar
        // TODOS los espacios antes de comparar (json.replace(" ", "")) o reutilizar
        // limpiarEspaciosJson del reto 7. El test {"id":1} debe dar false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esObjetoVacio");
    }

    /**
     * Reto Extra 2: Verificación de array vacío.
     * Comprueba si el literal JSON representa exactamente el array vacío `[]` (ignorando espacios en blanco).
     *
     * @param json literal de texto JSON
     * @return true si es un array vacío
     */
    public static boolean esArrayVacio(String json) {
        // GUÍA: teoría 2.1 (array = empieza por '['; aquí el array VACÍO exacto).
        // 1. Calca esObjetoVacio del reto 1 pero comparando con "[]".
        // 2. Quita TODOS los espacios (el test manda " [  ] " y espera true).
        // PISTA: return json != null && json.replace(" ", "").equals("[]");
        // OJO: el test [1,2] debe dar false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esArrayVacio");
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
        // GUÍA: teoría 2.1 (un string JSON va entre comillas dobles).
        // 1. Si json es null -> false.
        // 2. Necesita AL MENOS 2 caracteres (las dos comillas): json.length() >= 2.
        // 3. Debe empezar Y terminar por comilla doble: startsWith("\"") && endsWith("\"").
        // PISTA: return json != null && json.length() >= 2 && json.startsWith("\"") && json.endsWith("\"");
        // OJO: el test manda "\"" (UNA sola comilla, length 1) y espera false ->
        // por eso la comprobación de longitud >= 2 es imprescindible: sin ella, una
        // sola comilla cumpliría startsWith y endsWith a la vez. "hola" (sin comillas) -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esStringValido");
    }

    /**
     * Reto Extra 4: Validación de número entero.
     * Determina si el número JSON representa un valor entero válido sin parte decimal.
     *
     * @param json literal de texto JSON
     * @return true si es un entero válido
     */
    public static boolean esNumeroEntero(String json) {
        // GUÍA: teoría 2.1 (un número JSON es entero si NO tiene parte decimal).
        // 1. Si json es null -> false.
        // 2. Intenta parsear como ENTERO dentro de un try/catch:
        //    Integer.parseInt(json.strip())  (o Long.parseLong para enteros grandes).
        // 3. Si parsea sin lanzar -> true; si lanza NumberFormatException -> false.
        // PISTA: try { Long.parseLong(json.strip()); return true; } catch (NumberFormatException e) { return false; }
        // OJO: el test manda "3.14" y espera false: parseInt/parseLong LANZAN con el
        // punto decimal (a diferencia de Double.parseDouble que sí lo acepta) -> por eso
        // usamos parseInt/parseLong, no parseDouble. "-5" debe dar true; "abc" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNumeroEntero");
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
        // GUÍA: teoría 2.1 (extraer el texto interior de un string JSON y desescapar).
        // 1. Reutiliza esStringValido(json) del reto 3: si NO es un string válido -> return null.
        // 2. Quita la comilla inicial y final: json.substring(1, json.length() - 1).
        // 3. Desescapa las comillas interiores: reemplaza la secuencia \" por ".
        // PISTA: cuerpo.replace("\\\"", "\"")  ← en Java fuente \\\" representa los dos
        //   caracteres barra+comilla; en el JSON real eso es un \" escapado.
        // OJO: el test manda "\"dijo \\\"si\\\"\"" (en el JSON real: "dijo \"si\"") y espera
        // exactamente  dijo "si"  con las comillas ya desescapadas. Para "sincomillas"
        // (sin comillas) espera null -> de ahí la validación del paso 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerTextoString");
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
        // GUÍA: teoría 2.1 (array plano: contar elementos separados por comas).
        // 1. Si json es null -> 0. Recorta con strip().
        // 2. Quita los corchetes externos: substring(1, len-1) -> el "interior".
        // 3. Si el interior (tras strip) está vacío -> 0 (caso "[]").
        // 4. Si no, parte por comas y cuenta los trozos.
        // PISTA: String interior = json.strip(); interior = interior.substring(1, interior.length()-1).strip();
        //        if (interior.isEmpty()) return 0; return interior.split(",").length;
        // OJO: el test "[1, \"dos\", null]" espera 3 y "[]" espera 0. Asume array PLANO
        // (sin objetos ni arrays anidados), así que split(",") basta; no compliques con
        // comas dentro de strings (no las hay en los casos de prueba).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarElementosArraySimple");
    }

    /**
     * Reto Extra 7: Normalización sintáctica de espacios.
     * Elimina todos los espacios en blanco que se encuentren fuera de las comillas dobles de los literales String.
     *
     * @param json texto JSON a normalizar
     * @return JSON normalizado libre de espacios externos irrelevantes
     */
    public static String limpiarEspaciosJson(String json) {
        // GUÍA: teoría 2.1 (quitar espacios FUERA de los strings, no dentro).
        // 1. Si json es null -> null.
        // 2. Recorre el texto carácter a carácter con un StringBuilder.
        // 3. Lleva un booleano "dentroDeComillas" que conmutas cada vez que ves un '"'.
        // 4. Añade el carácter siempre, EXCEPTO si es espacio Y estás fuera de comillas.
        // PISTA: boolean dentro = false; for (char c : json.toCharArray()) { if (c=='"') dentro=!dentro;
        //        if (c==' ' && !dentro) continue; sb.append(c); }
        // OJO: el test  " \"con espacios\" "  espera  "con espacios"  CON su espacio interior
        // intacto -> por eso NO vale json.replace(" ",""): hay que respetar lo que va entre
        // comillas. El otro caso  " { \"a\" : 1 } "  debe quedar  {"a":1}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarEspaciosJson");
    }

    /**
     * Reto Extra 8: Validación de booleano falso.
     * Comprueba si el literal JSON es exactamente el booleano falso.
     *
     * @param json literal de texto JSON
     * @return true si es exactamente "false"
     */
    public static boolean esBooleanoFalso(String json) {
        // GUÍA: una línea —
        // return json != null && json.strip().equals("false");
        // OJO: el test manda "  false  " con espacios y espera true -> strip() obligatorio.
        // "true" debe dar false. La comparación es con equals (exacta y sensible a mayúsculas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBooleanoFalso");
    }

    /**
     * Reto Extra 9: Validación de tipo nulo en JSON.
     * Comprueba si el literal JSON es exactamente el valor null de JSON.
     *
     * @param json literal de texto JSON
     * @return true si es exactamente "null"
     */
    public static boolean esNullJson(String json) {
        // GUÍA: una línea —
        // return json != null && json.strip().equals("null");
        // OJO: distingue el null de JAVA (parámetro ausente -> false) del literal de
        // texto "null" de JSON (-> true). El test manda "  null  " (true) y "undefined" (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNullJson");
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
        // GUÍA: teoría 2.1 (patrón "valor por defecto si no es reconocible").
        // 1. Reutiliza tipo(json) (el método base de este fichero).
        // 2. Si json es null O tipo(json) devuelve "desconocido" -> return defecto.
        // 3. En cualquier otro caso (objeto/array/string/numero/booleano/null) -> return json.
        // PISTA: return (json == null || tipo(json).equals("desconocido")) ? defecto : json;
        // OJO: el test pasa ("{}", "\"defecto\"") y espera "{}" (es reconocible);
        // ("??", "\"defecto\"") espera "\"defecto\"" (no reconocible -> contingencia).
        // CULTURA: es el mismo patrón que orElse de Optional (teoría 1.2): "esto o un default".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerLiteralDefecto");
    }

}
