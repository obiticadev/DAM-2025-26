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

    @Test
    void testPasoExtra01() throws Exception {
        assertFalse(Ej062FilterAndInterceptor.pasoExtra01(null, null, "key"));
    }

    @Test
    void testPasoExtra02() {
        assertFalse(Ej062FilterAndInterceptor.pasoExtra02("127.0.0.1", 11, 10));
    }

    @Test
    void testPasoExtra03() {
        assertNull(Ej062FilterAndInterceptor.pasoExtra03("/users/42", "/users/{id}"));
    }

    @Test
    void testPasoExtra04() {
        assertNull(Ej062FilterAndInterceptor.pasoExtra04(new Object()));
    }

    @Test
    void testPasoExtra05() {
        assertNull(Ej062FilterAndInterceptor.pasoExtra05(null));
    }

    @Test
    void testPasoExtra06() {
        assertNull(Ej062FilterAndInterceptor.pasoExtra06(null, "attr", String.class));
    }

    @Test
    void testPasoExtra07() {
        assertFalse(Ej062FilterAndInterceptor.pasoExtra07(null));
    }

    @Test
    void testPasoExtra08() {
        Ej062FilterAndInterceptor.pasoExtra08(null);
    }

    @Test
    void testPasoExtra09() {
        assertNull(Ej062FilterAndInterceptor.pasoExtra09(null));
    }

    @Test
    void testPasoExtra10() {
        assertNull(Ej062FilterAndInterceptor.pasoExtra10(null, new RuntimeException("err")));
    }
}
