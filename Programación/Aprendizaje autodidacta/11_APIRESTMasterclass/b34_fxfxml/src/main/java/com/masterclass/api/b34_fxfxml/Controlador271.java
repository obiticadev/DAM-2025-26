package com.masterclass.api.b34_fxfxml;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlador de apoyo para {@code vista271.fxml} (lo usa {@link Ej271FxmlLoaderBasics}).
 *
 * <p>Es la clase que el FXML referencia en su atributo {@code fx:controller}. Cuando el
 * {@code FXMLLoader} carga el {@code .fxml}, crea una instancia de esta clase, inyecta en sus
 * campos {@code @FXML} los nodos cuyo {@code fx:id} coincide con el nombre del campo, y al final
 * invoca {@link #initialize()} (si existe). Mínimo a propósito: el ejercicio 271 solo comprueba
 * que el controlador se asocia y se puede recuperar con {@code loader.getController()}.
 */
public class Controlador271 {

    /** Inyectado desde {@code <Label fx:id="titulo" .../>} del FXML. */
    @FXML
    private Label titulo;

    /** Lo llama el FXMLLoader una vez inyectados los @FXML; aquí solo deja constancia. */
    @FXML
    public void initialize() {
        if (titulo != null) {
            titulo.setText("Vista 271 cargada desde FXML");
        }
    }

    /** Permite al test comprobar que el @FXML se inyectó (no es null tras load()). */
    public Label getTitulo() {
        return titulo;
    }
}
