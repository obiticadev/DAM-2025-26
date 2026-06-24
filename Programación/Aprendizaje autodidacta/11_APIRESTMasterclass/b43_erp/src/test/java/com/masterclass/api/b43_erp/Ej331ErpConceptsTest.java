package com.masterclass.api.b43_erp;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej331ErpConceptsTest {

    @Test
    void areaDeModelo() {
        assertEquals("Ventas", Ej331ErpConcepts.areaDeModelo("sale.order"));
        assertEquals("CRM", Ej331ErpConcepts.areaDeModelo("res.partner"));
        assertEquals("Contabilidad", Ej331ErpConcepts.areaDeModelo("account.move"));
        assertEquals("Desconocido", Ej331ErpConcepts.areaDeModelo("no.existe")); // caso límite
        assertEquals("Desconocido", Ej331ErpConcepts.areaDeModelo(null));        // caso límite
    }

    @Test
    void esDatoMaestro() {
        assertTrue(Ej331ErpConcepts.esDatoMaestro("res.partner"));
        assertTrue(Ej331ErpConcepts.esDatoMaestro("product.template"));
        assertFalse(Ej331ErpConcepts.esDatoMaestro("sale.order"));
        assertFalse(Ej331ErpConcepts.esDatoMaestro(null)); // caso límite
    }

    @Test
    void testRetoExtra01_normalizarNombreModelo() {
        assertEquals("sale.order", Ej331ErpConcepts.normalizarNombreModelo("  Sale.Order "));
        assertEquals("", Ej331ErpConcepts.normalizarNombreModelo(null));
    }

    @Test
    void testRetoExtra02_esModeloValido() {
        assertTrue(Ej331ErpConcepts.esModeloValido("sale.order"));
        assertTrue(Ej331ErpConcepts.esModeloValido("sale.order.line"));
        assertFalse(Ej331ErpConcepts.esModeloValido("sale"));
    }

    @Test
    void testRetoExtra03_glosario() {
        assertEquals("Extraer, transformar y cargar", Ej331ErpConcepts.glosario("etl"));
        assertEquals("", Ej331ErpConcepts.glosario("xyz"));
    }

    @Test
    void testRetoExtra04_moduloDependeDe() {
        assertTrue(Ej331ErpConcepts.moduloDependeDe("ventas", "inventario"));
        assertFalse(Ej331ErpConcepts.moduloDependeDe("inventario", "ventas"));
    }

    @Test
    void testRetoExtra05_prioridadCarga() {
        assertTrue(Ej331ErpConcepts.prioridadCarga("res.partner")
                < Ej331ErpConcepts.prioridadCarga("sale.order"));
    }

    @Test
    void testRetoExtra06_rutaApiModelo() {
        assertEquals("/api/res/partner", Ej331ErpConcepts.rutaApiModelo("res.partner"));
    }

    @Test
    void testRetoExtra07_contarModelosDeArea() {
        List<String> modelos = List.of("sale.order", "res.partner", "sale.order.line");
        assertEquals(2, Ej331ErpConcepts.contarModelosDeArea(modelos, "Ventas"));
    }

    @Test
    void testRetoExtra08_mismaArea() {
        assertTrue(Ej331ErpConcepts.mismaArea("sale.order", "sale.order.line"));
        assertFalse(Ej331ErpConcepts.mismaArea("foo", "bar"));
    }

    @Test
    void testRetoExtra09_flujoOrderToCash() {
        String f = Ej331ErpConcepts.flujoOrderToCash();
        assertTrue(f.startsWith("presupuesto"));
        assertTrue(f.contains("factura"));
    }

    @Test
    void testRetoExtra10_tipoRelacionCampo() {
        assertEquals("many2one", Ej331ErpConcepts.tipoRelacionCampo("partner_id"));
        assertEquals("one2many/many2many", Ej331ErpConcepts.tipoRelacionCampo("line_ids"));
        assertEquals("escalar", Ej331ErpConcepts.tipoRelacionCampo("name"));
    }
}
