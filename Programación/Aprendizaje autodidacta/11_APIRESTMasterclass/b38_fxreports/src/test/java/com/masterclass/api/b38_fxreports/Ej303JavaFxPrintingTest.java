package com.masterclass.api.b38_fxreports;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej303JavaFxPrintingTest {

    private static final double D = 1e-6;

    @Test
    void escalaParaCaber() {
        assertEquals(0.25, Ej303JavaFxPrinting.escalaParaCaber(200, 400, 100, 100), D); // el alto manda
        assertEquals(1.0, Ej303JavaFxPrinting.escalaParaCaber(50, 50, 100, 100), D);    // ya cabe: no agranda
        assertEquals(0.0, Ej303JavaFxPrinting.escalaParaCaber(0, 400, 100, 100), D);    // caso límite: nodo sin ancho
    }

    @Test
    void paginasNecesarias() {
        assertEquals(3, Ej303JavaFxPrinting.paginasNecesarias(250, 100)); // 2.5 -> 3
        assertEquals(1, Ej303JavaFxPrinting.paginasNecesarias(100, 100));
        assertEquals(1, Ej303JavaFxPrinting.paginasNecesarias(0, 100)); // caso límite: siempre >= 1
    }

    @Test
    void areaImprimible() {
        assertArrayEquals(new double[]{500, 700}, Ej303JavaFxPrinting.areaImprimible(600, 800, 50), D);
        assertArrayEquals(new double[]{0, 0}, Ej303JavaFxPrinting.areaImprimible(100, 100, 60), D); // margen excesivo
    }

    @Test
    void retoExtra01_cabeEnPagina() {
        assertTrue(Ej303JavaFxPrinting.cabeEnPagina(50, 50, 100, 100));
        assertFalse(Ej303JavaFxPrinting.cabeEnPagina(200, 50, 100, 100)); // se sale de ancho
    }

    @Test
    void retoExtra02_orientacion() {
        assertEquals("horizontal", Ej303JavaFxPrinting.orientacion(800, 600));
        assertEquals("vertical", Ej303JavaFxPrinting.orientacion(600, 800));
        assertEquals("cuadrado", Ej303JavaFxPrinting.orientacion(500, 500));
    }

    @Test
    void retoExtra03_escalaPorcentaje() {
        assertEquals(25, Ej303JavaFxPrinting.escalaPorcentaje(0.25));
        assertEquals(100, Ej303JavaFxPrinting.escalaPorcentaje(1.0));
    }

    @Test
    void retoExtra04_centrarX() {
        assertEquals(100.0, Ej303JavaFxPrinting.centrarX(100, 300), D);
    }

    @Test
    void retoExtra05_mmAPuntos() {
        assertEquals(72.0, Ej303JavaFxPrinting.mmAPuntos(25.4), D);
    }

    @Test
    void retoExtra06_puntosAMm() {
        assertEquals(25.4, Ej303JavaFxPrinting.puntosAMm(72), D);
    }

    @Test
    void retoExtra07_dimensionEscalada() {
        assertArrayEquals(new double[]{50, 100}, Ej303JavaFxPrinting.dimensionEscalada(200, 400, 0.25), D);
    }

    @Test
    void retoExtra08_margenDeducido() {
        assertEquals(50.0, Ej303JavaFxPrinting.margenDeducido(600, 500), D);
    }

    @Test
    void retoExtra09_paginasDeTabla() {
        assertEquals(3, Ej303JavaFxPrinting.paginasDeTabla(25, 10));
        assertEquals(2, Ej303JavaFxPrinting.paginasDeTabla(20, 10));
        assertEquals(0, Ej303JavaFxPrinting.paginasDeTabla(0, 10)); // caso límite
    }

    @Test
    void retoExtra10_rangoDePagina() {
        assertArrayEquals(new int[]{0, 10}, Ej303JavaFxPrinting.rangoDePagina(1, 10, 25));
        assertArrayEquals(new int[]{20, 25}, Ej303JavaFxPrinting.rangoDePagina(3, 10, 25)); // última hoja, 5 filas
    }
}
