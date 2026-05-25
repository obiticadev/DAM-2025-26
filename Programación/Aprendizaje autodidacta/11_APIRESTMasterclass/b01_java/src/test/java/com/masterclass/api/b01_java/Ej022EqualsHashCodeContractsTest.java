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

    @Test
    void retoExtra01_esSimetrico() {
        var a1 = new Articulo("A1", "Tornillo");
        var a2 = new Articulo("A1", "Tornillo");
        var b = new Articulo("B1", "Clavo");
        assertTrue(Ej022EqualsHashCodeContracts.esSimetrico(a1, a2));
        assertTrue(Ej022EqualsHashCodeContracts.esSimetrico(a1, b));
        assertTrue(Ej022EqualsHashCodeContracts.esSimetrico(null, null));
        assertTrue(Ej022EqualsHashCodeContracts.esSimetrico(a1, null));
    }

    @Test
    void retoExtra02_esTransitivo() {
        var a = new Articulo("A1", "Tornillo");
        var b = new Articulo("A1", "Tornillo (v2)");
        var c = new Articulo("A1", "Tornillo (v3)");
        assertTrue(Ej022EqualsHashCodeContracts.esTransitivo(a, b, c));
        assertTrue(Ej022EqualsHashCodeContracts.esTransitivo(a, b, null));
    }

    @Test
    void retoExtra03_esConsistenteHashCode() {
        var a = new Articulo("A1", "Tornillo");
        assertTrue(Ej022EqualsHashCodeContracts.esConsistenteHashCode(a));
        assertTrue(Ej022EqualsHashCodeContracts.esConsistenteHashCode("hola"));
        assertFalse(Ej022EqualsHashCodeContracts.esConsistenteHashCode(null));
    }

    @Test
    void retoExtra04_verificarContratoEqualsYHashCode() {
        var a = new Articulo("A1", "Tornillo");
        var b = new Articulo("A1", "Tornillo (v2)");
        var c = new Articulo("B1", "Clavo");
        assertTrue(Ej022EqualsHashCodeContracts.verificarContratoEqualsYHashCode(a, b));
        assertTrue(Ej022EqualsHashCodeContracts.verificarContratoEqualsYHashCode(a, c)); // Si no son iguales, cumple el contrato de igual forma
    }

    @Test
    void retoExtra05_esEqualsNuloSeguro() {
        var a = new Articulo("A1", "Tornillo");
        assertTrue(Ej022EqualsHashCodeContracts.esEqualsNuloSeguro(a));
        assertFalse(Ej022EqualsHashCodeContracts.esEqualsNuloSeguro(null));
    }

    @Test
    void retoExtra06_formatearToStringElegante() {
        assertEquals("Articulo{id=123, descripcion='Tornillo de acero'}",
                Ej022EqualsHashCodeContracts.formatearToStringElegante("Articulo", 123, "Tornillo de acero"));
    }

    @Test
    void retoExtra07_verificarColisionHash() {
        // En Java, las cadenas "Aa" y "BB" tienen el mismo hashCode: 2112
        String s1 = "Aa";
        String s2 = "BB";
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(s1, s2);
        assertTrue(Ej022EqualsHashCodeContracts.verificarColisionHash(s1, s2));
        assertFalse(Ej022EqualsHashCodeContracts.verificarColisionHash("hola", "mundo"));
    }

    @Test
    void retoExtra08_esIdentidadConsistente() {
        var a = new Articulo("A1", "Tornillo");
        assertTrue(Ej022EqualsHashCodeContracts.esIdentidadConsistente(a));
        assertFalse(Ej022EqualsHashCodeContracts.esIdentidadConsistente(null));
    }

    @Test
    void retoExtra09_calcularHashCombinado() {
        int hash1 = Ej022EqualsHashCodeContracts.calcularHashCombinado("A1", 100);
        int hash2 = java.util.Objects.hash("A1", 100);
        assertEquals(hash2, hash1);
    }

    @Test
    void retoExtra10_esInstanciaCompatible() {
        var a = new Articulo("A1", "Tornillo");
        assertTrue(Ej022EqualsHashCodeContracts.esInstanciaCompatible(a, Articulo.class));
        assertFalse(Ej022EqualsHashCodeContracts.esInstanciaCompatible("hola", Articulo.class));
        assertFalse(Ej022EqualsHashCodeContracts.esInstanciaCompatible(null, Articulo.class));
    }
}
