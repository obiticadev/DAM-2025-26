package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

class Ej058FileDownloadTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej058FileDownload()).build();

    @Test
    void descargaConDisposition() throws Exception {
        mvc.perform(get("/api/download"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"datos.txt\""))
                .andExpect(content().string("hola mundo"));
    }

    @Test
    void testPasoExtra01() {
        assertEquals("archivo_limpio.txt", Ej058FileDownload.pasoExtra01("archivo/limpio.txt"));
        assertEquals("test_archivo.png", Ej058FileDownload.pasoExtra01("test:archivo.png"));
    }

    @Test
    void testPasoExtra02() {
        assertEquals("attachment; filename*=UTF-8''canci%C3%B3n.mp3", Ej058FileDownload.pasoExtra02("canción.mp3"));
    }

    @Test
    void testPasoExtra03() {
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        ResponseEntity.BodyBuilder result = Ej058FileDownload.pasoExtra03(builder, new byte[]{1, 2, 3});
        assertNotNull(result);
    }

    @Test
    void testPasoExtra04() {
        assertEquals("text/csv", Ej058FileDownload.pasoExtra04("csv"));
        assertEquals("application/pdf", Ej058FileDownload.pasoExtra04("pdf"));
        assertEquals("application/octet-stream", Ej058FileDownload.pasoExtra04("unknown"));
    }

    @Test
    void testPasoExtra05() {
        assertEquals("inline", Ej058FileDownload.pasoExtra05("pdf"));
        assertEquals("inline", Ej058FileDownload.pasoExtra05("png"));
        assertEquals("attachment", Ej058FileDownload.pasoExtra05("zip"));
    }

    @Test
    void testPasoExtra06() {
        assertNotNull(Ej058FileDownload.pasoExtra06("test".getBytes()));
    }

    @Test
    void testPasoExtra07() {
        byte[] input = "hello stream".getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(input);
        assertArrayEquals(input, Ej058FileDownload.pasoExtra07(bais));
    }

    @Test
    void testPasoExtra08() {
        assertNotNull(Ej058FileDownload.pasoExtra08("contenido a comprimir"));
    }

    @Test
    void testPasoExtra09() {
        assertTrue(Ej058FileDownload.pasoExtra09("C:/app/sandbox/file.txt", "C:/app/sandbox"));
        assertFalse(Ej058FileDownload.pasoExtra09("C:/app/other/file.txt", "C:/app/sandbox"));
    }

    @Test
    void testPasoExtra10() {
        ResponseEntity<byte[]> response = Ej058FileDownload.pasoExtra10("test.bin", new byte[]{10, 20});
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }
}

