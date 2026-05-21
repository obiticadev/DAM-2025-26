package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej043AutoConfigurationInsightTest {

    private final Set<String> cp = Set.of("javax.sql.DataSource", "org.h2.Driver");

    @Test
    void activaSiClaseYNoHayBeanUsuario() {
        assertTrue(Ej043AutoConfigurationInsight.shouldActivate(cp, "org.h2.Driver", false));
    }

    @Test
    void noActivaSiUsuarioYaTieneBean() {
        assertFalse(Ej043AutoConfigurationInsight.shouldActivate(cp, "org.h2.Driver", true));
    }

    @Test
    void noActivaSiFaltaClase() {
        assertFalse(Ej043AutoConfigurationInsight.shouldActivate(cp, "org.postgresql.Driver", false));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 01")
    @org.junit.jupiter.api.Test
    void testPasoExtra01() {
        @org.springframework.boot.autoconfigure.SpringBootApplication(exclude = {
            org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
        })
        class DummyApplication {}

        assertTrue(Ej043AutoConfigurationInsight.pasoExtra01(DummyApplication.class, "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"));
        assertFalse(Ej043AutoConfigurationInsight.pasoExtra01(DummyApplication.class, "org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 02")
    @org.junit.jupiter.api.Test
    void testPasoExtra02() {
        var service = Ej043AutoConfigurationInsight.pasoExtra02();
        assertNotNull(service);
        assertEquals("Autoconfigured", service.getOrigin());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 03")
    @org.junit.jupiter.api.Test
    void testPasoExtra03() {
        var userBean = Ej043AutoConfigurationInsight.pasoExtra03(true);
        assertNotNull(userBean);
        assertTrue(userBean instanceof Ej043AutoConfigurationInsight.UserConfiguredService);

        var autoBean = Ej043AutoConfigurationInsight.pasoExtra03(false);
        assertNotNull(autoBean);
        assertTrue(autoBean instanceof Ej043AutoConfigurationInsight.AutoConfiguredService);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 04")
    @org.junit.jupiter.api.Test
    void testPasoExtra04() {
        var comp = Ej043AutoConfigurationInsight.pasoExtra04();
        assertNotNull(comp);
        assertEquals("Web Context", comp.getContextType());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 05")
    @org.junit.jupiter.api.Test
    void testPasoExtra05() {
        var comp = Ej043AutoConfigurationInsight.pasoExtra05();
        assertNotNull(comp);
        assertEquals("Non-Web Context", comp.getContextType());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 06")
    @org.junit.jupiter.api.Test
    void testPasoExtra06() {
        assertTrue(Ej043AutoConfigurationInsight.pasoExtra06(List.of("singleBean"), "somePrimary"));
        assertTrue(Ej043AutoConfigurationInsight.pasoExtra06(List.of("beanA", "somePrimary"), "somePrimary"));
        assertFalse(Ej043AutoConfigurationInsight.pasoExtra06(List.of("beanA", "beanB"), "somePrimary"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 07")
    @org.junit.jupiter.api.Test
    void testPasoExtra07() {
        var comp = Ej043AutoConfigurationInsight.pasoExtra07();
        assertNotNull(comp);
        assertEquals("Active", comp.status());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 08")
    @org.junit.jupiter.api.Test
    void testPasoExtra08() {
        var sorted = Ej043AutoConfigurationInsight.pasoExtra08(List.of(
            "HibernateJpaAutoConfiguration",
            "DataSourceAutoConfiguration"
        ));
        assertNotNull(sorted);
        assertEquals("DataSourceAutoConfiguration", sorted.get(0));
        assertEquals("HibernateJpaAutoConfiguration", sorted.get(1));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 09")
    @org.junit.jupiter.api.Test
    void testPasoExtra09() {
        var imports = "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration\n"
                    + "org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration";
        assertTrue(Ej043AutoConfigurationInsight.pasoExtra09(imports, "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"));
        assertFalse(Ej043AutoConfigurationInsight.pasoExtra09(imports, "org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @org.junit.jupiter.api.Test
    void testPasoExtra10() {
        var context = new org.springframework.context.support.GenericApplicationContext();
        var report = org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport.get(context.getBeanFactory());
        var matched = Ej043AutoConfigurationInsight.pasoExtra10(report);
        assertNotNull(matched);
    }
}
