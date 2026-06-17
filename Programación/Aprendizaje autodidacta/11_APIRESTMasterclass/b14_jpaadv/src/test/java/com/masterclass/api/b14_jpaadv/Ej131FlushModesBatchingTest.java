package com.masterclass.api.b14_jpaadv;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej131FlushModesBatchingTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Item131.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void flushHaceVisibleAntesDelCommit() {
        assertEquals(5, Ej131FlushModesBatching.persistirYFlush(em, 5));
    }

    @Test
    void testRetoExtra01() {
        var i = new Item131("Laptop");
        assertEquals("Laptop", Ej131FlushModesBatching.obtenerNombre(i));
    }

    @Test
    void testRetoExtra02() {
        var i = Ej131FlushModesBatching.crearItem("Teclado");
        assertNotNull(i);
    }

    @Test
    void testRetoExtra03() {
        var i = new Item131("Laptop");
        assertFalse(Ej131FlushModesBatching.tieneId(i));
    }

    @Test
    void testRetoExtra04() {
        var i = new Item131("Laptop");
        assertTrue(Ej131FlushModesBatching.esNuevo(i));
    }

    @Test
    void testRetoExtra05() {
        var i = new Item131("Laptop Dell");
        assertTrue(Ej131FlushModesBatching.nombreContiene(i, "Dell"));
        assertFalse(Ej131FlushModesBatching.nombreContiene(i, "HP"));
    }

    @Test
    void testRetoExtra06() {
        var i = new Item131("Laptop");
        assertTrue(Ej131FlushModesBatching.esValido(i));
    }

    @Test
    void testRetoExtra07() {
        var i = new Item131("Laptop");
        assertNull(Ej131FlushModesBatching.obtenerId(i));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej131FlushModesBatching.esFlushModeCommit(FlushModeType.COMMIT));
        assertFalse(Ej131FlushModesBatching.esFlushModeCommit(FlushModeType.AUTO));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej131FlushModesBatching.esFlushModeAuto(FlushModeType.AUTO));
        assertFalse(Ej131FlushModesBatching.esFlushModeAuto(FlushModeType.COMMIT));
    }

    @Test
    void testRetoExtra10() {
        var i = new Item131("Laptop");
        assertEquals("Item[Id=null, Nombre=Laptop]", Ej131FlushModesBatching.formatearItem(i));
    }

}
