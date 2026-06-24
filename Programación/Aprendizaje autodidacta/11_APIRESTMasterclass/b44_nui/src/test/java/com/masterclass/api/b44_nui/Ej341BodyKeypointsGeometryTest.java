package com.masterclass.api.b44_nui;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej341BodyKeypointsGeometryTest {

    @Test
    void anguloArticulacion() {
        // BA=(0,1), BC=(1,0): perpendiculares -> 90°
        assertEquals(90.0,
                Ej341BodyKeypointsGeometry.anguloArticulacion(new Punto(0, 1), new Punto(0, 0), new Punto(1, 0)),
                1e-9);
        // segmento degenerado (a==b) -> -1
        assertEquals(-1.0,
                Ej341BodyKeypointsGeometry.anguloArticulacion(new Punto(0, 0), new Punto(0, 0), new Punto(1, 0)),
                1e-9); // caso límite
    }

    @Test
    void distanciaArticulaciones() {
        assertEquals(5.0, Ej341BodyKeypointsGeometry.distanciaArticulaciones(
                Map.of("hombro", new Punto(0, 0), "mano", new Punto(0, 5)), "hombro", "mano"), 1e-9);
        assertEquals(-1.0, Ej341BodyKeypointsGeometry.distanciaArticulaciones(
                Map.of("hombro", new Punto(0, 0)), "hombro", "mano"), 1e-9); // caso límite: keypoint ausente
    }

    @Test
    void retoExtra01_esSimetrico() {
        assertTrue(Ej341BodyKeypointsGeometry.esSimetrico(90, 92, 5));
        assertFalse(Ej341BodyKeypointsGeometry.esSimetrico(90, 100, 5));
    }

    @Test
    void retoExtra02_centroDeMasa() {
        assertEquals(new Punto(1, 1),
                Ej341BodyKeypointsGeometry.centroDeMasa(List.of(new Punto(0, 0), new Punto(2, 2))));
        assertEquals(new Punto(0, 0), Ej341BodyKeypointsGeometry.centroDeMasa(List.of())); // caso límite
    }

    @Test
    void retoExtra03_contarRepeticiones() {
        assertEquals(2, Ej341BodyKeypointsGeometry.contarRepeticiones(
                List.of(170.0, 90.0, 170.0, 90.0, 170.0), 100, 160));
        assertEquals(0, Ej341BodyKeypointsGeometry.contarRepeticiones(List.of(), 100, 160)); // caso límite
    }

    @Test
    void retoExtra04_detectaCaida() {
        assertTrue(Ej341BodyKeypointsGeometry.detectaCaida(0.3, 0.5));
        assertFalse(Ej341BodyKeypointsGeometry.detectaCaida(0.8, 0.5));
    }

    @Test
    void retoExtra05_filtrarJitter() {
        assertEquals(90.0, Ej341BodyKeypointsGeometry.filtrarJitter(100, 90, 5), 1e-9); // salto grande -> previo
        assertEquals(92.0, Ej341BodyKeypointsGeometry.filtrarJitter(92, 90, 5), 1e-9);
    }

    @Test
    void retoExtra06_confianzaPromedio() {
        assertEquals(0.7, Ej341BodyKeypointsGeometry.confianzaPromedio(List.of(0.8, 0.6)), 1e-9);
        assertEquals(0.0, Ej341BodyKeypointsGeometry.confianzaPromedio(List.of()), 1e-9); // caso límite
    }

    @Test
    void retoExtra07_hayOclusion() {
        assertTrue(Ej341BodyKeypointsGeometry.hayOclusion(
                Map.of("mano", new Punto(0, 0)), List.of("mano", "pie")));
        assertFalse(Ej341BodyKeypointsGeometry.hayOclusion(
                Map.of("mano", new Punto(0, 0), "pie", new Punto(1, 1)), List.of("mano")));
    }

    @Test
    void retoExtra08_normalizarPorAltura() {
        assertEquals(0.5, Ej341BodyKeypointsGeometry.normalizarPorAltura(50, 100), 1e-9);
        assertEquals(0.0, Ej341BodyKeypointsGeometry.normalizarPorAltura(50, 0), 1e-9); // caso límite
    }

    @Test
    void retoExtra09_parecidoPosturas() {
        assertEquals(0.0, Ej341BodyKeypointsGeometry.parecidoPosturas(
                List.of(1.0, 2.0, 3.0), List.of(1.0, 2.0, 3.0)), 1e-9);
        assertEquals(1.0, Ej341BodyKeypointsGeometry.parecidoPosturas(
                List.of(1.0, 2.0), List.of(2.0, 2.0)), 1e-9);
        assertEquals(-1.0, Ej341BodyKeypointsGeometry.parecidoPosturas(
                List.of(1.0), List.of(1.0, 2.0)), 1e-9); // caso límite: tamaños distintos
    }

    @Test
    void retoExtra10_esEsqueletoCompleto() {
        Map<String, Punto> kps = Map.of("a", new Punto(0, 0), "b", new Punto(1, 1));
        assertTrue(Ej341BodyKeypointsGeometry.esEsqueletoCompleto(kps, List.<String[]>of(new String[]{"a", "b"})));
        assertFalse(Ej341BodyKeypointsGeometry.esEsqueletoCompleto(kps, List.<String[]>of(new String[]{"a", "c"})));
    }
}
