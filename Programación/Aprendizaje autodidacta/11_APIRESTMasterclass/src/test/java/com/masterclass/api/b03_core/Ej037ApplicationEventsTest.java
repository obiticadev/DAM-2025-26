package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej037ApplicationEventsTest {

    @Test
    void entregaATodosLosListeners() {
        var bus = new Ej037ApplicationEvents();
        List<Object> a = new ArrayList<>();
        List<Object> b = new ArrayList<>();
        bus.subscribe(a::add);
        bus.subscribe(b::add);
        bus.publish("E1");
        bus.publish("E2");
        assertEquals(List.of("E1", "E2"), a);
        assertEquals(List.of("E1", "E2"), b);
    }

    @org.springframework.context.annotation.Configuration
    @org.springframework.scheduling.annotation.EnableAsync
    static class ConfigEventos {
        @org.springframework.context.annotation.Bean
        public OyenteDePedidos oyenteDePedidos() {
            return new OyenteDePedidos();
        }

        @org.springframework.context.annotation.Bean
        public OyenteMontoFiltrado oyenteMontoFiltrado() {
            return new OyenteMontoFiltrado();
        }

        @org.springframework.context.annotation.Bean
        public OyenteGenerico oyenteGenerico() {
            return new OyenteGenerico();
        }

        @org.springframework.context.annotation.Bean
        public OrdenDeListenersConOrder.PrimerOyente primerOyente() {
            return new OrdenDeListenersConOrder.PrimerOyente();
        }

        @org.springframework.context.annotation.Bean
        public OrdenDeListenersConOrder.SegundoOyente segundoOyente() {
            return new OrdenDeListenersConOrder.SegundoOyente();
        }

        @org.springframework.context.annotation.Bean
        public ListenerAsincronoConAsync listenerAsincronoConAsync() {
            return new ListenerAsincronoConAsync();
        }

        @org.springframework.context.annotation.Bean
        public TransitionalEventListenerSim transitionalEventListenerSim() {
            return new TransitionalEventListenerSim();
        }

        @org.springframework.context.annotation.Bean
        public ListenerDeEventosContexto listenerDeEventosContexto() {
            return new ListenerDeEventosContexto();
        }

        @org.springframework.context.annotation.Bean
        public OyenteConFirma oyenteConFirma() {
            return new OyenteConFirma("Firmante A");
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_publicarEventoCustom() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigEventos.class)) {
            OyenteDePedidos.limpiar();
            Ej037ApplicationEvents.publicarEventoCustom(ctx, "Pedido de prueba");
            // Se asume que publicar lo envía al contexto de Spring
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_oyenteDePedidos() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigEventos.class)) {
            OyenteDePedidos.limpiar();
            ctx.publishEvent(new PedidoCreadoEvent(this, "Pedido 1"));
            assertEquals(List.of("Pedido 1"), OyenteDePedidos.getMensajes());
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_oyenteMontoFiltrado() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigEventos.class)) {
            OyenteMontoFiltrado.limpiar();
            ctx.publishEvent(new EventoMonto(50.0));
            ctx.publishEvent(new EventoMonto(150.0));
            assertEquals(List.of(150.0), OyenteMontoFiltrado.getMontos());
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_publicarEventosMultiples() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigEventos.class)) {
            OyenteDePedidos.limpiar();
            var e1 = new PedidoCreadoEvent(this, "A");
            var e2 = new PedidoCreadoEvent(this, "B");
            Ej037ApplicationEvents.publicarEventosMultiples(ctx, List.of(e1, e2));
            assertEquals(List.of("A", "B"), OyenteDePedidos.getMensajes());
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_eventoGenericoPojo() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigEventos.class)) {
            OyenteGenerico.limpiar();
            ctx.publishEvent(new EventoGenericoPojo("Info Pojo"));
            assertEquals(List.of("Info Pojo"), OyenteGenerico.getInfos());
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_ordenDeListenersConOrder() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigEventos.class)) {
            OrdenDeListenersConOrder.limpiar();
            ctx.publishEvent("EventoDePruebaString");
            assertEquals(List.of("primero", "segundo"), OrdenDeListenersConOrder.getOrden());
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_listenerAsincronoConAsync() throws InterruptedException {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigEventos.class)) {
            ListenerAsincronoConAsync.limpiar();
            String hiloPrincipal = Thread.currentThread().getName();
            ctx.publishEvent("EventoAsync");
            
            // Damos tiempo a la ejecución asíncrona
            int intentos = 0;
            while (ListenerAsincronoConAsync.getHiloProcesamiento() == null && intentos < 20) {
                Thread.sleep(50);
                intentos++;
            }
            
            assertNotNull(ListenerAsincronoConAsync.getHiloProcesamiento());
            assertNotEquals(hiloPrincipal, ListenerAsincronoConAsync.getHiloProcesamiento());
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_transitionalEventListenerSim() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigEventos.class)) {
            TransitionalEventListenerSim.limpiar();
            ctx.publishEvent("EventoTransaccional");
            // Simplemente comprobamos que el listener se registra correctamente
            assertNotNull(ctx.getBean(TransitionalEventListenerSim.class));
        }
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_listenerDeEventosContexto() {
        ListenerDeEventosContexto.limpiar();
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigEventos.class)) {
            assertEquals(1, ListenerDeEventosContexto.getRefrescos());
            assertEquals(0, ListenerDeEventosContexto.getCierres());
        }
        assertEquals(1, ListenerDeEventosContexto.getRefrescos());
        assertEquals(1, ListenerDeEventosContexto.getCierres());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_publicadorConCallbackSincrono() {
        try (var ctx = new org.springframework.context.annotation.AnnotationConfigApplicationContext(ConfigEventos.class)) {
            var event = new EventoConCallback("Hola");
            ctx.publishEvent(event);
            assertTrue(event.getFirmas().contains("Firmante A"));
        }
    }
}
