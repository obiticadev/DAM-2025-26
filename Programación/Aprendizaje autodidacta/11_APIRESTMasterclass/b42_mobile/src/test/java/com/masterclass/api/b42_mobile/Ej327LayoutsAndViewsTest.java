package com.masterclass.api.b42_mobile;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej327LayoutsAndViewsTest {

    private static final double D = 1e-9;

    @Test
    void equivalenteJavaFx() {
        assertEquals("VBox", Ej327LayoutsAndViews.equivalenteJavaFx("LinearLayoutVertical"));
        assertEquals("GridPane", Ej327LayoutsAndViews.equivalenteJavaFx("GridLayout"));
        assertEquals("", Ej327LayoutsAndViews.equivalenteJavaFx("WidgetRaro")); // caso límite: desconocido
    }

    @Test
    void dpAPx() {
        assertEquals(32, Ej327LayoutsAndViews.dpAPx(16, 320)); // 16dp a doble densidad = 32px
        assertEquals(0, Ej327LayoutsAndViews.dpAPx(0, 320));   // caso límite: 0dp = 0px
    }

    @Test
    void retoExtra01_pxADp() {
        assertEquals(16.0, Ej327LayoutsAndViews.pxADp(32, 320), D);
    }

    @Test
    void retoExtra02_spAPx() {
        assertEquals(16, Ej327LayoutsAndViews.spAPx(16, 160, 1.0));
        assertEquals(24, Ej327LayoutsAndViews.spAPx(16, 160, 1.5)); // accesibilidad: fuente grande
    }

    @Test
    void retoExtra03_esContenedor() {
        assertTrue(Ej327LayoutsAndViews.esContenedor("GridLayout"));
        assertFalse(Ej327LayoutsAndViews.esContenedor("Button"));
    }

    @Test
    void retoExtra04_contarVistas() {
        assertEquals(4, Ej327LayoutsAndViews.contarVistas(List.of("a", "b", "c", "d")));
        assertEquals(0, Ej327LayoutsAndViews.contarVistas(null));
    }

    @Test
    void retoExtra05_buscarVistaPorId() {
        Map<String, String> arbol = Map.of("btn_ok", "Button");
        assertEquals("Button", Ej327LayoutsAndViews.buscarVistaPorId(arbol, "btn_ok"));
        assertEquals("", Ej327LayoutsAndViews.buscarVistaPorId(arbol, "btn_no"));
    }

    @Test
    void retoExtra06_orientacionPantalla() {
        assertEquals("vertical", Ej327LayoutsAndViews.orientacionPantalla(1080, 1920));
        assertEquals("horizontal", Ej327LayoutsAndViews.orientacionPantalla(1920, 1080));
    }

    @Test
    void retoExtra07_cabeEnPantalla() {
        assertTrue(Ej327LayoutsAndViews.cabeEnPantalla(360, 360)); // cabe justo
        assertFalse(Ej327LayoutsAndViews.cabeEnPantalla(400, 360));
    }

    @Test
    void retoExtra08_anchoConPeso() {
        assertEquals(100.0, Ej327LayoutsAndViews.anchoConPeso(300, 1, 3), D);
        assertEquals(0.0, Ej327LayoutsAndViews.anchoConPeso(300, 0, 0), D); // sin pesos
    }

    @Test
    void retoExtra09_esIdValido() {
        assertTrue(Ej327LayoutsAndViews.esIdValido("@+id/boton"));
        assertTrue(Ej327LayoutsAndViews.esIdValido("@id/boton"));
        assertFalse(Ej327LayoutsAndViews.esIdValido("boton"));
        assertFalse(Ej327LayoutsAndViews.esIdValido("@+id/"));
    }

    @Test
    void retoExtra10_gravedadOpuesta() {
        assertEquals("end", Ej327LayoutsAndViews.gravedadOpuesta("start"));
        assertEquals("bottom", Ej327LayoutsAndViews.gravedadOpuesta("top"));
        assertEquals("center", Ej327LayoutsAndViews.gravedadOpuesta("center"));
        assertEquals("", Ej327LayoutsAndViews.gravedadOpuesta("raro"));
    }
}
