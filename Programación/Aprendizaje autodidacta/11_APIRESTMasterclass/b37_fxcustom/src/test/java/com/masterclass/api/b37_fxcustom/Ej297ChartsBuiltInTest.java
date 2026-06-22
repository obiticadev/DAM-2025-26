package com.masterclass.api.b37_fxcustom;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej297ChartsBuiltInTest {

    private static final double D = 1e-9;

    @Test
    void serieXY() {
        List<double[]> s = Ej297ChartsBuiltIn.serieXY(new double[]{10, 20, 30});
        assertEquals(3, s.size());
        assertArrayEquals(new double[]{1, 10}, s.get(0), D); // x empieza en 1
        assertArrayEquals(new double[]{2, 20}, s.get(1), D);
        assertArrayEquals(new double[]{3, 30}, s.get(2), D);
        assertTrue(Ej297ChartsBuiltIn.serieXY(new double[]{}).isEmpty()); // caso límite
    }

    @Test
    void porcentajesPie() {
        List<Double> p = Ej297ChartsBuiltIn.porcentajesPie(new double[]{25, 25, 50});
        assertEquals(25.0, p.get(0), D);
        assertEquals(25.0, p.get(1), D);
        assertEquals(50.0, p.get(2), D);
        assertTrue(Ej297ChartsBuiltIn.porcentajesPie(new double[]{0, 0}).isEmpty()); // total 0 -> vacío
    }

    @Test
    void retoExtra01_etiquetaCategoria() {
        assertEquals("Cat 1", Ej297ChartsBuiltIn.etiquetaCategoria(1));
    }

    @Test
    void retoExtra02_maximoSerie() {
        assertEquals(9.0, Ej297ChartsBuiltIn.maximoSerie(new double[]{3, 9, 5}), D);
        assertEquals(0.0, Ej297ChartsBuiltIn.maximoSerie(new double[]{}), D); // caso límite
    }

    @Test
    void retoExtra03_mediaSerie() {
        assertEquals(4.0, Ej297ChartsBuiltIn.mediaSerie(new double[]{2, 4, 6}), D);
        assertEquals(0.0, Ej297ChartsBuiltIn.mediaSerie(new double[]{}), D); // caso límite
    }

    @Test
    void retoExtra04_acumulado() {
        List<Double> a = Ej297ChartsBuiltIn.acumulado(new double[]{1, 2, 3});
        assertEquals(List.of(1.0, 3.0, 6.0), a);
    }

    @Test
    void retoExtra05_normalizar0a100() {
        List<Double> n = Ej297ChartsBuiltIn.normalizar0a100(new double[]{5, 10});
        assertEquals(50.0, n.get(0), D);
        assertEquals(100.0, n.get(1), D);
    }

    @Test
    void retoExtra06_agruparPorCategoria() {
        Map<String, Double> m = Ej297ChartsBuiltIn.agruparPorCategoria(
                List.of("A", "B", "A"), new double[]{10, 5, 20});
        assertEquals(30.0, m.get("A"), D);
        assertEquals(5.0, m.get("B"), D);
        assertEquals(2, m.size());
    }

    @Test
    void retoExtra07_rangoEjeY() {
        assertArrayEquals(new double[]{0, 80}, Ej297ChartsBuiltIn.rangoEjeY(new double[]{73}, 10), D);
        assertArrayEquals(new double[]{0, 50}, Ej297ChartsBuiltIn.rangoEjeY(new double[]{50}, 10), D); // ya múltiplo
    }

    @Test
    void retoExtra08_tendencia() {
        assertEquals("sube", Ej297ChartsBuiltIn.tendencia(new double[]{1, 5}));
        assertEquals("baja", Ej297ChartsBuiltIn.tendencia(new double[]{5, 1}));
        assertEquals("estable", Ej297ChartsBuiltIn.tendencia(new double[]{3, 3}));
    }

    @Test
    void retoExtra09_topN() {
        Map<String, Double> datos = Ej297ChartsBuiltIn.datos(
                new String[]{"A", "B", "C"}, new double[]{10, 30, 20});
        assertEquals(List.of("B", "C"), Ej297ChartsBuiltIn.topN(datos, 2));
    }

    @Test
    void retoExtra10_apiladoTotal() {
        List<Double> t = Ej297ChartsBuiltIn.apiladoTotal(new double[]{1, 2, 3}, new double[]{10, 20});
        assertEquals(List.of(11.0, 22.0), t); // se para en el más corto
    }
}
