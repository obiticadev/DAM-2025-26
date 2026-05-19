package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej137SpecificationsTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej137Specifications s;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Libro137.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Libro137("tecnico", 500));
        em.persist(new Libro137("tecnico", 100));
        em.persist(new Libro137("novela", 800));
        em.getTransaction().commit();
        s = new Ej137Specifications(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void predicadosComponibles() {
        assertEquals(3, s.buscar(null, null).size());
        assertEquals(2, s.buscar("tecnico", null).size());
        assertEquals(1, s.buscar("tecnico", 300).size());
    }
}
