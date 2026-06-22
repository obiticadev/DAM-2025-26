package com.masterclass.api.b33_fxcontrols;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static org.junit.jupiter.api.Assertions.*;

class Ej268BindingsExpressionsTest {

    @Test
    void etiquetaEstadoSaldo() {
        assertEquals("Rojo", Ej268BindingsExpressions.etiquetaEstadoSaldo(-5));
        assertEquals("OK", Ej268BindingsExpressions.etiquetaEstadoSaldo(0)); // caso límite: 0 no es negativo
    }

    @Test
    void mensajeCarrito() {
        assertEquals("3 artículos", Ej268BindingsExpressions.mensajeCarrito(3));
        assertEquals("vacío", Ej268BindingsExpressions.mensajeCarrito(0)); // caso límite
    }

    @Test
    void retoExtra01_concatenar() {
        assertEquals("a-b", Ej268BindingsExpressions.concatenar(
                new SimpleStringProperty("a"), new SimpleStringProperty("b")));
    }

    @Test
    void retoExtra02_seleccionTernaria() {
        assertEquals("sí", Ej268BindingsExpressions.seleccionTernaria(new SimpleBooleanProperty(true), "sí", "no"));
        assertEquals("no", Ej268BindingsExpressions.seleccionTernaria(new SimpleBooleanProperty(false), "sí", "no"));
    }

    @Test
    void retoExtra03_maximo() {
        assertEquals(9, Ej268BindingsExpressions.maximo(new SimpleIntegerProperty(9), new SimpleIntegerProperty(4)));
    }

    @Test
    void retoExtra04_iguales() {
        assertTrue(Ej268BindingsExpressions.iguales(new SimpleIntegerProperty(7), 7));
        assertFalse(Ej268BindingsExpressions.iguales(new SimpleIntegerProperty(7), 8));
    }

    @Test
    void retoExtra05_tamanoLista() {
        assertEquals(3, Ej268BindingsExpressions.tamanoLista(FXCollections.observableArrayList("a", "b", "c")));
    }

    @Test
    void retoExtra06_listaVacia() {
        assertTrue(Ej268BindingsExpressions.listaVacia(FXCollections.observableArrayList()));
        assertFalse(Ej268BindingsExpressions.listaVacia(FXCollections.observableArrayList("x")));
    }

    @Test
    void retoExtra07_formatearEtiqueta() {
        assertEquals("Total: 5", Ej268BindingsExpressions.formatearEtiqueta(new SimpleIntegerProperty(5)));
    }

    @Test
    void retoExtra08_fichaPersona() {
        assertEquals("Ana (30)", Ej268BindingsExpressions.fichaPersona(
                new SimpleStringProperty("Ana"), new SimpleIntegerProperty(30)));
    }

    @Test
    void retoExtra09_valorEnIndice() {
        ObservableList<String> lista = FXCollections.observableArrayList("cero", "uno", "dos");
        assertEquals("uno", Ej268BindingsExpressions.valorEnIndice(lista, new SimpleIntegerProperty(1)));
    }

    @Test
    void retoExtra10_semaforoSaldo() {
        assertEquals("NEGATIVO", Ej268BindingsExpressions.semaforoSaldo(-5));
        assertEquals("ALTO", Ej268BindingsExpressions.semaforoSaldo(2000));
        assertEquals("NORMAL", Ej268BindingsExpressions.semaforoSaldo(500));
    }
}
