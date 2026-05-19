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
}
