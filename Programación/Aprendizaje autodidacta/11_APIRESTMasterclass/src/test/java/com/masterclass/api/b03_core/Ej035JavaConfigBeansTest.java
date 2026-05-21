package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b03_core.Ej035JavaConfigBeans.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import java.time.Clock;
import static org.junit.jupiter.api.Assertions.*;

class Ej035JavaConfigBeansTest {

    @Test
    void elContextoEntregaElClock() {
        Clock c = Ej035JavaConfigBeans.obtenerClock();
        assertNotNull(c, "el @Bean clock() debe estar definido y registrado");
        assertNotNull(c.instant());
    }

    @Configuration
    static class MiConfigSimple {
        @Bean
        public String miMensaje() { return "Configurado"; }
    }

    @Configuration
    static class ConfigMetodo {
        @Bean
        public StringBuilder sb() { return new StringBuilder("Base"); }
        @Bean
        public String mensaje(StringBuilder s) { return s.append("Metodo").toString(); }
    }

    @Configuration
    static class ConfigParametro {
        @Bean
        public String base() { return "Parametro"; }
        @Bean
        public String mensajeCompleto(String b) { return b + "Inyectado"; }
    }

    @Configuration
    @Import(MiConfigSimple.class)
    static class ConfigImportadora {}

    @Configuration
    static class ConfigProfile {
        @Bean
        @Profile("dev")
        public String devBean() { return "dev"; }
        @Bean
        @Profile("prod")
        public String prodBean() { return "prod"; }
    }

    @Configuration
    @PropertySource("classpath:application.properties")
    static class ConfigProperties {
        @Value("${app.nombre:Masterclass}")
        private String appNombre;
        @Bean
        public String nombre() { return appNombre; }
    }

    static class ConfigLite {
        @Bean
        public StringBuilder sbLite() { return new StringBuilder("Lite"); }
    }

    @Configuration
    static class ConfigAlias {
        @Bean(name = {"miAliasA", "miAliasB"})
        public String aliasBean() { return "Alias"; }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_obtenerBeanJavaConfig() {
        Object bean = Ej035JavaConfigBeans.obtenerBeanJavaConfig(MiConfigSimple.class, "miMensaje");
        assertEquals("Configurado", bean);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_configuracionConInyeccionMetodo() {
        try (var ctx = new AnnotationConfigApplicationContext(ConfigMetodo.class)) {
            String m = ctx.getBean("mensaje", String.class);
            assertTrue(m.contains("Metodo"));
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_configuracionConInyeccionParametro() {
        try (var ctx = new AnnotationConfigApplicationContext(ConfigParametro.class)) {
            String m = ctx.getBean("mensajeCompleto", String.class);
            assertEquals("ParametroInyectado", m);
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_importarConfiguracionesMultiples() {
        var ctx = Ej035JavaConfigBeans.ImportarConfiguracionesMultiples(MiConfigSimple.class, ConfigParametro.class);
        try (ctx) {
            assertTrue(ctx.containsBean("miMensaje"));
            assertTrue(ctx.containsBean("mensajeCompleto"));
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_beanCondicionalPorProfile() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.getEnvironment().setActiveProfiles("dev");
            ctx.register(ConfigProfile.class);
            ctx.refresh();
            
            assertTrue(ctx.containsBean("devBean"));
            assertFalse(ctx.containsBean("prodBean"));
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_propiedadesExternasValue() {
        try (var ctx = new AnnotationConfigApplicationContext(ConfigProperties.class)) {
            String nombre = ctx.getBean("nombre", String.class);
            assertNotNull(nombre);
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_configuracionLiteMode() {
        try (var ctx = new AnnotationConfigApplicationContext(ConfigLite.class)) {
            StringBuilder sb1 = ctx.getBean(StringBuilder.class);
            assertNotNull(sb1);
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_registrarBeanDefinitionProgrammatic() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.refresh();
            Ej035JavaConfigBeans.registrarBeanDefinitionProgrammatic(ctx, "manualString", String.class);
            
            assertTrue(ctx.containsBean("manualString"));
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_configuracionDinamicaImportSelector() {
        var selector = new ConfiguracionDinamicaImportSelector();
        String[] imports = selector.selectImports(null);
        assertNotNull(imports);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_beanAliasJavaConfig() {
        try (var ctx = new AnnotationConfigApplicationContext(ConfigAlias.class)) {
            Object a = ctx.getBean("miAliasA");
            Object b = ctx.getBean("miAliasB");
            assertSame(a, b);
            assertEquals("Alias", a);
        }
    }
}

