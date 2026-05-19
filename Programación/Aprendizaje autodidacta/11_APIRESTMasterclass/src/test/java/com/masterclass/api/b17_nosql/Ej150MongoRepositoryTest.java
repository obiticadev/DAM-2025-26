package com.masterclass.api.b17_nosql;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej150MongoRepositoryTest {

    @Test
    void saveYFindById() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        repo.save(new Pedido149("p1", "ana", 50));
        assertEquals("ana", repo.findById("p1").orElseThrow().cliente());
        assertTrue(repo.findById("zzz").isEmpty());
    }

    @Test
    void saveEsUpsert() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        repo.save(new Pedido149("p1", "ana", 50));
        repo.save(new Pedido149("p1", "ana", 99));
        assertEquals(1, repo.findAll().size());
        assertEquals(99, repo.findById("p1").orElseThrow().total());
    }

    @Test
    void deleteByIdIdempotente() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        repo.save(new Pedido149("p1", "ana", 50));
        assertTrue(repo.deleteById("p1"));
        assertFalse(repo.deleteById("p1"));
    }

    @Test
    void findByClienteDerivado() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        repo.save(new Pedido149("p1", "ana", 50));
        repo.save(new Pedido149("p2", "leo", 70));
        repo.save(new Pedido149("p3", "ana", 10));
        assertEquals(2, repo.findByCliente("ana").size());
        assertTrue(repo.findByCliente("nadie").isEmpty());
    }
}
