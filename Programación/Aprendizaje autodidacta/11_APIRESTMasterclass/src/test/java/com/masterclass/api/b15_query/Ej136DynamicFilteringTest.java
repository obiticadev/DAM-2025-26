package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej136DynamicFilteringTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej136DynamicFiltering f;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Coche136.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Coche136("Seat", 15000));
        em.persist(new Coche136("Seat", 25000));
        em.persist(new Coche136("BMW", 40000));
        em.getTransaction().commit();
        f = new Ej136DynamicFiltering(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void sinFiltros() {
        assertEquals(3, f.buscar(null, null).size());
    }

    @Test
    void soloMarca() {
        assertEquals(2, f.buscar("Seat", null).size());
    }

    @Test
    void marcaYPrecio() {
        assertEquals(1, f.buscar("Seat", 20000.0).size());
    }
}
