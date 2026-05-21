package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej048RequestBodyPostTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej048RequestBodyPost()).build();

    @Test
    void creaConCreated() throws Exception {
        mvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"cafe\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/items/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("cafe"));
    }

    @Test
    void nombreVacioEs400() throws Exception {
        mvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_crearConLista() throws Exception {
        mvc.perform(post("/api/items/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"nombre\":\"te\"},{\"nombre\":\"cafe\"}]"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.total").value(2))
                .andExpect(jsonPath("$.items[0].id").value(1))
                .andExpect(jsonPath("$.items[0].nombre").value("te"))
                .andExpect(jsonPath("$.items[1].id").value(2))
                .andExpect(jsonPath("$.items[1].nombre").value("cafe"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_crearConValidacion() throws Exception {
        mvc.perform(post("/api/items/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"ok\"}"))
                .andExpect(status().isUnprocessableEntity());

        mvc.perform(post("/api/items/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"cafe\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("cafe"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_crearConMetadatos() throws Exception {
        mvc.perform(post("/api/items/metadatos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"taza\",\"tags\":{\"color\":\"azul\",\"material\":\"ceramica\"}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("taza"))
                .andExpect(jsonPath("$.tags.color").value("azul"))
                .andExpect(jsonPath("$.tags.material").value("ceramica"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_crearConTipoContent() throws Exception {
        mvc.perform(post("/api/items/json-only")
                        .contentType(MediaType.APPLICATION_XML)
                        .content("<ItemIn><nombre>xml</nombre></ItemIn>"))
                .andExpect(status().isUnsupportedMediaType());

        mvc.perform(post("/api/items/json-only")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"json\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("json"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_crearConCabeceraAudit() throws Exception {
        mvc.perform(post("/api/items/audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Created-By", "Admin")
                        .content("{\"nombre\":\"mesa\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Item mesa creado por Admin"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_crearSinNombre() throws Exception {
        mvc.perform(post("/api/items/defensivo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("cuerpo invalido"));

        mvc.perform(post("/api/items/defensivo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("cuerpo invalido"));

        mvc.perform(post("/api/items/defensivo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"silla\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_crearConIdEspecifico() throws Exception {
        mvc.perform(post("/api/items/especifico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"nombre\":\"invalido\"}"))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/items/especifico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":99,\"nombre\":\"conflicto\"}"))
                .andExpect(status().isConflict());

        mvc.perform(post("/api/items/especifico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":10,\"nombre\":\"valido\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.nombre").value("valido"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_crearRetornandoLocationAbsoluta() throws Exception {
        mvc.perform(post("/api/items/absoluto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"libro\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", org.hamcrest.Matchers.containsString("/api/items/123")))
                .andExpect(jsonPath("$.id").value(123));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_crearRetornandoSoloId() throws Exception {
        mvc.perform(post("/api/items/solo-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"lampara\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(999L));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_crearConLocalDate() throws Exception {
        mvc.perform(post("/api/items/fecha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"fruta\",\"expiracion\":\"2026-05-21\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("fruta"))
                .andExpect(jsonPath("$.expiracion").value("2026-05-21"));
    }
}
