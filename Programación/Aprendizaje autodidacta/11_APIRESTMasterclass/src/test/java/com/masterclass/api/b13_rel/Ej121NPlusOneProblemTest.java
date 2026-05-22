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

    @Test
    void testRetoExtra01() {
        var b = new Blog121("Tech");
        assertEquals(0, Ej121NPlusOneProblem.contarPosts(b));
        b.add(new Post121("JPA"));
        assertEquals(1, Ej121NPlusOneProblem.contarPosts(b));
    }

    @Test
    void testRetoExtra02() {
        var b = new Blog121("Tech");
        assertFalse(Ej121NPlusOneProblem.tienePosts(b));
        b.add(new Post121("JPA"));
        assertTrue(Ej121NPlusOneProblem.tienePosts(b));
    }

    @Test
    void testRetoExtra03() {
        var b = new Blog121("Tech");
        var p = new Post121("JPA");
        b.add(p);
        assertTrue(Ej121NPlusOneProblem.contienePost(b, p));
    }

    @Test
    void testRetoExtra04() {
        var b = new Blog121("Tech");
        b.add(new Post121("JPA"));
        assertTrue(Ej121NPlusOneProblem.tieneTitulo(b, "JPA"));
        assertFalse(Ej121NPlusOneProblem.tieneTitulo(b, "Spring"));
    }

    @Test
    void testRetoExtra05() {
        var b = new Blog121("Tech");
        b.add(new Post121("Hibernate"));
        assertEquals(1, Ej121NPlusOneProblem.contarPostsTituloLargo(b, 5));
    }

    @Test
    void testRetoExtra06() {
        var p = Ej121NPlusOneProblem.crearPost("A");
        assertNotNull(p);
    }

    @Test
    void testRetoExtra07() {
        var b = new Blog121("Tech");
        b.add(new Post121("A"));
        assertTrue(Ej121NPlusOneProblem.removerPrimerPost(b));
        assertEquals(0, b.getPosts().size());
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej121NPlusOneProblem.esValido(new Blog121("A")));
        assertFalse(Ej121NPlusOneProblem.esValido(null));
    }

    @Test
    void testRetoExtra09() {
        var b = new Blog121("Tech");
        var p1 = new Post121("A");
        var p2 = new Post121("B");
        Ej121NPlusOneProblem.vincularPosts(b, java.util.List.of(p1, p2));
        assertEquals(2, b.getPosts().size());
    }

    @Test
    void testRetoExtra10() {
        var b = new Blog121("Tech");
        assertEquals("Blog[Posts=0]", Ej121NPlusOneProblem.formatearBlog(b));
    }

}
