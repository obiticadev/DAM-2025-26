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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: valida que 'listener' no sea null.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: añádelo a la lista interna 'listeners'.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: el orden de suscripción debe preservarse (List lo garantiza).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: recorre los listeners en orden de suscripción.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: a cada listener pásale el 'evento' con accept(evento).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: todos los listeners deben recibir el MISMO evento.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si un listener lanza, decide la política (aquí: propaga, simple).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: publicar sin listeners no debe fallar (lista vacía -> no-op).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: no modifiques la lista de listeners durante la iteración.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: tras publicar, los listeners siguen suscritos (no se consumen).
    }

}
