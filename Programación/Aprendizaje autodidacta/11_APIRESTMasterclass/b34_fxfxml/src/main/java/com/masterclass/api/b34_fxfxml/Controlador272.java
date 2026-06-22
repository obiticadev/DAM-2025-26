package com.masterclass.api.b34_fxfxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controlador de apoyo para {@code vista272.fxml} (lo usa {@link Ej272ControllerInjection}).
 *
 * <p>Demuestra los tres tipos de inyección que hace el {@code FXMLLoader}:
 * <ul>
 *   <li><b>Campos {@code @FXML}</b> con el mismo nombre que el {@code fx:id} del nodo.</li>
 *   <li><b>Método {@code initialize()}</b>: se ejecuta cuando ya están todos los nodos inyectados.</li>
 *   <li><b>Manejador {@code @FXML}</b> referenciado desde el FXML con {@code onAction="#alAceptar"}.</li>
 * </ul>
 *
 * <p>El ejercicio 272 comprueba que tras {@code load()} ningún {@code @FXML} es null y que al
 * disparar el botón el saludo se actualiza (la lógica del controlador funciona).
 */
public class Controlador272 {

    @FXML
    private TextField usuario;

    @FXML
    private Label saludo;

    @FXML
    private Button aceptar;

    /** Cuenta cuántas veces se ha pulsado el botón (lo lee el test). */
    private int pulsaciones = 0;

    @FXML
    public void initialize() {
        // Los @FXML ya están inyectados aquí; un buen sitio para estado inicial.
        if (saludo != null) {
            saludo.setText("");
        }
    }

    /** Manejador referenciado por {@code onAction="#alAceptar"} en el FXML. */
    @FXML
    public void alAceptar() {
        pulsaciones++;
        String nombre = usuario == null || usuario.getText() == null ? "" : usuario.getText();
        if (saludo != null) {
            saludo.setText("Hola, " + nombre);
        }
    }

    public TextField getUsuario() {
        return usuario;
    }

    public Label getSaludo() {
        return saludo;
    }

    public Button getAceptar() {
        return aceptar;
    }

    public int getPulsaciones() {
        return pulsaciones;
    }
}
