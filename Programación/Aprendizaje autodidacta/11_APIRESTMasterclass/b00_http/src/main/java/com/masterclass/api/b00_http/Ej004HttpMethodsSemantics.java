package com.masterclass.api.b00_http;

/**
 * Ejercicio 004 · Semántica de los verbos HTTP (seguridad e idempotencia).
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.3).
 */
public final class Ej004HttpMethodsSemantics {

    private Ej004HttpMethodsSemantics() {
    }

    /**
     * Indica si un verbo es "seguro" (no modifica estado en el servidor).
     *
     * @param method verbo HTTP, sin distinguir mayúsculas (GET, post, ...)
     * @return true para verbos seguros (solo GET y HEAD en este bootcamp)
     * @throws IllegalArgumentException si el verbo no es uno de los 6 estándar
     */
    public static boolean isSafe(String method) {
        // TODO 1: si method es null, lanza IllegalArgumentException.
        // TODO 2: normaliza a mayúsculas y recorta espacios.
        // TODO 3: valida que sea uno de GET/HEAD/POST/PUT/PATCH/DELETE; si no, IllegalArgumentException.
        // TODO 4: devuelve true solo para GET y HEAD.
        // TODO 5: cualquier otro verbo estándar -> false (modifica estado).
        return false;
    }

    /**
     * Indica si un verbo es idempotente.
     *
     * @param method verbo HTTP
     * @return true para GET, HEAD, PUT, DELETE
     * @throws IllegalArgumentException si el verbo no es estándar
     */
    public static boolean isIdempotent(String method) {
        // TODO 6: reutiliza la normalización/validación (no dupliques): apóyate en isSafe
        //         o extrae un helper privado que normalice y valide.
        // TODO 7: GET y HEAD son idempotentes (y además seguros).
        // TODO 8: PUT es idempotente (reemplazo total).
        // TODO 9: DELETE es idempotente (borrar lo ya borrado deja el mismo estado).
        // TODO 10: POST y PATCH NO son idempotentes -> false.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("GET safe=" + isSafe("GET") + " idemp=" + isIdempotent("GET"));
        System.out.println("POST safe=" + isSafe("POST") + " idemp=" + isIdempotent("POST"));
    }

    /**
     * RETO EXTRA 1: Métodos seguros ampliados (estándar RFC).
     * El bootcamp básico cubre GET y HEAD, pero OPTIONS y TRACE también son considerados "seguros"
     * por el estándar IETF HTTP.
     * 
     * @param method método HTTP
     * @return true si es seguro según la especificación HTTP (GET, HEAD, OPTIONS, TRACE)
     */
    public static boolean esMetodoSeguroWeb(String method) {
        // GUÍA: teoría 0.5.
        // 1. null → IllegalArgumentException (lo exige el test).
        // 2. Normaliza: trim() + toUpperCase().
        // 3. true si está en {GET, HEAD, OPTIONS, TRACE}; cualquier otro → false.
        // PISTA: crea un helper privado `normalizar(String)` que haga los pasos
        // 1-2: lo vas a reutilizar en los 9 retos siguientes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMetodoSeguroWeb");
    }

    /**
     * RETO EXTRA 2: Métodos idempotentes ampliados (estándar RFC).
     * OPTIONS y TRACE son idempotentes. GET, HEAD, PUT y DELETE también lo son.
     * 
     * @param method método HTTP
     * @return true si es un método idempotente estándar
     */
    public static boolean esMetodoIdempotenteWeb(String method) {
        // GUÍA: teoría 0.5 (chuleta: idempotentes = GET HEAD OPTIONS PUT DELETE + TRACE).
        // 1. Reutiliza tu helper de normalización.
        // 2. true si está en {GET, HEAD, OPTIONS, TRACE, PUT, DELETE}.
        // RELACIÓN CLAVE: todo método seguro es idempotente (no tocar el estado
        // N veces = no tocarlo 1 vez), pero no al revés: PUT y DELETE son
        // idempotentes sin ser seguros. Podrías escribir:
        // return esMetodoSeguroWeb(method) || PUT || DELETE.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMetodoIdempotenteWeb");
    }

    /**
     * RETO EXTRA 3: ¿El método admite cuerpo (Payload)?
     * Algunos verbos HTTP son para enviar información en el body de la request, otros no deben llevar body.
     * 
     * @param method método HTTP
     * @return true si el verbo admite cuerpo por diseño (POST, PUT, PATCH)
     */
    public static boolean admiteCuerpoDePeticion(String method) {
        // GUÍA: teoría 0.5 (columna "¿Body?" de la tabla).
        // true si está en {POST, PUT, PATCH}; GET/HEAD/DELETE/OPTIONS → false.
        // Normaliza antes de comparar, como siempre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para admiteCuerpoDePeticion");
    }

    /**
     * RETO EXTRA 4: Cacheabilidad estándar.
     * Por defecto, los navegadores y servidores caché proxy solo guardan respuestas de métodos específicos.
     * 
     * @param method método HTTP
     * @return true si el método es cacheable por defecto (GET y HEAD)
     */
    public static boolean esCacheablePorDefecto(String method) {
        // GUÍA: true solo para GET y HEAD (normalizando).
        // POR QUÉ: cachear tiene sentido cuando repetir la operación devuelve lo
        // mismo y no tiene efectos → solo los métodos seguros de lectura. Un
        // proxy jamás cachea un POST: cada uno puede crear algo distinto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCacheablePorDefecto");
    }

    /**
     * RETO EXTRA 5: Validación de seguridad de hilos en API Gateway.
     * Si el Gateway determina que la ruta es de solo lectura, bloquea verbos de escritura.
     * 
     * @param method método HTTP
     * @param esRutaSoloLectura flag de configuración de la ruta
     * @return true si el método HTTP está permitido para esa configuración
     */
    public static boolean validarCambioEstadoPermitido(String method, boolean esRutaSoloLectura) {
        // GUÍA: lógica de un API Gateway en miniatura.
        // - Si la ruta NO es de solo lectura → todo permitido → true.
        // - Si SÍ es de solo lectura → solo se permiten métodos seguros
        //   (reutiliza esMetodoSeguroWeb).
        // PISTA: cabe en una línea: return !esRutaSoloLectura || esMetodoSeguroWeb(method);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarCambioEstadoPermitido");
    }

    /**
     * RETO EXTRA 6: Normalización y saneamiento extremo de verbos.
     * Los clientes a veces envían espacios extras o caracteres raros.
     * 
     * @param method método HTTP
     * @return método sanitizado y en mayúsculas
     * @throws IllegalArgumentException si es nulo, vacío, o tiene caracteres no alfabéticos
     */
    public static String normalizarMetodoExtremo(String method) {
        // GUÍA:
        // 1. null o en blanco → IllegalArgumentException.
        // 2. trim() + toUpperCase().
        // 3. Si tras limpiar contiene algo que NO sea letra A-Z → IllegalArgumentException
        //    (el test rechaza "GET1" por el dígito).
        // PISTA: limpio.matches("[A-Z]+") valida todo el paso 3 de golpe.
        // 4. Devuelve el verbo limpio: "  get  " → "GET".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarMetodoExtremo");
    }

    /**
     * RETO EXTRA 7: Identificar extensiones WebDAV.
     * WebDAV extiende HTTP con métodos para gestionar archivos en servidores remotos.
     * 
     * @param method método HTTP
     * @return true si es un método clásico de WebDAV (PROPFIND, MKCOL, LOCK, UNLOCK)
     */
    public static boolean esMetodoWebDav(String method) {
        // GUÍA: comprobación de pertenencia, ya la dominas.
        // true si está en {PROPFIND, MKCOL, LOCK, UNLOCK} (normalizando antes).
        // CULTURA: WebDAV es la extensión de HTTP para gestionar ficheros remotos;
        // la usan Windows ("conectar unidad de red a URL"), Nextcloud, SharePoint.
        // Moraleja: el conjunto de verbos HTTP es EXTENSIBLE, no son solo los 7.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMetodoWebDav");
    }

    /**
     * RETO EXTRA 8: Determinar código de respuesta de éxito recomendado por el protocolo.
     * 
     * @param method método HTTP
     * @return el código HTTP clásico de éxito (POST -> 201, DELETE -> 204, GET/PUT/PATCH -> 200)
     */
    public static int determinarCodigoExitoPorDefecto(String method) {
        // GUÍA: conecta teoría 0.4 + 0.9 (tabla CRUD).
        //   POST   → 201 (Created: nació un recurso)
        //   DELETE → 204 (No Content: nada que devolver)
        //   GET / PUT / PATCH → 200 (OK con body de respuesta)
        // PISTA: switch sobre el verbo normalizado con default → 200.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para determinarCodigoExitoPorDefecto");
    }

    /**
     * RETO EXTRA 9: Tolerancia a fallos y reintentos automáticos de Red.
     * En arquitecturas distribuidas de microservicios (PSP), la red puede fallar. Reintentar un POST que
     * falló por timeout podría duplicar una transacción (ej. un pago). Reintentar un PUT es seguro.
     * 
     * @param method método HTTP
     * @return true si la red puede reintentar la operación automáticamente de forma segura
     */
    public static boolean permiteReintentoAutomatico(String method) {
        // GUÍA: aquí cierras el círculo de la idempotencia (teoría 0.5).
        // Reintentable a ciegas = idempotente → return esMetodoIdempotenteWeb(method);
        // EL ESCENARIO REAL: mandas un pago por POST, salta timeout… ¿llegó o no?
        // Si reintentas, puedes cobrar dos veces. Con PUT no hay duda: re-aplicar
        // el mismo estado es inocuo. ESTA es la razón por la que la tabla de 0.5
        // existe y se pregunta en entrevistas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para permiteReintentoAutomatico");
    }

    /**
     * RETO EXTRA 10: Semántica de Colección vs Recurso Individual.
     * 
     * @param method método HTTP
     * @return true si el método típicamente requiere actuar sobre un ID específico (ej. DELETE o PUT sobre /productos/12)
     *         y false si opera sobre la colección (ej. POST o GET sobre /productos)
     */
    public static boolean requiereUriEspecificaRecurso(String method) {
        // GUÍA: teoría 0.9 (colección vs elemento).
        // Según los tests: DELETE (y por la misma lógica PUT/PATCH) actúan sobre
        // UN recurso concreto → /productos/12 → true.
        // GET y POST operan sobre la colección → /productos → false.
        // (GET /productos/12 también existe, claro: este reto simplifica al caso
        // típico para que interiorices la diferencia colección/elemento.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para requiereUriEspecificaRecurso");
    }

}
