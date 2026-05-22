package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej112PersistenceContextTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Long id;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Doc112.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        var d = new Doc112("original");
        em.persist(d);
        em.getTransaction().commit();
        id = d.getId();
        em.clear();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void dirtyCheckingPersisteSinSave() {
        Ej112PersistenceContext.cambioSinSave(em, id, "nuevo");
        em.clear();
        assertEquals("nuevo", em.find(Doc112.class, id).getNombre());
    }

    @Test
    void detachedNoPersiste() {
        Ej112PersistenceContext.cambioEnDetachedNoPersiste(em, id, "ignorado");
        em.clear();
        assertEquals("original", em.find(Doc112.class, id).getNombre());
    }

@Test
    void testDesafioEntityManagerActivo() {
        assertTrue(Ej112PersistenceContext.desafioEntityManagerActivo(em));
        assertFalse(Ej112PersistenceContext.desafioEntityManagerActivo(null));
    }

    @Test
    void testDesafioEsEntidadGestionada() {
        var u = Ej112PersistenceContext.desafioCrearInstanciaUsuario("A");
        em.persist(u);
        assertTrue(Ej112PersistenceContext.desafioEsEntidadGestionada(em, u));
    }

    @Test
    void testDesafioPersistirEntidad() {
        var u = Ej112PersistenceContext.desafioCrearInstanciaUsuario("A");
        Ej112PersistenceContext.desafioPersistirEntidad(em, u);
        assertTrue(em.contains(u));
    }

    @Test
    void testDesafioSincronizarContexto() {
        assertDoesNotThrow(() -> Ej112PersistenceContext.desafioSincronizarContexto(em));
    }

    @Test
    void testDesafioDesasociarEntidad() {
        var u = Ej112PersistenceContext.desafioCrearInstanciaUsuario("A");
        em.persist(u);
        Ej112PersistenceContext.desafioDesasociarEntidad(em, u);
        assertFalse(em.contains(u));
    }

    @Test
    void testDesafioReasociarEntidad() {
        var u = Ej112PersistenceContext.desafioCrearInstanciaUsuario("A");
        em.persist(u);
        em.detach(u);
        var merged = Ej112PersistenceContext.desafioReasociarEntidad(em, u);
        assertTrue(em.contains(merged));
    }

    @Test
    void testDesafioRemoverEntidad() {
        var u = Ej112PersistenceContext.desafioCrearInstanciaUsuario("A");
        em.persist(u);
        Ej112PersistenceContext.desafioRemoverEntidad(em, u);
        assertFalse(em.contains(u));
    }

    @Test
    void testDesafioVaciarContexto() {
        var u = Ej112PersistenceContext.desafioCrearInstanciaUsuario("A");
        em.persist(u);
        Ej112PersistenceContext.desafioVaciarContexto(em);
        assertFalse(em.contains(u));
    }

    @Test
    void testDesafioCrearInstanciaUsuario() {
        var u = Ej112PersistenceContext.desafioCrearInstanciaUsuario("Ana");
        assertEquals("Ana", u.getNombre());
    }

    @Test
    void testDesafioTieneIdAsignado() {
        var u = new Usuario();
        assertFalse(Ej112PersistenceContext.desafioTieneIdAsignado(u));
        u.setId(10L);
        assertTrue(Ej112PersistenceContext.desafioTieneIdAsignado(u));
    }
}
