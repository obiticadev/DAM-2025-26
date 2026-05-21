package com.masterclass.api.b00_http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej008RestResourceModelerTest {

    @Test
    void coleccion() {
        assertEquals("/pedidos", Ej008RestResourceModeler.collection("pedidos"));
    }

    @Test
    void elemento() {
        assertEquals("/pedidos/42", Ej008RestResourceModeler.item("pedidos", 42));
    }

    @Test
    void anidado() {
        assertEquals("/pedidos/42/lineas", Ej008RestResourceModeler.nested("pedidos", 42, "lineas"));
    }

    @Test
    void recursoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej008RestResourceModeler.collection(""));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_esSustantivoPluralValido() {
        assertTrue(Ej008RestResourceModeler.esSustantivoPluralValido("usuarios"));
        assertFalse(Ej008RestResourceModeler.esSustantivoPluralValido("usuario"));
        assertFalse(Ej008RestResourceModeler.esSustantivoPluralValido("usuarios123"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_pluralizarRecurso() {
        assertEquals("pedidos", Ej008RestResourceModeler.pluralizarRecurso("pedido"));
        assertEquals("canales", Ej008RestResourceModeler.pluralizarRecurso("canal"));
        assertEquals("actividades", Ej008RestResourceModeler.pluralizarRecurso("actividad"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_esIdentificadorValido() {
        assertTrue(Ej008RestResourceModeler.esIdentificadorValido(123));
        assertTrue(Ej008RestResourceModeler.esIdentificadorValido("da39a3ee-5e6b-4b0d-9f0e-d1c05d83626e"));
        assertFalse(Ej008RestResourceModeler.esIdentificadorValido(-5));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_mapearRutaAPlantilla() {
        assertEquals("/usuarios/{id}/pedidos/{id}",
                Ej008RestResourceModeler.mapearRutaAPlantilla("/usuarios/123/pedidos/da39a3ee-5e6b-4b0d-9f0e-d1c05d83626e"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_esJerarquiaDeSubrecursoValida() {
        assertTrue(Ej008RestResourceModeler.esJerarquiaDeSubrecursoValida("/usuarios/12/pedidos/34"));
        assertFalse(Ej008RestResourceModeler.esJerarquiaDeSubrecursoValida("/tiendas/1/estanterias/2/cajas/3/productos"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_generarLinkRel() {
        assertEquals("/api/v1/pedidos/42", Ej008RestResourceModeler.generarLinkRel("/api/v1", "pedidos", 42));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_extraerIdDeRuta() {
        assertEquals("123", Ej008RestResourceModeler.extraerIdDeRuta("/usuarios/123/pedidos", "usuarios"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_formatearRutaConQuery() {
        assertEquals("/pedidos/42?embed=cliente&sort=fecha",
                Ej008RestResourceModeler.formatearRutaConQuery("pedidos", 42, "embed", "cliente", "sort", "fecha"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_esColeccionRuta() {
        assertTrue(Ej008RestResourceModeler.esColeccionRuta("/pedidos"));
        assertTrue(Ej008RestResourceModeler.esColeccionRuta("/pedidos/"));
        assertFalse(Ej008RestResourceModeler.esColeccionRuta("/pedidos/42"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_detectarAntipatronesRuta() {
        assertFalse(Ej008RestResourceModeler.detectarAntipatronesRuta("/pedidos/crear").isEmpty());
        assertTrue(Ej008RestResourceModeler.detectarAntipatronesRuta("/pedidos").isEmpty());
    }
}
