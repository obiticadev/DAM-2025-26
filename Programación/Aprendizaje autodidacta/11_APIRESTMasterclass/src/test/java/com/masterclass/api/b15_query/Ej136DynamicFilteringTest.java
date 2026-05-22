package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej136DynamicFilteringTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej136DynamicFiltering f;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Coche136.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Coche136("Seat", 15000));
        em.persist(new Coche136("Seat", 25000));
        em.persist(new Coche136("BMW", 40000));
        em.getTransaction().commit();
        f = new Ej136DynamicFiltering(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void sinFiltros() {
        assertEquals(3, f.buscar(null, null).size());
    }

    @Test
    void soloMarca() {
        assertEquals(2, f.buscar("Seat", null).size());
    }

    @Test
    void marcaYPrecio() {
        assertEquals(1, f.buscar("Seat", 20000.0).size());
    }

    @Test
    void testRetoExtra01() {
        assertTrue(Ej136DynamicFiltering.esFiltroVacio(" "));
        assertFalse(Ej136DynamicFiltering.esFiltroVacio("Ropa"));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej136DynamicFiltering.esPrecioValido(10.0));
        assertFalse(Ej136DynamicFiltering.esPrecioValido(-5.0));
    }

    @Test
    void testRetoExtra03() {
        assertEquals("select p from Prod136 p", Ej136DynamicFiltering.selectBase());
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej136DynamicFiltering.esPrecioNulo(null));
        assertFalse(Ej136DynamicFiltering.esPrecioNulo(10.0));
    }

    @Test
    void testRetoExtra05() {
        assertTrue(Ej136DynamicFiltering.esCategoriaVacia(" "));
    }

    @Test
    void testRetoExtra06() {
        assertEquals("ropa", Ej136DynamicFiltering.normalizarFiltro("  Ropa  "));
    }

    @Test
    void testRetoExtra07() {
        assertTrue(Ej136DynamicFiltering.debeFiltrar("Ropa", null));
        assertFalse(Ej136DynamicFiltering.debeFiltrar(null, null));
    }

    @Test
    void testRetoExtra08() {
        assertEquals("%ropa%", Ej136DynamicFiltering.formatearLike("Ropa"));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej136DynamicFiltering.sonCategoriasIguales("Ropa", "ROPA"));
    }

    @Test
    void testRetoExtra10() {
        assertEquals("Filtros[Cat=Ropa, MaxPrecio=10.0]", Ej136DynamicFiltering.formatearFiltrosActivos("Ropa", 10.0));
    }

}
