package bloque7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej37 - Path y Paths: operaciones basicas")
class Ej37_PathBasicoTest {

    @Test @DisplayName("construirRuta: une partes")
    void construirRuta() {
        String r = Ej37_PathBasico.construirRuta("temp", "bloque7", "datos.txt");
        assertTrue(r.contains("temp"));
        assertTrue(r.contains("datos.txt"));
    }

    @Test @DisplayName("nombreFichero: extrae nombre")
    void nombreFichero() {
        assertEquals("datos.txt", Ej37_PathBasico.nombreFichero("temp/bloque7/datos.txt"));
    }

    @Test @DisplayName("directorioPadre: extrae padre")
    void directorioPadre() {
        String p = Ej37_PathBasico.directorioPadre("temp/bloque7/datos.txt");
        assertNotNull(p);
        assertTrue(p.contains("bloque7"));
    }

    @Test @DisplayName("directorioPadre: sin padre devuelve null")
    void directorioPadre_sinPadre() {
        assertNull(Ej37_PathBasico.directorioPadre("datos.txt"));
    }

    @Test @DisplayName("resolver: combina base y relativa")
    void resolver() {
        String r = Ej37_PathBasico.resolver("temp/bloque7", "datos.txt");
        assertTrue(r.contains("datos.txt"));
        assertTrue(r.contains("bloque7"));
    }

    @Test @DisplayName("aAbsoluta: devuelve ruta absoluta")
    void aAbsoluta() {
        String abs = Ej37_PathBasico.aAbsoluta("temp/bloque7");
        assertTrue(abs.length() > "temp/bloque7".length());
    }

    @Test @DisplayName("contarNiveles: a/b/c.txt -> 3")
    void contarNiveles() {
        assertEquals(3, Ej37_PathBasico.contarNiveles("a/b/c.txt"));
    }

    @Test @DisplayName("normalizar: elimina . y ..")
    void normalizar() {
        String n = Ej37_PathBasico.normalizar("a/b/../c/./d.txt");
        assertFalse(n.contains(".."));
        assertTrue(n.contains("d.txt"));
    }
}
