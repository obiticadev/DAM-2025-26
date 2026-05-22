package com.masterclass.api.b12_jpa;

import com.masterclass.api.support.JpaTestSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej111EnumAndEmbeddableTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = JpaTestSupport.emf(Socio111.class);
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void enumYEmbebidoPersisten() {
        var s = new Socio111("Ana", Ej111EnumAndEmbeddable.Estado.ACTIVO,
                new Direccion111("C/ Mayor", "Madrid"));
        var recargado = Ej111EnumAndEmbeddable.guardarYRecargar(em, s);
        assertEquals(Ej111EnumAndEmbeddable.Estado.ACTIVO, recargado.getEstado());
        assertEquals("Madrid", recargado.getDireccion().getCiudad());
    }

@Test
    void testDesafioTieneEnumerated() {
        assertTrue(Ej111EnumAndEmbeddable.desafioTieneEnumerated(Pedido.class, "estado"));
    }

    @Test
    void testDesafioObtenerEnumType() {
        assertEquals(jakarta.persistence.EnumType.STRING, Ej111EnumAndEmbeddable.desafioObtenerEnumType(Pedido.class, "estado"));
    }

    @Test
    void testDesafioEsEmbeddable() {
        assertTrue(Ej111EnumAndEmbeddable.desafioEsEmbeddable(Direccion.class));
    }

    @Test
    void testDesafioTieneEmbedded() {
        assertTrue(Ej111EnumAndEmbeddable.desafioTieneEmbedded(Pedido.class, "direccionEnvio"));
    }

    @Test
    void testDesafioCrearDireccion() {
        var d = Ej111EnumAndEmbeddable.desafioCrearDireccion("C", "M");
        assertEquals("C", d.getCalle());
    }

    @Test
    void testDesafioCrearPedido() {
        var p = Ej111EnumAndEmbeddable.desafioCrearPedido(EstadoPedido.NUEVO, new Direccion());
        assertEquals(EstadoPedido.NUEVO, p.getEstado());
    }

    @Test
    void testDesafioValidarEstadoAsignado() {
        assertThrows(IllegalArgumentException.class, () -> Ej111EnumAndEmbeddable.desafioValidarEstadoAsignado(null));
    }

    @Test
    void testDesafioCoincideCiudad() {
        var d = Ej111EnumAndEmbeddable.desafioCrearDireccion("C", "Mad");
        var p = Ej111EnumAndEmbeddable.desafioCrearPedido(EstadoPedido.NUEVO, d);
        assertTrue(Ej111EnumAndEmbeddable.desafioCoincideCiudad(p, "Mad"));
    }

    @Test
    void testDesafioEsEntregado() {
        var p = Ej111EnumAndEmbeddable.desafioCrearPedido(EstadoPedido.ENTREGADO, new Direccion());
        assertTrue(Ej111EnumAndEmbeddable.desafioEsEntregado(p));
    }

    @Test
    void testDesafioTieneDireccion() {
        var p = Ej111EnumAndEmbeddable.desafioCrearPedido(EstadoPedido.NUEVO, new Direccion());
        assertTrue(Ej111EnumAndEmbeddable.desafioTieneDireccion(p));
    }
}
