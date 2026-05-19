package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej015GenericsRepositoryTest {

    record User(Integer id, String nombre) {
    }

    @Test
    void crudCompleto() {
        var repo = new Ej015GenericsRepository<User, Integer>(User::id);
        repo.save(new User(1, "Ana"));
        repo.save(new User(2, "Leo"));
        assertEquals("Ana", repo.findById(1).orElseThrow().nombre());
        assertEquals(2, repo.findAll().size());
        assertTrue(repo.deleteById(1));
        assertTrue(repo.findById(1).isEmpty());
        assertFalse(repo.deleteById(99));
    }

    @Test
    void saveReemplaza() {
        var repo = new Ej015GenericsRepository<User, Integer>(User::id);
        repo.save(new User(1, "Ana"));
        repo.save(new User(1, "Ana2"));
        assertEquals("Ana2", repo.findById(1).orElseThrow().nombre());
        assertEquals(1, repo.findAll().size());
    }
}
