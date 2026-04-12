package bloque4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej19 - Producto")
class Ej19_ProductoTest {

    @Test @DisplayName("Constructor valido")
    void constructor() {
        Ej19_Producto p = new Ej19_Producto(1, "Test", 10.0, 2, 3);
        assertEquals(1, p.getId());
        assertEquals("Test", p.getNombre());
        assertEquals(10.0, p.getPrecio());
        assertEquals(2, p.getFilas());
        assertEquals(3, p.getColumnas());
    }

    @Test @DisplayName("Constructor invalido: id <= 0")
    void constructor_idNeg() {
        assertThrows(IllegalArgumentException.class,
            () -> new Ej19_Producto(0, "Test", 10, 2, 3));
    }

    @Test @DisplayName("Constructor invalido: nombre vacio")
    void constructor_nombreVacio() {
        assertThrows(IllegalArgumentException.class,
            () -> new Ej19_Producto(1, "", 10, 2, 3));
    }

    @Test @DisplayName("Constructor invalido: precio < 0")
    void constructor_precioNeg() {
        assertThrows(IllegalArgumentException.class,
            () -> new Ej19_Producto(1, "Test", -1, 2, 3));
    }

    @Test @DisplayName("getStockTotal: inicialmente 0")
    void stockInicial() {
        assertEquals(0, new Ej19_Producto(1, "T", 10, 2, 3).getStockTotal());
    }

    @Test @DisplayName("getStockTotal: tras almacenar")
    void stockTrasAlmacenar() {
        Ej19_Producto p = new Ej19_Producto(1, "T", 10, 2, 3);
        p.almacenar(0, 0, 5);
        p.almacenar(1, 2, 3);
        assertEquals(8, p.getStockTotal());
    }

    @Test @DisplayName("getValorTotal: stock * precio")
    void valorTotal() {
        Ej19_Producto p = new Ej19_Producto(1, "T", 2.50, 2, 2);
        p.almacenar(0, 0, 10);
        assertEquals(25.0, p.getValorTotal(), 0.01);
    }

    @Test @DisplayName("almacenar fuera de rango false")
    void almacenarFuera() {
        assertFalse(new Ej19_Producto(1, "T", 10, 2, 2).almacenar(9, 0, 5));
    }

    @Test @DisplayName("retirar: sin stock suficiente false")
    void retirarSinStock() {
        Ej19_Producto p = new Ej19_Producto(1, "T", 10, 2, 2);
        p.almacenar(0, 0, 3);
        assertFalse(p.retirar(0, 0, 5));
        assertTrue(p.retirar(0, 0, 2));
        assertEquals(1, p.getStockTotal());
    }
}
