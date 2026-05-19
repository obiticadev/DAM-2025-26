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
}
