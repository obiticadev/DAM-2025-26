package com.masterclass.api.b13_rel;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej117ManyToManyJoinTableTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Alumno117.class, Curso117.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void matriculasManyToMany() {
        var a = new Alumno117("Ana");
        a.matricular(new Curso117("Mates"));
        a.matricular(new Curso117("Lengua"));
        assertEquals(2, Ej117ManyToManyJoinTable.guardarYContarCursos(em, a));
    }

    @Test
    void testRetoExtra01() {
        var a = new Alumno117("Ana");
        assertEquals(0, Ej117ManyToManyJoinTable.contarCursos(a));
        a.matricular(new Curso117("Mates"));
        assertEquals(1, Ej117ManyToManyJoinTable.contarCursos(a));
    }

    @Test
    void testRetoExtra02() {
        var a = new Alumno117("Ana");
        assertFalse(Ej117ManyToManyJoinTable.tieneCursos(a));
        a.matricular(new Curso117("Mates"));
        assertTrue(Ej117ManyToManyJoinTable.tieneCursos(a));
    }

    @Test
    void testRetoExtra03() {
        var a = new Alumno117("Ana");
        var c = new Curso117("Mates");
        a.matricular(c);
        assertTrue(Ej117ManyToManyJoinTable.estaMatriculado(a, c));
    }

    @Test
    void testRetoExtra04() {
        var a = new Alumno117("Ana");
        a.matricular(new Curso117("Historia"));
        assertTrue(Ej117ManyToManyJoinTable.tieneCursoPorNombre(a, "Historia"));
        assertFalse(Ej117ManyToManyJoinTable.tieneCursoPorNombre(a, "Lengua"));
    }

    @Test
    void testRetoExtra05() {
        var a = new Alumno117("Ana");
        a.matricular(new Curso117("Geografia"));
        assertEquals(1, Ej117ManyToManyJoinTable.contarCursosNombreLargo(a, 5));
    }

    @Test
    void testRetoExtra06() {
        var c = Ej117ManyToManyJoinTable.crearCurso("Mates");
        assertNotNull(c);
    }

    @Test
    void testRetoExtra07() {
        var a = new Alumno117("Ana");
        var c = new Curso117("Mates");
        a.matricular(c);
        assertTrue(Ej117ManyToManyJoinTable.desmatricular(a, c));
        assertEquals(0, a.getCursos().size());
    }

    @Test
    void testRetoExtra08() {
        var a1 = new Alumno117("Ana");
        var a2 = new Alumno117("Eva");
        var c = new Curso117("Mates");
        a1.matricular(c);
        a2.matricular(c);
        assertTrue(Ej117ManyToManyJoinTable.compartenCurso(a1, a2));
    }

    @Test
    void testRetoExtra09() {
        var a = new Alumno117("Ana");
        var c1 = new Curso117("Mates");
        var c2 = new Curso117("Historia");
        Ej117ManyToManyJoinTable.matricularEnLote(a, java.util.List.of(c1, c2));
        assertEquals(2, a.getCursos().size());
    }

    @Test
    void testRetoExtra10() {
        var a = new Alumno117("Ana");
        assertEquals("Alumno[Id=null, Cursos=0]", Ej117ManyToManyJoinTable.formatearAlumno(a));
    }

}
