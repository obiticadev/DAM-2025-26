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

    @Test
    void retoExtra01_echoDiferente() throws Exception {
        mvc.perform(get("/api/echo-diff/cualquierValor"))
                .andExpect(status().isOk())
                .andExpect(content().string("cualquierValor"));
    }

    @Test
    void retoExtra02_multiplesVariables() throws Exception {
        mvc.perform(get("/api/multi/hola/sub/mundo"))
                .andExpect(status().isOk())
                .andExpect(content().string("hola-mundo"));
    }

    @Test
    void retoExtra03_variableInteger() throws Exception {
        mvc.perform(get("/api/buscar/25"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @Test
    void retoExtra04_variableUuid() throws Exception {
        var uuid = java.util.UUID.randomUUID();
        mvc.perform(get("/api/uuid/" + uuid))
                .andExpect(status().isOk())
                .andExpect(content().string(uuid.toString()));
    }

    @Test
    void retoExtra05_variableRegex() throws Exception {
        mvc.perform(get("/api/codigo/ABC-1234"))
                .andExpect(status().isOk())
                .andExpect(content().string("codigo valido: ABC-1234"));

        mvc.perform(get("/api/codigo/abc-1234"))
                .andExpect(status().isNotFound());
    }

    @Test
    void retoExtra06_variableFecha() throws Exception {
        mvc.perform(get("/api/fecha/2026-05-21"))
                .andExpect(status().isOk())
                .andExpect(content().string("2026"));
    }

    @Test
    void retoExtra07_variableOpcional() throws Exception {
        mvc.perform(get("/api/opcional/adicional"))
                .andExpect(status().isOk())
                .andExpect(content().string("adicional"));

        mvc.perform(get("/api/opcional"))
                .andExpect(status().isOk())
                .andExpect(content().string("vacio"));
    }

    @Test
    void retoExtra08_mapaVariables() throws Exception {
        mvc.perform(get("/api/dinamico/perro/gato"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.c1").value("perro"))
                .andExpect(jsonPath("$.c2").value("gato"));
    }

    @Test
    void retoExtra09_listaVariables() throws Exception {
        mvc.perform(get("/api/batch/10,20,30,40"))
                .andExpect(status().isOk())
                .andExpect(content().string("4"));
    }

    @Test
    void retoExtra10_matrizVariables() throws Exception {
        // Spring 6 usa PathPatternParser por defecto, que conserva las matrix
        // variables sin necesidad de UrlPathHelper.setRemoveSemicolonContent(false)
        // (API eliminada del builder standalone en Spring 6.0).
        var localMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders
                .standaloneSetup(new Ej046PathVariables())
                .build();

        localMvc.perform(get("/api/coches/renault;color=rojo;anio=2020"))
                .andExpect(status().isOk())
                .andExpect(content().string("modelo:renault, color:rojo, anio:2020"));
    }
}
