package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej092StrategyAndPolicyTest {

    @Test
    void estrategias() {
        assertEquals(100, Ej092StrategyAndPolicy.aplicar(100, "none"), 0.0001);
        assertEquals(70, Ej092StrategyAndPolicy.aplicar(100, "black-friday"), 0.0001);
        assertEquals(90, Ej092StrategyAndPolicy.aplicar(100, "vip"), 0.0001);
    }

    @Test
    void desconocida() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej092StrategyAndPolicy.aplicar(100, "ninguna"));
    }

    @Test
    void precioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej092StrategyAndPolicy.aplicar(-1, "none"));
    }

@Test
    void testDesafioEstrategiaNone() {
        var op = Ej092StrategyAndPolicy.desafioEstrategiaNone();
        assertEquals(100.0, op.applyAsDouble(100.0), 0.001);
    }

    @Test
    void testDesafioEstrategiaBlackFriday() {
        var op = Ej092StrategyAndPolicy.desafioEstrategiaBlackFriday();
        assertEquals(70.0, op.applyAsDouble(100.0), 0.001);
    }

    @Test
    void testDesafioEstrategiaVip() {
        var op = Ej092StrategyAndPolicy.desafioEstrategiaVip();
        assertEquals(90.0, op.applyAsDouble(100.0), 0.001);
    }

    @Test
    void testDesafioCrearMapaEstrategias() {
        var mapa = Ej092StrategyAndPolicy.desafioCrearMapaEstrategias(p->p, p->p, p->p);
        assertEquals(3, mapa.size());
    }

    @Test
    void testDesafioVerificarOperador() {
        assertTrue(Ej092StrategyAndPolicy.desafioVerificarOperador(p -> p * 0.5, 100.0, 50.0));
    }

    @Test
    void testDesafioValidarPrecioEstrategia() {
        assertThrows(IllegalArgumentException.class, () -> Ej092StrategyAndPolicy.desafioValidarPrecioEstrategia(-1));
        assertDoesNotThrow(() -> Ej092StrategyAndPolicy.desafioValidarPrecioEstrategia(0));
    }

    @Test
    void testDesafioObtenerEstrategiaEspecifica() {
        var m = Map.of("vip", Ej092StrategyAndPolicy.desafioEstrategiaVip());
        assertNotNull(Ej092StrategyAndPolicy.desafioObtenerEstrategiaEspecífica(m, "vip"));
    }

    @Test
    void testDesafioLanzarEstrategiaInexistente() {
        var m = Map.of("vip", Ej092StrategyAndPolicy.desafioEstrategiaVip());
        assertThrows(IllegalArgumentException.class, () -> Ej092StrategyAndPolicy.desafioLanzarEstrategiaInexistente(m, "hack"));
    }

    @Test
    void testDesafioEjecutarEstrategia() {
        assertEquals(80.0, Ej092StrategyAndPolicy.desafioEjecutarEstrategia(p -> p - 20, 100.0), 0.001);
    }

    @Test
    void testDesafioEstrategiasInmodificables() {
        assertDoesNotThrow(() -> Ej092StrategyAndPolicy.desafioEstrategiasInmodificables(Ej092StrategyAndPolicy.estrategias()));
    }
}
