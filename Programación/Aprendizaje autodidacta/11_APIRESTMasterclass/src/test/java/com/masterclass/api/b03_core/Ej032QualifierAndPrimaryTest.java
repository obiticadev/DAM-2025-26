package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b03_core.Ej032QualifierAndPrimary.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej032QualifierAndPrimaryTest {

    @Test
    void resuelvePorQualifier() {
        assertEquals("sms:hi", Ej032QualifierAndPrimary.resolver("sms").enviar("hi"));
        assertEquals("email:hi", Ej032QualifierAndPrimary.resolver("email").enviar("hi"));
    }

    @Test
    void qualifierInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej032QualifierAndPrimary.resolver("fax"));
    }

    @Test
    void primarioEsEmail() {
        assertInstanceOf(EmailNotificador.class, Ej032QualifierAndPrimary.primario());
    }

    @Component
    @Primary
    static class MiNotificadorPrimary implements Notificador {
        public String enviar(String msg) { return "primary:" + msg; }
    }

    @Component
    @Qualifier("smsSpecial")
    static class MiNotificadorSms implements Notificador {
        public String enviar(String msg) { return "smsSpecial:" + msg; }
    }

    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @Qualifier
    @interface MiCalificadorCustom {}

    @Component
    @MiCalificadorCustom
    static class MiNotificadorConAnotacionCustom implements Notificador {
        public String enviar(String msg) { return "custom:" + msg; }
    }

    @Test
    void retoExtra01_obtenerBeanPrimary() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiNotificadorPrimary.class, MiNotificadorSms.class);
            ctx.refresh();
            Notificador p = Ej032QualifierAndPrimary.obtenerBeanPrimary(ctx, Notificador.class);
            assertNotNull(p);
            assertEquals("primary:test", p.enviar("test"));
        }
    }

    @Test
    void retoExtra02_obtenerBeanQualifier() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiNotificadorPrimary.class, MiNotificadorSms.class);
            ctx.refresh();
            Notificador q = Ej032QualifierAndPrimary.obtenerBeanQualifier(ctx, Notificador.class, "smsSpecial");
            assertNotNull(q);
            assertEquals("smsSpecial:test", q.enviar("test"));
        }
    }

    @Test
    void retoExtra03_obtenerTodosLosBeansComoMapa() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiNotificadorPrimary.class, MiNotificadorSms.class);
            ctx.refresh();
            Map<String, Notificador> map = Ej032QualifierAndPrimary.obtenerTodosLosBeansComoMapa(ctx, Notificador.class);
            assertEquals(2, map.size());
            assertTrue(map.containsKey("ej032QualifierAndPrimaryTest.MiNotificadorPrimary"));
            assertTrue(map.containsKey("ej032QualifierAndPrimaryTest.MiNotificadorSms"));
        }
    }

    @Test
    void retoExtra04_servicioDesambiguador() {
        var p = new MiNotificadorPrimary();
        var s = new MiNotificadorSms();
        var des = new ServicioDesambiguador(List.of(s, p));
        // Debe resolver preferentemente el primario 'p'
        assertEquals(p, des.resolverNotificador());
    }

    @Test
    void retoExtra05_servicioDualQualifier() {
        var email = new EmailNotificador();
        var sms = new SmsNotificador();
        var dual = new ServicioDualQualifier(email, sms);
        assertEquals("email:alerta & sms:alerta", dual.notificarAmbos("alerta"));
    }

    @Test
    void retoExtra06_resolucionConAnotacionCustom() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiNotificadorPrimary.class, MiNotificadorConAnotacionCustom.class);
            ctx.refresh();
            Object res = Ej032QualifierAndPrimary.resolucionConAnotacionCustom(ctx, Notificador.class, MiCalificadorCustom.class);
            assertNotNull(res);
            assertTrue(res instanceof MiNotificadorConAnotacionCustom);
        }
    }

    @Test
    void retoExtra07_esBeanPrimaryProgramatico() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiNotificadorPrimary.class, MiNotificadorSms.class);
            ctx.refresh();
            assertTrue(Ej032QualifierAndPrimary.esBeanPrimaryProgramatico(ctx, "ej032QualifierAndPrimaryTest.MiNotificadorPrimary"));
            assertFalse(Ej032QualifierAndPrimary.esBeanPrimaryProgramatico(ctx, "ej032QualifierAndPrimaryTest.MiNotificadorSms"));
        }
    }

    @Test
    void retoExtra08_contarBeansPorCalificador() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiNotificadorPrimary.class, MiNotificadorSms.class);
            ctx.refresh();
            int count = Ej032QualifierAndPrimary.contarBeansPorCalificador(ctx, Notificador.class, "smsSpecial");
            assertEquals(1, count);
        }
    }

    @Test
    void retoExtra09_servicioFallbackQualifier() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.refresh(); // vacío
            var fall = new ServicioFallbackQualifier(ctx);
            // "smsSpecial" no existe en el contexto, debe saltar al fallback SmsNotificador
            Notificador n = fall.resolverConFallback("smsSpecial");
            assertNotNull(n);
            assertTrue(n instanceof SmsNotificador);
        }
    }

    @Test
    void retoExtra10_reemplazarPrimaryEnCaliente() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiNotificadorPrimary.class, MiNotificadorSms.class);
            ctx.refresh();
            
            // Antes de cambiar, el primary es MiNotificadorPrimary
            assertEquals("primary:test", ctx.getBean(Notificador.class).enviar("test"));
            
            Ej032QualifierAndPrimary.reemplazarPrimaryEnCaliente(ctx, "ej032QualifierAndPrimaryTest.MiNotificadorSms");
            
            // Ahora el primary debe ser MiNotificadorSms
            assertEquals("smsSpecial:test", ctx.getBean(Notificador.class).enviar("test"));
        }
    }
}

