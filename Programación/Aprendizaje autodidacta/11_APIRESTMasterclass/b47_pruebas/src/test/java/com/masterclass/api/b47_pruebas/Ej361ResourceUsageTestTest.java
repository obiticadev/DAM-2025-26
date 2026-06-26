package com.masterclass.api.b47_pruebas;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class Ej361ResourceUsageTestTest {

    @Test
    void presupuestoMemoria_dentroDelLimite() {
        assertTrue(Ej361ResourceUsageTest.presupuestoMemoria(1000L, 1500L, 1000L));
    }

    @Test
    void presupuestoMemoria_fueraDelLimite() {
        assertFalse(Ej361ResourceUsageTest.presupuestoMemoria(1000L, 2500L, 1000L));
    }

    @Test
    void presupuestoMemoria_deltaNegatico_esOk() {
        // GC liberó memoria entre mediciones → delta < 0 → tratado como 0 → dentro del límite
        assertTrue(Ej361ResourceUsageTest.presupuestoMemoria(2000L, 1000L, 0L));
    }

    @Test
    void presupuestoMemoria_limiteNegativo() {
        assertFalse(Ej361ResourceUsageTest.presupuestoMemoria(1000L, 1001L, -1L));
    }

    @Test
    void detectarFugaHilos_hayFuga() {
        assertTrue(Ej361ResourceUsageTest.detectarFugaHilos(5, 10, 2)); // 10 > 5+2=7
    }

    @Test
    void detectarFugaHilos_sinFuga() {
        assertFalse(Ej361ResourceUsageTest.detectarFugaHilos(5, 7, 2)); // exactamente 5+2=7 → NO fuga
    }

    @Test
    void hilosActivos_positivo() {
        assertTrue(Ej361ResourceUsageTest.hilosActivos() > 0);
    }

    @Test
    void memoriaUsadaBytes_positivo() {
        assertTrue(Ej361ResourceUsageTest.memoriaUsadaBytes() > 0);
    }

    @Test
    void retoExtra01_bytesPorOperacion() {
        assertEquals(100.0, Ej361ResourceUsageTest.bytesPorOperacion(1000L, 2000L, 10), 0.01);
        assertEquals(0.0, Ej361ResourceUsageTest.bytesPorOperacion(2000L, 1000L, 10), 0.01); // GC → 0
        assertEquals(-1, Ej361ResourceUsageTest.bytesPorOperacion(1000L, 2000L, 0), 0.01);
    }

    @Test
    void retoExtra02_simularMemoryLeak() {
        assertEquals(100, Ej361ResourceUsageTest.simularMemoryLeak(100));
        assertEquals(0, Ej361ResourceUsageTest.simularMemoryLeak(0));
        assertEquals(0, Ej361ResourceUsageTest.simularMemoryLeak(-5));
    }

    @Test
    void retoExtra03_cierreConExcepcion() {
        AutoCloseable lanzaExcepcion = () -> { throw new Exception("cierre fallido"); };
        assertTrue(Ej361ResourceUsageTest.cierreConExcepcion(lanzaExcepcion));
        AutoCloseable cerradoOk = () -> {};
        assertFalse(Ej361ResourceUsageTest.cierreConExcepcion(cerradoOk));
        assertFalse(Ej361ResourceUsageTest.cierreConExcepcion(null));
    }

    @Test
    void retoExtra04_ejecutorTerminado() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        assertTrue(Ej361ResourceUsageTest.ejecutorTerminado(es, 1000));
        assertFalse(Ej361ResourceUsageTest.ejecutorTerminado(null, 1000));
    }

    @Test
    void retoExtra05_pctDescriptoresUsados() {
        assertEquals(50.0, Ej361ResourceUsageTest.pctDescriptoresUsados(500, 1000), 0.01);
        assertEquals(-1, Ej361ResourceUsageTest.pctDescriptoresUsados(500, 0), 0.01);
        assertTrue(Ej361ResourceUsageTest.pctDescriptoresUsados(1500, 1000) > 100); // violación
    }

    @Test
    void retoExtra06_alertaOffHeap() {
        assertFalse(Ej361ResourceUsageTest.alertaOffHeap(800L, 1000L)); // exactamente 80 % → false
        assertTrue(Ej361ResourceUsageTest.alertaOffHeap(801L, 1000L));
        assertFalse(Ej361ResourceUsageTest.alertaOffHeap(100L, 0L)); // límite <= 0 → false
    }

    @Test
    void retoExtra07_stackTotalMB() {
        assertEquals(0.5, Ej361ResourceUsageTest.stackTotalMB(512, 1), 0.001); // 512/1024 = 0.5
        assertEquals(-1, Ej361ResourceUsageTest.stackTotalMB(0, 10), 0.001);
        assertEquals(-1, Ej361ResourceUsageTest.stackTotalMB(512, 0), 0.001);
    }

    @Test
    void retoExtra08_nucleosEnUso() {
        assertEquals(4.0, Ej361ResourceUsageTest.nucleosEnUso(0.5, 8), 0.01);
        assertEquals(0.0, Ej361ResourceUsageTest.nucleosEnUso(0.0, 8), 0.01); // 0 % válido
        assertEquals(-1, Ej361ResourceUsageTest.nucleosEnUso(1.1, 8), 0.01);
        assertEquals(-1, Ej361ResourceUsageTest.nucleosEnUso(0.5, 0), 0.01);
    }

    @Test
    void retoExtra09_fugaLenta() {
        assertTrue(Ej361ResourceUsageTest.fugaLenta(List.of(100L, 110L, 120L, 200L), 50L)); // 200-100=100>50
        assertFalse(Ej361ResourceUsageTest.fugaLenta(List.of(100L, 120L), 50L)); // 120-100=20<=50
        assertFalse(Ej361ResourceUsageTest.fugaLenta(null, 50L));
        assertFalse(Ej361ResourceUsageTest.fugaLenta(List.of(100L), 50L));
    }

    @Test
    void retoExtra10_estadoSalud() {
        assertEquals("OK", Ej361ResourceUsageTest.estadoSalud(false, false));
        assertEquals("MEMORIA", Ej361ResourceUsageTest.estadoSalud(true, false));
        assertEquals("HILOS", Ej361ResourceUsageTest.estadoSalud(false, true));
        assertEquals("CRITICO", Ej361ResourceUsageTest.estadoSalud(true, true));
    }
}
