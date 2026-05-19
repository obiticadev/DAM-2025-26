package com.masterclass.api.b02_json;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b02_json.Ej025JsonAnnotations.Usuario;
import static org.junit.jupiter.api.Assertions.*;

class Ej025JsonAnnotationsTest {

    @Test
    void renombraYOculta() {
        String j = Ej025JsonAnnotations.toJson(new Usuario("ana", "secreto"));
        assertTrue(j.contains("\"user_name\":\"ana\""), "debe usar user_name: " + j);
        assertFalse(j.contains("secreto"), "la password no debe aparecer: " + j);
        assertFalse(j.contains("password"));
    }
}
