package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej047QueryParamsTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej047QueryParams()).build();

    @Test
    void sumaConAmbos() throws Exception {
        mvc.perform(get("/api/sum").param("a", "2").param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    void bPorDefectoCero() throws Exception {
        mvc.perform(get("/api/sum").param("a", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_multiplicar() throws Exception {
        mvc.perform(get("/api/mult").param("a", "6").param("b", "7"))
                .andExpect(status().isOk())
                .andExpect(content().string("42"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_saludoPorDefecto() throws Exception {
        mvc.perform(get("/api/saludo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola"));

        mvc.perform(get("/api/saludo").param("idioma", "en"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));

        mvc.perform(get("/api/saludo").param("idioma", "fr"))
                .andExpect(status().isOk())
                .andExpect(content().string("Aloha"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_listaQueryParams() throws Exception {
        mvc.perform(get("/api/nombres").param("nombres", "Ana", "Luis", "Pedro"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ana,Luis,Pedro"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_mapaQueryParams() throws Exception {
        mvc.perform(get("/api/filtro").param("criterio", "activo").param("orden", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.criterio").value("activo"))
                .andExpect(jsonPath("$.orden").value("desc"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_parametroOpcional() throws Exception {
        mvc.perform(get("/api/buscar").param("q", "muelle"))
                .andExpect(status().isOk())
                .andExpect(content().string("buscando: muelle"));

        mvc.perform(get("/api/buscar"))
                .andExpect(status().isOk())
                .andExpect(content().string("sin criterio"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_booleanoParam() throws Exception {
        mvc.perform(get("/api/transformar").param("texto", "antigravity").param("invertido", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string("ytivargitna"));

        mvc.perform(get("/api/transformar").param("texto", "antigravity"))
                .andExpect(status().isOk())
                .andExpect(content().string("antigravity"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_fechaParam() throws Exception {
        mvc.perform(get("/api/eventos").param("limite", "2026-05-21"))
                .andExpect(status().isOk())
                .andExpect(content().string("MAY"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_enumParam() throws Exception {
        mvc.perform(get("/api/horario").param("dia", "LUNES"))
                .andExpect(status().isOk())
                .andExpect(content().string("dia laborable"));

        mvc.perform(get("/api/horario").param("dia", "SABADO"))
                .andExpect(status().isOk())
                .andExpect(content().string("fin de semana"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_validarMayorDeEdad() throws Exception {
        mvc.perform(get("/api/edad").param("edad", "20"))
                .andExpect(status().isOk())
                .andExpect(content().string("acceso concedido"));

        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            mvc.perform(get("/api/edad").param("edad", "15"));
        });
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_queryConMismoNombre() throws Exception {
        mvc.perform(get("/api/profile").param("username", "master"))
                .andExpect(status().isOk())
                .andExpect(content().string("perfil de: master"));
    }
}
