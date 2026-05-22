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

    @Test
    void testRetoExtra01_esUsuarioValido() {
        // Verifica si un usuario cumple los minimos de registro (username largo y mayor de edad).
        assertTrue(Ej076ProgrammaticValidation.esUsuarioValido("user123", 18));
    }

    @Test
    void testRetoExtra02_validarComentario() {
        // Determina si un comentario es apto para publicar (no nulo, no vacio y menor de 200 chars).
        assertTrue(Ej076ProgrammaticValidation.validarComentario("buen curso"));
    }

    @Test
    void testRetoExtra03_esIpValida() {
        // Valida sintaxis basica de una direccion IPv4.
        assertTrue(Ej076ProgrammaticValidation.esIpValida("192.168.1.1"));
    }

    @Test
    void testRetoExtra04_esRangoValido() {
        // Valida que un valor este estrictamente entre min y max.
        assertTrue(Ej076ProgrammaticValidation.esRangoValido(1, 10, 5));
    }

    @Test
    void testRetoExtra05_esColorHexValido() {
        // Verifica que el color sea un codigo hexadecimal de 6 caracteres valido.
        assertTrue(Ej076ProgrammaticValidation.esColorHexValido("#FFFFFF"));
    }

    @Test
    void testRetoExtra06_esUrlValida() {
        // Comprueba que empiece por http:// o https:// de manera segura.
        assertTrue(Ej076ProgrammaticValidation.esUrlValida("https://google.com"));
    }

    @Test
    void testRetoExtra07_tienePalabraProhibida() {
        // Busca palabras ofensivas de una lista negra dentro del texto.
        assertTrue(Ej076ProgrammaticValidation.tienePalabraProhibida("spam total", java.util.List.of("spam")));
    }

    @Test
    void testRetoExtra08_validarIdFormato() {
        // Valida si el ID tiene patron alfanumerico con guiones.
        assertTrue(Ej076ProgrammaticValidation.validarIdFormato("USR-481516"));
    }

    @Test
    void testRetoExtra09_esExtensionPermitida() {
        // Verifica si la extension del archivo esta entre las permitidas.
        assertTrue(Ej076ProgrammaticValidation.esExtensionPermitida("foto.png", java.util.List.of("png", "jpg")));
    }

    @Test
    void testRetoExtra10_esTarjetaCreditoValida() {
        // Valida que el numero de tarjeta tenga 16 digitos sin espacios.
        assertTrue(Ej076ProgrammaticValidation.esTarjetaCreditoValida("1234567812345678"));
    }

}