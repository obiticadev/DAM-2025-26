package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej111EnumAndEmbeddableTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Socio111.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void enumYEmbebidoPersisten() {
        var s = new Socio111("Ana", Ej111EnumAndEmbeddable.Estado.ACTIVO,
                new Direccion111("C/ Mayor", "Madrid"));
        var recargado = Ej111EnumAndEmbeddable.guardarYRecargar(em, s);
        assertEquals(Ej111EnumAndEmbeddable.Estado.ACTIVO, recargado.getEstado());
        assertEquals("Madrid", recargado.getDireccion().getCiudad());
    }
}
