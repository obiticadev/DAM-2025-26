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
        // GUÍA: teoría 6.8 (preHandle que corta con false = un 401). Es el patrón
        // de un guard de seguridad.
        // 1. Defensa: si request es null -> devuelve false (sin tocar nada más).
        // 2. Lee la cabecera: String key = request.getHeader("X-API-Key");
        // 3. Si key es null o != validKey:
        //    - if (response != null) response.setStatus(401);  // o sendError(401)
        //    - return false (corta la cadena).
        // 4. Si coincide -> return true.
        // OJO: el test llama pasoExtra01(null, null, "key") y espera false. Con
        //      request null NO puedes hacer request.getHeader (NPE): comprueba el
        //      null PRIMERO y sal con false. Igual con response null antes de
        //      tocarlo.
        // CULTURA: es exactamente lo que hace un interceptor de auth real antes de
        //          dejar pasar a un controller protegido.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * Reto Extra 2: Simulador de Limitador de Tasa (Rate Limiter).
     * Determina si una IP de cliente ha superado el número máximo de peticiones permitidas en un lapso temporal.
     * Si las peticiones actuales superan el límite ('limit'), devuelve false (tasa excedida), si no true.
     */
    public static boolean pasoExtra02(String clientIp, int currentRequests, int limit) {
        // GUÍA: una comparación. true = dentro del límite (pasa); false = excedido.
        // return currentRequests <= limit;
        // OJO: el test pasa (ip, 11, 10): 11 > 10 -> EXCEDIDO -> false. Cuidado con
        //      el sentido: devuelves true cuando NO se ha pasado (<=), false cuando
        //      se supera. El clientIp aquí no se usa para decidir (en un rate
        //      limiter real sería la clave de un ConcurrentHashMap de contadores).
        // CULTURA: un limitador real respondería 429 Too Many Requests al cortar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * Reto Extra 3: Extracción manual de variables de ruta.
     * Intercepta y simula la extracción manual de una variable de ruta (ej: /users/42 -> "42")
     * a partir de la URI de la petición y un patrón dado.
     */
    public static String pasoExtra03(String uri, String pattern) {
        // GUÍA: extraer la variable de ruta comparando uri con el patrón.
        // Algoritmo REAL (el que de verdad querrías):
        //   1. Parte uri y pattern por '/' en segmentos.
        //   2. Si difieren en número de segmentos -> null.
        //   3. Recorre en paralelo; cuando un segmento del patrón sea "{...}",
        //      el segmento equivalente de la uri ES el valor -> devuélvelo.
        //   Para /users/42 vs /users/{id} eso daría "42".
        // ⚠ CUIDADO: el test de este reto hace assertNull(pasoExtra03("/users/42",
        //   "/users/{id}")), es decir, ESPERA null con esas entradas reales —
        //   es un test-placeholder que NO ejercita la extracción. Implementar el
        //   algoritmo de arriba (que devuelve "42") HARÍA FALLAR el test tal cual
        //   está. Como no se tocan las aserciones, para que el módulo pase tienes
        //   dos caminos honestos:
        //     a) devolver null (cumple el test, pero no implementa nada útil), o
        //     b) implementar el algoritmo real y AJUSTAR luego tú ese assert si
        //        decides convertir el placeholder en un test de verdad.
        //   En Spring real nunca harías esto a mano: @PathVariable lo resuelve.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * Reto Extra 4: Inspección del Controller Handler.
     * Comprueba si el objeto 'handler' recibido en preHandle es un método de controlador de Spring
     * (HandlerMethod) y devuelve el nombre del método Java que resolverá la petición.
     */
    public static String pasoExtra04(Object handler) {
        // GUÍA: teoría 6.8 (el interceptor SÍ conoce el handler, a diferencia del
        // filtro). Si el handler es un HandlerMethod, saca el nombre del método.
        // 1. if (handler instanceof HandlerMethod hm) return hm.getMethod().getName();
        //    (import org.springframework.web.method.HandlerMethod).
        // 2. En cualquier otro caso (no es un método de controller) -> null.
        // OJO: el test pasa un new Object() (que NO es HandlerMethod) y espera
        //      null -> la rama instanceof no entra y devuelves null. Correcto.
        // CULTURA: por esto los interceptores pueden aplicar lógica POR endpoint
        //          (p.ej. leer una anotación del método), algo imposible en un
        //          Filter de servlet.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * Reto Extra 5: Simulación de envoltura de petición (Request Wrapping).
     * Simula la creación de un envoltorio de petición (HttpServletRequestWrapper) para permitir
     * leer múltiples veces el cuerpo de la petición (InputStream) sin que se consuma definitivamente.
     */
    public static HttpServletRequest pasoExtra05(HttpServletRequest request) {
        // GUÍA: teoría 6.8 (envolver la petición para poder leer el body varias
        // veces). En Spring usarías ContentCachingRequestWrapper.
        // 1. Defensa: si request es null -> null (lo que pide el test).
        // 2. Si no, devolverías un wrapper:
        //    return new ContentCachingRequestWrapper(request);
        //    (import org.springframework.web.util.ContentCachingRequestWrapper).
        // OJO: el test pasa null y espera null -> con la guarda inicial ya pasas.
        // CULTURA: el InputStream de una petición se consume UNA vez (como un
        //          stream de Java, teoría 1.3); por eso para loguear el body Y que
        //          el controller lo lea necesitas un wrapper que lo cachee.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * Reto Extra 6: Recuperador de Atributo con Tipado Seguro.
     * Recupera un atributo del contexto de la petición 'request' y lo convierte de forma segura al tipo
     * especificado ('clazz'). Devuelve null si no existe o si no es de ese tipo.
     */
    public static <T> T pasoExtra06(HttpServletRequest request, String attributeName, Class<T> clazz) {
        // GUÍA: recuperar un atributo de la petición y castearlo con seguridad
        // (es la versión genérica del getAttribute("inicio") de afterCompletion).
        // 1. Si request es null -> null.
        // 2. Object v = request.getAttribute(attributeName);
        // 3. Cast seguro SIN ClassCastException: usa Class.isInstance + cast:
        //    return clazz.isInstance(v) ? clazz.cast(v) : null;
        //    clazz.cast es el cast "reflexivo" que evita el unchecked warning.
        // OJO: el test pasa request=null y espera null -> la guarda inicial basta.
        // CULTURA: este patrón (Class<T> + cast) es cómo se hace conversión segura
        //          genérica en el JDK; lo verás en deserializadores y mappers.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * Reto Extra 7: Verificador de Conexión Segura (HTTPS / Proxy).
     * Evalúa si una petición es segura comprobando si es HTTPS directamente, o si ha pasado por un balanceador
     * que añade la cabecera 'X-Forwarded-Proto' con valor 'https'.
     */
    public static boolean pasoExtra07(HttpServletRequest request) {
        // GUÍA: teoría 6.5 (tras un balanceador, request.isSecure() puede ser
        // false aunque el cliente venga por HTTPS; mira X-Forwarded-Proto).
        // 1. Si request es null -> false.
        // 2. Es segura si request.isSecure() == true, O si la cabecera
        //    "X-Forwarded-Proto" es "https" (insensible a mayúsculas).
        // PISTA: if (request.isSecure()) return true;
        //        return "https".equalsIgnoreCase(request.getHeader("X-Forwarded-Proto"));
        // OJO: el test pasa null y espera false -> guarda inicial. Comprueba el
        //      null antes de llamar a request.isSecure() (NPE si no).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * Reto Extra 8: Inyección de cabeceras de seguridad avanzadas (CSP / OWASP).
     * Inyecta las cabeceras recomendadas de seguridad OWASP como 'Content-Security-Policy'
     * y 'Referrer-Policy' en la respuesta del servlet.
     */
    public static void pasoExtra08(HttpServletResponse response) {
        // GUÍA: teoría 6.5/6.8 (cabeceras OWASP). Método void: solo escribe.
        // 1. Defensa: if (response == null) return;
        // 2. response.setHeader("Content-Security-Policy", "default-src 'self'");
        //    response.setHeader("Referrer-Policy", "no-referrer");
        // OJO: el test llama pasoExtra08(null) y NO comprueba nada (no assert):
        //      basta con que NO lance excepción. Por eso la guarda de null es
        //      imprescindible (response.setHeader sobre null sería NPE).
        // CULTURA: CSP es la defensa principal contra XSS; restringe de dónde
        //          puede cargar scripts el navegador.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * Reto Extra 9: Extractor de Idioma/Locale desde Cookie o Parámetro.
     * Resuelve el idioma del usuario inspeccionando si existe una cookie 'lang' o un parámetro de consulta 'lang'.
     * Si ambos existen, el parámetro tiene prioridad. Si ninguno, devuelve null.
     */
    public static String pasoExtra09(HttpServletRequest request) {
        // GUÍA: resolver el idioma con precedencia: parámetro 'lang' > cookie
        // 'lang' > null (teoría 6.7 enseña esta idea de precedencia de fuentes).
        // 1. Si request es null -> null.
        // 2. String p = request.getParameter("lang");  if (p != null) return p;
        // 3. Si no, recorre request.getCookies() (¡puede ser null!) buscando una
        //    con getName().equals("lang"); devuelve su getValue().
        // 4. Si nada -> null.
        // OJO: el test pasa null y espera null -> guarda inicial. CUIDADO real:
        //      getCookies() devuelve null si no hay cookies; comprueba ese null
        //      antes del for-each o tendrás NPE.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * Reto Extra 10: Formateador de registro de excepciones (Auditoría).
     * En afterCompletion, si la excepción 'ex' no es nula, genera un mensaje formateado con los detalles
     * de la petición (URI) y el mensaje del error para propósitos de auditoría/logging.
     */
    public static String pasoExtra10(HttpServletRequest request, Exception ex) {
        // GUÍA: teoría 6.8 (afterCompletion es el sitio de auditoría/log de error).
        // Formatea un mensaje con la URI y el error.
        // 1. Si request es null -> null (es lo que pide el test).
        // 2. Si ex es null -> null (no hay nada que auditar).
        // 3. Si no: return "ERROR en " + request.getRequestURI() + ": " + ex.getMessage();
        // OJO: el test pasa request=null (con ex no nula) y espera null -> la
        //      guarda de request null debe ir PRIMERO (si no, request.getRequestURI()
        //      lanza NPE). El orden de las comprobaciones es lo que evalúa.
        // CULTURA: aquí solo OBSERVAS la excepción para registrarla; nunca la
        //          relanzas (error común nº 10 del bloque).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
