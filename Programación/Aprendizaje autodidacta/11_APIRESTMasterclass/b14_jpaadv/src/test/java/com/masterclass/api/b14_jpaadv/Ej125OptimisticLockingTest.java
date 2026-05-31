package com.masterclass.api.b14_jpaadv;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej125OptimisticLockingTest {

    private EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(ProdVer125.class);
    }

    @AfterEach
    void tearDown() {
        emf.close();
    }

    @Test
    void guardaYActualiza() {
        var em = emf.createEntityManager();
        Long id = Ej125OptimisticLocking.guardar(em, new ProdVer125(100));
        Ej125OptimisticLocking.actualizarPrecio(em, id, 120);
        em.clear();
        assertEquals(120.0, em.find(ProdVer125.class, id).getPrecio(), 0.0001);
        em.close();
    }

    @Test
    void conflictoDeVersionLanza() {
        var em1 = emf.createEntityManager();
        Long id = Ej125OptimisticLocking.guardar(em1, new ProdVer125(100));
        em1.clear();

        var a = em1.find(ProdVer125.class, id);

        var em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        var b = em2.find(ProdVer125.class, id);
        b.setPrecio(200);
        em2.getTransaction().commit();
        em2.close();

        em1.getTransaction().begin();
        a.setPrecio(300);
        assertThrows(Exception.class, () -> {
            try {
                em1.getTransaction().commit();
            } catch (OptimisticLockException e) {
                throw e;
            }
        });
        em1.close();
    }

    @Test
    void testRetoExtra01() {
        var p = new ProdVer125(100);
        assertEquals(100.0, Ej125OptimisticLocking.obtenerPrecio(p), 0.001);
    }

    @Test
    void testRetoExtra02() {
        var p = new ProdVer125(100);
        assertEquals(0, Ej125OptimisticLocking.obtenerVersion(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new ProdVer125(100);
        assertTrue(Ej125OptimisticLocking.precioEsMayor(p, 50));
        assertFalse(Ej125OptimisticLocking.precioEsMayor(p, 150));
    }

    @Test
    void testRetoExtra04() {
        var p = new ProdVer125(100);
        assertTrue(Ej125OptimisticLocking.esVersionInicial(p));
    }

    @Test
    void testRetoExtra05() {
        var p = new ProdVer125(100);
        Ej125OptimisticLocking.incrementarPrecioPorcentaje(p, 10);
        assertEquals(110.0, p.getPrecio(), 0.001);
    }

    @Test
    void testRetoExtra06() {
        var p = Ej125OptimisticLocking.crearProducto(80);
        assertNotNull(p);
    }

    @Test
    void testRetoExtra07() {
        var p = new ProdVer125(100);
        Ej125OptimisticLocking.aplicarDescuento(p, 20);
        assertEquals(80.0, p.getPrecio(), 0.001);
    }

    @Test
    void testRetoExtra08() {
        var p = new ProdVer125(100);
        assertFalse(Ej125OptimisticLocking.tieneId(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new ProdVer125(100);
        assertTrue(Ej125OptimisticLocking.precioEsValido(p));
    }

    @Test
    void testRetoExtra10() {
        var p = new ProdVer125(100);
        assertEquals("Prod[Precio=100.0, Ver=0]", Ej125OptimisticLocking.formatearProducto(p));
    }

}
