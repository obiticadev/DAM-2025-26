package com.bootcamp.nivel3;

import com.bootcamp.nivel3_dao.Ej22_DAO_Insertar;
import com.bootcamp.nivel3_dao.Ej22_DAO_Insertar.Videojuego;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej22Test {

    private static Ej22_DAO_Insertar.DAOVideojuego dao;

    @BeforeAll
    static void setup() throws Exception {
        dao = new Ej22_DAO_Insertar.DAOVideojuego();
        dao.crearTabla();
    }

    @Test
    @Order(1)
    @DisplayName("insertar() devuelve true con datos válidos")
    void insertarDevuelveTrue() throws Exception {
        assertTrue(dao.insertar(new Videojuego("Zelda: TOTK", "Aventura", 2023, 69.99)));
    }

    @Test
    @Order(2)
    @DisplayName("contar() aumenta en 1 tras cada insert")
    void contadorAumentaTrasInsertar() throws Exception {
        int antes = dao.contar();
        dao.insertar(new Videojuego("Elden Ring", "RPG", 2022, 59.99));
        assertEquals(antes + 1, dao.contar());
    }

    @Test
    @Order(3)
    @DisplayName("Insertar varios incrementa el contador correctamente")
    void variosInsertsAcumulan() throws Exception {
        int antes = dao.contar();
        dao.insertar(new Videojuego("A", "X", 2000, 10.0));
        dao.insertar(new Videojuego("B", "Y", 2001, 20.0));
        dao.insertar(new Videojuego("C", "Z", 2002, 30.0));
        assertEquals(antes + 3, dao.contar());
    }

    @Test
    @Order(4)
    @DisplayName("insertar() con precio y año variados no lanza excepción")
    void insertarTiposNumericos() {
        assertDoesNotThrow(() ->
            dao.insertar(new Videojuego("Minecraft", "Sandbox", 2011, 26.99))
        );
    }

    @Test
    @Order(5)
    @DisplayName("contar() devuelve valor >= 0 siempre")
    void contarNoNegativo() throws Exception {
        assertTrue(dao.contar() >= 0);
    }
}
