package com.masterclass.api.b37_fxcustom;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej294SkinnableControlTest {

    private static final double D = 1e-9;

    @Test
    void valorAcotado() {
        assertEquals(100.0, Ej294SkinnableControl.valorAcotado(150, 0, 100), D);
        assertEquals(0.0, Ej294SkinnableControl.valorAcotado(-5, 0, 100), D); // caso límite (por abajo)
        assertEquals(42.0, Ej294SkinnableControl.valorAcotado(42, 0, 100), D);
    }

    @Test
    void estrellasLlenas() {
        assertEquals(3, Ej294SkinnableControl.estrellasLlenas(3, 5));
        assertEquals(5, Ej294SkinnableControl.estrellasLlenas(9, 5)); // caso límite (por arriba)
        assertEquals(0, Ej294SkinnableControl.estrellasLlenas(-1, 5));
    }

    @Test
    void retoExtra01_simboloEstrella() {
        assertEquals("★", Ej294SkinnableControl.simboloEstrella(true));
        assertEquals("☆", Ej294SkinnableControl.simboloEstrella(false));
    }

    @Test
    void retoExtra02_textoValoracion() {
        assertEquals("3 de 5", Ej294SkinnableControl.textoValoracion(3, 5));
    }

    @Test
    void retoExtra03_fraccionDeRango() {
        assertEquals(0.25, Ej294SkinnableControl.fraccionDeRango(25, 0, 100), D);
        assertEquals(0.0, Ej294SkinnableControl.fraccionDeRango(5, 5, 5), D); // caso límite: rango degenerado
    }

    @Test
    void retoExtra04_anguloAguja() {
        assertEquals(135.0, Ej294SkinnableControl.anguloAguja(0.5), D);
        assertEquals(270.0, Ej294SkinnableControl.anguloAguja(1.0), D);
    }

    @Test
    void retoExtra05_colorPorZona() {
        assertEquals("verde", Ej294SkinnableControl.colorPorZona(0.3));
        assertEquals("ambar", Ej294SkinnableControl.colorPorZona(0.6));
        assertEquals("rojo", Ej294SkinnableControl.colorPorZona(0.9));
    }

    @Test
    void retoExtra06_acotarEntero() {
        assertEquals(10, Ej294SkinnableControl.acotarEntero(12, 1, 10));
        assertEquals(1, Ej294SkinnableControl.acotarEntero(0, 1, 10)); // caso límite
    }

    @Test
    void retoExtra07_incrementar() {
        assertEquals(4, Ej294SkinnableControl.incrementar(3, 5));
        assertEquals(5, Ej294SkinnableControl.incrementar(5, 5)); // caso límite: ya en el tope
    }

    @Test
    void retoExtra08_posicionesMarcas() {
        List<Double> marcas = Ej294SkinnableControl.posicionesMarcas(3);
        assertEquals(3, marcas.size());
        assertEquals(0.0, marcas.get(0), D);
        assertEquals(0.5, marcas.get(1), D);
        assertEquals(1.0, marcas.get(2), D);
        assertEquals(List.of(0.0), Ej294SkinnableControl.posicionesMarcas(1)); // caso límite
    }

    @Test
    void retoExtra09_pseudoEstado() {
        assertEquals("vacio", Ej294SkinnableControl.pseudoEstado(0.0));
        assertEquals("lleno", Ej294SkinnableControl.pseudoEstado(1.0));
        assertEquals("parcial", Ej294SkinnableControl.pseudoEstado(0.4));
    }

    @Test
    void retoExtra10_serializarEstado() {
        assertEquals("3/5", Ej294SkinnableControl.serializarEstado(3, 5));
        assertEquals("5/5", Ej294SkinnableControl.serializarEstado(7, 5)); // caso límite: acota al serializar
    }
}
