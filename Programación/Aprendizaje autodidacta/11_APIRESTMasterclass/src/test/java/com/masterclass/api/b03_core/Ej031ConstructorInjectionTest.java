package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b03_core.Ej031ConstructorInjection.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    static class CircularA {
        public CircularA(CircularB b) {}
    }
    static class CircularB {
        public CircularB(CircularA a) {}
    }
    static class NoCircular {
        public NoCircular(String val) {}
    }

    static class ClaseInmutable {
        private final String campo;
        public ClaseInmutable(String campo) { this.campo = campo; }
    }

    static class ClaseMutable {
        private String campo;
        public ClaseMutable(String campo) { this.campo = campo; }
    }

    @Test
    void retoExtra01_multiplesRepos() {
        RepoSaludos r1 = () -> "Hola";
        RepoSaludos r2 = () -> "Mundo";
        var servicio = new ServicioConMultiplesRepos(List.of(r1, r2));
        assertEquals("Hola, Mundo", servicio.saludarTodos());
    }

    @Test
    void retoExtra02_dependenciaOpcional() {
        var sConRepo = new ServicioConDependenciaOpcional(Optional.of(() -> "Hola"));
        assertEquals("Hola", sConRepo.obtenerPlantilla());

        var sSinRepo = new ServicioConDependenciaOpcional(Optional.empty());
        assertEquals("Default", sSinRepo.obtenerPlantilla());
    }

    @Test
    void retoExtra03_configuracionLimite() {
        var s = new ServicioConConfiguracion(() -> "Saludo: {}", 10);
        // "Saludo: Ana" -> longitud 11 -> excede 10 -> recortar a "Saludo: An..." (o similar según lógica, ej: "Saludo: A...")
        // Probamos una coincidencia lógica del test
        String res = s.saludarConLimite("Ana");
        assertTrue(res.endsWith("..."));
        assertTrue(res.length() <= 13); // longitud límite + 3 de los puntos suspensivos
    }

    @Test
    void retoExtra04_detectarInyeccionCircular() {
        assertTrue(Ej031ConstructorInjection.detectarInyeccionCircular(CircularA.class, CircularB.class));
        assertFalse(Ej031ConstructorInjection.detectarInyeccionCircular(CircularA.class, NoCircular.class));
    }

    @Test
    void retoExtra05_composicion() {
        var s1 = new ServicioSaludo(() -> "Buenos días, {}");
        var s2 = new ServicioSaludo(() -> "¡Que tengas un buen día, {}!");
        var comp = new ServicioComposicion(s1, s2);
        assertEquals("Buenos días, Carlos. ¡Que tengas un buen día, Carlos!", comp.saludarCompuesto("Carlos"));
    }

    @Test
    void retoExtra06_lazyProxy() {
        int[] counter = {0};
        var lazy = new ServicioLazyProxy(() -> {
            counter[0]++;
            return () -> "Lazy";
        });
        assertEquals(0, counter[0], "No debe evaluarse el repo al construir");
        assertEquals("Lazy", lazy.obtenerPlantillaDiferida());
        assertEquals(1, counter[0], "Debe evaluarse tras la llamada");
    }

    @Test
    void retoExtra07_decorador() {
        RepoSaludos orig = () -> "Hola {}";
        var decorador = new ServicioDecorador(orig, "[", "]");
        assertEquals("[Hola {}]", decorador.plantilla());
    }

    @Test
    void retoExtra08_factory() {
        Map<String, RepoSaludos> factory = Map.of(
            "formal", () -> "Estimado {},",
            "casual", () -> "¡Qué pasa, {}!"
        );
        var s = new ServicioConFactory(factory);
        assertEquals("Estimado Juan,", s.saludarCon("formal", "Juan"));
        assertEquals("¡Qué pasa, Pepe!", s.saludarCon("casual", "Pepe"));
    }

    @Test
    void retoExtra09_verificarInyeccionSegura() {
        assertTrue(Ej031ConstructorInjection.verificarInyeccionSegura(ClaseInmutable.class));
        assertFalse(Ej031ConstructorInjection.verificarInyeccionSegura(ClaseMutable.class));
    }

    @Test
    void retoExtra10_filtroRepos() {
        RepoSaludos r1 = () -> "ES_Hola";
        RepoSaludos r2 = () -> "EN_Hello";
        var s = new ServicioConFiltro(List.of(r1, r2));
        assertEquals("ES_Hola", s.saludarConPrefijo("ES_"));
        assertEquals("EN_Hello", s.saludarConPrefijo("EN_"));
    }
}

