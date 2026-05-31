package com.masterclass.api.b04_boot;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;
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

    @org.junit.jupiter.api.Test
    void testPasoExtra01() throws Exception {
        var runner = Ej044CommandLineRunner.pasoExtra01();
        assertNotNull(runner);
        assertFalse(runner.isRunExecuted());
        assertTrue(runner instanceof org.springframework.boot.CommandLineRunner);
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra02() throws Exception {
        var runner = Ej044CommandLineRunner.pasoExtra02();
        assertNotNull(runner);
        assertTrue(runner instanceof org.springframework.boot.ApplicationRunner);
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra03() {
        var runner = new Ej044CommandLineRunner.HighPriorityRunner();
        int order = Ej044CommandLineRunner.pasoExtra03(runner);
        assertEquals(1, order);
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra04() {
        var runner = new Ej044CommandLineRunner.LowPriorityRunner();
        int order = Ej044CommandLineRunner.pasoExtra04(runner);
        assertEquals(2, order);
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra05() {
        var runner = Ej044CommandLineRunner.pasoExtra05();
        assertNotNull(runner);
        assertFalse(runner.isSeedEnabled());
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra06() {
        var args = new org.springframework.boot.DefaultApplicationArguments("--optionA=val1", "nonOptionA");
        var nonOptions = Ej044CommandLineRunner.pasoExtra06(args);
        assertNotNull(nonOptions);
        assertTrue(nonOptions.contains("nonOptionA"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra07() {
        var args = new org.springframework.boot.DefaultApplicationArguments("--optionA=val1", "--optionB");
        var optionNames = Ej044CommandLineRunner.pasoExtra07(args);
        assertNotNull(optionNames);
        assertTrue(optionNames.contains("optionA"));
        assertTrue(optionNames.contains("optionB"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra08() {
        var args = new org.springframework.boot.DefaultApplicationArguments("--optionA=val1", "--optionA=val2");
        var values = Ej044CommandLineRunner.pasoExtra08(args, "optionA");
        assertNotNull(values);
        assertTrue(values.contains("val1"));
        assertTrue(values.contains("val2"));
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra09() {
        var generator = Ej044CommandLineRunner.pasoExtra09(42);
        assertNotNull(generator);
        assertTrue(generator instanceof org.springframework.boot.ExitCodeGenerator);
    }

    @org.junit.jupiter.api.Test
    void testPasoExtra10() {
        org.springframework.boot.CommandLineRunner failingRunner = args -> {
            throw new Exception("Boot failed");
        };
        assertThrows(RuntimeException.class, () -> Ej044CommandLineRunner.pasoExtra10(failingRunner, "arg"));
    }
}
