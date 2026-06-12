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

        // GUÍA: teoría 3.6 (en Spring marcarías este método con
        // @jakarta.annotation.PostConstruct para que se llame tras inyectar; aquí lo invocas tú).
        public void alIniciar() {
            // GUÍA: una línea — inicializado = true;
            throw new UnsupportedOperationException("TODO: Implementar alIniciar (@PostConstruct)");
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
        public void afterPropertiesSet() {
            // GUÍA: teoría 3.6 (InitializingBean.afterPropertiesSet == @PostConstruct).
            // Una línea — inicializado = true;
            throw new UnsupportedOperationException("TODO: Implementar afterPropertiesSet");
        }

        @Override
        public void destroy() {
            // GUÍA: teoría 3.6 (DisposableBean.destroy == @PreDestroy).
            // Una línea — destruido = true;
            throw new UnsupportedOperationException("TODO: Implementar destroy");
        }

        public boolean isInicializado() { return inicializado; }
        public boolean isDestruido() { return destruido; }
    }

    /**
     * Reto Extra 3: Implementación personalizada de BeanPostProcessor para interceptar
     * e inyectar valores modificados de forma reflectiva en beans específicos tras su creación.
     */
    public static class CustomBeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {
        // GUÍA: teoría 3.6 (un BeanPostProcessor intercepta TODOS los beans antes/después de
        // inicializarlos). Devolver el MISMO bean = no tocarlo; devolver otro = sustituirlo.
        // Los dos métodos de la interfaz son 'default' y ya devuelven el bean tal cual, por eso
        // esta clase compila vacía. Si quisieras transformar el bean, sobrescribe aquí
        // postProcessBeforeInitialization / postProcessAfterInitialization y devuelve el bean.
        // OJO: el test pasa un Object y espera assertSame en before y after (es decir, devolver
        //   el mismo objeto). El comportamiento por defecto ya lo cumple.
    }

    /**
     * Reto Extra 4: Comprueba de forma programática si al cerrar el ApplicationContext
     * se dispararon correctamente las llamadas a @PreDestroy de un bean.
     */
    public static boolean verificarLlamadaPreDestroy(org.springframework.context.support.GenericApplicationContext ctx, String nombreBean) {
        // GUÍA: teoría 3.6 (al cerrar el contexto se llama a destroy()/@PreDestroy de los singletons).
        // 1. Recupera el bean ANTES de cerrar: var b = (BeanLifecycleConInterfaces) ctx.getBean(nombreBean).
        // 2. Cierra el contexto: ctx.close();  // dispara destroy()
        // 3. return b.isDestruido();           // ¿se ejecutó el callback?
        // OJO: el test comprueba !b.isDestruido() ANTES y espera que devuelvas true (tras cerrar,
        //   el bean quedó destruido). Mantén la referencia al bean ANTES de close().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para verificarLlamadaPreDestroy");
    }

    /**
     * Reto Extra 5: Declaración de inicialización explícita utilizando los atributos
     * de configuración de métodos initMethod y destroyMethod en la definición de beans.
     */
    public static class InitMetodoEnConfiguracion {
        private boolean initLlamado = false;
        private boolean destroyLlamado = false;

        // GUÍA: teoría 3.7 (estos nombres se enlazan en @Bean(initMethod=..., destroyMethod=...)).
        public void miInitPersonalizado() {
            // GUÍA: una línea — initLlamado = true;
            throw new UnsupportedOperationException("TODO: Implementar miInitPersonalizado");
        }

        public void miDestroyPersonalizado() {
            // GUÍA: una línea — destroyLlamado = true;
            throw new UnsupportedOperationException("TODO: Implementar miDestroyPersonalizado");
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
            // GUÍA: teoría 3.6 (Spring te "inyecta" tu propio nombre llamando a este setter).
            // Una línea — this.beanName = name;
            throw new UnsupportedOperationException("TODO: Implementar setBeanName");
        }

        @Override
        public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) {
            // GUÍA: teoría 3.6 (Spring te pasa el propio contexto por este setter).
            // Una línea — this.context = applicationContext;
            throw new UnsupportedOperationException("TODO: Implementar setApplicationContext");
        }

        public String getBeanName() { return beanName; }
        public org.springframework.context.ApplicationContext getContext() { return context; }
    }

    /**
     * Reto Extra 7: Un BeanPostProcessor que audita y registra el tiempo exacto en milisegundos
     * que toma la creación e inicialización de cada bean.
     */
    public static class PostProcessorDeAuditoria implements org.springframework.beans.factory.config.BeanPostProcessor {
        private final java.util.Map<String, Long> inicios = new java.util.HashMap<>();
        private final java.util.Map<String, Long> tiempos = new java.util.HashMap<>();

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) {
            // GUÍA: teoría 3.6 (marca el instante de inicio ANTES de inicializar el bean).
            // 1. inicios.put(beanName, System.nanoTime());
            // 2. return bean;   // SIEMPRE devuelve el bean (si no, rompes el arranque).
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) {
            // GUÍA: teoría 3.6 (al terminar, calcula la duración y guárdala).
            // 1. Long t0 = inicios.get(beanName); si no es null ->
            //    tiempos.put(beanName, System.nanoTime() - t0);
            // 2. return bean;
            // OJO: el test registra este post-procesador y un bean y solo comprueba que
            //   getTiempos() NO esté vacío tras el refresh. NO lances aquí: Spring llama a este
            //   método para CADA bean durante el arranque.
            return bean;
        }

        public java.util.Map<String, Long> getTiempos() { return tiempos; }
    }

    /**
     * Reto Extra 8: Callback de destrucción defensivo para evitar que un error durante
     * el apagado de un bean provoque excepciones nulas o bloquee la parada del sistema.
     */
    public static void EvitarDestroyNulo(AutoCloseable recurso) {
        // GUÍA: teoría 1.9 + 3.6 (cerrar un recurso de forma defensiva al apagar).
        // 1. Si 'recurso' es null -> simplemente return (no hagas nada, no falles).
        // 2. Si no, try { recurso.close(); } catch (Exception e) { /* traga/loguea */ }
        //    para que un fallo al cerrar no tumbe el resto del apagado.
        // OJO: el test pasa un AutoCloseable que pone un flag a true (debe ejecutarse) y también
        //   pasa null esperando assertDoesNotThrow. Por eso NO dejes el throw: este método debe
        //   ejecutarse limpiamente en ambos casos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para EvitarDestroyNulo");
    }

    /**
     * Reto Extra 9: Devuelve una lista cronológica que verifique el orden exacto de precedencia
     * en el ciclo de vida entre PostConstruct, InitializingBean y un init-method personalizado.
     */
    public static java.util.List<String> obtenerOrdenDeCallbacks() {
        // GUÍA: teoría 3.6 (el orden de inicialización cuando coinciden las tres formas).
        // Una línea — return java.util.List.of("PostConstruct", "InitializingBean", "InitMethod");
        // OJO: el test compara con esa lista EXACTA y en ESE orden: primero @PostConstruct,
        //   luego afterPropertiesSet() (InitializingBean) y por último el init-method del @Bean.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerOrdenDeCallbacks");
    }

    /**
     * Reto Extra 10: Intercepta mediante un BeanPostProcessor personalizado la inicialización de beans configurados
     * como Lazy e imprime la marca temporal exacta de su inicialización tardía.
     */
    public static class IntercepcionConPostProcessorLazy implements org.springframework.beans.factory.config.BeanPostProcessor {
        private String ultimoLazyInicializado = null;

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) {
            // GUÍA: teoría 3.5/3.6 (un bean @Lazy no se inicializa hasta que se pide; cuando
            // por fin pasa por aquí, registramos su nombre).
            // 1. ultimoLazyInicializado = beanName;   // (en el test solo se inicializa el lazy)
            // 2. return bean;
            // OJO: el test comprueba getUltimoLazyInicializado()==null tras el refresh (el lazy
            //   aún no se ha creado) y, tras ctx.getBean(MiBeanLazy.class), espera
            //   "ej034BeanLifecycleTest.MiBeanLazy". NO lances: este método corre para cada bean.
            return bean;
        }

        public String getUltimoLazyInicializado() { return ultimoLazyInicializado; }
    }

}
