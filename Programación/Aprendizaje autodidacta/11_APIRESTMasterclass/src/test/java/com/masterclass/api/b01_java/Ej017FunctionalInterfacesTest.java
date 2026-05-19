package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej017FunctionalInterfacesTest {

    @Test
    void transformar() {
        assertEquals(List.of(1, 4, 9),
                Ej017FunctionalInterfaces.transformar(List.of(1, 2, 3), x -> x * x));
    }

    @Test
    void filtrar() {
        assertEquals(List.of(2, 4),
                Ej017FunctionalInterfaces.filtrar(List.of(1, 2, 3, 4), x -> x % 2 == 0));
    }

    @Test
    void seguroOrElse() {
        assertEquals("ok", Ej017FunctionalInterfaces.seguroOrElse(() -> "ok", "fb"));
        assertEquals("fb", Ej017FunctionalInterfaces.seguroOrElse(() -> {
            throw new RuntimeException("boom");
        }, "fb"));
    }
}
