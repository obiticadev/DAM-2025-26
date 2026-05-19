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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: incrementa el contador interno 'conteo' (una petición más).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: añade a 'response' la cabecera "X-Handled" = "true".
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: guarda el instante de inicio como atributo de 'request'
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: devuelve true para que la petición continúe hacia el controller.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si devolvieras false, la petición se cortaría aquí (no lo hagas).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: recupera el atributo "inicio" de 'request'.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si existe, calcula la duración = System.nanoTime() - inicio.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: añade la cabecera "X-Duration-Nanos" con esa duración.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: no lances excepción aquí aunque 'ex' no sea null (solo observas).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: este punto SIEMPRE se ejecuta (haya error o no): ideal para métricas.
    }

}
