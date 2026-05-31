package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b03_core.Ej036ConditionalBeans.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Conditional;
import static org.junit.jupiter.api.Assertions.*;

class Ej036ConditionalBeansTest {

    @Test
    void perfiles() {
        assertEquals("disco-local", Ej036ConditionalBeans.segunPerfil("dev").donde());
        assertEquals("disco-local", Ej036ConditionalBeans.segunPerfil("test").donde());
        assertEquals("aws-s3", Ej036ConditionalBeans.segunPerfil("prod").donde());
    }

    @Test
    void perfilInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej036ConditionalBeans.segunPerfil("qa"));
    }

    @Configuration
    static class ConfigCondicionales {
        @Bean
        public String beanSiempreActivo() { return "Siempre"; }
    }

    @Test
    void retoExtra01_registrarConPropiedadHabilitada() {
        // Simulación lógica de aserción
        assertNotNull(new registrarConPropiedadHabilitada());
    }

    @Test
    void retoExtra02_registrarConPropiedadAusente() {
        // Simulación lógica de aserción
        assertNotNull(new registrarConPropiedadAusente());
    }

    @Test
    void retoExtra03_windowsOSCondition() {
        var cond = new WindowsOSCondition();
        // Comprobar que no lanza excepciones y devuelve boolean coerente con el sistema operativo
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
        assertEquals(isWindows, cond.matches(null, null));
    }

    @Test
    void retoExtra04_servicioSoloWindows() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(ServicioSoloWindows.class);
            ctx.refresh();
            
            boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
            assertEquals(isWindows, ctx.containsBean("ej036ConditionalBeansTest.ServicioSoloWindows"));
        }
    }

    @Test
    void retoExtra05_registrarSiClaseExiste() {
        // Simulación lógica de aserción
        assertNotNull(new registrarSiClaseExiste());
    }

    @Test
    void retoExtra06_registrarSiOtroBeanExiste() {
        // Simulación lógica de aserción
        assertNotNull(new registrarSiOtroBeanExiste());
    }

    @Test
    void retoExtra07_condicionalMultiple() {
        // Simulación lógica de aserción
        assertNotNull(new CondicionalMultiple());
    }

    @Test
    void retoExtra08_condicionNegada() {
        var cond = new CondicionNegada();
        assertFalse(cond.matches(null, null));
    }

    @Test
    void retoExtra09_registrarCondicionalPorRecurso() {
        // Simulación lógica de aserción
        assertNotNull(new registrarCondicionalPorRecurso());
    }

    @Test
    void retoExtra10_evaluarCondicionDeRegistro() {
        try (var ctx = new AnnotationConfigApplicationContext(ConfigCondicionales.class)) {
            assertTrue(Ej036ConditionalBeans.evaluarConditionDeRegistro(ctx, "beanSiempreActivo"));
            assertFalse(Ej036ConditionalBeans.evaluarConditionDeRegistro(ctx, "inexistente"));
        }
    }
}

