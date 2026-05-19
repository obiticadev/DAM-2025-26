package com.masterclass.api.b13_rel;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej121NPlusOneProblemTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Blog121.class, Post121.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        var b1 = new Blog121("b1");
        b1.add(new Post121("p1"));
        b1.add(new Post121("p2"));
        var b2 = new Blog121("b2");
        b2.add(new Post121("p3"));
        em.persist(b1);
        em.persist(b2);
        em.getTransaction().commit();
        em.clear();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void joinFetchInicializaColecciones() {
        var blogs = Ej121NPlusOneProblem.todosConPosts(em);
        em.clear();
        assertEquals(2, blogs.size());
        int total = blogs.stream().mapToInt(b -> b.getPosts().size()).sum();
        assertEquals(3, total, "posts deben estar inicializados tras el JOIN FETCH");
    }
}
