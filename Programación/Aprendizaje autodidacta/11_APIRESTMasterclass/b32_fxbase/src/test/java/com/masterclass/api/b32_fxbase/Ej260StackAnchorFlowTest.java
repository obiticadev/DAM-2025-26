package com.masterclass.api.b32_fxbase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import static org.junit.jupiter.api.Assertions.*;

class Ej260StackAnchorFlowTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void apilar() {
        StackPane sp = Ej260StackAnchorFlow.apilar(List.of(new Label("a"), new Label("b")));
        assertNotNull(sp);
        assertEquals(2, sp.getChildren().size());
        assertTrue(Ej260StackAnchorFlow.apilar(null).getChildren().isEmpty()); // caso límite
    }

    @Test
    void anclar() {
        Label l = new Label("x");
        AnchorPane ap = Ej260StackAnchorFlow.anclar(l, 5, 6, 7, 8);
        assertNotNull(ap);
        assertEquals(5.0, AnchorPane.getTopAnchor(l), 0.001);
        assertEquals(8.0, AnchorPane.getLeftAnchor(l), 0.001);
    }

    @Test
    void retoExtra01_nodoSuperior() {
        Label a = new Label("a");
        Label b = new Label("b");
        assertEquals(b, Ej260StackAnchorFlow.nodoSuperior(new StackPane(a, b)));
        assertNull(Ej260StackAnchorFlow.nodoSuperior(new StackPane())); // caso límite
    }

    @Test
    void retoExtra02_filasNecesarias() {
        assertEquals(3, Ej260StackAnchorFlow.filasNecesarias(7, 3));
        assertEquals(0, Ej260StackAnchorFlow.filasNecesarias(0, 3)); // caso límite
    }

    @Test
    void retoExtra03_construirTilePane() {
        TilePane tp = Ej260StackAnchorFlow.construirTilePane(List.of(new Label("a"), new Label("b")), 4);
        assertEquals(4, tp.getPrefColumns());
        assertEquals(2, tp.getChildren().size());
    }

    @Test
    void retoExtra04_anclarEstirado() {
        assertArrayEquals(new double[]{0, 0, 0, 0}, Ej260StackAnchorFlow.anclarEstirado(new Label("x")), 0.001);
    }

    @Test
    void retoExtra05_centrarEnStack() {
        StackPane sp = new StackPane();
        Label l = new Label("x");
        sp.getChildren().add(l);
        assertEquals("CENTER", Ej260StackAnchorFlow.centrarEnStack(sp, l));
    }

    @Test
    void retoExtra06_huecosUltimaFila() {
        assertEquals(2, Ej260StackAnchorFlow.huecosUltimaFila(7, 3));
        assertEquals(0, Ej260StackAnchorFlow.huecosUltimaFila(6, 3)); // fila completa
    }

    @Test
    void retoExtra07_posicionEnTile() {
        assertArrayEquals(new int[]{1, 1}, Ej260StackAnchorFlow.posicionEnTile(4, 3));
        assertNull(Ej260StackAnchorFlow.posicionEnTile(4, 0)); // caso límite
    }

    @Test
    void retoExtra08_desfasesEnAbanico() {
        List<double[]> d = Ej260StackAnchorFlow.desfasesEnAbanico(3, 20);
        assertEquals(3, d.size());
        assertArrayEquals(new double[]{40, 40}, d.get(2), 0.001);
        assertTrue(Ej260StackAnchorFlow.desfasesEnAbanico(0, 20).isEmpty()); // caso límite
    }

    @Test
    void retoExtra09_quitarAnclas() {
        AnchorPane ap = new AnchorPane();
        Label l = new Label("x");
        ap.getChildren().add(l);
        AnchorPane.setTopAnchor(l, 5.0);
        assertTrue(Ej260StackAnchorFlow.quitarAnclas(ap, l));
        assertNull(AnchorPane.getTopAnchor(l));
    }

    @Test
    void retoExtra10_elegirLayout() {
        assertEquals("StackPane", Ej260StackAnchorFlow.elegirLayout("superponer"));
        assertEquals("TilePane", Ej260StackAnchorFlow.elegirLayout("rejilla-uniforme"));
    }
}
