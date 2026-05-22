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

    @Test
    void testRetoExtra01() {
        var a = new Autor118("Cervantes");
        assertEquals(0, Ej118BidirectionalSync.contarLibros(a));
        a.addLibro(new Libro118("Quijote"));
        assertEquals(1, Ej118BidirectionalSync.contarLibros(a));
    }

    @Test
    void testRetoExtra02() {
        var a = new Autor118("Cervantes");
        assertFalse(Ej118BidirectionalSync.tieneLibros(a));
        a.addLibro(new Libro118("Quijote"));
        assertTrue(Ej118BidirectionalSync.tieneLibros(a));
    }

    @Test
    void testRetoExtra03() {
        var a = new Autor118("Cervantes");
        var l = new Libro118("Quijote");
        a.addLibro(l);
        assertTrue(Ej118BidirectionalSync.esLibroSincronizado(a, l));
    }

    @Test
    void testRetoExtra04() {
        var a = new Autor118("Cervantes");
        a.addLibro(new Libro118("Quijote"));
        assertTrue(Ej118BidirectionalSync.tieneLibroConTitulo(a, "Quijote"));
        assertFalse(Ej118BidirectionalSync.tieneLibroConTitulo(a, "Novela"));
    }

    @Test
    void testRetoExtra05() {
        var a = new Autor118("Cervantes");
        a.addLibro(new Libro118("El Quijote"));
        assertEquals(1, Ej118BidirectionalSync.contarLibrosTituloLargo(a, 8));
    }

    @Test
    void testRetoExtra06() {
        var l = Ej118BidirectionalSync.crearLibro("La Galatea");
        assertNotNull(l);
    }

    @Test
    void testRetoExtra07() {
        var a = new Autor118("Cervantes");
        var l = new Libro118("Quijote");
        a.addLibro(l);
        Ej118BidirectionalSync.desvincularLibro(a, l);
        assertFalse(a.getLibros().contains(l));
        assertNull(l.getAutor());
    }

    @Test
    void testRetoExtra08() {
        var l = new Libro118("Quijote");
        assertFalse(Ej118BidirectionalSync.tieneAutor(l));
    }

    @Test
    void testRetoExtra09() {
        var a = new Autor118("Cervantes");
        var l1 = new Libro118("Q1");
        var l2 = new Libro118("Q2");
        Ej118BidirectionalSync.vincularEnLote(a, java.util.List.of(l1, l2));
        assertEquals(2, a.getLibros().size());
    }

    @Test
    void testRetoExtra10() {
        var a = new Autor118("Cervantes");
        assertEquals("Autor[Libros=0]", Ej118BidirectionalSync.formatearAutor(a));
    }

}
