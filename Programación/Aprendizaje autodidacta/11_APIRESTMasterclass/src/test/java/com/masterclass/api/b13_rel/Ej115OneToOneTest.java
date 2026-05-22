package com.masterclass.api.b13_rel;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej115OneToOneTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Usuario115.class, Perfil115.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void persisteRelacion11() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        var r = Ej115OneToOne.guardarYRecargar(em, u);
        assertNotNull(r);
        assertNotNull(r.getPerfil());
        assertEquals("hola", r.getPerfil().getBio());
    }

    @Test
    void testRetoExtra01() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        assertTrue(Ej115OneToOne.validarPerfilAsociado(u));
        assertFalse(Ej115OneToOne.validarPerfilAsociado(null));
    }

    @Test
    void testRetoExtra02() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        assertEquals("hola", Ej115OneToOne.obtenerBioDelUsuario(u, "def"));
        assertEquals("def", Ej115OneToOne.obtenerBioDelUsuario(null, "def"));
    }

    @Test
    void testRetoExtra03() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        assertTrue(Ej115OneToOne.esBioSuficiente(u, 3));
        assertFalse(Ej115OneToOne.esBioSuficiente(u, 10));
    }

    @Test
    void testRetoExtra04() {
        var u = new Usuario115("Ana", new Perfil115("Ana"));
        assertTrue(Ej115OneToOne.tieneMismoNombreYBio(u));
    }

    @Test
    void testRetoExtra05() {
        var u = new Usuario115("Ana", new Perfil115("hola mundo"));
        assertTrue(Ej115OneToOne.bioContienePalabra(u, "mundo"));
        assertFalse(Ej115OneToOne.bioContienePalabra(u, "casa"));
    }

    @Test
    void testRetoExtra06() {
        var p = Ej115OneToOne.crearPerfilSaneado("  hola  ");
        assertEquals("hola", p.getBio());
    }

    @Test
    void testRetoExtra07() {
        var u = new Usuario115("Ana", null);
        assertEquals("SIN_PERFIL", Ej115OneToOne.formatearPerfil(u));
    }

    @Test
    void testRetoExtra08() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        assertFalse(Ej115OneToOne.perfilTieneIdPar(u));
    }

    @Test
    void testRetoExtra09() {
        var u = new Usuario115("Ana", null);
        assertFalse(Ej115OneToOne.usuarioTieneId(u));
    }

    @Test
    void testRetoExtra10() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        var c = Ej115OneToOne.clonarUsuarioSencillo(u);
        assertNotNull(c);
        assertEquals(u.getPerfil(), c.getPerfil());
    }

}
