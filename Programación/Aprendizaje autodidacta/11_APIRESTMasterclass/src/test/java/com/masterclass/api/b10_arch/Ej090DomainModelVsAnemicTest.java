package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b10_arch.Ej090DomainModelVsAnemic.Carrito;
import static org.junit.jupiter.api.Assertions.*;

class Ej090DomainModelVsAnemicTest {

    @Test
    void flujoNormal() {
        var c = new Carrito();
        c.anadir(2);
        c.anadir(3);
        c.pagar();
        assertEquals(5, c.unidades());
        assertTrue(c.pagado());
    }

    @Test
    void noSePuedeAnadirTrasPagar() {
        var c = new Carrito();
        c.anadir(1);
        c.pagar();
        assertThrows(IllegalStateException.class, () -> c.anadir(1));
    }

    @Test
    void noSePuedePagarVacio() {
        assertThrows(IllegalStateException.class, () -> new Carrito().pagar());
    }

    @Test
    void cantidadInvalida() {
        assertThrows(IllegalArgumentException.class, () -> new Carrito().anadir(0));
    }
}
