package com.masterclass.api.b06_webadv;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Ejercicio 062 · HandlerInterceptor que mide y cuenta.
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.5).
 *
 * <p>El test registra este interceptor en un MockMvc standalone, hace 2 peticiones
 * y comprueba que el contador llega a 2 y que se añade el header X-Handled.
 */
public class Ej062FilterAndInterceptor implements HandlerInterceptor {

    private int conteo = 0;

    /**
     * Se ejecuta ANTES del controller.
     *
     * @param request  petición
     * @param response respuesta
     * @param handler  handler destino
     * @return true para continuar la cadena
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // TODO 1: incrementa el contador interno 'conteo' (una petición más).
        // TODO 2: añade a 'response' la cabecera "X-Handled" = "true".
        // TODO 3: guarda el instante de inicio como atributo de 'request'
        //         (request.setAttribute("inicio", System.nanoTime())).
        // TODO 4: devuelve true para que la petición continúe hacia el controller.
        // TODO 5: si devolvieras false, la petición se cortaría aquí (no lo hagas).
        return false;
    }

    /**
     * Se ejecuta DESPUÉS de completar la petición.
     *
     * @param request  petición
     * @param response respuesta
     * @param handler  handler
     * @param ex       excepción si la hubo (puede ser null)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // TODO 6: recupera el atributo "inicio" de 'request'.
        // TODO 7: si existe, calcula la duración = System.nanoTime() - inicio.
        // TODO 8: añade la cabecera "X-Duration-Nanos" con esa duración.
        // TODO 9: no lances excepción aquí aunque 'ex' no sea null (solo observas).
        // TODO 10: este punto SIEMPRE se ejecuta (haya error o no): ideal para métricas.
    }

    /**
     * @return número de peticiones interceptadas
     */
    public int conteo() {
        return conteo;
    }

    public static void main(String[] args) {
        System.out.println("Interceptor listo");
    }

    /**
     * Reto Extra 1: Validación de API Key en Interceptor.
     * Comprueba si la cabecera 'X-API-Key' está presente y es válida. Si es incorrecta o nula,
     * escribe un error 401 Unauthorized en la respuesta y corta la cadena retornando false.
     */
    public static boolean pasoExtra01(HttpServletRequest request, HttpServletResponse response, String validKey) throws Exception {
        // TODO extra: valida el X-API-Key y corta la ejecución enviando un status 401 si falla.
        return false;
    }

    /**
     * Reto Extra 2: Simulador de Limitador de Tasa (Rate Limiter).
     * Determina si una IP de cliente ha superado el número máximo de peticiones permitidas en un lapso temporal.
     * Si las peticiones actuales superan el límite ('limit'), devuelve false (tasa excedida), si no true.
     */
    public static boolean pasoExtra02(String clientIp, int currentRequests, int limit) {
        // TODO extra: simula si la petición supera el límite de peticiones permitido por IP.
        return false;
    }

    /**
     * Reto Extra 3: Extracción manual de variables de ruta.
     * Intercepta y simula la extracción manual de una variable de ruta (ej: /users/42 -> "42")
     * a partir de la URI de la petición y un patrón dado.
     */
    public static String pasoExtra03(String uri, String pattern) {
        // TODO extra: extrae el identificador variable de la URI según el patrón proporcionado.
        return null;
    }

    /**
     * Reto Extra 4: Inspección del Controller Handler.
     * Comprueba si el objeto 'handler' recibido en preHandle es un método de controlador de Spring
     * (HandlerMethod) y devuelve el nombre del método Java que resolverá la petición.
     */
    public static String pasoExtra04(Object handler) {
        // TODO extra: inspecciona si el handler es HandlerMethod y devuelve el nombre del método.
        return null;
    }

    /**
     * Reto Extra 5: Simulación de envoltura de petición (Request Wrapping).
     * Simula la creación de un envoltorio de petición (HttpServletRequestWrapper) para permitir
     * leer múltiples veces el cuerpo de la petición (InputStream) sin que se consuma definitivamente.
     */
    public static HttpServletRequest pasoExtra05(HttpServletRequest request) {
        // TODO extra: envuelve la petición en un wrapper que permita la relectura del body.
        return null;
    }

    /**
     * Reto Extra 6: Recuperador de Atributo con Tipado Seguro.
     * Recupera un atributo del contexto de la petición 'request' y lo convierte de forma segura al tipo
     * especificado ('clazz'). Devuelve null si no existe o si no es de ese tipo.
     */
    public static <T> T pasoExtra06(HttpServletRequest request, String attributeName, Class<T> clazz) {
        // TODO extra: recupera el atributo y devuélvelo casteado de forma segura al tipo de la clase.
        return null;
    }

    /**
     * Reto Extra 7: Verificador de Conexión Segura (HTTPS / Proxy).
     * Evalúa si una petición es segura comprobando si es HTTPS directamente, o si ha pasado por un balanceador
     * que añade la cabecera 'X-Forwarded-Proto' con valor 'https'.
     */
    public static boolean pasoExtra07(HttpServletRequest request) {
        // TODO extra: comprueba si la petición es segura basándote en el protocolo o las cabeceras de proxy.
        return false;
    }

    /**
     * Reto Extra 8: Inyección de cabeceras de seguridad avanzadas (CSP / OWASP).
     * Inyecta las cabeceras recomendadas de seguridad OWASP como 'Content-Security-Policy'
     * y 'Referrer-Policy' en la respuesta del servlet.
     */
    public static void pasoExtra08(HttpServletResponse response) {
        // TODO extra: añade cabeceras avanzadas de CSP y Referrer-Policy a la respuesta.
    }

    /**
     * Reto Extra 9: Extractor de Idioma/Locale desde Cookie o Parámetro.
     * Resuelve el idioma del usuario inspeccionando si existe una cookie 'lang' o un parámetro de consulta 'lang'.
     * Si ambos existen, el parámetro tiene prioridad. Si ninguno, devuelve null.
     */
    public static String pasoExtra09(HttpServletRequest request) {
        // TODO extra: extrae el locale/idioma priorizando el parámetro y luego la cookie.
        return null;
    }

    /**
     * Reto Extra 10: Formateador de registro de excepciones (Auditoría).
     * En afterCompletion, si la excepción 'ex' no es nula, genera un mensaje formateado con los detalles
     * de la petición (URI) y el mensaje del error para propósitos de auditoría/logging.
     */
    public static String pasoExtra10(HttpServletRequest request, Exception ex) {
        // TODO extra: genera un log formateado para auditoría si existe una excepción.
        return null;
    }

}
