package com.masterclass.api.b35_fxdata;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelo de fila con el patrón "JavaFX bean": el estado vive en {@code Property} observables y se
 * expone con getter, setter y el accesor {@code xProperty()}. Es el formato que una
 * {@code TableView} necesita para enlazar columnas (Ej281) y para que editar una celda se refleje
 * en el modelo al instante (Ej282).
 *
 * <p>¿Por qué no un {@code record} (como el {@code ClienteDto} de Ej286)? Un record es inmutable y
 * sin properties: vale para transportar datos (DTO), pero la TableView no puede observar sus campos
 * ni editarlos en celda. Para la vista de tabla editable se usa este bean mutable y observable.
 */
public final class ClienteFx {

    private final StringProperty nombre = new SimpleStringProperty(this, "nombre", "");
    private final StringProperty email = new SimpleStringProperty(this, "email", "");
    private final IntegerProperty edad = new SimpleIntegerProperty(this, "edad", 0);

    public ClienteFx(String nombre, String email, int edad) {
        this.nombre.set(nombre);
        this.email.set(email);
        this.edad.set(edad);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String valor) {
        nombre.set(valor);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String valor) {
        email.set(valor);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public int getEdad() {
        return edad.get();
    }

    public void setEdad(int valor) {
        edad.set(valor);
    }

    public IntegerProperty edadProperty() {
        return edad;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
