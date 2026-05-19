package com.masterclass.api.b13_rel;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej115OneToOneTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Usuario115.class, Perfil115.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void persisteRelacion11() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        var r = Ej115OneToOne.guardarYRecargar(em, u);
        assertNotNull(r);
        assertNotNull(r.getPerfil());
        assertEquals("hola", r.getPerfil().getBio());
    }
}
