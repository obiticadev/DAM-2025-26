package com.masterclass.api.b13_rel;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej122JoinFetchAndEntityGraphTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Long id;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Proyecto122.class, Tarea122.class);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        var p = new Proyecto122("Apolo");
        p.add(new Tarea122("t1"));
        p.add(new Tarea122("t2"));
        em.persist(p);
        em.getTransaction().commit();
        id = p.getId();
        em.clear();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void entityGraphInicializa() {
        var p = Ej122JoinFetchAndEntityGraph.cargarConGrafo(em, id);
        em.clear();
        assertEquals(2, p.getTareas().size());
    }

    @Test
    void joinFetchInicializa() {
        var p = Ej122JoinFetchAndEntityGraph.cargarConJoinFetch(em, id);
        em.clear();
        assertEquals(2, p.getTareas().size());
    }

    @Test
    void testRetoExtra01() {
        var p = new Proyecto122("Web");
        assertEquals(0, Ej122JoinFetchAndEntityGraph.contarTareas(p));
        p.add(new Tarea122("UI"));
        assertEquals(1, Ej122JoinFetchAndEntityGraph.contarTareas(p));
    }

    @Test
    void testRetoExtra02() {
        var p = new Proyecto122("Web");
        assertFalse(Ej122JoinFetchAndEntityGraph.tieneTareas(p));
        p.add(new Tarea122("UI"));
        assertTrue(Ej122JoinFetchAndEntityGraph.tieneTareas(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new Proyecto122("Web");
        var t = new Tarea122("UI");
        p.add(t);
        assertTrue(Ej122JoinFetchAndEntityGraph.contieneTarea(p, t));
    }

    @Test
    void testRetoExtra04() {
        var p = new Proyecto122("Web");
        p.add(new Tarea122("UI"));
        assertTrue(Ej122JoinFetchAndEntityGraph.tieneTitulo(p, "UI"));
        assertFalse(Ej122JoinFetchAndEntityGraph.tieneTitulo(p, "DB"));
    }

    @Test
    void testRetoExtra05() {
        var p = new Proyecto122("Web");
        p.add(new Tarea122("Backend"));
        assertEquals(1, Ej122JoinFetchAndEntityGraph.contarTareasTituloLargo(p, 5));
    }

    @Test
    void testRetoExtra06() {
        var t = Ej122JoinFetchAndEntityGraph.crearTarea("A");
        assertNotNull(t);
    }

    @Test
    void testRetoExtra07() {
        var p = new Proyecto122("Web");
        p.add(new Tarea122("A"));
        assertTrue(Ej122JoinFetchAndEntityGraph.removerPrimeraTarea(p));
        assertEquals(0, p.getTareas().size());
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej122JoinFetchAndEntityGraph.esValido(new Proyecto122("A")));
        assertFalse(Ej122JoinFetchAndEntityGraph.esValido(null));
    }

    @Test
    void testRetoExtra09() {
        var p = new Proyecto122("Web");
        var t1 = new Tarea122("A");
        var t2 = new Tarea122("B");
        Ej122JoinFetchAndEntityGraph.vincularTareas(p, java.util.List.of(t1, t2));
        assertEquals(2, p.getTareas().size());
    }

    @Test
    void testRetoExtra10() {
        var p = new Proyecto122("Web");
        assertEquals("Proyecto[Id=null, Tareas=0]", Ej122JoinFetchAndEntityGraph.formatearProyecto(p));
    }

}
