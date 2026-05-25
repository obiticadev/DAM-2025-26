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

@Test
    void testDesafioTieneGeneratedValue() {
        assertTrue(Ej104IdGenerationStrategies.desafioTieneGeneratedValue(RegistroConSecuencia.class, "id"));
        assertTrue(Ej104IdGenerationStrategies.desafioTieneGeneratedValue(RegistroConIdentidad.class, "id"));
    }

    @Test
    void testDesafioObtenerEstrategiaGeneracion() {
        assertEquals(jakarta.persistence.GenerationType.SEQUENCE, Ej104IdGenerationStrategies.desafioObtenerEstrategiaGeneracion(RegistroConSecuencia.class, "id"));
        assertEquals(jakarta.persistence.GenerationType.IDENTITY, Ej104IdGenerationStrategies.desafioObtenerEstrategiaGeneracion(RegistroConIdentidad.class, "id"));
    }

    @Test
    void testDesafioEsEstrategiaSequence() {
        assertTrue(Ej104IdGenerationStrategies.desafioEsEstrategiaSequence(RegistroConSecuencia.class, "id"));
        assertFalse(Ej104IdGenerationStrategies.desafioEsEstrategiaSequence(RegistroConIdentidad.class, "id"));
    }

    @Test
    void testDesafioEsEstrategiaIdentity() {
        assertFalse(Ej104IdGenerationStrategies.desafioEsEstrategiaIdentity(RegistroConSecuencia.class, "id"));
        assertTrue(Ej104IdGenerationStrategies.desafioEsEstrategiaIdentity(RegistroConIdentidad.class, "id"));
    }

    @Test
    void testDesafioTieneSequenceGenerator() {
        assertTrue(Ej104IdGenerationStrategies.desafioTieneSequenceGenerator(RegistroConSecuencia.class, "id"));
        assertFalse(Ej104IdGenerationStrategies.desafioTieneSequenceGenerator(RegistroConIdentidad.class, "id"));
    }

    @Test
    void testDesafioObtenerNombreSequence() {
        assertEquals("REG_SEQ", Ej104IdGenerationStrategies.desafioObtenerNombreSequence(RegistroConSecuencia.class, "id"));
    }

    @Test
    void testDesafioObtenerAllocationSize() {
        assertEquals(1, Ej104IdGenerationStrategies.desafioObtenerAllocationSize(RegistroConSecuencia.class, "id"));
    }

    @Test
    void testDesafioCrearRegistroConSecuencia() {
        var r = Ej104IdGenerationStrategies.desafioCrearRegistroConSecuencia(10L, "D");
        assertEquals(10L, r.getId());
    }

    @Test
    void testDesafioCrearRegistroConIdentidad() {
        var r = Ej104IdGenerationStrategies.desafioCrearRegistroConIdentidad(10L, "D");
        assertEquals(10L, r.getId());
    }

    @Test
    void testDesafioIdInicialEsNulo() {
        assertTrue(Ej104IdGenerationStrategies.desafioIdInicialEsNulo(new RegistroConSecuencia()));
    }
}
