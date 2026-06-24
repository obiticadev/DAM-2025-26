package com.masterclass.api.b44_nui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej344MlForUiPipelineTest {

    @Test
    void normalizarFeatures() {
        assertArrayEquals(new double[]{0.5},
                Ej344MlForUiPipeline.normalizarFeatures(new double[]{5}, new double[]{0}, new double[]{10}), 1e-9);
        assertNull(Ej344MlForUiPipeline.normalizarFeatures(
                new double[]{5}, new double[]{0}, new double[]{10, 20})); // caso límite: longitudes distintas
    }

    @Test
    void predecir() {
        // z = 1+1-1 = 1 -> sigmoide(1) ~ 0.73 >= 0.5 -> 1
        assertEquals(1, Ej344MlForUiPipeline.predecir(new double[]{1, 1}, new double[]{1, 1}, -1));
        // z = -1 -> sigmoide ~ 0.27 < 0.5 -> 0
        assertEquals(0, Ej344MlForUiPipeline.predecir(new double[]{0, 0}, new double[]{1, 1}, -1));
        assertEquals(-1, Ej344MlForUiPipeline.predecir(new double[]{1}, new double[]{1, 1}, 0)); // caso límite
    }

    @Test
    void retoExtra01_productoPunto() {
        assertEquals(6.0, Ej344MlForUiPipeline.productoPunto(new double[]{1, 2, 3}, new double[]{1, 1, 1}), 1e-9);
        assertEquals(0.0, Ej344MlForUiPipeline.productoPunto(new double[]{}, new double[]{}), 1e-9); // caso límite
    }

    @Test
    void retoExtra02_sigmoide() {
        assertEquals(0.5, Ej344MlForUiPipeline.sigmoide(0), 1e-9);
        assertTrue(Ej344MlForUiPipeline.sigmoide(10) > 0.99); // tiende a 1
    }

    @Test
    void retoExtra03_clamp01() {
        assertEquals(1.0, Ej344MlForUiPipeline.clamp01(1.5), 1e-9);
        assertEquals(0.0, Ej344MlForUiPipeline.clamp01(-0.2), 1e-9);
    }

    @Test
    void retoExtra04_unHot() {
        assertArrayEquals(new double[]{0, 1, 0}, Ej344MlForUiPipeline.unHot(1, 3), 1e-9);
        assertArrayEquals(new double[]{0, 0, 0}, Ej344MlForUiPipeline.unHot(5, 3), 1e-9); // caso límite: fuera de rango
    }

    @Test
    void retoExtra05_precision() {
        assertEquals(0.8, Ej344MlForUiPipeline.precision(8, 2), 1e-9);
        assertEquals(0.0, Ej344MlForUiPipeline.precision(0, 0), 1e-9); // caso límite
    }

    @Test
    void retoExtra06_recall() {
        assertEquals(0.8, Ej344MlForUiPipeline.recall(8, 2), 1e-9);
        assertEquals(0.0, Ej344MlForUiPipeline.recall(0, 0), 1e-9); // caso límite
    }

    @Test
    void retoExtra07_matrizConfusion() {
        assertArrayEquals(new int[]{2, 1, 0, 1},
                Ej344MlForUiPipeline.matrizConfusion(new int[]{1, 0, 1, 0}, new int[]{1, 1, 1, 0}));
        assertArrayEquals(new int[0],
                Ej344MlForUiPipeline.matrizConfusion(new int[]{1}, new int[]{1, 0})); // caso límite
    }

    @Test
    void retoExtra08_exactitud() {
        assertEquals(0.9, Ej344MlForUiPipeline.exactitud(9, 10), 1e-9);
        assertEquals(0.0, Ej344MlForUiPipeline.exactitud(0, 0), 1e-9); // caso límite
    }

    @Test
    void retoExtra09_actualizarPeso() {
        assertEquals(0.7, Ej344MlForUiPipeline.actualizarPeso(0.5, 1.0, 2.0, 0.1), 1e-9);
        assertEquals(0.5, Ej344MlForUiPipeline.actualizarPeso(0.5, 1.0, 2.0, 0.0), 1e-9); // caso límite: tasa 0
    }

    @Test
    void retoExtra10_exportarPesos() {
        assertEquals("1.0,2.5", Ej344MlForUiPipeline.exportarPesos(new double[]{1.0, 2.5}));
        assertEquals("", Ej344MlForUiPipeline.exportarPesos(new double[]{})); // caso límite
    }
}
