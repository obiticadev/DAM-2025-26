package com.masterclass.api.b14_jpaadv;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej126PessimisticLockingTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(ArtStock126.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new ArtStock126(1L, 10));
        em.getTransaction().commit();
        em.clear();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void reservaConLock() {
        assertEquals(7, Ej126PessimisticLocking.reservar(em, 1L, 3));
        em.clear();
        assertEquals(7, em.find(ArtStock126.class, 1L).getStock());
    }

    @Test
    void sinStockFalla() {
        assertThrows(IllegalStateException.class,
                () -> Ej126PessimisticLocking.reservar(em, 1L, 50));
    }
}
