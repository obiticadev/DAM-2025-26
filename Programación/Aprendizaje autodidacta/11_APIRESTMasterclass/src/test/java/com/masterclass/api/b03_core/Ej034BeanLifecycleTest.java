package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b03_core.Ej034BeanLifecycle.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej034BeanLifecycleTest {

    @Test
    void ordenDeFases() {
        var b = new Ej034BeanLifecycle();
        b.init();
        b.usar();
        b.destroy();
        assertEquals(List.of("init", "uso", "destroy"), b.fases());
    }

    @Configuration
    static class ConfigCicloVida {
        @Bean(initMethod = "miInitPersonalizado", destroyMethod = "miDestroyPersonalizado")
        public InitMetodoEnConfiguracion initMetodoEnConfiguracion() {
            return new InitMetodoEnConfiguracion();
        }
    }

    @Component
    @Lazy
    static class MiBeanLazy {}

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_beanLifecycleConAnotaciones() {
        var b = new BeanLifecycleConAnotaciones();
        assertFalse(b.isInicializado());
        b.alIniciar();
        assertTrue(b.isInicializado());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_beanLifecycleConInterfaces() throws Exception {
        var b = new BeanLifecycleConInterfaces();
        assertFalse(b.isInicializado());
        assertFalse(b.isDestruido());
        
        b.afterPropertiesSet();
        assertTrue(b.isInicializado());
        
        b.destroy();
        assertTrue(b.isDestruido());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_customBeanPostProcessor() {
        var pp = new CustomBeanPostProcessor();
        Object bean = new Object();
        assertSame(bean, pp.postProcessBeforeInitialization(bean, "dummy"));
        assertSame(bean, pp.postProcessAfterInitialization(bean, "dummy"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_verificarLlamadaPreDestroy() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(BeanLifecycleConInterfaces.class);
            ctx.refresh();
            var b = ctx.getBean(BeanLifecycleConInterfaces.class);
            assertFalse(b.isDestruido());
            
            boolean verificado = Ej034BeanLifecycle.verificarLlamadaPreDestroy(ctx, "ej034BeanLifecycleTest.BeanLifecycleConInterfaces");
            assertTrue(verificado);
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_initMetodoEnConfiguracion() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(ConfigCicloVida.class);
            ctx.refresh();
            var b = ctx.getBean(InitMetodoEnConfiguracion.class);
            assertTrue(b.isInitLlamado());
            assertFalse(b.isDestroyLlamado());
            
            ctx.close();
            assertTrue(b.isDestroyLlamado());
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_beanConAwareInterfaces() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(BeanConAwareInterfaces.class);
            ctx.refresh();
            var b = ctx.getBean(BeanConAwareInterfaces.class);
            assertEquals("ej034BeanLifecycleTest.BeanConAwareInterfaces", b.getBeanName());
            assertEquals(ctx, b.getContext());
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_postProcessorDeAuditoria() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            var aud = new PostProcessorDeAuditoria();
            ctx.getBeanFactory().addBeanPostProcessor(aud);
            ctx.register(BeanLifecycleConAnotaciones.class);
            ctx.refresh();
            
            Map<String, Long> tiempos = aud.getTiempos();
            assertFalse(tiempos.isEmpty());
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_evitarDestroyNulo() {
        boolean[] cerrado = {false};
        AutoCloseable rec = () -> cerrado[0] = true;
        Ej034BeanLifecycle.EvitarDestroyNulo(rec);
        assertTrue(cerrado[0]);

        // No debe fallar ni lanzar excepción en caso de nulo
        assertDoesNotThrow(() -> Ej034BeanLifecycle.EvitarDestroyNulo(null));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_obtenerOrdenDeCallbacks() {
        List<String> orden = Ej034BeanLifecycle.obtenerOrdenDeCallbacks();
        assertEquals(List.of("PostConstruct", "InitializingBean", "InitMethod"), orden);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_intercepcionConPostProcessorLazy() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            var lazyPP = new IntercepcionConPostProcessorLazy();
            ctx.getBeanFactory().addBeanPostProcessor(lazyPP);
            ctx.register(MiBeanLazy.class);
            ctx.refresh();
            
            // El bean lazy no debe haberse inicializado aún
            assertNull(lazyPP.getUltimoLazyInicializado());
            
            ctx.getBean(MiBeanLazy.class);
            assertEquals("ej034BeanLifecycleTest.MiBeanLazy", lazyPP.getUltimoLazyInicializado());
        }
    }
}

