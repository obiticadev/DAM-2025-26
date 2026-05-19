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
}
