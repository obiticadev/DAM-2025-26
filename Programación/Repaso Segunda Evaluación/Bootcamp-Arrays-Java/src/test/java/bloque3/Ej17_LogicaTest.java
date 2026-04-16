package bloque3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej17 - Logica de Negocio")
class Ej17_LogicaTest {

    @Test @DisplayName("Constructor valido")
    void constructor() {
        Ej17_Logica a = new Ej17_Logica(3, 4, 2.50);
        assertEquals(3, a.getFilas());
        assertEquals(4, a.getColumnas());
    }

    @Test @DisplayName("Constructor lanza excepcion si filas <= 0")
    void constructor_invalido() {
        assertThrows(IllegalArgumentException.class, () -> new Ej17_Logica(0, 4, 2.50));
    }

    @Test @DisplayName("Constructor lanza excepcion si precio < 0")
    void constructor_precioNeg() {
        assertThrows(IllegalArgumentException.class, () -> new Ej17_Logica(3, 4, -1));
    }

    @Test @DisplayName("almacenar: valido incrementa stock")
    void almacenar_valido() {
        Ej17_Logica a = new Ej17_Logica(2, 2, 1.0);
        assertTrue(a.almacenar(0, 0, 10));
        assertEquals(10, a.stockTotal());
        assertTrue(a.almacenar(0, 0, 5));
        assertEquals(15, a.stockTotal());
    }

    @Test @DisplayName("almacenar: fuera de rango false")
    void almacenar_fueraRango() {
        Ej17_Logica a = new Ej17_Logica(2, 2, 1.0);
        assertFalse(a.almacenar(9, 0, 5));
    }

    @Test @DisplayName("almacenar: cantidad <= 0 false")
    void almacenar_cantidadNeg() {
        Ej17_Logica a = new Ej17_Logica(2, 2, 1.0);
        assertFalse(a.almacenar(0, 0, 0));
        assertFalse(a.almacenar(0, 0, -1));
    }

    @Test @DisplayName("retirar: valido reduce stock")
    void retirar_valido() {
        Ej17_Logica a = new Ej17_Logica(2, 2, 1.0);
        a.almacenar(0, 0, 10);
        assertTrue(a.retirar(0, 0, 3));
        assertEquals(7, a.stockTotal());
    }

    @Test @DisplayName("retirar: sin stock suficiente false")
    void retirar_sinStock() {
        Ej17_Logica a = new Ej17_Logica(2, 2, 1.0);
        a.almacenar(0, 0, 5);
        assertFalse(a.retirar(0, 0, 10));
    }

    @Test @DisplayName("valorTotal: stock * precio")
    void valorTotal() {
        Ej17_Logica a = new Ej17_Logica(2, 2, 2.50);
        a.almacenar(0, 0, 10);
        assertEquals(25.0, a.valorTotal(), 0.01);
    }

    @Test @DisplayName("posicionesVacias: cuenta ceros")
    void posicionesVacias() {
        Ej17_Logica a = new Ej17_Logica(2, 2, 1.0);
        assertEquals(4, a.posicionesVacias());
        a.almacenar(0, 0, 5);
        assertEquals(3, a.posicionesVacias());
    }

    @Test @DisplayName("estanteriaConMasStock: indice correcto")
    void estanteriaConMasStock() {
        Ej17_Logica a = new Ej17_Logica(3, 2, 1.0);
        a.almacenar(0, 0, 5);
        a.almacenar(2, 0, 20);
        assertEquals(2, a.estanteriaConMasStock());
    }
}
