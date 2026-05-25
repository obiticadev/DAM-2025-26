package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej137SpecificationsTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej137Specifications s;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Libro137.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Libro137("tecnico", 500));
        em.persist(new Libro137("tecnico", 100));
        em.persist(new Libro137("novela", 800));
        em.getTransaction().commit();
        s = new Ej137Specifications(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void predicadosComponibles() {
        assertEquals(3, s.buscar(null, null).size());
        assertEquals(2, s.buscar("tecnico", null).size());
        assertEquals(1, s.buscar("tecnico", 300).size());
    }

    @Test
    void testRetoExtra01() {
        assertTrue(Ej137Specifications.esFiltroVacio(" "));
        assertFalse(Ej137Specifications.esFiltroVacio("Ropa"));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej137Specifications.esPrecioValido(10.0));
        assertFalse(Ej137Specifications.esPrecioValido(-5.0));
    }

    @Test
    void testRetoExtra03() {
        assertEquals("%ropa%", Ej137Specifications.formatearLike("Ropa"));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej137Specifications.esPrecioNulo(null));
        assertFalse(Ej137Specifications.esPrecioNulo(10.0));
    }

    @Test
    void testRetoExtra05() {
        assertTrue(Ej137Specifications.esCategoriaVacia(" "));
    }

    @Test
    void testRetoExtra06() {
        assertTrue(Ej137Specifications.requiereFiltroNombre("Laptop"));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("ROPA", Ej137Specifications.normalizarCategoria("  Ropa  "));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej137Specifications.tieneEspecificaciones("Laptop", null, null));
        assertFalse(Ej137Specifications.tieneEspecificaciones(null, null, null));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej137Specifications.preciosCoherentes(10.0, 20.0));
        assertFalse(Ej137Specifications.preciosCoherentes(20.0, 10.0));
    }

    @Test
    void testRetoExtra10() {
        assertEquals("Specs[Nom=Ropa, Min=10.0, Max=20.0]", Ej137Specifications.formatearEspecificacion("Ropa", 10.0, 20.0));
    }

}
