package com.masterclass.api.b38_fxreports;

import com.masterclass.api.b38_fxreports.Ej301ReportParamsAndGroups.LineaDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class Ej301ReportParamsAndGroupsTest {

    private static final double D = 1e-6;

    private static List<LineaDto> lineas() {
        return Ej301ReportParamsAndGroups.lineas(
                new LineaDto("A", "Agua", 10),
                new LineaDto("A", "Zumo", 20),
                new LineaDto("B", "Pan", 5));
    }

    @Test
    void parametro() {
        assertEquals("Ventas", Ej301ReportParamsAndGroups.parametro(Map.of("titulo", "Ventas"), "titulo", "Informe"));
        assertEquals("hoy", Ej301ReportParamsAndGroups.parametro(Map.of(), "fecha", "hoy")); // caso límite: falta
    }

    @Test
    void subtotalPorGrupo() {
        Map<String, Double> m = Ej301ReportParamsAndGroups.subtotalPorGrupo(lineas());
        assertEquals(30.0, m.get("A"), D);
        assertEquals(5.0, m.get("B"), D);
        assertTrue(Ej301ReportParamsAndGroups.subtotalPorGrupo(null).isEmpty()); // caso límite
    }

    @Test
    void granTotal() {
        assertEquals(35.0, Ej301ReportParamsAndGroups.granTotal(lineas()), D);
        assertEquals(0.0, Ej301ReportParamsAndGroups.granTotal(List.of()), D); // caso límite
    }

    @Test
    void retoExtra01_tituloDelInforme() {
        assertEquals("Q1", Ej301ReportParamsAndGroups.tituloDelInforme(Map.of("titulo", "Q1")));
        assertEquals("Informe sin título", Ej301ReportParamsAndGroups.tituloDelInforme(Map.of())); // caso límite
    }

    @Test
    void retoExtra02_numeroDeGrupos() {
        assertEquals(2, Ej301ReportParamsAndGroups.numeroDeGrupos(lineas()));
        assertEquals(0, Ej301ReportParamsAndGroups.numeroDeGrupos(null)); // caso límite
    }

    @Test
    void retoExtra03_lineasDeGrupo() {
        assertEquals(2, Ej301ReportParamsAndGroups.lineasDeGrupo(lineas(), "A").size());
        assertTrue(Ej301ReportParamsAndGroups.lineasDeGrupo(lineas(), "Z").isEmpty()); // caso límite
    }

    @Test
    void retoExtra04_subtotalDe() {
        assertEquals(30.0, Ej301ReportParamsAndGroups.subtotalDe(lineas(), "A"), D);
        assertEquals(0.0, Ej301ReportParamsAndGroups.subtotalDe(lineas(), "Z"), D); // caso límite
    }

    @Test
    void retoExtra05_contarPorGrupo() {
        Map<String, Integer> m = Ej301ReportParamsAndGroups.contarPorGrupo(lineas());
        assertEquals(2, m.get("A"));
        assertEquals(1, m.get("B"));
    }

    @Test
    void retoExtra06_mediaPorGrupo() {
        Map<String, Double> m = Ej301ReportParamsAndGroups.mediaPorGrupo(lineas());
        assertEquals(15.0, m.get("A"), D);
        assertEquals(5.0, m.get("B"), D);
    }

    @Test
    void retoExtra07_grupoMayorSubtotal() {
        assertEquals(Optional.of("A"), Ej301ReportParamsAndGroups.grupoMayorSubtotal(lineas()));
        assertTrue(Ej301ReportParamsAndGroups.grupoMayorSubtotal(null).isEmpty()); // caso límite
    }

    @Test
    void retoExtra08_porcentajeDeGrupo() {
        assertEquals(85.714285, Ej301ReportParamsAndGroups.porcentajeDeGrupo(lineas(), "A"), D);
        assertEquals(0.0, Ej301ReportParamsAndGroups.porcentajeDeGrupo(List.of(), "A"), D); // caso límite
    }

    @Test
    void retoExtra09_parametroEntero() {
        assertEquals(3, Ej301ReportParamsAndGroups.parametroEntero(Map.of("copias", "3"), "copias", 1));
        assertEquals(1, Ej301ReportParamsAndGroups.parametroEntero(Map.of(), "copias", 1)); // falta -> defecto
        assertEquals(1, Ej301ReportParamsAndGroups.parametroEntero(Map.of("copias", "x"), "copias", 1)); // no num
    }

    @Test
    void retoExtra10_lineaResumen() {
        assertEquals("3 líneas en 2 grupos. Total: 35.00 €", Ej301ReportParamsAndGroups.lineaResumen(lineas()));
    }
}
