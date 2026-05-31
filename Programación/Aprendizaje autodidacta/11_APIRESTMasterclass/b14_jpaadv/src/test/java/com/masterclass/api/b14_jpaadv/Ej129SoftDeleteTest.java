package com.masterclass.api.b14_jpaadv;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej129SoftDeleteTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Ej129SoftDelete repo;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(ClienteSD129.class);
        em = emf.createEntityManager();
        repo = new Ej129SoftDelete(em);
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void borradoLogicoExcluyeDeListado() {
        Long id = repo.crear(new ClienteSD129("Ana"));
        repo.crear(new ClienteSD129("Leo"));
        assertEquals(2, repo.listarActivos().size());
        assertTrue(repo.borrarLogico(id));
        assertEquals(1, repo.listarActivos().size());

        em.clear();
        assertTrue(em.find(ClienteSD129.class, id).isBorrado(),
                "la fila sigue existiendo, solo marcada");
    }

    @Test
    void testRetoExtra01() {
        var c = new ClienteSD129("Ana");
        assertEquals("Ana", Ej129SoftDelete.obtenerNombre(c));
    }

    @Test
    void testRetoExtra02() {
        var c = new ClienteSD129("Ana");
        assertFalse(Ej129SoftDelete.estaBorrado(c));
    }

    @Test
    void testRetoExtra03() {
        var c = Ej129SoftDelete.crearCliente("Eva");
        assertNotNull(c);
    }

    @Test
    void testRetoExtra04() {
        var c = new ClienteSD129("Ana");
        Ej129SoftDelete.borrarClienteManual(c);
        assertTrue(c.isBorrado());
    }

    @Test
    void testRetoExtra05() {
        var c = new ClienteSD129("Ana");
        c.setBorrado(true);
        Ej129SoftDelete.restaurarCliente(c);
        assertFalse(c.isBorrado());
    }

    @Test
    void testRetoExtra06() {
        var c = new ClienteSD129("Ana");
        assertNull(Ej129SoftDelete.obtenerId(c));
    }

    @Test
    void testRetoExtra07() {
        var c = new ClienteSD129("Ana");
        assertTrue(Ej129SoftDelete.esNuevo(c));
    }

    @Test
    void testRetoExtra08() {
        var c = new ClienteSD129("Ana Lopez");
        assertTrue(Ej129SoftDelete.nombreContiene(c, "Lopez"));
        assertFalse(Ej129SoftDelete.nombreContiene(c, "Marta"));
    }

    @Test
    void testRetoExtra09() {
        var c = new ClienteSD129("Ana");
        assertTrue(Ej129SoftDelete.estaActivo(c));
    }

    @Test
    void testRetoExtra10() {
        var c = new ClienteSD129("Ana");
        assertEquals("Cliente[Id=null, Activo=true]", Ej129SoftDelete.formatearCliente(c));
    }

}
