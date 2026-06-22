package com.masterclass.api.b33_fxcontrols;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static org.junit.jupiter.api.Assertions.*;

class Ej267BidirectionalBindingTest {

    @Test
    void sincronizarTextos() {
        assertEquals("HOLA", Ej267BidirectionalBinding.sincronizarTextos("X", "HOLA"));
    }

    @Test
    void sincronizarDosControles() {
        assertEquals("mundo", Ej267BidirectionalBinding.sincronizarDosControles("mundo"));
        assertEquals("", Ej267BidirectionalBinding.sincronizarDosControles("")); // caso límite: cadena vacía
    }

    @Test
    void retoExtra01_enlaceBidireccional() {
        assertTrue(Ej267BidirectionalBinding.enlaceBidireccional(
                new SimpleStringProperty("a"), new SimpleStringProperty("b")));
    }

    @Test
    void retoExtra02_escribirEnUno() {
        assertEquals("hecho", Ej267BidirectionalBinding.escribirEnUno(
                new SimpleStringProperty(""), new SimpleStringProperty(""), "hecho"));
    }

    @Test
    void retoExtra03_valorDerechoGana() {
        assertEquals("derecha", Ej267BidirectionalBinding.valorDerechoGana("izquierda", "derecha"));
    }

    @Test
    void retoExtra04_enlaceNumerico() {
        assertEquals(99, Ej267BidirectionalBinding.enlaceNumerico(
                new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), 99));
    }

    @Test
    void retoExtra05_enlaceBooleano() {
        assertTrue(Ej267BidirectionalBinding.enlaceBooleano(
                new SimpleBooleanProperty(false), new SimpleBooleanProperty(false)));
    }

    @Test
    void retoExtra06_cambioEnAmbasDirecciones() {
        assertEquals("desde B", Ej267BidirectionalBinding.cambioEnAmbasDirecciones(
                new SimpleStringProperty(""), new SimpleStringProperty("")));
    }

    @Test
    void retoExtra07_desligarBidireccional() {
        StringProperty a = new SimpleStringProperty("inicio");
        StringProperty b = new SimpleStringProperty("inicio");
        // tras desligar, cambiar A no toca B: B conserva "inicio"
        assertEquals("inicio", Ej267BidirectionalBinding.desligarBidireccional(a, b));
    }

    @Test
    void retoExtra08_tresEnlazados() {
        assertEquals("nuevo", Ej267BidirectionalBinding.tresEnlazados(
                new SimpleStringProperty("a"), new SimpleStringProperty("b"), new SimpleStringProperty("c")));
    }

    @Test
    void retoExtra09_enlaceConConversor() {
        assertEquals("42", Ej267BidirectionalBinding.enlaceConConversor(
                new SimpleStringProperty(""), new SimpleIntegerProperty(0)));
    }

    @Test
    void retoExtra10_formularioEspejo() {
        assertEquals("tecleado por el usuario", Ej267BidirectionalBinding.formularioEspejo(
                new SimpleStringProperty(""), new SimpleStringProperty("")));
    }
}
