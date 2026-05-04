package com.bootcamp.nivel2;

import com.bootcamp.nivel2_crud.Ej12_SelectTodosALista;
import com.bootcamp.nivel2_crud.Ej12_SelectTodosALista.Ciudad;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej12Test {

    @BeforeAll
    static void setup() throws Exception {
        Ej12_SelectTodosALista.inicializar();
    }

    @Test
    @Order(1)
    @DisplayName("obtenerTodas() no devuelve null")
    void obtenerTodasNoNull() throws Exception {
        assertNotNull(Ej12_SelectTodosALista.obtenerTodas());
    }

    @Test
    @Order(2)
    @DisplayName("obtenerTodas() devuelve al menos 4 ciudades")
    void obtenerTodasTieneDatos() throws Exception {
        assertTrue(Ej12_SelectTodosALista.obtenerTodas().size() >= 4);
    }

    @Test
    @Order(3)
    @DisplayName("obtenerTodas() devuelve objetos Ciudad con id > 0")
    void ciudadesConIdValido() throws Exception {
        List<Ciudad> lista = Ej12_SelectTodosALista.obtenerTodas();
        lista.forEach(c -> assertTrue(c.id > 0, "El id debe ser positivo"));
    }

    @Test
    @Order(4)
    @DisplayName("obtenerPorPais('España') devuelve solo ciudades de España")
    void filtrarPorPais() throws Exception {
        List<Ciudad> espana = Ej12_SelectTodosALista.obtenerPorPais("España");
        assertFalse(espana.isEmpty(), "Debe haber ciudades de España");
        espana.forEach(c -> assertEquals("España", c.pais));
    }

    @Test
    @Order(5)
    @DisplayName("obtenerPorPais() con país inexistente devuelve lista vacía")
    void filtrarPaisInexistente() throws Exception {
        List<Ciudad> lista = Ej12_SelectTodosALista.obtenerPorPais("Wakanda");
        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    @Test
    @Order(6)
    @DisplayName("obtenerMasPoblada() devuelve la ciudad con mayor población")
    void masPobladaCorrectamente() throws Exception {
        Ciudad mas = Ej12_SelectTodosALista.obtenerMasPoblada();
        assertNotNull(mas, "Con datos en la tabla no debe devolver null");
        List<Ciudad> todas = Ej12_SelectTodosALista.obtenerTodas();
        int maxPoblacion = todas.stream().mapToInt(c -> c.poblacion).max().orElse(0);
        assertEquals(maxPoblacion, mas.poblacion);
    }
}
