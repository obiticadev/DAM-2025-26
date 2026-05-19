package com.masterclass.api.b16_xml;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class Ej147FileBackedRepositoryTest {

    @Test
    void guardaYRecupera(@TempDir Path dir) {
        Ej147FileBackedRepository repo =
                new Ej147FileBackedRepository(dir.resolve("notas.txt"));
        repo.save(1L, "hola");
        repo.save(2L, "mundo");
        assertEquals(Optional.of("hola"), repo.findById(1L));
        assertEquals(2, repo.findAll().size());
    }

    @Test
    void upsertReemplaza(@TempDir Path dir) {
        Ej147FileBackedRepository repo =
                new Ej147FileBackedRepository(dir.resolve("notas.txt"));
        repo.save(1L, "v1");
        repo.save(1L, "v2");
        assertEquals(Optional.of("v2"), repo.findById(1L));
        assertEquals(1, repo.findAll().size());
    }

    @Test
    void invalidos(@TempDir Path dir) {
        Ej147FileBackedRepository repo =
                new Ej147FileBackedRepository(dir.resolve("notas.txt"));
        assertThrows(IllegalArgumentException.class, () -> repo.save(0L, "x"));
        assertThrows(IllegalArgumentException.class, () -> repo.save(1L, "a;b"));
        assertEquals(Optional.empty(), repo.findById(99L));
    }
}
