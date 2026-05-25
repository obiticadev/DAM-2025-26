package com.masterclass.api.b14_jpaadv;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej130InheritanceStrategiesTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Pago130.class, PagoTarjeta130.class, PagoTransferencia130.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void herenciaPolimorfica() {
        assertEquals(2, Ej130InheritanceStrategies.guardarYContar(em));
        em.clear();
        var soloTarjeta = Ej130InheritanceStrategies.soloTarjeta(em);
        assertEquals(1, soloTarjeta.size());
        assertInstanceOf(PagoTarjeta130.class, soloTarjeta.get(0));
    }

    @Test
    void testRetoExtra01() {
        var p = new PagoTarjeta130(100, "1234");
        assertEquals(100.0, Ej130InheritanceStrategies.obtenerImporte(p), 0.001);
    }

    @Test
    void testRetoExtra02() {
        var p = new PagoTarjeta130(100, "1234");
        assertTrue(Ej130InheritanceStrategies.esPagoTarjeta(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new PagoTransferencia130(100, "ES12");
        assertTrue(Ej130InheritanceStrategies.esPagoTransferencia(p));
    }

    @Test
    void testRetoExtra04() {
        var p = Ej130InheritanceStrategies.crearPagoTarjeta(100, "1234");
        assertNotNull(p);
    }

    @Test
    void testRetoExtra05() {
        var p = Ej130InheritanceStrategies.crearPagoTransferencia(100, "ES12");
        assertNotNull(p);
    }

    @Test
    void testRetoExtra06() {
        var p = new PagoTarjeta130(100, "1234");
        assertTrue(Ej130InheritanceStrategies.importeSupera(p, 50));
    }

    @Test
    void testRetoExtra07() {
        var p = new PagoTarjeta130(100, "1234");
        assertNull(Ej130InheritanceStrategies.obtenerId(p));
    }

    @Test
    void testRetoExtra08() {
        var p = new PagoTarjeta130(100, "1234");
        assertEquals("1234", Ej130InheritanceStrategies.obtenerPan(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new PagoTransferencia130(100, "ES12");
        assertEquals("ES12", Ej130InheritanceStrategies.obtenerIban(p));
    }

    @Test
    void testRetoExtra10() {
        var p = new PagoTarjeta130(100, "1234");
        assertEquals("Pago[Tipo=TARJETA, Importe=100.0]", Ej130InheritanceStrategies.formatearPago(p));
    }

}
