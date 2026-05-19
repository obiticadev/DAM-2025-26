package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej052DeleteResourceTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej052DeleteResource()).build();

    @Test
    void borraExistente204() throws Exception {
        mvc.perform(delete("/api/items/5")).andExpect(status().isNoContent());
    }

    @Test
    void inexistente404() throws Exception {
        mvc.perform(delete("/api/items/0")).andExpect(status().isNotFound());
    }
}
