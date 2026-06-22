package com.masterclass.api.b39_fxdeploy;

import com.masterclass.api.b39_fxdeploy.Ej306IntegratedHelp.AcercaDe;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej306IntegratedHelpTest {

    private static final AcercaDe INFO = new AcercaDe("Mi App", "1.0.0", 2026, "Ana");

    @Test
    void textoAcercaDe() {
        assertEquals("Mi App v1.0.0\n© 2026 Ana", Ej306IntegratedHelp.textoAcercaDe(INFO));
        assertEquals("", Ej306IntegratedHelp.textoAcercaDe(null)); // caso límite
    }

    @Test
    void esUrlValida() {
        assertTrue(Ej306IntegratedHelp.esUrlValida("https://docs.app"));
        assertTrue(Ej306IntegratedHelp.esUrlValida("http://docs.app"));
        assertFalse(Ej306IntegratedHelp.esUrlValida("ftp://x"));
        assertFalse(Ej306IntegratedHelp.esUrlValida(null)); // caso límite
    }

    @Test
    void lineaCopyright() {
        assertEquals("© 2026 Ana", Ej306IntegratedHelp.lineaCopyright(2026, "Ana"));
        assertEquals("© 2026", Ej306IntegratedHelp.lineaCopyright(2026, "")); // sin autor
    }

    @Test
    void retoExtra01_tituloVentana() {
        assertEquals("Acerca de — Mi App", Ej306IntegratedHelp.tituloVentana(INFO));
        assertEquals("Acerca de", Ej306IntegratedHelp.tituloVentana(null)); // caso límite
    }

    @Test
    void retoExtra02_urlDocsVersion() {
        assertEquals("https://docs.app/2.4.1/index.html",
                Ej306IntegratedHelp.urlDocsVersion("https://docs.app", "2.4.1"));
        assertEquals("https://docs.app/2.4.1/index.html",
                Ej306IntegratedHelp.urlDocsVersion("https://docs.app/", "2.4.1")); // barra final no duplica
    }

    @Test
    void retoExtra03_esEmailValido() {
        assertTrue(Ej306IntegratedHelp.esEmailValido("a@b.com"));
        assertFalse(Ej306IntegratedHelp.esEmailValido("a@@b.com")); // dos arrobas
        assertFalse(Ej306IntegratedHelp.esEmailValido("ab.com")); // sin arroba
    }

    @Test
    void retoExtra04_seccionesManual() {
        List<String> s = Ej306IntegratedHelp.seccionesManual();
        assertEquals(5, s.size());
        assertEquals("Introducción", s.get(0));
    }

    @Test
    void retoExtra05_buscarEnManual() {
        List<String> r = Ej306IntegratedHelp.buscarEnManual(Ej306IntegratedHelp.seccionesManual(), "PASO");
        assertEquals(List.of("Primeros pasos"), r); // no distingue mayúsculas
        assertEquals(List.of(), Ej306IntegratedHelp.buscarEnManual(null, "x")); // caso límite
    }

    @Test
    void retoExtra06_pistaAtajo() {
        assertEquals("Pulsa [F1] para ayuda", Ej306IntegratedHelp.pistaAtajo("F1"));
        assertEquals("", Ej306IntegratedHelp.pistaAtajo(null)); // caso límite
    }

    @Test
    void retoExtra07_indiceNumerado() {
        assertEquals(List.of("1. A", "2. B"), Ej306IntegratedHelp.indiceNumerado(List.of("A", "B")));
        assertEquals(List.of(), Ej306IntegratedHelp.indiceNumerado(null)); // caso límite
    }

    @Test
    void retoExtra08_urlConAncla() {
        assertEquals("docs.html#primeros-pasos",
                Ej306IntegratedHelp.urlConAncla("docs.html", "Primeros pasos"));
    }

    @Test
    void retoExtra09_copyrightDesactualizado() {
        assertTrue(Ej306IntegratedHelp.copyrightDesactualizado(new AcercaDe("A", "1.0.0", 2024, "X"), 2026));
        assertFalse(Ej306IntegratedHelp.copyrightDesactualizado(INFO, 2026)); // mismo año
    }

    @Test
    void retoExtra10_acercaDeCompleto() {
        assertEquals("Mi App v1.0.0\n© 2026 Ana\nVersión v1.0.0",
                Ej306IntegratedHelp.acercaDeCompleto(INFO));
        assertEquals("", Ej306IntegratedHelp.acercaDeCompleto(null)); // caso límite
    }
}
