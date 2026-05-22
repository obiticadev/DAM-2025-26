package com.masterclass.api.b03_core;

/**
 * Ejercicio 032 · @Qualifier / @Primary (resolución entre varios candidatos).
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.2).
 *
 * <p>Versión sin Spring: cuando hay varias implementaciones, el "selector"
 * decide cuál usar por nombre, igual que hace @Qualifier.
 */
public final class Ej032QualifierAndPrimary {

    public interface Notificador {
        String enviar(String msg);
    }

    public static class EmailNotificador implements Notificador {
        public String enviar(String msg) {
            return "email:" + msg;
        }
    }

    public static class SmsNotificador implements Notificador {
        public String enviar(String msg) {
            return "sms:" + msg;
        }
    }

    private Ej032QualifierAndPrimary() {
    }

    /**
     * Resuelve la implementación por su "qualifier".
     *
     * @param qualifier "email" o "sms"
     * @return la implementación correspondiente
     * @throws IllegalArgumentException si el qualifier no se reconoce
     */
    public static Notificador resolver(String qualifier) {
        // TODO 1: si qualifier es null, lanza IllegalArgumentException.
        // TODO 2: normaliza el qualifier (trim + minúsculas) para comparar.
        // TODO 3: usa un switch sobre el valor normalizado.
        // TODO 4: caso "email" -> new EmailNotificador().
        // TODO 5: caso "sms" -> new SmsNotificador().
        // TODO 6: default -> IllegalArgumentException con mensaje indicando el valor inválido.
        return null;
    }

    /**
     * Devuelve la implementación marcada como primaria (la usada si no se cualifica).
     *
     * @return la implementación por defecto (EmailNotificador)
     */
    public static Notificador primario() {
        // TODO 7: @Primary significa "el elegido cuando no se especifica qualifier".
        // TODO 8: aquí el primario es EmailNotificador.
        // TODO 9: reutiliza resolver("email") para no duplicar la construcción.
        // TODO 10: devuelve esa instancia.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(resolver("sms").enviar("hi"));
        System.out.println(primario().enviar("hi"));
    }

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Recupera del contexto de Spring el bean que tiene prioridad @Primary.
     */
    public static <T> T obtenerBeanPrimary(org.springframework.context.ApplicationContext ctx, Class<T> tipo) {
        // TODO extra: Reto Extra 1: Recupera del contexto de Spring el bean que tiene prioridad @Primary.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBeanPrimary");
    }

    /**
     * Reto Extra 2: Recupera un bean específico cualificado con @Qualifier desde el ApplicationContext.
     */
    public static <T> T obtenerBeanQualifier(org.springframework.context.ApplicationContext ctx, Class<T> tipo, String calificador) {
        // TODO extra: Reto Extra 2: Recupera un bean específico cualificado con @Qualifier desde el ApplicationContext.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBeanQualifier");
    }

    /**
     * Reto Extra 3: Recupera todos los beans de un determinado tipo como un mapa indexado por nombre del bean.
     */
    public static <T> java.util.Map<String, T> obtenerTodosLosBeansComoMapa(org.springframework.context.ApplicationContext ctx, Class<T> tipo) {
        // TODO extra: Reto Extra 3: Recupera todos los beans de un determinado tipo como un mapa indexado por nombre del bean.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTodosLosBeansComoMapa");
    }

    /**
     * Reto Extra 4: Servicio desambiguador que inyecta una colección de notificadores,
     * pero prioriza el que tenga una anotación @Primary si está disponible, o usa uno por defecto.
     */
    public static class ServicioDesambiguador {
        // TODO extra: Reto Extra 4: Servicio desambiguador que inyecta una colección de notificadores,
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 5: Servicio que inyecta dos notificadores homónimos usando calificadores explícitos.
     */
    public static class ServicioDualQualifier {
        // TODO extra: Reto Extra 5: Servicio que inyecta dos notificadores homónimos usando calificadores explícitos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 6: Simula la resolución de beans en un contexto evaluando una anotación cualificadora personalizada.
     */
    public static Object resolucionConAnotacionCustom(org.springframework.context.ApplicationContext ctx, Class<?> tipo, Class<? extends java.lang.annotation.Annotation> customQualifier) {
        // TODO extra: Reto Extra 6: Simula la resolución de beans en un contexto evaluando una anotación cualificadora personalizada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolucionConAnotacionCustom");
    }

    /**
     * Reto Extra 7: Consulta las definiciones del BeanFactory para saber si un determinado bean tiene activada la propiedad Primary.
     */
    public static boolean esBeanPrimaryProgramatico(org.springframework.context.support.GenericApplicationContext ctx, String nombreBean) {
        // TODO extra: Reto Extra 7: Consulta las definiciones del BeanFactory para saber si un determinado bean tiene activada la propiedad Primary.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBeanPrimaryProgramatico");
    }

    /**
     * Reto Extra 8: Cuenta y lista cuántos beans en el ApplicationContext poseen una cualificación explícita de un tipo.
     */
    public static int contarBeansPorCalificador(org.springframework.context.support.GenericApplicationContext ctx, Class<?> tipo, String qualifierValue) {
        // TODO extra: Reto Extra 8: Cuenta y lista cuántos beans en el ApplicationContext poseen una cualificación explícita de un tipo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarBeansPorCalificador");
    }

    /**
     * Reto Extra 9: Servicio resiliente de resolución. Intenta resolver un notificador cualificado;
     * si no existe en el ApplicationContext, captura la excepción y retorna un SmsNotificador por defecto.
     */
    public static class ServicioFallbackQualifier {
        // TODO extra: Reto Extra 9: Servicio resiliente de resolución. Intenta resolver un notificador cualificado;
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 10: Mutación programática en caliente de las definiciones de beans para marcar un bean como Primary en tiempo de ejecución.
     */
    public static void reemplazarPrimaryEnCaliente(org.springframework.context.support.GenericApplicationContext ctx, String nuevoPrimaryBean) {
        // TODO extra: Reto Extra 10: Mutación programática en caliente de las definiciones de beans para marcar un bean como Primary en tiempo de ejecución.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reemplazarPrimaryEnCaliente");
    }

}
