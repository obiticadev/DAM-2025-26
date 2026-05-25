package com.masterclass.api.b00_http;

/**
 * Ejercicio 003 · Clasificador de códigos de estado.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.4).
 */
public final class Ej003StatusCodeResolver {

    private Ej003StatusCodeResolver() {
    }

    /**
     * Devuelve la familia textual del código.
     *
     * @param status código HTTP entre 100 y 599
     * @return uno de: "Informativa", "Exito", "Redireccion", "ErrorCliente", "ErrorServidor"
     * @throws IllegalArgumentException si el código está fuera de [100, 599]
     */
    public static String family(int status) {
        // TODO 1: si status < 100 o status > 599, lanza IllegalArgumentException.
        // TODO 2: rango [100,199] -> "Informativa".
        // TODO 3: rango [200,299] -> "Exito".
        // TODO 4: rango [300,399] -> "Redireccion".
        // TODO 5: rango [400,499] -> "ErrorCliente".
        // TODO 6: rango [500,599] -> "ErrorServidor".
        return "";
    }

    /**
     * Indica si el código representa un error (4xx o 5xx).
     *
     * @param status código HTTP
     * @return true si es 4xx o 5xx
     */
    public static boolean isError(int status) {
        // TODO 7: reutiliza family(status) y compara contra "ErrorCliente"/"ErrorServidor".
        // TODO 8: no dupliques la lógica de rangos: apóyate en family().
        return false;
    }

    /**
     * Indica si la culpa del error es del cliente (4xx).
     *
     * @param status código HTTP
     * @return true solo para la familia 4xx
     */
    public static boolean isClientFault(int status) {
        // TODO 9: true únicamente si family(status) == "ErrorCliente".
        // TODO 10: un 5xx NO es culpa del cliente -> debe devolver false.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("404 -> " + family(404) + " error=" + isError(404) + " cliente=" + isClientFault(404));
        System.out.println("200 -> " + family(200) + " error=" + isError(200));
    }

    /**
     * RETO EXTRA 1: Redirecciones que no alteran la petición original.
     * En HTTP, las redirecciones 307 (Temporary Redirect) y 308 (Permanent Redirect) garantizan
     * que el cliente NO debe cambiar el método HTTP (ej. de POST a GET) al repetir la petición.
     * 
     * @param status código HTTP
     * @return true si es una redirección estricta que preserva el método (307 o 308)
     */
    public static boolean esRedireccionEstrictaMetodo(int status) {
        // TODO extra: RETO EXTRA 1: Redirecciones que no alteran la petición original.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRedireccionEstrictaMetodo");
    }

    /**
     * RETO EXTRA 2: Resolución inversa de frases de motivo.
     * 
     * @param phrase frase de motivo estándar (ej. "Not Found")
     * @return el código de estado correspondiente (ej. 404); -1 si no se reconoce
     */
    public static int resolverCodigoPorPhrase(String phrase) {
        // TODO extra: RETO EXTRA 2: Resolución inversa de frases de motivo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverCodigoPorPhrase");
    }

    /**
     * RETO EXTRA 3: Detección de caída temporal de servidor.
     * Ciertos errores 5xx indican que el servidor está sobrecargado o fuera de servicio temporalmente,
     * no que tenga un fallo de programación interno.
     * 
     * @param status código HTTP
     * @return true si es 503 (Service Unavailable) o 504 (Gateway Timeout)
     */
    public static boolean esErrorDeServidorTemporal(int status) {
        // TODO extra: RETO EXTRA 3: Detección de caída temporal de servidor.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorDeServidorTemporal");
    }

    /**
     * RETO EXTRA 4: Identificar si una petición es reintentable de forma automática.
     * 
     * @param status código HTTP
     * @return true si el cliente puede intentar realizar la misma petición de nuevo sin cambios
     */
    public static boolean esReintentablePorElCliente(int status) {
        // TODO extra: RETO EXTRA 4: Identificar si una petición es reintentable de forma automática.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esReintentablePorElCliente");
    }

    /**
     * RETO EXTRA 5: Directiva de acción recomendada para el cliente de la API.
     * 
     * @param status código HTTP
     * @return la directiva ("CORREGIR_PETICION", "REAUTENTICAR", "REINTENTAR_DESPUES", "PROCESAR_EXITO", "NINGUNA")
     */
    public static String determinarAccionCliente(int status) {
        // TODO extra: RETO EXTRA 5: Directiva de acción recomendada para el cliente de la API.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para determinarAccionCliente");
    }

    /**
     * RETO EXTRA 6: Validación de código estándar IANA.
     * Evita procesar códigos inventados o no estandarizados en la industria.
     * 
     * @param status código de estado
     * @return true si es un código comúnmente aceptado en APIs REST (ej. 200, 201, 202, 204, 301, 302, 304, 400, 401, 403, 404, 409, 422, 500, 502, 503, 504)
     */
    public static boolean esCodigoEstandarIana(int status) {
        // TODO extra: RETO EXTRA 6: Validación de código estándar IANA.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCodigoEstandarIana");
    }

    /**
     * RETO EXTRA 7: Comprobación de cuerpo permitido.
     * El estándar HTTP (RFC 7231) prohíbe explícitamente enviar cuerpos en las respuestas con ciertos códigos.
     * 
     * @param status código HTTP
     * @return true si la especificación permite retornar cuerpo de respuesta para este código
     */
    public static boolean permiteCuerpoEnRespuesta(int status) {
        // TODO extra: RETO EXTRA 7: Comprobación de cuerpo permitido.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para permiteCuerpoEnRespuesta");
    }

    /**
     * RETO EXTRA 8: Error crítico de infraestructura de red vs error de aplicación.
     * 
     * @param status código HTTP
     * @return true si es 502 (Bad Gateway) o 504 (Gateway Timeout), indicando fallos en proxies/balanceadores
     */
    public static boolean esErrorCriticoDeInfraestructura(int status) {
        // TODO extra: RETO EXTRA 8: Error crítico de infraestructura de red vs error de aplicación.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorCriticoDeInfraestructura");
    }

    /**
     * RETO EXTRA 9: Traductor de excepciones de Backend a códigos HTTP.
     * En Spring, un ExceptionHandler mapea excepciones de Java a códigos de estado HTTP.
     * 
     * @param t excepción lanzada por la aplicación
     * @return el código HTTP adecuado (ej. 400 para IllegalArgumentException, 403 para AccessDeniedException, etc.)
     */
    public static int resolverCodigoDesdeExcepcion(Throwable t) {
        // TODO extra: RETO EXTRA 9: Traductor de excepciones de Backend a códigos HTTP.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverCodigoDesdeExcepcion");
    }

    /**
     * RETO EXTRA 10: Discriminar expiración de sesión de falta de privilegios.
     * 401 y 403 representan conceptos de seguridad totalmente distintos.
     * 
     * @param status código HTTP
     * @return true si el código representa que el cliente NO está autenticado (401)
     */
    public static boolean esAutenticacionExpirada(int status) {
        // TODO extra: RETO EXTRA 10: Discriminar expiración de sesión de falta de privilegios.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAutenticacionExpirada");
    }

}
