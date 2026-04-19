package bloque6b;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej60 - Deserializar XML")
class Ej60_DeserializarXMLTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() throws JAXBException {
        dir = tempDir.toString();
        JAXBContext ctx = JAXBContext.newInstance(FacturaXML.class);
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(new FacturaXML(1, 150.75), new File(dir + "/f.xml"));
    }

    @Test @DisplayName("deserializarFactura")
    void desFact() throws JAXBException {
        FacturaXML f = Ej60_DeserializarXML.deserializarFactura(dir + "/f.xml");
        assertNotNull(f);
        assertEquals(1, f.getId());
        assertEquals(150.75, f.getTotal(), 0.01);
    }

    @Test @DisplayName("desdeString")
    void desStr() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                     "<facturaXML><id>5</id><total>99.9</total></facturaXML>";
        FacturaXML f = Ej60_DeserializarXML.desdeString(xml);
        assertEquals(5, f.getId());
    }

    @Test @DisplayName("verificarIntegridad")
    void integridad() throws JAXBException {
        assertTrue(Ej60_DeserializarXML.verificarIntegridad(dir + "/vi.xml", new FacturaXML(3, 50.0)));
    }

    @Test @DisplayName("leerIdFactura")
    void leerId() throws JAXBException { assertEquals(1, Ej60_DeserializarXML.leerIdFactura(dir + "/f.xml")); }

    @Test @DisplayName("leerTotalFactura")
    void leerTotal() throws JAXBException { assertEquals(150.75, Ej60_DeserializarXML.leerTotalFactura(dir + "/f.xml"), 0.01); }

    @Test @DisplayName("tieneConstructorVacio")
    void constructor() { assertTrue(Ej60_DeserializarXML.tieneConstructorVacio()); }

    @Test @DisplayName("intentarDeserializar: fichero invalido")
    void invalido() throws IOException {
        new FileOutputStream(dir + "/bad.xml").close();
        String r = Ej60_DeserializarXML.intentarDeserializar(dir + "/bad.xml");
        assertNotEquals("OK", r);
    }
}
