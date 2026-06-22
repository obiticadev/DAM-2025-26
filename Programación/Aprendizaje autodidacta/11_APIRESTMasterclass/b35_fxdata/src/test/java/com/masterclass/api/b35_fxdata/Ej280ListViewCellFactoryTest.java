package com.masterclass.api.b35_fxdata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.scene.control.ListView;

import com.masterclass.api.b35_fxdata.Ej280ListViewCellFactory.Producto;

import static org.junit.jupiter.api.Assertions.*;

class Ej280ListViewCellFactoryTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void textoCelda() {
        assertEquals("Café (2.50 €)", Ej280ListViewCellFactory.textoCelda(new Producto("Café", 2.5, 10), false));
        assertNull(Ej280ListViewCellFactory.textoCelda(new Producto("Café", 2.5, 10), true)); // caso límite: celda vacía
    }

    @Test
    void claseEstado() {
        assertEquals("disponible", Ej280ListViewCellFactory.claseEstado(new Producto("Café", 2.5, 10)));
        assertEquals("agotado", Ej280ListViewCellFactory.claseEstado(new Producto("Té", 1.8, 0))); // caso límite: stock 0
        assertEquals("bajo", Ej280ListViewCellFactory.claseEstado(new Producto("Agua", 1.0, 3)));
    }

    @Test
    void retoExtra01_textoSimple() {
        assertEquals("hola", Ej280ListViewCellFactory.textoSimple("hola"));
    }

    @Test
    void retoExtra02_textoNombre() {
        assertEquals("Café", Ej280ListViewCellFactory.textoNombre(new Producto("Café", 2.5, 10)));
    }

    @Test
    void retoExtra03_textoConPrecio() {
        assertEquals("Café - 2.50 €", Ej280ListViewCellFactory.textoConPrecio(new Producto("Café", 2.5, 10)));
    }

    @Test
    void retoExtra04_textoSeguro() {
        assertEquals("Café", Ej280ListViewCellFactory.textoSeguro(new Producto("Café", 2.5, 10), false));
        assertEquals("", Ej280ListViewCellFactory.textoSeguro(new Producto("Café", 2.5, 10), true));
    }

    @Test
    void retoExtra05_construirListView() {
        ListView<String> lv = Ej280ListViewCellFactory.construirListView(List.of("a", "b", "c"));
        assertEquals(3, lv.getItems().size());
    }

    @Test
    void retoExtra06_seleccionarPrimero() {
        ListView<String> lv = new ListView<>(javafx.collections.FXCollections.observableArrayList("a", "b"));
        assertEquals("a", Ej280ListViewCellFactory.seleccionarPrimero(lv));
    }

    @Test
    void retoExtra07_seleccionarIndice() {
        ListView<String> lv = new ListView<>(javafx.collections.FXCollections.observableArrayList("a", "b", "c"));
        assertEquals("c", Ej280ListViewCellFactory.seleccionarIndice(lv, 2));
    }

    @Test
    void retoExtra08_contarSeleccionMultiple() {
        ListView<String> lv = new ListView<>(javafx.collections.FXCollections.observableArrayList("a", "b", "c"));
        assertEquals(2, Ej280ListViewCellFactory.contarSeleccionMultiple(lv, 0, 2));
    }

    @Test
    void retoExtra09_ponerPlaceholder() {
        assertEquals("Sin datos", Ej280ListViewCellFactory.ponerPlaceholder(new ListView<>(), "Sin datos"));
    }

    @Test
    void retoExtra10_agregarItemVivo() {
        ListView<String> lv = new ListView<>(javafx.collections.FXCollections.observableArrayList("a"));
        assertEquals(2, Ej280ListViewCellFactory.agregarItemVivo(lv, "b"));
    }
}
