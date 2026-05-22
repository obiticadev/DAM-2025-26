package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b10_arch.Ej086RepositoryPattern.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej086RepositoryPatternTest {

    @Test
    void escenarioReutilizable() {
        assertEquals(1, Ej086RepositoryPattern.escenario(new LibroRepositoryMem()));
    }

    @Test
    void crudDirecto() {
        var r = new LibroRepositoryMem();
        r.save(new Libro(1L, "DDD"));
        assertEquals("DDD", r.findById(1L).orElseThrow().titulo());
        assertEquals(1, r.count());
        assertTrue(r.deleteById(1L));
        assertEquals(0, r.count());
    }

@Test
    void testDesafioValidarLibro() {
        assertThrows(IllegalArgumentException.class, () -> Ej086RepositoryPattern.desafioValidarLibro(null));
        assertThrows(IllegalArgumentException.class, () -> Ej086RepositoryPattern.desafioValidarLibro(new Libro(null, "Test")));
    }

    @Test
    void testDesafioGuardarEnMemoria() {
        var db = new java.util.LinkedHashMap<Long, Libro>();
        var l = new Libro(1L, "Clean Code");
        Ej086RepositoryPattern.desafioGuardarEnMemoria(db, l);
        assertEquals(l, db.get(1L));
    }

    @Test
    void testDesafioBuscarPorId() {
        var db = new java.util.LinkedHashMap<Long, Libro>();
        var l = new Libro(1L, "Clean Code");
        db.put(1L, l);
        assertTrue(Ej086RepositoryPattern.desafioBuscarPorId(db, 1L).isPresent());
        assertFalse(Ej086RepositoryPattern.desafioBuscarPorId(db, 2L).isPresent());
    }

    @Test
    void testDesafioCopiarLista() {
        var list = List.of(new Libro(1L, "A"), new Libro(2L, "B"));
        var copy = Ej086RepositoryPattern.desafioCopiarLista(list);
        assertEquals(list, copy);
        assertNotSame(list, copy);
    }

    @Test
    void testDesafioEliminarDeMemoria() {
        var db = new java.util.LinkedHashMap<Long, Libro>();
        db.put(1L, new Libro(1L, "A"));
        assertTrue(Ej086RepositoryPattern.desafioEliminarDeMemoria(db, 1L));
        assertFalse(Ej086RepositoryPattern.desafioEliminarDeMemoria(db, 2L));
    }

    @Test
    void testDesafioObtenerTamaño() {
        var db = new java.util.LinkedHashMap<Long, Libro>();
        assertEquals(0, Ej086RepositoryPattern.desafioObtenerTamaño(db));
        db.put(1L, new Libro(1L, "A"));
        assertEquals(1, Ej086RepositoryPattern.desafioObtenerTamaño(db));
    }

    @Test
    void testDesafioEjecutarEscenario() {
        var repo = new LibroRepositoryMem();
        long count = Ej086RepositoryPattern.desafioEjecutarEscenario(repo, new Libro(1L, "A"), new Libro(2L, "B"));
        assertEquals(1, count);
    }

    @Test
    void testDesafioExisteLibro() {
        var repo = new LibroRepositoryMem();
        repo.save(new Libro(1L, "A"));
        assertTrue(Ej086RepositoryPattern.desafioExisteLibro(repo, 1L));
        assertFalse(Ej086RepositoryPattern.desafioExisteLibro(repo, 2L));
    }

    @Test
    void testDesafioGuardarVarios() {
        var repo = new LibroRepositoryMem();
        Ej086RepositoryPattern.desafioGuardarVarios(repo, List.of(new Libro(1L, "A"), new Libro(2L, "B")));
        assertEquals(2, repo.count());
    }

    @Test
    void testDesafioObtenerTitulos() {
        var repo = new LibroRepositoryMem();
        repo.save(new Libro(1L, "A"));
        repo.save(new Libro(2L, "B"));
        assertEquals(List.of("A", "B"), Ej086RepositoryPattern.desafioObtenerTitulos(repo));
    }
}
