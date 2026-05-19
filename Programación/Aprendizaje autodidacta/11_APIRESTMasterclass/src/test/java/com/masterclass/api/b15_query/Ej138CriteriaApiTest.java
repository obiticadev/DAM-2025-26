package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej138CriteriaApiTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej138CriteriaApi c;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Venta138.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Venta138("EU", 100));
        em.persist(new Venta138("EU", 250));
        em.persist(new Venta138("US", 999));
        em.getTransaction().commit();
        c = new Ej138CriteriaApi(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void sumaPorRegion() {
        assertEquals(350.0, c.totalPorRegion("EU"), 0.0001);
        assertEquals(0.0, c.totalPorRegion("ASIA"), 0.0001);
    }
}
