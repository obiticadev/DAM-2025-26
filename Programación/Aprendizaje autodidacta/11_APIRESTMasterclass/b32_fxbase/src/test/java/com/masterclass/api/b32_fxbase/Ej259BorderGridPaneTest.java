package com.masterclass.api.b32_fxbase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import static org.junit.jupiter.api.Assertions.*;

class Ej259BorderGridPaneTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void colocarEnBorde() {
        Label l = new Label("c");
        BorderPane bp = Ej259BorderGridPane.colocarEnBorde("center", l);
        assertNotNull(bp);
        assertEquals(l, bp.getCenter());
        assertNull(Ej259BorderGridPane.colocarEnBorde("inexistente", l)); // caso límite
    }

    @Test
    void indiceLineal() {
        assertEquals(5, Ej259BorderGridPane.indiceLineal(1, 2, 3));
        assertEquals(-1, Ej259BorderGridPane.indiceLineal(1, 3, 3)); // columna fuera de rango
    }

    @Test
    void retoExtra01_zonaOpuesta() {
        assertEquals("bottom", Ej259BorderGridPane.zonaOpuesta("top"));
        assertEquals("right", Ej259BorderGridPane.zonaOpuesta("left"));
    }

    @Test
    void retoExtra02_colocarEnGrid() {
        GridPane g = new GridPane();
        Label l = new Label("x");
        Ej259BorderGridPane.colocarEnGrid(g, l, 2, 1);
        assertEquals(2, GridPane.getColumnIndex(l));
        assertEquals(1, GridPane.getRowIndex(l));
    }

    @Test
    void retoExtra03_aplicarColspan() {
        GridPane g = new GridPane();
        Label l = new Label("x");
        g.add(l, 0, 0);
        Ej259BorderGridPane.aplicarColspan(g, l, 3);
        assertEquals(3, GridPane.getColumnSpan(l));
    }

    @Test
    void retoExtra04_nodoEnZona() {
        Label l = new Label("r");
        BorderPane bp = new BorderPane();
        bp.setRight(l);
        assertEquals(l, Ej259BorderGridPane.nodoEnZona(bp, "right"));
        assertNull(Ej259BorderGridPane.nodoEnZona(bp, "left"));
    }

    @Test
    void retoExtra05_contarZonasOcupadas() {
        BorderPane bp = new BorderPane();
        bp.setTop(new Label("t"));
        bp.setCenter(new Label("c"));
        assertEquals(2, Ej259BorderGridPane.contarZonasOcupadas(bp));
    }

    @Test
    void retoExtra06_coordenadasDesdeIndice() {
        assertArrayEquals(new int[]{1, 2}, Ej259BorderGridPane.coordenadasDesdeIndice(5, 3));
        assertNull(Ej259BorderGridPane.coordenadasDesdeIndice(5, 0)); // caso límite
    }

    @Test
    void retoExtra07_totalCeldas() {
        assertEquals(12, Ej259BorderGridPane.totalCeldas(3, 4));
        assertEquals(0, Ej259BorderGridPane.totalCeldas(0, 4)); // caso límite
    }

    @Test
    void retoExtra08_celdasOcupadas() {
        assertEquals(6, Ej259BorderGridPane.celdasOcupadas(2, 3));
        assertEquals(1, Ej259BorderGridPane.celdasOcupadas(1, 1));
    }

    @Test
    void retoExtra09_esZonaValida() {
        assertTrue(Ej259BorderGridPane.esZonaValida("center"));
        assertFalse(Ej259BorderGridPane.esZonaValida("middle"));
    }

    @Test
    void retoExtra10_construirGrid() {
        GridPane g = Ej259BorderGridPane.construirGrid(3, 2);
        assertEquals(6, g.getChildren().size());
    }
}
