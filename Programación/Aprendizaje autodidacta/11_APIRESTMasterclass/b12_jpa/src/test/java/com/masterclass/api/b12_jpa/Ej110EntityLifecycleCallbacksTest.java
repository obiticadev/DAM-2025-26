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

@Test
    void testDesafioTienePrePersist() {
        assertTrue(Ej110EntityLifecycleCallbacks.desafioTienePrePersist(Auditado.class, "antesDeGuardar"));
    }

    @Test
    void testDesafioTienePreUpdate() {
        assertTrue(Ej110EntityLifecycleCallbacks.desafioTienePreUpdate(Auditado.class, "antesDeActualizar"));
    }

    @Test
    void testDesafioTieneCreadoEn() {
        var a = new Auditado();
        assertFalse(Ej110EntityLifecycleCallbacks.desafioTieneCreadoEn(a));
        a.setCreadoEn(java.time.LocalDateTime.now());
        assertTrue(Ej110EntityLifecycleCallbacks.desafioTieneCreadoEn(a));
    }

    @Test
    void testDesafioTieneActualizadoEn() {
        var a = new Auditado();
        assertFalse(Ej110EntityLifecycleCallbacks.desafioTieneActualizadoEn(a));
        a.setActualizadoEn(java.time.LocalDateTime.now());
        assertTrue(Ej110EntityLifecycleCallbacks.desafioTieneActualizadoEn(a));
    }

    @Test
    void testDesafioInicializarCreadoEn() {
        var a = new Auditado();
        Ej110EntityLifecycleCallbacks.desafioInicializarCreadoEn(a);
        assertNotNull(a.getCreadoEn());
    }

    @Test
    void testDesafioInicializarActualizadoEn() {
        var a = new Auditado();
        Ej110EntityLifecycleCallbacks.desafioInicializarActualizadoEn(a);
        assertNotNull(a.getActualizadoEn());
    }

    @Test
    void testDesafioValidarAuditado() {
        assertThrows(IllegalArgumentException.class, () -> Ej110EntityLifecycleCallbacks.desafioValidarAuditado(null));
    }

    @Test
    void testDesafioCrearInstanciaAuditado() {
        var a = Ej110EntityLifecycleCallbacks.desafioCrearInstanciaAuditado("test");
        assertEquals("test", a.getDato());
    }

    @Test
    void testDesafioFechasCoherentes() {
        var a = new Auditado();
        a.setCreadoEn(java.time.LocalDateTime.now());
        a.setActualizadoEn(java.time.LocalDateTime.now());
        assertTrue(Ej110EntityLifecycleCallbacks.desafioFechasCoherentes(a));
    }

    @Test
    void testDesafioTieneIdAsignado() {
        var a = new Auditado();
        assertFalse(Ej110EntityLifecycleCallbacks.desafioTieneIdAsignado(a));
        a.setId(10L);
        assertTrue(Ej110EntityLifecycleCallbacks.desafioTieneIdAsignado(a));
    }
}
