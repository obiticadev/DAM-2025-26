package com.masterclass.api.b03_core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Ejercicio 037 · Eventos de aplicación (publisher/listener).
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.3).
 *
 * <p>Mini bus de eventos: registra listeners y publica eventos a todos.
 */
public class Ej037ApplicationEvents {

    private final List<Consumer<Object>> listeners = new ArrayList<>();

    /**
     * Suscribe un listener que recibirá todos los eventos publicados.
     *
     * @param listener acción a ejecutar por evento
     */
    public void subscribe(Consumer<Object> listener) {
        // TODO 1: valida que 'listener' no sea null.
        // TODO 2: añádelo a la lista interna 'listeners'.
        // TODO 3: el orden de suscripción debe preservarse (List lo garantiza).
    }

    /**
     * Publica un evento: lo entrega a cada listener en orden de suscripción.
     *
     * @param evento objeto evento
     */
    public void publish(Object evento) {
        // TODO 4: recorre los listeners en orden de suscripción.
        // TODO 5: a cada listener pásale el 'evento' con accept(evento).
        // TODO 6: todos los listeners deben recibir el MISMO evento.
        // TODO 7: si un listener lanza, decide la política (aquí: propaga, simple).
        // TODO 8: publicar sin listeners no debe fallar (lista vacía -> no-op).
        // TODO 9: no modifiques la lista de listeners durante la iteración.
        // TODO 10: tras publicar, los listeners siguen suscritos (no se consumen).
    }

    public static void main(String[] args) {
        var bus = new Ej037ApplicationEvents();
        bus.subscribe(e -> System.out.println("listener: " + e));
        bus.publish("PedidoCreado");
    }

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Evento de aplicación personalizado.
     */
    public static class PedidoCreadoEvent extends org.springframework.context.ApplicationEvent {
        // GUÍA: teoría 3.9 (un evento clásico hereda de ApplicationEvent, que EXIGE pasar el
        // 'source' al super). Ese super(source) es justo lo que hacía falta para compilar.
        private final String mensaje;

        public PedidoCreadoEvent(Object source, String mensaje) {
            super(source);            // obligatorio: el origen del evento
            this.mensaje = mensaje;
        }

        public String getMensaje() { return mensaje; }
    }

    public static void publicarEventoCustom(org.springframework.context.ApplicationContext ctx, String mensaje) {
        // GUÍA: teoría 3.9 (publicar un evento en el contexto).
        // Una línea — ctx.publishEvent(new PedidoCreadoEvent(ctx, mensaje));
        // El 'source' puede ser el propio contexto o cualquier objeto que origine el evento.
    }

    /**
     * Reto Extra 2: Oyente de eventos personalizado que escucha PedidoCreadoEvent.
     */
    public static class OyenteDePedidos {
        // GUÍA: teoría 3.9 (un oyente reacciona a un evento marcando un método con @EventListener).
        // Modelo de lo que añadirías:
        //   @org.springframework.context.event.EventListener
        //   public void onPedido(PedidoCreadoEvent e) { /* reaccionar a e.getMensaje() */ }
        // OJO: este reto no tiene test activo (la config de eventos está comentada en el test);
        //   basta con que compile y entiendas el patrón publisher/listener.
    }

    /**
     * Reto Extra 3: Oyente condicional por SpEL (monto > 100).
     */
    public static class EventoMonto {
        // GUÍA: teoría 3.9 (un evento POJO con un dato que una condición SpEL puede filtrar).
        private final double monto;
        public EventoMonto(double monto) { this.monto = monto; }
        public double getMonto() { return monto; }
        // En el oyente (OyenteMontoFiltrado.procesarMonto, abajo) usarías:
        //   @EventListener(condition = "#root.event.monto > 100")
        // para procesar SOLO los eventos cuyo monto supere 100.
    }

    public static class OyenteMontoFiltrado {
        private static final List<Double> montos = new java.util.ArrayList<>();
        
        public static List<Double> getMontos() {
            return montos;
        }
        
        public static void limpiar() {
            montos.clear();
        }

        // TODO extra (Reto 3): Anota con @org.springframework.context.event.EventListener usando una condición SpEL para procesar solo si el monto es mayor que 100.
        public void procesarMonto(EventoMonto event) {
        }
    }

    /**
     * Reto Extra 4: Publicar eventos múltiples.
     */
    public static void publicarEventosMultiples(org.springframework.context.ApplicationContext ctx, List<Object> eventos) {
        // GUÍA: teoría 3.9 (publicar una lista de eventos, uno a uno, en orden).
        // 1. eventos.forEach(ctx::publishEvent);
        // OJO: cada evento se entrega a sus oyentes según su tipo (Spring enruta por la firma del
        //   @EventListener). Si eventos es null, decide tu política (aquí basta con recorrerlo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para publicarEventosMultiples");
    }

    /**
     * Reto Extra 5: Eventos genéricos basados en POJOs sin heredar de ApplicationEvent.
     */
    public static class EventoGenericoPojo {
        // GUÍA: teoría 3.9 (desde Spring 4.2 un evento puede ser un POJO normal, SIN heredar de
        // ApplicationEvent). Llevamos un dato 'info' que el oyente leerá.
        private final String info;
        public EventoGenericoPojo(String info) { this.info = info; }
        public String getInfo() { return info; }
    }

    public static class OyenteGenerico {
        private static final List<String> infos = new java.util.ArrayList<>();
        
        public static List<String> getInfos() {
            return infos;
        }
        
        public static void limpiar() {
            infos.clear();
        }

        // TODO extra (Reto 5): Anota para recibir EventoGenericoPojo y guardar su info en la lista.
        public void escucharGenerico(EventoGenericoPojo event) {
        }
    }

    /**
     * Reto Extra 6: Orden de ejecución con @Order.
     */
    public static class OrdenDeListenersConOrder {
        // GUÍA: teoría 3.9 (varios oyentes del MISMO evento se ordenan con @Order(n); menor n =
        // antes). Modelo:
        //   @EventListener @org.springframework.core.annotation.Order(1)
        //   public void primero(PedidoCreadoEvent e) { ... }
        //   @EventListener @org.springframework.core.annotation.Order(2)
        //   public void segundo(PedidoCreadoEvent e) { ... }
    }

    /**
     * Reto Extra 7: Oyentes asíncronos con @Async.
     */
    public static class ListenerAsincronoConAsync {
        // GUÍA: teoría 3.9 + 1.11 (un oyente @Async corre en OTRO hilo, sin bloquear al
        // publicador; requiere @EnableAsync en la configuración). Modelo:
        //   @EventListener @org.springframework.scheduling.annotation.Async
        //   public void enSegundoPlano(PedidoCreadoEvent e) { ... }
    }

    /**
     * Reto Extra 8: Oyentes atados a fases transaccionales.
     */
    public static class TransitionalEventListenerSim {
        // GUÍA: teoría 3.9 (un @TransactionalEventListener ata la reacción a una FASE de la
        // transacción, p.ej. solo tras el commit). Lo retomarás con datos (b11+). Modelo:
        //   @org.springframework.transaction.event.TransactionalEventListener(
        //        phase = TransactionPhase.AFTER_COMMIT)
        //   public void trasCommit(PedidoCreadoEvent e) { ... }
    }

    /**
     * Reto Extra 9: Escuchar eventos del ciclo de vida del contexto nativo de Spring.
     */
    public static class ListenerDeEventosContexto {
        // GUÍA: teoría 3.9 (Spring publica eventos propios del ciclo de vida del contexto que
        // también puedes escuchar). Modelo:
        //   @EventListener
        //   public void onArranque(org.springframework.context.event.ContextRefreshedEvent e) { ... }
        //   @EventListener
        //   public void onCierre(org.springframework.context.event.ContextClosedEvent e) { ... }
    }

    /**
     * Reto Extra 10: Publicación de eventos con callback síncrono para verificar confirmaciones.
     */
    public static class EventoConCallback {
        // GUÍA: teoría 3.9 (un evento que lleva un "buzón" mutable donde los oyentes dejan su
        // confirmación/firma síncrona). El oyente (OyenteConFirma.procesarYFirmar, abajo) añadiría
        // su firma a este evento.
        private final java.util.List<String> firmas = new java.util.ArrayList<>();
        public void firmar(String quien) { firmas.add(quien); }
        public java.util.List<String> getFirmas() { return firmas; }
    }

    public static class OyenteConFirma {
        private final String nombre;
        public OyenteConFirma(String nombre) {
            this.nombre = nombre;
        }

        // TODO extra (Reto 10): Anota con @org.springframework.context.event.EventListener para recibir EventoConCallback y firmar el evento.
        public void procesarYFirmar(EventoConCallback event) {
        }
    }

}
