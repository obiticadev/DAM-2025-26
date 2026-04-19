package bloque6b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import jakarta.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej61 - Comparar JSON vs XML")
class Ej61_CompararJSONvsXMLTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("guardarEnJSON: devuelve tamano > 0")
    void json() throws IOException {
        assertTrue(Ej61_CompararJSONvsXML.guardarEnJSON(dir + "/c.json", new ProductoJSON("X", 1)) > 0);
    }

    @Test @DisplayName("guardarEnXML: devuelve tamano > 0")
    void xml() throws JAXBException {
        assertTrue(Ej61_CompararJSONvsXML.guardarEnXML(dir + "/c.xml", new FacturaXML(1, 1)) > 0);
    }

    @Test @DisplayName("contenidoJSON: no vacio")
    void contJSON() throws IOException {
        String json = Ej61_CompararJSONvsXML.contenidoJSON(new ProductoJSON("X", 1));
        assertFalse(json.isEmpty());
        assertTrue(json.contains("X"));
    }

    @Test @DisplayName("contenidoXML: no vacio")
    void contXML() throws JAXBException {
        String xml = Ej61_CompararJSONvsXML.contenidoXML(new FacturaXML(1, 100));
        assertFalse(xml.isEmpty());
    }

    @Test @DisplayName("formatoMasPequeno: devuelve JSON o XML")
    void formato() throws Exception {
        Ej61_CompararJSONvsXML.guardarEnJSON(dir + "/c.json", new ProductoJSON("X", 1));
        Ej61_CompararJSONvsXML.guardarEnXML(dir + "/c.xml", new FacturaXML(1, 1));
        String r = Ej61_CompararJSONvsXML.formatoMasPequeno(dir + "/c.json", dir + "/c.xml");
        assertTrue(r.equals("JSON") || r.equals("XML") || r.equals("IGUALES"));
    }

    @Test @DisplayName("resumenComparativo: contiene info")
    void resumen() throws Exception {
        Ej61_CompararJSONvsXML.guardarEnJSON(dir + "/c.json", new ProductoJSON("X", 1));
        Ej61_CompararJSONvsXML.guardarEnXML(dir + "/c.xml", new FacturaXML(1, 1));
        String res = Ej61_CompararJSONvsXML.resumenComparativo(dir + "/c.json", dir + "/c.xml");
        assertFalse(res.isEmpty());
    }
}
