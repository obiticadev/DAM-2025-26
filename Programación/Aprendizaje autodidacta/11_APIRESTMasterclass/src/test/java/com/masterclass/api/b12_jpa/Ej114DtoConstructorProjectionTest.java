package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej114DtoConstructorProjectionTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej114DtoConstructorProjection q;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Pedido114.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Pedido114(100.0));
        em.persist(new Pedido114(250.0));
        em.getTransaction().commit();
        q = new Ej114DtoConstructorProjection(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void proyectaADto() {
        var r = q.resumen();
        assertEquals(2, r.size());
        assertEquals(100.0, r.get(0).total(), 0.0001);
        assertNotNull(r.get(0).id());
    }
}
