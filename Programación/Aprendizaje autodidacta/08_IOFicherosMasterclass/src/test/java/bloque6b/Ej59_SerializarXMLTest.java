package bloque6b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import jakarta.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej59 - Serializar XML")
class Ej59_SerializarXMLTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("serializarFactura: crea fichero XML")
    void serializar() throws JAXBException {
        String r = dir + "/f.xml";
        Ej59_SerializarXML.serializarFactura(r, new FacturaXML(1, 100.0));
        assertTrue(new File(r).exists());
        assertTrue(new File(r).length() > 0);
    }

    @Test @DisplayName("facturaAXmlString: contiene elementos XML")
    void aString() throws JAXBException {
        String xml = Ej59_SerializarXML.facturaAXmlString(new FacturaXML(1, 100.0));
        assertTrue(xml.contains("facturaXML") || xml.contains("FacturaXML") || xml.contains("factura"));
        assertTrue(xml.contains("100"));
    }

    @Test @DisplayName("serializarBonito: contiene saltos de linea")
    void bonito() throws JAXBException {
        String xml = Ej59_SerializarXML.serializarBonito(new FacturaXML(1, 50.0));
        assertTrue(xml.contains("\n") || xml.contains("\r"));
    }

    @Test @DisplayName("tieneAnotacion: true")
    void anotacion() { assertTrue(Ej59_SerializarXML.tieneAnotacion()); }

    @Test @DisplayName("ficheroNoVacio: true")
    void noVacio() throws JAXBException {
        String r = dir + "/f.xml";
        Ej59_SerializarXML.serializarFactura(r, new FacturaXML(1, 50.0));
        assertTrue(Ej59_SerializarXML.ficheroNoVacio(r));
    }

    @Test @DisplayName("tamanoFichero: > 0")
    void tamano() throws JAXBException {
        String r = dir + "/f.xml";
        Ej59_SerializarXML.serializarFactura(r, new FacturaXML(1, 50.0));
        assertTrue(Ej59_SerializarXML.tamanoFichero(r) > 0);
    }
}
