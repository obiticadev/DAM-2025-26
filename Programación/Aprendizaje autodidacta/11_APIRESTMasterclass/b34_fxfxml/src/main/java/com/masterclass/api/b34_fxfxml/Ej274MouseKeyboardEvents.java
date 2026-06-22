package com.masterclass.api.b34_fxfxml;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Ejercicio 274 · Eventos de ratón y teclado, <i>filtros</i> vs <i>handlers</i> y propagación.
 *
 * <p>Teoría: {@code teoria/34_JavaFX_FXML_MVC.md} (sección 2.3).
 *
 * <p>Un evento JavaFX viaja por el árbol en tres fases: <b>captura</b> (de la raíz hacia el nodo
 * objetivo, donde actúan los <i>filtros</i>), <b>objetivo</b> (el nodo donde ocurrió) y <b>burbuja</b>
 * (del objetivo hacia la raíz, donde actúan los <i>handlers</i>). Un filtro puede {@code consume()}
 * el evento y cortar su viaje. Aquí lo demostramos con un {@link EventType} propio (para no depender
 * de coordenadas reales) y manejamos {@link MouseEvent}/{@link KeyEvent} de verdad en los retos.
 *
 * <p>Clase {@code final} con métodos {@code static}.
 */
public final class Ej274MouseKeyboardEvents {

    /** Tipo de evento de prueba (sintético): nos deja estudiar la propagación sin ratón real. */
    public static final EventType<Event> EVENTO_PRUEBA = new EventType<>(Event.ANY, "PRUEBA_B34");

    private Ej274MouseKeyboardEvents() {
    }

    /**
     * Registra un filtro en el padre y handlers en padre e hijo, dispara el evento y devuelve el
     * orden en que se ejecutaron: así se ve la fase de captura (filtro) frente a la de burbuja (handler).
     *
     * @param padre          contenedor (raíz de la mini-jerarquía)
     * @param hijo           nodo dentro del padre
     * @param dispararEnHijo si true se dispara sobre el hijo; si false, sobre el padre
     * @return lista con las etiquetas en orden de ejecución, o {@code null} sin implementar
     */
    public static List<String> ordenDePropagacion(VBox padre, Button hijo, boolean dispararEnHijo) {
        // TODO 1: si padre o hijo son null, devuelve null.
        // TODO 2: crea List<String> orden = new ArrayList<>() para apuntar el orden de ejecución.
        // TODO 3: registra en el PADRE un FILTRO: padre.addEventFilter(EVENTO_PRUEBA, e -> orden.add("filtro-padre")).
        // TODO 4: registra HANDLERS: hijo.addEventHandler(EVENTO_PRUEBA, e -> orden.add("handler-hijo"))
        //         y padre.addEventHandler(EVENTO_PRUEBA, e -> orden.add("handler-padre")).
        // TODO 5: dispara sobre el objetivo elegido (hijo o padre según el flag) con
        //         objetivo.fireEvent(new Event(EVENTO_PRUEBA)) y devuelve 'orden'.
        return null;
    }

    /**
     * Describe un evento de ratón como "BOTON:clics" (p.ej. "PRIMARY:2").
     *
     * @param evento evento de ratón
     * @return descripción, o {@code ""} si el evento es null / sin implementar
     */
    public static String descripcionRaton(MouseEvent evento) {
        // TODO 6: si evento es null, devuelve "".
        // TODO 7: obtén el botón con evento.getButton() (un MouseButton).
        // TODO 8: obtén el número de clics con evento.getClickCount().
        // TODO 9: construye el texto "BOTON:clics" (el nombre del botón es boton.name() o su toString).
        // TODO 10: devuelve ese texto.
        return "";
    }

    public static void main(String[] args) {
        VBox padre = new VBox();
        Button hijo = new Button("hijo");
        padre.getChildren().add(hijo);
        System.out.println("Orden (en hijo): " + ordenDePropagacion(padre, hijo, true));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Es clic con el botón primario (izquierdo)?
     */
    public static boolean esClicPrimario(MouseEvent evento) {
        // GUÍA: teoría 2.3 (MouseButton distingue PRIMARY, SECONDARY, MIDDLE).
        // 1. return evento.getButton() == MouseButton.PRIMARY;
        // OJO: el test pasa un clic PRIMARY (true) y uno SECONDARY (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esClicPrimario");
    }

    /**
     * Reto Extra 2: ¿Es doble clic?
     */
    public static boolean esDobleClic(MouseEvent evento) {
        // GUÍA: teoría 2.3 (getClickCount() acumula clics rápidos seguidos).
        // 1. return evento.getClickCount() >= 2;
        // OJO: el test pasa clickCount=2 (true) y clickCount=1 (false). Usa >= 2 por si llega a 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDobleClic");
    }

    /**
     * Reto Extra 3: Nombre de la tecla pulsada.
     * Devuelve el nombre legible de la tecla del evento (p.ej. "Enter").
     */
    public static String teclaPulsada(KeyEvent evento) {
        // GUÍA: teoría 2.5 (KeyEvent.getCode() devuelve un KeyCode con nombre legible).
        // 1. return evento.getCode().getName();
        // OJO: el test construye un KeyEvent con KeyCode.ENTER y espera "Enter".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para teclaPulsada");
    }

    /**
     * Reto Extra 4: ¿Se pulsó con Ctrl?
     */
    public static boolean conControl(MouseEvent evento) {
        // GUÍA: teoría 2.3 (los modificadores -Ctrl, Shift, Alt- se consultan con isXxxDown()).
        // 1. return evento.isControlDown();
        // OJO: el test crea un clic con Ctrl pulsado (true) y otro sin (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conControl");
    }

    /**
     * Reto Extra 5: Un filtro que consume corta antes de llegar al hijo.
     * Pon un filtro consumidor en el padre y un handler en el hijo; di si el hijo llegó a ejecutarse.
     */
    public static boolean llegaAlHijoTrasConsumir(VBox padre, Button hijo) {
        // GUÍA: teoría 2.4 (consume() en la fase de captura impide que el evento alcance al objetivo).
        // 1. boolean[] corrio = {false}.
        // 2. padre.addEventFilter(EVENTO_PRUEBA, e -> e.consume()).
        // 3. hijo.addEventHandler(EVENTO_PRUEBA, e -> corrio[0] = true).
        // 4. hijo.fireEvent(new Event(EVENTO_PRUEBA)). 5. return corrio[0].
        // OJO: el test espera FALSE: el filtro del padre consumió el evento durante la captura, así
        //   que el handler del hijo nunca corrió. Es la base de "interceptar y cancelar".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para llegaAlHijoTrasConsumir");
    }

    /**
     * Reto Extra 6: En un mismo nodo, el filtro va antes que el handler.
     * Registra filtro y handler en el mismo botón, dispara una vez y devuelve el orden de ejecución.
     */
    public static List<String> ordenFiltroHandlerMismoNodo(Button boton) {
        // GUÍA: teoría 2.3 (aunque sea el mismo nodo, el filtro pertenece a la fase de captura -> primero).
        // 1. List<String> orden = new ArrayList<>().
        // 2. boton.addEventFilter(EVENTO_PRUEBA, e -> orden.add("filtro")).
        // 3. boton.addEventHandler(EVENTO_PRUEBA, e -> orden.add("handler")).
        // 4. boton.fireEvent(new Event(EVENTO_PRUEBA)). 5. return orden.
        // OJO: el test espera ["filtro","handler"] en ese orden exacto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenFiltroHandlerMismoNodo");
    }

    /**
     * Reto Extra 7: ¿Es el atajo Ctrl+S?
     */
    public static boolean esAtajoGuardar(KeyEvent evento) {
        // GUÍA: teoría 2.5 (un atajo = una tecla + modificadores; aquí Ctrl + S).
        // 1. return evento.getCode() == KeyCode.S && evento.isControlDown();
        // OJO: el test pasa S con Ctrl (true), S sin Ctrl (false) y A con Ctrl (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAtajoGuardar");
    }

    /**
     * Reto Extra 8: Posición del ratón como "x,y".
     * Devuelve las coordenadas del evento dentro del nodo, en enteros, separadas por coma.
     */
    public static String posicionRaton(MouseEvent evento) {
        // GUÍA: teoría 2.3 (getX/getY son coordenadas relativas al nodo; getSceneX/Y, a la escena).
        // 1. return ((int) evento.getX()) + "," + ((int) evento.getY());
        // OJO: el test crea un evento en (10, 20) y espera "10,20". Castea a int para no arrastrar ".0".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para posicionRaton");
    }

    /**
     * Reto Extra 9: Consumir en el objetivo frena la burbuja.
     * El hijo registra su paso y consume; el padre intenta registrarse en la burbuja. Devuelve el orden.
     */
    public static List<String> burbujaFrenadaEnObjetivo(VBox padre, Button hijo) {
        // GUÍA: teoría 2.4 (consume() en el objetivo evita que los ancestros reciban el evento al subir).
        // 1. List<String> orden = new ArrayList<>().
        // 2. hijo.addEventHandler(EVENTO_PRUEBA, e -> { orden.add("handler-hijo"); e.consume(); }).
        // 3. padre.addEventHandler(EVENTO_PRUEBA, e -> orden.add("handler-padre")).
        // 4. hijo.fireEvent(new Event(EVENTO_PRUEBA)). 5. return orden.
        // OJO: el test espera SOLO ["handler-hijo"]: al consumir en el objetivo, la fase de burbuja
        //   no llega al padre. Compáralo con ordenDePropagacion (sin consumir, sí llegaba al padre).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para burbujaFrenadaEnObjetivo");
    }

    /**
     * Reto Extra 10: Mini-router de teclado (accesibilidad).
     * Traduce una tecla en una acción lógica: ENTER -> "aceptar", ESCAPE -> "cancelar", resto -> "".
     */
    public static String accionPorTecla(KeyEvent evento) {
        // GUÍA: teoría 2.5 (mapear teclas a acciones lógicas es la base de los atajos y la accesibilidad).
        // 1. KeyCode c = evento.getCode().
        // 2. if (c == KeyCode.ENTER) return "aceptar"; if (c == KeyCode.ESCAPE) return "cancelar"; return "".
        // OJO: el test prueba ENTER -> "aceptar", ESCAPE -> "cancelar" y una tecla cualquiera (A) -> "".
        // CULTURA: ENTER confirma y ESC cancela en CUALQUIER diálogo (lo verás en el ej. 278 y, como
        //   requisito de accesibilidad, en el bloque b36): el teclado debe poder hacer todo lo del ratón.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para accionPorTecla");
    }

    /** Fábrica de eventos de ratón para los tests (lectura de propiedades, sin despacho real). */
    static MouseEvent clic(MouseButton boton, int clics, double x, double y, boolean ctrl) {
        return new MouseEvent(MouseEvent.MOUSE_CLICKED, x, y, x, y, boton, clics,
                false, ctrl, false, false,
                boton == MouseButton.PRIMARY, false, boton == MouseButton.SECONDARY,
                false, false, false, null);
    }

    /** Fábrica de eventos de teclado para los tests. */
    static KeyEvent tecla(KeyCode codigo, boolean ctrl) {
        return new KeyEvent(KeyEvent.KEY_PRESSED, "", "", codigo, false, ctrl, false, false);
    }
}
