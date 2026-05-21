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
        private final String mensaje;
        public PedidoCreadoEvent(Object source, String mensaje) {
            super(source);
            this.mensaje = mensaje;
        }
        public String getMensaje() {
            return mensaje;
        }
    }

    public static void publicarEventoCustom(org.springframework.context.ApplicationContext ctx, String mensaje) {
        // TODO extra (Reto 1): Publica un PedidoCreadoEvent en el contexto dado con el mensaje indicado.
    }

    /**
     * Reto Extra 2: Oyente de eventos personalizado que escucha PedidoCreadoEvent.
     */
    public static class OyenteDePedidos {
        private static final List<String> mensajes = new java.util.ArrayList<>();
        
        public static List<String> getMensajes() {
            return mensajes;
        }
        
        public static void limpiar() {
            mensajes.clear();
        }

        // TODO extra (Reto 2): Anota este método para escuchar PedidoCreadoEvent y guardar el mensaje en la lista 'mensajes'.
        public void procesarPedido(PedidoCreadoEvent event) {
        }
    }

    /**
     * Reto Extra 3: Oyente condicional por SpEL (monto > 100).
     */
    public static class EventoMonto {
        private final double monto;
        public EventoMonto(double monto) {
            this.monto = monto;
        }
        public double getMonto() {
            return monto;
        }
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
        // TODO extra (Reto 4): Publica todos los eventos en la lista secuencialmente usando el contexto.
    }

    /**
     * Reto Extra 5: Eventos genéricos basados en POJOs sin heredar de ApplicationEvent.
     */
    public static class EventoGenericoPojo {
        private final String info;
        public EventoGenericoPojo(String info) {
            this.info = info;
        }
        public String getInfo() {
            return info;
        }
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
        private static final List<String> orden = new java.util.ArrayList<>();
        
        public static List<String> getOrden() {
            return orden;
        }
        
        public static void limpiar() {
            orden.clear();
        }

        // TODO extra (Reto 6.1): Anota con @org.springframework.context.event.EventListener y @org.springframework.core.annotation.Order(1).
        public static class PrimerOyente {
            public void alRecibir(String event) {
                orden.add("primero");
            }
        }

        // TODO extra (Reto 6.2): Anota con @org.springframework.context.event.EventListener y @org.springframework.core.annotation.Order(2).
        public static class SegundoOyente {
            public void alRecibir(String event) {
                orden.add("segundo");
            }
        }
    }

    /**
     * Reto Extra 7: Oyentes asíncronos con @Async.
     */
    public static class ListenerAsincronoConAsync {
        private static String hiloProcesamiento;

        public static String getHiloProcesamiento() {
            return hiloProcesamiento;
        }

        public static void limpiar() {
            hiloProcesamiento = null;
        }

        // TODO extra (Reto 7): Anota con @org.springframework.context.event.EventListener y @org.springframework.scheduling.annotation.Async para ejecución asíncrona.
        public void alRecibirAsync(String event) {
        }
    }

    /**
     * Reto Extra 8: Oyentes atados a fases transaccionales.
     */
    public static class TransitionalEventListenerSim {
        private static boolean procesado;

        public static boolean isProcesado() {
            return procesado;
        }

        public static void limpiar() {
            procesado = false;
        }

        // TODO extra (Reto 8): Anota con @org.springframework.transaction.event.TransactionalEventListener para simular eventos transaccionales.
        public void alRecibirTransaccional(String event) {
        }
    }

    /**
     * Reto Extra 9: Escuchar eventos del ciclo de vida del contexto nativo de Spring.
     */
    public static class ListenerDeEventosContexto {
        private static int refrescos = 0;
        private static int cierres = 0;

        public static int getRefrescos() {
            return refrescos;
        }

        public static int getCierres() {
            return cierres;
        }

        public static void limpiar() {
            refrescos = 0;
            cierres = 0;
        }

        // TODO extra (Reto 9.1): Escucha ContextRefreshedEvent.
        public void alRefrescarContexto(org.springframework.context.event.ContextRefreshedEvent event) {
        }

        // TODO extra (Reto 9.2): Escucha ContextClosedEvent.
        public void alCerrarContexto(org.springframework.context.event.ContextClosedEvent event) {
        }
    }

    /**
     * Reto Extra 10: Publicación de eventos con callback síncrono para verificar confirmaciones.
     */
    public static class EventoConCallback {
        private final String mensaje;
        private final List<String> firmas = new java.util.ArrayList<>();

        public EventoConCallback(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void firmar(String nombreListener) {
            firmas.add(nombreListener);
        }

        public List<String> getFirmas() {
            return firmas;
        }
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
