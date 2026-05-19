package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej108NativeQueriesTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej108NativeQueries q;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Ciudad108.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Ciudad108("Madrid", 3000000));
        em.persist(new Ciudad108("Soria", 40000));
        em.getTransaction().commit();
        q = new Ej108NativeQueries(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void nativo() {
        assertEquals(2, q.contarNativo());
        assertEquals(1, q.grandesNativo(1000000).size());
    }
}
