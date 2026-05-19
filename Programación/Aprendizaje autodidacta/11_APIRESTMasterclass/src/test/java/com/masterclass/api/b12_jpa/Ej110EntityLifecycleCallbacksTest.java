package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej110EntityLifecycleCallbacksTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Auditable110.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void prePersistRellenaFecha() {
        var a = new Auditable110("x");
        assertNull(a.getCreadoEn());
        Ej110EntityLifecycleCallbacks.guardar(em, a);
        assertNotNull(a.getCreadoEn(), "@PrePersist debe poblar creadoEn");
    }
}
