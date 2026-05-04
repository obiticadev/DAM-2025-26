package com.bootcamp.nivel2;

import com.bootcamp.nivel2_crud.Ej18_MultiplesInserts;
import com.bootcamp.nivel2_crud.Ej18_MultiplesInserts.Pelicula;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej18Test {

    @BeforeAll
    static void setup() throws Exception {
        Ej18_MultiplesInserts.crearTablaPeliculas();
    }

    @Test
    @Order(1)
    @DisplayName("insertarPelicula() devuelve true con datos válidos")
    void insertarPeliculaOk() throws Exception {
        assertTrue(Ej18_MultiplesInserts.insertarPelicula(new Pelicula("Test", "Director", 2000)));
    }

    @Test
    @Order(2)
    @DisplayName("contarPeliculas() aumenta tras insertar")
    void contadorAumenta() throws Exception {
        int antes = Ej18_MultiplesInserts.contarPeliculas();
        Ej18_MultiplesInserts.insertarPelicula(new Pelicula("Otra", "Otro", 2001));
        assertEquals(antes + 1, Ej18_MultiplesInserts.contarPeliculas());
    }

    @Test
    @Order(3)
    @DisplayName("insertarLote() devuelve el número correcto de éxitos")
    void insertarLoteOk() throws Exception {
        List<Pelicula> lote = List.of(
            new Pelicula("Pel1", "Dir1", 2010),
            new Pelicula("Pel2", "Dir1", 2011),
            new Pelicula("Pel3", "Dir2", 2012)
        );
        assertEquals(3, Ej18_MultiplesInserts.insertarLote(lote));
    }

    @Test
    @Order(4)
    @DisplayName("insertarLote() con lista vacía devuelve 0")
    void insertarLoteVacio() throws Exception {
        assertEquals(0, Ej18_MultiplesInserts.insertarLote(List.of()));
    }

    @Test
    @Order(5)
    @DisplayName("contarPorDirector() cuenta correctamente")
    void contarPorDirectorOk() throws Exception {
        Ej18_MultiplesInserts.insertarPelicula(new Pelicula("X", "UnicoDirector", 2020));
        Ej18_MultiplesInserts.insertarPelicula(new Pelicula("Y", "UnicoDirector", 2021));
        assertEquals(2, Ej18_MultiplesInserts.contarPorDirector("UnicoDirector"));
    }

    @Test
    @Order(6)
    @DisplayName("contarPorDirector() con director inexistente devuelve 0")
    void contarDirectorInexistente() throws Exception {
        assertEquals(0, Ej18_MultiplesInserts.contarPorDirector("DirectorQueNoExiste"));
    }
}
