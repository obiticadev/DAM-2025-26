package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej134SortingTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej134Sorting s;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Prod134.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Prod134("Cebra", 30));
        em.persist(new Prod134("Avispa", 10));
        em.persist(new Prod134("Mosca", 20));
        em.getTransaction().commit();
        s = new Ej134Sorting(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void ordenPorNombreAsc() {
        var l = s.ordenar("nombre", true);
        assertEquals("Avispa", l.get(0).getNombre());
    }

    @Test
    void ordenPorPrecioDesc() {
        var l = s.ordenar("precio", false);
        assertEquals(30.0, l.get(0).getPrecio(), 0.0001);
    }

    @Test
    void campoNoPermitido() {
        assertThrows(IllegalArgumentException.class, () -> s.ordenar("password; DROP", true));
    }
}
