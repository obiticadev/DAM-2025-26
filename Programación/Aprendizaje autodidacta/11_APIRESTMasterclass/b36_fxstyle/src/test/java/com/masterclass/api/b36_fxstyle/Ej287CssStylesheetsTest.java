package com.masterclass.api.b36_fxstyle;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej287CssStylesheetsTest {

    @Test
    void claseSegunSaldo() {
        assertEquals("saldo-negativo", Ej287CssStylesheets.claseSegunSaldo(-5));
        assertEquals("saldo-positivo", Ej287CssStylesheets.claseSegunSaldo(12));
        assertEquals("saldo-cero", Ej287CssStylesheets.claseSegunSaldo(0)); // caso límite (frontera)
    }

    @Test
    void clasesDeFila() {
        assertEquals(List.of("fila", "fila-seleccionada", "fila-destacada"),
                Ej287CssStylesheets.clasesDeFila(true, true));
        assertEquals(List.of("fila", "fila-seleccionada"),
                Ej287CssStylesheets.clasesDeFila(true, false));
        assertEquals(List.of("fila"),
                Ej287CssStylesheets.clasesDeFila(false, false)); // caso límite: solo la base
    }

    @Test
    void retoExtra01_selectorDeClase() {
        assertEquals(".boton-primario", Ej287CssStylesheets.selectorDeClase("boton-primario"));
    }

    @Test
    void retoExtra02_selectorDeId() {
        assertEquals("#boton-guardar", Ej287CssStylesheets.selectorDeId("boton-guardar"));
    }

    @Test
    void retoExtra03_selectorDescendiente() {
        assertEquals(".dialogo .boton", Ej287CssStylesheets.selectorDescendiente("dialogo", "boton"));
    }

    @Test
    void retoExtra04_selectorMulticlase() {
        assertEquals(".a.b.c", Ej287CssStylesheets.selectorMulticlase(List.of("a", "b", "c")));
        assertEquals("", Ej287CssStylesheets.selectorMulticlase(List.of())); // caso límite
    }

    @Test
    void retoExtra05_estiloInline() {
        Map<String, String> props = new LinkedHashMap<>();
        props.put("-fx-text-fill", "red");
        props.put("-fx-font-size", "14");
        assertEquals("-fx-text-fill: red; -fx-font-size: 14;", Ej287CssStylesheets.estiloInline(props));
    }

    @Test
    void retoExtra06_agregarClase() {
        assertEquals(List.of("fila", "activa"), Ej287CssStylesheets.agregarClase(List.of("fila"), "activa"));
        assertEquals(List.of("activa"), Ej287CssStylesheets.agregarClase(List.of("activa"), "activa")); // sin duplicar
    }

    @Test
    void retoExtra07_quitarClase() {
        assertEquals(List.of("fila"), Ej287CssStylesheets.quitarClase(List.of("fila", "activa"), "activa"));
    }

    @Test
    void retoExtra08_alternarClase() {
        assertEquals(List.of("fila", "activa"), Ej287CssStylesheets.alternarClase(List.of("fila"), "activa"));
        assertEquals(List.of("fila"), Ej287CssStylesheets.alternarClase(List.of("fila", "activa"), "activa"));
    }

    @Test
    void retoExtra09_especificidadMayor() {
        assertEquals("#id", Ej287CssStylesheets.especificidadMayor("#id", ".clase"));
        assertEquals(".a.b", Ej287CssStylesheets.especificidadMayor(".a.b", ".c"));
    }

    @Test
    void retoExtra10_claseSegunNivel() {
        assertEquals("nivel-error", Ej287CssStylesheets.claseSegunNivel("ERROR"));
        assertEquals("nivel-info", Ej287CssStylesheets.claseSegunNivel("INFO"));
        assertEquals("nivel-desconocido", Ej287CssStylesheets.claseSegunNivel("XXX")); // caso límite
    }
}
