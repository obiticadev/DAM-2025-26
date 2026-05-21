package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej017FunctionalInterfacesTest {

    @Test
    void transformar() {
        assertEquals(List.of(1, 4, 9),
                Ej017FunctionalInterfaces.transformar(List.of(1, 2, 3), x -> x * x));
    }

    @Test
    void filtrar() {
        assertEquals(List.of(2, 4),
                Ej017FunctionalInterfaces.filtrar(List.of(1, 2, 3, 4), x -> x % 2 == 0));
    }

    @Test
    void seguroOrElse() {
        assertEquals("ok", Ej017FunctionalInterfaces.seguroOrElse(() -> "ok", "fb"));
        assertEquals("fb", Ej017FunctionalInterfaces.seguroOrElse(() -> {
            throw new RuntimeException("boom");
        }, "fb"));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 1")
    @Test
    void retoExtra01_combinarPredicadosAND() {
        Predicate<Integer> p1 = n -> n > 5;
        Predicate<Integer> p2 = n -> n % 2 == 0;
        var andPred = Ej017FunctionalInterfaces.combinarPredicadosAND(p1, p2);
        assertTrue(andPred.test(6));
        assertFalse(andPred.test(4));
        assertFalse(andPred.test(7));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 2")
    @Test
    void retoExtra02_combinarPredicadosOR() {
        Predicate<Integer> p1 = n -> n > 5;
        Predicate<Integer> p2 = n -> n % 2 == 0;
        var orPred = Ej017FunctionalInterfaces.combinarPredicadosOR(p1, p2);
        assertTrue(orPred.test(6));
        assertTrue(orPred.test(4));
        assertTrue(orPred.test(7));
        assertFalse(orPred.test(3));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 3")
    @Test
    void retoExtra03_negarPredicado() {
        Predicate<Integer> p = n -> n > 5;
        var negated = Ej017FunctionalInterfaces.negarPredicado(p);
        assertFalse(negated.test(6));
        assertTrue(negated.test(4));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 4")
    @Test
    void retoExtra04_componerFunciones() {
        Function<Integer, Integer> f1 = n -> n * 2;
        Function<Integer, String> f2 = n -> "res: " + n;
        var comp = Ej017FunctionalInterfaces.componerFunciones(f1, f2);
        assertEquals("res: 10", comp.apply(5));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 5")
    @Test
    void retoExtra05_ejecutarYMedirTiempo() {
        long time = Ej017FunctionalInterfaces.ejecutarYMedirTiempo(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        assertTrue(time >= 10, "Debería tardar al menos 10 milisegundos");
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 6")
    @Test
    void retoExtra06_consumirLista() {
        List<String> list = List.of("a", "b");
        List<String> dest = new java.util.ArrayList<>();
        Ej017FunctionalInterfaces.consumirLista(list, dest::add);
        assertEquals(List.of("a", "b"), dest);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 7")
    @Test
    void retoExtra07_crearConSupplier() {
        Supplier<String> supplier = () -> "instancia";
        assertEquals("instancia", Ej017FunctionalInterfaces.crearConSupplier(supplier));
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 8")
    @Test
    void retoExtra08_encadenarConsumidores() {
        List<String> logs = new java.util.ArrayList<>();
        Consumer<String> c1 = s -> logs.add("c1: " + s);
        Consumer<String> c2 = s -> logs.add("c2: " + s);
        var combined = Ej017FunctionalInterfaces.encadenarConsumidores(c1, c2);
        combined.accept("hola");
        assertEquals(List.of("c1: hola", "c2: hola"), logs);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 9")
    @Test
    void retoExtra09_filtrarYTransformar() {
        List<Integer> list = List.of(1, 2, 3, 4);
        var res = Ej017FunctionalInterfaces.filtrarYTransformar(list, n -> n % 2 == 0, n -> "num " + n);
        assertEquals(List.of("num 2", "num 4"), res);
    }

    @org.junit.jupiter.api.Disabled("Activa para probar el RETO EXTRA 10")
    @Test
    void retoExtra10_obtenerSeguroConSupplier() {
        Supplier<String> success = () -> "ok";
        Supplier<String> error = () -> {
            throw new RuntimeException("boom");
        };
        Supplier<String> fallback = () -> "fallback";
        
        assertEquals("ok", Ej017FunctionalInterfaces.obtenerSeguroConSupplier(success, fallback));
        assertEquals("fallback", Ej017FunctionalInterfaces.obtenerSeguroConSupplier(error, fallback));
    }
}

