package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej104IdGenerationStrategiesTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Nota104.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void idsAutogenerados() {
        Long[] ids = Ej104IdGenerationStrategies.guardarDos(em, new Nota104("a"), new Nota104("b"));
        assertNotNull(ids[0]);
        assertNotNull(ids[1]);
        assertNotEquals(ids[0], ids[1]);
    }
}
