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

@Test
    void testDesafioVerificarEstadoInicial() {
        assertTrue(Ej090DomainModelVsAnemic.desafioVerificarEstadoInicial(new Carrito()));
    }

    @Test
    void testDesafioValidarUnidadesAIngresar() {
        assertThrows(IllegalArgumentException.class, () -> Ej090DomainModelVsAnemic.desafioValidarUnidadesAIngresar(0));
        assertDoesNotThrow(() -> Ej090DomainModelVsAnemic.desafioValidarUnidadesAIngresar(5));
    }

    @Test
    void testDesafioVerificarNoMudarTrasPago() {
        assertThrows(IllegalStateException.class, () -> Ej090DomainModelVsAnemic.desafioVerificarNoMudarTrasPago(true));
        assertDoesNotThrow(() -> Ej090DomainModelVsAnemic.desafioVerificarNoMudarTrasPago(false));
    }

    @Test
    void testDesafioIncrementarUnidades() {
        assertEquals(5, Ej090DomainModelVsAnemic.desafioIncrementarUnidades(2, 3));
    }

    @Test
    void testDesafioValidarCarritoVacio() {
        assertThrows(IllegalStateException.class, () -> Ej090DomainModelVsAnemic.desafioValidarCarritoVacio(0));
        assertDoesNotThrow(() -> Ej090DomainModelVsAnemic.desafioValidarCarritoVacio(1));
    }

    @Test
    void testDesafioVerificarDoblePago() {
        assertThrows(IllegalStateException.class, () -> Ej090DomainModelVsAnemic.desafioVerificarDoblePago(true));
        assertDoesNotThrow(() -> Ej090DomainModelVsAnemic.desafioVerificarDoblePago(false));
    }

    @Test
    void testDesafioTransicionEstadoPago() {
        assertTrue(Ej090DomainModelVsAnemic.desafioTransicionEstadoPago());
    }

    @Test
    void testDesafioObtenerUnidadesProtegidas() {
        var c = new Carrito();
        c.anadir(10);
        assertEquals(10, Ej090DomainModelVsAnemic.desafioObtenerUnidadesProtegidas(c));
    }

    @Test
    void testDesafioVerificarSiPagado() {
        var c = new Carrito();
        assertFalse(Ej090DomainModelVsAnemic.desafioVerificarSiPagado(c));
    }

    @Test
    void testDesafioVerificarInvarianteGlobal() {
        assertTrue(Ej090DomainModelVsAnemic.desafioVerificarInvarianteGlobal(new Carrito()));
    }
}
