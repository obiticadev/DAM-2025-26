package com.masterclass.api.b15_query;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b15_query.Ej139QueryByExample.PersonaProbe;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej139QueryByExampleTest {

    @Test
    void condicionesSoloNoNulos() {
        var c = Ej139QueryByExample.condicionesDe(new PersonaProbe("Ana", null, 30));
        assertEquals(2, c.size());
        assertEquals("Ana", c.get("nombre"));
        assertEquals(30, c.get("edad"));
        assertFalse(c.containsKey("ciudad"));
    }

    @Test
    void probeVacio() {
        assertTrue(Ej139QueryByExample.condicionesDe(new PersonaProbe(null, null, null)).isEmpty());
    }

    @Test
    void filtrado() {
        List<Map<String, Object>> datos = List.of(
                Map.of("nombre", "Ana", "ciudad", "Madrid", "edad", 30),
                Map.of("nombre", "Ana", "ciudad", "Soria", "edad", 40),
                Map.of("nombre", "Leo", "ciudad", "Madrid", "edad", 30));
        var r = Ej139QueryByExample.filtrar(datos, new PersonaProbe("Ana", null, null));
        assertEquals(2, r.size());
    }

    @Test
    void testRetoExtra01() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertEquals("Laptop", Ej139QueryByExample.obtenerNombre(p));
    }

    @Test
    void testRetoExtra02() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertEquals("Tech", Ej139QueryByExample.obtenerCategoria(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertTrue(Ej139QueryByExample.tieneCamposDeEjemplo(p));
    }

    @Test
    void testRetoExtra04() {
        var p = Ej139QueryByExample.crearEjemplo("PC", "Tech");
        assertNotNull(p);
    }

    @Test
    void testRetoExtra05() {
        var p = new Prod139("Laptop", "Tech", 100.0);
        assertTrue(Ej139QueryByExample.tienePrecioAsignado(p));
    }

    @Test
    void testRetoExtra06() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertNull(Ej139QueryByExample.obtenerId(p));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("laptop", Ej139QueryByExample.normalizarTexto("  Laptop  "));
    }

    @Test
    void testRetoExtra08() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertTrue(Ej139QueryByExample.esNuevo(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertTrue(Ej139QueryByExample.esValido(p));
    }

    @Test
    void testRetoExtra10() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertEquals("Example[Nombre=Laptop, Cat=Tech]", Ej139QueryByExample.formatearEjemplo(p));
    }

}
