package com.masterclass.api.b13_rel;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej120FetchLazyEagerTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Long id;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Biblioteca120.class, LibroLazy120.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        var b = new Biblioteca120("Central");
        b.add(new LibroLazy120("a"));
        b.add(new LibroLazy120("b"));
        em.persist(b);
        em.getTransaction().commit();
        id = b.getId();
        em.clear();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void coleccionLazyDetachedLanza() {
        var detached = Ej120FetchLazyEager.cargarYDetach(em, id);
        assertNotNull(detached);
        assertThrows(LazyInitializationException.class, () -> detached.getLibros().size());
    }
}
