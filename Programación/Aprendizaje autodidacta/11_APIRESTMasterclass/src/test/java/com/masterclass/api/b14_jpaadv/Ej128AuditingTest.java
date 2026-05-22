package com.masterclass.api.b14_jpaadv;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej128AuditingTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(DocAud128.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void prePersistYPreUpdate() {
        var d = new DocAud128("v1");
        Long id = Ej128Auditing.guardar(em, d);
        assertNotNull(d.getCreadoEn());
        assertNull(d.getActualizadoEn());

        Ej128Auditing.modificar(em, id, "v2");
        em.clear();
        var recargado = em.find(DocAud128.class, id);
        assertNotNull(recargado.getCreadoEn());
        assertNotNull(recargado.getActualizadoEn(), "@PreUpdate debe poblar actualizadoEn");
    }

    @Test
    void testRetoExtra01() {
        var d = new DocAud128("hola");
        assertEquals("hola", Ej128Auditing.obtenerTexto(d));
    }

    @Test
    void testRetoExtra02() {
        var d = new DocAud128("hola");
        assertFalse(Ej128Auditing.tieneCreadoEn(d));
    }

    @Test
    void testRetoExtra03() {
        var d = new DocAud128("hola");
        assertFalse(Ej128Auditing.tieneActualizadoEn(d));
    }

    @Test
    void testRetoExtra04() {
        var d = Ej128Auditing.crearDocumento("nuevo");
        assertNotNull(d);
    }

    @Test
    void testRetoExtra05() {
        var d = new DocAud128("hola");
        assertFalse(Ej128Auditing.haSidoEditado(d));
    }

    @Test
    void testRetoExtra06() {
        var d = new DocAud128("hola");
        Ej128Auditing.actualizarTexto(d, "mundo");
        assertEquals("mundo", d.getTexto());
    }

    @Test
    void testRetoExtra07() {
        var d = new DocAud128("hola");
        assertNull(Ej128Auditing.obtenerId(d));
    }

    @Test
    void testRetoExtra08() {
        var d = new DocAud128("hola mundo");
        assertTrue(Ej128Auditing.contienePalabra(d, "mundo"));
        assertFalse(Ej128Auditing.contienePalabra(d, "casa"));
    }

    @Test
    void testRetoExtra09() {
        var d = new DocAud128("hola");
        assertTrue(Ej128Auditing.esNuevo(d));
    }

    @Test
    void testRetoExtra10() {
        var d = new DocAud128("hola");
        assertEquals("Doc[Id=null, Creado=null]", Ej128Auditing.formatearAuditoria(d));
    }

}
