package com.masterclass.api.b14_jpaadv;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej128AuditingTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(DocAud128.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void prePersistYPreUpdate() {
        var d = new DocAud128("v1");
        Long id = Ej128Auditing.guardar(em, d);
        assertNotNull(d.getCreadoEn());
        assertNull(d.getActualizadoEn());

        Ej128Auditing.modificar(em, id, "v2");
        em.clear();
        var recargado = em.find(DocAud128.class, id);
        assertNotNull(recargado.getCreadoEn());
        assertNotNull(recargado.getActualizadoEn(), "@PreUpdate debe poblar actualizadoEn");
    }
}
