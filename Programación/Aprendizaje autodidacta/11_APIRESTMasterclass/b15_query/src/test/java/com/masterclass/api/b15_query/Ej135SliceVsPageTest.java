package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej135SliceVsPageTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej135SliceVsPage s;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Reg135.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        for (int i = 0; i < 12; i++) em.persist(new Reg135("r" + i));
        em.getTransaction().commit();
        s = new Ej135SliceVsPage(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void haySiguiente() {
        var sl = s.slice(0, 5);
        assertEquals(5, sl.contenido().size());
        assertTrue(sl.haySiguiente());
    }

    @Test
    void ultimoSliceSinSiguiente() {
        var sl = s.slice(2, 5);
        assertEquals(2, sl.contenido().size());
        assertFalse(sl.haySiguiente());
    }

    @Test
    void testRetoExtra01() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 0, 10, true);
        assertTrue(Ej135SliceVsPage.tieneSiguiente(s));
    }

    @Test
    void testRetoExtra02() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 1, 10, false);
        assertTrue(Ej135SliceVsPage.tieneAnterior(s));
    }

    @Test
    void testRetoExtra03() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 0, 10, false);
        assertTrue(Ej135SliceVsPage.esPrimera(s));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej135SliceVsPage.esValida(0, 10));
    }

    @Test
    void testRetoExtra05() {
        assertEquals(20, Ej135SliceVsPage.calcularOffset(2, 10));
    }

    @Test
    void testRetoExtra06() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 0, 10, false);
        assertEquals(1, Ej135SliceVsPage.tamanoContenido(s));
    }

    @Test
    void testRetoExtra07() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of(), 0, 10, false);
        assertTrue(Ej135SliceVsPage.estaVacio(s));
    }

    @Test
    void testRetoExtra08() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 3, 10, false);
        assertEquals(3, Ej135SliceVsPage.numeroPagina(s));
    }

    @Test
    void testRetoExtra09() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 0, 10, false);
        assertTrue(Ej135SliceVsPage.tamanoEsCorrecto(s, 10));
    }

    @Test
    void testRetoExtra10() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 0, 10, false);
        assertEquals("Slice[Pagina=0, HasNext=false]", Ej135SliceVsPage.formatearSlice(s));
    }

}
