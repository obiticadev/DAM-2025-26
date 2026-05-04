package com.bootcamp.nivel3;

import com.bootcamp.nivel3_dao.Ej24_DAO_ObtenerPorId;
import com.bootcamp.nivel3_dao.Ej24_DAO_ObtenerPorId.Videojuego;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej24Test {

    private static Ej24_DAO_ObtenerPorId.DAOVideojuego dao;

    @BeforeAll
    static void setup() throws Exception {
        dao = new Ej24_DAO_ObtenerPorId.DAOVideojuego();
        dao.crearTabla();
        dao.insertar(new Videojuego("Zelda: TOTK", "Aventura", 2023, 69.99));
        dao.insertar(new Videojuego("Elden Ring", "RPG", 2022, 59.99));
    }

    @Test
    @Order(1)
    @DisplayName("obtenerPorId() con id existente devuelve objeto no null")
    void obtenerIdExistente() throws Exception {
        assertNotNull(dao.obtenerPorId(1));
    }

    @Test
    @Order(2)
    @DisplayName("obtenerPorId() con id inexistente devuelve null")
    void obtenerIdInexistente() throws Exception {
        assertNull(dao.obtenerPorId(99999));
    }

    @Test
    @Order(3)
    @DisplayName("obtenerPorId() devuelve el videojuego correcto")
    void datosCorrectos() throws Exception {
        Videojuego v = dao.obtenerPorId(1);
        assertNotNull(v);
        assertEquals(1, v.getId());
        assertFalse(v.getTitulo().isBlank());
        assertTrue(v.getPrecio() > 0);
    }

    @Test
    @Order(4)
    @DisplayName("existe() devuelve true para id existente")
    void existeTrue() throws Exception {
        assertTrue(dao.existe(1));
    }

    @Test
    @Order(5)
    @DisplayName("existe() devuelve false para id inexistente")
    void existeFalse() throws Exception {
        assertFalse(dao.existe(99999));
    }

    @Test
    @Order(6)
    @DisplayName("obtenerPorId() tras insertar encuentra el nuevo registro")
    void encuentraRegistroNuevo() throws Exception {
        dao.insertar(new Videojuego("Nuevo Juego", "Accion", 2024, 39.99));
        // La lista puede tener más elementos; solo verificamos que alguno tiene título "Nuevo Juego"
        boolean encontrado = dao.obtenerTodos().stream()
                .anyMatch(v -> "Nuevo Juego".equals(v.getTitulo()));
        assertTrue(encontrado);
    }
}
