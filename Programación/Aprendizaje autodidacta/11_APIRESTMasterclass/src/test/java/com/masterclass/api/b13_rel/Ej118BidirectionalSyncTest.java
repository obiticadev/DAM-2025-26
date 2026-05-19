package com.masterclass.api.b13_rel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej118BidirectionalSyncTest {

    @Test
    void addSincronizaAmbosLados() {
        var autor = new Autor118();
        var libro = new Libro118("DDD");
        autor.addLibro(libro);
        assertEquals(1, autor.getLibros().size());
        assertSame(autor, libro.getAutor(), "el lado dueño debe apuntar al autor");
    }

    @Test
    void removeRompeAmbosLados() {
        var autor = new Autor118();
        var libro = new Libro118("X");
        autor.addLibro(libro);
        autor.removeLibro(libro);
        assertTrue(autor.getLibros().isEmpty());
        assertNull(libro.getAutor());
    }

    @Test
    void addNullFalla() {
        assertThrows(IllegalArgumentException.class, () -> new Autor118().addLibro(null));
    }
}
