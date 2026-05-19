package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
}
