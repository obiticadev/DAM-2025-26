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

    @Test
    void testRetoExtra01() {
        assertEquals(20, Ej133Pagination.calcularOffset(2, 10));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej133Pagination.esValida(0, 10));
        assertFalse(Ej133Pagination.esValida(-1, 10));
    }

    @Test
    void testRetoExtra03() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 0, 10);
        assertTrue(Ej133Pagination.tieneSiguiente(p));
    }

    @Test
    void testRetoExtra04() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 1, 10);
        assertTrue(Ej133Pagination.tieneAnterior(p));
    }

    @Test
    void testRetoExtra05() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 0, 10);
        assertTrue(Ej133Pagination.esPrimera(p));
    }

    @Test
    void testRetoExtra06() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 2, 10);
        assertTrue(Ej133Pagination.esUltima(p));
    }

    @Test
    void testRetoExtra07() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 0, 10);
        assertEquals(1, Ej133Pagination.indiceSiguiente(p));
    }

    @Test
    void testRetoExtra08() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 1, 10);
        assertEquals(0, Ej133Pagination.indiceAnterior(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of(), 25, 0, 10);
        assertTrue(Ej133Pagination.estaVacia(p));
    }

    @Test
    void testRetoExtra10() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 0, 10);
        assertEquals("Pagina[0/3, Contenido=1]", Ej133Pagination.formatearPagina(p));
    }

}
