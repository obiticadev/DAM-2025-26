package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej051PatchPartialUpdateTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej051PatchPartialUpdate()).build();

    @Test
    void soloCambiaNombre() throws Exception {
        mvc.perform(patch("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"nuevo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("nuevo"))
                .andExpect(jsonPath("$.activo").value(true));
    }
}
