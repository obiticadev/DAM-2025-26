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
}
