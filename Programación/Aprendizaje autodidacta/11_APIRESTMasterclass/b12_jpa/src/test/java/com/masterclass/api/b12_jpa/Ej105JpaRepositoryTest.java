package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej105JpaRepositoryTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej105JpaRepository manualRepo;
    private ProductoRepository repo;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Tarea105.class, Producto.class);
        em = emf.createEntityManager();
        manualRepo = new Ej105JpaRepository(em);
        repo = new ProductoRepositoryEm(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void crudCompleto() {
        var t = manualRepo.save(new Tarea105("comprar"));
        assertNotNull(t.getId());
        assertEquals("comprar", manualRepo.findById(t.getId()).getTitulo());
        manualRepo.save(new Tarea105("otra"));
        assertEquals(2, manualRepo.findAll().size());
        assertTrue(manualRepo.deleteById(t.getId()));
        assertEquals(1, manualRepo.findAll().size());
        assertFalse(manualRepo.deleteById(999L));
    }

@Test
    void testDesafioRepositoryActivo() {
        assertTrue(Ej105JpaRepository.desafioRepositoryActivo(repo));
        assertFalse(Ej105JpaRepository.desafioRepositoryActivo(null));
    }

    @Test
    void testDesafioGuardarProducto() {
        var p = Ej105JpaRepository.desafioCrearInstanciaProducto("T", 10.0);
        var saved = Ej105JpaRepository.desafioGuardarProducto(repo, p);
        assertNotNull(saved.getId());
    }

    @Test
    void testDesafioBuscarPorId() {
        var p = Ej105JpaRepository.desafioCrearInstanciaProducto("T", 10.0);
        var saved = repo.save(p);
        assertTrue(Ej105JpaRepository.desafioBuscarPorId(repo, saved.getId()).isPresent());
    }

    @Test
    void testDesafioBuscarTodos() {
        repo.deleteAll();
        var p = Ej105JpaRepository.desafioCrearInstanciaProducto("T", 10.0);
        repo.save(p);
        assertEquals(1, Ej105JpaRepository.desafioBuscarTodos(repo).size());
    }

    @Test
    void testDesafioExistePorId() {
        var p = Ej105JpaRepository.desafioCrearInstanciaProducto("T", 10.0);
        var saved = repo.save(p);
        assertTrue(Ej105JpaRepository.desafioExistePorId(repo, saved.getId()));
    }

    @Test
    void testDesafioContarProductos() {
        repo.deleteAll();
        assertEquals(0, Ej105JpaRepository.desafioContarProductos(repo));
    }

    @Test
    void testDesafioEliminarPorId() {
        var p = Ej105JpaRepository.desafioCrearInstanciaProducto("T", 10.0);
        var saved = repo.save(p);
        Ej105JpaRepository.desafioEliminarPorId(repo, saved.getId());
        assertFalse(repo.existsById(saved.getId()));
    }

    @Test
    void testDesafioValidarParaGuardar() {
        assertThrows(IllegalArgumentException.class, () -> Ej105JpaRepository.desafioValidarParaGuardar(null));
    }

    @Test
    void testDesafioCrearInstanciaProducto() {
        var p = Ej105JpaRepository.desafioCrearInstanciaProducto("A", 20.0);
        assertEquals("A", p.getNombre());
    }

    @Test
    void testDesafioTieneElementos() {
        assertTrue(Ej105JpaRepository.desafioTieneElementos(List.of(new Producto())));
        assertFalse(Ej105JpaRepository.desafioTieneElementos(List.of()));
    }
}
