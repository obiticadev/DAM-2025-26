package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej109ModifyingQueriesTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej109ModifyingQueries q;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Prod109.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Prod109("a", 100, 5));
        em.persist(new Prod109("a", 200, 0));
        em.persist(new Prod109("b", 50, 3));
        em.getTransaction().commit();
        q = new Ej109ModifyingQueries(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void updateMasivo() {
        assertEquals(2, q.subirPrecios("a", 10));
    }

    @Test
    void deleteMasivo() {
        assertEquals(1, q.borrarSinStock());
    }

@Test
    void testDesafioRepositoryActivo() {
        assertTrue(Ej109ModifyingQueries.desafioRepositoryActivo(repo));
        assertFalse(Ej109ModifyingQueries.desafioRepositoryActivo(null));
    }

    @Test
    void testDesafioActualizarDepartamento() {
        repo.deleteAll();
        var e = Ej109ModifyingQueries.desafioCrearInstanciaEmpleado("Ana", "IT");
        repo.save(e);
        int mod = Ej109ModifyingQueries.desafioActualizarDepartamento(repo, "HR", "IT");
        assertEquals(1, mod);
    }

    @Test
    void testDesafioMetodoTieneModifying() {
        assertTrue(Ej109ModifyingQueries.desafioMetodoTieneModifying(EmpleadoRepository.class, "actualizarDepartamento", String.class, String.class));
    }

    @Test
    void testDesafioMetodoTieneQuery() {
        assertTrue(Ej109ModifyingQueries.desafioMetodoTieneQuery(EmpleadoRepository.class, "actualizarDepartamento", String.class, String.class));
    }

    @Test
    void testDesafioVerificarModificacionExitosa() {
        assertTrue(Ej109ModifyingQueries.desafioVerificarModificacionExitosa(1));
        assertFalse(Ej109ModifyingQueries.desafioVerificarModificacionExitosa(0));
    }

    @Test
    void testDesafioValidarNuevoDepartamento() {
        assertThrows(IllegalArgumentException.class, () -> Ej109ModifyingQueries.desafioValidarNuevoDepartamento(null));
    }

    @Test
    void testDesafioCrearInstanciaEmpleado() {
        var e = Ej109ModifyingQueries.desafioCrearInstanciaEmpleado("A", "D");
        assertEquals("A", e.getNombre());
    }

    @Test
    void testDesafioContarEmpleados() {
        repo.deleteAll();
        assertEquals(0, Ej109ModifyingQueries.desafioContarEmpleados(repo));
    }

    @Test
    void testDesafioVerificarTodosEnDepartamento() {
        var list = List.of(Ej109ModifyingQueries.desafioCrearInstanciaEmpleado("A", "D"));
        assertTrue(Ej109ModifyingQueries.desafioVerificarTodosEnDepartamento(list, "D"));
    }

    @Test
    void testDesafioTieneDatos() {
        assertTrue(Ej109ModifyingQueries.desafioTieneDatos(List.of()));
    }
}
