package com.masterclass.api.b12_jpa;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b12_jpa.Ej113EqualsHashCodeEntities.Cuenta113;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej113EqualsHashCodeEntitiesTest {

    @Test
    void igualdadPorIban() {
        var a = new Cuenta113("ES1", 100);
        var b = new Cuenta113("ES1", 999);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void distintoIban() {
        assertNotEquals(new Cuenta113("ES1", 0), new Cuenta113("ES2", 0));
    }

    @Test
    void funcionaEnSet() {
        Set<Cuenta113> s = new HashSet<>();
        s.add(new Cuenta113("ES1", 1));
        s.add(new Cuenta113("ES1", 2));
        assertEquals(1, s.size());
    }

@Test
    void testDesafioReflexivo() {
        var c = new Cliente();
        assertTrue(Ej113EqualsHashCodeEntities.desafioReflexivo(c));
    }

    @Test
    void testDesafioSimetrico() {
        var c1 = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("a", "Ana");
        var c2 = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("a", "Ana");
        assertTrue(Ej113EqualsHashCodeEntities.desafioSimetrico(c1, c2));
    }

    @Test
    void testDesafioNuloFalso() {
        var c = new Cliente();
        assertTrue(Ej113EqualsHashCodeEntities.desafioNuloFalso(c));
    }

    @Test
    void testDesafioClaseDiferenteFalso() {
        var c = new Cliente();
        assertTrue(Ej113EqualsHashCodeEntities.desafioClaseDiferenteFalso(c));
    }

    @Test
    void testDesafioMismoUuidIguales() {
        var c1 = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("a", "Ana");
        var c2 = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("a", "Ana");
        assertTrue(Ej113EqualsHashCodeEntities.desafioMismoUuidIguales(c1, c2));
    }

    @Test
    void testDesafioDiferenteUuidNoIguales() {
        var c1 = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("a", "Ana");
        var c2 = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("b", "Ana");
        assertTrue(Ej113EqualsHashCodeEntities.desafioDiferenteUuidNoIguales(c1, c2));
    }

    @Test
    void testDesafioHashCodeCoherente() {
        var c1 = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("a", "Ana");
        var c2 = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("a", "Ana");
        assertTrue(Ej113EqualsHashCodeEntities.desafioHashCodeCoherente(c1, c2));
    }

    @Test
    void testDesafioCrearClienteConUuid() {
        var c = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("abc", "Ana");
        assertEquals("abc", c.getUuid());
    }

    @Test
    void testDesafioInvarianteIdDb() {
        var c1 = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("a", "Ana");
        var c2 = Ej113EqualsHashCodeEntities.desafioCrearClienteConUuid("a", "Ana");
        assertTrue(Ej113EqualsHashCodeEntities.desafioInvarianteIdDb(c1, c2, 10L));
    }

    @Test
    void testDesafioTieneUuidValido() {
        var c = new Cliente();
        assertFalse(Ej113EqualsHashCodeEntities.desafioTieneUuidValido(c));
        c.setUuid("a");
        assertTrue(Ej113EqualsHashCodeEntities.desafioTieneUuidValido(c));
    }
}
