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
        // TODO extra: Reto Extra 1: Evento de aplicación personalizado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    public static void publicarEventoCustom(org.springframework.context.ApplicationContext ctx, String mensaje) {
        // TODO extra (Reto 1): Publica un PedidoCreadoEvent en el contexto dado con el mensaje indicado.
    }

    /**
     * Reto Extra 2: Oyente de eventos personalizado que escucha PedidoCreadoEvent.
     */
    public static class OyenteDePedidos {
        // TODO extra: Reto Extra 2: Oyente de eventos personalizado que escucha PedidoCreadoEvent.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 3: Oyente condicional por SpEL (monto > 100).
     */
    public static class EventoMonto {
        // TODO extra: Reto Extra 3: Oyente condicional por SpEL (monto > 100).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
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
        // TODO extra: Reto Extra 4: Publicar eventos múltiples.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para publicarEventosMultiples");
    }

    /**
     * Reto Extra 5: Eventos genéricos basados en POJOs sin heredar de ApplicationEvent.
     */
    public static class EventoGenericoPojo {
        // TODO extra: Reto Extra 5: Eventos genéricos basados en POJOs sin heredar de ApplicationEvent.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
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
        // TODO extra: Reto Extra 6: Orden de ejecución con @Order.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 7: Oyentes asíncronos con @Async.
     */
    public static class ListenerAsincronoConAsync {
        // TODO extra: Reto Extra 7: Oyentes asíncronos con @Async.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 8: Oyentes atados a fases transaccionales.
     */
    public static class TransitionalEventListenerSim {
        // TODO extra: Reto Extra 8: Oyentes atados a fases transaccionales.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 9: Escuchar eventos del ciclo de vida del contexto nativo de Spring.
     */
    public static class ListenerDeEventosContexto {
        // TODO extra: Reto Extra 9: Escuchar eventos del ciclo de vida del contexto nativo de Spring.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 10: Publicación de eventos con callback síncrono para verificar confirmaciones.
     */
    public static class EventoConCallback {
        // TODO extra: Reto Extra 10: Publicación de eventos con callback síncrono para verificar confirmaciones.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
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
