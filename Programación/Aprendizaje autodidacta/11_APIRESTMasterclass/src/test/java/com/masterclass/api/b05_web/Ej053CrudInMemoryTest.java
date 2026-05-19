package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej053CrudInMemoryTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej053CrudInMemory()).build();

    @Test
    void cicloDeVidaCompleto() throws Exception {
        mvc.perform(post("/api/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"comprar\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        mvc.perform(get("/api/tareas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("comprar"));

        mvc.perform(get("/api/tareas")).andExpect(jsonPath("$.length()").value(1));

        mvc.perform(delete("/api/tareas/1")).andExpect(status().isNoContent());
        mvc.perform(get("/api/tareas/1")).andExpect(status().isNotFound());
    }
}
