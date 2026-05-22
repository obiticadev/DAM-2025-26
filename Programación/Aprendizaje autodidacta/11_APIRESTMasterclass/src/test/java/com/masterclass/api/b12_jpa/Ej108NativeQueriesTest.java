package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej108NativeQueriesTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej108NativeQueries q;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Ciudad108.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Ciudad108("Madrid", 3000000));
        em.persist(new Ciudad108("Soria", 40000));
        em.getTransaction().commit();
        q = new Ej108NativeQueries(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void nativo() {
        assertEquals(2, q.contarNativo());
        assertEquals(1, q.grandesNativo(1000000).size());
    }

@Test
    void testDesafioRepositoryActivo() {
        assertTrue(Ej108NativeQueries.desafioRepositoryActivo(repo));
        assertFalse(Ej108NativeQueries.desafioRepositoryActivo(null));
    }

    @Test
    void testDesafioBuscarPorDeptoNativa() {
        repo.deleteAll();
        var e = Ej108NativeQueries.desafioCrearInstanciaEmpleado("Ana", "IT");
        repo.save(e);
        assertEquals(1, Ej108NativeQueries.desafioBuscarPorDeptoNativa(repo, "IT").size());
    }

    @Test
    void testDesafioValidarFiltroDepto() {
        assertThrows(IllegalArgumentException.class, () -> Ej108NativeQueries.desafioValidarFiltroDepto(" "));
    }

    @Test
    void testDesafioCrearInstanciaEmpleado() {
        var e = Ej108NativeQueries.desafioCrearInstanciaEmpleado("A", "D");
        assertEquals("A", e.getNombre());
    }

    @Test
    void testDesafioObtenerNombreTablaNativo() {
        assertEquals("EMPLEADO", Ej108NativeQueries.desafioObtenerNombreTablaNativo());
    }

    @Test
    void testDesafioDatosCoherentes() {
        var e = Ej108NativeQueries.desafioCrearInstanciaEmpleado("A", "D");
        assertTrue(Ej108NativeQueries.desafioDatosCoherentes(e));
    }

    @Test
    void testDesafioValidarEmpleadoDePrueba() {
        assertThrows(IllegalArgumentException.class, () -> Ej108NativeQueries.desafioValidarEmpleadoDePrueba(new Empleado()));
    }

    @Test
    void testDesafioContarEmpleados() {
        repo.deleteAll();
        assertEquals(0, Ej108NativeQueries.desafioContarEmpleados(repo));
    }

    @Test
    void testDesafioTieneRegistros() {
        assertTrue(Ej108NativeQueries.desafioTieneRegistros(List.of(new Empleado())));
    }

    @Test
    void testDesafioObtenerNombresLista() {
        var list = List.of(Ej108NativeQueries.desafioCrearInstanciaEmpleado("Ana", "IT"));
        assertEquals(List.of("Ana"), Ej108NativeQueries.desafioObtenerNombresLista(list));
    }
}
