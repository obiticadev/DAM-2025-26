package com.bootcamp.nivel2;

import com.bootcamp.nivel2_crud.Ej13_SelectPorId;
import com.bootcamp.nivel2_crud.Ej13_SelectPorId.Libro;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej13Test {

    @BeforeAll
    static void setup() throws Exception {
        Ej13_SelectPorId.inicializar();
    }

    @Test
    @Order(1)
    @DisplayName("obtenerPorId() con id existente no devuelve null")
    void obtenerIdExistente() throws Exception {
        assertNotNull(Ej13_SelectPorId.obtenerPorId(1));
    }

    @Test
    @Order(2)
    @DisplayName("obtenerPorId() con id inexistente devuelve null")
    void obtenerIdInexistente() throws Exception {
        assertNull(Ej13_SelectPorId.obtenerPorId(99999));
    }

    @Test
    @Order(3)
    @DisplayName("obtenerPorId() devuelve el libro correcto")
    void obtenerIdDatosCorrectos() throws Exception {
        Libro libro = Ej13_SelectPorId.obtenerPorId(1);
        assertNotNull(libro);
        assertEquals(1, libro.id);
        assertFalse(libro.titulo.isBlank(), "El título no debe estar vacío");
    }

    @Test
    @Order(4)
    @DisplayName("existe() devuelve true para id existente")
    void existeTrue() throws Exception {
        assertTrue(Ej13_SelectPorId.existe(1));
    }

    @Test
    @Order(5)
    @DisplayName("existe() devuelve false para id inexistente")
    void existeFalse() throws Exception {
        assertFalse(Ej13_SelectPorId.existe(99999));
    }

    @Test
    @Order(6)
    @DisplayName("buscarPorTitulo() encuentra coincidencia parcial")
    void buscarPorTituloOk() throws Exception {
        Libro libro = Ej13_SelectPorId.buscarPorTitulo("Java");
        assertNotNull(libro, "Debe encontrar el libro con 'Java' en el título");
        assertTrue(libro.titulo.contains("Java"));
    }

    @Test
    @Order(7)
    @DisplayName("buscarPorTitulo() devuelve null si no hay coincidencia")
    void buscarSinCoincidencia() throws Exception {
        assertNull(Ej13_SelectPorId.buscarPorTitulo("TituloQueNoExisteXYZ123"));
    }
}
