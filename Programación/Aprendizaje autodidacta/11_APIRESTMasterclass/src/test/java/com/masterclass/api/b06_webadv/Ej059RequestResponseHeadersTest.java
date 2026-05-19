package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej059RequestResponseHeadersTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej059RequestResponseHeaders()).build();

    @Test
    void reflejaCorrelationId() throws Exception {
        mvc.perform(get("/api/trace").header("X-Request-Id", "abc"))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"))
                .andExpect(header().string("X-Correlation-Id", "abc"));
    }

    @Test
    void generaIdSiFalta() throws Exception {
        mvc.perform(get("/api/trace"))
                .andExpect(status().isOk())
                .andExpect(header().exists("X-Correlation-Id"));
    }
}
