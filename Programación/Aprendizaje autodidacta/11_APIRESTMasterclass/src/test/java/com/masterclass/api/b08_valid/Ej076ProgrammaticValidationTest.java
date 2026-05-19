package com.masterclass.api.b08_valid;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b08_valid.Ej076ProgrammaticValidation.ComentarioDto;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej076ProgrammaticValidationTest {

    @Test
    void detectaTextoVacio() {
        List<String> msgs = Ej076ProgrammaticValidation.mensajesDeError(new ComentarioDto(""));
        assertEquals(List.of("el texto es obligatorio"), msgs);
    }

    @Test
    void validoSinMensajes() {
        assertTrue(Ej076ProgrammaticValidation.mensajesDeError(new ComentarioDto("hola")).isEmpty());
    }
}
