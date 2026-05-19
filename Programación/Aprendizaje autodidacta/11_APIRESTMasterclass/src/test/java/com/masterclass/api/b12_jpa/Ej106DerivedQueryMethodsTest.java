package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej106DerivedQueryMethodsTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej106DerivedQueryMethods repo;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Articulo106.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Articulo106("libros", 10));
        em.persist(new Articulo106("libros", 30));
        em.persist(new Articulo106("ropa", 50));
        em.getTransaction().commit();
        repo = new Ej106DerivedQueryMethods(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void consultas() {
        assertEquals(2, repo.findByCategoria("libros").size());
        assertEquals(2, repo.findByPrecioMayorQue(20).size());
        assertEquals(2, repo.countByCategoria("libros"));
        assertEquals(0, repo.countByCategoria("nada"));
    }
}
