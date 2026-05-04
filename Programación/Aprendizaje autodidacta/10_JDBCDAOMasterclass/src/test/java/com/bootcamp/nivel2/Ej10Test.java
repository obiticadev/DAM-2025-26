package com.bootcamp.nivel2;

import com.bootcamp.nivel2_crud.Ej10_InsertTiposMixtos;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej10Test {

    @BeforeEach
    void setup() throws Exception {
        Ej10_InsertTiposMixtos.crearTablaProductos();
    }

    @Test
    @Order(1)
    @DisplayName("insertarProducto() devuelve true con datos válidos")
    void insertarProductoOk() throws Exception {
        assertTrue(Ej10_InsertTiposMixtos.insertarProducto("TestItem", 9.99, 10));
    }

    @Test
    @Order(2)
    @DisplayName("stockTotal() aumenta tras insertar")
    void stockTotalAumenta() throws Exception {
        int antes = Ej10_InsertTiposMixtos.stockTotal();
        Ej10_InsertTiposMixtos.insertarProducto("StockTest", 1.0, 50);
        assertTrue(Ej10_InsertTiposMixtos.stockTotal() >= antes + 50);
    }

    @Test
    @Order(3)
    @DisplayName("insertarVariosProductos() devuelve el número correcto de éxitos")
    void insertarVariosOk() throws Exception {
        Object[][] lote = {
            {"A", 1.0, 5},
            {"B", 2.0, 10},
            {"C", 3.0, 15}
        };
        assertEquals(3, Ej10_InsertTiposMixtos.insertarVariosProductos(lote));
    }

    @Test
    @Order(4)
    @DisplayName("insertarVariosProductos() con lista vacía devuelve 0")
    void insertarLoteVacioDevuelveCero() throws Exception {
        assertEquals(0, Ej10_InsertTiposMixtos.insertarVariosProductos(new Object[0][0]));
    }

    @Test
    @Order(5)
    @DisplayName("stockTotal() es >= 0 en cualquier estado")
    void stockTotalNoNegativo() throws Exception {
        assertTrue(Ej10_InsertTiposMixtos.stockTotal() >= 0);
    }
}
