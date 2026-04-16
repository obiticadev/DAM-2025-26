package bloque2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej08 - Leer Texto con FileReader")
class Ej08_LeerTextoTest {

    @TempDir
    Path tempDir;
    String fichero;

    @BeforeEach
    void setUp() throws IOException {
        fichero = tempDir.resolve("test.txt").toString();
        try (FileWriter fw = new FileWriter(fichero)) {
            fw.write("Nombre: Ana\nDNI: 12345678A\nHabitacion: 301");
        }
    }

    @Test @DisplayName("leerTodo: devuelve contenido completo")
    void leerTodo_completo() throws IOException {
        assertEquals("Nombre: Ana\nDNI: 12345678A\nHabitacion: 301",
                Ej08_LeerTexto.leerTodo(fichero));
    }

    @Test @DisplayName("leerTodo: fichero vacio devuelve cadena vacia")
    void leerTodo_vacio() throws IOException {
        String vacio = tempDir.resolve("vacio.txt").toString();
        try (FileWriter fw = new FileWriter(vacio)) { /* vacio */ }
        assertEquals("", Ej08_LeerTexto.leerTodo(vacio));
    }

    @Test @DisplayName("contarLineas: cuenta correctamente")
    void contarLineas_correcto() throws IOException {
        assertEquals(3, Ej08_LeerTexto.contarLineas(fichero));
    }

    @Test @DisplayName("contarLineas: fichero sin saltos tiene 1 linea")
    void contarLineas_sinSaltos() throws IOException {
        String una = tempDir.resolve("una.txt").toString();
        try (FileWriter fw = new FileWriter(una)) { fw.write("Solo una"); }
        assertEquals(1, Ej08_LeerTexto.contarLineas(una));
    }

    @Test @DisplayName("obtenerLinea: devuelve linea correcta")
    void obtenerLinea_correcta() throws IOException {
        assertEquals("DNI: 12345678A", Ej08_LeerTexto.obtenerLinea(fichero, 1));
    }

    @Test @DisplayName("obtenerLinea: indice fuera devuelve null")
    void obtenerLinea_fuera() throws IOException {
        assertNull(Ej08_LeerTexto.obtenerLinea(fichero, 99));
    }

    @Test @DisplayName("obtenerLinea: indice negativo lanza excepcion")
    void obtenerLinea_negativo() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej08_LeerTexto.obtenerLinea(fichero, -1));
    }

    @Test @DisplayName("contienePalabra: encuentra palabra existente")
    void contienePalabra_existe() throws IOException {
        assertTrue(Ej08_LeerTexto.contienePalabra(fichero, "ana")); // case insensitive
    }

    @Test @DisplayName("contienePalabra: no encuentra palabra inexistente")
    void contienePalabra_noExiste() throws IOException {
        assertFalse(Ej08_LeerTexto.contienePalabra(fichero, "Pedro"));
    }

    @Test @DisplayName("contarCaracter: cuenta correctamente")
    void contarCaracter_correcto() throws IOException {
        int count = Ej08_LeerTexto.contarCaracter(fichero, ':');
        assertEquals(3, count);
    }

    @Test @DisplayName("contarCaracter: devuelve 0 si no existe")
    void contarCaracter_noExiste() throws IOException {
        assertEquals(0, Ej08_LeerTexto.contarCaracter(fichero, 'z'));
    }

    @Test @DisplayName("resumenFichero: contiene datos clave")
    void resumenFichero_formato() throws IOException {
        String resumen = Ej08_LeerTexto.resumenFichero(fichero);
        assertTrue(resumen.contains("Lineas:"));
        assertTrue(resumen.contains("Caracteres:"));
    }

    @Test @DisplayName("extraerCampo: extrae Nombre correctamente")
    void extraerCampo_nombre() throws IOException {
        assertEquals("Ana", Ej08_LeerTexto.extraerCampo(fichero, "Nombre"));
    }

    @Test @DisplayName("extraerCampo: campo inexistente devuelve null")
    void extraerCampo_noExiste() throws IOException {
        assertNull(Ej08_LeerTexto.extraerCampo(fichero, "Email"));
    }
}
