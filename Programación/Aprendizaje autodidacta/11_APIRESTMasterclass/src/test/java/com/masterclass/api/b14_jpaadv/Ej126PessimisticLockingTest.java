package com.masterclass.api.b14_jpaadv;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej126PessimisticLockingTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(ArtStock126.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new ArtStock126(1L, 10));
        em.getTransaction().commit();
        em.clear();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void reservaConLock() {
        assertEquals(7, Ej126PessimisticLocking.reservar(em, 1L, 3));
        em.clear();
        assertEquals(7, em.find(ArtStock126.class, 1L).getStock());
    }

    @Test
    void sinStockFalla() {
        assertThrows(IllegalStateException.class,
                () -> Ej126PessimisticLocking.reservar(em, 1L, 50));
    }

    @Test
    void testRetoExtra01() {
        var a = new ArtStock126(1L, 10);
        assertEquals(10, Ej126PessimisticLocking.obtenerStock(a));
    }

    @Test
    void testRetoExtra02() {
        var a = new ArtStock126(1L, 10);
        assertTrue(Ej126PessimisticLocking.tieneStockDisponible(a));
    }

    @Test
    void testRetoExtra03() {
        var a = new ArtStock126(1L, 10);
        assertTrue(Ej126PessimisticLocking.stockSuficiente(a, 5));
        assertFalse(Ej126PessimisticLocking.stockSuficiente(a, 15));
    }

    @Test
    void testRetoExtra04() {
        var a = Ej126PessimisticLocking.crearArticulo(2L, 20);
        assertNotNull(a);
    }

    @Test
    void testRetoExtra05() {
        var a = new ArtStock126(1L, 10);
        Ej126PessimisticLocking.incrementarStock(a, 5);
        assertEquals(15, a.getStock());
    }

    @Test
    void testRetoExtra06() {
        var a = new ArtStock126(1L, 10);
        assertTrue(Ej126PessimisticLocking.decrementarStock(a, 3));
        assertEquals(7, a.getStock());
    }

    @Test
    void testRetoExtra07() {
        var a = new ArtStock126(99L, 10);
        assertEquals(99L, Ej126PessimisticLocking.obtenerId(a));
    }

    @Test
    void testRetoExtra08() {
        var a = new ArtStock126(1L, 0);
        assertTrue(Ej126PessimisticLocking.stockEsCero(a));
    }

    @Test
    void testRetoExtra09() {
        var a = new ArtStock126(1L, 10);
        assertTrue(Ej126PessimisticLocking.stockEsPar(a));
    }

    @Test
    void testRetoExtra10() {
        var a = new ArtStock126(1L, 10);
        assertEquals("Art[Id=1, Stock=10]", Ej126PessimisticLocking.formatearStock(a));
    }

}
