package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej141AggregationsGroupByTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej141AggregationsGroupBy a;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Venta141.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Venta141("A"));
        em.persist(new Venta141("A"));
        em.persist(new Venta141("B"));
        em.getTransaction().commit();
        a = new Ej141AggregationsGroupBy(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void conteoPorCategoria() {
        var m = a.conteoPorCategoria();
        assertEquals(2L, m.get("A"));
        assertEquals(1L, m.get("B"));
        assertEquals(2, m.size());
    }
}
