package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej103EntityMappingTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Producto103.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void persisteYRecupera() {
        Long id = Ej103EntityMapping.guardar(em, new Producto103(1L, "Teclado", 50.0));
        assertEquals(1L, id);
        em.clear();
        Producto103 p = Ej103EntityMapping.buscar(em, 1L);
        assertNotNull(p);
        assertEquals("Teclado", p.getNombre());
        assertNull(Ej103EntityMapping.buscar(em, 99L));
    }
}
