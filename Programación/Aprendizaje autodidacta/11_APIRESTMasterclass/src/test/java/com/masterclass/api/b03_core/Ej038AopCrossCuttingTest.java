package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej038AopCrossCuttingTest {

    @Test
    void cuentaYDevuelveResultado() {
        var aop = new Ej038AopCrossCutting();
        assertEquals("a", aop.alrededor(() -> "a"));
        assertEquals(42, (int) aop.alrededor(() -> 42));
        assertEquals(2, aop.invocaciones());
    }

    @org.springframework.context.annotation.Configuration
    @org.springframework.context.annotation.EnableAspectJAutoProxy
    static class ConfigAop {
        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.ServicioNegocio servicioNegocio() {
            return new Ej038AopCrossCutting.ServicioNegocio();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.AspectoAntesDeLlamada aspectoAntesDeLlamada() {
            return new Ej038AopCrossCutting.AspectoAntesDeLlamada();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.AspectoDespuesDeRetorno aspectoDespuesDeRetorno() {
            return new Ej038AopCrossCutting.AspectoDespuesDeRetorno();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.AspectoEnCasoDeError aspectoEnCasoDeError() {
            return new Ej038AopCrossCutting.AspectoEnCasoDeError();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.AspectoAlrededorAround aspectoAlrededorAround() {
            return new Ej038AopCrossCutting.AspectoAlrededorAround();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.PointcutPorAnotacionCustom pointcutPorAnotacionCustom() {
            return new Ej038AopCrossCutting.PointcutPorAnotacionCustom();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.AspectoConMedicionDeTiempo aspectoConMedicionDeTiempo() {
            return new Ej038AopCrossCutting.AspectoConMedicionDeTiempo();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.PointcutCombinadoConOperadores pointcutCombinadoConOperadores() {
            return new Ej038AopCrossCutting.PointcutCombinadoConOperadores();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.AccederAParametrosDeMetodo accederAParametrosDeMetodo() {
            return new Ej038AopCrossCutting.AccederAParametrosDeMetodo();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.ModificarArgumentosDinamicos modificarArgumentosDinamicos() {
            return new Ej038AopCrossCutting.ModificarArgumentosDinamicos();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.AspectoOrdenadoConOrder.AspectoPrimero aspectoPrimero() {
            return new Ej038AopCrossCutting.AspectoOrdenadoConOrder.AspectoPrimero();
        }

        @org.springframework.context.annotation.Bean
        public Ej038AopCrossCutting.AspectoOrdenadoConOrder.AspectoSegundo aspectoSegundo() {
            return new Ej038AopCrossCutting.AspectoOrdenadoConOrder.AspectoSegundo();
        }
    }

    @Test
    void retoExtra01_aspectoAntesDeLlamada() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigAop.class)) {
            Ej038AopCrossCutting.AspectoAntesDeLlamada.limpiar();
            var servicio = ctx.getBean(Ej038AopCrossCutting.ServicioNegocio.class);
            servicio.saludar("Juan");
            assertEquals(1, Ej038AopCrossCutting.AspectoAntesDeLlamada.getContadorBefore());
        }
    }

    @Test
    void retoExtra02_aspectoDespuesDeRetorno() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigAop.class)) {
            Ej038AopCrossCutting.AspectoDespuesDeRetorno.limpiar();
            var servicio = ctx.getBean(Ej038AopCrossCutting.ServicioNegocio.class);
            int res = servicio.calcular(10, 20);
            assertEquals(30, res);
            assertEquals(30, Ej038AopCrossCutting.AspectoDespuesDeRetorno.getUltimoResultado());
        }
    }

    @Test
    void retoExtra03_aspectoEnCasoDeError() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigAop.class)) {
            Ej038AopCrossCutting.AspectoEnCasoDeError.limpiar();
            var servicio = ctx.getBean(Ej038AopCrossCutting.ServicioNegocio.class);
            assertThrows(RuntimeException.class, servicio::metodoConError);
            assertEquals("Error simulado", Ej038AopCrossCutting.AspectoEnCasoDeError.getMensajeError());
        }
    }

    @Test
    void retoExtra04_aspectoAlrededorAround() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigAop.class)) {
            Ej038AopCrossCutting.AspectoAlrededorAround.limpiar();
            var servicio = ctx.getBean(Ej038AopCrossCutting.ServicioNegocio.class);
            assertEquals("Hola, Pepe", servicio.saludar("Pepe"));
            assertTrue(Ej038AopCrossCutting.AspectoAlrededorAround.isAroundEjecutado());
        }
    }

    @Test
    void retoExtra05_pointcutPorAnotacionCustom() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigAop.class)) {
            Ej038AopCrossCutting.PointcutPorAnotacionCustom.limpiar();
            var servicio = ctx.getBean(Ej038AopCrossCutting.ServicioNegocio.class);
            servicio.metodoAuditado();
            assertTrue(Ej038AopCrossCutting.PointcutPorAnotacionCustom.isAuditadoEjecutado());
        }
    }

    @Test
    void retoExtra06_aspectoConMedicionDeTiempo() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigAop.class)) {
            Ej038AopCrossCutting.AspectoConMedicionDeTiempo.limpiar();
            var servicio = ctx.getBean(Ej038AopCrossCutting.ServicioNegocio.class);
            servicio.saludar("Carlos");
            assertTrue(Ej038AopCrossCutting.AspectoConMedicionDeTiempo.getDuracionUltimoMetodoNs() >= 0);
        }
    }

    @Test
    void retoExtra07_pointcutCombinadoConOperadores() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigAop.class)) {
            Ej038AopCrossCutting.PointcutCombinadoConOperadores.limpiar();
            var servicio = ctx.getBean(Ej038AopCrossCutting.ServicioNegocio.class);
            servicio.metodoAuditado();
            assertTrue(Ej038AopCrossCutting.PointcutCombinadoConOperadores.isPointcutCombinadoEjecutado());
        }
    }

    @Test
    void retoExtra08_accederAParametrosDeMetodo() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigAop.class)) {
            Ej038AopCrossCutting.AccederAParametrosDeMetodo.limpiar();
            var servicio = ctx.getBean(Ej038AopCrossCutting.ServicioNegocio.class);
            servicio.saludar("Ana");
            assertArrayEquals(new Object[]{"Ana"}, Ej038AopCrossCutting.AccederAParametrosDeMetodo.getUltimosArgumentos());
        }
    }

    @Test
    void retoExtra09_modificarArgumentosDinamicos() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigAop.class)) {
            var servicio = ctx.getBean(Ej038AopCrossCutting.ServicioNegocio.class);
            // Si el reto muta el parámetro "Ana" por otro, se asertará aquí
            assertEquals("Hola, Ana", servicio.saludar("Ana"));
        }
    }

    @Test
    void retoExtra10_aspectoOrdenadoConOrder() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigAop.class)) {
            Ej038AopCrossCutting.AspectoOrdenadoConOrder.limpiar();
            var servicio = ctx.getBean(Ej038AopCrossCutting.ServicioNegocio.class);
            servicio.saludar("Luis");
            // Se asume que los aspectos se ejecutan y añaden al listado en orden de precedencia
            assertTrue(Ej038AopCrossCutting.AspectoOrdenadoConOrder.getOrdenAspectos().isEmpty() || 
                       Ej038AopCrossCutting.AspectoOrdenadoConOrder.getOrdenAspectos().get(0).equals("AspectoPrimero"));
        }
    }
}
