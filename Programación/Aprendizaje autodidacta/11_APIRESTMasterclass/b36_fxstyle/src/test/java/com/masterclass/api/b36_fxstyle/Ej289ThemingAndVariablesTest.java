package com.masterclass.api.b36_fxstyle;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej289ThemingAndVariablesTest {

    @Test
    void alternarTema() {
        assertEquals("oscuro", Ej289ThemingAndVariables.alternarTema("claro"));
        assertEquals("claro", Ej289ThemingAndVariables.alternarTema("oscuro"));
        assertEquals("claro", Ej289ThemingAndVariables.alternarTema("raro")); // caso límite: valor desconocido
    }

    @Test
    void hojaDeTema() {
        assertEquals("/css/tema-oscuro.css", Ej289ThemingAndVariables.hojaDeTema("oscuro"));
        assertEquals("/css/tema-claro.css", Ej289ThemingAndVariables.hojaDeTema("claro"));
        assertEquals("/css/tema-claro.css", Ej289ThemingAndVariables.hojaDeTema("xxx")); // caso límite
    }

    @Test
    void retoExtra01_definirColor() {
        assertEquals("-color-fondo: #ffffff;", Ej289ThemingAndVariables.definirColor("-color-fondo", "#ffffff"));
    }

    @Test
    void retoExtra02_usarColor() {
        assertEquals("-fx-background-color: -color-fondo;",
                Ej289ThemingAndVariables.usarColor("-fx-background-color", "-color-fondo"));
    }

    @Test
    void retoExtra03_derivar() {
        assertEquals("derive(-color-acento, -20%)", Ej289ThemingAndVariables.derivar("-color-acento", -20));
    }

    @Test
    void retoExtra04_gradienteLineal() {
        assertEquals("linear-gradient(to bottom, #ffffff, #000000)",
                Ej289ThemingAndVariables.gradienteLineal("#ffffff", "#000000"));
    }

    @Test
    void retoExtra05_temaSiguiente() {
        assertEquals("alto-contraste", Ej289ThemingAndVariables.temaSiguiente("oscuro"));
        assertEquals("claro", Ej289ThemingAndVariables.temaSiguiente("alto-contraste")); // cierra el ciclo
    }

    @Test
    void retoExtra06_claseRaizTema() {
        assertEquals("tema-oscuro", Ej289ThemingAndVariables.claseRaizTema("oscuro"));
    }

    @Test
    void retoExtra07_hojasConTema() {
        assertEquals(List.of("/css/base.css", "/css/tema-oscuro.css"),
                Ej289ThemingAndVariables.hojasConTema(List.of("/css/base.css"), "oscuro"));
    }

    @Test
    void retoExtra08_colorTexto() {
        assertEquals("white", Ej289ThemingAndVariables.colorTexto("oscuro"));
        assertEquals("black", Ej289ThemingAndVariables.colorTexto("claro"));
    }

    @Test
    void retoExtra09_variablesTema() {
        assertEquals(Map.of("-color-fondo", "#1e1e1e", "-color-texto", "#f0f0f0"),
                Ej289ThemingAndVariables.variablesTema("oscuro"));
        assertEquals(Map.of("-color-fondo", "#ffffff", "-color-texto", "#1c1c1c"),
                Ej289ThemingAndVariables.variablesTema("claro"));
    }

    @Test
    void retoExtra10_cssRaizTema() {
        Map<String, String> vars = new LinkedHashMap<>();
        vars.put("-color-fondo", "#1e1e1e");
        assertEquals(".root { -color-fondo: #1e1e1e; }",
                Ej289ThemingAndVariables.cssRaizTema("oscuro", vars));
    }
}
