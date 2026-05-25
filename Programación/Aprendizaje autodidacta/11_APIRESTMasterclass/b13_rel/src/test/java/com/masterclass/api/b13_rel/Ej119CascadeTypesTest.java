package com.masterclass.api.b13_rel;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej119CascadeTypesTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Factura119.class, Concepto119.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void cascadaYOrphanRemoval() {
        var f = new Factura119();
        f.add(new Concepto119("c1"));
        f.add(new Concepto119("c2"));
        assertEquals(2, Ej119CascadeTypes.guardarEnCascada(em, f));
        assertEquals(1, Ej119CascadeTypes.quitarConceptoYContar(em, f.getId()));
    }

    @Test
    void testRetoExtra01() {
        var f = new Factura119();
        assertEquals(0, Ej119CascadeTypes.contarConceptos(f));
        f.add(new Concepto119("Servicio"));
        assertEquals(1, Ej119CascadeTypes.contarConceptos(f));
    }

    @Test
    void testRetoExtra02() {
        var f = new Factura119();
        assertFalse(Ej119CascadeTypes.tieneConceptos(f));
        f.add(new Concepto119("Servicio"));
        assertTrue(Ej119CascadeTypes.tieneConceptos(f));
    }

    @Test
    void testRetoExtra03() {
        var f = new Factura119();
        var c = new Concepto119("Servicio");
        f.add(c);
        assertTrue(Ej119CascadeTypes.contieneConcepto(f, c));
    }

    @Test
    void testRetoExtra04() {
        var f = new Factura119();
        f.add(new Concepto119("Premium"));
        assertTrue(Ej119CascadeTypes.tieneDescripcion(f, "Premium"));
        assertFalse(Ej119CascadeTypes.tieneDescripcion(f, "Normal"));
    }

    @Test
    void testRetoExtra05() {
        var f = new Factura119();
        f.add(new Concepto119("Mantenimiento"));
        assertEquals(1, Ej119CascadeTypes.contarConceptosDescLarga(f, 8));
    }

    @Test
    void testRetoExtra06() {
        var c = Ej119CascadeTypes.crearConcepto("Soporte");
        assertNotNull(c);
    }

    @Test
    void testRetoExtra07() {
        var f = new Factura119();
        f.add(new Concepto119("A"));
        assertTrue(Ej119CascadeTypes.removerPrimerConcepto(f));
        assertEquals(0, f.getConceptos().size());
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej119CascadeTypes.esValida(new Factura119()));
        assertFalse(Ej119CascadeTypes.esValida(null));
    }

    @Test
    void testRetoExtra09() {
        var f = new Factura119();
        var c1 = new Concepto119("A");
        var c2 = new Concepto119("B");
        Ej119CascadeTypes.vincularConceptos(f, java.util.List.of(c1, c2));
        assertEquals(2, f.getConceptos().size());
    }

    @Test
    void testRetoExtra10() {
        var f = new Factura119();
        assertEquals("Factura[Id=null, Conceptos=0]", Ej119CascadeTypes.formatearFactura(f));
    }

}
