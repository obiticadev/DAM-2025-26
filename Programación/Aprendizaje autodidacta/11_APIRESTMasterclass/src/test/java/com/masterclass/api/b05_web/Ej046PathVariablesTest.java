package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej046PathVariablesTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej046PathVariables()).build();

    @Test
    void ecoDelSegmento() throws Exception {
        mvc.perform(get("/api/echo/hola123"))
                .andExpect(status().isOk())
                .andExpect(content().string("hola123"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_echoDiferente() throws Exception {
        mvc.perform(get("/api/echo-diff/cualquierValor"))
                .andExpect(status().isOk())
                .andExpect(content().string("cualquierValor"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_multiplesVariables() throws Exception {
        mvc.perform(get("/api/multi/hola/sub/mundo"))
                .andExpect(status().isOk())
                .andExpect(content().string("hola-mundo"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_variableInteger() throws Exception {
        mvc.perform(get("/api/buscar/25"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_variableUuid() throws Exception {
        var uuid = java.util.UUID.randomUUID();
        mvc.perform(get("/api/uuid/" + uuid))
                .andExpect(status().isOk())
                .andExpect(content().string(uuid.toString()));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_variableRegex() throws Exception {
        mvc.perform(get("/api/codigo/ABC-1234"))
                .andExpect(status().isOk())
                .andExpect(content().string("codigo valido: ABC-1234"));

        mvc.perform(get("/api/codigo/abc-1234"))
                .andExpect(status().isNotFound());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_variableFecha() throws Exception {
        mvc.perform(get("/api/fecha/2026-05-21"))
                .andExpect(status().isOk())
                .andExpect(content().string("2026"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_variableOpcional() throws Exception {
        mvc.perform(get("/api/opcional/adicional"))
                .andExpect(status().isOk())
                .andExpect(content().string("adicional"));

        mvc.perform(get("/api/opcional"))
                .andExpect(status().isOk())
                .andExpect(content().string("vacio"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_mapaVariables() throws Exception {
        mvc.perform(get("/api/dinamico/perro/gato"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.c1").value("perro"))
                .andExpect(jsonPath("$.c2").value("gato"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_listaVariables() throws Exception {
        mvc.perform(get("/api/batch/10,20,30,40"))
                .andExpect(status().isOk())
                .andExpect(content().string("4"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_matrizVariables() throws Exception {
        var helper = new org.springframework.web.util.UrlPathHelper();
        helper.setRemoveSemicolonContent(false);
        var localMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders
                .standaloneSetup(new Ej046PathVariables())
                .setUrlPathHelper(helper)
                .build();

        localMvc.perform(get("/api/coches/renault;color=rojo;anio=2020"))
                .andExpect(status().isOk())
                .andExpect(content().string("modelo:renault, color:rojo, anio:2020"));
    }
}
