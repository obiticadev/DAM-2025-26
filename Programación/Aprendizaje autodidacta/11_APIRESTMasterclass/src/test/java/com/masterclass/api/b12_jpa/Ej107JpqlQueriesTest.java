package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej107JpqlQueriesTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej107JpqlQueries q;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Empleado107.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Empleado107("Ana", "IT", 3000));
        em.persist(new Empleado107("Alba", "IT", 1000));
        em.persist(new Empleado107("Bea", "RRHH", 2000));
        em.getTransaction().commit();
        q = new Ej107JpqlQueries(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void proyeccionYAgregados() {
        assertEquals(java.util.List.of("Alba", "Ana"), q.nombresPorDepartamento("IT"));
        assertEquals(2000.0, q.salarioMedio("IT"), 0.0001);
        assertEquals(0.0, q.salarioMedio("NADA"), 0.0001);
        assertEquals(2, q.buscarPorNombreLike("a").size());
    }
}
