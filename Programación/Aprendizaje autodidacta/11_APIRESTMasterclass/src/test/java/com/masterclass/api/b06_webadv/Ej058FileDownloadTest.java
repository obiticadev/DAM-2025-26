package com.masterclass.api.b06_webadv;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Ej058FileDownloadTest {

    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new Ej058FileDownload()).build();

    @Test
    void descargaConDisposition() throws Exception {
        mvc.perform(get("/api/download"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"datos.txt\""))
                .andExpect(content().string("hola mundo"));
    }
}
