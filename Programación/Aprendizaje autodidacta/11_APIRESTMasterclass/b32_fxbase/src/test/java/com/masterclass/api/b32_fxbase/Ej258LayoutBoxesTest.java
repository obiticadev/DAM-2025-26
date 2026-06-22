package com.masterclass.api.b32_fxbase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import static org.junit.jupiter.api.Assertions.*;

class Ej258LayoutBoxesTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    @Test
    void anchoTotalHBox() {
        // 100+50+30 = 180; huecos 2*10=20; padding 2*8=16 -> 216
        assertEquals(216, Ej258LayoutBoxes.anchoTotalHBox(List.of(100.0, 50.0, 30.0), 10, 8), 0.001);
        assertEquals(16, Ej258LayoutBoxes.anchoTotalHBox(List.of(), 10, 8), 0.001); // caso límite
    }

    @Test
    void construirVBox() {
        VBox caja = Ej258LayoutBoxes.construirVBox(List.of(new Label("a"), new Label("b")), 5);
        assertNotNull(caja);
        assertEquals(5, caja.getSpacing(), 0.001);
        assertEquals(2, caja.getChildren().size());
    }

    @Test
    void retoExtra01_altoTotalVBox() {
        assertEquals(216, Ej258LayoutBoxes.altoTotalVBox(List.of(100.0, 50.0, 30.0), 10, 8), 0.001);
        assertEquals(16, Ej258LayoutBoxes.altoTotalVBox(List.of(), 10, 8), 0.001);
    }

    @Test
    void retoExtra02_construirHBox() {
        HBox caja = Ej258LayoutBoxes.construirHBox(List.of(new Label("a")), 7);
        assertEquals(7, caja.getSpacing(), 0.001);
        assertEquals(1, caja.getChildren().size());
    }

    @Test
    void retoExtra03_aplicarPadding() {
        Region r = Ej258LayoutBoxes.aplicarPadding(new VBox(), 12);
        assertEquals(12, r.getPadding().getTop(), 0.001);
        assertEquals(12, r.getPadding().getLeft(), 0.001);
    }

    @Test
    void retoExtra04_aplicarMargen() {
        assertEquals(new Insets(6), Ej258LayoutBoxes.aplicarMargen(new Label("x"), 6));
    }

    @Test
    void retoExtra05_repartoConPrioridad() {
        assertEquals(100, Ej258LayoutBoxes.repartoConPrioridad(300, 3), 0.001);
        assertEquals(0, Ej258LayoutBoxes.repartoConPrioridad(300, 0), 0.001); // caso límite
    }

    @Test
    void retoExtra06_marcarCrecimiento() {
        assertEquals("ALWAYS", Ej258LayoutBoxes.marcarCrecimiento(new Label("x")));
    }

    @Test
    void retoExtra07_espacioDeHuecos() {
        assertEquals(20, Ej258LayoutBoxes.espacioDeHuecos(3, 10), 0.001);
        assertEquals(0, Ej258LayoutBoxes.espacioDeHuecos(1, 10), 0.001); // caso límite: 1 hijo
    }

    @Test
    void retoExtra08_centrarContenido() {
        assertEquals("CENTER", Ej258LayoutBoxes.centrarContenido(new VBox()));
    }

    @Test
    void retoExtra09_distribuirEquitativo() {
        assertEquals(100, Ej258LayoutBoxes.distribuirEquitativo(320, 3, 10), 0.001);
        assertEquals(0, Ej258LayoutBoxes.distribuirEquitativo(320, 0, 10), 0.001); // caso límite
    }

    @Test
    void retoExtra10_anchoMinimoNecesario() {
        // 40+40 = 80; hueco 1*5=5; padding 2*4=8 -> 93
        assertEquals(93, Ej258LayoutBoxes.anchoMinimoNecesario(List.of(40.0, 40.0), 5, 4), 0.001);
    }
}
