package com.masterclass.api.b03_core;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 034 · Ciclo de vida: init y destroy.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.3).
 *
 * <p>Simula @PostConstruct/@PreDestroy: registra el orden de las fases.
 */
public class Ej034BeanLifecycle {

    private final List<String> fases = new ArrayList<>();

    /** Llamado tras inyectar dependencias (equivale a @PostConstruct). */
    public void init() {
        // TODO 1: @PostConstruct se ejecuta UNA vez, tras construir e inyectar.
        // TODO 2: añade el literal "init" a la lista 'fases'.
        // TODO 3: no añadas "init" más de una vez por instancia (idealmente).
    }

    /** Lógica de negocio entre init y destroy. */
    public void usar() {
        // TODO 4: representa el uso normal del bean (entre init y destroy).
        // TODO 5: añade el literal "uso" a 'fases'.
        // TODO 6: 'usar' puede invocarse varias veces, pero aquí basta registrar el hito.
    }

    /** Llamado al cerrar el contenedor (equivale a @PreDestroy). */
    public void destroy() {
        // TODO 7: @PreDestroy se ejecuta al cerrar el contexto, antes de descartar el bean.
        // TODO 8: añade el literal "destroy" a 'fases'.
        // TODO 9: tras destroy no debería haber más uso (invariante del ciclo de vida).
    }

    /**
     * @return las fases registradas en orden de ejecución
     */
    public List<String> fases() {
        // TODO 10: devuelve la lista 'fases' (el orden esperado es init, uso, destroy).
        return fases;
    }

    public static void main(String[] args) {
        var b = new Ej034BeanLifecycle();
        b.init();
        b.usar();
        b.destroy();
        System.out.println(b.fases());
    }

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: DTO que simula la ejecución de un método de inicialización
     * usando la anotación jakarta.annotation.PostConstruct de forma reflectiva.
     */
    public static class BeanLifecycleConAnotaciones {
        private boolean inicializado = false;

        @jakarta.annotation.PostConstruct
        public void alIniciar() {
            // TODO extra (Reto 1): Marca inicializado como true.
            this.inicializado = true;
        }

        public boolean isInicializado() {
            return inicializado;
        }
    }

    /**
     * Reto Extra 2: Inicialización y destrucción de recursos implementando
     * las interfaces clásicas de Spring InitializingBean y DisposableBean.
     */
    public static class BeanLifecycleConInterfaces implements org.springframework.beans.factory.InitializingBean, org.springframework.beans.factory.DisposableBean {
        private boolean inicializado = false;
        private boolean destruido = false;

        @Override
        public void afterPropertiesSet() throws Exception {
            // TODO extra (Reto 2): Se ejecuta tras la inyección de propiedades.
            this.inicializado = true;
        }

        @Override
        public void destroy() throws Exception {
            // TODO extra (Reto 2): Se ejecuta al destruir el bean.
            this.destruido = true;
        }

        public boolean isInicializado() { return inicializado; }
        public boolean isDestruido() { return destruido; }
    }

    /**
     * Reto Extra 3: Implementación personalizada de BeanPostProcessor para interceptar
     * e inyectar valores modificados de forma reflectiva en beans específicos tras su creación.
     */
    public static class CustomBeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) {
            // TODO extra (Reto 3): Intercepción antes de la inicialización de los beans.
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) {
            // TODO extra (Reto 3): Intercepción tras la inicialización.
            return bean;
        }
    }

    /**
     * Reto Extra 4: Comprueba de forma programática si al cerrar el ApplicationContext
     * se dispararon correctamente las llamadas a @PreDestroy de un bean.
     */
    public static boolean verificarLlamadaPreDestroy(org.springframework.context.support.GenericApplicationContext ctx, String nombreBean) {
        // TODO extra (Reto 4): Cierra el contexto y verifica si se disparó la destrucción.
        return false;
    }

    /**
     * Reto Extra 5: Declaración de inicialización explícita utilizando los atributos
     * de configuración de métodos initMethod y destroyMethod en la definición de beans.
     */
    public static class InitMetodoEnConfiguracion {
        private boolean initLlamado = false;
        private boolean destroyLlamado = false;

        public void miInitPersonalizado() {
            // TODO extra (Reto 5): Inicialización declarada.
            this.initLlamado = true;
        }

        public void miDestroyPersonalizado() {
            // TODO extra (Reto 5): Destrucción declarada.
            this.destroyLlamado = true;
        }

        public boolean isInitLlamado() { return initLlamado; }
        public boolean isDestroyLlamado() { return destroyLlamado; }
    }

    /**
     * Reto Extra 6: DTO que implementa las interfaces BeanNameAware y ApplicationContextAware
     * para autoconsultar su propio nombre y contexto en caliente.
     */
    public static class BeanConAwareInterfaces implements org.springframework.beans.factory.BeanNameAware, org.springframework.context.ApplicationContextAware {
        private String beanName;
        private org.springframework.context.ApplicationContext context;

        @Override
        public void setBeanName(String name) {
            // TODO extra (Reto 6): Asigna el nombre asignado al bean por Spring.
            this.beanName = name;
        }

        @Override
        public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) {
            // TODO extra (Reto 6): Asigna el contexto de la aplicación.
            this.context = applicationContext;
        }

        public String getBeanName() { return beanName; }
        public org.springframework.context.ApplicationContext getContext() { return context; }
    }

    /**
     * Reto Extra 7: Un BeanPostProcessor que audita y registra el tiempo exacto en milisegundos
     * que toma la creación e inicialización de cada bean.
     */
    public static class PostProcessorDeAuditoria implements org.springframework.beans.factory.config.BeanPostProcessor {
        private final java.util.Map<String, Long> tiempos = new java.util.HashMap<>();

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) {
            // TODO extra (Reto 7): Registra la marca temporal de inicio.
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) {
            // TODO extra (Reto 7): Calcula la diferencia de tiempo transcurrido y guárdala en el mapa.
            return bean;
        }

        public java.util.Map<String, Long> getTiempos() {
            return tiempos;
        }
    }

    /**
     * Reto Extra 8: Callback de destrucción defensivo para evitar que un error durante
     * el apagado de un bean provoque excepciones nulas o bloquee la parada del sistema.
     */
    public static void EvitarDestroyNulo(AutoCloseable recurso) {
        // TODO extra (Reto 8): Invoca recurso.close() de forma segura capturando posibles excepciones.
    }

    /**
     * Reto Extra 9: Devuelve una lista cronológica que verifique el orden exacto de precedencia
     * en el ciclo de vida entre PostConstruct, InitializingBean y un init-method personalizado.
     */
    public static java.util.List<String> obtenerOrdenDeCallbacks() {
        // TODO extra (Reto 9): Retorna los literales "PostConstruct", "InitializingBean" y "InitMethod" en su orden real de ejecución en Spring.
        return java.util.List.of();
    }

    /**
     * Reto Extra 10: Intercepta mediante un BeanPostProcessor personalizado la inicialización de beans configurados
     * como Lazy e imprime la marca temporal exacta de su inicialización tardía.
     */
    public static class IntercepcionConPostProcessorLazy implements org.springframework.beans.factory.config.BeanPostProcessor {
        private String ultimoLazyInicializado;

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) {
            // TODO extra (Reto 10): Comprueba si el bean tiene la anotación @Lazy o su definición es lazy, y almacena su nombre.
            return bean;
        }

        public String getUltimoLazyInicializado() {
            return ultimoLazyInicializado;
        }
    }

}
