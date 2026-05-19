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
}
