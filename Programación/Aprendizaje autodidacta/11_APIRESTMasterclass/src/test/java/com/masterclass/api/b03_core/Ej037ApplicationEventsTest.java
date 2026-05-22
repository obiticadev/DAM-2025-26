package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej037ApplicationEventsTest {

    @Test
    void entregaATodosLosListeners() {
        var bus = new Ej037ApplicationEvents();
        List<Object> a = new ArrayList<>();
        List<Object> b = new ArrayList<>();
        bus.subscribe(a::add);
        bus.subscribe(b::add);
        bus.publish("E1");
        bus.publish("E2");
        assertEquals(List.of("E1", "E2"), a);
        assertEquals(List.of("E1", "E2"), b);
    }

    /*
    @org.springframework.context.annotation.Configuration
    @org.springframework.scheduling.annotation.EnableAsync
    static class ConfigEventos {
        ...
    }
    */
}
