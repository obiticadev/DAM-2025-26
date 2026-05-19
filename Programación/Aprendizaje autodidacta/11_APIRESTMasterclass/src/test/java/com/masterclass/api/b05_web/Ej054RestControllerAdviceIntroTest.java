package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej054RestControllerAdviceIntroTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej054RestControllerAdviceIntro()).build();

    @Test
    void divisionOk() throws Exception {
        mvc.perform(get("/api/div").param("a", "10").param("b", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    void divisionPorCero400() throws Exception {
        mvc.perform(get("/api/div").param("a", "10").param("b", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("division por cero"));
    }
}
