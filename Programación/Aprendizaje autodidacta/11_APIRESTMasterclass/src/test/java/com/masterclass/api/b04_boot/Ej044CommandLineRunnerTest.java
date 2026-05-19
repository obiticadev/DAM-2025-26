package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej044CommandLineRunnerTest {

    @Test
    void procesaArgumentos() {
        var r = new Ej044CommandLineRunner();
        r.run("--seed=true", "--region=eu", "ignorame");
        assertEquals(List.of("seed=true", "region=eu"), r.log());
        assertTrue(r.ejecutado());
    }

    @Test
    void idempotente() {
        var r = new Ej044CommandLineRunner();
        r.run("--a=1");
        r.run("--b=2");
        assertEquals(List.of("a=1"), r.log(), "el segundo run no debe re-ejecutar");
    }

    @Test
    void argSinValor() {
        var r = new Ej044CommandLineRunner();
        r.run("--flag");
        assertEquals(List.of("flag="), r.log());
    }
}
