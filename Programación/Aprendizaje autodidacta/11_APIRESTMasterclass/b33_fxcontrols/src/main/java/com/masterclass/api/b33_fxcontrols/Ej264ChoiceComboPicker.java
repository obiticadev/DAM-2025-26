package com.masterclass.api.b33_fxcontrols;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Ejercicio 264 · Controles de selección: ChoiceBox, ComboBox, DatePicker, Spinner, Slider.
 *
 * <p>Teoría: {@code teoria/33_JavaFX_Controles_Binding.md} (sección 1.2).
 *
 * <p>Excepción al molde (addendum §1.6): extiende {@code Application} para el Playground; la lógica
 * testeable vive en métodos {@code static} puros que construyen el control y leen su valor.
 */
public class Ej264ChoiceComboPicker extends Application {

    /**
     * Construye un {@link ComboBox} de cadenas con los items dados y deja seleccionado uno.
     *
     * @param items     opciones del desplegable
     * @param seleccion valor a dejar elegido (debe estar en items)
     * @return el ComboBox configurado; {@code null} sin implementar
     */
    public static ComboBox<String> construirCombo(List<String> items, String seleccion) {
        // TODO 1: crea un ComboBox<String> vacío.
        // TODO 2: añade todos los items: combo.getItems().addAll(items).
        // TODO 3: deja seleccionado 'seleccion' con setValue(...).
        // TODO 4: (opcional pedagógico) NO lo hagas editable: el valor solo puede salir de items.
        // TODO 5: devuelve el combo.
        return null;
    }

    /**
     * Crea un {@link Spinner} de enteros acotado y devuelve su valor inicial.
     *
     * @param min     valor mínimo permitido
     * @param max     valor máximo permitido
     * @param inicial valor de arranque (entre min y max)
     * @return el valor actual del spinner; {@code -1} sin implementar
     */
    public static int valorSpinner(int min, int max, int inicial) {
        // TODO 6: crea new Spinner<Integer>(min, max, inicial).
        // TODO 7: recuerda que el Spinner usa por dentro un SpinnerValueFactory que acota el rango.
        // TODO 8: lee su valor con getValue() (es un Integer).
        // TODO 9: el autoboxing convierte Integer -> int al devolverlo.
        // TODO 10: devuelve ese valor.
        return -1;
    }

    /** Playground visual: muestra varios controles de selección. El alumno pulsa Run. */
    @Override
    public void start(Stage stage) {
        ComboBox<String> combo = construirCombo(List.of("Rojo", "Verde", "Azul"), "Verde");
        VBox raiz = new VBox(10,
                new Label("Color:"),
                combo != null ? combo : new Label("Implementa construirCombo()"),
                new Slider(0, 100, 50),
                new Spinner<>(0, 10, 3));
        stage.setTitle("Ej264 · Selección");
        stage.setScene(new Scene(raiz, 320, 220));
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Spinner inicial: " + valorSpinner(0, 10, 5));
        launch(args);
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Crear un slider.
     * Devuelve un {@link Slider} con el mínimo, máximo y valor inicial indicados.
     */
    public static Slider crearSlider(double min, double max, double valor) {
        // GUÍA: teoría 1.2 (Slider = entrada continua acotada entre min y max).
        // 1. Devuelve new Slider(min, max, valor).
        // OJO: el test comprueba getValue(); el constructor recibe (min, max, value), ese orden.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearSlider");
    }

    /**
     * Reto Extra 2: Porcentaje recorrido de un slider.
     * Devuelve qué porcentaje (0–100) representa el valor actual dentro de su rango.
     */
    public static double porcentajeSlider(Slider slider) {
        // GUÍA: teoría 1.2 (un slider tiene getMin/getMax/getValue).
        // 1. recorrido = (getValue - getMin) / (getMax - getMin). 2. Multiplica por 100.
        // PISTA: (slider.getValue() - slider.getMin()) / (slider.getMax() - slider.getMin()) * 100;
        // OJO: el test usa min=0,max=200,value=50 -> 25.0. Cuidado con dividir enteros: aquí es double.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para porcentajeSlider");
    }

    /**
     * Reto Extra 3: Seleccionar el primer item.
     * Selecciona el primer elemento del {@link ComboBox} y devuelve su valor.
     */
    public static String seleccionarPrimero(ComboBox<String> combo) {
        // GUÍA: teoría 1.2 (getSelectionModel gestiona la selección por índice).
        // 1. combo.getSelectionModel().selectFirst(). 2. Devuelve combo.getValue().
        // PISTA: selectFirst() equivale a select(0).
        // OJO: el value y el selectionModel quedan sincronizados; tras selectFirst getValue() es el item 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para seleccionarPrimero");
    }

    /**
     * Reto Extra 4: Llenar un ChoiceBox.
     * Mete los items en un {@link ChoiceBox} y devuelve cuántos quedaron.
     */
    public static int itemsChoiceBox(List<String> items) {
        // GUÍA: teoría 1.2 (ChoiceBox = desplegable simple, sin editar; ComboBox es su versión rica).
        // 1. Crea un ChoiceBox<String>. 2. getItems().addAll(items). 3. Devuelve getItems().size().
        // OJO: lista vacía -> 0. ChoiceBox sirve para POCAS opciones; para muchas, ComboBox.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para itemsChoiceBox");
    }

    /**
     * Reto Extra 5: Valor del combo o uno por defecto.
     * Devuelve el valor seleccionado del combo, o {@code porDefecto} si no hay ninguno.
     */
    public static String valorODefault(ComboBox<String> combo, String porDefecto) {
        // GUÍA: teoría 1.2 (un combo sin selección tiene getValue() == null).
        // 1. Si combo.getValue() es null, devuelve porDefecto; si no, el value.
        // PISTA: combo.getValue() != null ? combo.getValue() : porDefecto; (o requireNonNullElse).
        // OJO: el test crea un combo SIN seleccionar nada -> debe caer en porDefecto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorODefault");
    }

    /**
     * Reto Extra 6: Incrementar un spinner.
     * Sube el {@link Spinner} 'pasos' posiciones y devuelve su nuevo valor.
     */
    public static int incrementarSpinner(Spinner<Integer> spinner, int pasos) {
        // GUÍA: teoría 1.2 (Spinner.increment(n) avanza n pasos respetando el máximo del factory).
        // 1. spinner.increment(pasos). 2. Devuelve spinner.getValue().
        // OJO: si te pasas del máximo, el SpinnerValueFactory te deja en el tope (no lanza error).
        //   El test arranca en 3 sobre rango 0..10 e incrementa 2 -> 5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarSpinner");
    }

    /**
     * Reto Extra 7: Fecha elegida en un DatePicker.
     * Pone la fecha en el {@link DatePicker} y devuelve la fecha que guarda.
     */
    public static LocalDate fijarFecha(DatePicker picker, LocalDate fecha) {
        // GUÍA: teoría 1.2 (DatePicker trabaja con LocalDate, no con String).
        // 1. picker.setValue(fecha). 2. Devuelve picker.getValue().
        // OJO: el value es un LocalDate de java.time (b26/b30 lo usan); el texto visible se
        //   formatea con un StringConverter (eso lo trabajas en Ej269).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fijarFecha");
    }

    /**
     * Reto Extra 8: Días entre dos DatePicker.
     * Devuelve los días desde la fecha de 'desde' hasta la de 'hasta'.
     */
    public static long diasEntre(DatePicker desde, DatePicker hasta) {
        // GUÍA: teoría 1.2 + java.time. ChronoUnit.DAYS.between(a, b).
        // 1. Lee ambos getValue(). 2. return ChronoUnit.DAYS.between(desde.getValue(), hasta.getValue()).
        // PISTA: between(a, b) es positivo si b es posterior a a.
        // OJO: el test pone 2024-01-01 y 2024-01-08 -> 7. El orden de argumentos importa (signo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para diasEntre");
    }

    /**
     * Reto Extra 9: Texto libre de un ComboBox editable.
     * Hace el combo editable, escribe el texto en su editor y devuelve lo escrito.
     */
    public static String comboEditableTexto(ComboBox<String> combo, String texto) {
        // GUÍA: teoría 1.2 (un ComboBox editable permite teclear un valor que NO está en items).
        // 1. combo.setEditable(true). 2. combo.getEditor().setText(texto). 3. Devuelve getEditor().getText().
        // PISTA: getEditor() devuelve el TextField interno; solo existe si es editable.
        // OJO: en un combo NO editable getEditor() es null -> NPE; por eso pones editable=true antes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comboEditableTexto");
    }

    /**
     * Reto Extra 10: Configurar marcas y ajuste de un slider.
     * Activa marcas mayores cada 'unidad' y el "snap" a marcas; devuelve si quedó activado el snap.
     */
    public static boolean snapSliderTicks(Slider slider, double unidad) {
        // GUÍA: teoría 1.2 (showTickMarks/majorTickUnit/snapToTicks dan un slider "con pasos").
        // 1. slider.setMajorTickUnit(unidad). 2. slider.setShowTickMarks(true).
        // 3. slider.setSnapToTicks(true). 4. Devuelve slider.isSnapToTicks().
        // OJO: snapToTicks hace que el value salte a la marca más cercana al soltar; sin él, es continuo.
        // CULTURA: Spinner y Slider son "entradas acotadas" como <input type="number"> y
        //   <input type="range"> de HTML; el snap es el atributo step. La acotación evita
        //   validar a mano (no puedes meter un valor fuera de rango).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para snapSliderTicks");
    }
}
