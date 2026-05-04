package com.bootcamp.nivel2;

import com.bootcamp.nivel2_crud.Ej14_Update;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej14Test {

    @BeforeAll
    static void setup() throws Exception {
        Ej14_Update.inicializar();
    }

    @Test
    @Order(1)
    @DisplayName("actualizarSalario() devuelve true para id existente")
    void actualizarSalarioExistente() throws Exception {
        assertTrue(Ej14_Update.actualizarSalario(1, 40000.0));
    }

    @Test
    @Order(2)
    @DisplayName("El salario cambia realmente en la base de datos")
    void salarioCambiaEnBD() throws Exception {
        Ej14_Update.actualizarSalario(1, 55000.0);
        assertEquals(55000.0, Ej14_Update.obtenerSalario(1), 0.01);
    }

    @Test
    @Order(3)
    @DisplayName("actualizarSalario() devuelve false para id inexistente")
    void actualizarSalarioInexistente() throws Exception {
        assertFalse(Ej14_Update.actualizarSalario(99999, 1000.0));
    }

    @Test
    @Order(4)
    @DisplayName("actualizarDepartamento() devuelve true para id existente")
    void actualizarDepartamentoExistente() throws Exception {
        assertTrue(Ej14_Update.actualizarDepartamento(2, "IT"));
    }

    @Test
    @Order(5)
    @DisplayName("actualizarDepartamento() devuelve false para id inexistente")
    void actualizarDepartamentoInexistente() throws Exception {
        assertFalse(Ej14_Update.actualizarDepartamento(99999, "IT"));
    }

    @Test
    @Order(6)
    @DisplayName("obtenerSalario() devuelve -1 para id inexistente")
    void obtenerSalarioInexistente() throws Exception {
        assertEquals(-1.0, Ej14_Update.obtenerSalario(99999), 0.01);
    }
}
