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
        // GUÍA: teoría 3.7 (arrancar un contexto desde una clase @Configuration y resolver por nombre).
        // 1. try (var ctx = new AnnotationConfigApplicationContext(configClass)) {
        // 2.     return ctx.getBean(nombreBean);   // el nombre del bean = nombre del método @Bean
        // 3. }   // try-with-resources cierra el contexto al salir
        // OJO: el test pasa MiConfigSimple (tiene @Bean String miMensaje()->"Configurado") y
        //   nombreBean="miMensaje"; espera que devuelvas "Configurado". El nombre es el del
        //   MÉTODO, no el de la clase.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBeanJavaConfig");
    }

    /**
     * Reto Extra 2: Configuración clásica que enlaza beans llamando directamente a otro método anotado con @Bean.
     * Esto verifica el proxy CGLIB que intercepta la llamada para garantizar una única instancia singleton.
     */
    @org.springframework.context.annotation.Configuration
    public static class ConfiguracionConInyeccionMetodo {
        // GUÍA: teoría 3.7 (enlazar beans llamando directamente a otro método @Bean).
        // El test de este reto usa la clase ConfigMetodo definida en el PROPIO test (no esta),
        // así que aquí basta con que tengas un ejemplo de referencia que compile:
        //   @org.springframework.context.annotation.Bean
        //   public StringBuilder sb() { return new StringBuilder("Base"); }
        //   @org.springframework.context.annotation.Bean
        //   public String mensaje() { return sb().append("Metodo").toString(); }
        // CULTURA: gracias al proxy CGLIB de @Configuration, sb() devuelve el MISMO singleton
        //   aunque lo llames a mano (no crea un StringBuilder nuevo).
    }

    /**
     * Reto Extra 3: Configuración limpia pasando el bean secundario como parámetro del método @Bean,
     * permitiendo que Spring lo inyecte de manera segura.
     */
    @org.springframework.context.annotation.Configuration
    public static class ConfiguracionConInyeccionParametro {
        // GUÍA: teoría 3.7 (estilo recomendado: recibe el bean dependiente como PARÁMETRO del
        // método @Bean y deja que Spring lo inyecte).
        // El test usa ConfigParametro (definida en el test). Referencia que compila:
        //   @org.springframework.context.annotation.Bean
        //   public String base() { return "Parametro"; }
        //   @org.springframework.context.annotation.Bean
        //   public String mensajeCompleto(String base) { return base + "Inyectado"; }
    }

    /**
     * Reto Extra 4: Consolida múltiples clases de configuración independientes en un único contexto mediante @Import.
     */
    public static org.springframework.context.ApplicationContext ImportarConfiguracionesMultiples(Class<?>... configClasses) {
        // GUÍA: teoría 3.7 (un contexto puede registrar VARIAS @Configuration de una vez; es el
        // equivalente programático de @Import).
        // 1. var ctx = new AnnotationConfigApplicationContext(configClasses);
        //    (el constructor varargs hace register + refresh de todas).
        // 2. return ctx;   // NO lo cierres: el test lo castea a ConfigurableApplicationContext y lo cierra él.
        // OJO: el test pasa MiConfigSimple y ConfigParametro y espera que el contexto contenga
        //   tanto "miMensaje" como "mensajeCompleto" (un bean de cada configuración).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ImportarConfiguracionesMultiples");
    }

    /**
     * Reto Extra 5: Carga o descarta beans de forma selectiva basándote en los perfiles de entorno activos ("dev", "prod").
     */
    @org.springframework.context.annotation.Configuration
    public static class BeanCondicionalPorProfile {
        // GUÍA: teoría 3.8 (cargar beans según el perfil activo con @Profile).
        // El test usa ConfigProfile (en el test) y activa "dev" con
        // ctx.getEnvironment().setActiveProfiles("dev") ANTES de register+refresh. Referencia:
        //   @org.springframework.context.annotation.Bean
        //   @org.springframework.context.annotation.Profile("dev")
        //   public String devBean() { return "dev"; }
        //   @org.springframework.context.annotation.Bean
        //   @org.springframework.context.annotation.Profile("prod")
        //   public String prodBean() { return "prod"; }
        // OJO: con perfil "dev" activo solo se registra devBean (containsBean("prodBean")==false).
    }

    /**
     * Reto Extra 6: Inyecta valores externalizados de un fichero de propiedades utilizando @Value y @PropertySource en campos de la clase de configuración.
     */
    @org.springframework.context.annotation.Configuration
    public static class PropiedadesExternasValue {
        // GUÍA: teoría 3.7 (externalizar valores con @Value + @PropertySource).
        // El test usa ConfigProperties (en el test), que hace:
        //   @PropertySource("classpath:application.properties")
        //   @Value("${app.nombre:Masterclass}") private String appNombre;   // default tras ':'
        //   @Bean public String nombre() { return appNombre; }
        // CULTURA: esto es el germen de application.properties del bloque 4. El ":Masterclass"
        //   es el valor por defecto si la propiedad no existe.
    }

    /**
     * Reto Extra 7: Simula el modo "Lite" de Spring (métodos @Bean declarados en una clase normal sin la anotación @Configuration).
     * Comprueba pedagógicamente que se generan múltiples instancias (no hay proxy CGLIB).
     */
    public static class ConfiguracionLiteMode {
        // GUÍA: teoría 3.7 (modo "lite": métodos @Bean en una clase SIN @Configuration -> NO hay
        // proxy CGLIB). Fíjate en que esta clase NO lleva @Configuration (a propósito).
        // El test usa ConfigLite (en el test):
        //   @org.springframework.context.annotation.Bean
        //   public StringBuilder sbLite() { return new StringBuilder("Lite"); }
        // OJO: en modo lite, llamar sbLite() a mano crearía objetos distintos (sin singleton
        //   garantizado por proxy); por eso el estilo "full" con @Configuration es el recomendado.
    }

    /**
     * Reto Extra 8: Registra dinámicamente un BeanDefinition manipulando el registro del contexto en caliente.
     */
    public static void registrarBeanDefinitionProgrammatic(org.springframework.context.support.GenericApplicationContext ctx, String nombre, Class<?> beanClass) {
        // GUÍA: teoría 3.7 (crear y registrar una BeanDefinition en caliente).
        // 1. Construye la definición:
        //    var def = org.springframework.beans.factory.support.BeanDefinitionBuilder
        //                  .genericBeanDefinition(beanClass).getBeanDefinition();
        // 2. Regístrala: ctx.registerBeanDefinition(nombre, def);
        // OJO: el test registra "manualString" con String.class sobre un contexto ya refrescado y
        //   espera ctx.containsBean("manualString") == true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrarBeanDefinitionProgrammatic");
    }

    /**
     * Reto Extra 9: Diseña un selector dinámico de configuraciones que implemente ImportSelector para cargar beans condicionalmente.
     */
    public static class ConfiguracionDinamicaImportSelector implements org.springframework.context.annotation.ImportSelector {
        @Override
        public String[] selectImports(org.springframework.core.type.AnnotationMetadata importingClassMetadata) {
            // GUÍA: teoría 3.7 (un ImportSelector decide en tiempo de arranque QUÉ clases
            // @Configuration importar; devuelve sus nombres cualificados).
            // 1. Devuelve un array con los nombres de clase a importar; si no quieres importar
            //    ninguna, devuelve un array vacío (NUNCA null).
            // PISTA: return new String[] { MiConfigSimple.class.getName() };  (o new String[0]).
            // OJO: el test solo comprueba assertNotNull(selector.selectImports(null)); por eso es
            //   crítico devolver un array (vacío vale), nunca null.
            return new String[0];
        }
    }

    /**
     * Reto Extra 10: Configura nombres alternativos (alias) de beans en la anotación @Bean y verifica su resolución.
     */
    @org.springframework.context.annotation.Configuration
    public static class BeanAliasJavaConfig {
        // GUÍA: teoría 3.7 (un mismo bean bajo varios nombres con @Bean(name={...})).
        // El test usa ConfigAlias (en el test):
        //   @org.springframework.context.annotation.Bean(name = {"miAliasA", "miAliasB"})
        //   public String aliasBean() { return "Alias"; }
        // OJO: ctx.getBean("miAliasA") y ctx.getBean("miAliasB") devuelven la MISMA instancia
        //   (assertSame): son dos nombres para un único singleton.
    }

}
