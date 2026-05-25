package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej030ComponentScanTest {

    @Test
    void contenedorDevuelveBeanFuncional() {
        var s = Ej030ComponentScan.obtenerBean();
        assertNotNull(s, "el contexto debe entregar el bean");
        assertEquals("hola", s.saludar());
    }

    @Component
    @Scope("prototype")
    static class MiPrototypeBean {}

    @Component
    @Lazy
    static class MiLazyBean {}

    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @interface CustomAnnotation {}

    @Component
    @CustomAnnotation
    static class BeanConAnotacion {}

    @Test
    void retoExtra01_obtenerBeanPorNombre() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(Ej030ComponentScan.Saludador.class);
            ctx.refresh();
            Object bean = Ej030ComponentScan.obtenerBeanPorNombre(ctx, "saludador");
            assertNotNull(bean);
            assertTrue(bean instanceof Ej030ComponentScan.Saludador);
        }
    }

    @Test
    void retoExtra02_esSingleton() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(Ej030ComponentScan.Saludador.class);
            ctx.register(MiPrototypeBean.class);
            ctx.refresh();
            assertTrue(Ej030ComponentScan.esSingleton(ctx, "saludador"));
            assertFalse(Ej030ComponentScan.esSingleton(ctx, "ej030ComponentScanTest.MiPrototypeBean"));
        }
    }

    @Test
    void retoExtra03_crearContextoFiltradoConExclusion() {
        var ctx = Ej030ComponentScan.crearContextoFiltradoConExclusion(MiPrototypeBean.class);
        try (ctx) {
            assertNotNull(ctx);
            // Debe poder resolver Saludador si está en el mismo paquete y no excluido
            assertTrue(ctx.containsBeanDefinition("saludador"));
            // No debe contener la clase excluida
            assertFalse(ctx.containsBean("ej030ComponentScanTest.MiPrototypeBean"));
        }
    }

    @Test
    void retoExtra04_contarBeansDefinidos() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(Ej030ComponentScan.Saludador.class);
            ctx.register(MiPrototypeBean.class);
            ctx.refresh();
            int count = Ej030ComponentScan.contarBeansDefinidos(ctx);
            assertTrue(count >= 2);
        }
    }

    @Test
    void retoExtra05_registrarBeanEnCaliente() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.refresh();
            String manualInst = "Instancia manual";
            Ej030ComponentScan.registrarBeanEnCaliente(ctx, "miManual", manualInst);
            assertEquals(manualInst, ctx.getBean("miManual"));
        }
    }

    @Test
    void retoExtra06_esBeanPrototypeDefinido() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(Ej030ComponentScan.Saludador.class);
            ctx.register(MiPrototypeBean.class);
            ctx.refresh();
            assertTrue(Ej030ComponentScan.esBeanPrototypeDefinido(ctx, "ej030ComponentScanTest.MiPrototypeBean"));
            assertFalse(Ej030ComponentScan.esBeanPrototypeDefinido(ctx, "saludador"));
        }
    }

    @Test
    void retoExtra07_obtenerBeansPorAnotacion() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(BeanConAnotacion.class);
            ctx.refresh();
            Map<String, Object> beans = Ej030ComponentScan.obtenerBeansPorAnotacion(ctx, CustomAnnotation.class);
            assertEquals(1, beans.size());
            assertTrue(beans.values().iterator().next() instanceof BeanConAnotacion);
        }
    }

    @Test
    void retoExtra08_obtenerNombresDeBeans() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(Ej030ComponentScan.Saludador.class);
            ctx.refresh();
            List<String> nombres = Ej030ComponentScan.obtenerNombresDeBeans(ctx);
            assertFalse(nombres.isEmpty());
            // Verificar orden alfabético
            for (int i = 0; i < nombres.size() - 1; i++) {
                assertTrue(nombres.get(i).compareTo(nombres.get(i + 1)) <= 0);
            }
        }
    }

    @Test
    void retoExtra09_cerrarYVerificarActivo() {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.refresh();
        assertTrue(ctx.isActive());
        boolean cerrado = Ej030ComponentScan.cerrarYVerificarActivo(ctx);
        assertTrue(cerrado);
        assertFalse(ctx.isActive());
    }

    @Test
    void retoExtra10_esDefinicionLazy() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiLazyBean.class);
            ctx.register(Ej030ComponentScan.Saludador.class);
            ctx.refresh();
            assertTrue(Ej030ComponentScan.esDefinicionLazy(ctx, "ej030ComponentScanTest.MiLazyBean"));
            assertFalse(Ej030ComponentScan.esDefinicionLazy(ctx, "saludador"));
        }
    }
}

