package com.masterclass.api.b33_fxcontrols;

import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Ejercicio 263 · Controles básicos: Label, Button, TextField, PasswordField, CheckBox, RadioButton.
 *
 * <p>Teoría: {@code teoria/33_JavaFX_Controles_Binding.md} (sección 1.1).
 *
 * <p>Excepción al molde del proyecto (addendum §1.6 del roadmap): esta clase <b>extiende
 * {@code Application}</b> para ser el Playground visual, por eso NO tiene constructor privado.
 * La lógica testeable sigue viviendo en métodos {@code static} puros que construyen controles y
 * leen su estado, sin abrir ventana.
 */
public class Ej263BasicControls extends Application {

    /**
     * Construye un formulario de login: un {@link VBox} con un {@link TextField} (id "usuario"),
     * un {@link PasswordField} (id "clave"), un {@link CheckBox} (id "recordar") y un
     * {@link Button} (id "entrar").
     *
     * @return la raíz con los cuatro controles identificados; {@code null} sin implementar
     */
    public static VBox construirLogin() {
        // TODO 1: crea el TextField del usuario y dale el id "usuario" (setId).
        // TODO 2: crea el PasswordField de la clave y dale el id "clave".
        // TODO 3: crea el CheckBox "Recordarme" y dale el id "recordar".
        // TODO 4: crea el Button "Entrar" y dale el id "entrar".
        // TODO 5: mételos los cuatro, en ese orden, en un VBox y devuélvelo.
        return null;
    }

    /**
     * Devuelve el texto del {@link RadioButton} seleccionado dentro de un {@link ToggleGroup}.
     *
     * @param grupo grupo de radios (exclusividad mutua)
     * @return el texto del radio marcado, o {@code ""} si no hay ninguno / sin implementar
     */
    public static String resumenSeleccion(ToggleGroup grupo) {
        // TODO 6: si grupo es null, devuelve "" (caso límite).
        // TODO 7: obtén el toggle seleccionado con grupo.getSelectedToggle().
        // TODO 8: si no hay ninguno seleccionado (null), devuelve "".
        // TODO 9: el toggle seleccionado es un RadioButton: castea y pide su getText().
        // TODO 10: devuelve ese texto.
        return "";
    }

    /** Playground visual: monta el formulario real y lo muestra. El alumno pulsa Run. */
    @Override
    public void start(Stage stage) {
        VBox raiz = construirLogin();
        if (raiz == null) {
            raiz = new VBox(new Label("Implementa construirLogin() para ver el formulario."));
        }
        stage.setTitle("Ej263 · Controles básicos");
        stage.setScene(new Scene(raiz, 320, 220));
        stage.show();
    }

    public static void main(String[] args) {
        ToggleGroup g = new ToggleGroup();
        RadioButton r = new RadioButton("Tarjeta");
        r.setToggleGroup(g);
        r.setSelected(true);
        System.out.println("Seleccionado: " + resumenSeleccion(g));
        launch(args);
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Crear una etiqueta.
     * Devuelve un {@link Label} con el texto indicado.
     */
    public static Label crearEtiqueta(String texto) {
        // GUÍA: teoría 1.1 (Label = control de solo lectura para mostrar texto).
        // 1. Devuelve new Label(texto).
        // OJO: el test comprueba getText(); el Label no edita, solo muestra.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearEtiqueta");
    }

    /**
     * Reto Extra 2: Alternar una casilla.
     * Invierte el estado seleccionado del {@link CheckBox} y devuelve el nuevo estado.
     */
    public static boolean alternarMarca(CheckBox casilla) {
        // GUÍA: teoría 1.1 (CheckBox tiene estado selected true/false).
        // 1. Lee casilla.isSelected(). 2. Ponle el contrario con setSelected(...). 3. Devuelve el nuevo valor.
        // PISTA: casilla.setSelected(!casilla.isSelected());
        // OJO: el test llama dos veces y espera que vuelva al estado original (toggle).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para alternarMarca");
    }

    /**
     * Reto Extra 3: Leer una contraseña.
     * Devuelve el texto real escrito en un {@link PasswordField}.
     */
    public static String textoSeguro(PasswordField campo) {
        // GUÍA: teoría 1.1 (PasswordField OCULTA los caracteres al pintarlos, no al guardarlos).
        // 1. Devuelve campo.getText().
        // OJO: aunque la UI muestre puntos, getText() devuelve el texto en claro. Nunca lo
        //   loguees ni lo mandes sin cifrar (esto conecta con b30 · criptografía).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoSeguro");
    }

    /**
     * Reto Extra 4: Contar casillas marcadas.
     * Cuenta cuántos {@link CheckBox} de la lista están seleccionados.
     */
    public static int contarSeleccionados(List<CheckBox> casillas) {
        // GUÍA: teoría 1.1. Recorre y cuenta los que isSelected().
        // 1. Stream sobre la lista. 2. filter(CheckBox::isSelected). 3. count() -> int.
        // PISTA: (int) casillas.stream().filter(CheckBox::isSelected).count();
        // OJO: lista vacía -> 0; el test mezcla marcados y sin marcar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarSeleccionados");
    }

    /**
     * Reto Extra 5: Habilitar o deshabilitar un botón.
     * Pone el {@link Button} habilitado o no según la condición y devuelve si quedó deshabilitado.
     */
    public static boolean habilitarSi(Button boton, boolean habilitado) {
        // GUÍA: teoría 1.1 (disable: el control se ve atenuado y no recibe eventos).
        // 1. boton.setDisable(!habilitado). 2. Devuelve boton.isDisable().
        // OJO: 'disable' NO es lo mismo que 'visible': deshabilitado SIGUE viéndose (gris),
        //   invisible desaparece. Si pides habilitado=true, isDisable() debe ser false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para habilitarSi");
    }

    /**
     * Reto Extra 6: Texto del radio seleccionado, de forma segura.
     * Devuelve un {@link Optional} con el texto del radio marcado del grupo, o vacío si no hay.
     */
    public static Optional<String> radioSeleccionado(ToggleGroup grupo) {
        // GUÍA: teoría 1.1 (ToggleGroup garantiza que como mucho UNO está seleccionado).
        // 1. Pide grupo.getSelectedToggle(). 2. Si es null -> Optional.empty().
        // 3. Si no, castea a RadioButton y Optional.of(rb.getText()).
        // PISTA: Optional.ofNullable(grupo.getSelectedToggle()).map(t -> ((RadioButton) t).getText());
        // OJO: el test prueba el caso "ninguno seleccionado" -> empty(), sin NullPointerException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para radioSeleccionado");
    }

    /**
     * Reto Extra 7: Validar que un campo no está vacío.
     * Indica si el {@link TextField} tiene texto no en blanco.
     */
    public static boolean validarNoVacio(TextField campo) {
        // GUÍA: teoría 1.1 + 4.x (validación; aquí la versión imperativa, en Ej270 la reactiva).
        // 1. Devuelve !campo.getText().isBlank().
        // OJO: isBlank() trata "   " (solo espacios) como vacío; el test mete espacios.
        //   getText() de un TextField nunca es null (es "" por defecto), por eso no peta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarNoVacio");
    }

    /**
     * Reto Extra 8: Limpiar varios campos de texto.
     * Vacía el texto de cada control y devuelve cuántos se limpiaron.
     */
    public static int limpiarFormulario(List<TextInputControl> campos) {
        // GUÍA: teoría 1.1 (TextField y PasswordField comparten la superclase TextInputControl).
        // 1. Recorre la lista. 2. A cada uno clear(). 3. Devuelve campos.size().
        // PISTA: clear() es de TextInputControl, así que sirve para TextField Y PasswordField.
        // OJO: trabajar con la superclase TextInputControl es polimorfismo (b01): un solo bucle
        //   limpia tipos distintos. Tras clear(), getText() es "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarFormulario");
    }

    /**
     * Reto Extra 9: Añadir texto al final de un campo.
     * Concatena el sufijo al texto actual del campo y devuelve el texto resultante.
     */
    public static String concatenarTexto(TextField campo, String sufijo) {
        // GUÍA: teoría 1.1 (TextInputControl tiene API de edición: appendText, insertText, positionCaret).
        // 1. campo.appendText(sufijo). 2. Devuelve campo.getText().
        // PISTA: appendText añade al final SIN tocar el resto; equivale a setText(getText()+sufijo).
        // OJO: el test parte de un campo con texto inicial y espera "inicial"+"sufijo".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para concatenarTexto");
    }

    /**
     * Reto Extra 10: Agrupar radios en exclusividad mutua.
     * Crea un {@link ToggleGroup}, mete en él todos los {@link RadioButton} y lo devuelve.
     */
    public static ToggleGroup agruparEnToggle(List<RadioButton> radios) {
        // GUÍA: teoría 1.1 (un ToggleGroup hace que marcar uno DESMARQUE el anterior).
        // 1. Crea un ToggleGroup. 2. A cada radio, radio.setToggleGroup(grupo). 3. Devuelve el grupo.
        // PISTA: radios.forEach(r -> r.setToggleGroup(grupo));
        // OJO: el test marca dos radios seguidos y comprueba que solo el ÚLTIMO queda seleccionado
        //   (la exclusividad la impone el grupo). grupo.getToggles() debe tener tantos como radios.
        // CULTURA: un ToggleGroup es el equivalente a varios <input type="radio" name="x"> de HTML:
        //   el atributo 'name' compartido es lo que crea la exclusividad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agruparEnToggle");
    }
}
