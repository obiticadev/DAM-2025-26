package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej105JpaRepositoryTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej105JpaRepository repo;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Tarea105.class);
        em = emf.createEntityManager();
        repo = new Ej105JpaRepository(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void crudCompleto() {
        var t = repo.save(new Tarea105("comprar"));
        assertNotNull(t.getId());
        assertEquals("comprar", repo.findById(t.getId()).getTitulo());
        repo.save(new Tarea105("otra"));
        assertEquals(2, repo.findAll().size());
        assertTrue(repo.deleteById(t.getId()));
        assertEquals(1, repo.findAll().size());
        assertFalse(repo.deleteById(999L));
    }
}
