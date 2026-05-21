package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej057FileUploadTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej057FileUpload()).build();

    @Test
    void subeYResume() throws Exception {
        var f = new MockMultipartFile("file", "datos.txt", "text/plain", "hola".getBytes());
        mvc.perform(multipart("/api/upload").file(f))
                .andExpect(status().isOk())
                .andExpect(content().string("datos.txt:4"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    void testPasoExtra01() {
        var f = new MockMultipartFile("file", "image.PNG", "image/png", new byte[10]);
        assertTrue(Ej057FileUpload.pasoExtra01(f, List.of("png", "jpg")));
        assertFalse(Ej057FileUpload.pasoExtra01(f, List.of("pdf", "zip")));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    void testPasoExtra02() {
        var f = new MockMultipartFile("file", "file.txt", "text/plain", new byte[100]);
        assertTrue(Ej057FileUpload.pasoExtra02(f, 200));
        assertFalse(Ej057FileUpload.pasoExtra02(f, 50));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    void testPasoExtra03() {
        var f = new MockMultipartFile("file", "doc.pdf", "application/pdf", new byte[10]);
        assertTrue(Ej057FileUpload.pasoExtra03(f, List.of("application/pdf", "image/png")));
        assertFalse(Ej057FileUpload.pasoExtra03(f, List.of("text/plain")));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    void testPasoExtra04() {
        var evilFile = new MockMultipartFile("file", "../../../etc/passwd", "text/plain", new byte[10]);
        assertEquals("passwd", Ej057FileUpload.pasoExtra04(evilFile));
        var simpleFile = new MockMultipartFile("file", "clean.txt", "text/plain", new byte[10]);
        assertEquals("clean.txt", Ej057FileUpload.pasoExtra04(simpleFile));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    void testPasoExtra05() {
        var f = new MockMultipartFile("file", "test.txt", "text/plain", "hello".getBytes());
        // md5 of "hello" is 5d41402abc4b2a76b9719d911017c592
        assertEquals("5d41402abc4b2a76b9719d911017c592", Ej057FileUpload.pasoExtra05(f));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    void testPasoExtra06() {
        var f1 = new MockMultipartFile("file1", "a.txt", "text/plain", new byte[15]);
        var f2 = new MockMultipartFile("file2", "b.txt", "text/plain", new byte[25]);
        assertEquals(40L, Ej057FileUpload.pasoExtra06(List.of(f1, f2)));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    void testPasoExtra07() {
        var img = new MockMultipartFile("file", "photo.jpg", "image/jpeg", new byte[10]);
        var doc = new MockMultipartFile("file", "doc.pdf", "application/pdf", new byte[10]);
        assertTrue(Ej057FileUpload.pasoExtra07(img));
        assertFalse(Ej057FileUpload.pasoExtra07(doc));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    void testPasoExtra08() {
        var csv = new MockMultipartFile("file", "data.csv", "text/csv", "id,nombre,edad\n1,Juan,20".getBytes());
        List<String> headers = Ej057FileUpload.pasoExtra08(csv);
        assertNotNull(headers);
        assertEquals(3, headers.size());
        assertEquals("id", headers.get(0));
        assertEquals("nombre", headers.get(1));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    void testPasoExtra09() {
        var f = new MockMultipartFile("file", "photo.png", "image/png", new byte[10]);
        String path = Ej057FileUpload.pasoExtra09(f, "C:/uploads");
        assertNotNull(path);
        assertTrue(path.replace('\\', '/').endsWith("uploads/photo.png"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    void testPasoExtra10() {
        var empty = new MockMultipartFile("file", "empty.txt", "text/plain", new byte[0]);
        var notEmpty = new MockMultipartFile("file", "data.txt", "text/plain", new byte[10]);
        assertTrue(Ej057FileUpload.pasoExtra10(empty));
        assertTrue(Ej057FileUpload.pasoExtra10(null));
        assertFalse(Ej057FileUpload.pasoExtra10(notEmpty));
    }
}
