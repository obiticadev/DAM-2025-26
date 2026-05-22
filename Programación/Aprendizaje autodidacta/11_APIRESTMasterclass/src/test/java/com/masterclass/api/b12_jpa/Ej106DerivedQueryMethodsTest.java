package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej106DerivedQueryMethodsTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej106DerivedQueryMethods repo;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Articulo106.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Articulo106("libros", 10));
        em.persist(new Articulo106("libros", 30));
        em.persist(new Articulo106("ropa", 50));
        em.getTransaction().commit();
        repo = new Ej106DerivedQueryMethods(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void consultas() {
        assertEquals(2, repo.findByCategoria("libros").size());
        assertEquals(2, repo.findByPrecioMayorQue(20).size());
        assertEquals(2, repo.countByCategoria("libros"));
        assertEquals(0, repo.countByCategoria("nada"));
    }

@Test
    void testDesafioRepositoryActivo() {
        assertTrue(Ej106DerivedQueryMethods.desafioRepositoryActivo(repo));
        assertFalse(Ej106DerivedQueryMethods.desafioRepositoryActivo(null));
    }

    @Test
    void testDesafioBuscarPorNombre() {
        repo.deleteAll();
        var e = Ej106DerivedQueryMethods.desafioCrearInstanciaEmpleado("Ana", "IT");
        repo.save(e);
        assertEquals(1, Ej106DerivedQueryMethods.desafioBuscarPorNombre(repo, "Ana").size());
    }

    @Test
    void testDesafioBuscarPorDepartamento() {
        repo.deleteAll();
        var e = Ej106DerivedQueryMethods.desafioCrearInstanciaEmpleado("Ana", "IT");
        repo.save(e);
        assertEquals(1, Ej106DerivedQueryMethods.desafioBuscarPorDepartamento(repo, "IT").size());
    }

    @Test
    void testDesafioBuscarPorDeptoYNombre() {
        repo.deleteAll();
        var e = Ej106DerivedQueryMethods.desafioCrearInstanciaEmpleado("Ana", "IT");
        repo.save(e);
        assertEquals(1, Ej106DerivedQueryMethods.desafioBuscarPorDeptoYNombre(repo, "IT", "Ana").size());
    }

    @Test
    void testDesafioExistePorNombre() {
        repo.deleteAll();
        var e = Ej106DerivedQueryMethods.desafioCrearInstanciaEmpleado("Ana", "IT");
        repo.save(e);
        assertTrue(Ej106DerivedQueryMethods.desafioExistePorNombre(repo, "Ana"));
    }

    @Test
    void testDesafioContarEnDepartamento() {
        repo.deleteAll();
        var e = Ej106DerivedQueryMethods.desafioCrearInstanciaEmpleado("Ana", "IT");
        repo.save(e);
        assertEquals(1, Ej106DerivedQueryMethods.desafioContarEnDepartamento(repo, "IT"));
    }

    @Test
    void testDesafioValidarDepartamentoBuscado() {
        assertThrows(IllegalArgumentException.class, () -> Ej106DerivedQueryMethods.desafioValidarDepartamentoBuscado(null));
        assertDoesNotThrow(() -> Ej106DerivedQueryMethods.desafioValidarDepartamentoBuscado("HR"));
    }

    @Test
    void testDesafioCrearInstanciaEmpleado() {
        var e = Ej106DerivedQueryMethods.desafioCrearInstanciaEmpleado("Leo", "HR");
        assertEquals("Leo", e.getNombre());
    }

    @Test
    void testDesafioContieneDepartamento() {
        var list = List.of(Ej106DerivedQueryMethods.desafioCrearInstanciaEmpleado("Leo", "HR"));
        assertTrue(Ej106DerivedQueryMethods.desafioContieneDepartamento(list, "HR"));
    }

    @Test
    void testDesafioTieneDatos() {
        assertTrue(Ej106DerivedQueryMethods.desafioTieneDatos(List.of(new Empleado())));
        assertFalse(Ej106DerivedQueryMethods.desafioTieneDatos(List.of()));
    }
}
