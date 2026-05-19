package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej109ModifyingQueriesTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej109ModifyingQueries q;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Prod109.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Prod109("a", 100, 5));
        em.persist(new Prod109("a", 200, 0));
        em.persist(new Prod109("b", 50, 3));
        em.getTransaction().commit();
        q = new Ej109ModifyingQueries(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void updateMasivo() {
        assertEquals(2, q.subirPrecios("a", 10));
    }

    @Test
    void deleteMasivo() {
        assertEquals(1, q.borrarSinStock());
    }
}
