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
}
