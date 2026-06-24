package com.masterclass.api.b46_datacomp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class Ej353ComponentSerializationTest {

    private static ConfiguracionComponente muestra() {
        return new ConfiguracionComponente("jdbc:h2:mem", "sa", "secreto", 30);
    }

    @Test
    void serializar() {
        byte[] bytes = Ej353ComponentSerialization.serializar(muestra());
        assertNotNull(bytes);
        assertTrue(bytes.length > 0);
        assertNull(Ej353ComponentSerialization.serializar(new Object())); // no serializable
        assertNull(Ej353ComponentSerialization.serializar(null));         // caso límite
    }

    @Test
    void deserializar() {
        byte[] bytes = Ej353ComponentSerialization.serializar(muestra());
        Object vuelta = Ej353ComponentSerialization.deserializar(bytes);
        assertEquals(muestra(), vuelta);
        assertNull(Ej353ComponentSerialization.deserializar(null)); // caso límite
    }

    @Test
    void testRetoExtra01_esSerializable() {
        assertTrue(Ej353ComponentSerialization.esSerializable(muestra()));
        assertFalse(Ej353ComponentSerialization.esSerializable(new Object()));
    }

    @Test
    void testRetoExtra02_tamanoSerializado() {
        assertTrue(Ej353ComponentSerialization.tamanoSerializado(muestra()) > 0);
    }

    @Test
    void testRetoExtra03_passwordNoSeSerializa() {
        assertTrue(Ej353ComponentSerialization.passwordNoSeSerializa());
    }

    @Test
    void testRetoExtra04_roundTrip() {
        assertEquals(muestra(), Ej353ComponentSerialization.roundTrip(muestra()));
    }

    @Test
    void testRetoExtra05_serialVersionUidDe() {
        assertEquals(46L, Ej353ComponentSerialization.serialVersionUidDe(ConfiguracionComponente.class));
    }

    @Test
    void testRetoExtra06_serializarComprimido() {
        byte[] comprimido = Ej353ComponentSerialization.serializarComprimido(muestra());
        assertNotNull(comprimido);
        assertTrue(comprimido.length > 0);
    }

    @Test
    void testRetoExtra07_deserializarComprimido() {
        byte[] comprimido = Ej353ComponentSerialization.serializarComprimido(muestra());
        assertEquals(muestra(), Ej353ComponentSerialization.deserializarComprimido(comprimido));
    }

    @Test
    void testRetoExtra08_serializarYLeerDeFichero(@TempDir Path dir) {
        File f = dir.resolve("comp.bin").toFile();
        assertEquals(muestra(), Ej353ComponentSerialization.serializarYLeerDeFichero(muestra(), f));
    }

    @Test
    void testRetoExtra09_clonarPorSerializacion() {
        ConfiguracionComponente original = muestra();
        Object clon = Ej353ComponentSerialization.clonarPorSerializacion(original);
        assertNotSame(original, clon);
        assertEquals(original, clon);
    }

    @Test
    void testRetoExtra10_restaurarTransientReconectando() {
        ConfiguracionComponente v = Ej353ComponentSerialization.restaurarTransientReconectando(muestra());
        assertEquals("reconectado", v.getPassword());
    }
}
