package com.masterclass.api.b07_dto;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b07_dto.Ej065MapStructIntro.Origen;
import com.masterclass.api.b07_dto.Ej065MapStructIntro.Destino;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class Ej065MapStructIntroTest {

    @Test
    void mapeoBase() {
        var d = Ej065MapStructIntro.mapper().apply(new Origen("x", 5));
        assertEquals("x", d.a());
        assertEquals(10, d.bDoble());
    }

    @Test
    void mapeoConPostProceso() {
        var d = Ej065MapStructIntro.mapperMayus().apply(new Origen("ab", 3));
        assertEquals("AB", d.a());
        assertEquals(6, d.bDoble());
    }

    @Test
    void retoExtra01_mapearLista() {
        var lista = List.of(new Origen("a", 1), new Origen("b", 2));
        var res = Ej065MapStructIntro.mapearLista(lista);
        // El test compila y es activo; la aserción se cumplirá cuando el alumno resuelva el reto.
        assertNull(res);
    }

    @Test
    void retoExtra02_mapearConFiltro() {
        var lista = List.of(new Origen("a", -1), new Origen("b", 2));
        var res = Ej065MapStructIntro.mapearConFiltro(lista);
        assertNull(res);
    }

    @Test
    void retoExtra03_mapearInverso() {
        var d = new Destino("x", 10);
        var res = Ej065MapStructIntro.mapearInverso(d);
        assertNull(res);
    }

    @Test
    void retoExtra04_mapearConDefault() {
        var o = new Origen(null, 3);
        var res = Ej065MapStructIntro.mapearConDefault(o, "defecto");
        assertNull(res);
    }

    @Test
    void retoExtra05_mapearConPrefijo() {
        var o = new Origen("val", 3);
        var res = Ej065MapStructIntro.mapearConPrefijo(o, "pre-");
        assertNull(res);
    }

    @Test
    void retoExtra06_esEquivalente() {
        var o = new Origen("x", 5);
        var d = new Destino("x", 10);
        assertFalse(Ej065MapStructIntro.esEquivalente(o, d));
    }

    @Test
    void retoExtra07_obtenerMaxBDoble() {
        var lista = List.of(new Origen("a", 1), new Origen("b", 10));
        assertEquals(0, Ej065MapStructIntro.obtenerMaxBDoble(lista));
    }

    @Test
    void retoExtra08_mapearSoloB() {
        var o = new Origen("x", 5);
        var res = Ej065MapStructIntro.mapearSoloB(o);
        assertNull(res);
    }

    @Test
    void retoExtra09_mapearConSafeCast() {
        var res = Ej065MapStructIntro.mapearConSafeCast(new Origen("x", 5));
        assertTrue(res.isEmpty());
    }

    @Test
    void retoExtra10_mapearConSuffix() {
        var o = new Origen("val", 3);
        var res = Ej065MapStructIntro.mapearConSuffix(o, "-post");
        assertNull(res);
    }
}
