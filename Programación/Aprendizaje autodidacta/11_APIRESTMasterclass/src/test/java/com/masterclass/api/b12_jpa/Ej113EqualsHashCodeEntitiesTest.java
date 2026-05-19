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
}
