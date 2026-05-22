package com.masterclass.api.b03_core;

import java.time.Clock;

/**
 * Ejercicio 035 · Configuración por Java: @Configuration / @Bean.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.5).
 */
public final class Ej035JavaConfigBeans {

    /**
     * Clase de configuración.
     */
    // TODO 1: anota esta clase con @org.springframework.context.annotation.Configuration.
    public static class AppConfig {
        // TODO 2: anota este método con @org.springframework.context.annotation.Bean.
        // TODO 3: el método debe devolver Clock.systemUTC() (no null).
        public Clock clock() {
            return null;
        }
    }

    private Ej035JavaConfigBeans() {
    }

    /**
     * Arranca un contexto con AppConfig y devuelve el bean Clock.
     *
     * @return el Clock gestionado por Spring (no null si la config es correcta)
     */
    public static Clock obtenerClock() {
        // TODO 4: crea un AnnotationConfigApplicationContext pasando AppConfig.class.
        // TODO 5: el contexto procesará @Configuration y registrará el @Bean.
        // TODO 6: recupera el bean con ctx.getBean(Clock.class).
        // TODO 7: cierra el contexto cuando termines (try-with-resources).
        // TODO 8: devuelve la instancia obtenida del contenedor.
        // TODO 9: si el @Bean devolviera null, getBean fallaría: por eso TODO 3 importa.
        // TODO 10: NO devuelvas Clock.systemUTC() directamente: debe venir del contexto.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(obtenerClock());
    }

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Arranca un contexto pasando una clase @Configuration y recupera un bean por su nombre.
     */
    public static Object obtenerBeanJavaConfig(Class<?> configClass, String nombreBean) {
        // TODO extra: Reto Extra 1: Arranca un contexto pasando una clase @Configuration y recupera un bean por su nombre.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBeanJavaConfig");
    }

    /**
     * Reto Extra 2: Configuración clásica que enlaza beans llamando directamente a otro método anotado con @Bean.
     * Esto verifica el proxy CGLIB que intercepta la llamada para garantizar una única instancia singleton.
     */
    public static class ConfiguracionConInyeccionMetodo {
        // TODO extra: Reto Extra 2: Configuración clásica que enlaza beans llamando directamente a otro método anotado con @Bean.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 3: Configuración limpia pasando el bean secundario como parámetro del método @Bean,
     * permitiendo que Spring lo inyecte de manera segura.
     */
    public static class ConfiguracionConInyeccionParametro {
        // TODO extra: Reto Extra 3: Configuración limpia pasando el bean secundario como parámetro del método @Bean,
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 4: Consolida múltiples clases de configuración independientes en un único contexto mediante @Import.
     */
    public static org.springframework.context.ApplicationContext ImportarConfiguracionesMultiples(Class<?>... configClasses) {
        // TODO extra: Reto Extra 4: Consolida múltiples clases de configuración independientes en un único contexto mediante @Import.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ImportarConfiguracionesMultiples");
    }

    /**
     * Reto Extra 5: Carga o descarta beans de forma selectiva basándote en los perfiles de entorno activos ("dev", "prod").
     */
    public static class BeanCondicionalPorProfile {
        // TODO extra: Reto Extra 5: Carga o descarta beans de forma selectiva basándote en los perfiles de entorno activos ("dev", "prod").
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 6: Inyecta valores externalizados de un fichero de propiedades utilizando @Value y @PropertySource en campos de la clase de configuración.
     */
    public static class PropiedadesExternasValue {
        // TODO extra: Reto Extra 6: Inyecta valores externalizados de un fichero de propiedades utilizando @Value y @PropertySource en campos de la clase de configuración.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 7: Simula el modo "Lite" de Spring (métodos @Bean declarados en una clase normal sin la anotación @Configuration).
     * Comprueba pedagógicamente que se generan múltiples instancias (no hay proxy CGLIB).
     */
    public static class ConfiguracionLiteMode {
        // TODO extra: Reto Extra 7: Simula el modo "Lite" de Spring (métodos @Bean declarados en una clase normal sin la anotación @Configuration).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 8: Registra dinámicamente un BeanDefinition manipulando el registro del contexto en caliente.
     */
    public static void registrarBeanDefinitionProgrammatic(org.springframework.context.support.GenericApplicationContext ctx, String nombre, Class<?> beanClass) {
        // TODO extra: Reto Extra 8: Registra dinámicamente un BeanDefinition manipulando el registro del contexto en caliente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrarBeanDefinitionProgrammatic");
    }

    /**
     * Reto Extra 9: Diseña un selector dinámico de configuraciones que implemente ImportSelector para cargar beans condicionalmente.
     */
    public static class ConfiguracionDinamicaImportSelector implements org.springframework.context.annotation.ImportSelector {
        // TODO extra: Reto Extra 9: Diseña un selector dinámico de configuraciones que implemente ImportSelector para cargar beans condicionalmente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 10: Configura nombres alternativos (alias) de beans en la anotación @Bean y verifica su resolución.
     */
    public static class BeanAliasJavaConfig {
        // TODO extra: Reto Extra 10: Configura nombres alternativos (alias) de beans en la anotación @Bean y verifica su resolución.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

}
