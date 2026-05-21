package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b02_json.Ej025JsonAnnotations.Usuario;
import static org.junit.jupiter.api.Assertions.*;

class Ej025JsonAnnotationsTest {

    @Test
    void renombraYOculta() {
        String j = Ej025JsonAnnotations.toJson(new Usuario("ana", "secreto"));
        assertTrue(j.contains("\"user_name\":\"ana\""), "debe usar user_name: " + j);
        assertFalse(j.contains("secreto"), "la password no debe aparecer: " + j);
        assertFalse(j.contains("password"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void retoExtra01_serializarExcluyendoNulos() {
        var u = new Usuario("ana", null);
        String j = Ej025JsonAnnotations.serializarExcluyendoNulos(u);
        assertFalse(j.contains("password"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void retoExtra02_serializarConInclusionNoVacio() {
        var list = new java.util.ArrayList<String>();
        var wrapper = new Object() {
            public java.util.List<String> items = list;
        };
        String j = Ej025JsonAnnotations.serializarConInclusionNoVacio(wrapper);
        assertFalse(j.contains("items"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void retoExtra03_serializarConOrdenEspecifico() {
        var wrapper = new Object() {
            public String z = "z";
            public String a = "a";
        };
        String j = Ej025JsonAnnotations.serializarConOrdenEspecifico(wrapper);
        assertTrue(j.indexOf("\"a\":") < j.indexOf("\"z\":"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void retoExtra04_serializarConFormatoFecha() {
        var obj = new Object() {
            public java.time.LocalDate fecha = java.time.LocalDate.of(2026, 5, 21);
        };
        String j = Ej025JsonAnnotations.serializarConFormatoFecha(obj);
        assertTrue(j.contains("2026-05-21"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void retoExtra05_deserializarConAlias() {
        String json = "{\"full_name\":\"Ana María\"}";
        var aliasDto = Ej025JsonAnnotations.deserializarConAlias(json, Ej025JsonAnnotations.DtoConAlias.class);
        assertEquals("Ana María", aliasDto.nombre);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void retoExtra06_deserializarConCamposUnwrapped() {
        String json = "{\"id\":123,\"calle\":\"Mayor 12\",\"ciudad\":\"Madrid\"}";
        var unwrappedDto = Ej025JsonAnnotations.deserializarConCamposUnwrapped(json, Ej025JsonAnnotations.DtoConUnwrapped.class);
        assertEquals(123, unwrappedDto.id);
        assertNotNull(unwrappedDto.direccion);
        assertEquals("Mayor 12", unwrappedDto.direccion.calle);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void retoExtra07_serializarValorUnico() {
        String j = Ej025JsonAnnotations.serializarValorUnico(Ej025JsonAnnotations.EstadoPedido.ENVIADO);
        assertEquals("\"E\"", j);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void retoExtra08_esCampoIgnorado() {
        assertTrue(Ej025JsonAnnotations.esCampoIgnorado(Usuario.class, "password"));
        assertFalse(Ej025JsonAnnotations.esCampoIgnorado(Usuario.class, "nombre"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void retoExtra09_deserializarConPropiedadesDinamicas() {
        String json = "{\"id\":1,\"campo_extra\":\"valor_extra\"}";
        var dinamico = Ej025JsonAnnotations.deserializarConPropiedadesDinamicas(json, Ej025JsonAnnotations.DtoDinamico.class);
        assertEquals(1, dinamico.id);
        assertEquals("valor_extra", dinamico.extra().get("campo_extra"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void retoExtra10_serializarConPropiedadesDinamicas() {
        var dinamico = new Ej025JsonAnnotations.DtoDinamico();
        dinamico.id = 1;
        dinamico.setExtra("color", "rojo");
        String j = Ej025JsonAnnotations.serializarConPropiedadesDinamicas(dinamico);
        assertTrue(j.contains("\"color\":\"rojo\""));
        assertTrue(j.contains("\"id\":1"));
    }
}
