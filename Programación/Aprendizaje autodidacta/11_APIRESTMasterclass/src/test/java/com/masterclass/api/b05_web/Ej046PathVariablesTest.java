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
}
