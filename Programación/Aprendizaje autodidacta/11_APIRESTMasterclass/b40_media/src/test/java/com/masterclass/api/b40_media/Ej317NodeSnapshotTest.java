package com.masterclass.api.b40_media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej317NodeSnapshotTest {

    @Test
    void dimensionSnapshot() {
        assertArrayEquals(new int[]{200, 100}, Ej317NodeSnapshot.dimensionSnapshot(100, 50, 2.0));
        assertArrayEquals(new int[]{99, 99}, Ej317NodeSnapshot.dimensionSnapshot(99, 99, 1.0));
        assertArrayEquals(new int[]{0, 0}, Ej317NodeSnapshot.dimensionSnapshot(100, 50, 0)); // caso límite: escala inválida
    }

    @Test
    void indicePixel() {
        assertEquals(23, Ej317NodeSnapshot.indicePixel(2, 3, 10));
        assertEquals(0, Ej317NodeSnapshot.indicePixel(0, 0, 5)); // primer píxel
    }

    @Test
    void retoExtra01_bytesSnapshot() {
        assertEquals(80000L, Ej317NodeSnapshot.bytesSnapshot(200, 100));
    }

    @Test
    void retoExtra02_coordDesdeIndice() {
        assertArrayEquals(new int[]{2, 3}, Ej317NodeSnapshot.coordDesdeIndice(23, 10)); // inverso del core
    }

    @Test
    void retoExtra03_regionDentro() {
        assertTrue(Ej317NodeSnapshot.regionDentro(10, 10, 50, 50, 100, 100));
        assertFalse(Ej317NodeSnapshot.regionDentro(80, 80, 50, 50, 100, 100)); // se sale
    }

    @Test
    void retoExtra04_escalaParaResolucion() {
        assertEquals(2.0, Ej317NodeSnapshot.escalaParaResolucion(960, 1920), 1e-9);
        assertEquals(0.0, Ej317NodeSnapshot.escalaParaResolucion(0, 1920), 1e-9); // caso límite
    }

    @Test
    void retoExtra05_aHexColor() {
        assertEquals("#102030", Ej317NodeSnapshot.aHexColor(0xFF102030)); // el alfa se descarta
    }

    @Test
    void retoExtra06_desdeHexColor() {
        assertEquals(0xFF102030, Ej317NodeSnapshot.desdeHexColor("#102030")); // ahora opaco
    }

    @Test
    void retoExtra07_componerSobre() {
        assertEquals(0xFFAABBCC, Ej317NodeSnapshot.componerSobre(0xFFAABBCC, 0x102030)); // alfa 255: gana el frente
        assertEquals(0xFF102030, Ej317NodeSnapshot.componerSobre(0x00AABBCC, 0x102030)); // alfa 0: gana el fondo
    }

    @Test
    void retoExtra08_escalarManteniendo() {
        assertArrayEquals(new int[]{400, 300}, Ej317NodeSnapshot.escalarManteniendo(800, 600, 400));
    }

    @Test
    void retoExtra09_baldosasNecesarias() {
        assertArrayEquals(new int[]{3, 1}, Ej317NodeSnapshot.baldosasNecesarias(250, 100, 100)); // redondea hacia arriba
    }

    @Test
    void retoExtra10_milimetrosAPuntos() {
        assertEquals(72.0, Ej317NodeSnapshot.milimetrosAPuntos(25.4), 1e-9); // una pulgada
        assertEquals(0.0, Ej317NodeSnapshot.milimetrosAPuntos(0), 1e-9);
    }
}
