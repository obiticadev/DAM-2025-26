package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej135SliceVsPageTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej135SliceVsPage s;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Reg135.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        for (int i = 0; i < 12; i++) em.persist(new Reg135("r" + i));
        em.getTransaction().commit();
        s = new Ej135SliceVsPage(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void haySiguiente() {
        var sl = s.slice(0, 5);
        assertEquals(5, sl.contenido().size());
        assertTrue(sl.haySiguiente());
    }

    @Test
    void ultimoSliceSinSiguiente() {
        var sl = s.slice(2, 5);
        assertEquals(2, sl.contenido().size());
        assertFalse(sl.haySiguiente());
    }
}
