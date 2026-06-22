package com.masterclass.api.b34_fxfxml;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Ejercicio 273 · El modelo de eventos: {@code onAction}, {@link EventHandler} y {@link ActionEvent}.
 *
 * <p>Teoría: {@code teoria/34_JavaFX_FXML_MVC.md} (sección 2.1).
 *
 * <p>En JavaFX, "reaccionar a un clic" es registrar un {@link EventHandler} en el control. La forma
 * corta es {@code boton.setOnAction(e -> ...)} (una sola acción) y la general es
 * {@code nodo.addEventHandler(tipo, manejador)} (varios manejadores por tipo). En los tests no hay
 * ratón: disparamos el evento con {@code boton.fire()} o construyendo el {@link ActionEvent} a mano,
 * lo que hace el modelo de eventos 100% testeable headless.
 *
 * <p>Clase {@code final} con métodos {@code static}.
 */
public final class Ej273EventHandlers {

    private Ej273EventHandlers() {
    }

    /**
     * Registra un manejador en el botón y lo dispara {@code veces} veces, contando los disparos.
     *
     * @param boton botón sobre el que registrar la acción
     * @param veces número de disparos a simular con {@code fire()}
     * @return cuántas veces se ejecutó el manejador; {@code -1} sin implementar
     */
    public static int contarDisparos(Button boton, int veces) {
        // TODO 1: si boton es null o veces < 0, devuelve -1 (entrada inválida).
        // TODO 2: crea un contador mutable: int[] contador = {0} (truco para mutar dentro de la lambda).
        // TODO 3: registra la acción: boton.setOnAction(e -> contador[0]++).
        // TODO 4: dispara 'veces' veces con un bucle de boton.fire().
        // TODO 5: devuelve contador[0].
        return -1;
    }

    /**
     * Lee el texto del control que originó un evento (su {@code source}).
     *
     * @param evento un {@link ActionEvent} cualquiera
     * @return el texto del botón fuente, o {@code ""} si la fuente no es un botón / sin implementar
     */
    public static String textoDeLaFuente(ActionEvent evento) {
        // TODO 6: si evento es null, devuelve "".
        // TODO 7: obtén la fuente con Object fuente = evento.getSource().
        // TODO 8: comprueba si fuente es un Button (instanceof).
        // TODO 9: si NO lo es, devuelve "".
        // TODO 10: si lo es, castea a Button y devuelve su getText().
        return "";
    }

    public static void main(String[] args) {
        Button b = new Button("Pulsa");
        System.out.println("Disparos: " + contarDisparos(b, 3));
        System.out.println("Fuente: " + textoDeLaFuente(new ActionEvent(new Button("Hola"), null)));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Fabricar un manejador que cuenta.
     * Devuelve un EventHandler que, cada vez que se invoca, incrementa el contador dado.
     */
    public static EventHandler<ActionEvent> manejadorContador(int[] contador) {
        // GUÍA: teoría 2.1 (un EventHandler<ActionEvent> es funcional: una lambda 'e -> ...').
        // 1. return e -> contador[0]++;
        // OJO: el test llama dos veces a handler.handle(null) y espera contador[0]==2 (handle ignora
        //   el evento aquí, por eso puede ser null).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para manejadorContador");
    }

    /**
     * Reto Extra 2: ¿Tiene el botón una acción registrada?
     */
    public static boolean tieneAccion(Button boton) {
        // GUÍA: teoría 2.1 (onActionProperty guarda el manejador; getOnAction() lo devuelve o null).
        // 1. return boton.getOnAction() != null;
        // OJO: el test prueba un botón recién creado (false) y uno con setOnAction (true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneAccion");
    }

    /**
     * Reto Extra 3: Disparar y recuperar la fuente del evento.
     * Registra un manejador que captura la fuente, dispara el botón y devuelve esa fuente.
     */
    public static Object fuenteAlDisparar(Button boton) {
        // GUÍA: teoría 2.1 (dentro del manejador, event.getSource() es el control que disparó).
        // 1. Object[] caja = new Object[1].
        // 2. boton.setOnAction(e -> caja[0] = e.getSource()). 3. boton.fire(). 4. return caja[0].
        // OJO: el test comprueba con assertSame que la fuente devuelta ES el mismo botón.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fuenteAlDisparar");
    }

    /**
     * Reto Extra 4: Disparar solo si el control está habilitado.
     * Dispara el botón únicamente si NO está deshabilitado; indica si llegó a dispararse.
     */
    public static boolean dispararSiHabilitado(Button boton) {
        // GUÍA: teoría 2.2 (un control deshabilitado no responde a eventos de usuario).
        // 1. Si boton.isDisabled() -> return false (no se dispara).
        // 2. boton.fire(); return true.
        // OJO: el test prueba un botón normal (true) y otro con setDisable(true) (false). Aquí imitamos
        //   a mano lo que la UI hace sola: ignorar el clic en un control deshabilitado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dispararSiHabilitado");
    }

    /**
     * Reto Extra 5: Consumir un evento.
     * Marca el evento como consumido y dice si quedó consumido.
     */
    public static boolean consumir(ActionEvent evento) {
        // GUÍA: teoría 2.3 (consumir un evento DETIENE su propagación; lo verás a fondo en el ej. 274).
        // 1. evento.consume(); 2. return evento.isConsumed();
        // OJO: el test crea un ActionEvent nuevo (no consumido) y espera true tras consumir.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para consumir");
    }

    /**
     * Reto Extra 6: Varios manejadores para el mismo evento.
     * Añade 'n' manejadores que cuentan, dispara UNA vez y devuelve el total de ejecuciones.
     */
    public static int variosManejadores(Button boton, int n) {
        // GUÍA: teoría 2.1 (addEventHandler ACUMULA manejadores; setOnAction solo guarda UNO).
        // 1. int[] total = {0}. 2. n veces: boton.addEventHandler(ActionEvent.ACTION, e -> total[0]++).
        // 3. boton.fire() UNA sola vez. 4. return total[0].
        // PISTA: con addEventHandler los 'n' manejadores se ejecutan en el MISMO disparo.
        // OJO: el test usa n=3 y un solo fire() -> espera 3 (no 1). Esa es la diferencia con setOnAction.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para variosManejadores");
    }

    /**
     * Reto Extra 7: Nombre del tipo de evento.
     * Devuelve el nombre del tipo de un evento (p.ej. "ACTION").
     */
    public static String tipoDeEvento(Event evento) {
        // GUÍA: teoría 2.3 (cada evento tiene un EventType con jerarquía: ACTION, MOUSE_CLICKED…).
        // 1. return evento.getEventType().getName();
        // OJO: el test pasa un ActionEvent (su tipo es ActionEvent.ACTION) y espera "ACTION".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tipoDeEvento");
    }

    /**
     * Reto Extra 8: Recoger en orden los textos de los botones disparados.
     * Pone a cada botón un manejador que apunta su texto en una lista, los dispara en orden y la devuelve.
     */
    public static List<String> textosEnOrden(List<Button> botones) {
        // GUÍA: teoría 2.1 (el manejador "cierra" sobre variables externas: aquí, la lista).
        // 1. List<String> orden = new ArrayList<>().
        // 2. Por cada botón b: b.setOnAction(e -> orden.add(b.getText())).
        // 3. Por cada botón b (otra vez, o en el mismo bucle): b.fire().
        // 4. return orden.
        // PISTA: 'b' debe ser efectivamente final dentro de la lambda; un for-each te lo da gratis.
        // OJO: el test pasa botones con texto "a" y "b" en ese orden y espera ["a","b"].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textosEnOrden");
    }

    /**
     * Reto Extra 9: Un manejador que lanza excepción no debe tumbar al que dispara.
     * Registra un manejador que lanza, dispara dentro de try/catch y dice si capturaste el fallo.
     */
    public static boolean capturaFalloDeManejador(Button boton) {
        // GUÍA: teoría 2.4 (una excepción dentro de un manejador se propaga a quien disparó el evento).
        // 1. boton.setOnAction(e -> { throw new RuntimeException("fallo en el handler"); }).
        // 2. try { boton.fire(); return false; } catch (RuntimeException ex) { return true; }
        // OJO: el test espera true (capturaste la excepción). En una app real, un handler debe
        //   gestionar sus propios errores; si no, el fallo "burbujea" y puede romper la interacción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para capturaFalloDeManejador");
    }

    /**
     * Reto Extra 10: Un contador completo dirigido por eventos.
     * El botón suma 1 a un modelo cada vez que se pulsa; dispáralo 'clics' veces y devuelve el valor.
     */
    public static int contadorPorClics(Button boton, int clics) {
        // GUÍA: teoría 2.1 + 4.x (el evento MODIFICA un modelo; la base de un "command" de MVVM).
        // 1. int[] modelo = {0}. 2. boton.setOnAction(e -> modelo[0]++). 3. dispara 'clics' veces.
        // 4. return modelo[0].
        // OJO: con clics=5 el test espera 5. Ojo a clics<=0: no dispares (el for no entra) -> 0.
        // CULTURA: "el evento manda un mensaje que cambia el estado" es el patrón Command. En MVVM
        //   (ej. 276) ese estado vive en el ViewModel y la vista solo lanza el comando; aquí lo ves crudo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contadorPorClics");
    }

    /** Helper de demostración: lista de botones con los textos dados. */
    static List<Button> botonesCon(String... textos) {
        List<Button> lista = new ArrayList<>();
        for (String t : textos) {
            lista.add(new Button(t));
        }
        return lista;
    }
}
