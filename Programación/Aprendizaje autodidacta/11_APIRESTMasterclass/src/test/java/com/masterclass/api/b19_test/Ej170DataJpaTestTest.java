package com.masterclass.api.b19_test;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej170DataJpaTestTest {

    private final RepoMem170 repo = () -> Map.of("Ada", 30, "Bob", 18, "Cid", 25);

    @Test
    void filtraYOrdena() {
        assertEquals(List.of("Ada", "Cid"),
                Ej170DataJpaTest.findByEdadMayorQue(repo, 20));
    }

    @Test
    void nadieSupera() {
        assertTrue(Ej170DataJpaTest.findByEdadMayorQue(repo, 99).isEmpty());
    }

    @Test
    void repoNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej170DataJpaTest.findByEdadMayorQue(null, 10));
    }
}
