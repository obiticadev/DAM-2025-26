package com.masterclass.api.b47_pruebas;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej357TestStrategyPlanTest {

    @Test
    void planDePruebas_moduloNormal() {
        var modulo = new Ej357TestStrategyPlan.Modulo("Auth", 7, 10);
        var plan = Ej357TestStrategyPlan.planDePruebas(modulo);
        assertNotNull(plan);
        assertTrue(plan.niveles().contains(Ej357TestStrategyPlan.NivelPrueba.UNITARIA));
        assertTrue(plan.niveles().contains(Ej357TestStrategyPlan.NivelPrueba.INTEGRACION));
        assertTrue(plan.niveles().contains(Ej357TestStrategyPlan.NivelPrueba.SISTEMA));
        assertEquals(210, plan.numCasos()); // 7 * 10 * 3
        assertTrue(plan.criterioSalida().contains("74%")); // 60 + 7*2
    }

    @Test
    void planDePruebas_moduloNull() {
        assertNull(Ej357TestStrategyPlan.planDePruebas(null));
    }

    @Test
    void planDePruebas_riesgoAlto_incluyeAceptacion() {
        var modulo = new Ej357TestStrategyPlan.Modulo("Core", 9, 5);
        var plan = Ej357TestStrategyPlan.planDePruebas(modulo);
        assertNotNull(plan);
        assertTrue(plan.niveles().contains(Ej357TestStrategyPlan.NivelPrueba.ACEPTACION));
    }

    @Test
    void cumpleCriterioSalida_pasa() {
        var plan = new Ej357TestStrategyPlan.PlanPruebas(List.of(), 10, "0 críticos y cobertura >= 74%");
        var res = new Ej357TestStrategyPlan.Resultados(0, 80.0);
        assertTrue(Ej357TestStrategyPlan.cumpleCriterioSalida(plan, res));
    }

    @Test
    void cumpleCriterioSalida_fallaPorCriticos() {
        var plan = new Ej357TestStrategyPlan.PlanPruebas(List.of(), 10, "0 críticos y cobertura >= 74%");
        var res = new Ej357TestStrategyPlan.Resultados(1, 80.0);
        assertFalse(Ej357TestStrategyPlan.cumpleCriterioSalida(plan, res));
    }

    @Test
    void cumpleCriterioSalida_fallaPorCobertura() {
        var plan = new Ej357TestStrategyPlan.PlanPruebas(List.of(), 10, "0 críticos y cobertura >= 74%");
        var res = new Ej357TestStrategyPlan.Resultados(0, 60.0);
        assertFalse(Ej357TestStrategyPlan.cumpleCriterioSalida(plan, res));
    }

    @Test
    void cumpleCriterioSalida_null() {
        assertFalse(Ej357TestStrategyPlan.cumpleCriterioSalida(null, null));
    }

    @Test
    void retoExtra01_clasificarEstrategia() {
        assertEquals("PIRAMIDE", Ej357TestStrategyPlan.clasificarEstrategia(100, 30, 5));
        assertEquals("TROFEO", Ej357TestStrategyPlan.clasificarEstrategia(50, 60, 5));
        assertEquals("SIN_FORMA", Ej357TestStrategyPlan.clasificarEstrategia(10, 10, 10));
    }

    @Test
    void retoExtra02_modulosPorRiesgo() {
        var lista = List.of(
                new Ej357TestStrategyPlan.Modulo("A", 8, 5),
                new Ej357TestStrategyPlan.Modulo("B", 3, 5),
                new Ej357TestStrategyPlan.Modulo("C", 9, 5)
        );
        var resultado = Ej357TestStrategyPlan.modulosPorRiesgo(lista, 5);
        assertEquals(2, resultado.size());
        assertEquals("C", resultado.get(0).nombre()); // mayor riesgo primero
        assertDoesNotThrow(() -> Ej357TestStrategyPlan.modulosPorRiesgo(null, 5));
    }

    @Test
    void retoExtra03_rasSinCobertura() {
        Map<String, Integer> matriz = Map.of("RA1", 5, "RA2", 0, "RA3", 3);
        var resultado = Ej357TestStrategyPlan.rasSinCobertura(matriz, 3);
        assertTrue(resultado.contains("RA2"));
        assertFalse(resultado.contains("RA3")); // exactamente 3 >= 3
        assertEquals(List.of(), Ej357TestStrategyPlan.rasSinCobertura(matriz, 0));
    }

    @Test
    void retoExtra04_tipoTestDouble() {
        assertEquals("STUB", Ej357TestStrategyPlan.tipoTestDouble("sin_efectos_secundarios"));
        assertEquals("MOCK", Ej357TestStrategyPlan.tipoTestDouble("verificar_interaccion"));
        assertEquals("DUMMY", Ej357TestStrategyPlan.tipoTestDouble(null));
        assertEquals("DUMMY", Ej357TestStrategyPlan.tipoTestDouble("desconocido"));
    }

    @Test
    void retoExtra05_priorizarPruebas() {
        var pruebas = List.of("test_a", "test_b", "test_c");
        var impactos = Map.of("test_a", 5, "test_c", 10);
        var resultado = Ej357TestStrategyPlan.priorizarPruebas(pruebas, impactos);
        assertEquals("test_c", resultado.get(0));
        assertEquals("test_a", resultado.get(1));
        assertEquals("test_b", resultado.get(2)); // impacto 0
    }

    @Test
    void retoExtra06_detectarFlaky() {
        Map<String, List<Boolean>> historial = Map.of(
                "t1", List.of(true, false, true),
                "t2", List.of(true, true),
                "t3", List.of(false)
        );
        var flaky = Ej357TestStrategyPlan.detectarFlaky(historial);
        assertTrue(flaky.contains("t1"));
        assertFalse(flaky.contains("t2"));
        assertFalse(flaky.contains("t3"));
    }

    @Test
    void retoExtra07_criteriosEntradaCumplidos() {
        assertTrue(Ej357TestStrategyPlan.criteriosEntradaCumplidos(true, true, true));
        assertFalse(Ej357TestStrategyPlan.criteriosEntradaCumplidos(false, true, true));
        assertFalse(Ej357TestStrategyPlan.criteriosEntradaCumplidos(true, false, true));
    }

    @Test
    void retoExtra08_estimarEsfuerzo() {
        assertEquals(25, Ej357TestStrategyPlan.estimarEsfuerzo(10, 2.0, 5, 1.0)); // 20+5=25
        assertEquals(0, Ej357TestStrategyPlan.estimarEsfuerzo(0, 2.0, 0, 1.0));
        assertEquals(-1, Ej357TestStrategyPlan.estimarEsfuerzo(-1, 2.0, 5, 1.0));
    }

    @Test
    void retoExtra09_featureListaParaQA() {
        assertTrue(Ej357TestStrategyPlan.featureListaParaQA(true, 80.0, 80.0, 0)); // exactamente igual
        assertFalse(Ej357TestStrategyPlan.featureListaParaQA(false, 80.0, 80.0, 0));
        assertFalse(Ej357TestStrategyPlan.featureListaParaQA(true, 80.0, 80.0, 1));
    }

    @Test
    void retoExtra10_herramientaPrincipal() {
        assertEquals("JUnit5+Mockito", Ej357TestStrategyPlan.herramientaPrincipal("b19"));
        assertEquals("SonarQube+Jacoco", Ej357TestStrategyPlan.herramientaPrincipal("b23"));
        assertEquals("EstrategiaFormal", Ej357TestStrategyPlan.herramientaPrincipal("b47"));
        assertEquals("Desconocido", Ej357TestStrategyPlan.herramientaPrincipal("b99"));
        assertEquals("Desconocido", Ej357TestStrategyPlan.herramientaPrincipal(null));
    }
}
