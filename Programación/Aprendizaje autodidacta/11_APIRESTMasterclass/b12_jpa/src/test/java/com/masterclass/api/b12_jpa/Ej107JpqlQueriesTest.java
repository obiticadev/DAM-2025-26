package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej107JpqlQueriesTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej107JpqlQueries q;
    private EmpleadoRepository repo;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Empleado107.class, Empleado.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Empleado107("Ana", "IT", 3000));
        em.persist(new Empleado107("Alba", "IT", 1000));
        em.persist(new Empleado107("Bea", "RRHH", 2000));
        em.getTransaction().commit();
        q = new Ej107JpqlQueries(em);
        repo = new EmpleadoRepositoryEm(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void proyeccionYAgregados() {
        assertEquals(java.util.List.of("Alba", "Ana"), q.nombresPorDepartamento("IT"));
        assertEquals(2000.0, q.salarioMedio("IT"), 0.0001);
        assertEquals(0.0, q.salarioMedio("NADA"), 0.0001);
        assertEquals(2, q.buscarPorNombreLike("a").size());
    }

@Test
    void testDesafioRepositoryActivo() {
        assertTrue(Ej107JpqlQueries.desafioRepositoryActivo(repo));
        assertFalse(Ej107JpqlQueries.desafioRepositoryActivo(null));
    }

    @Test
    void testDesafioBuscarPorDeptoJpql() {
        repo.deleteAll();
        var e = Ej107JpqlQueries.desafioCrearInstanciaEmpleado("Ana", "IT");
        repo.save(e);
        assertEquals(1, Ej107JpqlQueries.desafioBuscarPorDeptoJpql(repo, "IT").size());
    }

    @Test
    void testDesafioContarTodos() {
        repo.deleteAll();
        assertEquals(0, Ej107JpqlQueries.desafioContarTodos(repo));
    }

    @Test
    void testDesafioValidarDeptoBuscado() {
        assertThrows(IllegalArgumentException.class, () -> Ej107JpqlQueries.desafioValidarDeptoBuscado(" "));
    }

    @Test
    void testDesafioCrearInstanciaEmpleado() {
        var e = Ej107JpqlQueries.desafioCrearInstanciaEmpleado("A", "D");
        assertEquals("A", e.getNombre());
    }

    @Test
    void testDesafioCoincideDepartamento() {
        var e = Ej107JpqlQueries.desafioCrearInstanciaEmpleado("A", "D");
        assertTrue(Ej107JpqlQueries.desafioCoincideDepartamento(e, "D"));
    }

    @Test
    void testDesafioValidarIdAsignado() {
        var e = new Empleado();
        assertThrows(IllegalStateException.class, () -> Ej107JpqlQueries.desafioValidarIdAsignado(e));
    }

    @Test
    void testDesafioObtenerListaInmodificable() {
        var list = new java.util.ArrayList<Empleado>();
        list.add(new Empleado());
        var imm = Ej107JpqlQueries.desafioObtenerListaInmodificable(list);
        assertThrows(UnsupportedOperationException.class, () -> imm.add(new Empleado()));
    }

    @Test
    void testDesafioTieneEmpleadosIT() {
        var list = List.of(Ej107JpqlQueries.desafioCrearInstanciaEmpleado("A", "IT"));
        assertTrue(Ej107JpqlQueries.desafioTieneEmpleadosIT(list));
    }

    @Test
    void testDesafioContieneElementos() {
        assertTrue(Ej107JpqlQueries.desafioContieneElementos(List.of(new Empleado())));
    }
}
