package com.masterclass.api.b44_nui;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import com.masterclass.api.b44_nui.Ej337NuiOverview.Modalidad;

import static org.junit.jupiter.api.Assertions.*;

class Ej337NuiOverviewTest {

    @Test
    void clasificarModalidad() {
        assertEquals(Modalidad.VOZ, Ej337NuiOverview.clasificarModalidad("comando de voz"));
        assertEquals(Modalidad.GESTO, Ej337NuiOverview.clasificarModalidad("swipe a la derecha"));
        assertNull(Ej337NuiOverview.clasificarModalidad("texto sin pistas")); // caso límite
    }

    @Test
    void pipelineDe() {
        assertEquals(List.of("captura", "preproceso", "reconocimiento", "intencion", "accion"),
                Ej337NuiOverview.pipelineDe(Modalidad.VOZ));
        assertEquals(List.of(), Ej337NuiOverview.pipelineDe(null)); // caso límite
    }

    @Test
    void retoExtra01_latenciaTotalMs() {
        assertEquals(35, Ej337NuiOverview.latenciaTotalMs(List.of(10, 20, 5)));
        assertEquals(0, Ej337NuiOverview.latenciaTotalMs(List.of())); // caso límite
    }

    @Test
    void retoExtra02_modalidadDeRespaldo() {
        assertEquals(Modalidad.GESTO, Ej337NuiOverview.modalidadDeRespaldo(Modalidad.VOZ));
        assertEquals(Modalidad.VOZ, Ej337NuiOverview.modalidadDeRespaldo(Modalidad.GESTO));
        assertNull(Ej337NuiOverview.modalidadDeRespaldo(null)); // caso límite
    }

    @Test
    void retoExtra03_mantieneAlternativa() {
        assertTrue(Ej337NuiOverview.mantieneAlternativa(true, true));
        assertFalse(Ej337NuiOverview.mantieneAlternativa(true, false)); // NUI sola = inaccesible
    }

    @Test
    void retoExtra04_modalidadesCombinadas() {
        assertEquals(List.of("GESTO", "VOZ"),
                Ej337NuiOverview.modalidadesCombinadas(List.of(Modalidad.VOZ, Modalidad.GESTO, Modalidad.VOZ)));
        assertEquals(List.of(), Ej337NuiOverview.modalidadesCombinadas(List.of())); // caso límite
    }

    @Test
    void retoExtra05_requiereConsentimientoBiometrico() {
        assertTrue(Ej337NuiOverview.requiereConsentimientoBiometrico(Modalidad.CUERPO));
        assertFalse(Ej337NuiOverview.requiereConsentimientoBiometrico(Modalidad.GESTO));
    }

    @Test
    void retoExtra06_detectaWakeWord() {
        assertTrue(Ej337NuiOverview.detectaWakeWord("hola asistente abre ventas", "hola asistente"));
        assertFalse(Ej337NuiOverview.detectaWakeWord("abre ventas", "hola asistente"));
    }

    @Test
    void retoExtra07_activaPorConfianza() {
        assertTrue(Ej337NuiOverview.activaPorConfianza(0.8, 0.6));
        assertFalse(Ej337NuiOverview.activaPorConfianza(0.5, 0.6));
    }

    @Test
    void retoExtra08_traducirIntencion() {
        assertEquals("abrir", Ej337NuiOverview.traducirIntencion("open", Map.of("open", "abrir")));
        assertEquals("xyz", Ej337NuiOverview.traducirIntencion("xyz", Map.of("open", "abrir"))); // sin traducción
    }

    @Test
    void retoExtra09_modoManosLibres() {
        assertTrue(Ej337NuiOverview.modoManosLibres(true, false));
        assertFalse(Ej337NuiOverview.modoManosLibres(false, false));
    }

    @Test
    void retoExtra10_descripcionAccesible() {
        assertEquals("control por voz", Ej337NuiOverview.descripcionAccesible(Modalidad.VOZ));
        assertEquals("", Ej337NuiOverview.descripcionAccesible(null)); // caso límite
    }
}
