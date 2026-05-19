package com.masterclass.api.b05_web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej049ResponseEntityTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej049ResponseEntity()).build();

    @Test
    void teapotConHeaderYBody() throws Exception {
        mvc.perform(get("/api/teapot"))
                .andExpect(status().is(418))
                .andExpect(header().string("X-Powered-By", "masterclass"))
                .andExpect(content().string("no coffee"));
    }
}
