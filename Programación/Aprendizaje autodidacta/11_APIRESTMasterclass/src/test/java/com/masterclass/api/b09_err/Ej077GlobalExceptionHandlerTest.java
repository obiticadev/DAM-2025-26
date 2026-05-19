package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej077GlobalExceptionHandlerTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej077GlobalExceptionHandler()).build();

    @Test
    void existenteOk() throws Exception {
        mvc.perform(get("/api/recurso/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("recurso-3"));
    }

    @Test
    void inexistenteSeTraduceA404() throws Exception {
        mvc.perform(get("/api/recurso/4"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("no existe: 4"));
    }
}
