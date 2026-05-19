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
}
