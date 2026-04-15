package bloque4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej19 - try-with-resources Basico")
class Ej19_TryWithResourcesTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("escribirSeguro + leerSeguro: ida y vuelta")
    void escribirLeer() throws IOException {
        String ruta = dir + "/tw.txt";
        Ej19_TryWithResources.escribirSeguro(ruta, "Hola mundo");
        assertTrue(Ej19_TryWithResources.leerSeguro(ruta).contains("Hola mundo"));
    }

    @Test @DisplayName("copiarSeguro: copia lineas correctamente")
    void copiarSeguro() throws IOException {
        String orig = dir + "/o.txt";
        Ej19_TryWithResources.escribirSeguro(orig, "A\nB\nC");
        assertEquals(3, Ej19_TryWithResources.copiarSeguro(orig, dir + "/c.txt"));
    }

    @Test @DisplayName("registrarVisita: contiene datos del registro")
    void registrarVisita() throws IOException {
        String ruta = dir + "/vis.txt";
        Ej19_TryWithResources.registrarVisita(ruta, "Luna", "Ana", "Vacuna", "2024-01-01");
        String c = Ej19_TryWithResources.leerSeguro(ruta);
        assertTrue(c.contains("Luna"));
        assertTrue(c.contains("Ana"));
        assertTrue(c.contains("Vacuna"));
    }

    @Test @DisplayName("anadirLineaSeguro: no borra contenido previo")
    void anadirLinea() throws IOException {
        String ruta = dir + "/ap.txt";
        Ej19_TryWithResources.escribirSeguro(ruta, "Inicio");
        Ej19_TryWithResources.anadirLineaSeguro(ruta, "Fin");
        String c = Ej19_TryWithResources.leerSeguro(ruta);
        assertTrue(c.contains("Inicio"));
        assertTrue(c.contains("Fin"));
    }

    @Test @DisplayName("filtrarPorPrefijo: filtra correctamente")
    void filtrarPrefijo() throws IOException {
        String ruta = dir + "/pref.txt";
        Ej19_TryWithResources.escribirSeguro(ruta, "Mascota: Luna\nDueno: Ana\nMascota: Max");
        String result = Ej19_TryWithResources.filtrarPorPrefijo(ruta, "mascota");
        assertTrue(result.contains("Luna"));
        assertTrue(result.contains("Max"));
        assertFalse(result.contains("Dueno"));
    }

    @Test @DisplayName("filtrarPorPrefijo: sin coincidencias devuelve vacio")
    void filtrarPrefijo_vacio() throws IOException {
        String ruta = dir + "/pref2.txt";
        Ej19_TryWithResources.escribirSeguro(ruta, "Hola\nMundo");
        assertEquals("", Ej19_TryWithResources.filtrarPorPrefijo(ruta, "xyz"));
    }

    @Test @DisplayName("contarLegibles: cuenta correctamente")
    void contarLegibles() throws IOException {
        String r1 = dir + "/ex1.txt";
        String r2 = dir + "/ex2.txt";
        Ej19_TryWithResources.escribirSeguro(r1, "a");
        Ej19_TryWithResources.escribirSeguro(r2, "b");
        assertEquals(2, Ej19_TryWithResources.contarLegibles(
                new String[]{r1, dir + "/noexiste.txt", r2}));
    }

    @Test @DisplayName("contarLegibles: array vacio devuelve 0")
    void contarLegibles_vacio() {
        assertEquals(0, Ej19_TryWithResources.contarLegibles(new String[0]));
    }
}
