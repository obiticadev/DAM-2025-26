package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej114DtoConstructorProjectionTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej114DtoConstructorProjection q;
    private EmpleadoRepository repo;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Pedido114.class, Empleado.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Pedido114(100.0));
        em.persist(new Pedido114(250.0));
        em.getTransaction().commit();
        q = new Ej114DtoConstructorProjection(em);
        repo = new EmpleadoRepositoryEm(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void proyectaADto() {
        var r = q.resumen();
        assertEquals(2, r.size());
        assertEquals(100.0, r.get(0).total(), 0.0001);
        assertNotNull(r.get(0).id());
    }

@Test
    void testDesafioRepositoryActivo() {
        assertTrue(Ej114DtoConstructorProjection.desafioRepositoryActivo(repo));
        assertFalse(Ej114DtoConstructorProjection.desafioRepositoryActivo(null));
    }

    @Test
    void testDesafioBuscarProyecciones() {
        repo.deleteAll();
        var e = Ej114DtoConstructorProjection.desafioCrearEmpleadoEntidad("Ana", "IT");
        repo.save(e);
        assertEquals(1, Ej114DtoConstructorProjection.desafioBuscarProyecciones(repo).size());
    }

    @Test
    void testDesafioDtoValido() {
        assertTrue(Ej114DtoConstructorProjection.desafioDtoValido(new EmpleadoDto("A", "D")));
    }

    @Test
    void testDesafioCrearDto() {
        var d = Ej114DtoConstructorProjection.desafioCrearDto("A", "D");
        assertEquals("A", d.nombre());
    }

    @Test
    void testDesafioContieneDeptoIT() {
        var list = List.of(new EmpleadoDto("A", "IT"));
        assertTrue(Ej114DtoConstructorProjection.desafioContieneDeptoIT(list));
    }

    @Test
    void testDesafioObtenerNombresDtos() {
        var list = List.of(new EmpleadoDto("A", "D"));
        assertEquals(List.of("A"), Ej114DtoConstructorProjection.desafioObtenerNombresDtos(list));
    }

    @Test
    void testDesafioValidarDtoCompleto() {
        assertThrows(IllegalArgumentException.class, () -> Ej114DtoConstructorProjection.desafioValidarDtoCompleto(new EmpleadoDto("A", null)));
    }

    @Test
    void testDesafioCrearEmpleadoEntidad() {
        var e = Ej114DtoConstructorProjection.desafioCrearEmpleadoEntidad("A", "D");
        assertEquals("A", e.getNombre());
    }

    @Test
    void testDesafioContarDtos() {
        repo.deleteAll();
        assertEquals(0, Ej114DtoConstructorProjection.desafioContarDtos(repo));
    }

    @Test
    void testDesafioTieneDatos() {
        assertTrue(Ej114DtoConstructorProjection.desafioTieneDatos(List.of(new EmpleadoDto("A", "D"))));
    }
}
