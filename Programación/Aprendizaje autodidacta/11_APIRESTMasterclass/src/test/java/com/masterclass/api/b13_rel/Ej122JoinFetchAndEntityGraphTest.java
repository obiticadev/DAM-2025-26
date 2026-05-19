package com.masterclass.api.b13_rel;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej122JoinFetchAndEntityGraphTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Long id;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Proyecto122.class, Tarea122.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        var p = new Proyecto122("Apolo");
        p.add(new Tarea122("t1"));
        p.add(new Tarea122("t2"));
        em.persist(p);
        em.getTransaction().commit();
        id = p.getId();
        em.clear();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void entityGraphInicializa() {
        var p = Ej122JoinFetchAndEntityGraph.cargarConGrafo(em, id);
        em.clear();
        assertEquals(2, p.getTareas().size());
    }

    @Test
    void joinFetchInicializa() {
        var p = Ej122JoinFetchAndEntityGraph.cargarConJoinFetch(em, id);
        em.clear();
        assertEquals(2, p.getTareas().size());
    }
}
