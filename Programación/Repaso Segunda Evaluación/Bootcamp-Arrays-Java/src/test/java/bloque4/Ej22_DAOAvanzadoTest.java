package bloque4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej22 - DAO Avanzado")
class Ej22_DAOAvanzadoTest {

    private Ej22_DAOAvanzado dao;

    @BeforeEach
    void setUp() {
        dao = new Ej22_DAOAvanzado();
        dao.agregar(new Ej19_Producto(1, "Laptop", 999.99, 2, 3));
        dao.agregar(new Ej19_Producto(2, "Mouse", 19.99, 1, 2));
        dao.agregar(new Ej19_Producto(3, "Laptop Pro", 1499.99, 2, 3));
    }

    @Test @DisplayName("buscarPorNombre: case insensitive")
    void porNombre() { assertEquals(2, dao.buscarPorNombre("laptop").size()); }

    @Test @DisplayName("buscarPorNombre: sin resultados")
    void porNombre_vacio() { assertEquals(0, dao.buscarPorNombre("xyz").size()); }

    @Test @DisplayName("productoMasCaro")
    void masCaro() { assertEquals(3, dao.productoMasCaro().getId()); }

    @Test @DisplayName("productoMasCaro: lista vacia null")
    void masCaro_vacio() { assertNull(new Ej22_DAOAvanzado().productoMasCaro()); }

    @Test @DisplayName("precioMedio")
    void precioMedio() {
        double esperado = (999.99 + 19.99 + 1499.99) / 3.0;
        assertEquals(esperado, dao.precioMedio(), 0.01);
    }

    @Test @DisplayName("buscarPorRangoPrecio")
    void rangoPrecio() { assertEquals(1, dao.buscarPorRangoPrecio(10, 100).size()); }

    @Test @DisplayName("existeProducto")
    void existe() {
        assertTrue(dao.existeProducto(1));
        assertFalse(dao.existeProducto(99));
    }

    @Test @DisplayName("buscarConStockMinimo")
    void stockMinimo() { assertEquals(3, dao.buscarConStockMinimo(0).size()); }
}
