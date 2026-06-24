package com.masterclass.api.b42_mobile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej329SensorsModelTest {

    private static final double D = 1e-9;

    @Test
    void filtrarLecturaSensor() {
        assertArrayEquals(new double[]{2, 3, 5},
                Ej329SensorsModel.filtrarLecturaSensor(new double[]{2, 4, 6}, 2), D);
        assertArrayEquals(new double[0],
                Ej329SensorsModel.filtrarLecturaSensor(new double[0], 2), D); // caso límite: señal vacía
    }

    @Test
    void magnitud() {
        assertEquals(5.0, Ej329SensorsModel.magnitud(3, 4, 0), D);
        assertEquals(0.0, Ej329SensorsModel.magnitud(0, 0, 0), D); // caso límite: vector nulo
    }

    @Test
    void retoExtra01_media() {
        assertEquals(4.0, Ej329SensorsModel.media(new double[]{2, 4, 6}), D);
        assertEquals(0.0, Ej329SensorsModel.media(new double[0]), D);
    }

    @Test
    void retoExtra02_superaUmbral() {
        assertTrue(Ej329SensorsModel.superaUmbral(12, 9.8));
        assertFalse(Ej329SensorsModel.superaUmbral(5, 9.8));
        assertTrue(Ej329SensorsModel.superaUmbral(-12, 9.8)); // valor absoluto
    }

    @Test
    void retoExtra03_filtroPasoBajo() {
        assertEquals(5.0, Ej329SensorsModel.filtroPasoBajo(0, 10, 0.5), D);
        assertEquals(10.0, Ej329SensorsModel.filtroPasoBajo(0, 10, 1.0), D);
    }

    @Test
    void retoExtra04_aceleracionLineal() {
        assertEquals(0.0, Ej329SensorsModel.aceleracionLineal(9.81), D);
    }

    @Test
    void retoExtra05_enReposo() {
        assertTrue(Ej329SensorsModel.enReposo(9.81, 0.1));
        assertFalse(Ej329SensorsModel.enReposo(12.0, 0.1));
    }

    @Test
    void retoExtra06_lecturaValida() {
        assertTrue(Ej329SensorsModel.lecturaValida(50, 0, 100));
        assertFalse(Ej329SensorsModel.lecturaValida(150, 0, 100));
    }

    @Test
    void retoExtra07_normalizar() {
        assertEquals(0.5, Ej329SensorsModel.normalizar(50, 100), D);
        assertEquals(1.0, Ej329SensorsModel.normalizar(150, 100), D); // se satura
    }

    @Test
    void retoExtra08_contarPicos() {
        assertEquals(2, Ej329SensorsModel.contarPicos(new double[]{0, 12, 0, 11, 0}, 9.8));
        assertEquals(0, Ej329SensorsModel.contarPicos(new double[]{0, 1}, 9.8)); // caso límite: < 3 muestras
    }

    @Test
    void retoExtra09_gradosARadianes() {
        assertEquals(Math.PI, Ej329SensorsModel.gradosARadianes(180), D);
    }

    @Test
    void retoExtra10_ejeDominante() {
        assertEquals("Y", Ej329SensorsModel.ejeDominante(1, 9.8, 2));
        assertEquals("Z", Ej329SensorsModel.ejeDominante(1, 2, 9.8));
    }
}
