package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej140InterfaceProjectionsTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej140InterfaceProjections p;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Usuario140.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Usuario140("a@b.com", "hashA"));
        em.persist(new Usuario140("c@d.com", "hashB"));
        em.getTransaction().commit();
        p = new Ej140InterfaceProjections(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void proyectaSoloIdEmail() {
        var v = p.vistas();
        assertEquals(2, v.size());
        assertEquals("a@b.com", v.get(0).email());
        assertNotNull(v.get(0).id());
    }
}
