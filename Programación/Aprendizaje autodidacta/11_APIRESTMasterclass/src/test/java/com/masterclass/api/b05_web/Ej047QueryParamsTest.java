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
}
