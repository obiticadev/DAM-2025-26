package com.masterclass.api.b13_rel;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej116OneToManyManyToOneTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Pedido116.class, Linea116.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void persisteLineasEnCascada() {
        var p = new Pedido116();
        p.addLinea(new Linea116("cafe"));
        p.addLinea(new Linea116("te"));
        assertEquals(2, Ej116OneToManyManyToOne.guardarYContarLineas(em, p));
    }

    @Test
    void testRetoExtra01() {
        var p = new Pedido116();
        assertEquals(0, Ej116OneToManyManyToOne.contarLineas(p));
        p.addLinea(new Linea116("A"));
        assertEquals(1, Ej116OneToManyManyToOne.contarLineas(p));
    }

    @Test
    void testRetoExtra02() {
        var p = new Pedido116();
        assertFalse(Ej116OneToManyManyToOne.tieneLineas(p));
        p.addLinea(new Linea116("A"));
        assertTrue(Ej116OneToManyManyToOne.tieneLineas(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new Pedido116();
        var l = new Linea116("A");
        p.addLinea(l);
        assertTrue(Ej116OneToManyManyToOne.esLineaSincronizada(p, l));
    }

    @Test
    void testRetoExtra04() {
        var p = new Pedido116();
        var l = new Linea116("Laptop");
        p.addLinea(l);
        assertTrue(Ej116OneToManyManyToOne.contieneProducto(p, "Laptop"));
        assertFalse(Ej116OneToManyManyToOne.contieneProducto(p, "PC"));
    }

    @Test
    void testRetoExtra05() {
        var p = new Pedido116();
        p.addLinea(new Linea116("Mouse"));
        p.addLinea(new Linea116("Monitor"));
        assertEquals(1, Ej116OneToManyManyToOne.contarProductosConNombreLargo(p, 6));
    }

    @Test
    void testRetoExtra06() {
        var l = Ej116OneToManyManyToOne.crearLinea("Teclado");
        assertNotNull(l);
    }

    @Test
    void testRetoExtra07() {
        var p = new Pedido116();
        p.addLinea(new Linea116("Mouse"));
        assertEquals("Mouse", Ej116OneToManyManyToOne.obtenerPrimerProducto(p));
    }

    @Test
    void testRetoExtra08() {
        var p = new Pedido116();
        p.addLinea(new Linea116("A"));
        assertTrue(Ej116OneToManyManyToOne.todosProductosValidos(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new Pedido116();
        var l = new Linea116("A");
        Ej116OneToManyManyToOne.vincularLinea(p, l);
        assertEquals(p, l.getPedido());
    }

    @Test
    void testRetoExtra10() {
        var p = new Pedido116();
        assertEquals("Pedido[Id=null, Lineas=0]", Ej116OneToManyManyToOne.formatearPedido(p));
    }

}
