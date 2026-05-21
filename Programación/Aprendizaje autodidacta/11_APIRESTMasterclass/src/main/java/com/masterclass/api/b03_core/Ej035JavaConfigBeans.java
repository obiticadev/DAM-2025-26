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
        // TODO extra (Reto 1): Arranca un AnnotationConfigApplicationContext, recupera el bean por nombre y cierra el contexto.
        return null;
    }

    /**
     * Reto Extra 2: Configuración clásica que enlaza beans llamando directamente a otro método anotado con @Bean.
     * Esto verifica el proxy CGLIB que intercepta la llamada para garantizar una única instancia singleton.
     */
    public static class ConfiguracionConInyeccionMetodo {
        // TODO extra (Reto 2): Define dos métodos @Bean, uno que dependa de otro llamándolo directamente.
    }

    /**
     * Reto Extra 3: Configuración limpia pasando el bean secundario como parámetro del método @Bean,
     * permitiendo que Spring lo inyecte de manera segura.
     */
    public static class ConfiguracionConInyeccionParametro {
        // TODO extra (Reto 3): Define un método @Bean que reciba otra dependencia como parámetro.
    }

    /**
     * Reto Extra 4: Consolida múltiples clases de configuración independientes en un único contexto mediante @Import.
     */
    public static org.springframework.context.ApplicationContext ImportarConfiguracionesMultiples(Class<?>... configClasses) {
        // TODO extra (Reto 4): Crea un contexto consolidado importando todas las clases de configuración indicadas.
        return null;
    }

    /**
     * Reto Extra 5: Carga o descarta beans de forma selectiva basándote en los perfiles de entorno activos ("dev", "prod").
     */
    public static class BeanCondicionalPorProfile {
        // TODO extra (Reto 5): Anota beans con @Profile para condicionar su registro.
    }

    /**
     * Reto Extra 6: Inyecta valores externalizados de un fichero de propiedades utilizando @Value y @PropertySource en campos de la clase de configuración.
     */
    public static class PropiedadesExternasValue {
        // TODO extra (Reto 6): Usa @Value y @PropertySource para inicializar variables desde propiedades del entorno.
    }

    /**
     * Reto Extra 7: Simula el modo "Lite" de Spring (métodos @Bean declarados en una clase normal sin la anotación @Configuration).
     * Comprueba pedagógicamente que se generan múltiples instancias (no hay proxy CGLIB).
     */
    public static class ConfiguracionLiteMode {
        // TODO extra (Reto 7): Define métodos @Bean en una clase que NO posea la anotación @Configuration.
    }

    /**
     * Reto Extra 8: Registra dinámicamente un BeanDefinition manipulando el registro del contexto en caliente.
     */
    public static void registrarBeanDefinitionProgrammatic(org.springframework.context.support.GenericApplicationContext ctx, String nombre, Class<?> beanClass) {
        // TODO extra (Reto 8): Crea y registra un BeanDefinition programáticamente.
    }

    /**
     * Reto Extra 9: Diseña un selector dinámico de configuraciones que implemente ImportSelector para cargar beans condicionalmente.
     */
    public static class ConfiguracionDinamicaImportSelector implements org.springframework.context.annotation.ImportSelector {
        @Override
        public String[] selectImports(org.springframework.core.type.AnnotationMetadata importingClassMetadata) {
            // TODO extra (Reto 9): Devuelve un array con los nombres de las clases de configuración que se deben importar.
            return new String[0];
        }
    }

    /**
     * Reto Extra 10: Configura nombres alternativos (alias) de beans en la anotación @Bean y verifica su resolución.
     */
    public static class BeanAliasJavaConfig {
        // TODO extra (Reto 10): Define un método @Bean especificando múltiples alias en el atributo 'name'.
    }

}
