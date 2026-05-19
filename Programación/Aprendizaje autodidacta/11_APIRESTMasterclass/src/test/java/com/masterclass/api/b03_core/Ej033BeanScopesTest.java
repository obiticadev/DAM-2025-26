package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej033BeanScopesTest {

    @Test
    void singleton() {
        var s = new Ej033BeanScopes<>(Object::new, true);
        assertSame(s.get(), s.get());
    }

    @Test
    void prototype() {
        var p = new Ej033BeanScopes<>(Object::new, false);
        assertNotSame(p.get(), p.get());
    }
}
