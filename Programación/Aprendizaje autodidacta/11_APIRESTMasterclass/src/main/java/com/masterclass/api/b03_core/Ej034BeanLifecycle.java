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
        // TODO extra: Reto Extra 1: DTO que simula la ejecución de un método de inicialización
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 2: Inicialización y destrucción de recursos implementando
     * las interfaces clásicas de Spring InitializingBean y DisposableBean.
     */
    public static class BeanLifecycleConInterfaces implements org.springframework.beans.factory.InitializingBean, org.springframework.beans.factory.DisposableBean {
        // TODO extra: Reto Extra 2: Inicialización y destrucción de recursos implementando
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 3: Implementación personalizada de BeanPostProcessor para interceptar
     * e inyectar valores modificados de forma reflectiva en beans específicos tras su creación.
     */
    public static class CustomBeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {
        // TODO extra: Reto Extra 3: Implementación personalizada de BeanPostProcessor para interceptar
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 4: Comprueba de forma programática si al cerrar el ApplicationContext
     * se dispararon correctamente las llamadas a @PreDestroy de un bean.
     */
    public static boolean verificarLlamadaPreDestroy(org.springframework.context.support.GenericApplicationContext ctx, String nombreBean) {
        // TODO extra: Reto Extra 4: Comprueba de forma programática si al cerrar el ApplicationContext
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para verificarLlamadaPreDestroy");
    }

    /**
     * Reto Extra 5: Declaración de inicialización explícita utilizando los atributos
     * de configuración de métodos initMethod y destroyMethod en la definición de beans.
     */
    public static class InitMetodoEnConfiguracion {
        // TODO extra: Reto Extra 5: Declaración de inicialización explícita utilizando los atributos
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 6: DTO que implementa las interfaces BeanNameAware y ApplicationContextAware
     * para autoconsultar su propio nombre y contexto en caliente.
     */
    public static class BeanConAwareInterfaces implements org.springframework.beans.factory.BeanNameAware, org.springframework.context.ApplicationContextAware {
        // TODO extra: Reto Extra 6: DTO que implementa las interfaces BeanNameAware y ApplicationContextAware
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 7: Un BeanPostProcessor que audita y registra el tiempo exacto en milisegundos
     * que toma la creación e inicialización de cada bean.
     */
    public static class PostProcessorDeAuditoria implements org.springframework.beans.factory.config.BeanPostProcessor {
        // TODO extra: Reto Extra 7: Un BeanPostProcessor que audita y registra el tiempo exacto en milisegundos
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 8: Callback de destrucción defensivo para evitar que un error durante
     * el apagado de un bean provoque excepciones nulas o bloquee la parada del sistema.
     */
    public static void EvitarDestroyNulo(AutoCloseable recurso) {
        // TODO extra: Reto Extra 8: Callback de destrucción defensivo para evitar que un error durante
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para EvitarDestroyNulo");
    }

    /**
     * Reto Extra 9: Devuelve una lista cronológica que verifique el orden exacto de precedencia
     * en el ciclo de vida entre PostConstruct, InitializingBean y un init-method personalizado.
     */
    public static java.util.List<String> obtenerOrdenDeCallbacks() {
        // TODO extra: Reto Extra 9: Devuelve una lista cronológica que verifique el orden exacto de precedencia
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerOrdenDeCallbacks");
    }

    /**
     * Reto Extra 10: Intercepta mediante un BeanPostProcessor personalizado la inicialización de beans configurados
     * como Lazy e imprime la marca temporal exacta de su inicialización tardía.
     */
    public static class IntercepcionConPostProcessorLazy implements org.springframework.beans.factory.config.BeanPostProcessor {
        // TODO extra: Reto Extra 10: Intercepta mediante un BeanPostProcessor personalizado la inicialización de beans configurados
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

}
