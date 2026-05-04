package com.bootcamp.nivel3;

import com.bootcamp.nivel3_dao.Ej23_DAO_ObtenerTodos;
import com.bootcamp.nivel3_dao.Ej23_DAO_ObtenerTodos.Videojuego;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej23Test {

    private static Ej23_DAO_ObtenerTodos.DAOVideojuego dao;

    @BeforeAll
    static void setup() throws Exception {
        dao = new Ej23_DAO_ObtenerTodos.DAOVideojuego();
        dao.crearTabla();
        dao.insertar(new Videojuego("Zelda: TOTK", "Aventura", 2023, 69.99));
        dao.insertar(new Videojuego("Elden Ring", "RPG", 2022, 59.99));
        dao.insertar(new Videojuego("God of War", "Aventura", 2022, 49.99));
    }

    @Test
    @Order(1)
    @DisplayName("obtenerTodos() no devuelve null")
    void obtenerTodosNoNull() throws Exception {
        assertNotNull(dao.obtenerTodos());
    }

    @Test
    @Order(2)
    @DisplayName("obtenerTodos() devuelve al menos 3 videojuegos")
    void obtenerTodosTieneDatos() throws Exception {
        assertTrue(dao.obtenerTodos().size() >= 3);
    }

    @Test
    @Order(3)
    @DisplayName("Los objetos tienen id > 0 (asignado por BD)")
    void idAsignadoPorBD() throws Exception {
        dao.obtenerTodos().forEach(v ->
            assertTrue(v.getId() > 0, "El id asignado por AUTOINCREMENT debe ser > 0"));
    }

    @Test
    @Order(4)
    @DisplayName("Los objetos tienen título no vacío")
    void tituloNoVacio() throws Exception {
        dao.obtenerTodos().forEach(v ->
            assertFalse(v.getTitulo().isBlank(), "El título no debe estar vacío"));
    }

    @Test
    @Order(5)
    @DisplayName("obtenerPorGenero('Aventura') devuelve solo los de ese género")
    void filtrarPorGenero() throws Exception {
        List<Videojuego> aventura = dao.obtenerPorGenero("Aventura");
        assertFalse(aventura.isEmpty());
        aventura.forEach(v -> assertEquals("Aventura", v.getGenero()));
    }

    @Test
    @Order(6)
    @DisplayName("obtenerPorGenero() con género inexistente devuelve lista vacía")
    void generoInexistente() throws Exception {
        List<Videojuego> res = dao.obtenerPorGenero("Genero_que_no_existe");
        assertNotNull(res);
        assertTrue(res.isEmpty());
    }

    @Test
    @Order(7)
    @DisplayName("obtenerTodos() refleja un nuevo insert inmediatamente")
    void reflejaInsertNuevo() throws Exception {
        int antes = dao.obtenerTodos().size();
        dao.insertar(new Videojuego("Nuevo", "Accion", 2024, 39.99));
        assertEquals(antes + 1, dao.obtenerTodos().size());
    }
}
