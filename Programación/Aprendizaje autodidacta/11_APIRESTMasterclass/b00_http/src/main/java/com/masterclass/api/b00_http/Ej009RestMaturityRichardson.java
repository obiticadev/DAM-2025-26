package com.masterclass.api.b00_http;

/**
 * Ejercicio 009 · Modelo de madurez de Richardson.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.5).
 */
public final class Ej009RestMaturityRichardson {

    private Ej009RestMaturityRichardson() {
    }

    /**
     * Determina el nivel de madurez REST (0–3) de un diseño.
     *
     * <p>Heurística simplificada del bootcamp:
     * <ul>
     *   <li>Nivel 0: un único endpoint para todo (sin recursos).</li>
     *   <li>Nivel 1: múltiples recursos con URI, pero un solo verbo.</li>
     *   <li>Nivel 2: recursos + varios verbos HTTP + códigos de estado.</li>
     *   <li>Nivel 3: además expone enlaces de hipermedia (HATEOAS).</li>
     * </ul>
     *
     * @param multipleResources true si el diseño usa URIs por recurso
     * @param multipleVerbs     true si usa varios verbos HTTP correctamente
     * @param hypermedia        true si las respuestas incluyen enlaces
     * @return nivel entero de 0 a 3
     */
    public static int level(boolean multipleResources, boolean multipleVerbs, boolean hypermedia) {
        // TODO 1: el nivel es acumulativo: cada peldaño exige cumplir el anterior.
        // TODO 2: si NO hay múltiples recursos, el diseño es RPC plano -> nivel 0.
        // TODO 3: a partir de aquí ya hay recursos (al menos nivel 1).
        // TODO 4: si hay recursos pero NO múltiples verbos -> nivel 1.
        // TODO 5: comprobado que hay verbos, se alcanza al menos nivel 2.
        // TODO 6: si hay recursos + verbos pero NO hipermedia -> nivel 2.
        // TODO 7: la hipermedia (HATEOAS) solo cuenta si ya hay recursos y verbos.
        // TODO 8: si se cumplen los tres requisitos -> nivel 3.
        // TODO 9: no permitas "saltar" niveles (hipermedia sin verbos NO es nivel 3).
        // TODO 10: devuelve el entero calculado (0, 1, 2 o 3).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("RPC plano      -> " + level(false, false, false));
        System.out.println("REST realista  -> " + level(true, true, false));
        System.out.println("HATEOAS        -> " + level(true, true, true));
    }

    /**
     * RETO EXTRA 1: Evaluación heurística del nivel de madurez por ejemplo de tráfico.
     * Analiza una llamada HTTP concreta y estima su nivel de madurez según Richardson.
     *
     * @param url URL de la petición (ej. "/soap/service", "/usuarios/12")
     * @param metodoHttp método HTTP utilizado (ej. "POST", "GET")
     * @param cuerpoRespuesta respuesta JSON, XML o texto plano
     * @return nivel estimado (0 a 3)
     */
    public static int evaluarNivelPorEjemplo(String url, String metodoHttp, String cuerpoRespuesta) {
        // GUÍA: teoría 0.10. Eres un auditor de APIs: una sola petición, ¿qué nivel?
        // Heurística sugerida (los dos tests que tienes que pasar):
        // - Nivel 0: la URL huele a RPC/SOAP — contiene "soap", "rpc" o "service",
        //   o esDisenoRpc(url, metodo) da true (impleméntalo primero, reto 2).
        // - Si no es RPC, ya hay recursos → al menos nivel 1.
        // - Nivel 2: además usa un verbo distinto de POST (GET, PUT, DELETE...).
        // - Nivel 3: además el cuerpo contiene hipermedia
        //   (reutiliza contieneHipermediaHateoas, reto 3).
        // Test A: "/soap/customerService" + POST → 0.
        // Test B: "/usuarios/12" + GET + "_links" → 3.
        // CONSEJO: implementa los retos 2 y 3 ANTES que este; aquí solo orquestas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para evaluarNivelPorEjemplo");
    }

    /**
     * RETO EXTRA 2: Detección de diseño estilo RPC (Nivel 0).
     * RPC usa HTTP simplemente como transporte sin aprovechar la semántica de recursos.
     *
     * @param url URI propuesta (ej. "/crearUsuario", "/usuarios/42")
     * @param metodoHttp método HTTP (ej. "POST", "GET")
     * @return true si la URI delata un diseño RPC (contiene verbos o acciones en la URL, o todo es POST a un único punto)
     */
    public static boolean esDisenoRpc(String url, String metodoHttp) {
        // GUÍA: la firma del RPC es el VERBO DENTRO DE LA URL (teoría 0.9).
        // 1. null → false (o true, pero documéntalo; los tests no pasan null).
        // 2. Pasa la url a minúsculas y busca verbos de acción: "crear",
        //    "obtener", "eliminar", "actualizar", "borrar", "get", "set", "do".
        //    "/crearUsuario".toLowerCase() = "/crearusuario" → contiene "crear" ✔.
        // 3. "/usuarios/42" + GET no contiene ninguno → false.
        // BONUS: también delata RPC un POST contra un endpoint genérico
        // ("/api", "/service") — añádelo si quieres ir más lejos que el test.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDisenoRpc");
    }

    /**
     * RETO EXTRA 3: Detección de presencia de hipermedios HATEOAS (Nivel 3).
     * HATEOAS requiere que las respuestas guíen al cliente sobre las siguientes acciones posibles.
     *
     * @param cuerpoJson cuerpo de respuesta JSON
     * @return true si contiene enlaces hipermedia estándar (ej. "_links" en HAL, "links" en JSON:API)
     */
    public static boolean contieneHipermediaHateoas(String cuerpoJson) {
        // GUÍA:
        // 1. null → false.
        // 2. true si el JSON contiene la clave "_links" (convención HAL) o
        //    "links" (convención JSON:API).
        // PISTA: cuerpoJson.contains("\"_links\"") || contains("\"links\"") —
        // buscar CON comillas evita falsos positivos (un campo "backlinks" no
        // debería colar). Es heurística de String, no parsing real: Jackson
        // llega en el bloque 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneHipermediaHateoas");
    }

    /**
     * RETO EXTRA 4: Generación de enlace conforme al estándar HAL.
     * HAL (Hypertext Application Language) es una convención simple para hipermedia en JSON.
     *
     * @param href enlace destino (ej. "/pedidos/42")
     * @param rel relación del enlace (ej. "self", "next")
     * @return el bloque JSON formateado que representa el enlace en HAL
     */
    public static String generarEnlaceHal(String href, String rel) {
        // GUÍA: construir el JSON a mano (formato HAL, teoría 0.10):
        //   {"self": {"href": "/pedidos/42"}}
        // 1. Valida href y rel (null/blank → IllegalArgumentException o "").
        // 2. Concatena o usa String.format:
        //    String.format("{\"%s\": {\"href\": \"%s\"}}", rel, href)
        // El test solo exige que aparezcan "self" y "/pedidos/42" entre comillas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarEnlaceHal");
    }

    /**
     * RETO EXTRA 5: Clasificación semántica de código de estado HTTP (Nivel 2).
     * El Nivel 2 requiere el uso correcto de los códigos de estado para informar el resultado de la petición.
     *
     * @param status código HTTP (ej. 201, 409, 404)
     * @return la descripción semántica estándar (ej. "Created" para 201, "Conflict" para 409); o "Desconocido" si no es común
     */
    public static String clasificarCodigoEstadoMaturity(int status) {
        // GUÍA: la tabla de teoría 0.4, en forma de switch.
        // Mapea al menos: 200→"OK", 201→"Created", 204→"No Content",
        // 400→"Bad Request", 401→"Unauthorized", 403→"Forbidden",
        // 404→"Not Found", 409→"Conflict", 422→"Unprocessable Entity",
        // 500→"Internal Server Error". default → "Desconocido".
        // (Ya hiciste reasonPhrase en Ej002; esta versión amplía el catálogo.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clasificarCodigoEstadoMaturity");
    }

    /**
     * RETO EXTRA 6: Validación de Idempotencia de verbos HTTP (Nivel 2).
     * La idempotencia asegura la confiabilidad de la red en caso de reintentos.
     *
     * @param metodo método HTTP
     * @return true si es un método idempotente según la especificación HTTP (GET, PUT, DELETE, HEAD, OPTIONS)
     */
    public static boolean esIdempotenteParaMaturity(String metodo) {
        // GUÍA: ya lo resolviste en Ej004 (esMetodoIdempotenteWeb). Repítelo aquí
        // sin mirar: true si el verbo normalizado está en
        // {GET, HEAD, OPTIONS, PUT, DELETE}. Si dudaste, relee teoría 0.5:
        // ese conjunto tiene que salirte de memoria.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIdempotenteParaMaturity");
    }

    /**
     * RETO EXTRA 7: Sugeridor de Semántica REST (Refactorización RPC a REST).
     * Convierte operaciones RPC a su verbo y código de estado estándar correspondiente.
     *
     * @param operacionRpc nombre de la acción RPC (ej. "crearUsuario", "actualizarStock", "obtenerProductos")
     * @return una cadena explicativa con el formato "MÉTODO -> CÓDIGO_RECOMENDADO" (ej. "POST -> 201 Created")
     */
    public static String sugerirVerboYCodigo(String operacionRpc) {
        // GUÍA: traduce el PREFIJO de la operación RPC a verbo + código (0.4 + 0.9):
        //   empieza por "crear"               → "POST -> 201 Created"
        //   empieza por "obtener"/"listar"    → "GET -> 200 OK"
        //   empieza por "actualizar"          → "PUT -> 200 OK"
        //   empieza por "eliminar"/"borrar"   → "DELETE -> 204 No Content"
        //   otro                              → algo razonable ("POST -> 200 OK")
        // 1. null → "". 2. toLowerCase + startsWith en cascada.
        // Tests: "crearUsuario" debe contener "201"; "eliminarFila", "DELETE".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sugerirVerboYCodigo");
    }

    /**
     * RETO EXTRA 8: Parser de cabeceras HTTP Link de paginación (Nivel 3).
     * En APIs REST sin JSON estructurado, se utiliza la cabecera "Link" para relaciones HATEOAS.
     *
     * @param headerLinkValue cabecera Link completa (ej. "<https://api.example.com/items?page=2>; rel=\"next\", <https://api.example.com/items?page=1>; rel=\"prev\"")
     * @return un arreglo con las URLs de los enlaces encontrados para la relación "next"
     */
    public static String[] extraerEnlacesDeCabeceraLink(String headerLinkValue) {
        // GUÍA: formato de la cabecera Link (así pagina la API de GitHub):
        //   <URL>; rel="next", <URL>; rel="prev"
        // 1. null → new String[0].
        // 2. Divide por comas → cada trozo es un enlace.
        // 3. Quédate solo con los trozos que contengan rel="next".
        // 4. De cada uno, extrae lo que hay ENTRE '<' y '>':
        //    trozo.substring(trozo.indexOf('<') + 1, trozo.indexOf('>')).
        // 5. Devuelve como array (lista.toArray(new String[0])).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerEnlacesDeCabeceraLink");
    }

    /**
     * RETO EXTRA 9: Validación de Colección Paginada HATEOAS estándar.
     *
     * @param cuerpoJson respuesta JSON completa
     * @return true si es una respuesta que incluye navegación completa de paginación mediante enlaces
     */
    public static boolean esColeccionPaginadaSegura(String cuerpoJson) {
        // GUÍA: extiende el reto 3.
        // 1. Debe contener hipermedia (contieneHipermediaHateoas).
        // 2. Y además algún enlace de NAVEGACIÓN: "next", "prev", "first" o
        //    "last" (con comillas, como en el reto 3).
        // El test pasa {"_links": {"next": ...}} → ambas condiciones se cumplen.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esColeccionPaginadaSegura");
    }

    /**
     * RETO EXTRA 10: Generador de Colección HAL Paginada básica.
     *
     * @param resource recurso en plural
     * @param page página actual (0-indexed)
     * @param totalPages total de páginas disponibles
     * @return el objeto JSON HAL básico con los enlaces "self", "next" y "prev" según corresponda
     */
    public static String generarRespuestaHalPaginada(String resource, int page, int totalPages) {
        // GUÍA: el jefe final del bloque HATEOAS. Objetivo (page=1, totalPages=3):
        // {"_links": {"self": {"href": "/pedidos?page=1"},
        //             "next": {"href": "/pedidos?page=2"},
        //             "prev": {"href": "/pedidos?page=0"}}}
        // 1. "self" SIEMPRE aparece, con la página actual.
        // 2. "next" SOLO si page < totalPages - 1 (en la última no hay siguiente).
        // 3. "prev" SOLO si page > 0 (en la primera no hay anterior).
        // 4. Construye con StringBuilder o StringJoiner(",") y envuelve en
        //    {"_links": { ... }}. Cuidado con las comas finales.
        // El test usa page=1 de 3 → página intermedia → next Y prev presentes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarRespuestaHalPaginada");
    }

}
