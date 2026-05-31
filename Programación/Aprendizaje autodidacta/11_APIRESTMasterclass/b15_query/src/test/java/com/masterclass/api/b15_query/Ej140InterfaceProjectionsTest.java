package com.masterclass.api.b15_query;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej140InterfaceProjectionsTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej140InterfaceProjections p;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Usuario140.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Usuario140("a@b.com", "hashA"));
        em.persist(new Usuario140("c@d.com", "hashB"));
        em.getTransaction().commit();
        p = new Ej140InterfaceProjections(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void proyectaSoloIdEmail() {
        var v = p.vistas();
        assertEquals(2, v.size());
        assertEquals("a@b.com", v.get(0).email());
        assertNotNull(v.get(0).id());
    }

    @Test
    void testRetoExtra01() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertEquals("Laptop", Ej140InterfaceProjections.obtenerNombre(p));
    }

    @Test
    void testRetoExtra02() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertEquals("Tech", Ej140InterfaceProjections.obtenerCategoria(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new Prod140("Laptop", "Tech", 100.0);
        assertTrue(Ej140InterfaceProjections.esPrecioValido(p));
    }

    @Test
    void testRetoExtra04() {
        var p = Ej140InterfaceProjections.crearProducto("PC", "Tech", 200.0);
        assertNotNull(p);
    }

    @Test
    void testRetoExtra05() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertFalse(Ej140InterfaceProjections.tieneId(p));
    }

    @Test
    void testRetoExtra06() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertNull(Ej140InterfaceProjections.obtenerId(p));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("laptop", Ej140InterfaceProjections.normalizarTexto("  Laptop  "));
    }

    @Test
    void testRetoExtra08() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertTrue(Ej140InterfaceProjections.esNuevo(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertTrue(Ej140InterfaceProjections.categoriaContiene(p, "ch"));
    }

    @Test
    void testRetoExtra10() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertEquals("Prod[Nombre=Laptop, Cat=Tech]", Ej140InterfaceProjections.formatearProducto(p));
    }

}
