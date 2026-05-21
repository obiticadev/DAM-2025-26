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
        // TODO extra 1: valida e implementa la comprobación de seguridad para GET, HEAD, OPTIONS y TRACE.
        // Lanza IllegalArgumentException si el método es nulo o está vacío.
        return false;
    }

    /**
     * RETO EXTRA 2: Métodos idempotentes ampliados (estándar RFC).
     * OPTIONS y TRACE son idempotentes. GET, HEAD, PUT y DELETE también lo son.
     * 
     * @param method método HTTP
     * @return true si es un método idempotente estándar
     */
    public static boolean esMetodoIdempotenteWeb(String method) {
        // TODO extra 2: valida e implementa la comprobación de idempotencia extendida.
        // Métodos idempotentes: GET, HEAD, PUT, DELETE, OPTIONS, TRACE.
        return false;
    }

    /**
     * RETO EXTRA 3: ¿El método admite cuerpo (Payload)?
     * Algunos verbos HTTP son para enviar información en el body de la request, otros no deben llevar body.
     * 
     * @param method método HTTP
     * @return true si el verbo admite cuerpo por diseño (POST, PUT, PATCH)
     */
    public static boolean admiteCuerpoDePeticion(String method) {
        // TODO extra 3: retorna true si el método es POST, PUT o PATCH.
        // Retorna false para GET, DELETE, HEAD, OPTIONS, TRACE.
        return false;
    }

    /**
     * RETO EXTRA 4: Cacheabilidad estándar.
     * Por defecto, los navegadores y servidores caché proxy solo guardan respuestas de métodos específicos.
     * 
     * @param method método HTTP
     * @return true si el método es cacheable por defecto (GET y HEAD)
     */
    public static boolean esCacheablePorDefecto(String method) {
        // TODO extra 4: implementa si el método es cacheable de forma nativa por el protocolo.
        return false;
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
        // TODO extra 5: si esRutaSoloLectura es true, solo se permiten métodos seguros.
        // Si es false, se permiten todos. Retorna false si no es permitido.
        return false;
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
        // TODO extra 6: limpia espacios al principio y final. Verifica que no sea nulo ni vacío,
        // y que solo contenga letras (A-Z, a-z). Retorna la cadena convertida a mayúsculas.
        return "";
    }

    /**
     * RETO EXTRA 7: Identificar extensiones WebDAV.
     * WebDAV extiende HTTP con métodos para gestionar archivos en servidores remotos.
     * 
     * @param method método HTTP
     * @return true si es un método clásico de WebDAV (PROPFIND, MKCOL, LOCK, UNLOCK)
     */
    public static boolean esMetodoWebDav(String method) {
        // TODO extra 7: valida si el método pertenece a la extensión WebDAV.
        return false;
    }

    /**
     * RETO EXTRA 8: Determinar código de respuesta de éxito recomendado por el protocolo.
     * 
     * @param method método HTTP
     * @return el código HTTP clásico de éxito (POST -> 201, DELETE -> 204, GET/PUT/PATCH -> 200)
     */
    public static int determinarCodigoExitoPorDefecto(String method) {
        // TODO extra 8: retorna 201 para POST, 204 para DELETE, y 200 para GET, PUT y PATCH.
        return 200;
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
        // TODO extra 9: solo se pueden reintentar automáticamente los métodos que sean
        // IDEMPOTENTES (GET, HEAD, PUT, DELETE, OPTIONS). POST y PATCH no lo permiten.
        return false;
    }

    /**
     * RETO EXTRA 10: Semántica de Colección vs Recurso Individual.
     * 
     * @param method método HTTP
     * @return true si el método típicamente requiere actuar sobre un ID específico (ej. DELETE o PUT sobre /productos/12)
     *         y false si opera sobre la colección (ej. POST o GET sobre /productos)
     */
    public static boolean requiereUriEspecificaRecurso(String method) {
        // TODO extra 10: retorna true si el método es PUT, PATCH o DELETE, ya que requieren identificar
        // el recurso exacto a modificar o eliminar. Retorna false para GET y POST.
        return false;
    }

}
