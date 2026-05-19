package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej133PaginationTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej133Pagination p;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Item133.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        for (int i = 1; i <= 25; i++) em.persist(new Item133("i" + i));
        em.getTransaction().commit();
        p = new Ej133Pagination(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void paginaIntermedia() {
        var pg = p.page(1, 10);
        assertEquals(10, pg.contenido().size());
        assertEquals(25, pg.total());
        assertEquals(3, pg.totalPaginas());
    }

    @Test
    void ultimaPaginaParcial() {
        var pg = p.page(2, 10);
        assertEquals(5, pg.contenido().size());
    }

    @Test
    void parametrosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> p.page(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> p.page(0, 0));
    }
}
