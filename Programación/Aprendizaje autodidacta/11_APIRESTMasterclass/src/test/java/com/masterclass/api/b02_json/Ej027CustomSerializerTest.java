package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import com.masterclass.api.b02_json.Ej027CustomSerializer.Precio;
import com.masterclass.api.b02_json.Ej027CustomSerializer.TarjetaCredito;
import com.masterclass.api.b02_json.Ej027CustomSerializer.Rol;
import com.masterclass.api.b02_json.Ej027CustomSerializer.Dinero;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej027CustomSerializerTest {

    @Test
    void formateaConSimbolo() {
        assertEquals("\"9.90 €\"", Ej027CustomSerializer.toJson(new Precio(9.9)));
        assertEquals("\"10.00 €\"", Ej027CustomSerializer.toJson(new Precio(10)));
    }

    @Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_LocalDateSerializer() {
        LocalDate fecha = LocalDate.of(2026, 5, 21);
        String json = Ej027CustomSerializer.serializarFecha(fecha);
        assertEquals("\"21-05-2026\"", json);
    }

    @Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_LocalDateDeserializer() {
        LocalDate fecha = Ej027CustomSerializer.deserializarFecha("\"21-05-2026\"");
        assertNotNull(fecha);
        assertEquals(2026, fecha.getYear());
        assertEquals(5, fecha.getMonthValue());
        assertEquals(21, fecha.getDayOfMonth());
    }

    @Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_TarjetaMaskSerializer() {
        TarjetaCredito tc = new TarjetaCredito("1234-5678-9012-3456");
        String json = Ej027CustomSerializer.serializarTarjeta(tc);
        assertEquals("\"****-****-****-3456\"", json);
        
        TarjetaCredito tcCorta = new TarjetaCredito("123");
        String jsonCorta = Ej027CustomSerializer.serializarTarjeta(tcCorta);
        assertEquals("\"****\"", jsonCorta);
    }

    @Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_RolDeserializer() {
        assertEquals(Rol.ADMIN, Ej027CustomSerializer.deserializarRol("\"admin\""));
        assertEquals(Rol.USER, Ej027CustomSerializer.deserializarRol("\"User\""));
        assertEquals(Rol.GUEST, Ej027CustomSerializer.deserializarRol("\"GUEST\""));
        assertEquals(Rol.ADMIN, Ej027CustomSerializer.deserializarRol("\"1\""));
        assertEquals(Rol.USER, Ej027CustomSerializer.deserializarRol("\"2\""));
        assertEquals(Rol.GUEST, Ej027CustomSerializer.deserializarRol("\"3\""));
    }

    @Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_SiNoBooleanSerializer() {
        assertEquals("\"SI\"", Ej027CustomSerializer.serializarBooleano(true));
        assertEquals("\"NO\"", Ej027CustomSerializer.serializarBooleano(false));
    }

    @Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_SiNoBooleanDeserializer() {
        assertTrue(Ej027CustomSerializer.deserializarBooleano("\"SI\""));
        assertTrue(Ej027CustomSerializer.deserializarBooleano("\"s\""));
        assertTrue(Ej027CustomSerializer.deserializarBooleano("\"1\""));
        assertTrue(Ej027CustomSerializer.deserializarBooleano("\"true\""));
        
        assertFalse(Ej027CustomSerializer.deserializarBooleano("\"NO\""));
        assertFalse(Ej027CustomSerializer.deserializarBooleano("\"n\""));
        assertFalse(Ej027CustomSerializer.deserializarBooleano("\"0\""));
        assertFalse(Ej027CustomSerializer.deserializarBooleano("\"false\""));
    }

    @Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_MapToArraySerializer() {
        Map<String, String> data = Map.of("color", "azul", "tamano", "grande");
        String json = Ej027CustomSerializer.serializarMapaComoArray(data);
        assertTrue(json.startsWith("["));
        assertTrue(json.endsWith("]"));
        assertTrue(json.contains("\"clave\":\"color\""));
        assertTrue(json.contains("\"valor\":\"azul\""));
        assertTrue(json.contains("\"clave\":\"tamano\""));
        assertTrue(json.contains("\"valor\":\"grande\""));
    }

    @Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_ArrayToMapDeserializer() {
        String json = "[{\"clave\":\"host\",\"valor\":\"localhost\"},{\"clave\":\"user\",\"valor\":\"root\"}]";
        Map<String, String> map = Ej027CustomSerializer.deserializarArrayComoMapa(json);
        assertNotNull(map);
        assertEquals("localhost", map.get("host"));
        assertEquals("root", map.get("user"));
        assertEquals(2, map.size());
    }

    @Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_DineroSerializer() {
        Dinero d = new Dinero(150.75, "usd");
        String json = Ej027CustomSerializer.serializarDinero(d);
        assertTrue(json.contains("\"monto\":150.75"));
        assertTrue(json.contains("\"codigoDivisa\":\"USD\""));
        assertTrue(json.contains("\"formateado\":\"150.75 USD\""));
    }

    @Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_crearMapperConModuloComplejo() {
        ObjectMapper mapper = Ej027CustomSerializer.crearMapperConModuloComplejo();
        assertNotNull(mapper);
        // Verifica que registra y serializa correctamente un LocalDate y un Dinero con el mismo mapper
        try {
            String fStr = mapper.writeValueAsString(LocalDate.of(2026, 12, 31));
            assertEquals("\"31-12-2026\"", fStr);
            
            String dStr = mapper.writeValueAsString(new Dinero(10.5, "eur"));
            assertTrue(dStr.contains("\"monto\":10.5"));
            assertTrue(dStr.contains("\"codigoDivisa\":\"EUR\""));
        } catch (Exception e) {
            fail("Falló al utilizar el ObjectMapper complejo registrado: " + e.getMessage());
        }
    }
}
