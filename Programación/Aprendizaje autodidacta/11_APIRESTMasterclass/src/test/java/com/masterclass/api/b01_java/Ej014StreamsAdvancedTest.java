package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej014StreamsAdvancedTest {

    @Test
    void aplanar() {
        assertEquals(List.of(1, 2, 3, 4),
                Ej014StreamsAdvanced.aplanar(List.of(List.of(1, 2), List.of(3), List.of(4))));
    }

    @Test
    void agruparPorInicial() {
        var m = Ej014StreamsAdvanced.agruparPorInicial(List.of("ana", "alba", "bea"));
        assertEquals(List.of("ana", "alba"), m.get('a'));
        assertEquals(List.of("bea"), m.get('b'));
    }

    @Test
    void unirConComas() {
        assertEquals("a, b, c", Ej014StreamsAdvanced.unirConComas(List.of("a", "b", "c")));
        assertEquals("", Ej014StreamsAdvanced.unirConComas(List.of()));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_agruparPorLongitud() {
        var res = Ej014StreamsAdvanced.agruparPorLongitud(List.of("a", "bb", "ccc", "dd"));
        assertEquals(List.of("a"), res.get(1));
        assertEquals(List.of("bb", "dd"), res.get(2));
        assertEquals(List.of("ccc"), res.get(3));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_particionarParesImpares() {
        var res = Ej014StreamsAdvanced.particionarParesImpares(List.of(1, 2, 3, 4));
        assertEquals(List.of(2, 4), res.get(true));
        assertEquals(List.of(1, 3), res.get(false));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_aplanarListas() {
        var res = Ej014StreamsAdvanced.aplanarListas(java.util.Arrays.asList(
            List.of("hello"),
            null,
            java.util.Arrays.asList("", "world")
        ));
        assertEquals(List.of("hello", "world"), res);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_contarOcurrencias() {
        var res = Ej014StreamsAdvanced.contarOcurrencias(List.of("a", "b", "a", "c", "b", "a"));
        assertEquals(3L, res.get("a"));
        assertEquals(2L, res.get("b"));
        assertEquals(1L, res.get("c"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_obtenerNombresConcatenadosPorGrupo() {
        var res = Ej014StreamsAdvanced.obtenerNombresConcatenadosPorGrupo(List.of("a", "bb", "ccc", "dd"));
        assertEquals("a", res.get(1));
        assertEquals("bb, dd", res.get(2));
        assertEquals("ccc", res.get(3));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_encontrarProductoMasCaroPorCategoria() {
        var p1 = new Ej014StreamsAdvanced.Producto("p1", "CatA", 10.0);
        var p2 = new Ej014StreamsAdvanced.Producto("p2", "CatA", 20.0);
        var p3 = new Ej014StreamsAdvanced.Producto("p3", "CatB", 15.0);
        
        var res = Ej014StreamsAdvanced.encontrarProductoMasCaroPorCategoria(List.of(p1, p2, p3));
        assertEquals(p2, res.get("CatA").orElseThrow());
        assertEquals(p3, res.get("CatB").orElseThrow());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_calcularPromedioPrecio() {
        var p1 = new Ej014StreamsAdvanced.Producto("p1", "CatA", 10.0);
        var p2 = new Ej014StreamsAdvanced.Producto("p2", "CatA", 20.0);
        var p3 = new Ej014StreamsAdvanced.Producto("p3", "CatB", 15.0);
        
        assertEquals(15.0, Ej014StreamsAdvanced.calcularPromedioPrecio(List.of(p1, p2, p3), "CatA"), 0.0001);
        assertEquals(0.0, Ej014StreamsAdvanced.calcularPromedioPrecio(List.of(p1, p2, p3), "CatC"), 0.0001);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_crearMapaDeValoresUnicos() {
        var p1 = new Ej014StreamsAdvanced.Producto("prod", "CatA", 10.0);
        var p2 = new Ej014StreamsAdvanced.Producto("prod", "CatB", 30.0);
        var p3 = new Ej014StreamsAdvanced.Producto("other", "CatB", 15.0);
        
        var res = Ej014StreamsAdvanced.crearMapaDeValoresUnicos(List.of(p1, p2, p3));
        assertEquals(2, res.size());
        assertEquals(p2, res.get("prod"));
        assertEquals(p3, res.get("other"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_generarEstadisticasPrecio() {
        var p1 = new Ej014StreamsAdvanced.Producto("p1", "CatA", 10.0);
        var p2 = new Ej014StreamsAdvanced.Producto("p2", "CatA", 20.0);
        
        var stats = Ej014StreamsAdvanced.generarEstadisticasPrecio(List.of(p1, p2));
        assertEquals(2, stats.getCount());
        assertEquals(15.0, stats.getAverage(), 0.0001);
        assertEquals(20.0, stats.getMax(), 0.0001);
        assertEquals(10.0, stats.getMin(), 0.0001);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_agruparYOrdenarResultados() {
        var p1 = new Ej014StreamsAdvanced.Producto("p1", "CatB", 10.0);
        var p2 = new Ej014StreamsAdvanced.Producto("p2", "CatA", 20.0);
        
        var res = Ej014StreamsAdvanced.agruparYOrdenarResultados(List.of(p1, p2));
        var keys = new java.util.ArrayList<>(res.keySet());
        assertEquals(List.of("CatA", "CatB"), keys);
        assertEquals(List.of(p2), res.get("CatA"));
        assertEquals(List.of(p1), res.get("CatB"));
    }
}

