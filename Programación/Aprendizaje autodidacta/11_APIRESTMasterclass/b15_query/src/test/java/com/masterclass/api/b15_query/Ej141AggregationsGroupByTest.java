package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej141AggregationsGroupByTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej141AggregationsGroupBy a;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Venta141.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Venta141("A"));
        em.persist(new Venta141("A"));
        em.persist(new Venta141("B"));
        em.getTransaction().commit();
        a = new Ej141AggregationsGroupBy(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void conteoPorCategoria() {
        var m = a.conteoPorCategoria();
        assertEquals(2L, m.get("A"));
        assertEquals(1L, m.get("B"));
        assertEquals(2, m.size());
    }

    @Test
    void testRetoExtra01() {
        var i = new Item141("Laptop", "Tech", 100);
        assertEquals("Laptop", Ej141AggregationsGroupBy.obtenerNombre(i));
    }

    @Test
    void testRetoExtra02() {
        var i = new Item141("Laptop", "Tech", 100);
        assertEquals("Tech", Ej141AggregationsGroupBy.obtenerCategoria(i));
    }

    @Test
    void testRetoExtra03() {
        var i = new Item141("Laptop", "Tech", 100.0);
        assertEquals(100.0, Ej141AggregationsGroupBy.obtenerPrecio(i), 0.001);
    }

    @Test
    void testRetoExtra04() {
        var i = Ej141AggregationsGroupBy.crearItem("PC", "Tech", 200.0);
        assertNotNull(i);
    }

    @Test
    void testRetoExtra05() {
        var i = new Item141("Laptop", "Tech", 100);
        assertFalse(Ej141AggregationsGroupBy.tieneId(i));
    }

    @Test
    void testRetoExtra06() {
        var i = new Item141("Laptop", "Tech", 100);
        assertNull(Ej141AggregationsGroupBy.obtenerId(i));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("laptop", Ej141AggregationsGroupBy.normalizarTexto("  Laptop  "));
    }

    @Test
    void testRetoExtra08() {
        var i = new Item141("Laptop", "Tech", 100);
        assertTrue(Ej141AggregationsGroupBy.esNuevo(i));
    }

    @Test
    void testRetoExtra09() {
        var i = new Item141("Laptop", "Tech", 100);
        assertTrue(Ej141AggregationsGroupBy.categoriaContiene(i, "ch"));
    }

    @Test
    void testRetoExtra10() {
        var i = new Item141("Laptop", "Tech", 100);
        assertEquals("Item[Nombre=Laptop, Cat=Tech, Precio=100.0]", Ej141AggregationsGroupBy.formatearItem(i));
    }

}
