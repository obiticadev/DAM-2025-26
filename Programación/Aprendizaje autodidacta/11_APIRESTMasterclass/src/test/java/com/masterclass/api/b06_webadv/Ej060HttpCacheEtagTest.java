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

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void testPasoExtra01() {
        assertNotNull(Ej060HttpCacheEtag.pasoExtra01("datos"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void testPasoExtra02() {
        assertEquals("W/\"abc\"", Ej060HttpCacheEtag.pasoExtra02("\"abc\""));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void testPasoExtra03() {
        assertTrue(Ej060HttpCacheEtag.pasoExtra03("\"123\"", "\"123\""));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void testPasoExtra04() {
        assertTrue(Ej060HttpCacheEtag.pasoExtra04("\"abc\", \"xyz\"", "\"xyz\""));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void testPasoExtra05() {
        assertNotNull(Ej060HttpCacheEtag.pasoExtra05("Wed, 21 Oct 2015 07:28:00 GMT"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void testPasoExtra06() {
        assertNotNull(Ej060HttpCacheEtag.pasoExtra06(java.time.Instant.now()));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void testPasoExtra07() {
        assertTrue(Ej060HttpCacheEtag.pasoExtra07(3600).contains("max-age=3600"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void testPasoExtra08() {
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        assertNotNull(Ej060HttpCacheEtag.pasoExtra08(builder));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void testPasoExtra09() {
        assertEquals(3600, Ej060HttpCacheEtag.pasoExtra09("max-age=3600, public"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void testPasoExtra10() {
        ResponseEntity<byte[]> response = Ej060HttpCacheEtag.pasoExtra10(new byte[]{1,2}, "\"abc\"", java.time.Instant.now(), 60);
        assertNotNull(response);
    }
}
