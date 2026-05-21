package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej045HelloControllerTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej045HelloController()).build();

    @Test
    void devuelveHola() throws Exception {
        mvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hola"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_saludoPersonalizado() throws Exception {
        mvc.perform(get("/api/saludo-custom").header("X-User-Name", "Carlos"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola, Carlos"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_saludoJson() throws Exception {
        mvc.perform(get("/api/saludo-json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("hola"))
                .andExpect(jsonPath("$.timestamp").value(1234567890L));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_endpointCreado() throws Exception {
        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/creado"))
                .andExpect(status().isCreated())
                .andExpect(content().string("recurso creado"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_aliasSaludo() throws Exception {
        mvc.perform(get("/api/alias1"))
                .andExpect(status().isOk())
                .andExpect(content().string("alias"));

        mvc.perform(get("/api/alias2"))
                .andExpect(status().isOk())
                .andExpect(content().string("alias"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_echoMethod() throws Exception {
        mvc.perform(get("/api/echo-method"))
                .andExpect(status().isOk())
                .andExpect(content().string("GET"));

        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/echo-method"))
                .andExpect(status().isOk())
                .andExpect(content().string("POST"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_cabecerasPersonalizadas() throws Exception {
        mvc.perform(get("/api/headers")
                .header("User-Agent", "Mozilla")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['User-Agent']").value("Mozilla"))
                .andExpect(jsonPath("$.Accept").value("application/json"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_contentTypeJson() throws Exception {
        mvc.perform(get("/api/json-only"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_consumesTextPlain() throws Exception {
        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/text-only")
                .contentType(org.springframework.http.MediaType.TEXT_PLAIN)
                .content("hola mundo"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_teapotStatus() throws Exception {
        mvc.perform(get("/api/teapot"))
                .andExpect(status().is(418))
                .andExpect(content().string("soy una tetera"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_contadorVisitas() throws Exception {
        mvc.perform(get("/api/contador"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        mvc.perform(get("/api/contador"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }
}
