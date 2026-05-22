package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej138CriteriaApiTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej138CriteriaApi c;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Venta138.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Venta138("EU", 100));
        em.persist(new Venta138("EU", 250));
        em.persist(new Venta138("US", 999));
        em.getTransaction().commit();
        c = new Ej138CriteriaApi(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void sumaPorRegion() {
        assertEquals(350.0, c.totalPorRegion("EU"), 0.0001);
        assertEquals(0.0, c.totalPorRegion("ASIA"), 0.0001);
    }

    @Test
    void testRetoExtra01() {
        assertTrue(Ej138CriteriaApi.esFiltroVacio(" "));
        assertFalse(Ej138CriteriaApi.esFiltroVacio("Ropa"));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej138CriteriaApi.esPrecioValido(10.0));
        assertFalse(Ej138CriteriaApi.esPrecioValido(-5.0));
    }

    @Test
    void testRetoExtra03() {
        assertEquals("%ropa%", Ej138CriteriaApi.formatearLike("Ropa"));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej138CriteriaApi.esCategoriaVacia(" "));
    }

    @Test
    void testRetoExtra05() {
        assertTrue(Ej138CriteriaApi.esIdValido(1L));
        assertFalse(Ej138CriteriaApi.esIdValido(null));
    }

    @Test
    void testRetoExtra06() {
        assertTrue(Ej138CriteriaApi.esOrdenacionValida("nombre"));
        assertFalse(Ej138CriteriaApi.esOrdenacionValida("password"));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("nombre", Ej138CriteriaApi.normalizarCampo("  Nombre  "));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej138CriteriaApi.debeFiltrarPrecio(10.0));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej138CriteriaApi.rangoPreciosValido(10.0, 20.0));
        assertFalse(Ej138CriteriaApi.rangoPreciosValido(20.0, 10.0));
    }

    @Test
    void testRetoExtra10() {
        assertEquals("Criteria[Cat=Ropa, MaxPrecio=10.0]", Ej138CriteriaApi.formatearCriteria("Ropa", 10.0));
    }

}
