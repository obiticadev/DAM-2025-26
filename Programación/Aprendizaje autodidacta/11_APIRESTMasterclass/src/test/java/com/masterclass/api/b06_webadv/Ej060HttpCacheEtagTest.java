package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej060HttpCacheEtagTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej060HttpCacheEtag()).build();

    @Test
    void primeraDevuelve200ConEtag_segundaDevuelve304() throws Exception {
        MvcResult r = mvc.perform(get("/api/recurso"))
                .andExpect(status().isOk())
                .andExpect(header().exists("ETag"))
                .andReturn();
        String etag = r.getResponse().getHeader("ETag");
        assertNotNull(etag);

        mvc.perform(get("/api/recurso").header("If-None-Match", etag))
                .andExpect(status().isNotModified());
    }
}
