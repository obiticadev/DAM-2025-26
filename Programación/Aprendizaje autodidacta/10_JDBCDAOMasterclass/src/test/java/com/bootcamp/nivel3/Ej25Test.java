package com.bootcamp.nivel3;

import com.bootcamp.nivel3_dao.Ej25_DAO_Actualizar;
import com.bootcamp.nivel3_dao.Ej25_DAO_Actualizar.Videojuego;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej25Test {

    private static Ej25_DAO_Actualizar.DAOVideojuego dao;

    @BeforeAll
    static void setup() throws Exception {
        dao = new Ej25_DAO_Actualizar.DAOVideojuego();
        dao.crearTabla();
        dao.insertar(new Videojuego("Zelda: BOTW", "Aventura", 2017, 59.99));
        dao.insertar(new Videojuego("Elden Ring", "RPG", 2022, 59.99));
    }

    @Test
    @Order(1)
    @DisplayName("actualizar() devuelve true para id existente")
    void actualizarIdExistente() throws Exception {
        Videojuego actualizado = new Videojuego(1, "Zelda: TOTK", "Aventura", 2023, 69.99);
        assertTrue(dao.actualizar(actualizado));
    }

    @Test
    @Order(2)
    @DisplayName("Los datos cambian realmente en la BD tras actualizar()")
    void datosActualizadosEnBD() throws Exception {
        Videojuego nuevo = new Videojuego(1, "Zelda: TOTK Final", "Accion-Aventura", 2023, 79.99);
        dao.actualizar(nuevo);
        Videojuego leido = dao.obtenerPorId(1);
        assertNotNull(leido);
        assertEquals("Zelda: TOTK Final", leido.getTitulo());
        assertEquals("Accion-Aventura", leido.getGenero());
        assertEquals(79.99, leido.getPrecio(), 0.001);
    }

    @Test
    @Order(3)
    @DisplayName("actualizar() devuelve false para id inexistente")
    void actualizarIdInexistente() throws Exception {
        Videojuego fantasma = new Videojuego(99999, "No existe", "X", 2000, 0.0);
        assertFalse(dao.actualizar(fantasma));
    }

    @Test
    @Order(4)
    @DisplayName("El id del objeto no cambia tras actualizar()")
    void idNoMuta() throws Exception {
        Videojuego antes = dao.obtenerPorId(2);
        assertNotNull(antes);
        Videojuego mod = new Videojuego(2, "Elden Ring GOTY", "RPG", 2023, 39.99);
        dao.actualizar(mod);
        Videojuego despues = dao.obtenerPorId(2);
        assertNotNull(despues);
        assertEquals(2, despues.getId());
    }

    @Test
    @Order(5)
    @DisplayName("actualizar() no afecta otras filas")
    void otrasFilasIntactas() throws Exception {
        String tituloAntes = dao.obtenerPorId(2) != null
                ? dao.obtenerPorId(2).getTitulo() : null;
        // Actualiza solo id=1
        dao.actualizar(new Videojuego(1, "Cambiado", "X", 2000, 1.0));
        // id=2 debe seguir igual
        Videojuego dos = dao.obtenerPorId(2);
        if (tituloAntes != null && dos != null) {
            assertEquals(tituloAntes, dos.getTitulo());
        }
    }
}
