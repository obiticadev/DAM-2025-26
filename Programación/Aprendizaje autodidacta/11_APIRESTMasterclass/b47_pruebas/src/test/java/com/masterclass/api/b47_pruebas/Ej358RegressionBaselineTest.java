package com.masterclass.api.b47_pruebas;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Ej358RegressionBaselineTest {

    @Test
    void compararConBaseline_sinRegresiones() {
        var base = Map.<String, Object>of("latencia", 100, "errores", 0);
        var actual = Map.<String, Object>of("latencia", 105, "errores", 0);
        var resultado = Ej358RegressionBaseline.compararConBaseline(actual, base, 0.10);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void compararConBaseline_regresionNumerica() {
        var base = Map.<String, Object>of("latencia", 100);
        var actual = Map.<String, Object>of("latencia", 130); // 30 % > 10 %
        var resultado = Ej358RegressionBaseline.compararConBaseline(actual, base, 0.10);
        assertFalse(resultado.isEmpty());
        assertEquals("latencia", resultado.get(0).metrica());
    }

    @Test
    void compararConBaseline_claveNueva() {
        var base = Map.<String, Object>of("latencia", 100);
        var actual = Map.<String, Object>of("latencia", 100, "nuevo_kpi", 42);
        var resultado = Ej358RegressionBaseline.compararConBaseline(actual, base, 0.10);
        assertTrue(resultado.stream().anyMatch(r -> r.severidad().equals("NUEVO")));
    }

    @Test
    void compararConBaseline_null() {
        assertEquals(List.of(), Ej358RegressionBaseline.compararConBaseline(null, Map.of(), 0.1));
    }

    @Test
    void guardarBaseline_ordenado() {
        var base = Map.<String, Object>of("z", 9, "a", 1, "m", 5);
        String resultado = Ej358RegressionBaseline.guardarBaseline(base);
        String[] lineas = resultado.split("\n");
        assertEquals("a=1", lineas[0]);
        assertEquals("m=5", lineas[1]);
        assertEquals("z=9", lineas[2]);
    }

    @Test
    void guardarBaseline_null() {
        assertEquals("", Ej358RegressionBaseline.guardarBaseline(null));
    }

    @Test
    void retoExtra01_verificarSnapshot() {
        assertTrue(Ej358RegressionBaseline.verificarSnapshot("abc", "abc"));
        assertFalse(Ej358RegressionBaseline.verificarSnapshot("abc", "xyz"));
        assertTrue(Ej358RegressionBaseline.verificarSnapshot(null, null));
        assertFalse(Ej358RegressionBaseline.verificarSnapshot("abc", null));
    }

    @Test
    void retoExtra02_compararConToleranciasPorMetrica() {
        var base = Map.<String, Object>of("latencia", 100, "errores", 0);
        var actual = Map.<String, Object>of("latencia", 115, "errores", 0); // 15 % de latencia
        var tolerancias = Map.of("latencia", 0.20); // 20 % para latencia
        var resultado = Ej358RegressionBaseline.compararConToleranciasPorMetrica(actual, base, tolerancias, 0.05);
        assertTrue(resultado.isEmpty()); // 15 % < 20 % → no hay regresión
    }

    @Test
    void retoExtra03_filtrarAprobadas() {
        var regresiones = List.of(
                new Ej358RegressionBaseline.Regresion("latencia", 100, 130, "ALTO"),
                new Ej358RegressionBaseline.Regresion("errores", 0, 1, "ALTO")
        );
        var resultado = Ej358RegressionBaseline.filtrarAprobadas(regresiones, Set.of("latencia"));
        assertEquals(1, resultado.size());
        assertEquals("errores", resultado.get(0).metrica());
    }

    @Test
    void retoExtra04_diffBaseline() {
        var diff = Ej358RegressionBaseline.diffBaseline("a=1\nb=2", "a=1\nc=3");
        assertTrue(diff.stream().anyMatch(l -> l.startsWith("+")));
        assertTrue(diff.stream().anyMatch(l -> l.startsWith("-")));
    }

    @Test
    void retoExtra05_comandoGitBaseline() {
        String cmd = Ej358RegressionBaseline.comandoGitBaseline("v1.2");
        assertTrue(cmd.contains("baseline.txt"));
        assertTrue(cmd.contains("v1.2"));
        assertEquals("", Ej358RegressionBaseline.comandoGitBaseline(null));
    }

    @Test
    void retoExtra06_hayRegresionLatencia() {
        assertTrue(Ej358RegressionBaseline.hayRegresionLatencia(100, 151, 50)); // 151 > 150
        assertFalse(Ej358RegressionBaseline.hayRegresionLatencia(100, 150, 50)); // exactamente igual → no
        assertFalse(Ej358RegressionBaseline.hayRegresionLatencia(100, 120, 50));
    }

    @Test
    void retoExtra07_estadoAprobacion() {
        assertEquals("APROBADO", Ej358RegressionBaseline.estadoAprobacion(null));
        assertEquals("APROBADO", Ej358RegressionBaseline.estadoAprobacion(List.of()));
        assertEquals("PENDIENTE_REVISION", Ej358RegressionBaseline.estadoAprobacion(List.of("+ nueva_linea")));
    }

    @Test
    void retoExtra08_generarConSeed() {
        var l1 = Ej358RegressionBaseline.generarConSeed(42L, 5);
        var l2 = Ej358RegressionBaseline.generarConSeed(42L, 5);
        assertEquals(l1, l2); // reproducible
        assertEquals(5, l1.size());
        assertEquals(List.of(), Ej358RegressionBaseline.generarConSeed(42L, 0));
    }

    @Test
    void retoExtra09_deltaPorcentual() {
        assertEquals(10.0, Ej358RegressionBaseline.deltaPorcentual(100.0, 110.0), 0.01);
        assertEquals(0.0, Ej358RegressionBaseline.deltaPorcentual(0.0, 100.0), 0.01);
        assertEquals(-10.0, Ej358RegressionBaseline.deltaPorcentual(100.0, 90.0), 0.01);
    }

    @Test
    void retoExtra10_informeCI() {
        var regresiones = List.of(
                new Ej358RegressionBaseline.Regresion("lat", 100, 130, "ALTO")
        );
        String informe = Ej358RegressionBaseline.informeCI(regresiones, "http://ci/baseline");
        assertTrue(informe.contains("REGRESION DETECTADA"));
        assertEquals("SIN_REGRESION", Ej358RegressionBaseline.informeCI(List.of(), null));
    }
}
