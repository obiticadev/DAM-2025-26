package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej103EntityMappingTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Producto103.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void persisteYRecupera() {
        Long id = Ej103EntityMapping.guardar(em, new Producto103(1L, "Teclado", 50.0));
        assertEquals(1L, id);
        em.clear();
        Producto103 p = Ej103EntityMapping.buscar(em, 1L);
        assertNotNull(p);
        assertEquals("Teclado", p.getNombre());
        assertNull(Ej103EntityMapping.buscar(em, 99L));
    }

@Test
    void testDesafioTieneAnotacionEntity() {
        assertTrue(Ej103EntityMapping.desafioTieneAnotacionEntity(Empleado.class));
    }

    @Test
    void testDesafioTieneAnotacionTable() {
        assertTrue(Ej103EntityMapping.desafioTieneAnotacionTable(Empleado.class));
    }

    @Test
    void testDesafioCampoTieneId() {
        assertTrue(Ej103EntityMapping.desafioCampoTieneId(Empleado.class, "id"));
        assertFalse(Ej103EntityMapping.desafioCampoTieneId(Empleado.class, "nombre"));
    }

    @Test
    void testDesafioCampoTieneColumn() {
        assertTrue(Ej103EntityMapping.desafioCampoTieneColumn(Empleado.class, "nombre"));
    }

    @Test
    void testDesafioObtenerNombreTabla() {
        assertEquals("EMPLEADO", Ej103EntityMapping.desafioObtenerNombreTabla(Empleado.class));
    }

    @Test
    void testDesafioObtenerNombreColumna() {
        assertEquals("EMP_ID", Ej103EntityMapping.desafioObtenerNombreColumna(Empleado.class, "id"));
        assertEquals("EMP_NOM", Ej103EntityMapping.desafioObtenerNombreColumna(Empleado.class, "nombre"));
    }

    @Test
    void testDesafioValidarNombreEmpleado() {
        assertThrows(IllegalArgumentException.class, () -> Ej103EntityMapping.desafioValidarNombreEmpleado(null));
        assertDoesNotThrow(() -> Ej103EntityMapping.desafioValidarNombreEmpleado("Ana"));
    }

    @Test
    void testDesafioColumnaNoNula() {
        assertTrue(Ej103EntityMapping.desafioColumnaNoNula(Empleado.class, "nombre"));
    }

    @Test
    void testDesafioCrearEmpleadoBasico() {
        var e = Ej103EntityMapping.desafioCrearEmpleadoBasico(1L, "Ana");
        assertEquals(1L, e.getId());
        assertEquals("Ana", e.getNombre());
    }

    @Test
    void testDesafioTieneIdConfigurado() {
        var e = new Empleado();
        assertFalse(Ej103EntityMapping.desafioTieneIdConfigurado(e));
        e.setId(10L);
        assertTrue(Ej103EntityMapping.desafioTieneIdConfigurado(e));
    }
}
