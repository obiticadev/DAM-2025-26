package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b03_core.Ej031ConstructorInjection.ServicioSaludo;
import static org.junit.jupiter.api.Assertions.*;

class Ej031ConstructorInjectionTest {

    @Test
    void usaLaDependenciaInyectada() {
        var s = new ServicioSaludo(() -> "Hola, {}!");
        assertEquals("Hola, Ana!", s.saludar("Ana"));
    }

    @Test
    void otraPlantilla() {
        var s = new ServicioSaludo(() -> "[{}]");
        assertEquals("[Leo]", s.saludar("Leo"));
    }
}
