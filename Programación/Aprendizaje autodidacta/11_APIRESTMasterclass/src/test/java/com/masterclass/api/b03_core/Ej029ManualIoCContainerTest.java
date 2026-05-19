package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej029ManualIoCContainerTest {

    @Test
    void singletonYResolucion() {
        var c = new Ej029ManualIoCContainer();
        c.register(StringBuilder.class, StringBuilder::new);
        var a = c.getBean(StringBuilder.class);
        var b = c.getBean(StringBuilder.class);
        assertSame(a, b, "debe ser singleton");
    }

    @Test
    void noRegistrado() {
        var c = new Ej029ManualIoCContainer();
        assertThrows(IllegalStateException.class, () -> c.getBean(String.class));
    }
}
