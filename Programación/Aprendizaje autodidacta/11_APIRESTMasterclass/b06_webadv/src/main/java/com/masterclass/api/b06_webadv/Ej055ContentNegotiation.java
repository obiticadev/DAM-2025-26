package com.masterclass.api.b06_webadv;

import java.util.List;

/**
 * Ejercicio 055 · Negociación de contenido (resolución de MediaType).
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.1).
 *
 * <p>Decide el MediaType de salida según Accept y lo que el endpoint produce.
 */
public final class Ej055ContentNegotiation {

    private Ej055ContentNegotiation() {
    }

    /**
     * Resuelve el tipo de respuesta. Devuelve "" si no hay representación aceptable
     * (lo que en una API real sería un 406 Not Acceptable).
     *
     * @param accept   cabecera Accept del cliente
     * @param produces tipos que el endpoint declara producir (orden = preferencia)
     * @return el MediaType elegido o "" si 406
     */
    public static String resolve(String accept, List<String> produces) {
        // TODO 1: si produces es null/vacío, no hay nada que ofrecer -> "".
        // TODO 2: si accept es null/blank, equivale a "*/*" (cliente sin preferencia).
        // TODO 3: separa accept por coma en tokens.
        // TODO 4: recorta espacios de cada token.
        // TODO 5: descarta el parámetro de calidad ";q=" si viene.
        // TODO 6: si algún token es "*/*", devuelve produces.get(0).
        // TODO 7: soporta comodín de subtipo "application/*": casa con cualquier application/x.
        // TODO 8: recorre 'produces' en orden de preferencia del servidor.
        // TODO 9: devuelve el primero que el cliente acepte (exacto o por comodín).
        // TODO 10: si nada casa, devuelve "" (caso 406 Not Acceptable).
        return "";
    }

    public static void main(String[] args) {
        System.out.println(resolve("application/*", List.of("application/json")));
    }

    /**
     * Reto Extra 1: Validación sintáctica de tipos producidos.
     * Comprueba si todos los elementos de la lista 'produces' son MediaTypes sintácticamente válidos
     * (no son nulos, no están en blanco y contienen exactamente una barra '/').
     */
    public static boolean pasoExtra01(List<String> produces) {
        // GUÍA: teoría 6.1 (un media-type bien formado es "tipo/subtipo").
        // 1. Si produces es null o vacío -> el test no lo cubre, pero por defensa
        //    decide tú (false es razonable: "no hay nada válido").
        // 2. Recorre TODOS los elementos: cada uno debe ser no null, no blank y
        //    contener EXACTAMENTE una '/'. Para contar barras:
        //    s.chars().filter(c -> c == '/').count() == 1.
        // 3. Devuelve true solo si todos cumplen (un único fallo -> false).
        // PISTA: produces.stream().allMatch(s -> s != null && !s.isBlank()
        //            && s.chars().filter(c -> c == '/').count() == 1);
        // OJO: el test manda ["application/json","invalid-media-type"] (sin '/')
        //      -> false, y ["","text/plain"] (blank) -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * Reto Extra 2: Extracción limpia de MIME types de la cabecera Accept ordenados por q-value.
     * Extrae los MIME types desde la cabecera Accept (ej: "text/plain;q=0.5, application/json;q=0.9")
     * y los devuelve en una lista limpia de cadenas ordenadas por q-value de mayor a menor.
     * Si no viene 'q', se asume 'q=1.0'. Si 'q' no es numérico, ignora ese token.
     */
    public static List<String> pasoExtra02(String accept) {
        // GUÍA: teoría 6.1, "la gramática de Accept" y la regla del q por defecto.
        // 1. Si accept es null/blank -> lista vacía.
        // 2. split(",") en tokens; para cada token, trim().
        // 3. Separa el MIME del q: parte por ';'. El [0].trim() es el MIME.
        //    Busca un fragmento "q=" en el resto; si no hay -> q = 1.0.
        // 4. Si el q no es numérico, IGNORA ese token (try/catch en parseDouble).
        // 5. Ordena por q DESCENDENTE y devuelve solo los MIME (sin el q).
        // PISTA: usa un record/par interno (mime, q) o un Map; ordena con
        //        Comparator.comparingDouble(...).reversed() y mapea a la lista.
        // OJO: "text/plain;q=0.5, application/json;q=0.9, text/html" debe dar
        //      [text/html (1.0), application/json (0.9), text/plain (0.5)] EN ESE
        //      orden. text/html no trae q -> 1.0 y por eso queda PRIMERO.
        // CULTURA: esto es exactamente lo que ordena Spring por dentro para elegir
        //          converter; aquí lo haces a mano para entenderlo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * Reto Extra 3: Comparación exacta y por comodín parcial.
     * Comprueba si un token de Accept (ej: "text/*" o "application/json") coincide con un tipo producido
     * (ej: "text/plain" o "application/json"). Soporta comodines en el subtipo.
     */
    public static boolean pasoExtra03(String accept, String produces) {
        // GUÍA: teoría 6.1, comodines de subtipo. Es el "casa con" del ejercicio base.
        // 1. Si alguno es null -> false.
        // 2. Si son iguales (equals) -> true (caso exacto).
        // 3. Si accept termina en "/*", compara solo el TIPO (lo de antes de '/'):
        //    accept "text/*" casa con produces "text/plain" porque ambos son "text".
        // PISTA: String tipoAccept = accept.split("/", 2)[0];
        //        String tipoProduces = produces.split("/", 2)[0];
        //        return accept.endsWith("/*") && tipoAccept.equals(tipoProduces);
        //        (con el equals exacto resuelto antes).
        // OJO: el test exige "text/*" vs "application/json" -> false (tipos
        //      distintos), pero "text/*" vs "text/plain" -> true.
        // CULTURA: reutilizarás esta misma comparación dentro de resolve() y de
        //          pasoExtra04; piensa en extraerla como helper.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * Reto Extra 4: Resolutor de negociación con soporte completo de q-values.
     * Resuelve el mejor MediaType a partir de la cabecera 'accept' que contiene q-values,
     * comparando con la lista de tipos que el servidor 'produces'. Devuelve el que tenga
     * mayor calidad asignada por el cliente. Si hay empate, prefiere el orden del servidor.
     */
    public static String pasoExtra04(String accept, List<String> produces) {
        // GUÍA: teoría 6.1. Aquí el q-value del CLIENTE manda sobre el orden del
        // servidor (al revés que resolve()). Apóyate en pasoExtra02.
        // 1. Construye un mapa MIME -> q a partir de accept (reutiliza la lógica
        //    de pasoExtra02, que ya parsea q-values).
        // 2. Para cada tipo de 'produces', mira qué q le asignó el cliente
        //    (exacto o por comodín de subtipo con pasoExtra03; 0 si no aparece).
        // 3. Devuelve el produces con MAYOR q. En empate, gana el ORDEN de
        //    produces (recorre produces en orden y usa > estricto, no >=).
        // OJO: el test da accept "application/json;q=0.8, application/xml;q=0.9"
        //      con produces=[json, xml]; aunque el servidor prefiere json, el
        //      cliente puntúa xml más alto (0.9>0.8) -> devuelve "application/xml".
        // PISTA: mejor = ""; mejorQ = -1; recorre produces; si q > mejorQ actualiza.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * Reto Extra 5: Mapeador de formatos alternativos basados en Query Param.
     * Mapea un parámetro de formato (ej: "json", "xml", "text") a su correspondiente
     * MediaType estándar de internet ("application/json", "application/xml", "text/plain").
     * Si el formato es desconocido o nulo, devuelve "application/octet-stream".
     */
    public static String pasoExtra05(String formatQueryParam) {
        // GUÍA: teoría 6.7 (versionado por query param usa esta misma idea de
        // mapear un alias corto a algo estándar). Aquí: formato -> MIME.
        // 1. Si el parámetro es null -> "application/octet-stream".
        // 2. switch sobre el valor (mejor en minúsculas con toLowerCase):
        //    "json" -> "application/json"
        //    "xml"  -> "application/xml"
        //    "text" -> "text/plain"
        //    default -> "application/octet-stream"  (el "no sé qué es").
        // PISTA: un switch expression de Java 21 queda limpísimo:
        //        return switch (formatQueryParam == null ? "" : formatQueryParam) { ... };
        // OJO: el test pide "unknown" -> "application/octet-stream".
        // CULTURA: el "?format=json" es la estrategia de negociación más cómoda
        //          para probar una API desde el navegador (sin tocar cabeceras).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * Reto Extra 6: Extracción de parámetros de charset del MediaType.
     * Extrae el valor del parámetro charset (ej: "text/plain;charset=utf-8") de forma
     * insensible a mayúsculas. Si no se especifica charset, devuelve "UTF-8".
     */
    public static String pasoExtra06(String accept) {
        // GUÍA: teoría 6.1 (parámetros de un media-type van tras ';').
        // 1. Si accept es null/blank o no contiene "charset" -> devuelve "UTF-8".
        // 2. Si trae "...;charset=utf-8", extrae lo que va tras "charset=".
        //    Busca de forma INSENSIBLE a mayúsculas: pásalo a minúsculas para
        //    localizar el índice, pero recorta del original si quisieras conservar
        //    el caso (aquí el test devuelve "utf-8" en minúsculas).
        // PISTA: int i = accept.toLowerCase().indexOf("charset=");
        //        if (i < 0) return "UTF-8";
        //        String resto = accept.substring(i + 8);  // tras "charset="
        //        return resto.split(";")[0].trim();        // corta otros params.
        // OJO: el test exige "text/plain;charset=utf-8" -> "utf-8" y, SIN charset,
        //      "application/json" -> "UTF-8" (el default, en mayúsculas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * Reto Extra 7: Detección de tipos de contenido vendor-specific.
     * Comprueba si el MediaType especificado es un tipo de contenido personalizado o de proveedor
     * (ej: empieza por "application/vnd." o contiene "vnd.").
     */
    public static boolean pasoExtra07(String acceptHeader) {
        // GUÍA: una línea. Los tipos "de proveedor" llevan el segmento "vnd."
        // (vendor). Ejemplo: application/vnd.mycompany.v1+json.
        // return acceptHeader != null && acceptHeader.contains("vnd.");
        // OJO: el test da "application/vnd.mycompany.v1+json" -> true y
        //      "application/json" -> false.
        // CULTURA: esta convención es la base del versionado por Accept (6.7):
        //          application/vnd.empresa.app-v2+json codifica empresa Y versión
        //          dentro del propio media-type.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * Reto Extra 8: Negociación estricta libre de comodines globales.
     * Resuelve el MediaType pero rechaza la negociación si el cliente solicita un comodín global (estrella/estrella)
     * por motivos de seguridad o conformidad estricta de esquemas, devolviendo una cadena vacía.
     */
    public static String pasoExtra08(String accept, List<String> produces) {
        // GUÍA: como resolve() (6.1) pero RECHAZANDO el comodín global "*/*".
        // 1. Si accept (tras trim) es exactamente "*/*" -> devuelve "" (rechazo).
        // 2. En cualquier otro caso, delega en resolve(accept, produces): reutiliza
        //    el ejercicio base, no copies su lógica.
        // PISTA: if (accept != null && accept.trim().equals("*/*")) return "";
        //        return resolve(accept, produces);
        // OJO: el test exige "*/*" -> "" y "application/json" -> "application/json".
        // CULTURA: en esquemas estrictos (APIs bancarias, contratos OpenAPI duros)
        //          se prohíbe el "dame lo que sea" para forzar formatos explícitos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * Reto Extra 9: Negociación con soporte de versión específica en el MediaType.
     * Resuelve el MediaType compatible considerando parámetros de versión del cliente (ej: ";version=2").
     * Si el servidor produce "application/json" y el cliente pide "application/json;version=2",
     * devuelve el tipo exacto con su parámetro si es compatible.
     */
    public static String pasoExtra09(String accept, List<String> produces) {
        // GUÍA: teoría 6.1 + 6.7. El cliente puede pedir un tipo con parámetro de
        // versión (";version=2"); si el tipo base está en produces, devuélvelo
        // ENTERO (con el parámetro).
        // 1. Saca el tipo base del accept: accept.split(";")[0].trim().
        // 2. Si ese tipo base está en produces -> devuelve el accept COMPLETO
        //    (tal cual vino, con ";version=2" si lo traía).
        // 3. Si no casa -> "" (no aceptable).
        // OJO: el test exige:
        //   "application/json;version=2" + [json] -> "application/json;version=2"
        //   "application/json"           + [json] -> "application/json"
        //   Es decir, devuelves el accept de entrada, no el de produces.
        // PISTA: String base = accept.split(";")[0].trim();
        //        return produces.contains(base) ? accept : "";
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * Reto Extra 10: Validador de formato de cabecera Accept.
     * Valida sintácticamente que una cabecera Accept cumpla con el estándar RFC 7231
     * (tipo/subtipo con parámetros opcionales de calidad o extensiones válidas).
     */
    public static boolean pasoExtra10(String accept) {
        // GUÍA: teoría 6.1. Un Accept válido es "tipo/subtipo" con parámetros
        // opcionales tras ';'. Aquí basta una validación pragmática (no el RFC
        // entero).
        // 1. Si es null/blank -> false.
        // 2. Quédate con la parte antes del primer ';' (el media-type).
        // 3. Esa parte debe tener tipo y subtipo no vacíos: split("/", 2) con
        //    longitud 2 y ninguna mitad en blanco.
        // PISTA: String mt = accept.split(";")[0].trim();
        //        String[] p = mt.split("/", 2);
        //        return p.length == 2 && !p[0].isBlank() && !p[1].isBlank();
        // OJO: "application/json" -> true; "text/plain;q=0.8" -> true (el ;q no
        //      invalida); "invalid-format-without-slash" -> false (sin '/').
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
