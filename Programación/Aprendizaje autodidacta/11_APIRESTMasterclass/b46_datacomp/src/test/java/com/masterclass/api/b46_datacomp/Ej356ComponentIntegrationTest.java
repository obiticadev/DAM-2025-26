package com.masterclass.api.b46_datacomp;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

class Ej356ComponentIntegrationTest {

    private static Configuracion cfg() {
        return new Configuracion("jdbc:h2:mem", "sa", 10);
    }

    @Test
    void integrar() {
        ComponenteEco comp = new ComponenteEco();
        assertTrue(Ej356ComponentIntegration.integrar(comp, cfg()));
        assertEquals(cfg(), comp.getConfiguracion()); // aplicó la configuración
        assertFalse(Ej356ComponentIntegration.integrar(null, cfg())); // caso límite
        assertFalse(Ej356ComponentIntegration.integrar(comp, null));  // caso límite
    }

    @Test
    void usar() {
        ComponenteEco comp = new ComponenteEco();
        assertEquals("eco:hola", Ej356ComponentIntegration.usar(comp, "hola"));
        assertNull(Ej356ComponentIntegration.usar(comp, null)); // caso límite
    }

    @Test
    void testRetoExtra01_inyectarPorConstructor() {
        ComponenteDao comp = Ej356ComponentIntegration.inyectarPorConstructor(cfg());
        assertEquals(cfg(), ((ComponenteEco) comp).getConfiguracion());
    }

    @Test
    void testRetoExtra02_fabricarComponente() {
        assertNotNull(Ej356ComponentIntegration.fabricarComponente("eco"));
        assertNull(Ej356ComponentIntegration.fabricarComponente("desconocido"));
    }

    @Test
    void testRetoExtra03_configuracionDesdeProperties() {
        Properties p = new Properties();
        p.setProperty("url", "jdbc:h2:mem");
        p.setProperty("usuario", "sa");
        p.setProperty("tamanoPagina", "20");
        Configuracion c = Ej356ComponentIntegration.configuracionDesdeProperties(p);
        assertEquals("jdbc:h2:mem", c.url());
        assertEquals(20, c.tamanoPagina());
    }

    @Test
    void testRetoExtra04_coordinarComponentes() {
        List<ComponenteDao> comps = List.of(new ComponenteEco(), new ComponenteEco());
        assertEquals(List.of("eco:hola", "eco:hola"),
                Ej356ComponentIntegration.coordinarComponentes(comps, "hola"));
    }

    @Test
    void testRetoExtra05_cicloDeVida() {
        assertEquals("init,destroy", Ej356ComponentIntegration.cicloDeVida());
    }

    @Test
    void testRetoExtra06_usarConMock() {
        assertEquals("mock:x", Ej356ComponentIntegration.usarConMock("x"));
    }

    @Test
    void testRetoExtra07_propagarError() {
        assertEquals("error:ERROR",
                Ej356ComponentIntegration.propagarError(new ComponenteEco(), "ERROR"));
    }

    @Test
    void testRetoExtra08_descubrirConServiceLoader() {
        assertTrue(Ej356ComponentIntegration.descubrirConServiceLoader() >= 1);
    }

    @Test
    void testRetoExtra09_registrarYUsar() {
        assertEquals(1, Ej356ComponentIntegration.registrarYUsar("hola"));
    }

    @Test
    void testRetoExtra10_integrarTodo() {
        assertEquals("eco:hola", Ej356ComponentIntegration.integrarTodo(cfg(), "hola"));
    }
}
