package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej062FilterAndInterceptorTest {

    @RestController
    static class Dummy {
        @GetMapping("/ping")
        String ping() {
            return "pong";
        }
    }

    @Test
    void interceptaCuentaYAnadeHeader() throws Exception {
        var interceptor = new Ej062FilterAndInterceptor();
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new Dummy())
                .addInterceptors(interceptor)
                .build();

        mvc.perform(get("/ping"))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Handled", "true"));
        mvc.perform(get("/ping")).andExpect(status().isOk());

        assertEquals(2, interceptor.conteo());
    }
}
