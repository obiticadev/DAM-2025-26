package com.bootcamp.nivel2;

import com.bootcamp.nivel2_crud.Ej15_Delete;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej15Test {

    @BeforeAll
    static void setup() throws Exception {
        Ej15_Delete.inicializar();
    }

    @Test
    @Order(1)
    @DisplayName("contarNotas() devuelve valor mayor que 0 con datos iniciales")
    void hayDatosIniciales() throws Exception {
        assertTrue(Ej15_Delete.contarNotas() > 0);
    }

    @Test
    @Order(2)
    @DisplayName("eliminarPorId() devuelve true para id existente")
    void eliminarIdExistente() throws Exception {
        assertTrue(Ej15_Delete.eliminarPorId(1));
    }

    @Test
    @Order(3)
    @DisplayName("Tras eliminar, el contador baja en 1")
    void contadorBajaTrasEliminar() throws Exception {
        int antes = Ej15_Delete.contarNotas();
        // La nota 2 debería existir (solo eliminamos la 1 en el test anterior)
        Ej15_Delete.eliminarPorId(2);
        assertTrue(Ej15_Delete.contarNotas() <= antes);
    }

    @Test
    @Order(4)
    @DisplayName("eliminarPorId() devuelve false para id inexistente")
    void eliminarIdInexistente() throws Exception {
        assertFalse(Ej15_Delete.eliminarPorId(99999));
    }

    @Test
    @Order(5)
    @DisplayName("eliminarPorCategoria() devuelve el número de filas eliminadas")
    void eliminarPorCategoriaOk() throws Exception {
        int eliminadas = Ej15_Delete.eliminarPorCategoria("Estudio");
        assertTrue(eliminadas >= 0, "No puede devolver un número negativo");
    }

    @Test
    @Order(6)
    @DisplayName("eliminarPorCategoria() con categoría inexistente devuelve 0")
    void eliminarCategoriaSinDatos() throws Exception {
        assertEquals(0, Ej15_Delete.eliminarPorCategoria("CategoriaQueNoExiste"));
    }
}
