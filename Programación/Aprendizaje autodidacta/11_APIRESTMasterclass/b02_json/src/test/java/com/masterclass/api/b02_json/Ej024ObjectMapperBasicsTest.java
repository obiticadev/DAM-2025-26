package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b02_json.Ej024ObjectMapperBasics.Cliente;
import static org.junit.jupiter.api.Assertions.*;

class Ej024ObjectMapperBasicsTest {

    @Test
    void serializa() {
        String j = Ej024ObjectMapperBasics.toJson(new Cliente(1L, "Ana"));
        assertTrue(j.contains("\"id\":1"));
        assertTrue(j.contains("\"nombre\":\"Ana\""));
    }

    @Test
    void deserializa() {
        Cliente c = Ej024ObjectMapperBasics.fromJson("{\"id\":5,\"nombre\":\"Leo\"}");
        assertEquals(5L, c.id());
        assertEquals("Leo", c.nombre());
    }

    @Test
    void roundTrip() {
        Cliente o = new Cliente(9L, "Zoe");
        assertEquals(o, Ej024ObjectMapperBasics.fromJson(Ej024ObjectMapperBasics.toJson(o)));
    }

    @Test
    void retoExtra01_serializarBonito() {
        var c = new Cliente(1L, "Ana");
        String j = Ej024ObjectMapperBasics.serializarBonito(c);
        assertTrue(j.contains("\n") || j.contains("\r"));
    }

    @Test
    void retoExtra02_deserializarGenerico() {
        String json = "{\"id\":7,\"nombre\":\"Luis\"}";
        Cliente c = Ej024ObjectMapperBasics.deserializarGenerico(json, Cliente.class);
        assertEquals(7L, c.id());
        assertEquals("Luis", c.nombre());
    }

    @Test
    void retoExtra03_esJsonValido() {
        assertTrue(Ej024ObjectMapperBasics.esJsonValido("{\"a\":1}"));
        assertFalse(Ej024ObjectMapperBasics.esJsonValido("{mal-formado}"));
    }

    @Test
    void retoExtra04_deserializarIgnorandoCamposDesconocidos() {
        String json = "{\"id\":1,\"nombre\":\"Ana\",\"email\":\"ana@correo.com\"}";
        Cliente c = Ej024ObjectMapperBasics.deserializarIgnorandoCamposDesconocidos(json);
        assertNotNull(c);
        assertEquals(1L, c.id());
        assertEquals("Ana", c.nombre());
    }

    @Test
    void retoExtra05_serializarABytes() {
        var c = new Cliente(1L, "Ana");
        byte[] bytes = Ej024ObjectMapperBasics.serializarABytes(c);
        assertNotNull(bytes);
        assertTrue(bytes.length > 0);
    }

    @Test
    void retoExtra06_deserializarDesdeBytes() {
        var c = new Cliente(1L, "Ana");
        byte[] bytes = Ej024ObjectMapperBasics.serializarABytes(c);
        Cliente result = Ej024ObjectMapperBasics.deserializarDesdeBytes(bytes);
        assertEquals(c, result);
    }

    @Test
    void retoExtra07_actualizarConMap() {
        var base = new Cliente(1L, "Ana");
        String patch = "{\"nombre\":\"Ana María\"}";
        Cliente actualizado = Ej024ObjectMapperBasics.actualizarConMap(patch, base);
        assertEquals(1L, actualizado.id());
        assertEquals("Ana María", actualizado.nombre());
    }

    @Test
    void retoExtra08_convertirEntreTipos() {
        java.util.Map<String, Object> map = java.util.Map.of("id", 123, "nombre", "Luis");
        Cliente c = Ej024ObjectMapperBasics.convertirEntreTipos(map, Cliente.class);
        assertEquals(123L, c.id());
        assertEquals("Luis", c.nombre());
    }

    @Test
    void retoExtra09_serializarConIdentacionYComprobar() {
        var c = new Cliente(1L, "Ana");
        assertTrue(Ej024ObjectMapperBasics.serializarConIdentacionYComprobar(c));
    }

    @Test
    void retoExtra10_escribirYLeerDeArchivoTemporal() {
        var c = new Cliente(99L, "Carlos");
        Cliente result = Ej024ObjectMapperBasics.escribirYLeerDeArchivoTemporal(c);
        assertEquals(c, result);
    }
}
