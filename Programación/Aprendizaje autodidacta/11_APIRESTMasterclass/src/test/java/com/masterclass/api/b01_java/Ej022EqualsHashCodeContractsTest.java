package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b01_java.Ej022EqualsHashCodeContracts.Articulo;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej022EqualsHashCodeContractsTest {

    @Test
    void igualdadPorCodigo() {
        var a = new Articulo("A1", "Tornillo");
        var b = new Articulo("A1", "Otro nombre");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void distintoCodigo() {
        assertNotEquals(new Articulo("A1", "x"), new Articulo("A2", "x"));
    }

    @Test
    void funcionaEnSet() {
        Set<Articulo> s = new HashSet<>();
        s.add(new Articulo("A1", "x"));
        s.add(new Articulo("A1", "y"));
        assertEquals(1, s.size());
    }
}
