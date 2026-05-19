package com.masterclass.api.b14_jpaadv;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej125OptimisticLockingTest {

    private EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(ProdVer125.class);
    }

    @AfterEach
    void tearDown() {
        emf.close();
    }

    @Test
    void guardaYActualiza() {
        var em = emf.createEntityManager();
        Long id = Ej125OptimisticLocking.guardar(em, new ProdVer125(100));
        Ej125OptimisticLocking.actualizarPrecio(em, id, 120);
        em.clear();
        assertEquals(120.0, em.find(ProdVer125.class, id).getPrecio(), 0.0001);
        em.close();
    }

    @Test
    void conflictoDeVersionLanza() {
        var em1 = emf.createEntityManager();
        Long id = Ej125OptimisticLocking.guardar(em1, new ProdVer125(100));
        em1.clear();

        var a = em1.find(ProdVer125.class, id);

        var em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        var b = em2.find(ProdVer125.class, id);
        b.setPrecio(200);
        em2.getTransaction().commit();
        em2.close();

        em1.getTransaction().begin();
        a.setPrecio(300);
        assertThrows(Exception.class, () -> {
            try {
                em1.getTransaction().commit();
            } catch (OptimisticLockException e) {
                throw e;
            }
        });
        em1.close();
    }
}
