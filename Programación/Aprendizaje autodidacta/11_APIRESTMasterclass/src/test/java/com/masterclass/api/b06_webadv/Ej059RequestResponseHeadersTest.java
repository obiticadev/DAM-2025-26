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

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void testPasoExtra01() {
        org.junit.jupiter.api.Assertions.assertEquals("es-ES", Ej059RequestResponseHeaders.pasoExtra01("es-ES,es;q=0.9"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void testPasoExtra02() {
        org.junit.jupiter.api.Assertions.assertTrue(Ej059RequestResponseHeaders.pasoExtra02("XMLHttpRequest"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void testPasoExtra03() {
        String[] creds = Ej059RequestResponseHeaders.pasoExtra03("Basic dXNlcjpwYXNz");
        org.junit.jupiter.api.Assertions.assertNotNull(creds);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void testPasoExtra04() {
        org.junit.jupiter.api.Assertions.assertEquals("token123", Ej059RequestResponseHeaders.pasoExtra04("Bearer token123"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void testPasoExtra05() {
        org.junit.jupiter.api.Assertions.assertEquals("192.168.1.1", Ej059RequestResponseHeaders.pasoExtra05("192.168.1.1, 10.0.0.1"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void testPasoExtra06() {
        org.junit.jupiter.api.Assertions.assertNotNull(Ej059RequestResponseHeaders.pasoExtra06("Mozilla/5.0"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void testPasoExtra07() {
        org.springframework.http.ResponseEntity.BodyBuilder builder = org.springframework.http.ResponseEntity.ok();
        org.junit.jupiter.api.Assertions.assertNotNull(Ej059RequestResponseHeaders.pasoExtra07(builder));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void testPasoExtra08() {
        org.junit.jupiter.api.Assertions.assertTrue(Ej059RequestResponseHeaders.pasoExtra08("gzip, deflate"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void testPasoExtra09() {
        java.util.List<String> tags = Ej059RequestResponseHeaders.pasoExtra09("admin, user");
        org.junit.jupiter.api.Assertions.assertNotNull(tags);
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void testPasoExtra10() {
        org.junit.jupiter.api.Assertions.assertFalse(Ej059RequestResponseHeaders.pasoExtra10("wrongsignature", "hello"));
    }
}
