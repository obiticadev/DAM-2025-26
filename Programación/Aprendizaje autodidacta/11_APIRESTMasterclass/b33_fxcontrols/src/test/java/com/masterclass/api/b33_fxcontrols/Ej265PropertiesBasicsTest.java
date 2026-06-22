package com.masterclass.api.b33_fxcontrols;

import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static org.junit.jupiter.api.Assertions.*;

class Ej265PropertiesBasicsTest {

    @Test
    void cambiosRegistrados() {
        assertEquals(List.of("a", "b", "c"),
                Ej265PropertiesBasics.cambiosRegistrados(List.of("a", "b", "c")));
        assertEquals(List.of(), Ej265PropertiesBasics.cambiosRegistrados(List.of())); // caso límite
    }

    @Test
    void contarNotificaciones() {
        // arranca en 0; set 1 (cambia), set 1 (NO cambia), set 2 (cambia) -> 2
        assertEquals(2, Ej265PropertiesBasics.contarNotificaciones(List.of(1, 1, 2)));
        assertEquals(0, Ej265PropertiesBasics.contarNotificaciones(List.of(0))); // caso límite: igual al inicial
    }

    @Test
    void retoExtra01_leerValor() {
        assertEquals("hola", Ej265PropertiesBasics.leerValor(new SimpleStringProperty("hola")));
    }

    @Test
    void retoExtra02_escribirValor() {
        StringProperty p = new SimpleStringProperty("viejo");
        assertEquals("nuevo", Ej265PropertiesBasics.escribirValor(p, "nuevo"));
    }

    @Test
    void retoExtra03_propiedadConNombre() {
        assertEquals("edad", Ej265PropertiesBasics.propiedadConNombre(new Object(), "edad"));
    }

    @Test
    void retoExtra04_ultimoValor() {
        assertEquals(30, Ej265PropertiesBasics.ultimoValor(new SimpleIntegerProperty(0), List.of(10, 20, 30)));
    }

    @Test
    void retoExtra05_invalidacionesContadas() {
        assertEquals(3, Ej265PropertiesBasics.invalidacionesContadas(List.of(10, 20, 30)));
    }

    @Test
    void retoExtra06_desencadenaCambio() {
        assertTrue(Ej265PropertiesBasics.desencadenaCambio(1, 2));
        assertFalse(Ej265PropertiesBasics.desencadenaCambio(5, 5)); // mismo valor: no notifica
    }

    @Test
    void retoExtra07_quitarListener() {
        assertEquals(0, Ej265PropertiesBasics.quitarListener(List.of(1, 2, 3)));
    }

    @Test
    void retoExtra08_valorOTextoVacio() {
        assertEquals("", Ej265PropertiesBasics.valorOTextoVacio(new SimpleStringProperty(null)));
        assertEquals("x", Ej265PropertiesBasics.valorOTextoVacio(new SimpleStringProperty("x")));
    }

    @Test
    void retoExtra09_conmutar() {
        assertTrue(Ej265PropertiesBasics.conmutar(new SimpleBooleanProperty(false)));
    }

    @Test
    void retoExtra10_renombrarPersona() {
        Ej265PropertiesBasics.Persona p = new Ej265PropertiesBasics.Persona("Ana");
        assertEquals("Berta", Ej265PropertiesBasics.renombrarPersona(p, "Berta"));
    }
}
