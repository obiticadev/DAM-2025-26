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

    @Test
    void testRetoExtra01() {
        var b = new Biblioteca120("Central");
        assertEquals(0, Ej120FetchLazyEager.contarLibros(b));
        b.add(new LibroLazy120("A"));
        assertEquals(1, Ej120FetchLazyEager.contarLibros(b));
    }

    @Test
    void testRetoExtra02() {
        var b = new Biblioteca120("Central");
        assertFalse(Ej120FetchLazyEager.tieneLibros(b));
        b.add(new LibroLazy120("A"));
        assertTrue(Ej120FetchLazyEager.tieneLibros(b));
    }

    @Test
    void testRetoExtra03() {
        var b = new Biblioteca120("Central");
        var l = new LibroLazy120("A");
        b.add(l);
        assertTrue(Ej120FetchLazyEager.contieneLibro(b, l));
    }

    @Test
    void testRetoExtra04() {
        var b = new Biblioteca120("Central");
        b.add(new LibroLazy120("Quijote"));
        assertTrue(Ej120FetchLazyEager.tieneTitulo(b, "Quijote"));
        assertFalse(Ej120FetchLazyEager.tieneTitulo(b, "Celestina"));
    }

    @Test
    void testRetoExtra05() {
        var b = new Biblioteca120("Central");
        b.add(new LibroLazy120("Quijote"));
        assertEquals(1, Ej120FetchLazyEager.contarLibrosTituloLargo(b, 5));
    }

    @Test
    void testRetoExtra06() {
        var l = Ej120FetchLazyEager.crearLibro("A");
        assertNotNull(l);
    }

    @Test
    void testRetoExtra07() {
        var b = new Biblioteca120("Central");
        b.add(new LibroLazy120("A"));
        assertTrue(Ej120FetchLazyEager.removerPrimerLibro(b));
        assertEquals(0, b.getLibros().size());
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej120FetchLazyEager.esValida(new Biblioteca120("A")));
        assertFalse(Ej120FetchLazyEager.esValida(null));
    }

    @Test
    void testRetoExtra09() {
        var b = new Biblioteca120("Central");
        var l1 = new LibroLazy120("A");
        var l2 = new LibroLazy120("B");
        Ej120FetchLazyEager.vincularLibros(b, java.util.List.of(l1, l2));
        assertEquals(2, b.getLibros().size());
    }

    @Test
    void testRetoExtra10() {
        var b = new Biblioteca120("Central");
        assertEquals("Biblioteca[Id=null, Libros=0]", Ej120FetchLazyEager.formatearBiblioteca(b));
    }

}
