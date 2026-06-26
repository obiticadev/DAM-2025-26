package com.masterclass.api.b47_pruebas;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej359LoadVolumeTestTest {

    @Test
    void medirThroughput_basico() {
        int[] c = {0};
        double ops = Ej359LoadVolumeTest.medirThroughput(() -> c[0]++, 100, 10);
        assertTrue(ops > 0);
    }

    @Test
    void medirThroughput_parametrosInvalidos() {
        assertEquals(-1, Ej359LoadVolumeTest.medirThroughput(null, 100, 10), 0.01);
        assertEquals(-1, Ej359LoadVolumeTest.medirThroughput(() -> {}, 0, 0), 0.01);
        assertEquals(-1, Ej359LoadVolumeTest.medirThroughput(() -> {}, 5, 5), 0.01); // warmup >= n
    }

    @Test
    void generarCarga_basico() {
        var peticiones = Ej359LoadVolumeTest.generarCarga(
                () -> new Ej359LoadVolumeTest.Peticion("/api", "GET"), 10);
        assertEquals(10, peticiones.size());
    }

    @Test
    void generarCarga_omiteNull() {
        int[] i = {0};
        var peticiones = Ej359LoadVolumeTest.generarCarga(
                () -> (i[0]++ % 2 == 0) ? new Ej359LoadVolumeTest.Peticion("/a", "GET") : null, 4);
        assertEquals(2, peticiones.size());
    }

    @Test
    void generarCarga_parametrosInvalidos() {
        assertEquals(List.of(), Ej359LoadVolumeTest.generarCarga(null, 5));
        assertEquals(List.of(), Ej359LoadVolumeTest.generarCarga(() -> null, 0));
    }

    @Test
    void retoExtra01_rampUp() {
        var niveles = Ej359LoadVolumeTest.rampUp(10, 10, 5);
        assertEquals(5, niveles.size());
        assertEquals(10, niveles.get(0));
        assertEquals(50, niveles.get(4));
        assertEquals(List.of(), Ej359LoadVolumeTest.rampUp(0, 10, 5));
    }

    @Test
    void retoExtra02_clasificarCarga() {
        assertEquals("SOSTENIDA", Ej359LoadVolumeTest.clasificarCarga(100, 100));
        assertEquals("PICO", Ej359LoadVolumeTest.clasificarCarga(150, 100));
        assertEquals("PICO", Ej359LoadVolumeTest.clasificarCarga(200, 100)); // exactamente 200 = base*2
        assertEquals("SOBRECARGA", Ej359LoadVolumeTest.clasificarCarga(201, 100));
    }

    @Test
    void retoExtra03_ejecutarEnParalelo() {
        var tareas = List.<Runnable>of(() -> {}, () -> {}, () -> {});
        int completadas = Ej359LoadVolumeTest.ejecutarEnParalelo(tareas, 2);
        assertEquals(3, completadas);
    }

    @Test
    void retoExtra04_aplicarBackPressure() {
        var tareas = List.<Runnable>of(() -> {}, () -> {}, () -> {}, () -> {}, () -> {});
        int[] resultado = Ej359LoadVolumeTest.aplicarBackPressure(tareas, 3);
        assertEquals(3, resultado[0]);
        assertEquals(2, resultado[1]);
    }

    @Test
    void retoExtra05_generarDataset() {
        var ds = Ej359LoadVolumeTest.generarDataset(5);
        assertEquals(5, ds.size());
        assertEquals("item_0", ds.get(0));
        assertEquals(List.of(), Ej359LoadVolumeTest.generarDataset(0));
    }

    @Test
    void retoExtra06_operacionesEnVuelo() {
        assertEquals(1.0, Ej359LoadVolumeTest.operacionesEnVuelo(100, 10), 0.01); // 100 * 10/1000
        assertEquals(-1, Ej359LoadVolumeTest.operacionesEnVuelo(0, 10), 0.01);
        assertEquals(0.0, Ej359LoadVolumeTest.operacionesEnVuelo(100, 0), 0.01); // latencia 0 OK
    }

    @Test
    void retoExtra07_pasosJMH() {
        var pasos = Ej359LoadVolumeTest.pasosJMH();
        assertEquals(5, pasos.size());
        assertEquals("@BenchmarkMode", pasos.get(0));
        assertEquals("@Benchmark", pasos.get(4));
    }

    @Test
    void retoExtra08_gcPorSegundo() {
        assertEquals(6.0, Ej359LoadVolumeTest.gcPorSegundo(0, 3, 500), 0.01); // 3/(500/1000)=6
        assertEquals(-1, Ej359LoadVolumeTest.gcPorSegundo(0, 3, 0), 0.01);
        assertEquals(0.0, Ej359LoadVolumeTest.gcPorSegundo(5, 3, 1000), 0.01); // delta negativo → 0
    }

    @Test
    void retoExtra09_enSteadyState() {
        var muestras = List.of(100.0, 101.0, 100.5, 99.5, 100.2);
        assertTrue(Ej359LoadVolumeTest.enSteadyState(muestras, 3, 5.0));
        var inestable = List.of(100.0, 200.0, 50.0);
        assertFalse(Ej359LoadVolumeTest.enSteadyState(inestable, 3, 5.0));
        assertFalse(Ej359LoadVolumeTest.enSteadyState(List.of(100.0, 200.0), 3, 5.0)); // menos de ventana
    }

    @Test
    void retoExtra10_scoreServicio() {
        assertEquals(90.0, Ej359LoadVolumeTest.scoreServicio(100, 100, 0.90), 0.01); // 1.0 * 0.90 * 100
        assertEquals(-1, Ej359LoadVolumeTest.scoreServicio(0, 100, 0.90), 0.01);
        assertEquals(-1, Ej359LoadVolumeTest.scoreServicio(100, 100, 1.1), 0.01);
    }
}
