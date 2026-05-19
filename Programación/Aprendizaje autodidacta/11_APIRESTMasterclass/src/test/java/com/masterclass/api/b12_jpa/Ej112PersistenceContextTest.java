package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej112PersistenceContextTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Long id;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Doc112.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        var d = new Doc112("original");
        em.persist(d);
        em.getTransaction().commit();
        id = d.getId();
        em.clear();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void dirtyCheckingPersisteSinSave() {
        Ej112PersistenceContext.cambioSinSave(em, id, "nuevo");
        em.clear();
        assertEquals("nuevo", em.find(Doc112.class, id).getNombre());
    }

    @Test
    void detachedNoPersiste() {
        Ej112PersistenceContext.cambioEnDetachedNoPersiste(em, id, "ignorado");
        em.clear();
        assertEquals("original", em.find(Doc112.class, id).getNombre());
    }
}
