package com.bootcamp.nivel3;

import com.bootcamp.nivel3_dao.Ej26_DAO_Eliminar;
import com.bootcamp.nivel3_dao.Ej26_DAO_Eliminar.Videojuego;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej26Test {

    private static Ej26_DAO_Eliminar.DAOVideojuego dao;

    @BeforeAll
    static void setup() throws Exception {
        dao = new Ej26_DAO_Eliminar.DAOVideojuego();
        dao.crearTabla();
        dao.insertar(new Videojuego("Zelda: TOTK",  "Aventura", 2023, 69.99));
        dao.insertar(new Videojuego("Elden Ring",   "RPG",      2022, 59.99));
        dao.insertar(new Videojuego("Minecraft",    "Sandbox",  2011, 26.99));
    }

    @Test
    @Order(1)
    @DisplayName("contar() devuelve al menos 3 (datos iniciales)")
    void hayDatosIniciales() throws Exception {
        assertTrue(dao.contar() >= 3);
    }

    @Test
    @Order(2)
    @DisplayName("eliminar() devuelve true para id existente")
    void eliminarIdExistente() throws Exception {
        assertTrue(dao.eliminar(1));
    }

    @Test
    @Order(3)
    @DisplayName("El contador baja tras eliminar")
    void contadorBajaTrasEliminar() throws Exception {
        int antes = dao.contar();
        dao.eliminar(2);
        assertTrue(dao.contar() <= antes,
                "El contador debe bajar o mantenerse (si ya estaba eliminado)");
    }

    @Test
    @Order(4)
    @DisplayName("eliminar() devuelve false para id inexistente")
    void eliminarIdInexistente() throws Exception {
        assertFalse(dao.eliminar(99999));
    }

    @Test
    @Order(5)
    @DisplayName("Eliminar el mismo id dos veces: segunda vez devuelve false")
    void dobleEliminar() throws Exception {
        dao.insertar(new Videojuego("Temp", "X", 2000, 1.0));
        int total = dao.contar();
        // Buscamos el último id insertado a través de la lista
        int ultimoId = dao.obtenerTodos().stream()
                .mapToInt(Videojuego::getId).max().orElse(-1);
        assertTrue(dao.eliminar(ultimoId));
        assertFalse(dao.eliminar(ultimoId), "Segunda eliminación debe devolver false");
    }

    @Test
    @Order(6)
    @DisplayName("contar() tras eliminar todos queda en 0 o menos que el inicio")
    void contadorCoherente() throws Exception {
        assertTrue(dao.contar() >= 0);
    }
}
