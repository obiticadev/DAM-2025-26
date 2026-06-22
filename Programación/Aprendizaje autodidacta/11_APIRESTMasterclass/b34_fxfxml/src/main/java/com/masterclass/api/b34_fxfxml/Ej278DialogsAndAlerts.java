package com.masterclass.api.b34_fxfxml;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Ejercicio 278 · Diálogos: {@link Alert}, {@code Dialog}, {@code ChoiceDialog} y su resultado.
 *
 * <p>Teoría: {@code teoria/34_JavaFX_FXML_MVC.md} (sección 4.3).
 *
 * <p>Un {@code Alert} pregunta y devuelve la decisión vía {@code showAndWait()} (un
 * {@code Optional<ButtonType>}). El problema: {@code showAndWait} ABRE una ventana y bloquea, así
 * que no es testeable. La solución profesional es <b>abstraer la pregunta tras una interfaz</b>
 * ({@link Confirmador}, {@link Selector}): en producción la implementa un {@code Alert} real; en el
 * test, una lambda que devuelve "sí/no" sin abrir nada. Así la LÓGICA de decisión queda testeable.
 *
 * <p>Clase {@code final} con métodos {@code static}.
 */
public final class Ej278DialogsAndAlerts {

    /** Abstrae una confirmación Sí/No (en producción, un Alert de confirmación). */
    @FunctionalInterface
    public interface Confirmador {
        boolean confirmar(String mensaje);
    }

    /** Abstrae la elección de una opción (en producción, un ChoiceDialog). */
    @FunctionalInterface
    public interface Selector {
        Optional<String> elegir(List<String> opciones);
    }

    private Ej278DialogsAndAlerts() {
    }

    /**
     * Elimina un elemento de la lista SOLO si el usuario confirma (vía la abstracción inyectada).
     *
     * @param confirmador la pregunta de confirmación (real o de test)
     * @param lista       lista mutable de la que eliminar
     * @param elemento    elemento a eliminar
     * @return true si se eliminó; {@code false} si no se confirmó, no estaba, o sin implementar
     */
    public static boolean eliminarSiConfirma(Confirmador confirmador, List<String> lista, String elemento) {
        // TODO 1: si confirmador es null o lista es null, devuelve false.
        // TODO 2: pregunta: boolean ok = confirmador.confirmar("¿Eliminar " + elemento + "?").
        // TODO 3: si NO confirma (!ok), devuelve false (no tocamos la lista).
        // TODO 4: intenta quitarlo: boolean quitado = lista.remove(elemento).
        // TODO 5: devuelve 'quitado' (false si el elemento no estaba en la lista).
        return false;
    }

    /**
     * Traduce el botón pulsado en un diálogo a una acción lógica.
     *
     * @param pulsado el {@link ButtonType} que el usuario pulsó
     * @return "aceptar" / "cancelar" / "" según el botón; {@code ""} sin implementar
     */
    public static String resultadoBotones(ButtonType pulsado) {
        // TODO 6: si pulsado es null, devuelve "".
        // TODO 7: si pulsado == ButtonType.OK, devuelve "aceptar".
        // TODO 8: si pulsado == ButtonType.CANCEL, devuelve "cancelar".
        // TODO 9: para cualquier otro botón, el resultado es "".
        // TODO 10: devuelve el resultado calculado.
        return "";
    }

    public static void main(String[] args) {
        // Playground "de consola": inyectamos un Confirmador que siempre dice que sí.
        java.util.List<String> datos = new java.util.ArrayList<>(List.of("a", "b"));
        Confirmador siempreSi = mensaje -> true;
        System.out.println("Eliminado 'a': " + eliminarSiConfirma(siempreSi, datos, "a") + " -> " + datos);
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Interpretar una confirmación booleana.
     * Devuelve "Sí" si confirmó, "No" si no.
     */
    public static String interpretar(boolean confirmado) {
        // GUÍA: teoría 4.3 (la decisión del diálogo se reduce a un sí/no que tu lógica interpreta).
        // 1. return confirmado ? "Sí" : "No";
        // OJO: el test prueba true -> "Sí" y false -> "No" (con tilde y mayúscula).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para interpretar");
    }

    /**
     * Reto Extra 2: ¿Es el botón Aceptar?
     */
    public static boolean esBotonOk(ButtonType boton) {
        // GUÍA: teoría 4.3 (los ButtonType estándar -OK, CANCEL, YES, NO- se comparan por identidad).
        // 1. return boton == ButtonType.OK;
        // OJO: el test pasa ButtonType.OK (true) y ButtonType.CANCEL (false). Compara con ==, no equals.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBotonOk");
    }

    /**
     * Reto Extra 3: Texto de un botón.
     * Devuelve la etiqueta del ButtonType.
     */
    public static String textoDe(ButtonType boton) {
        // GUÍA: teoría 4.3 (puedes crear botones a medida: new ButtonType("Guardar")).
        // 1. return boton.getText();
        // OJO: el test usa new ButtonType("Guardar") y espera "Guardar" (no usamos los estándar para
        //   evitar depender del idioma del sistema).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoDe");
    }

    /**
     * Reto Extra 4: Opción por defecto de una lista de elección.
     * Devuelve la primera opción (la preseleccionada en un ChoiceDialog), o "" si no hay.
     */
    public static String opcionPorDefecto(List<String> opciones) {
        // GUÍA: teoría 4.4 (un ChoiceDialog arranca con una opción marcada por defecto).
        // 1. Si opciones es null o está vacía -> "". 2. return opciones.get(0).
        // OJO: el test pasa ["rojo","verde"] -> "rojo" y una lista vacía -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para opcionPorDefecto");
    }

    /**
     * Reto Extra 5: Elegir una opción mediante la abstracción.
     * Pregunta al Selector y devuelve la opción elegida, o "" si se canceló.
     */
    public static String elegir(Selector selector, List<String> opciones) {
        // GUÍA: teoría 4.4 (igual que Confirmador, pero la respuesta es un Optional<String>).
        // 1. Optional<String> r = selector.elegir(opciones). 2. return r.orElse("").
        // OJO: el test inyecta un Selector que devuelve Optional.of("verde") (-> "verde") y otro que
        //   devuelve Optional.empty() (cancelar -> "").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para elegir");
    }

    /**
     * Reto Extra 6: Resultado a partir del Optional de showAndWait.
     * Traduce el Optional&lt;ButtonType&gt; en "aceptar" / "cancelar" / "cerrado".
     */
    public static String resultadoDeOptional(Optional<ButtonType> resultado) {
        // GUÍA: teoría 4.3 (showAndWait devuelve Optional vacío si se cierra la ventana con la X).
        // 1. Si resultado.isEmpty() -> "cerrado".
        // 2. ButtonType b = resultado.get(); return resultadoBotones(b) si no es "" ... o reutiliza:
        //    if (b == ButtonType.OK) return "aceptar"; if (b == ButtonType.CANCEL) return "cancelar"; return "cerrado".
        // OJO: el test prueba Optional.of(OK) -> "aceptar", Optional.of(CANCEL) -> "cancelar" y
        //   Optional.empty() -> "cerrado". Cerrar la ventana NO es ni aceptar ni cancelar explícito.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resultadoDeOptional");
    }

    /**
     * Reto Extra 7: Crear un Alert de confirmación con título y contenido.
     * Devuelve un Alert de tipo CONFIRMATION configurado (sin mostrarlo).
     */
    public static Alert crearConfirmacion(String titulo, String contenido) {
        // GUÍA: teoría 4.3 (un Alert es un Dialog prefabricado; se configura y luego se showAndWait).
        // 1. Alert a = new Alert(AlertType.CONFIRMATION). 2. a.setTitle(titulo); a.setContentText(contenido).
        // 3. return a (NO llames a showAndWait: bloquearía el test abriendo ventana).
        // OJO: crear un Alert necesita el toolkit (el test arranca Monocle). El test comprueba
        //   getTitle()/getAlertType() sin mostrar nada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearConfirmacion");
    }

    /**
     * Reto Extra 8: Número de botones de un Alert.
     * Devuelve cuántos ButtonType tiene el diálogo.
     */
    public static int numeroDeBotones(Alert alerta) {
        // GUÍA: teoría 4.3 (un CONFIRMATION trae 2 botones por defecto: Aceptar y Cancelar).
        // 1. return alerta.getButtonTypes().size();
        // OJO: el test crea un Alert CONFIRMATION -> espera 2. Contamos botones, no su texto (evita
        //   depender del idioma).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDeBotones");
    }

    /**
     * Reto Extra 9: Configurar los botones de un Alert.
     * Reemplaza los botones del diálogo por los dados y devuelve cuántos quedaron.
     */
    public static int configurarBotones(Alert alerta, List<ButtonType> botones) {
        // GUÍA: teoría 4.3 (puedes personalizar los botones: "Guardar / Descartar / Cancelar").
        // 1. alerta.getButtonTypes().setAll(botones). 2. return alerta.getButtonTypes().size().
        // PISTA: setAll vacía y pone los nuevos de golpe.
        // OJO: el test pasa 3 botones a medida -> espera 3 (sustituye a los 2 por defecto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para configurarBotones");
    }

    /**
     * Reto Extra 10: Vaciar una lista solo si se confirma.
     * Si el usuario confirma, vacía la lista y devuelve cuántos elementos se borraron; si no, 0.
     */
    public static int vaciarSiConfirma(Confirmador confirmador, List<String> lista) {
        // GUÍA: teoría 4.3 (operación destructiva -> SIEMPRE confirma antes; aquí, "vaciar todo").
        // 1. if (!confirmador.confirmar("¿Vaciar la lista?")) return 0.
        // 2. int borrados = lista.size(); lista.clear(); return borrados.
        // OJO: el test con un Confirmador que dice sí -> borra todos (devuelve el tamaño previo); con
        //   uno que dice no -> 0 y la lista intacta.
        // CULTURA: inyectar el Confirmador (en vez de llamar a Alert.showAndWait dentro de la lógica)
        //   es el patrón "puertos y adaptadores" de b10: la lógica depende de una INTERFAZ, no de la UI,
        //   y por eso se testea con un doble (b19). Misma idea que mockear un repositorio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vaciarSiConfirma");
    }
}
